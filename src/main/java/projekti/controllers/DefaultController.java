package projekti.controllers;

import javax.annotation.PostConstruct;

import org.eclipse.jetty.util.security.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import projekti.models.Account;
import projekti.repositories.AccountRepository;

import org.springframework.ui.Model;

@Controller
public class DefaultController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("*")
    public String getIndex(Model model) {
        return "redirect:/users";
    }

    @GetMapping("/login") 
    public String getLogin() {
        return "login";
    }

    /*
    @PostConstruct
    public void init() {
        for(int i=1;i<=10;i++) {
            String value = "test" + i;
            Account account = new Account();
            account.setName(value);
            account.setProfileName(value);
            account.setUsername(value);
            account.setPassword(passwordEncoder.encode(value));
            accountRepository.save(account);
        }
    }
    */
}