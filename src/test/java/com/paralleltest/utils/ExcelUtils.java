package com.paralleltest.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	

	public ExcelUtils(String excelPath, String sheetName) {

		File src = new File(excelPath);
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);

			sheet = wb.getSheet(sheetName);

		} catch (Exception e) {

			throw new RuntimeException("Could not read excel" + excelPath + " file.\n" + e.getStackTrace().toString());
		}

	}

	public static int getRowCount() {
		int rowCount = 0;

		try {
			rowCount = sheet.getPhysicalNumberOfRows();
			//System.out.println("No. of Rows: " + rowCount);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return rowCount;
	}

	public static int getColumnCount() {
		int colCount = 0;

		try {
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			//System.out.println("No. of Colunms: " + colCount);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return colCount;
	}

	// get sheet by name
	public static String getStringData(String sheetName, int row, int column) {
		
		String cellData = wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		return cellData;
	}
	// get sheet by name
	public static String getCellStringData(int row, int column) {
		DataFormatter formatter = new DataFormatter();
		Cell cell = sheet.getRow(row).getCell(column);
		String cellData = formatter.formatCellValue((cell));
		return cellData;
	}

	// get sheet @ index...
	public static String getStringData(int sheetIndex, int row, int column) {
		return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getStringCellValue();
	}

	// get numeric data
	public static double getNumericData(String sheetName, int row, int column) {
		double cellData = wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
		System.out.println(cellData);
		return cellData;
	}

}
