package calls.careercraftai.Entity;

//package calls.careercraftai.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Resume {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String summary;
    private String template;  // Make sure this field exists
    
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences;
    
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations;
    
    @ElementCollection
    private List<String> skills;
    
    @ElementCollection
    private List<String> certifications;
    
    // Add proper getter and setter for template
    public String getTemplate() {
        return template;
    }
    
    public void setTemplate(String template) {
        this.template = template;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public List<Education> getEducations() {
		return educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<String> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<String> certifications) {
		this.certifications = certifications;
	}

	public Resume(Long id, String fullName, String email, String phone, String address, String summary, String template,
			List<Experience> experiences, List<Education> educations, List<String> skills,
			List<String> certifications) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.summary = summary;
		this.template = template;
		this.experiences = experiences;
		this.educations = educations;
		this.skills = skills;
		this.certifications = certifications;
	}

	public Resume() {
        this.experiences = new ArrayList<>();
        this.educations = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.certifications = new ArrayList<>();
    }


	@Override
	public String toString() {
		return "Resume [id=" + id + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", summary=" + summary + ", template=" + template + ", experiences=" + experiences
				+ ", educations=" + educations + ", skills=" + skills + ", certifications=" + certifications + "]";
	}
	
	// Helper methods for adding items
    public void addExperience(Experience experience) {
        this.experiences.add(experience);
        experience.setResume(this);
    }
    
    public void addEducation(Education education) {
        this.educations.add(education);
        education.setResume(this);
    }
    
    // ... rest of your getters and setters

}
