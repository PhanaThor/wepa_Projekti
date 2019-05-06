package projekti.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(path = "/users/{profileName}/picture")
    public String postPicture(Model model, @PathVariable String profileName, @RequestParam("file") MultipartFile file, @RequestParam String description) throws IOException {
        model.addAttribute("viewedAccount", accountService.getAccountByProfileName(profileName));
        model.addAttribute("account", accountService.getLoggedAccount());
        
        if(file.getContentType().equals("image/png")) {
            accountService.savePicture(profileName, file.getBytes(), description);
        }
        
        return "redirect:/users/" + profileName;
    } 

    @GetMapping(path = "/users/{profileName}/picture/{id}", produces = "image/png")
    @ResponseBody
    public byte[] getPicture(@PathVariable String profileName, @PathVariable Long id) {
        return accountService.getPicture(profileName, id);
    }
}