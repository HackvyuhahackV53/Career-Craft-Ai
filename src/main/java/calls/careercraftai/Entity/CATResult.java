package calls.careercraftai.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CATResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userEmail;
    private String careerDomain;

    @Column(columnDefinition = "TEXT")
    private String recommendedPaths;

    private int score;
    private LocalDateTime timestamp = LocalDateTime.now();

    // Constructors
    public CATResult() {}
    public CATResult(String userEmail, String careerDomain, String recommendedPaths, int score) {
        this.userEmail = userEmail;
        this.careerDomain = careerDomain;
        this.recommendedPaths = recommendedPaths;
        this.score = score;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getCareerDomain() {
		return careerDomain;
	}
	public void setCareerDomain(String careerDomain) {
		this.careerDomain = careerDomain;
	}
	public String getRecommendedPaths() {
		return recommendedPaths;
	}
	public void setRecommendedPaths(String recommendedPaths) {
		this.recommendedPaths = recommendedPaths;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "CATResult [id=" + id + ", userEmail=" + userEmail + ", careerDomain=" + careerDomain
				+ ", recommendedPaths=" + recommendedPaths + ", score=" + score + ", timestamp=" + timestamp + "]";
	}

    // Getters & Setters
    
}

