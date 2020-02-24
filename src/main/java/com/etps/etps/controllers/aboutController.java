package com.etps.etps.controllers;

import com.etps.etps.models.User;
import com.etps.etps.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class aboutController {
    private final Users userDao;
    private User user;

    public aboutController(Users userDao){
        this.userDao = userDao;
    }

    private User currentUser(){
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {

            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = userDao.findById(user.getId());
        }
        return user;
    }
    @GetMapping("/about")
    public String showAbout(Model model) {
        model.addAttribute("user", currentUser());
        return "aboutPage";
    }
}
