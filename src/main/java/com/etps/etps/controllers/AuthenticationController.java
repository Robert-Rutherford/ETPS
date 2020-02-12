package com.etps.etps.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }
//  verified login redirect to profile.html

    @GetMapping("/register")
    public String showRegister() {
        return "users/register";
    }

    @GetMapping("/resetpassword")
    public String showResetPassword() {
        return "users/resetPassword";
    }
}
