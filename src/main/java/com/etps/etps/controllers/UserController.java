package com.etps.etps.controllers;

import com.etps.etps.models.Message;
import com.etps.etps.models.Provider;
import com.etps.etps.models.User;
import com.etps.etps.repositories.Providers;
import com.etps.etps.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private Users userDao;
    private PasswordEncoder passwordEncoder;
    private Providers providerDao;

    public UserController(Users userDao, PasswordEncoder passwordEncoder, Providers providerDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.providerDao = providerDao;
    }


//    Creating test users for demonstration
    @GetMapping("users/test")
    public String createTestUsers(){

        Provider codeUp = new Provider();
        codeUp.setId(900);
        codeUp.setProviderName("Codeup");
        codeUp.setDescription("At Codeup, we have one goal: To solve meaningful problems that bring the tech community together through empowerment. As a Codeup student, you have the opportunity to learn in a supportive environment with staff, instructors, and employer partners that do their part to innovate and lead the future of tech. Together, we are making our corner of the world a better place — one techie at a time.");

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("admin@admin.com");
        admin.setAdmin(true);

        User ghost = new User();
        ghost.setUsername("ghost");
        ghost.setPassword(passwordEncoder.encode("ghost"));
        ghost.setEmail("ghost@admin.com");

        User codeUpUser = new User();
        codeUpUser.setUsername("codeup");
        codeUpUser.setPassword(passwordEncoder.encode("test"));
        codeUpUser.setEmail("test@testing.com");
        codeUpUser.setProvider(codeUp);
        codeUpUser.setAdmin(false);

        System.out.println(admin);


        codeUpUser.setProvider(codeUp);


        providerDao.save(codeUp);
        if (userDao.findByUsername("codeup") == null){
        userDao.save(codeUpUser);
        }

        if (userDao.findByUsername("admin") == null){
        userDao.save(admin);
        }

        if (userDao.findByUsername("ghost") == null){
            userDao.save(ghost);
        }

        if (providerDao.findById(829) == null){
            providerDao.save(codeUp);
        }

        return "redirect:/";
    }



}