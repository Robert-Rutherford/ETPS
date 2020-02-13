package com.etps.etps.controllers;

import com.etps.etps.models.Message;
import com.etps.etps.models.User;
import com.etps.etps.repositories.Messages;
import com.etps.etps.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Controller
public class MessageController {

    private DateFormat returnFormater() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        return df;
    }

    private DateFormat df;

    private final Messages messageDao;
    private final Users userDao;

    public MessageController(Messages messageDao, Users userDao){
        this.messageDao = messageDao;
        this.userDao = userDao;
        this.df = returnFormater();
    }



    @GetMapping("/message")
    public String showMessage(){
        return "messageOut";
    }

    @GetMapping("/messages")
    public String showMessages(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.findById(user.getId());

        model.addAttribute("user", user);

        return "messages";
    }

    @GetMapping("/message/create")
    public String showMessageForm(Model model){
//        model.addAttribute("sentUser", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        model.addAttribute("message", new Message());
        return "messageForm";
    }

    @PostMapping("/message/create")
    public String submitMessage(Message message, @RequestParam String to, Model model) throws ParseException {
        LocalDateTime date = LocalDateTime.now();
//        System.out.println(date);

        User receivedUser = userDao.findByUsername(to);
        User sentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sentUser = userDao.findById(sentUser.getId());
        message.setReceivedUser(receivedUser);
        message.setSentUser(sentUser);
        message.setDateSent(date);
        message.setBeenRead(false);
        messageDao.save(message);

        return "users/profile";


    }
}
