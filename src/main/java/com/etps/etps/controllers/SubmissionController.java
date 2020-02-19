package com.etps.etps.controllers;

import com.etps.etps.excelConversions.StatusChange;
import com.etps.etps.models.Message;
import com.etps.etps.models.User;
import com.etps.etps.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubmissionController {
    private Users userDao;
    private Providers providerDao;
    private Campuses campusDao;
    private Programs programDao;
    private Submissions submissionDao;
    private Messages messageDao;

    public SubmissionController(Users userDao, Providers providerDao, Campuses campusDao, Programs programDao, Submissions submissionDao, Messages messageDao) {
        this.userDao = userDao;
        this.providerDao = providerDao;
        this.campusDao = campusDao;
        this.programDao = programDao;
        this.submissionDao = submissionDao;
        this.messageDao = messageDao;
    }


    @GetMapping("/submission")
    public String openSubmissionForm(){
        return "submissions";
    }


    @PostMapping("/message/{id}/submission/approved")
    public void approveTest(@PathVariable String id){
//        User user = userDao.findByUserProviderId(900);
        Message message = messageDao.findById(Long.parseLong(id));
        User receivedUser = message.getReceivedUser();
        User sentUser = message.getSentUser();
        User submissionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!sentUser.isAdmin()){
            submissionUser = sentUser;
        }else {
            submissionUser = receivedUser;
        }
        StatusChange statusChange = new StatusChange(submissionDao,providerDao);
        statusChange.ApproveSubmission(submissionUser);

//


//        return "redirect:/test";
    }
}
