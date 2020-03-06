package com.paralleltest;

import java.util.Map;

import org.testng.annotations.Test;

import com.pageFactory.ClickAcadamyDashboard;
import com.pageFactory.ClickAcadamyLandingPage;
import com.pageFactory.ClickAcadamyLoginPage;
import com.pageFactory.ClickAcadamyRegisterPage;
import com.pageFactory.ITProjectRequestPage;
import com.paralleltest.basetest.BaseTest;
import com.paralleltest.basetest.TestUtilities;
import com.paralleltest.utils.CsvDataProvider;
import com.paralleltest.utils.DBDataProvider;
import com.paralleltest.utils.ExcelDataProvider;

import freemarker.template.utility.CaptureOutput;
import junit.framework.Assert;

public class SignInPageTest extends TestUtilities {

	// @Test(dataProvider="csvTestReader", dataProviderClass = CsvDataProvider.class)
	public void loginCSVTest(Map<String, String> testData) {

		System.err.println("Running Test=> " + this + " -> on thread [" + Thread.currentThread().getId() + "]");

		// data extraction
		String id = testData.get("id");
		String username = testData.get("username");
		String password = testData.get("password");

		log.info("Starting positiveLogInTest #" + id + " for " + username);

		ClickAcadamyLandingPage home = new ClickAcadamyLandingPage(getDriver(), log);
		home.openPage();

		// assert page url
		Assert.assertEquals(home.getCurrentUrl(), home.getPageUrl());

		// assert page headline
		String headline = "An Academy to Learn Earn & Shine  in your QA Career";
		Assert.assertEquals(headline, home.getPageHeadline());

		// assert nav-bar
		Assert.assertTrue(home.getNavBar());

		home.closeNewsLetterPopup();

		// click login
		ClickAcadamyLoginPage loginPage = new ClickAcadamyLoginPage(getDriver(), log);
		loginPage = home.clickLoginBtn();

		// loginPage.enterEmailAddress("unresticteduser@gmail.com");
		loginPage.enterEmailAddress(username);

		loginPage.enterPassword(password);

		ClickAcadamyDashboard dashboard = loginPage.clickLogin();
		dashboard.clickLogout();

	}

	//@Test(dataProvider = "excelTestData", dataProviderClass = ExcelDataProvider.class)
	public void loginTestExcelData(String username, String password) {

		System.err.println("Running Test=> " + this + " -> on thread [" + Thread.currentThread().getId() + "]");

		log.info("Starting positiveLogInTest #  for " + username);

		ClickAcadamyLandingPage home = new ClickAcadamyLandingPage(getDriver(), log);
		home.openPage();

		// assert page url
		Assert.assertEquals(home.getCurrentUrl(), home.getPageUrl());

		// assert page headline
		String headline = "An Academy to Learn Earn & Shine  in your QA Career";
		Assert.assertEquals(headline, home.getPageHeadline());

		// assert nav-bar
		Assert.assertTrue(home.getNavBar());

		home.closeNewsLetterPopup();

		// click login
		ClickAcadamyLoginPage loginPage = new ClickAcadamyLoginPage(getDriver(), log);
		loginPage = home.clickLoginBtn();

		// loginPage.enterEmailAddress("unresticteduser@gmail.com");
		loginPage.enterEmailAddress(username);

		loginPage.enterPassword(password);

		ClickAcadamyDashboard dashboard = loginPage.clickLogin();
		dashboard.clickLogout();

	}
	
	@Test(dataProvider = "databaseTestData", dataProviderClass = DBDataProvider.class)
		public void loginTestDBData(String username, String password) {

			System.err.println("Running Test=> " + this + " -> on thread [" + Thread.currentThread().getId() + "]");

			log.info("Starting positiveLogInTest #  for " + username);

			
			ClickAcadamyLandingPage home = new ClickAcadamyLandingPage(getDriver(), log);
			home.openPage();

			// assert page url
			Assert.assertEquals(home.getCurrentUrl(), home.getPageUrl());

			// assert page headline
			String headline = "An Academy to Learn Earn & Shine  in your QA Career";
			Assert.assertEquals(headline, home.getPageHeadline());

			// assert nav-bar
			Assert.assertTrue(home.getNavBar());

			home.closeNewsLetterPopup();

			// click login
			ClickAcadamyLoginPage loginPage = new ClickAcadamyLoginPage(getDriver(), log);
			loginPage = home.clickLoginBtn();

			// loginPage.enterEmailAddress("unresticteduser@gmail.com");
			loginPage.enterEmailAddress(username);

			loginPage.enterPassword(password);

			ClickAcadamyDashboard dashboard = loginPage.clickLogin();
			dashboard.clickLogout();
			

		}
	
	//@Test(dataProvider = "SearchProvider", dataProviderClass = DBDataProvider.class)
	public void loginTest(String username, String password) {

		System.err.println("Running Test=> " + this + " -> on thread [" + Thread.currentThread().getId() + "]");

		log.info("Starting LogInTest #  for " + username);

		ClickAcadamyLandingPage home = new ClickAcadamyLandingPage(getDriver(), log);
		home.openPage();

		home.closeNewsLetterPopup();

		// click login
		ClickAcadamyLoginPage loginPage = new ClickAcadamyLoginPage(getDriver(), log);
		loginPage = home.clickLoginBtn();

		loginPage.enterEmailAddress(username);

		loginPage.enterPassword(password);

		ClickAcadamyDashboard dashboard = loginPage.clickLogin();
		dashboard.clickLogout();
		
	}



	// @Test(dataProvider="csvTestReader", dataProviderClass =  csvDataProvider.class)
	public void ariba(Map<String, String> testData) {

		// data extraction
		String id = testData.get("id");
		String username = testData.get("username");
		String password = testData.get("password");

		log.info("Starting positiveLogInTest #" + id + " for " + username);

		ClickAcadamyLandingPage home = new ClickAcadamyLandingPage(getDriver(), log);
		home.openPage();

		home.enterUsername(username);
		home.enterPassword(password);

		ClickAcadamyLoginPage loginPage = home.clickLogin();
		takeScreenshot("login home");
		ITProjectRequestPage proj = loginPage.createITProjectRequest();
		takeScreenshot("create");
		// proj.enterProjectName("This is a test");
		// proj.enterGeneralDescrition("mine is working...");
		// proj.clickUploadFile();
		// proj.clickDateWidget();
		proj.clickSubmit();
		takeScreenshot("click submit");

	}
}
