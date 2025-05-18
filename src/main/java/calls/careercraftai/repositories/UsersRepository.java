package calls.careercraftai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import calls.careercraftai.Entity.Users;

//import com.example.Demo.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    // Add custom query methods if needed
	public Users  findByEmail(String email);

	public Users getUserByusername(String email);
}
