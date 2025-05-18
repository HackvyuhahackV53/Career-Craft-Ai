package calls.careercraftai.repositories;

//package calls.careercraftai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import calls.careercraftai.entity.Answer;

import calls.careercraftai.Controller.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
