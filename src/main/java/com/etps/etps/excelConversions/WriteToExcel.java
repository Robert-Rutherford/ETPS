package com.etps.etps.excelConversions;

import com.etps.etps.models.*;
import com.etps.etps.repositories.Campuses;
import com.etps.etps.repositories.Programs;
import com.etps.etps.repositories.Providers;
import com.etps.etps.repositories.Submissions;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class WriteToExcel {
    private Providers providerDao;
    private Campuses campusDao;
    private Programs programDao;
    private Submissions submissionDao;

    public WriteToExcel(Providers providerDao, Campuses campusDao, Programs programDao,Submissions submissionDao) {
        this.providerDao = providerDao;
        this.campusDao = campusDao;
        this.programDao = programDao;
        this.submissionDao = submissionDao;
    }

    public void WriteExcel(Map<String, Object[]> data, File outPath) {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet();

        Set<String> keyset = data.keySet();

        int rowNum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rowNum++);
            Object[] objArr = data.get(key);
            int cellNum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellNum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Long)
                    cell.setCellValue((Long) obj);
            }
        }

        try {
            FileOutputStream out = new FileOutputStream(outPath);
            workbook.write(out);
            out.close();
            System.out.println("file written to " + outPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Map<String, Object[]> GenerateUserData(User user) {
        TreeMap<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"Provider ID", "Provider Name", "Provider Description", "Campus ID", "Campus Name",
                "Program ID", "Program Name", "Program Description", "ETP ID","Status"});
        int treeNum = 2;
        List<Provider> providers = new ArrayList<>();
        providers = providerDao.findAll();
        if (!user.isAdmin()) {
            List<Provider> searchProviders = new ArrayList<>();
            for (Provider provider: providers) {
                if (provider.getProvId() == user.getUserProviderId()){
                    searchProviders.add(provider);
                }
            }
            providers = searchProviders;
//            providers.add(providerDao.findAllById(user.getUserProviderId()));
        }

        for (Provider provider : providers) {
            treeNum = getTreeNum(data, treeNum, provider);
        }

        return data;
    }
    public Map<String, Object[]> GeneratePending(User user) {
        TreeMap<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"Provider ID", "Provider Name", "Provider Description", "Campus ID", "Campus Name",
                "Program ID", "Program Name", "Program Description", "ETP ID", "Status"});
        int treeNum = 2;
        List<Provider> providers = new ArrayList<>();
        List<Submission> submissions = new ArrayList<>();
        submissions = submissionDao.findAll();
        providers = providerDao.findAll();

        if (user.isAdmin()) {
            for (Provider provider : providers) {
                if ( (provider.getSubmission() != null) && provider.getSubmission().getStatus().equalsIgnoreCase("pending")){
//                    treeNum = getTreeNum(data, treeNum, submission);
                    treeNum = getTreeNum(data, treeNum, provider);
                }
            }
        } else {
            for (Provider provider : providers) {
                if ((provider.getSubmission() != null) && provider.getSubmission().getStatus().equalsIgnoreCase("pending")&&
                        (user.getUserProviderId() == provider.getProvId())){
//                    treeNum = getTreeNum(data, treeNum, submission);
                    treeNum = getTreeNum(data, treeNum, provider);
                }
            }
        }

        return data;
    }

    private int getTreeNum(TreeMap<String, Object[]> data, int treeNum, Provider provider) {
//        List<Campus> campusesList = campusDao.findAllByProvider_Id(provider.getId());
        List<Campus> campusesList = provider.getCampuses();

        for (Campus campus : campusesList) {
//            List<Program> programsList = programDao.findAllByCampus_Id(campus.getId());
            List<Program> programsList = campus.getPrograms();
            for (Program program : programsList) {
                data.put(Integer.toString(treeNum),
                        new Object[]{Long.toString(provider.getProvId()), provider.getProviderName(), provider.getDescription(),
                                campus.getCampId(), campus.getCampusName(), program.getProgId(), program.getName(),
                                program.getDescription(), program.getEtpCodeId(),provider.getSubmission().getStatus()});
                treeNum++;
            }
        }
        return treeNum;
    }

//    private int getTreeNum(TreeMap<String, Object[]> data, int treeNum, Submission submission) {
//        Provider provider = submission.getProvider();
//        Campus campus = submission.getCampus();
//        Program program = submission.getProgram();
//        data.put(Integer.toString(treeNum),
//                new Object[]{Long.toString(provider.getId()), provider.getProviderName(), provider.getDescription(),
//                        campus.getId(), campus.getCampusName(), program.getId(), program.getName(),
//                        program.getDescription(), program.getEtpCodeId(), submission.getStatus()});
//        treeNum++;
//        return treeNum;
//    }


}
