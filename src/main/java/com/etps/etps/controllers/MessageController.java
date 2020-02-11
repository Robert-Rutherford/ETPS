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

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
public class MessageController {

    private DateFormat df = new SimpleDateFormat("yyyy/MM/dd");


    private final Messages messageDao;
    private final Users userDao;

    public MessageController(Messages messageDao, Users userDao){
        this.messageDao = messageDao;
        this.userDao = userDao;
    }



    @GetMapping("/message")
    public String showMessage(){
        return "messageOut";
    }

    @GetMapping("/messages")
    public String showMessages(){
        return "messages";
    }

    @GetMapping("/message/create")
    public String showMessageForm(Model model){
//        model.addAttribute("sentUser", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        model.addAttribute("message", new Message());
        return "messageForm";
    }

    @PostMapping("/messages/create")
    public String submitMessage(Message message, @RequestParam String to, Model model){
        Date date = new Date;
        User receivedUser = userDao.findByUsername(to);
        User sentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sentUser = userDao.findById(sentUser.getId());
        message.setReceivedUser(receivedUser);
        message.setSentUser(sentUser);
        message.setDate_sent((Date) df.format(date));

    }
}
