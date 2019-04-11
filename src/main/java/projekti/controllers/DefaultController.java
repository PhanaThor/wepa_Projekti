package projekti.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class DefaultController {
    
    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("loggedUserProfileStr", "MrSample");
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {        
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam String username, @RequestParam String password) {
        System.out.println("UserName: " + username);
        System.out.println("Password: " + password);
        return "index";
    }

    @GetMapping("/logout")
    public String getLogout(Model model) {        
        return "redirect:/login";
    }
}