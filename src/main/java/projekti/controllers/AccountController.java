package projekti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import projekti.repositories.AccountRepository;

@Controller
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/users")
    public String getIndex(Model model) {
        model.addAttribute("users", accountRepository.findAll());
        
        return "users/index";
    }

    @GetMapping("/profile/{userProfileName}")
    public String getUserProfile(Model model, @PathVariable String userProfileName) throws Exception {
        model.addAttribute("profileName", userProfileName);
        model.addAttribute("profile", accountRepository.findByProfilename(userProfileName));

        return "users/profile";
    }
}