package ru.mishapp.springapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mishapp.springapp.models.User;
import ru.mishapp.springapp.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping
    public String mainPage(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("users", list);
        return "index";
    }

    @GetMapping("/{id}")
    public String changeUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "change";
    }

    @GetMapping("/new")
    public String addUser(@ModelAttribute("user") User user) {
        return "change";
    }

    @GetMapping("/del")
    public String delUser(@RequestParam("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping
    public String mainPost(@ModelAttribute("user") User user, @RequestParam("role") String authority){
        user.addRole(userService.getRole("ROLE_USER"));
        if (authority.equals("admin")){
            user.addRole(userService.getRole("ROLE_ADMIN"));
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
