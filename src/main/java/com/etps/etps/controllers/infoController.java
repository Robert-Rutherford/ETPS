package com.etps.etps.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class infoController {

    @GetMapping("/info")
    public String showInfo() {
        return "infoPage";
    }
}
