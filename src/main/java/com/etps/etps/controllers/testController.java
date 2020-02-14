package com.etps.etps.controllers;

import com.etps.etps.excelConversions.ReadFromExcel;
import com.etps.etps.excelConversions.WriteToExcel;
import com.etps.etps.models.DaoCombiner;
import com.etps.etps.models.User;
import com.etps.etps.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.File;
import java.util.Map;

@Controller
public class testController {
    private Users userDao;
    private Providers providerDao;
    private Campuses campusDao;
    private Programs programDao;
    private Submissions submissionDao;

    public testController(Users userDao, Providers providerDao, Campuses campusDao, Programs programDao, Submissions submissionDao) {
        this.userDao = userDao;
        this.providerDao = providerDao;
        this.campusDao = campusDao;
        this.programDao = programDao;
        this.submissionDao = submissionDao;
    }



    @GetMapping("/test")
    public String testShow() {
        return "testPage";
    }

    @PostMapping("/test/write")
    public String WriteTest() {

        DaoCombiner daoCombiner = new DaoCombiner(userDao,providerDao,campusDao,programDao);

        WriteToExcel writeToExcel = new WriteToExcel(providerDao,campusDao,programDao,submissionDao);

        String home = System.getProperty("user.home");
        User user = userDao.findByProviderId(802);
        File file = new File(home+"/Downloads/ETPS_"+user.getProvider().getId()+"_"+user.getProvider().getProviderName()+"_All.xlsx");

        Map<String, Object[]> testdata = writeToExcel.GenerateUserData(user);
//        File file = new File("testwrite1.xlsx");
        writeToExcel.WriteExcel(testdata, file);

        user = userDao.findByProviderId(1);
        testdata = writeToExcel.GenerateUserData(user);
        file = new File(home+"/Downloads/ETPS_data_All.xlsx");
        writeToExcel.WriteExcel(testdata, file);


        user = userDao.findByProviderId(900);
        file = new File(home+"/Downloads/ETPS_"+user.getProvider().getId()+"_"+user.getProvider().getProviderName()+"_Pending.xlsx");
        testdata = writeToExcel.GenerateUserData(user);
        writeToExcel.WriteExcel(testdata, file);





        return "redirect:/test";
    }

    @PostMapping("test/read")
    public String ReadTest(@RequestParam("readFile") File file) {

        DaoCombiner daoCombiner = new DaoCombiner(userDao,providerDao,campusDao,programDao);

        File newFile = new File(file.getAbsolutePath());
        System.out.println(file.getAbsolutePath());

        ReadFromExcel readFromExcel = new ReadFromExcel(providerDao, campusDao, programDao, submissionDao);
//        File file = new File("/Users/robertlr/IdeaProjects/etps/testread1.xlsx");
//        readFromExcel.ReadExcel(file);
//        readFromExcel.ReadExcel(pathTest);
        User user = userDao.findByProviderId(900);
        readFromExcel.ReadExcel(newFile,user);
        return "redirect:/test";
    }
}
