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

    @Autowired
    private final UserService userService;



    public AdminController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/users/new")
//    public ModelAndView newUser() {
//        User user = new User();
//        ModelAndView mav = new ModelAndView("user_form");
//        mav.addObject("user", user);
//
//        List<Role> roles = (List<Role>) roleRepository.findAll();
//
//        mav.addObject("allRoles", roles);
//
//        return mav;
//    }
//
//    @GetMapping("/users/edit/{id}")
//    public ModelAndView editUser(@PathVariable(name = "id") Integer id) {
//        User user = userService.getUserById(id);
//        ModelAndView mav = new ModelAndView("user_form");
//        mav.addObject("user", user);
//
//        List<Role> roles = (List<Role>) roleRepository.findAll();
//
//        mav.addObject("allRoles", roles);
//
//        return mav;
//    }
    @GetMapping("users")
    public String showAllUsers(ModelMap model) {
        if(userService.getAllUsers().isEmpty()) {
            return "zeroPage";
        } else {
            List<User> users = new ArrayList<>();
            for(User user: userService.getAllUsers()) {
                users.add(user);
            }
            model.addAttribute("users", users);
            return "UsersView";
        }
    }

    @GetMapping("add")
    public String addUser(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        List<Role> roles = userService.findAll();
        modelMap.addAttribute("allRoles", roles);
        return "addUser";
    }

    @PostMapping("save")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:users";
    }

    @PostMapping("delete")
    public String deleteUser(@RequestParam("Id") Long id) {
        userService.deleteUserById(id);
        return "redirect:users";
    }

    @PostMapping("update")
    public String updateUser(@RequestParam("user") Long Id, ModelMap model) {

        model.addAttribute("user", userService.getUserById( Id));
        List<Role> roles = userService.findAll();
        model.addAttribute("allRoles", roles);

        return "updateUser";
    }

    @PostMapping("updating")
    public String updatingUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:users";
    }
    @GetMapping("")
    public String showAdminPage(Model model, Principal principal){
        model.addAttribute("user",userService.findByUsername(principal.getName()));
        return "admin";

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
