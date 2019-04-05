package projekti.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/users/profile")
    public String showProfile(Model model) {
        List<String> myStrings = new ArrayList<>();
        myStrings.add("testi");
        model.addAttribute("strings", myStrings);
        return "User/Profile";
    }
}