package org.my.brdo.controller;

import org.my.brdo.entity.User;
import org.my.brdo.service.UserService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByUsername(user.getUsername());

        if (existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()) {
            result.rejectValue("username", null,
                    "Account already exists");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        userService.save(user);
        return "redirect:/login";
    }
}