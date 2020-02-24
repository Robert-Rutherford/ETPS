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

        User acUser = new User();
        acUser.setUsername("acUser");
        acUser.setPassword(passwordEncoder.encode("test2"));
        acUser.setEmail("testing@testing.com");
        acUser.setUserProviderId(802);
        acUser.setAdmin(false);

        User codeupUser = new User();
        acUser.setUsername("codeup");
        acUser.setPassword(passwordEncoder.encode("test"));
        acUser.setEmail("testin@testing.com");
        acUser.setUserProviderId(900);
        acUser.setAdmin(false);




        if (userDao.findByUsername("admin") == null){
            userDao.save(admin);
        }
        if (userDao.findByUsername("acUser") == null){
        userDao.save(acUser);
        }
        if (userDao.findByUsername("codeup") == null){
            userDao.save(codeupUser);
        }

        if (userDao.findByUsername("emailTest") == null){
            userDao.save(emailTest);
        }


        return "redirect:/";
    }



}