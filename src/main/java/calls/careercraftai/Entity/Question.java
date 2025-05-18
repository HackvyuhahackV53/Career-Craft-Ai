package calls.careercraftai.Entity;

//package calls.careercraftai.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String category;

    // Constructors
    public Question() {}

    public Question(String question, String category) {
        this.question = question;
        this.category = category;
    }

    // Getters and Setters
    public Long getId() {
        return id;
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
}

