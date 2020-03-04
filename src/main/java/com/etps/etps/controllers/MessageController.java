package com.etps.etps.controllers;

import com.etps.etps.excelConversions.StatusChange;
import com.etps.etps.models.Message;
import com.etps.etps.models.User;
import com.etps.etps.repositories.Messages;
import com.etps.etps.repositories.Providers;
import com.etps.etps.repositories.Submissions;
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
    private Submissions submissionDao;
    private Providers providerDao;

// Used to format the date to match the mySql database
    private DateFormat returnFormater() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        return df;
    }

//    Grabs current user logged in with spring security
    private User currentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.findById(user.getId());
        return user;
    }

    private DateFormat df;

    private final Messages messageDao;
    private final Users userDao;

    public MessageController(Messages messageDao, Users userDao, EmailService emailService,Submissions submissionDao,Providers providerDao){
        this.messageDao = messageDao;
        this.userDao = userDao;
        this.df = returnFormater();
        this.emailService = emailService;
        this.providerDao = providerDao;
        this.submissionDao = submissionDao;
    }

//    No longer a test, used for the updated message display
    @GetMapping("/message/new")
    public String showNewMessage(Model model) {
//        To get user inbox and outbox
        model.addAttribute("user", currentUser());
        model.addAttribute("message", new Message());

        return "messageTest";
    }

// Used to display an individual message based on ID
    @GetMapping("/message/{id}")
    public String showMessage(@PathVariable long id, Model model){
        Message message = messageDao.findById(id);
        StatusChange statusChange = new StatusChange(submissionDao,providerDao);

        if (currentUser().getId() == message.getReceivedUser().getId()) {
            message.setBeenRead(true);
        }
        boolean subCheck = !message.getSentUser().isAdmin() && statusChange.NoSubmission(message.getSentUser());

        model.addAttribute("subCheck",subCheck);
        model.addAttribute("user", currentUser());
        model.addAttribute("viewMessage", message);
        messageDao.save(message);
        return "messageDisplay";
    }

//    OUTDATED: Used to display outbox
    @GetMapping("/messages/out")
    public String showOutbox(Model model){
        model.addAttribute("user", currentUser());
        return "outbox";
    }

//    OUTDATED: Used to display inbox
    @GetMapping("/messages/in")
    public String showInbox(Model model){
        model.addAttribute("user", currentUser());
        return "inbox";
    }

//    Used to display the new message form
    @GetMapping("/message/create")
    public String showMessageForm(Model model){
        model.addAttribute("user", currentUser());
        model.addAttribute("message", new Message());
        return "messageForm";
    }


// Actual logic for creating the new message and sending the automated email
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

        return "redirect:/message/new";
    }

//    Sends an automated message to providers upon approval of a submission
    @GetMapping("/message/approved")
    public String autoApproveMsg(@ModelAttribute("flashMessage") Message message) throws ParseException {
        Date date = new Date();
        User receivedUser = userDao.findByUsername(message.getSentUser().getUsername());
        Message approved = new Message();
        approved.setReceivedUser(receivedUser);
        approved.setSentUser(currentUser());
        approved.setDateSent(df.parse(df.format(date)));
        approved.setBeenRead(false);
        approved.setTitle("Approved");
        approved.setBody("Your submission has been approved.");
        messageDao.save(approved);
        emailService.prepareAndSend(approved, "New Message From " + approved.getSentUser().getUsername(), "You have a new message!");
        return "redirect:/message/new";
    }

//    Prompts admin to include message with a rejection
    @GetMapping("/message/rejected")
    public String autoRejectMsg(@ModelAttribute("flashMessage") Message message, Model model) throws ParseException {
        User receivedUser = userDao.findByUsername(message.getSentUser().getUsername());
        model.addAttribute("reject", receivedUser);
        model.addAttribute("message", new Message());

        return "redirect:/message/new";
    }

//    Sends an automated message to the TWC upon upload of a submission
    @GetMapping("/message/submission")
    public String autoSubmitMsg() throws ParseException {
        Date date = new Date();

        Message message = new Message();
        message.setSentUser(currentUser());
        message.setReceivedUser(userDao.findByUsername("admin"));
        message.setDateSent(df.parse(df.format(date)));
        message.setBeenRead(false);
        message.setTitle("New Submission");
        message.setBody("You have received a new submission from " + currentUser().getUsername() + ". Please review.");
        messageDao.save(message);
        emailService.prepareAndSend(message, "New Message From " + message.getSentUser().getUsername(), "You have a new message!");

        return "redirect:/submission";
    }

//    Used to delete messages from inbox
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
