package calls.careercraftai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import calls.careercraftai.Entity.UserResponse;

@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Integer> {}
