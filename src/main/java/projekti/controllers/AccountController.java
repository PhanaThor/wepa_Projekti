package projekti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import projekti.services.AccountService;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/users")
    public String getUserList(Model model) {

        model.addAttribute("users", accountService.getAllAccounts());
        model.addAttribute("account", accountService.getLoggedAccount());
        
        return "users/list";
    }

    @GetMapping("/users/{profileName}")
    public String getProfile(Model model, @PathVariable String profileName) throws Exception {
        model.addAttribute("viewedAccount", accountService.getAccountByProfileName(profileName));
        model.addAttribute("account", accountService.getLoggedAccount());

        return "users/profile";
    }

    @GetMapping("/users/register")
    public String getRegisterAccount() {
        return "users/register";
    }

    @PostMapping("/users/register")
    public String postRegisterAccount(@RequestParam String name, @RequestParam String profileName, @RequestParam String username, @RequestParam String password, @RequestParam String password2) {
        if(accountService.userExists(username, "")) {
            return "redirect:/users/register?uexists";
        }

        if(accountService.userExists("", profileName)) {
            return "redirect:/users/register?pexists";
        }

        if(name.equals("") || profileName.equals("") || username.equals("") || password.equals("") || password2.equals("")) {
            return "redirect:/users/register?missing";
        }

        if(!password.equals(password2)) {
            return "redirect:/users/register?mismatch";
        }

        accountService.createAccount(name, profileName, username, password);

        return "redirect:/users/register?created";
    }
}