package com.etps.etps.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.InflaterInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.etps.etps.services.FileStorageService;
import com.etps.etps.excelConversions.WriteToExcel;
import com.etps.etps.models.DaoCombiner;
import com.etps.etps.models.User;
import com.etps.etps.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public String WriteApproved(HttpServletResponse response) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WriteToExcel writeToExcel = new WriteToExcel(providerDao,campusDao,programDao,submissionDao);
//        String home = System.getProperty("user.home");

        try {

            File writeFile = File.createTempFile("ETPS_"+loggedInUser.getUserProviderId()+"_Approved",".xlsx");


//            File file = new File(home+"/Downloads/ETPS_"+loggedInUser.getUserProviderId()+"_All.xlsx");
            Map<String, Object[]> writeData = writeToExcel.GenerateByStatus(loggedInUser,"approved");
            writeToExcel.WriteExcel(writeData, writeFile);
            // get your file as InputStream
            InputStream is = new FileInputStream(writeFile);
            response.setContentType("application/vnd.ms-excel");
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
            writeFile.delete();
        } catch (IOException ex) {
//            log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
            throw new RuntimeException("IOError writing file to output stream");
        }


        return "redirect:/home";
    }

    @PostMapping("/download/Pending")
    public void WritePending(HttpServletResponse response) {


        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WriteToExcel writeToExcel = new WriteToExcel(providerDao,campusDao,programDao,submissionDao);
//        String home = System.getProperty("user.home");

        try {

            if (loggedInUser.isAdmin()){
//                get sender's user and set them as the pending user instead

            }
            File writeFile = File.createTempFile("ETPS_"+loggedInUser.getUserProviderId()+"_Pending",".xlsx");

//            File file = new File(home+"/Downloads/ETPS_"+loggedInUser.getUserProviderId()+"_All.xlsx");
            Map<String, Object[]> writeData = writeToExcel.GenerateByStatus(loggedInUser,"pending");
            writeToExcel.WriteExcel(writeData, writeFile);
            // get your file as InputStream
            InputStream is = new FileInputStream(writeFile);
            response.setContentType("application/vnd.ms-excel");
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
            writeFile.delete();
        } catch (IOException ex) {
//            log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
            throw new RuntimeException("IOError writing file to output stream");
        }


//        return "redirect:/home";
    }

    @PostMapping("/download/Expired")
    public void WriteExpired(HttpServletResponse response) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WriteToExcel writeToExcel = new WriteToExcel(providerDao,campusDao,programDao,submissionDao);
//        String home = System.getProperty("user.home");

        try {
            File writeFile = File.createTempFile("ETPS_"+loggedInUser.getUserProviderId()+"_Expired",".xlsx");

//            File file = new File(home+"/Downloads/ETPS_"+loggedInUser.getUserProviderId()+"_All.xlsx");
            Map<String, Object[]> writeData = writeToExcel.GenerateByStatus(loggedInUser,"expired");
            writeToExcel.WriteExcel(writeData, writeFile);
            // get your file as InputStream
            InputStream is = new FileInputStream(writeFile);
            response.setContentType("application/vnd.ms-excel");
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
            writeFile.delete();
        } catch (IOException ex) {
//            log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
            throw new RuntimeException("IOError writing file to output stream");
        }

        //        return "redirect:/home";
    }


    @PostMapping("/download/All")
    public void WriteAll(HttpServletResponse response) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedInUser.isAdmin()){
            WriteToExcel writeToExcel = new WriteToExcel(providerDao,campusDao,programDao,submissionDao);
//        String home = System.getProperty("user.home");

            try {
                File writeFile = File.createTempFile("ETPS_"+loggedInUser.getUserProviderId()+"_All",".xlsx");

//            File file = new File(home+"/Downloads/ETPS_"+loggedInUser.getUserProviderId()+"_All.xlsx");
                Map<String, Object[]> writeData = writeToExcel.GenerateAllUserData(loggedInUser);
                writeToExcel.WriteExcel(writeData, writeFile);
//              get your file as InputStream
                InputStream is = new FileInputStream(writeFile);
                response.setContentType("application/vnd.ms-excel");
                // copy it to response's OutputStream
                org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
                writeFile.delete();
            } catch (IOException ex) {
//            log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
                throw new RuntimeException("IOError writing file to output stream");
            }

            //        return "redirect:/home";
        }

    }


//    @RequestMapping(value = "/files/test", method = RequestMethod.GET)
//    public String getFile(HttpServletResponse response) {
//        try {
//                    WriteToExcel writeToExcel = new WriteToExcel(providerDao,campusDao,programDao,submissionDao);
////        String home = System.getProperty("user.home");
//        User loggedInUser = userDao.findByUserProviderId(802);
//
//            File writeFile = File.createTempFile("ETPS_"+loggedInUser.getUserProviderId()+"_All",".xlsx");
//
////            File file = new File(home+"/Downloads/ETPS_"+loggedInUser.getUserProviderId()+"_All.xlsx");
//            Map<String, Object[]> writeData = writeToExcel.GenerateAllUserData(loggedInUser);
//            writeToExcel.WriteExcel(writeData, writeFile);
//            // get your file as InputStream
//            InputStream is = new FileInputStream(writeFile);
//            response.setContentType("application/vnd.ms-excel");
//            // copy it to response's OutputStream
//            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
//            response.flushBuffer();
//        } catch (IOException ex) {
////            log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
//            throw new RuntimeException("IOError writing file to output stream");
//        }
//        return "redirect:/test";
//    }

}
