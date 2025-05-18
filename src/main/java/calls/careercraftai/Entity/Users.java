package calls.careercraftai.Entity;

import jakarta.persistence.Column;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//@Entity
//public class Users {
//	
//	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//     Long id;
//
//    
//     String firstName;
//
//     String middleName;
//
//    
//    String lastName;
//
//    
//   String phoneNumber;
//
//    
//   String dateOfBirth;
//
//   String platform;
//    String email;
//
//    
//     String password;
//     String gender;
//
//
//   
//    boolean rememberMe;
//
//  
//    boolean termsAccepted;
//
//    boolean isPremium;
//
//	public Users() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public Users(Long id, String firstName, String middleName, String lastName, String phoneNumber, String dateOfBirth,
//			String platform, String email, String password, String gender, boolean rememberMe, boolean termsAccepted,
//			boolean isPremium) {
//		super();
//		this.id = id;
//		this.firstName = firstName;
//		this.middleName = middleName;
//		this.lastName = lastName;
//		this.phoneNumber = phoneNumber;
//		this.dateOfBirth = dateOfBirth;
//		this.platform = platform;
//		this.email = email;
//		this.password = password;
//		this.gender = gender;
//		this.rememberMe = rememberMe;
//		this.termsAccepted = termsAccepted;
//		this.isPremium = isPremium;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getMiddleName() {
//		return middleName;
//	}
//
//	public void setMiddleName(String middleName) {
//		this.middleName = middleName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	public String getDateOfBirth() {
//		return dateOfBirth;
//	}
//
//	public void setDateOfBirth(String dateOfBirth) {
//		this.dateOfBirth = dateOfBirth;
//	}
//
//	public String getPlatform() {
//		return platform;
//	}
//
//	public void setPlatform(String platform) {
//		this.platform = platform;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public boolean isRememberMe() {
//		return rememberMe;
//	}
//
//	public void setRememberMe(boolean rememberMe) {
//		this.rememberMe = rememberMe;
//	}
//
//	public boolean isTermsAccepted() {
//		return termsAccepted;
//	}
//
//	public void setTermsAccepted(boolean termsAccepted) {
//		this.termsAccepted = termsAccepted;
//	}
//
//	public boolean isPremium() {
//		return isPremium;
//	}
//
//	public void setPremium(boolean isPremium) {
//		this.isPremium = isPremium;
//	}
//
//	@Override
//	public String toString() {
//		return "Users [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
//				+ ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", platform=" + platform
//				+ ", email=" + email + ", password=" + password + ", gender=" + gender + ", rememberMe=" + rememberMe
//				+ ", termsAccepted=" + termsAccepted + ", isPremium=" + isPremium + "]";
//	}
//	
//
//}

//package calls.careercraftai.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Users {
	
	@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
   Long id;

  
   String username;

  // String middleName;

  
  //String lastName;

  
 //String phoneNumber;

  
 //String dateOfBirth;

 //String platform;
  String email;

  
   String password;
   //String gender;
   
   
   



public Users(Long id, String username, String email, String password) {
	super();
	this.id = id;
	this.username = username;
	this.email = email;
	this.password = password;
}


public Users() {
	super();
	// TODO Auto-generated constructor stub
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


@Override
public String toString() {
	return "Users [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
}

}
 
  


 
	





