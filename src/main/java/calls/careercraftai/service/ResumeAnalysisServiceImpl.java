package calls.careercraftai.service;

//package com.example.resumeanalyzer.service.impl;

//import com.example.resumeanalyzer.dto.AnalysisResult;
//import com.example.resumeanalyzer.service.ResumeAnalysisService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import calls.careercraftai.Entity.AnalysisResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ResumeAnalysisServiceImpl implements ResumeAnalysisService {

    @Override
    public AnalysisResult analyzeResume(MultipartFile file) throws Exception {
        String content = extractTextFromFile(file);
        int score = calculateScore(content);
        List<String> suggestions = generateSuggestions(content, score);
        
        return new AnalysisResult(score, suggestions);
    }

    private String extractTextFromFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String content = "";

        try (InputStream inputStream = file.getInputStream()) {
            if (fileName != null && fileName.toLowerCase().endsWith(".pdf")) {
                try (PDDocument document = PDDocument.load(inputStream)) {
                    PDFTextStripper stripper = new PDFTextStripper();
                    content = stripper.getText(document);
                }
            } else if (fileName != null && (fileName.toLowerCase().endsWith(".doc") || fileName.toLowerCase().endsWith(".docx"))) {
                try (XWPFDocument document = new XWPFDocument(inputStream)) {
                    XWPFWordExtractor extractor = new XWPFWordExtractor(document);
                    content = extractor.getText();
                }
            }
        }

        return content;
    }

    private int calculateScore(String content) {
        int score = 0;
        
        // Check for keywords (adjust these based on your requirements)
        String[] keywords = {"experience", "education", "skills", "projects", "achievements", 
                            "certifications", "leadership", "teamwork", "problem solving"};
        
        // Check for sections
        if (Pattern.compile("(?i)\\bwork experience\\b|\\bprofessional experience\\b").matcher(content).find()) {
            score += 20;
        }
        if (Pattern.compile("(?i)\\beducation\\b").matcher(content).find()) {
            score += 15;
        }
        if (Pattern.compile("(?i)\\bskills\\b").matcher(content).find()) {
            score += 15;
        }
        
        // Check for keywords
        int keywordCount = 0;
        for (String keyword : keywords) {
            if (content.toLowerCase().contains(keyword.toLowerCase())) {
                keywordCount++;
            }
        }
        score += Math.min(20, keywordCount * 2); // Max 20 points for keywords
        
        // Check length (very basic check)
        int wordCount = content.split("\\s+").length;
        if (wordCount > 300) {
            score += 10; // Good length
        } else if (wordCount > 150) {
            score += 5; // Minimum acceptable
        }
        
        // Check contact information
        if (Pattern.compile("(?i)\\bphone\\b|\\bemail\\b|\\bcontact\\b").matcher(content).find()) {
            score += 10;
        }
        
        // Check for action verbs
        String[] actionVerbs = {"achieved", "managed", "developed", "led", "implemented", 
                               "improved", "created", "designed", "increased", "reduced"};
        int actionVerbCount = 0;
        for (String verb : actionVerbs) {
            if (content.toLowerCase().contains(verb.toLowerCase())) {
                actionVerbCount++;
            }
        }
        score += Math.min(10, actionVerbCount); // Max 10 points for action verbs
        
        // Ensure score is between 0-100
        return Math.min(100, Math.max(0, score));
    }

    private List<String> generateSuggestions(String content, int score) {
        List<String> suggestions = new ArrayList<>();
        
        // Check for missing sections
        if (!Pattern.compile("(?i)\\bwork experience\\b|\\bprofessional experience\\b").matcher(content).find()) {
            suggestions.add("Add a 'Work Experience' or 'Professional Experience' section");
        }
        if (!Pattern.compile("(?i)\\beducation\\b").matcher(content).find()) {
            suggestions.add("Add an 'Education' section");
        }
        if (!Pattern.compile("(?i)\\bskills\\b").matcher(content).find()) {
            suggestions.add("Add a 'Skills' section highlighting your technical and soft skills");
        }
        
        // Check for contact info
        if (!Pattern.compile("(?i)\\bphone\\b|\\bemail\\b|\\bcontact\\b").matcher(content).find()) {
            suggestions.add("Include your contact information (phone, email)");
        }
        
        // Check length
        int wordCount = content.split("\\s+").length;
        if (wordCount < 150) {
            suggestions.add("Your resume seems too short. Consider adding more details about your experience and skills");
        } else if (wordCount > 500) {
            suggestions.add("Your resume might be too long. Try to keep it concise and relevant");
        }
        
        // Check for action verbs
        String[] actionVerbs = {"achieved", "managed", "developed", "led", "implemented", 
                               "improved", "created", "designed", "increased", "reduced"};
        int actionVerbCount = 0;
        for (String verb : actionVerbs) {
            if (content.toLowerCase().contains(verb.toLowerCase())) {
                actionVerbCount++;
            }
        }
        if (actionVerbCount < 3) {
            suggestions.add("Use more action verbs to describe your accomplishments (e.g., 'achieved', 'managed', 'developed')");
        }
        
        // Generic suggestions based on score
        if (score < 40) {
            suggestions.add("Consider restructuring your resume to include all key sections: Experience, Education, Skills");
            suggestions.add("Use bullet points to make your resume more readable");
        } else if (score < 60) {
            suggestions.add("Improve your resume by quantifying achievements (e.g., 'Increased sales by 20%')");
        } else if (score < 80) {
            suggestions.add("Tailor your resume more specifically to the job you're applying for");
        }
        
        return suggestions;
    }
}
