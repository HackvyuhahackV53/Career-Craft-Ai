package calls.careercraftai.Entity;

public class QuestionDTO {
    private String question;
    private String category;

    public QuestionDTO(String question, String category) {
        this.question = question;
        this.category = category;
    }

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "QuestionDTO [question=" + question + ", category=" + category + "]";
	}

    // Getters & Setters
    
    
}

