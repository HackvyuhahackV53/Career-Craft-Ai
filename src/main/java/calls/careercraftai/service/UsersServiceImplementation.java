package calls.careercraftai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.Demo.services.UsersService;

import calls.careercraftai.Entity.Users;
import calls.careercraftai.repositories.UsersRepository;


@Service
public class UsersServiceImplementation implements UsersService {
	
	@Autowired
	UsersRepository repository;
	@Override
	public String addUser(Users user) {
		repository.save(user);
		return "user is created";
	}
	
	@Override
	public boolean emailExist(String email) {
		// TODO Auto-generated method stub
		if(repository.findByEmail(email)==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users user=repository.findByEmail(email);
		String db_password=user.getPassword();
		if(db_password.equals(password)) {
			return true;
		}else {
			return false;
		}
		
	}

//	@Override
//	public String getRole(String email) {
//		Users user=repository.findByEmail(email);
//		String role=user.getRole();
//		return role;
//	}

	@Override
	public Users getUser(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		repository.save(user);
		
	}
	//"key": "rzp_test_m82Zp7tdMyP7Kw",s

}
