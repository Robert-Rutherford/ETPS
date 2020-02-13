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

    private Users userDao;

    public ProfileController(Users userDao){
        this.userDao = userDao;
    }
    @GetMapping("/profile")
    public String displayProfile(Model model){

//        Grabbing and assigning current user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.findById(user.getId());

        int unread = 0;

        for (Message message: user.getReceived()){
            if (!message.isBeenRead()){
                unread++;
            }
        }
        model.addAttribute("user",user);
        model.addAttribute("unread", unread);

        return "users/profile";
    }
}
