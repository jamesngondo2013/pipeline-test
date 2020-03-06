package com.pageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ITProjectRequestPage extends ClickAcadamyLoginPage{

	private By inputs = By.xpath("//input[contains(@ng-reflect-klass,'form-control af-input')]");
	
	public ITProjectRequestPage(WebDriver driver, Logger log) {
		super(driver, log);
		this.driver = driver;
	}

	public void enterProjectName(String val) {
		// TODO Auto-generated method stub
		WebElement elem = listOfElements().get(0);
		enter(val, elem);
		sleep(2);
	}
	
	public void enterGeneralDescrition(String val) {
		// TODO Auto-generated method stub
		WebElement elem = listOfElements().get(1);
		enter(val, elem);
	}
	
	private List<WebElement> listOfElements(){
		
		List<WebElement> elem = findAllElements(inputs);
		
		return elem;
	}

	public void clickSubmit() {
		
		//click(By.xpath("//*[contains(text(), 'Submit')]"));
		//log.info("clicked Submit button");
		
		WebElement elem = findElement(By.xpath("//*[contains(text(), 'Submit')]"));
		click(elem);
	}

	public void clickUploadFile() {
		// TODO Auto-generated method stub
		scrollToBottom();
		By by = By.xpath("//*[contains(text(), 'Upload file')]");
		List<WebElement> elem = findAllElements(by);
		click(elem.get(0));
	}

	public void clickDateWidget() {
		// TODO Auto-generated method stub
		WebElement elem = findElement(By.xpath("//input[@placeholder='MM/DD/YYYY']"));
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", elem);
		
		log.info("clicked Date Icon");
	}

	

}
