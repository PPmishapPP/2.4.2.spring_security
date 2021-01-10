package ru.mishapp.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mishapp.springapp.service.UserService;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String informPage(Model model, Authentication authentication) {
        model.addAttribute("user", userService.getUserByLogin(authentication.getName()));
        return "user";
    }

}
