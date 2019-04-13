package projekti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import projekti.models.Account;
import projekti.repositories.AccountRepository;

@Controller
public class AccountController {
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/users")
    public String getIndex(Model model) {

        model.addAttribute("users", accountRepository.findAll());
        model.addAttribute("loggedUserProfileStr", "MrSample");
        
        return "users/index";
    }

    @GetMapping("/users/{profileName}")
    public String getProfile(Model model, @PathVariable String profileName) throws Exception {
        model.addAttribute("profileName", profileName);
        model.addAttribute("profile", accountRepository.findByProfileName(profileName));

        return "users/profile";
    }

    @GetMapping("/users/register")
    public String getRegisterAccount() {
        return "users/register";
    }

    @PostMapping("/users/register")
    public String postRegisterAccount(@RequestParam String name, @RequestParam String profileName, @RequestParam String username, @RequestParam String password, @RequestParam String password2) {
        if(accountRepository.findByUsername(username) != null) {
            return "redirect:/users/register?uexists";
        }

        if(accountRepository.findByProfileName(profileName) != null) {
            return "redirect:/users/register?pexists";
        }

        if(name.equals("") || profileName.equals("") || username.equals("") || password.equals("") || password2.equals("")) {
            return "redirect:/users/register?missing";
        }

        if(!password.equals(password2)) {
            return "redirect:/users/register?mismatch";
        }

        Account account = new Account();
        account.setName(name);
        account.setProfileName(profileName);
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        accountRepository.save(account);

        return "redirect:/login";
    }
}