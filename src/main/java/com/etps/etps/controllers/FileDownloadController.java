package com.etps.etps.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import com.etps.etps.services.FileStorageService;
import com.etps.etps.excelConversions.WriteToExcel;
import com.etps.etps.models.DaoCombiner;
import com.etps.etps.models.User;
import com.etps.etps.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;



@Controller
public class FileDownloadController {

    private Users userDao;
    private Providers providerDao;
    private Campuses campusDao;
    private Programs programDao;
    private Submissions submissionDao;
//    private FileStorageService fileStorageService;

    public FileDownloadController(Users userDao, Providers providerDao, Campuses campusDao, Programs programDao, Submissions submissionDao) {
        this.userDao = userDao;
        this.providerDao = providerDao;
        this.campusDao = campusDao;
        this.programDao = programDao;
        this.submissionDao = submissionDao;
//        this.fileStorageService = fileStorageService;
    }


    @PostMapping("/download/Approved")
    public String WriteApproved() {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WriteToExcel writeToExcel = new WriteToExcel(providerDao,campusDao,programDao,submissionDao);
//        String home = System.getProperty("user.home");

        try {
            File writeFile = File.createTempFile("ETPS_"+loggedInUser.getUserProviderId()+"_Approved",".xlsx");


//            File file = new File(home+"/Downloads/ETPS_"+loggedInUser.getUserProviderId()+"_All.xlsx");
            Map<String, Object[]> writeData = writeToExcel.GenerateByStatus(loggedInUser,"approved");
            writeToExcel.WriteExcel(writeData, writeFile);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/home";
    }

    @PostMapping("/download/Pending")
    public String WritePending() {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WriteToExcel writeToExcel = new WriteToExcel(providerDao,campusDao,programDao,submissionDao);
//        String home = System.getProperty("user.home");

        try {
            File writeFile = File.createTempFile("ETPS_"+loggedInUser.getUserProviderId()+"_Pending",".xlsx");

//            File file = new File(home+"/Downloads/ETPS_"+loggedInUser.getUserProviderId()+"_All.xlsx");
            Map<String, Object[]> writeData = writeToExcel.GenerateByStatus(loggedInUser,"pending");
            writeToExcel.WriteExcel(writeData, writeFile);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/home";
    }

    @PostMapping("/download/Expired")
    public String WriteExpired() {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WriteToExcel writeToExcel = new WriteToExcel(providerDao,campusDao,programDao,submissionDao);
//        String home = System.getProperty("user.home");

        try {
            File writeFile = File.createTempFile("ETPS_"+loggedInUser.getUserProviderId()+"_Expired",".xlsx");

//            File file = new File(home+"/Downloads/ETPS_"+loggedInUser.getUserProviderId()+"_All.xlsx");
            Map<String, Object[]> writeData = writeToExcel.GenerateByStatus(loggedInUser,"expired");
            writeToExcel.WriteExcel(writeData, writeFile);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/home";
    }









//    private static final Logger logger = LoggerFactory.getLogger(FileDownloadController.class);
//
//    @Autowired
//    private FileStorageService fileStorageService;
//
//    @GetMapping("/downloadFile/{fileName:.+}")
//    public ResponseEntity < Resource > downloadFile(@PathVariable String fileName, HttpServletRequest request) {
//        // Load file as Resource
//        Resource resource = fileStorageService.loadFileAsResource(fileName);
//
//        // Try to determine file's content type
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
//        }
//
//        // Fallback to the default content type if type could not be determined
//        if (contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
}
