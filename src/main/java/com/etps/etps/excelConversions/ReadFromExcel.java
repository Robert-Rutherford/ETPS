package com.etps.etps.excelConversions;

import com.etps.etps.models.Campus;
import com.etps.etps.models.Program;
import com.etps.etps.models.Provider;
import com.etps.etps.models.ReadExcelObject;
import com.etps.etps.repositories.Campuses;
import com.etps.etps.repositories.Programs;
import com.etps.etps.repositories.Providers;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.parameters.P;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map;

public class ReadFromExcel {
    private Providers providerDao;
    private Campuses campusDao;
    private Programs programDao;

    public ReadFromExcel(Providers providerDao, Campuses campusDao, Programs programDao) {
        this.providerDao = providerDao;
        this.campusDao = campusDao;
        this.programDao = programDao;
    }

    public void ReadExcel (File filePath){
        try {
            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); //skip header
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                if (row.getRowNum() == 0){
                    continue;
                }

//                Object rowObject = new Object();
//                ReadExcelObject newRow = new ReadExcelObject();
                Provider newProvider = new Provider();
                Program newProgram = new Program();
                Campus newCampus = new Campus();

                newCampus.setProvider(newProvider);
                newProgram.setCampus(newCampus);

                // new Object[] {provider.getId(),provider.getProviderName(), provider.getDescription(),
//                        campus.getId(), campus.getCampusName(),program.getId(),program.getName(),
//                        program.getDescription(),program.getEtpCodeId()});

                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();

                    //Check the cell type and format accordingly
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex){
                        case 0:
                            long provider_id = (long) cell.getNumericCellValue();
                            newProvider.setId(provider_id);
                            break;
                        case 1:
                            newProvider.setProviderName(cell.getStringCellValue());
                            break;
                        case 2:
                            newProvider.setDescription(cell.getStringCellValue());
                            break;
                        case 3:
                            long campus_id = (long) cell.getNumericCellValue();;
                            newCampus.setId(campus_id);
                            break;
                        case 4:
                            newCampus.setCampusName(cell.getStringCellValue());
                            break;
                        case 5:
                            long program_id = (long) cell.getNumericCellValue();
                            newProgram.setId(program_id);
                            break;
                        case 6:
                            newProgram.setName(cell.getStringCellValue());
                            break;
                        case 7:
                            newProgram.setDescription(cell.getStringCellValue());
                            break;
                        case 8:
                            newProgram.setEtpCodeId(cell.getStringCellValue());
                            break;
                    }
                }

                ReadExcelObject excelData =  new ReadExcelObject(newProvider,newCampus,newProgram);
                readToDatabase(excelData);
            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void readToDatabase(ReadExcelObject data){

        Provider newProvider = data.getNewProvider();
        Campus newCampus = data.getNewCampus();
        Program newProgram = data.getNewProgram();

        providerDao.save(newProvider);
        campusDao.save(newCampus);
        programDao.save(newProgram);


    }

}
