package calls.careercraftai.Controller;

import java.util.ArrayList;
import java.util.List;

public class ATSAnalyzer {

    public static int calculateScore(String text) {
        int score = 0;

        if (text == null || text.isEmpty()) return 0;

        if (text.toLowerCase().contains("contact")) score += 20;
        if (text.toLowerCase().contains("skills")) score += 20;
        if (text.toLowerCase().contains("experience")) score += 30;
        if (text.toLowerCase().contains("education")) score += 20;

        if (text.length() > 500) score += 10; // Good length

        return Math.min(score, 100);
    }

    public static List<String> getSuggestions(String text) {
        List<String> suggestions = new ArrayList<>();

        if (!text.toLowerCase().contains("contact"))
            suggestions.add("Add a clear contact information section.");

        if (!text.toLowerCase().contains("skills"))
            suggestions.add("Include a skills section highlighting relevant skills.");

        if (!text.toLowerCase().contains("experience"))
            suggestions.add("Add detailed work experience with accomplishments.");

        if (!text.toLowerCase().contains("education"))
            suggestions.add("Mention your educational qualifications.");

        if (text.length() < 500)
            suggestions.add("Consider adding more relevant details to improve resume content.");

        return suggestions;
    }
}

