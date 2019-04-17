package projekti.controllers;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import projekti.services.AccountService;

import org.springframework.ui.Model;

@Controller
public class DefaultController {    
    @Autowired
    private AccountService accountService;

    @GetMapping("*")
    public String getIndex(Model model) {
        return "redirect:/users";
    }

    @GetMapping("/login") 
    public String getLogin() {
        return "login";
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final List<String> ACCOUNT_NAMES = Arrays.asList("Salla", "Ilta", "Aino", "Veikko", "Eeva", "Aili", "Hanna", "Teppo", "Ilse", "Pauli");
		
    @PostConstruct
    public void init() {        
        if(accountService.getAllAccounts().size() == 0) {
            for(String name : ACCOUNT_NAMES) {
                accountService.createAccount(name, randomAlphaNumericString(8), name, randomAlphaNumericString(10));
            }
        }
    }
    
    private static String randomAlphaNumericString(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }

        return builder.toString();
    }
}