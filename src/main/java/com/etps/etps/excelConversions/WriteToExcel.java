package com.etps.etps.excelConversions;

import com.etps.etps.models.Campus;
import com.etps.etps.models.Program;
import com.etps.etps.models.Provider;
import com.etps.etps.models.User;
import com.etps.etps.repositories.Campuses;
import com.etps.etps.repositories.Programs;
import com.etps.etps.repositories.Providers;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class WriteToExcel {
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

    public Map<String, Object[]> GenerateUserData(User user, Providers providerDao, Campuses campusDao, Programs programsDao) {
        TreeMap<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"Provider ID", "Provider Name", "Provider Description", "Campus ID", "Campus Name",
                "Program ID", "Program Name", "Program Description", "ETP ID"});
        int treeNum = 2;
        List<Provider> providers = new ArrayList<>();
        if (user.isAdmin()) {
            providers = providerDao.findAll();

        } else {
            providers.add(providerDao.findById(user.getProvider().getId()));
        }

        for (Provider provider : providers) {
            List<Campus> campusesList = campusDao.findAllByProvider_Id(provider.getId());

            for (Campus campus : campusesList) {
                List<Program> programsList = programsDao.findAllByCampus_Id(campus.getId());
                for (Program program : programsList) {
                    data.put(Integer.toString(treeNum),
                            new Object[]{Long.toString(provider.getId()), provider.getProviderName(), provider.getDescription(),
                                    campus.getId(), campus.getCampusName(), program.getId(), program.getName(),
                                    program.getDescription(), program.getEtpCodeId()});
                    treeNum++;
                }
            }
        }

        return data;
    }

}
