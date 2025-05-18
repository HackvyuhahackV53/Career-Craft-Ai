package calls.careercraftai.Controller;

import calls.careercraftai.Entity.AnswerDTO;
import calls.careercraftai.service.OpenAIService;
import calls.careercraftai.service.RateLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cat")
public class CATController {
    
    private final OpenAIService openAIService;

    @Autowired
    public CATController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitAnswers(@RequestBody List<AnswerDTO> answers) {
        try {
            String combinedResponses = combineResponses(answers);
            String recommendation = openAIService.getCareerRecommendation(combinedResponses);
            return ResponseEntity.ok(recommendation);
            
        } catch (RateLimitExceededException e) {
            return buildRateLimitResponse(e);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                    "error", "service_unavailable",
                    "message", "OpenAI service is currently unavailable"
                ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "error", "internal_error",
                    "message", "An unexpected error occurred"
                ));
        }
    }

    private ResponseEntity<Map<String, Object>> buildRateLimitResponse(RateLimitExceededException e) {
        long retryAfter = e.getRetryAfterSeconds() > 0 ? e.getRetryAfterSeconds() : 60;
        
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
            .header("Retry-After", String.valueOf(retryAfter))
            .body(Map.of(
                "error", "rate_limit_exceeded",
                "message", e.getMessage(),
                "remaining_requests", e.getRemainingRequests(),
                "retry_after_seconds", retryAfter,
                "timestamp", System.currentTimeMillis()
            ));
    }

    @GetMapping("/rate-limit-status")
    public ResponseEntity<Map<String, Object>> getRateLimitStatus() {
        return ResponseEntity.ok(Map.of(
            "remaining_requests", openAIService.getRemainingRequests(),
            "reset_in_seconds", openAIService.getResetTimeSeconds(),
            "timestamp", System.currentTimeMillis()
        ));
    }

    private String combineResponses(List<AnswerDTO> answers) {
        return answers.stream()
            .map(dto -> "Q: " + dto.getQuestion() + "\nA: " + dto.getAnswer())
            .collect(Collectors.joining("\n\n"));
    }
}