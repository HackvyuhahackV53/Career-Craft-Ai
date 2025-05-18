package calls.careercraftai.service;

//package com.example.resumeanalyzer.service;

//mport com.example.resumeanalyzer.dto.AnalysisResult;
import org.springframework.web.multipart.MultipartFile;

import calls.careercraftai.Entity.AnalysisResult;

public interface ResumeAnalysisService {
    AnalysisResult analyzeResume(MultipartFile file) throws Exception;
}
