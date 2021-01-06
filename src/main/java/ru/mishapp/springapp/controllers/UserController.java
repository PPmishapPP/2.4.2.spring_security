package ru.mishapp.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mishapp.springapp.models.Role;
import ru.mishapp.springapp.service.UserService;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "/user/{login}")
    public String informPage(@PathVariable("login") String login, Model model, Authentication authentication) {
        if (authentication.getAuthorities().stream()
                .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))){
            if (!authentication.getName().equals(login)){
                return "redirect:/user" + authentication.getName();
            }
        }
        model.addAttribute("user", userService.getUserByLogin(login));
        return "user";
    }

    @GetMapping("/user")
    public String rootPage(Principal principal){
        System.out.println("\nЯ тут\n");
        return "redirect:/user/" + principal.getName();

    }

}
