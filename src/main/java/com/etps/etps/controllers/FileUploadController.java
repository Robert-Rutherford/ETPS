package com.etps.etps.controllers;

import com.etps.etps.excelConversions.ReadFromExcel;
import com.etps.etps.models.User;
import com.etps.etps.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


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

            User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ReadFromExcel readFromExcel = new ReadFromExcel(providerDao, campusDao, programDao, submissionDao);
            File readFile = File.createTempFile("testFile",".xlsx");
            file.transferTo(readFile);

//        User loggedInUser = userDao.findByUserProviderId(900);
            readFromExcel.ReadExcel(readFile,loggedInUser);
            readFile.delete();

    } catch (IOException e) {
        e.printStackTrace();
    }
        return "redirect:/test";
    }

}
