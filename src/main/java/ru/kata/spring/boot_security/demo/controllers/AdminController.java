package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {


    private final UserService userService;

    private final UserValidator userValidator;

    @Autowired
    public AdminController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }


    @PostMapping("save")
    public String saveNewUser(@ModelAttribute("newuser") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) return "admin";

        userService.addUser(user);
        return "redirect:";
    }


    @DeleteMapping("delete")
    public String deleteUser(@RequestParam("Id") Long id) {
        userService.deleteUserById(id);
        return "redirect:";
    }


    @PatchMapping("updating")
    public String updatingUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:";
    }

    @GetMapping("")
    public String showAdminPage(ModelMap model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("newuser", new User());
        List<Role> roles = userService.findAll();
        model.addAttribute("allRoles", roles);

        List<User> users = new ArrayList<>();
        for (User user : userService.getAllUsers()) {
            users.add(user);
        }
        model.addAttribute("users", users);
        return "admin";


    }


}
