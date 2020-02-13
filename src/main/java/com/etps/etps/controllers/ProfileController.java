package com.etps.etps.controllers;

import com.etps.etps.models.Message;
import com.etps.etps.models.User;
import com.etps.etps.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private User currentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.findById(user.getId());
        return user;
    }

    private Users userDao;

    public ProfileController(Users userDao){
        this.userDao = userDao;
    }
    @GetMapping("/profile")
    public String displayProfile(Model model){

        int unread = 0;

        for (Message message: currentUser().getReceived()){
            if (!message.isBeenRead()){
                unread++;
            }
        }
        model.addAttribute("user",currentUser());
        model.addAttribute("unread", unread);

        return "users/profile";
    }
}
