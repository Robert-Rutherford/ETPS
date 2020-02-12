package com.etps.etps.controllers;

import com.etps.etps.excelConversions.FormLayout;
import com.etps.etps.excelConversions.ReadFromExcel;
import com.etps.etps.excelConversions.WriteToExcel;
import com.etps.etps.models.User;
import com.etps.etps.repositories.Campuses;
import com.etps.etps.repositories.Programs;
import com.etps.etps.repositories.Providers;
import com.etps.etps.repositories.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.util.Map;

@Controller
public class testController {
//    private WriteToExcel writeToExcel;
//    private FormLayout formLayout;
    private Users userDao;
    private Providers providerDao;
    private Campuses campusDao;
    private Programs programDao;

    public testController(Users userDao, Providers providerDao, Campuses campusDao, Programs programDao) {
        this.userDao = userDao;
        this.providerDao = providerDao;
        this.campusDao = campusDao;
        this.programDao = programDao;
    }


    @GetMapping("/test")
    public String testShow() {
        return "testPage";
    }

    @PostMapping("/test/write")
    public String WriteTest(){
//        User user = new User();
//        User user = userDao.findById(1);
        User user = userDao.findByProviderId(802);
        FormLayout formLayout = new FormLayout(userDao,programDao,providerDao,campusDao);
        Map<String, Object[]> testdata = formLayout.GenerateUserData(user,providerDao,campusDao,programDao);
        File file = new File("testwrite1.xlsx");
        WriteToExcel writeToExcel = new WriteToExcel();
        writeToExcel.WriteToExcel(testdata,file);

        return "testPage";
    }

    @PostMapping("test/read")
    public String ReadTest(){
        ReadFromExcel readFromExcel = new ReadFromExcel(providerDao,campusDao,programDao);
        File file = new File("/Users/robertlr/IdeaProjects/etps/testread1.xlsx");
        readFromExcel.ReadExcel(file);
        return "testPage";
    }
}
