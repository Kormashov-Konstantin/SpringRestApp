package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserServiceImpl userServiceImpl;
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("thisUser", userServiceImpl.findByUsername(principal.getName()));
        model.addAttribute("users", userServiceImpl.getAllUsers());
        model.addAttribute("Userlogin", userServiceImpl.findByEmail(principal.getName()));
        model.addAttribute("allRoles", roleServiceImpl.getRolesSet());
        model.addAttribute("newUser", new User());
        return "admin";
    }


    @PostMapping
    public String saveNewUser(@ModelAttribute("user") User newUser, @RequestParam("roles") long[] roles) {
        newUser.setRoles(roleServiceImpl.getRolesById(roles));
        userServiceImpl.saveUser(newUser);
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
}
