package com.etps.etps.excelConversions;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;

public class ReadFromExcel {
    public void ReadFromExcel (FileInputStream file){
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case NUMERIC:
//                           numeric values
                            cell.getNumericCellValue();
                            break;
                        case STRING:
//                            string values
                            cell.getStringCellValue();
                            break;
                        default:
                            break;
                    }
                }
                System.out.println("");
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
