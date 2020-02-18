package com.etps.etps.excelConversions;

import com.etps.etps.models.Provider;
import com.etps.etps.models.Submission;
import com.etps.etps.models.User;
import com.etps.etps.repositories.Providers;
import com.etps.etps.repositories.Submissions;

import java.util.ArrayList;
import java.util.List;

public class StatusChange {
    private Submissions submissionDao;
    private Providers providerDao;

    public StatusChange(Submissions submissionDao,Providers providerDao) {

        this.submissionDao = submissionDao;
        this.providerDao = providerDao;
    }

    public void ApproveSubmission(User user){
        long submitterID = user.getUserProviderId();
        List<Submission> submissions = submissionDao.findAll();
        List<Provider> providerList = providerDao.findAll();
        for (Provider provider: providerList) {
            if (provider.getProvId() == submitterID){
                if (provider.getSubmission().getStatus().equalsIgnoreCase("pending")){
                    provider.getSubmission().setStatus("approved");
                    submissionDao.save(provider.getSubmission());
                }
            }
        }




    }



}
