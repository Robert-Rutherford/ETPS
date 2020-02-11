package com.etps.etps.excelConversions;

import com.etps.etps.models.User;
import com.etps.etps.repositories.Users;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;

public class WriteToExcel {
    public void WriteToExcel(Map<String, Object[]> data, File outPath){

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet();

        Set<String> keyset = data.keySet();

        int rowNum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rowNum++);
            Object [] objArr = data.get(key);
            int cellNum = 0;
            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellNum++);
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }

        try{
            FileOutputStream out = new FileOutputStream(outPath);
            workbook.write(out);
            out.close();
            System.out.println("file written to "+ outPath);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
