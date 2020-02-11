package com.etps.etps.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @GetMapping("/profile")
    public String showProfile() {
        return "users/profile";
    }
}
