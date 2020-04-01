package edu.udacity.java.nano.chat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @PostMapping("/")
    public String loginPost(@RequestBody String username, HttpSession s, Model m){
        m.addAttribute("username", username);
        s.setAttribute("username", username.substring(9));
        return "redirect:/index";
    }
}
