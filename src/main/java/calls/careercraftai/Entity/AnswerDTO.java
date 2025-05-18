package calls.careercraftai.Entity;

public class AnswerDTO {
    private String email;
    private String question;
    private String answer;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public AnswerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AnswerDTO(String email, String question, String answer) {
		super();
		this.email = email;
		this.question = question;
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "AnswerDTO [email=" + email + ", question=" + question + ", answer=" + answer + "]";
	}

    // Constructors, Getters & Setters
    
}

