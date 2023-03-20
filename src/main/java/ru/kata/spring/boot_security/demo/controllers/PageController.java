package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PageController {
    @GetMapping("admin")
    public String showAdminPage(ModelMap model, Principal principal) {
        return "admin";

    }
    @GetMapping("user")
    public String showUserPage(Model model, Principal principal) {
        return "user";

    }
    @GetMapping("auth/login")
    public String loginPage() {
        return "login";
    }

}
