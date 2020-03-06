/**
 * 
 */
package com.pageFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @author james.ngondo
 */
public class BasePage
{ 

	protected WebDriver driver;
	public WebDriverWait wait;
	protected Logger log;
	protected String _logTag;

	public BasePage(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
		wait = new WebDriverWait(driver, 5);
	}
	
	// STATIC SLEEP 
		protected void sleep(long millis) {
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

    
	/** Open page with given URL */
	protected void openUrl(String url) {
		driver.get(url);
	}

	/** Find element using given locator */
	protected WebElement findElement(By locator) {
		return driver.findElement(locator);
	}
	
	/** Find all elements using given locator */
	protected List<WebElement> findAllElements(By locator) {
		return driver.findElements(locator);
	}

	/** Click on element with given locator when its visible */
	protected void click(By locator) {
		waitForVisibilityOf(locator, 30);
		findElement(locator).click();
		log.info("Clicked WebElement");
	}
	
	protected boolean retryingFindClick(By by) {
	    boolean result = false;
	    int attempts = 0;
	    while(attempts < 2) {
	        try {
	            driver.findElement(by).click();
	            result = true;
	            break;
	        } catch(StaleElementReferenceException e) {
	        }
	        attempts++;
	    }
	    return result;
	}
	
	protected void clickStallElement(By locator) {
		
		try {
			waitForVisibilityOf(locator, 30);
			findElement(locator).click();
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			waitForVisibilityOf(locator, 30);
			findElement(locator).click();
		}
	}
	
	/** Click on element with given locator when its visible */
	protected void click(WebElement locator) {
		if(locator.isDisplayed()) {
			locator.click();
			log.info("Clicked WebElement");
		}
		
	}

	/** Type given text into element with given locator */
	protected void enter(String text, By locator) {
		waitForVisibilityOf(locator, 30);
		findElement(locator).sendKeys(text);
		log.info("Entered text: " + text);
	}
	
	protected void enter(String text, WebElement locator) {
		if(locator.isDisplayed()) {
			locator.sendKeys(text);
			log.info("Entered text: " + text);
		}
		
	}
	
	public void selectDropdownByValue(By by, int value) {
		WebElement dropdownElement = findElement(by);
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByValue("" + value);
	}
	
	public void selectDropdownByIndex(By by, int index) {
		WebElement dropdownElement = findElement(by);
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByIndex(index);
	}
	
	public void selectDropdownByVisibleText(By by, String visibleText) {
		WebElement dropdownElement = findElement(by);
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByVisibleText(visibleText);
	}

	/** Get URL of current page from browser */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	/** Get title of current page */
	public String getCurrentPageTitle() {
		return driver.getTitle();
	}

	/** Get source of current page */
	public String getCurrentPageSource() {
		return driver.getPageSource();
	}

	/**
	 * Wait for specific ExpectedCondition for the given amount of time in seconds
	 */
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}

	/**
	 * Wait for given number of seconds for element with given locator to be visible
	 * on the page
	 */
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}
	
	/** Wait for alert present and then switch to it */
	protected Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
		return driver.switchTo().alert();
	}

	public void switchToWindowWithTitle(String expectedTitle) {
		// Switching to new window
		String firstWindow = driver.getWindowHandle();

		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> windowsIterator = allWindows.iterator();

		while (windowsIterator.hasNext()) {
			String windowHandle = windowsIterator.next().toString();
			if (!windowHandle.equals(firstWindow)) {
				driver.switchTo().window(windowHandle);
				if (getCurrentPageTitle().equals(expectedTitle)) {
					break;
				}
			}
		}
	}

	/** Switch to iFrame using it's locator */
	protected void switchToFrame(By frameLocator) {
		driver.switchTo().frame(findElement(frameLocator));
	}
	
	/** Press Key on locator */
	protected void pressKeys(By locator, Keys key) {
		findElement(locator).sendKeys(key);
	}

	/** Press Key using Actions class */
	protected void pressKeyWithActions(Keys key) {
		log.info("Pressing " + key.name() + " using Actions class");
		Actions action = new Actions(driver);
		action.sendKeys(key).build().perform();
	}
	
	/** Perform scroll to the bottom */
	public void scrollToBottom() {
		log.info("Scrolling to the bottom of the page");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	/** Perform scroll to the top of page */
	public void scrollUpPage() {
		log.info("Scrolling to the bottom of the page");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(0,0)");
	}
	
	/** Perform scroll to view specific element on page */
	public void scrollToViewSpecificElementOnPage(By locator) {
		log.info("Scrolling to view specific element on page");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		WebElement element = findElement(locator);
		
		jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
	}
	

	/** Drag 'from' element to 'to' element */
	protected void performDragAndDrop(By from, By to) {
		// Actions action = new Actions(driver);
		// action.dragAndDrop(find(from), find(to)).build().perform();

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript(
				"function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
						+ "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n"
						+ "data: {},\n" + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
						+ "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n"
						+ "return event;\n" + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
						+ "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
						+ "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
						+ "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n"
						+ "}\n" + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
						+ "var dragStartEvent =createEvent('dragstart');\n"
						+ "dispatchEvent(element, dragStartEvent);\n" + "var dropEvent = createEvent('drop');\n"
						+ "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
						+ "var dragEndEvent = createEvent('dragend');\n"
						+ "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
						+ "var source = arguments[0];\n" + "var destination = arguments[1];\n"
						+ "simulateHTML5DragAndDrop(source,destination);",
				findElement(from), findElement(to));
	}

	/** Perform mouse hover over element */
	protected void hoverOverElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception var3) {
			return false;
		}
	}
	
	protected void click(By by1, By by2) {
		if (this.isElementPresent(by1)) {
			this.click(by1);
		} else {
			this.click(by2);
		}

	}
	
	protected WebElement findFirstVisible (String awNameSubstring, String visibleText)
    {
        List<WebElement> webElements =
            this.findElementsByAwnameSubstring(awNameSubstring);
        for (WebElement e : webElements) {
            if (e.isDisplayed() && e.getText().equals(visibleText)) {
                return e;
            }
        }
        return null;
    }
	
	 protected List<WebElement> findElementsByAwnameSubstring (String awNameSubstring)
	    {
	        return findAllElements(By.xpath("//*[contains(@awname, '" + awNameSubstring
	            + "')]"));
	    }
	
	 /**
     * Return a list with all the elements on the page that match the awname
     * substring.
     *
     * @param awNameSubstring
     * @param visibleText
     * @return
     * @author levan.voronin
     */
    protected List<WebElement> findElementsByAwnameSubstring (String awNameSubstring,
        String visibleText)
    {
        // The | character between the xpaths is the logical or operator.
        return findAllElements(By.xpath("//*[contains(@awname, '" + awNameSubstring + "') "
            + "and normalize-space(text())='" + visibleText + "'] | "
            + "//*[contains(@awname, '" + awNameSubstring + "')]"
            + "//*[normalize-space(text())='" + visibleText + "']"));
    }

  
}
