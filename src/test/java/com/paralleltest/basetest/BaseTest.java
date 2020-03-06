package com.paralleltest.basetest;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.datasource.db.DatabaseConnectionManager;
import com.paralleltest.utils.ConfigDataProvider;
import com.paralleltest.utils.ExcelUtils;

public class BaseTest {
	
	private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver>();
	public WebDriverWait wait;
	protected Logger log;
	protected FirefoxProfile profile;
	
	public ConfigDataProvider config;
	public ExcelUtils excelUtils;
	public DatabaseConnectionManager connectionManager;
	
	protected String testSuiteName;
	protected String testName;
	protected String testMethodName;
	
	public WebDriver getDriver() {
		return drivers.get();
	}
	
	@BeforeSuite
	public void setUpSuite() {
		
		config = new ConfigDataProvider();
		excelUtils = new ExcelUtils(config.getExcelPathName(), config.getExcelSheetName());
		
		//DB connection
		
		DatabaseConnectionManager.getConnectionManagerInstance().setConnectionString(config.getDBConnectionString());
		DatabaseConnectionManager.getConnectionManagerInstance().setUser(config.getDBUsername());
		DatabaseConnectionManager.getConnectionManagerInstance().setPass(config.getDBPassword());
		
		//connectionManager = DatabaseConnectionManager.getConnectionManagerInstance();
		//connectionManager.setConnectionString(config.getDBConnectionString());
		//connectionManager.setUser(config.getDBUsername());
		//connectionManager.setPass(config.getDBPassword());
	}

	@BeforeMethod()
	public void setUp(Method method, ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
		log = LogManager.getLogger(testName);
		
		WebDriver driver = BrowserDriverFactory.createDriver(config.getBrowser(), log);
		drivers.set(driver);
		driver.get(config.getStagingURL());
		
		profile=new FirefoxProfile();
		// Set preferences for file type 
		profile.setPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream");
		
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 5);
		
		this.testSuiteName = ctx.getSuite().getName();
		this.testName = testName;
		this.testMethodName = method.getName();
	}

	 @AfterMethod(alwaysRun = true)
	  public void tearDown(ITestResult result) {
	    getDriver().quit();
	    drivers.remove();
	  }

}
