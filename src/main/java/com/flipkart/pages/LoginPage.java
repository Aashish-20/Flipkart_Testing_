package com.flipkart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(xpath = "//span[normalize-space()='Login']")
    WebElement loginBtn;
    
    @FindBy(xpath = "//input[@class='r4vIwl BV+Dqf']")
    WebElement enterCredentials;
    
    @FindBy(xpath = "//button[normalize-space()='Request OTP']")
    WebElement requestOtpBtn;
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public void clickLogin() {
    	loginBtn.click();
    }
    
    public void invalidLogin(String email) {
    	enterCredentials.sendKeys(email);
    	requestOtpBtn.submit();
    }
    
    public boolean erroMsgDisplayed() {
    	By errorMsg = By.xpath("//span[contains(text(),'Please enter valid Email ID/Mobile number')]");
    	wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
		return true;
    }
  
}