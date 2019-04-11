package projekti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import projekti.repositories.UserRepository;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String getIndex(Model model) {
        model.addAttribute("users", userRepository.findAll());
        
        return "users/index";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {        
        return "login";
    }

    @GetMapping("/logout")
    public String getLogout(Model model) {        
        return "login";
    }

    @GetMapping("/profile/{userProfileName}")
    public String getUserProfile(Model model, @PathVariable String userProfileName) throws Exception {
        model.addAttribute("profileName", userProfileName);
        model.addAttribute("profile", userRepository.findByProfileName(userProfileName));

        return "users/profile";
    }
}