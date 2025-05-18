package calls.careercraftai.Entity;

//package calls.careercraftai.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Experience {
    
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String jobTitle;
	    private String company;
	    private String duration;
	    private String description;
	    
	    @ManyToOne
	    @JoinColumn(name = "resume_id")
	    private Resume resume;
	    
	
    
    public Experience() {
			super();
			// TODO Auto-generated constructor stub
		}

	public Experience(String jobTitle, String company, String duration, String description) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.duration = duration;
        this.description = description;
    }
    
    // Getters and setters for all fields
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getJobTitle() {
        return jobTitle;
    }
    
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Resume getResume() {
        return resume;
    }
    
    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
