package com.etps.etps.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    @GetMapping("/")
    public String showLoginForm() {

        return "users/login";
    }
}
