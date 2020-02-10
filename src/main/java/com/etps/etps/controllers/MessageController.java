package com.etps.etps.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {


    @GetMapping("/message")
    public String showMessageForm(){
        return "message";
    }

    @GetMapping("/messages")
    public String showMessages(){
        return "messages";
    }
}
