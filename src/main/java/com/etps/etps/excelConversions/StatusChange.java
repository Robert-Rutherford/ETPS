package com.etps.etps.excelConversions;

import com.etps.etps.models.Submission;
import com.etps.etps.models.User;
import com.etps.etps.repositories.Submissions;

import java.util.ArrayList;
import java.util.List;

public class StatusChange {
    private Submissions submissionDao;

    public StatusChange(Submissions submissionDao) {
        this.submissionDao = submissionDao;
    }

    public void ApproveSubmission(User user){
        long submitterID = user.getProvider().getId();
        List<Submission> submissions = submissionDao.findAll();
        for (Submission submission: submissions) {
            if (submission.getProvider().getId() == submitterID){
                if (submission.getStatus().equalsIgnoreCase("pending")){
                    submission.setStatus("approved");
                    submissionDao.save(submission);
                }
            }
        }




    }



}
