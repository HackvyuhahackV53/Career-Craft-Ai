package calls.careercraftai.Controller;

//package com.example.resumeanalyzer.controller;

//import com.example.resumeanalyzer.dto.AnalysisResult;
//import com.example.resumeanalyzer.service.ResumeAnalysisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import calls.careercraftai.Entity.AnalysisResult;
import calls.careercraftai.service.ResumeAnalysisService;

@Controller
public class ResumeAnalyzerController {

    private final ResumeAnalysisService resumeAnalysisService;

    public ResumeAnalyzerController(ResumeAnalysisService resumeAnalysisService) {
        this.resumeAnalysisService = resumeAnalysisService;
    }

    

    @PostMapping("/analyze")
    public String analyzeResume(@RequestParam("resume") MultipartFile file, Model model) {
        try {
            AnalysisResult result = resumeAnalysisService.analyzeResume(file);
            model.addAttribute("score", result.getScore());
            model.addAttribute("suggestions", result.getSuggestions());
        } catch (Exception e) {
            model.addAttribute("error", "Error analyzing resume: " + e.getMessage());
        }
        return "index";
    }
}

