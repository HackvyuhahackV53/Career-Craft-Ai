package calls.careercraftai.service;

import java.io.IOException;

//package calls.careercraftai.service;

//package calls.careercraftai.service;

public class RateLimitExceededException extends IOException {
    private final int remainingRequests;
    private final long retryAfterSeconds;

    public RateLimitExceededException(String message, int remainingRequests, long retryAfterSeconds) {
        super(message);
        this.remainingRequests = remainingRequests;
        this.retryAfterSeconds = retryAfterSeconds;
    }

    public int getRemainingRequests() {
        return remainingRequests;
    }

    public long getRetryAfterSeconds() {
        return retryAfterSeconds;
    }
}
