package projekti.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import projekti.models.Account;
import projekti.services.AccountService;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
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
    public String getRegisterAccount(@ModelAttribute Account account) {
        return "users/register";
    }

    @PostMapping("/users/register")
    public String postRegisterAccount(@Valid @ModelAttribute Account account, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "users/register";
        }

        if(accountService.accountExists(account.getUsername(), "")) {
            return "redirect:/users/register?uexists";
        }

        if(accountService.accountExists("", account.getProfileName())) {
            return "redirect:/users/register?pexists";
        }

        accountService.createAccount(account);

        return "redirect:/users/register?created";
    }
}