package calls.careercraftai.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import okhttp3.*;

@Service
public class OpenAIService {
    private static final Logger logger = LoggerFactory.getLogger(OpenAIService.class);
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    
    // Enhanced rate limit tracking with timestamp
    private final AtomicInteger rateLimitRemaining = new AtomicInteger(-1);
    private final AtomicLong rateLimitResetTimestamp = new AtomicLong(-1);
    
    @Value("${openai.api.key}")
    private String apiKey;

    private final OkHttpClient client;

    public OpenAIService() {
        this.client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(this::rateLimitInterceptor)
            .build();
    }

    private Response rateLimitInterceptor(Interceptor.Chain chain) throws IOException {
        long currentTime = System.currentTimeMillis() / 1000;
        long resetTime = rateLimitResetTimestamp.get();
        
        // If we've hit the limit and reset time hasn't passed yet
        if (rateLimitRemaining.get() <= 0 && resetTime > currentTime) {
            long waitTime = resetTime - currentTime;
            logger.warn("Rate limit reached. Waiting {} seconds", waitTime);
            try {
                TimeUnit.SECONDS.sleep(waitTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Request interrupted while waiting for rate limit reset");
            }
        }
        return chain.proceed(chain.request());
    }

    @Retryable(value = {RateLimitExceededException.class, IOException.class}, 
               maxAttempts = 5,
               backoff = @Backoff(delay = 1000, multiplier = 2))
    public String getCareerRecommendation(String userResponses) throws IOException {
        try {
            JSONObject requestBody = buildRequestBody(userResponses);
            Request request = buildRequest(requestBody);

            try (Response response = client.newCall(request).execute()) {
                updateRateLimits(response.headers());
                
                if (!response.isSuccessful()) {
                    handleErrorResponse(response);
                }

                return parseResponse(response.body().string());
            }
        } catch (Exception e) {
            logger.error("OpenAI API request failed", e);
            throw e;
        }
    }

    private JSONObject buildRequestBody(String userResponses) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("temperature", 0.7);
        
        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", "Analyze these career responses:\n" + userResponses);
        messages.put(message);
        requestBody.put("messages", messages);
        return requestBody;
    }

    private Request buildRequest(JSONObject requestBody) {
        return new Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .post(RequestBody.create(requestBody.toString(), JSON))
            .addHeader("Authorization", "Bearer " + apiKey)
            .addHeader("Content-Type", "application/json")
            .build();
    }

    private void updateRateLimits(Headers headers) {
        try {
            String remaining = headers.get("x-ratelimit-remaining-requests");
            String reset = headers.get("x-ratelimit-reset-requests");
            
            if (remaining != null) {
                rateLimitRemaining.set(Integer.parseInt(remaining));
            }
            if (reset != null) {
                // Convert reset seconds to absolute timestamp
                long resetSeconds = Long.parseLong(reset);
                rateLimitResetTimestamp.set((System.currentTimeMillis() / 1000) + resetSeconds);
            }
        } catch (Exception e) {
            logger.warn("Failed to parse rate limit headers", e);
        }
    }

    private void handleErrorResponse(Response response) throws IOException {
        String errorBody = response.body() != null ? response.body().string() : "No error body";
        
        if (response.code() == 429) {
            logger.warn("Rate limit exceeded. Headers: {}", response.headers());
            throw new RateLimitExceededException(
                "Rate limit exceeded. Please try again later.",
                rateLimitRemaining.get(),
                rateLimitResetTimestamp.get() - (System.currentTimeMillis() / 1000)
            );
        }
        throw new IOException("API request failed with code " + response.code() + ": " + errorBody);
    }

    private String parseResponse(String responseBody) {
        try {
            JSONObject json = new JSONObject(responseBody);
            return json.getJSONArray("choices")
                     .getJSONObject(0)
                     .getJSONObject("message")
                     .getString("content");
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse OpenAI response", e);
        }
    }

    // Getters for rate limit status
    public int getRemainingRequests() { return rateLimitRemaining.get(); }
    public long getResetTimeSeconds() { 
        long remaining = rateLimitResetTimestamp.get() - (System.currentTimeMillis() / 1000);
        return remaining > 0 ? remaining : 0;
    }
}