package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("thisUser", userService.findByUsername(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("Userlogin", userService.findByEmail(principal.getName()));
        model.addAttribute("allRoles", roleService.getRolesSet());
        model.addAttribute("newUser", new User());
        return "admin";
    }


    @PostMapping
    public String saveNewUser(@ModelAttribute("user") User newUser, @RequestParam("roles") long[] roles) {
        newUser.setRoles(roleService.getRolesById(roles));
        userService.saveUser(newUser);
        return "redirect:/admin";
    }
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String removeUserById(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
