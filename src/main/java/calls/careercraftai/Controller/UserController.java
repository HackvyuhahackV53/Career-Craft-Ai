package calls.careercraftai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import calls.careercraftai.Entity.Users;
import calls.careercraftai.service.UsersService;
import jakarta.servlet.http.HttpSession;
@Controller
public class UserController {
	
	
	@Autowired
	UsersService userv;
	
	
	
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user)
		{
		boolean userStatus=userv.emailExist(user.getEmail());
		if(userStatus==false) {
			userv.addUser(user);
			return "index";
			}
		else {
			return "registerfail";
		}
	
		
	}
	
//	@PostMapping("/login")
//	public String validateUser( String email, String password) {
//		boolean loginStatus=userv.validateUser(email,password);
//		if(loginStatus==true) {
//			if(userv.getRole(email).equals("admin")) {
//			return "adminhome";
//			}else 
//			{
//				return "customer";
//			}
//		}
//		else {
//			return "loginfail";
//		}
//		
//	}
	
	
	
	@PostMapping("/login")
	public String validateUser(@RequestParam String email,
			@RequestParam String password, HttpSession session)
	{
		//invoking validateUser() in service
		if(userv.validateUser(email, password) == true)
		{
			
		
				return "home";
		}
		else
		{
			return "login";
		}
	}
	
	
	
	
}
