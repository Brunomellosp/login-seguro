package com.example.loginseguro.controller;

import com.example.loginseguro.service.CustomUserDetailsService;
import com.example.loginseguro.service.UserRegistrationDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final CustomUserDetailsService userService;

    public AuthController(CustomUserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid @ModelAttribute("user") UserRegistrationDTO user,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.registerNewUser(user);
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "register";
        }
        return "redirect:/login?registered";
    }
}
