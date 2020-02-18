package com.etps.etps.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.etps.etps.excelConversions.ReadFromExcel;
import com.etps.etps.models.User;
import com.etps.etps.payload.Response;
import com.etps.etps.repositories.*;
//import com.etps.etps.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Controller
public class FileUploadController {

    private Users userDao;
    private Providers providerDao;
    private Campuses campusDao;
    private Programs programDao;
    private Submissions submissionDao;
//    private FileStorageService fileStorageService;

    public FileUploadController(Users userDao, Providers providerDao, Campuses campusDao, Programs programDao, Submissions submissionDao) {
        this.userDao = userDao;
        this.providerDao = providerDao;
        this.campusDao = campusDao;
        this.programDao = programDao;
        this.submissionDao = submissionDao;
//        this.fileStorageService = fileStorageService;
    }


    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
//        String fileName = fileStorageService.storeFile(file);

//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();

//        Response loadedFile = new Response(fileName, fileDownloadUri,
//                file.getContentType(), file.getSize());

        try {

//            InputStream inputStream =  new BufferedInputStream(file.getInputStream());
            User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ReadFromExcel readFromExcel = new ReadFromExcel(providerDao, campusDao, programDao, submissionDao);
        File readFile = File.createTempFile("testFile",".xlsx");
        file.transferTo(readFile);

//        User user = userDao.findByUserProviderId(900);
        readFromExcel.ReadExcel(readFile,loggedInUser);
        readFile.delete();

    } catch (IOException e) {
        e.printStackTrace();
    }
        return "redirect:/test";
    }

}
