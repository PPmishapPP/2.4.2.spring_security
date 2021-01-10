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
    private UserService userService;

    @GetMapping
    public String adminPage(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("users", list);
        return "admin";
    }

    @GetMapping("/{id}")
    public String changeUserPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", userService.getAllRoles());
        model.addAttribute("action", "Изменить");
        return "change";
    }

    @GetMapping("/new")
    public String addUserPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", userService.getAllRoles());
        model.addAttribute("action", "Добавить");
        return "change";
    }

    @GetMapping("/del")
    public String delUser(@RequestParam("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("role_select") Long[] roleIds) {
        for (Long id : roleIds) {
            user.addRole(userService.getRole(id));
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
