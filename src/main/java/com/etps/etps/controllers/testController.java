package com.etps.etps.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class testController {
    @GetMapping("/test")
    public String testShow() {
        return "testPage";
    }

    @PostMapping("/test/write")
    public String WriteTest(){

        return "testPage";
    }

    @PostMapping("test/read")
    public String ReadTest(){

        return "testPage";
    }
}
