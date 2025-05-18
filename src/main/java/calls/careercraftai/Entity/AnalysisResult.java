package calls.careercraftai.Entity;

//package com.example.resumeanalyzer.dto;

import java.util.List;

public class AnalysisResult {
    private int score;
    private List<String> suggestions;

    public AnalysisResult(int score, List<String> suggestions) {
        this.score = score;
        this.suggestions = suggestions;
    }

    // Getters and Setters
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}