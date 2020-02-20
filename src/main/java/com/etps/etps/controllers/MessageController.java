package com.etps.etps.controllers;

import com.etps.etps.models.Message;
import com.etps.etps.models.User;
import com.etps.etps.repositories.Messages;
import com.etps.etps.repositories.Users;
import com.etps.etps.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {

    private final EmailService emailService;


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

    public MessageController(Messages messageDao, Users userDao, EmailService emailService){
        this.messageDao = messageDao;
        this.userDao = userDao;
        this.df = returnFormater();
        this.emailService = emailService;
    }



    @GetMapping("/message/{id}")
    public String showMessage(@PathVariable long id, Model model){
        Message message = messageDao.findById(id);

        if (currentUser().getId() == message.getReceivedUser().getId()) {
            message.setBeenRead(true);
        }
        model.addAttribute("user", currentUser());
        model.addAttribute("message", message);
        messageDao.save(message);
        return "message";
    }

    @GetMapping("/messages/out")
    public String showOutbox(Model model){
        model.addAttribute("user", currentUser());
        return "outbox";
    }

    @GetMapping("/messages/in")
    public String showInbox(Model model){
        model.addAttribute("user", currentUser());
        return "inbox";
    }

    @GetMapping("/message/create")
    public String showMessageForm(Model model){

        model.addAttribute("message", new Message());
        return "messageForm";
    }



    @PostMapping("/message/create")
    public String submitMessage(Message message, @RequestParam String to, Model model) throws ParseException {
        Date date = new Date();

        User receivedUser = userDao.findByUsername(to);
        message.setReceivedUser(receivedUser);
        message.setSentUser(currentUser());
        message.setDateSent(df.parse(df.format(date)));
        message.setBeenRead(false);
        messageDao.save(message);
        emailService.prepareAndSend(message, "New Message From " + message.getSentUser().getUsername(), "You have a new message!");

        return "redirect:/";
    }

    @GetMapping("/message/approved")
    public String autoApproveMsg(@ModelAttribute("flashMessage") Message message) throws ParseException {
        Date date = new Date();
        User receivedUser = userDao.findByUsername(message.getReceivedUser().getUsername());
        Message approved = new Message();
        approved.setReceivedUser(receivedUser);
        approved.setSentUser(currentUser());
        approved.setDateSent(df.parse(df.format(date)));
        approved.setBeenRead(false);
        approved.setTitle("Approved");
        approved.setBody("Your submission has been approved.");
        messageDao.save(approved);
        emailService.prepareAndSend(approved, "New Message From " + approved.getSentUser().getUsername(), "You have a new message!");

        return "redirect:/";
    }

    @GetMapping("/message/delete")
    public String deleteMessage(@RequestParam List<Long> id){

        for ( Long deleteid : id) {
            System.out.println(messageDao.findById((long) deleteid));
            messageDao.findById((long) deleteid).setDeleted(true);
            messageDao.findById((long) deleteid).setBeenRead(true);
            messageDao.save(messageDao.findById((long) deleteid));
        }
        return "redirect:/messages/in";
    }
}
