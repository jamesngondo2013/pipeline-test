package com.pageFactory;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ClickAcadamyRegisterPage extends ClickAcadamyLandingPage{

    public ClickAcadamyRegisterPage (WebDriver driver, Logger log)
    {
        super(driver, log);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(how=How.ID,using="user_name")
    @CacheLookup
    WebElement fullname;
    
    @FindBy(how=How.ID,using="user_email")
    @CacheLookup
    WebElement email;
    
    @FindBy(how=How.ID,using="user_password")
    @CacheLookup
    WebElement password;
    
    @FindBy(how=How.ID,using="user_password_confirmation")
    @CacheLookup
    WebElement confirm_password;
    
    @FindBy(how=How.ID,using="user_unsubscribe_from_marketing_emails")
    @CacheLookup
    WebElement check_unsubscribe;
    
    @FindBy(how=How.ID,using="user_agreed_to_terms")
    @CacheLookup
    WebElement check_agreed;
    
    @FindBy(how=How.XPATH,using=".//*[@name='commit']")
    @CacheLookup
    WebElement signUpBtn;

    public void enterUsername (String val)
    {
        fullname.sendKeys(val);
    }
    
    public void enterEmail (String val)
    {
        email.sendKeys(val);
    }

    public void enterPassword (String val)
    {
        password.sendKeys(val);
    }
    
    public void confirmPassword (String val)
    {
        confirm_password.sendKeys(val);
    }
    
    public void checkUnsubscribe ()
    {
        check_unsubscribe.click();
    }
    
    public void checkAgree ()
    {
        check_agreed.click();
    }

    public void clickSignUpBtn ()
    {
        signUpBtn.click();
    }


}
