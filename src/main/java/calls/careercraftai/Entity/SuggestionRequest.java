package calls.careercraftai.Entity;

public class SuggestionRequest {
    private String resumeText;

    public SuggestionRequest() {}
    public SuggestionRequest(String resumeText) {
        this.resumeText = resumeText;
    }

    public String getResumeText() {
        return resumeText;
    }
    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }
}

