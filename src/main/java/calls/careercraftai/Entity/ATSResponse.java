package calls.careercraftai.Entity;

import java.util.List;

public class ATSResponse {
    private int score;
    private List<String> missingKeywords;

    public ATSResponse() {}

    public ATSResponse(int score, List<String> missingKeywords) {
        this.score = score;
        this.missingKeywords = missingKeywords;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getMissingKeywords() {
        return missingKeywords;
    }

    public void setMissingKeywords(List<String> missingKeywords) {
        this.missingKeywords = missingKeywords;
    }
}