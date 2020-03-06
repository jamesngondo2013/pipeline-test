package com.paralleltest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
	
	Properties prop;

	public ConfigDataProvider() {
				
		File src = new File ("./config/config.properties");
		
		try {
			
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
			
		
		} catch (Exception e) {
			System.out.println("Not able to load config file >> " + e.getMessage());
		}
		
		
	}
	
	public String getDataFromConfig(String keyToSearch) {
		
		return prop.getProperty(keyToSearch);
	}
	
	public String getBrowser() {
		
		return prop.getProperty("browser");
	}
	
	public String getStagingURL() {
		
		return prop.getProperty("pageUrl");
	}
	
	public String getExcelPathName() {
		
		return prop.getProperty("excelPath");
	}
	
	public String getExcelSheetName() {
		
		return prop.getProperty("excelSheetName");
	}
	
	//========================================
	public String getDBConnectionString() {
		
		return prop.getProperty("DB_URL");
	}
	
	public String getDBUsername() {
		
		return prop.getProperty("DB_USER");
	}
	
	public String getDBPassword() {
		
		return prop.getProperty("DB_PASS");
	}

}
