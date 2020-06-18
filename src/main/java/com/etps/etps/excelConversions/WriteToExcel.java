package com.etps.etps.excelConversions;

import com.etps.etps.models.Campus;
import com.etps.etps.models.Program;
import com.etps.etps.models.Provider;
import com.etps.etps.models.User;
import com.etps.etps.repositories.*;
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

    //Additional
    //additions
    private IdsAndAckses idsAndAcksesDao;
    private CampusAdditionalContacts campusAdditionalContactsDao;
    private CampusAdditionalInformation campusAdditionalInformationDao;
    private CampusContacts campusContactsDao;
    private CampusAddresses campusAddressesDao;
    private ProgramAdditionalInformation programAdditionalInformationDao;
    private ProviderAdditionalContacts providerAdditionalContactsDao;
    private ProviderAddresses providerAddressesDao;
    private ProviderAdminContacts providerAdminContactsDao;
    private ProviderFinancialContacts providerFinancialContactsDao;

//    public WriteToExcel(Providers providerDao, Campuses campusDao, Programs programDao, Submissions submissionDao,
//                        IdsAndAckses idsAndAcksesDao, CampusAdditionalContacts campusAdditionalContactsDao,
//                        CampusAdditionalInformation campusAdditionalInformationDao, CampusContacts campusContactsDao,
//                        CampusAddresses campusAddressesDao, ProgramAdditionalInformation programAdditionalInformationDao,
//                        ProviderAdditionalContacts providerAdditionalContactsDao, ProviderAddresses providerAddressesDao,
//                        ProviderAdminContacts providerAdminContactsDao,
//                        ProviderFinancialContacts providerFinancialContactsDao) {
//        this.providerDao = providerDao;
//        this.campusDao = campusDao;
//        this.programDao = programDao;
//        this.submissionDao = submissionDao;
//        this.idsAndAcksesDao = idsAndAcksesDao;
//        this.campusAdditionalContactsDao = campusAdditionalContactsDao;
//        this.campusAdditionalInformationDao = campusAdditionalInformationDao;
//        this.campusContactsDao = campusContactsDao;
//        this.campusAddressesDao = campusAddressesDao;
//        this.programAdditionalInformationDao = programAdditionalInformationDao;
//        this.providerAdditionalContactsDao = providerAdditionalContactsDao;
//        this.providerAddressesDao = providerAddressesDao;
//        this.providerAdminContactsDao = providerAdminContactsDao;
//        this.providerFinancialContactsDao = providerFinancialContactsDao;
//    }

        public WriteToExcel(Providers providerDao, Campuses campusDao, Programs programDao, Submissions submissionDao) {
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

    public Map<String, Object[]> GenerateAllUserData(User user) {
        TreeMap<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"Provider ID", "Provider Name", "Provider Description", "Campus ID", "Campus Name",
                "Program ID", "Program Name", "Program Description", "ETP ID", "Status"});
        int treeNum = 2;
        List<Provider> providers = providerDao.findAll();
        if (!user.isAdmin()) {
            List<Provider> searchProviders = new ArrayList<>();
            for (Provider provider : providers) {
                if (provider.getProvId() == user.getUserProviderId()) {
                    searchProviders.add(provider);
                }
            }
            providers = searchProviders;

        }

        for (Provider provider : providers) {
            treeNum = getTreeNum(data, treeNum, provider);
        }

        return data;
    }

    public Map<String, Object[]> GenerateByStatus(User user, String status) {
        TreeMap<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"Provider ID", "Provider Name", "Provider Description", "Campus ID", "Campus Name",
                "Program ID", "Program Name", "Program Description", "ETP ID", "Status"});
        int treeNum = 2;
        List<Provider> providers = new ArrayList<>();
        providers = providerDao.findAll();

        if (user.isAdmin()) {
            for (Provider provider : providers) {
                if ((provider.getSubmission() != null) && provider.getSubmission().getStatus().equalsIgnoreCase(status)) {
                    treeNum = getTreeNum(data, treeNum, provider);
                }
            }
        } else {
            for (Provider provider : providers) {
                if ((provider.getSubmission() != null) && provider.getSubmission().getStatus().equalsIgnoreCase(status) &&
                        (user.getUserProviderId() == provider.getProvId())) {
                    treeNum = getTreeNum(data, treeNum, provider);
                }
            }
        }

        return data;
    }

    private int getTreeNum(TreeMap<String, Object[]> data, int treeNum, Provider provider) {

        List<Campus> campusesList = provider.getCampuses();

        for (Campus campus : campusesList) {
            List<Program> programsList = campus.getPrograms();
            for (Program program : programsList) {
                data.put(Integer.toString(treeNum),
                        new Object[]{Long.toString(provider.getProvId()), provider.getProviderName(), provider.getDescription(),
                                campus.getCampId(), campus.getCampusName(), program.getProgId(), program.getName(),
                                program.getDescription(), program.getEtpCodeId(), provider.getSubmission().getStatus()});
                treeNum++;
            }
        }
        return treeNum;
    }


}
