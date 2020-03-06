package com.datasource.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel
{
   public static String excelFilePath = "C:/Users/I350380/Documents/GitHub/Java-Selenium-WebDriver-Automation-Framework/E2EAutomationProject/src/main/java/com/datasource/excel/logindata.xlsx";
   
    static ArrayList<String> userList;

   public static ArrayList<String> excelUserLoginDetails (int colNum)
    {
        try {

        	File file = new File(excelFilePath);
        	FileInputStream excelFile = new FileInputStream(file);
        	Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = datatypeSheet.iterator();
            rowIterator.next();
            userList = new ArrayList<String>();
            while (rowIterator.hasNext()) {
                userList.add(rowIterator.next().getCell(colNum).getStringCellValue());
            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
       
		return userList;
    }
   
}
