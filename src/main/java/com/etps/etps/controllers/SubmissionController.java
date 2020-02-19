package com.etps.etps.controllers;

import com.etps.etps.excelConversions.StatusChange;
import com.etps.etps.excelConversions.WriteToExcel;
import com.etps.etps.models.DisplayData;
import com.etps.etps.models.Message;
import com.etps.etps.models.User;
import com.etps.etps.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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
    public String openSubmissionForm(Model model){
        User UserCheck = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.findByUsername(UserCheck.getUsername());
        WriteToExcel writeToExcel = new WriteToExcel(providerDao,campusDao,programDao,submissionDao);
        Map<String, Object[]> approveData = writeToExcel.GenerateByStatus(loggedInUser,"approved");
        Map<String, Object[]> pendingData = writeToExcel.GenerateByStatus(loggedInUser,"pending");
        Map<String, Object[]> expiredData = writeToExcel.GenerateByStatus(loggedInUser,"expired");
        ArrayList<DisplayData>  approvedList = new  ArrayList<>();
        ArrayList<DisplayData>  pendingList = new  ArrayList<>();
        ArrayList<DisplayData>  expiredList = new  ArrayList<>();

        Set<String> keyset = approveData.keySet();
        for (String key : keyset){
            Object[] objArr = approveData.get(key);
            DisplayData newData = new DisplayData(objArr);
            approvedList.add(newData);
        }
        Set<String> keyset2 = pendingData.keySet();
        for (String key : keyset2){
            Object[] objArr = pendingData.get(key);
            DisplayData newData = new DisplayData(objArr);
            pendingList.add(newData);
        }
        Set<String> keyset3 = expiredData.keySet();
        for (String key : keyset3){
            Object[] objArr = expiredData.get(key);
            DisplayData newData = new DisplayData(objArr);
            expiredList.add(newData);
        }

        model.addAttribute("approvedList",approvedList);
        model.addAttribute("pendingList",pendingList);
        model.addAttribute("expiredList",expiredList);


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
