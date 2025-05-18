package calls.careercraftai.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import calls.careercraftai.Entity.ATSResponse;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final OpenAIClient openAIClient;

    public ResumeServiceImpl(OpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
    }

    @Override
    public ATSResponse analyze(MultipartFile file) throws IOException {
        String text = extractText(file);
        int score = calculateScore(text);
        List<String> missingKeywords = findMissingKeywords(text);
        return new ATSResponse(score, missingKeywords);
    }

    @Override
    public List<String> getSuggestions(String resumeText) {
        return openAIClient.getSmartSuggestions(resumeText);
    }

    private String extractText(MultipartFile file) throws IOException {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    private int calculateScore(String text) {
        int score = 0;
        if (text.contains("Education")) score += 20;
        if (text.contains("Experience")) score += 20;
        if (text.contains("Skills")) score += 20;
        if (text.contains("Projects")) score += 20;
        if (text.contains("Contact")) score += 20;
        return score;
    }

    private List<String> findMissingKeywords(String text) {
        List<String> required = List.of("Java", "Spring Boot", "SQL", "Teamwork", "Leadership");
        return required.stream()
                       .filter(k -> !text.toLowerCase().contains(k.toLowerCase()))
                       .collect(Collectors.toList());
    }
}