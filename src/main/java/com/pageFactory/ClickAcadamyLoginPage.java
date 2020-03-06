package com.pageFactory;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClickAcadamyLoginPage extends BasePage{
	
	public ClickAcadamyLoginPage(WebDriver driver, Logger log) {
		super(driver, log);
		this.driver =driver;
	}
	
	private By emailLocator = By.id("user_email");
	private By passwordLocator = By.id("user_password");
	private By loginBtnLocator = By.name("commit");
	
	private By createDrop = By.xpath("//span[contains(@class,'a-srch-bar-create')]");
	private By proj = By.xpath("//*[contains(@title,'Birthday cake')]");

	public void enterEmailAddress(String email) {
		enter(email, emailLocator);
		sleep(30);
	}

	public void enterPassword(String pass) {
		enter(pass, passwordLocator);
		sleep(5);
	}

	public ClickAcadamyDashboard clickLogin() {
		click(loginBtnLocator);
		sleep(15);
		return new ClickAcadamyDashboard(driver, log);
	}

	public ITProjectRequestPage createITProjectRequest() {
		
		click(createDrop);
		click(proj);
		
		return new ITProjectRequestPage(driver, log);
	}

}
