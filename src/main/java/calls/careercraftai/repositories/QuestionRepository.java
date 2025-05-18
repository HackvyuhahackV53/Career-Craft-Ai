package calls.careercraftai.repositories;

//ackage calls.careercraftai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import calls.careercraftai.entity.Question;

import calls.careercraftai.Entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
