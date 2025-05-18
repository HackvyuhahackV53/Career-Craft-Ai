package calls.careercraftai.Controller;

//package calls.careercraftai.controller;

//import calls.careercraftai.entity.Question;
//import calls.careercraftai.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import calls.careercraftai.Entity.Question;
import calls.careercraftai.repositories.QuestionRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5500") // or wherever your HTML runs
@RestController
@RequestMapping("/api/cat")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepo;

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }
}

