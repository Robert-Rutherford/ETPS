package com.etps.etps.excelConversions;

import com.etps.etps.models.Campus;
import com.etps.etps.models.Program;
import com.etps.etps.models.Provider;
import com.etps.etps.models.User;
import com.etps.etps.repositories.Campuses;
import com.etps.etps.repositories.Programs;
import com.etps.etps.repositories.Providers;
import com.etps.etps.repositories.Users;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FormLayout {

    private Users userDao;
    private Programs programsDao;
    private Providers providerDao;
    private Campuses campusDao;

//    public FormLayout(Users userDao, Programs programsDao, Providers providerDao, Campuses campusDao) {
//        this.userDao = userDao;
//        this.programsDao = programsDao;
//        this.providerDao = providerDao;
//        this.campusDao = campusDao;
//    }

    public Map<String, Object[]> GenerateUserData(User user){
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"Provider ID", "Provider Name", "Provider Description","Campus ID","Campus Name",
                "Program ID", "Program Name", "Program Description", "ETP ID"});
        int treeNum = 2;
//        Provider provider = providerDao.findById(user.getProvider().getId());
        Provider provider = providerDao.findById(802);
        List<Campus> campusesList = campusDao.findAllByProvider_Id(provider.getId());
        for (Campus campus: campusesList) {
            List<Program> programsList = programsDao.findAllByCampus_Id(campus.getId());
            for (Program program: programsList) {
                data.put(Integer.toString(treeNum),
                        new Object[] {provider.getId(),provider.getProviderName(), provider.getDescription(),
                                campus.getId(), campus.getCampusName(),program.getId(),program.getName(),
                                program.getDescription(),program.getEtpCodeId()});
                treeNum++;
            }
        }

        return data;
    }

//    public static void main(String[] args) {
//        User user = new User();
//        FormLayout formLayout = new FormLayout();
//        Map<String, Object[]> testdata = formLayout.GenerateUserData(user);
//        File file = new File("testwrite1.xlsx");
//        WriteToExcel writeToExcel = new WriteToExcel();
//        writeToExcel.WriteToExcel(testdata,file);
//    }

//    public void generateObjectRow (Module module,)

}
