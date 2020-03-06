package com.paralleltest.utils;

import java.io.File;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

	public static void main(String[] args) {
		//String pathname = "src" + File.separator + "test" + File.separator + "resources"
		//		 + File.separator + "login-data.xlsx";
		//ExcelDataProvider excel = new ExcelDataProvider();
		//excel.testData();
	}
	
	@DataProvider(name="excelTestData", parallel=true)
	public Object[][] testData() {
		
		String pathname = "src" + File.separator + "test" + File.separator + "resources"
				 + File.separator + "login-data.xlsx";
		String sheetName = "login";
		
		//ExcelUtils utils = new ExcelUtils(pathname, sheetName);
		
		int rowCount = ExcelUtils.getRowCount();
		int colCount = ExcelUtils.getColumnCount();
		
		//rowCount-1 - this will exclude the excel header col
		Object[][] data = new Object[rowCount-1][colCount];
		
		for (int i = 1; i < rowCount; i++) {
			
			for (int j = 0; j <colCount; j++) {
				
				String cellData = ExcelUtils.getCellStringData(i, j);
				System.out.print(cellData + " | ");
				data[i-1][j] = cellData;
			}
			
			System.out.println();
		}
		
		return data;
		
	}

}
