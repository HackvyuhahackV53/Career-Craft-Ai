package calls.careercraftai.Controller;

import calls.careercraftai.Entity.Education;
import calls.careercraftai.Entity.Experience;
import calls.careercraftai.Entity.Resume;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/resume-builder")
public class ResumeBuilderController {

    @GetMapping
    public String showResumeBuilder(Model model) {
        Resume resume = new Resume();
        resume.setTemplate("Professional");
        
        // Initialize with empty sections
        if (resume.getExperiences().isEmpty()) {
            resume.getExperiences().add(new Experience());
        }
        if (resume.getEducations().isEmpty()) {
            resume.getEducations().add(new Education());
        }
        
        model.addAttribute("resume", resume);
        model.addAttribute("templates", getResumeTemplates());
        model.addAttribute("pageTitle", "Resume Builder | CareerCraft AI");
        return "resume-builder";
    }

    @PostMapping("/generate-summary")
    @ResponseBody
    public String generateAISummary(@RequestParam String skills, @RequestParam String experience) {
        return generateSummaryWithAI(skills, experience);
    }

    @PostMapping("/save")
    public String saveResume(@ModelAttribute Resume resume, Model model) {
        model.addAttribute("success", "Resume saved successfully!");
        model.addAttribute("resume", resume);
        return "resume-builder";
    }

    private String generateSummaryWithAI(String skills, String experience) {
        return "Results-oriented professional with " + experience + 
               " years of experience in " + skills + ". " +
               "Adept at problem-solving and team collaboration. " +
               "Looking to leverage skills in a challenging new role.";
    }

    private List<String> getResumeTemplates() {
        return Arrays.asList("Professional", "Modern", "Creative", "Minimalist");
    }
}
