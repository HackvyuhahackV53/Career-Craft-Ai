package calls.careercraftai.Controller;

//package calls.careercraftai.controller;

import calls.careercraftai.service.CareerAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/career")
public class CareerController {

    @Autowired
    private CareerAIService careerAIService;

    @PostMapping("/advice")
    public String getAdvice(@RequestBody String userInput) {
        return careerAIService.getCareerAdvice(userInput);
    }
    
    @PostMapping("/submit")
    public String handleFormSubmission(@RequestParam("name") String name,
                                       @RequestParam("email") String email,
                                       Model model) {
        // Process the input data
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        return "result";  // returns result.html page
    }
}


