package calls.careercraftai.service;

import calls.careercraftai.Entity.Users;

//import com.example.Demo.entities.Users;

public interface UsersService {
	
	public String addUser(Users user);
	public boolean emailExist(String email);
	public boolean validateUser(String email,String password);
	//public String getRole(String email);
	public Users getUser(String email);
	public void updateUser(Users user);

}
