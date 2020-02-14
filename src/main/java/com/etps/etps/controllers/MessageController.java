package com.etps.etps.controllers;

import com.etps.etps.models.Message;
import com.etps.etps.models.User;
import com.etps.etps.repositories.Messages;
import com.etps.etps.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {

    private DateFormat returnFormater() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        return df;
    }

    private User currentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.findById(user.getId());
        return user;
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
    public String showMessage(@RequestParam long id, Model model){
        Message message = messageDao.findById(id);

        if (currentUser().getId() == message.getReceivedUser().getId()) {
            message.setBeenRead(true);
        }
        System.out.println(currentUser().getId());
        System.out.println(message.getReceivedUser().getId());
        model.addAttribute("message", message);
        messageDao.save(message);
        return "message";
    }

    @GetMapping("/messages")
    public String showMessages(Model model){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        user = userDao.findById(user.getId());
        model.addAttribute("user", currentUser());
        return "messages";
    }

    @GetMapping("/message/create")
    public String showMessageForm(Model model){

        model.addAttribute("message", new Message());
        return "messageForm";
    }



    @PostMapping("/message/create")
    public String submitMessage(Message message, @RequestParam String to, Model model) throws ParseException {
//        LocalDateTime date = LocalDateTime.now();
//        System.out.println(date);
        Date date = new Date();

        User receivedUser = userDao.findByUsername(to);
        message.setReceivedUser(receivedUser);
        message.setSentUser(currentUser());
        message.setDateSent(df.parse(df.format(date)));
        message.setBeenRead(false);
        messageDao.save(message);

        return "redirect:/";

    }

    @GetMapping("/message/delete")
    public String deleteMessage(@RequestParam long id){

        messageDao.findById(id).setDeleted(true);
        messageDao.findById(id).setBeenRead(true);
        messageDao.save(messageDao.findById(id));


        return "redirect:/messages";
    }
}
