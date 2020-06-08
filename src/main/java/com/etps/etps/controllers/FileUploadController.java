package com.etps.etps.controllers;

import com.etps.etps.excelConversions.ReadFromExcel;
import com.etps.etps.models.Provider;
import com.etps.etps.models.User;
import com.etps.etps.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class FileUploadController {

    private Users userDao;
    private Providers providerDao;
    private Campuses campusDao;
    private Programs programDao;
    private Submissions submissionDao;


    public FileUploadController(Users userDao, Providers providerDao, Campuses campusDao, Programs programDao, Submissions submissionDao) {
        this.userDao = userDao;
        this.providerDao = providerDao;
        this.campusDao = campusDao;
        this.programDao = programDao;
        this.submissionDao = submissionDao;

    }


    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        try {

            User UserCheck = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User loggedInUser = userDao.findByUsername(UserCheck.getUsername());

            List<Provider> providers = providerDao.findAll();
            if (!loggedInUser.isAdmin()) {
                List<Provider> searchProviders = new ArrayList<>();
                for (Provider provider : providers) {
                    if (provider.getProvId() == loggedInUser.getUserProviderId()) {
                        searchProviders.add(provider);
                    }
                }
                providers = searchProviders;
            }

            for (Provider provider : providers) {
                if (provider.getSubmission().getStatus().equalsIgnoreCase("pending")) {
                    return "redirect:/submission";
                }
            }

            ReadFromExcel readFromExcel = new ReadFromExcel(providerDao, campusDao, programDao, submissionDao);


            File readFile = File.createTempFile("testFile", ".xlsx");
            file.transferTo(readFile);
            readFromExcel.ReadExcel(readFile, loggedInUser);
            readFile.delete();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/message/submission";
    }

}
