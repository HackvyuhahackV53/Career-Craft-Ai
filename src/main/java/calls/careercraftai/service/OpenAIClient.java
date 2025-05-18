package calls.careercraftai.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
@Component
public class OpenAIClient {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.endpoint}")
    private String endpoint;

    private final RestTemplate rest;

    public OpenAIClient(RestTemplateBuilder builder) {
        this.rest = builder.build();
    }

    @PostConstruct
    public void verify() {
        System.out.println("🔑 OpenAI key loaded? " + (apiKey != null && !apiKey.isBlank()));
    }

    public List<String> getSmartSuggestions(String resumeText) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String,Object> payload = Map.of(
            "model", "gpt-4",
            "messages", List.of(
                Map.of("role","system","content","You are a professional resume coach."),
                Map.of("role","user","content","Review the following resume and give suggestions:\n" + resumeText)
            )
        );

        HttpEntity<Map<String,Object>> req = new HttpEntity<>(payload, headers);
        ResponseEntity<Map> resp = rest.postForEntity(endpoint, req, Map.class);

        @SuppressWarnings("unchecked")
        Map<String,Object> choice = ((List<Map<String,Object>>)resp.getBody().get("choices")).get(0);
        @SuppressWarnings("unchecked")
        Map<String,Object> message = (Map<String,Object>) choice.get("message");
        String content = (String) message.get("content");

        return Arrays.asList(content.split("\\r?\\n"));
    }
}
