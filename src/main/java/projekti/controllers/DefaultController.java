package projekti.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class DefaultController {
    
    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("loggedUserProfileStr", "MrSample");
        return "index";
    }
}