package calls.careercraftai.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import calls.careercraftai.Entity.ATSResponse;
import calls.careercraftai.Entity.SuggestionRequest;
import calls.careercraftai.service.ResumeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ATSResponse> analyzeResume(@RequestParam("file") org.springframework.web.multipart.MultipartFile file) throws IOException {
        ATSResponse resp = resumeService.analyze(file);
        return ResponseEntity.ok(resp);
    }

    @PostMapping(value = "/suggestions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getSuggestions(@RequestBody SuggestionRequest req) {
        List<String> tips = resumeService.getSuggestions(req.getResumeText());
        return ResponseEntity.ok(tips);
    }
}
