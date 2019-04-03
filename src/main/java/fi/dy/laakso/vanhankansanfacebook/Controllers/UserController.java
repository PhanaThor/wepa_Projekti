package fi.dy.laakso.vanhankansanfacebook.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/users/profile")
    public String showProfile(Model model) {
        return "User/Profile";
    }
}