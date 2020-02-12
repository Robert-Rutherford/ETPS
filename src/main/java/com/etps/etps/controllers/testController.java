package com.etps.etps.controllers;

import com.etps.etps.excelConversions.ReadFromExcel;
import com.etps.etps.excelConversions.WriteToExcel;
import com.etps.etps.models.User;
import com.etps.etps.repositories.Campuses;
import com.etps.etps.repositories.Programs;
import com.etps.etps.repositories.Providers;
import com.etps.etps.repositories.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Map;
import java.util.Objects;

@Controller
public class testController {
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
    public String WriteTest() {
        String home = System.getProperty("user.home");
        File file = new File(home+"/Downloads/ETPS_data.xlsx");
        User user = userDao.findByProviderId(802);
        WriteToExcel writeToExcel = new WriteToExcel();
        Map<String, Object[]> testdata = writeToExcel.GenerateUserData(user, providerDao, campusDao, programDao);
//        File file = new File("testwrite1.xlsx");
        writeToExcel.WriteExcel(testdata, file);

        user = userDao.findByProviderId(1);
        testdata = writeToExcel.GenerateUserData(user, providerDao, campusDao, programDao);
        file = new File("testwrite2.xlsx");
        writeToExcel.WriteExcel(testdata, file);

        return "redirect:/test";
    }

    @PostMapping("test/read")
    public String ReadTest() {
//        public String ReadTest
//    }(Model model,@RequestParam("readFile") MultipartFile file,
//                           RedirectAttributes redirectAttributes) {


//        JFrame parentFrame = new JFrame("File to Read");
//
//        parentFrame.setSize(800, 600);
//        parentFrame.setLocationRelativeTo(null);
//        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        parentFrame.setVisible(true);
//
//        JFileChooser fileChooser = new JFileChooser();
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("xslx");
//        fileChooser.setFileFilter(filter);
//        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//        fileChooser.setDialogTitle("Specify a file to save");
//
//        int userSelection = fileChooser.showSaveDialog(parentFrame);
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//        }
//        File pathTest = new File((String) Objects.requireNonNull(model.getAttribute("readFile")));



        ReadFromExcel readFromExcel = new ReadFromExcel(providerDao, campusDao, programDao);
        File file = new File("/Users/robertlr/IdeaProjects/etps/testread1.xlsx");
        readFromExcel.ReadExcel(file);
//        readFromExcel.ReadExcel(pathTest);
        return "testPage";
    }
}
