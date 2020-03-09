package com.etps.etps.controllers;

import com.etps.etps.excelConversions.WriteToExcel;
import com.etps.etps.models.Message;
import com.etps.etps.models.User;
import com.etps.etps.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


@Controller
public class FileDownloadController {

    private Users userDao;
    private Providers providerDao;
    private Campuses campusDao;
    private Programs programDao;
    private Submissions submissionDao;
    private Messages messageDao;

    public FileDownloadController(Users userDao, Providers providerDao, Campuses campusDao, Programs programDao, Submissions submissionDao, Messages messageDao) {
        this.userDao = userDao;
        this.providerDao = providerDao;
        this.campusDao = campusDao;
        this.programDao = programDao;
        this.submissionDao = submissionDao;
        this.messageDao = messageDao;
    }


    @PostMapping("/download/Approved")
    public void WriteApproved(HttpServletResponse response) {

        User UserCheck = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.findByUsername(UserCheck.getUsername());
        WriteToExcel writeToExcel = new WriteToExcel(providerDao, campusDao, programDao, submissionDao);

        try {

            File writeFile = File.createTempFile("ETPS_" + loggedInUser.getUserProviderId() + "_Approved", ".xlsx");
            Map<String, Object[]> writeData = writeToExcel.GenerateByStatus(loggedInUser, "approved");
            writeToExcel.WriteExcel(writeData, writeFile);

            InputStream is = new FileInputStream(writeFile);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setHeader("ETPS_"+loggedInUser.getUserProviderId()+"_Approved",".xlsx");

            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();

        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }


//        return "redirect:/home";
    }

    @PostMapping("/download/Pending")
    public void WritePending(HttpServletResponse response) {

        User UserCheck = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.findByUsername(UserCheck.getUsername());
        WriteToExcel writeToExcel = new WriteToExcel(providerDao, campusDao, programDao, submissionDao);

        try {

            if (loggedInUser.isAdmin()) {
//                get sender's user and set them as the pending user instead

            }
            File writeFile = File.createTempFile("ETPS_" + loggedInUser.getUserProviderId() + "_Pending", ".xlsx");

            Map<String, Object[]> writeData = writeToExcel.GenerateByStatus(loggedInUser, "pending");
            writeToExcel.WriteExcel(writeData, writeFile);

            InputStream is = new FileInputStream(writeFile);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setHeader("ETPS_"+loggedInUser.getUserProviderId()+"_Pending",".xlsx");

            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();

        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }

//        return "redirect:/home";
    }

    @PostMapping("/download/Expired")
    public void WriteExpired(HttpServletResponse response) {

        User UserCheck = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.findByUsername(UserCheck.getUsername());
        WriteToExcel writeToExcel = new WriteToExcel(providerDao, campusDao, programDao, submissionDao);

        try {
            File writeFile = File.createTempFile("ETPS_" + loggedInUser.getUserProviderId() + "_Expired", ".xlsx");

            Map<String, Object[]> writeData = writeToExcel.GenerateByStatus(loggedInUser, "expired");
            writeToExcel.WriteExcel(writeData, writeFile);
            InputStream is = new FileInputStream(writeFile);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setHeader("ETPS_"+loggedInUser.getUserProviderId()+"_Expired",".xlsx");
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();

        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }

        //        return "redirect:/home";
    }


    @PostMapping("/download/All")
    public void WriteAll(HttpServletResponse response) {

        User UserCheck = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.findByUsername(UserCheck.getUsername());
        if (loggedInUser.isAdmin()) {
            WriteToExcel writeToExcel = new WriteToExcel(providerDao, campusDao, programDao, submissionDao);

            try {
                File writeFile = File.createTempFile("ETPS_" + loggedInUser.getUserProviderId() + "_All", ".xlsx");

                Map<String, Object[]> writeData = writeToExcel.GenerateAllUserData(loggedInUser);
                writeToExcel.WriteExcel(writeData, writeFile);

                InputStream is = new FileInputStream(writeFile);
//                response.setContentType("application/vnd.ms-excel");
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//                response.setHeader("ETPS_"+loggedInUser.getUserProviderId()+"_All",".xlsx");

                org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();

            } catch (IOException ex) {
                throw new RuntimeException("IOError writing file to output stream");
            }

            //        return "redirect:/home";
        }

    }

    @PostMapping("/message/{id}/download/submission")
    public void WriteSubmission(HttpServletResponse response, @PathVariable String id) {

        Message message = messageDao.findById(Long.parseLong(id));
        User receivedUser = message.getReceivedUser();
        User sentUser = message.getSentUser();
        User submissionUser;

        if (!sentUser.isAdmin()) {
            submissionUser = sentUser;
        } else {
            submissionUser = receivedUser;
        }

        WriteToExcel writeToExcel = new WriteToExcel(providerDao, campusDao, programDao, submissionDao);

        try {

            File writeFile = File.createTempFile("ETPS_" + submissionUser.getUserProviderId() + "_Pending", ".xlsx");

            Map<String, Object[]> writeData = writeToExcel.GenerateByStatus(submissionUser, "pending");
            writeToExcel.WriteExcel(writeData, writeFile);

            InputStream is = new FileInputStream(writeFile);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setHeader("ETPS_"+submissionUser.getUserProviderId()+"_Pending",".xlsx");

            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();

        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
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

