package com.etps.etps.controllers;

import com.etps.etps.models.Message;
import com.etps.etps.models.User;
import com.etps.etps.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class infoController {

    private final Users userDao;
    private User user;

    public infoController(Users userDao) {
        this.userDao = userDao;
    }

    private User currentUser() {
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = userDao.findById(user.getId());
        }
        return user;
    }

    @GetMapping("/info")
    public String showInfo(Model model) {

        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            int unread = 0;

            for (Message message : currentUser().getReceived()) {
                if (!message.isBeenRead()) {
                    unread++;
                }
            }
            model.addAttribute("unread", unread);
            model.addAttribute("user", currentUser());
        }
        return "infoPage";
    }
}
