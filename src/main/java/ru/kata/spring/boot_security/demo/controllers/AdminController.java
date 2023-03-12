package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {


    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("users")
    public String showAllUsers(ModelMap model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        if (userService.getAllUsers().isEmpty()) {
            return "zeroPage";
        } else {
            List<User> users = new ArrayList<>();
            for (User user : userService.getAllUsers()) {
                users.add(user);
            }
            model.addAttribute("users", users);
            return "UsersView";
        }
    }

    @GetMapping("add")
    public String addUser(ModelMap modelMap, Principal principal) {

        modelMap.addAttribute("masteruser", userService.findByUsername(principal.getName()));
        modelMap.addAttribute("newuser", new User());
        List<Role> roles = userService.findAll();
        modelMap.addAttribute("allRoles", roles);
        return "addUser";
    }

    @PostMapping("save")
    public String saveNewUser(@ModelAttribute("newuser") User user) {

        userService.addUser(user);
        return "redirect:";
    }

    @DeleteMapping("delete")
    public String deleteUser(@RequestParam("Id") Long id) {
        userService.deleteUserById(id);
        return "redirect:";
    }

    @PostMapping("update")
    public String updateUser(@RequestParam("editeduser") Long Id, ModelMap model) {

        model.addAttribute("editeduser", userService.getUserById(Id));
        List<Role> roles = userService.findAll();
        model.addAttribute("allRoles", roles);

        return "updateUser";
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

        if (userService.getAllUsers().isEmpty()) {
            return "zeroPage";
        } else {
            List<User> users = new ArrayList<>();
            for (User user : userService.getAllUsers()) {
                users.add(user);
            }
            model.addAttribute("users", users);
            return "admin";
        }

    }


//    @GetMapping("/admin")
//    public String showAdminPage(Model model){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findByUsername(auth.getName());
//        model.addAttribute("users",user);
//        return "admin";
//
//    }
}
