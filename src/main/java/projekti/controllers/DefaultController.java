package projekti.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class DefaultController {
    
    @GetMapping("/")
    public String getIndex(Model model) {
        return "redirect:/users";
    }

    @GetMapping("/login") 
    public String getLogin() {
        return "login";
    }
}