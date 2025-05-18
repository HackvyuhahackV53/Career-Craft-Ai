package calls.careercraftai.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import calls.careercraftai.Entity.ATSResponse;

public interface ResumeService {
    ATSResponse analyze(MultipartFile file) throws IOException;
    List<String> getSuggestions(String resumeText);
}
