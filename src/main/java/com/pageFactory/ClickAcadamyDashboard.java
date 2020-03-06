package com.pageFactory;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClickAcadamyDashboard extends BasePage{

	private By dropdown = By.xpath("//*[contains(@class,'open-my-profile-dropdown')]");
	private By logout = By.xpath("//*[@class='user-signout']");
	private By logOutLinkBtn = By.cssSelector("a[href='/sign_out']");

	public ClickAcadamyDashboard(WebDriver driver, Logger log) {
		super(driver, log);
		this.driver = driver;
	}

	public void clickLogout() {
		// TODO Auto-generated method stub
		click(dropdown);
		sleep(5);
		click(logOutLinkBtn);
	}
	
	
}
