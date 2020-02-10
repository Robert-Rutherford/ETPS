package com.etps.etps.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubmissionController {

    @GetMapping("/submission")
    public String openSubmissionForm(){
        return "submissions";
    }
}
