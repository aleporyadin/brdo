package com.poriadin.brdo.controller;

import com.poriadin.brdo.dto.AccountDto;
import com.poriadin.brdo.entities.Account;
import com.poriadin.brdo.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        AccountDto user = new AccountDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("account") AccountDto accountDto,
                               BindingResult result,
                               Model model) {
        Account existing = accountService.findByUsername(accountDto.getUsername());
        if (existing != null) {
            result.rejectValue("username", null, "There is already an account registered with that username");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", accountDto);
            return "register";
        }
        accountService.saveAccount(accountDto);
        return "redirect:/register?success";
    }

}
