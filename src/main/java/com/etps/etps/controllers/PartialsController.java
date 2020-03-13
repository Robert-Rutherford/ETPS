package com.etps.etps.controllers;

import com.etps.etps.models.User;
import com.etps.etps.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartialsController {
    private Users userDao;

    public PartialsController(Users userDao) {
        this.userDao = userDao;
    }

    private User currentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.findById(user.getId());
        return user;
    }

    @GetMapping("/sideNav")
    public String navBar(Model model) {
        model.addAttribute("user", currentUser());
        return "sideNav.html";
    }
}
