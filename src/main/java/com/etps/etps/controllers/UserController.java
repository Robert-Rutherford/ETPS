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

    private User currentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.findById(user.getId());
        return user;
    }

    @GetMapping("/login/success")
    public String setLoggedInUser(Model model){
        model.addAttribute("user", currentUser());
        return "redirect:/";
    }

    @GetMapping(value = {"/", "/home"})
    public String showHomePage(Model model) {
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            int unread = 0;

            for (Message message: currentUser().getReceived()){
                if (!message.isBeenRead()){
                    unread++;
                }
            }
            model.addAttribute("unread", unread);
            model.addAttribute("user", currentUser());
        }
        return "index";
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
        admin.setUserProviderId(1);
        admin.setEmail("admin@admin.com");
        admin.setAdmin(true);

        User emailTest = new User();
        emailTest.setUsername("emailTest");
        emailTest.setPassword(passwordEncoder.encode("ghost"));
        emailTest.setEmail("ethan.joiner@gmail.com");

        User codeUpUser = new User();
        codeUpUser.setUsername("codeup");
        codeUpUser.setPassword(passwordEncoder.encode("test"));
        codeUpUser.setEmail("test@testing.com");
        codeUpUser.setUserProviderId(900);
//        codeUpUser.setProvider(codeUp);
        codeUpUser.setAdmin(false);

        System.out.println(admin);


//        codeUpUser.setProvider(codeUp);


        providerDao.save(codeUp);
        if (userDao.findByUsername("codeup") == null){
        userDao.save(codeUpUser);
        }

        if (userDao.findByUsername("admin") == null){
        userDao.save(admin);
        }

        if (userDao.findByUsername("emailTest") == null){
            userDao.save(emailTest);
        }

        if (providerDao.findById(900) == null){
            providerDao.save(codeUp);
        }

        return "redirect:/";
    }



}