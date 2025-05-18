package calls.careercraftai.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class NavController {
	
	@GetMapping("/map-register")
	public String registerMapping() {
		return "index";
	}
	
	@GetMapping("/map-login")
	public String loginMapping() {
		return "index";
	}
	
	
	
	
	@GetMapping("/home")
	public String HomeMapping() {
		return "home";
	}
	
	
	
	@GetMapping("/resume-analyzer")
	public String ResumeAnalyserMapping() {
		return "resume_analyzer";
	}
	
	
	
	@GetMapping("/career-test")
	public String careerTestrMapping() {
		return "CAT.html";
	}
	
	
	@GetMapping("/career-advice")
	public String careerAdviceMapping() {
		return "careerAdvice.html";
	}
}
