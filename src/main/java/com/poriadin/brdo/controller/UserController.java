package com.poriadin.brdo.controller;

import com.poriadin.brdo.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", user);
        model.addAttribute("comments", userRepository.findAll());
        return "users";
    }
}
