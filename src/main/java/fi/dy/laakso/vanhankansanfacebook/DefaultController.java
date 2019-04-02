package fi.dy.laakso.vanhankansanfacebook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {
    
    @ResponseBody
    @GetMapping("/")
    public String getRoot() {
        return "Hello World!";
    }

    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("hello", "Hello Thymeleaf!");
        return "index";
    }
}