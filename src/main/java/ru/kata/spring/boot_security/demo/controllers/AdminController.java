package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserServiceImpl userServiceImpl;

    @Autowired
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("Users", "All User table");
        model.addAttribute("user", userServiceImpl.getAllUsers());
        return "admin";
    }
    @GetMapping("/new")
    public String createPageNewUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @GetMapping("{id}")
    public String showUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("User", "one User table");
        model.addAttribute("user", userServiceImpl.showUserById(id));
        return "adminoneuser";
    }

    @PostMapping
    public String saveNewUser(@ModelAttribute("user") User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin";
    }
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String removeUserById(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userServiceImpl.removeUserById(id);
        return "redirect:/admin";
    }
    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userServiceImpl.showUserById(id));
        return "edit";
    }
}
