package com.flipkart.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.utils.WaitUtils;

public class SellerRegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    
    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number']")
    WebElement enterMobileNumberInputElement;
    
    @FindBy(xpath = "//button[normalize-space()='Start Selling']")
    WebElement startSellingBtnElement;
    
    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number']")
    WebElement registerEmailInputElement;
    
    @FindBy(xpath = "//input[@placeholder='Enter Email ID']")
    WebElement enterEmailElement;
    
    @FindBy(xpath = "//button[normalize-space()='Send OTP']")
    WebElement sendOTPBtnElement;
    
    @FindBy(xpath = "//input[@aria-label='Please enter verification code. Digit 1']")
    WebElement otpDigitOne;
    
    @FindBy(xpath = "//input[@aria-label='Digit 2']")
    WebElement otpDigitTwo;
    
    @FindBy(xpath = "//input[@aria-label='Digit 3']")
    WebElement otpDigitThree;
    
    @FindBy(xpath = "//input[@aria-label='Digit 4']")
    WebElement otpDigitFour;
    
    @FindBy(xpath = "//input[@aria-label='Digit 5']")
    WebElement otpDigitFive;
    
    @FindBy(xpath = "//input[@aria-label='Digit 6']")
    WebElement otpDigitSix;
    
    @FindBy(xpath = "//span[normalize-space()='Register & Continue']")
    WebElement registerBtn;

    @FindBy(xpath = "//p[@class='resusables__Para-sc-ds85e6-5 styles__ErrorInfo-sc-12mlfxt-8 cGkuJs']")
    WebElement invalidOtpErrorMsgElement;
    
    public SellerRegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    
    public boolean isStartSellingBtnPresent() {
    	WaitUtils.waitForElementToBeVisible(driver, startSellingBtnElement, 10);
    	return startSellingBtnElement.isDisplayed();
    }
    
    public void clickStartSellingBtn() {
    	startSellingBtnElement.click();
    }
    
    public void enterNumberForSelling(String mobNum) {
    	enterMobileNumberInputElement.sendKeys(mobNum);
    }
    
    public void enterEmailForSelling(String mobNum) {
    	enterEmailElement.sendKeys(mobNum);
    }
    
    public void clickOtpBtn() {
    	sendOTPBtnElement.click();
    }
    
    
    public void registerForSelling(String testData) {
    	String s[]=testData.split(" ");
    	
    	String mobileNum = s[0];
    	String email = s[1];
    	String otpDigitONE = s[2];
    	String otpDigitTWO = s[3];
    	String otpDigitTHREE = s[4];
    	String otpDigitFOUR = s[5];
    	String otpDigitFIVE = s[6];
    	String otpDigitSIX = s[7]; 
    	
    	clickStartSellingBtn();
    	enterNumberForSelling(mobileNum);
    	clickOtpBtn();
    	
    	//enter otp
    	otpDigitOne.sendKeys(otpDigitONE);
    	otpDigitTwo.sendKeys(otpDigitTWO);
    	otpDigitThree.sendKeys(otpDigitTHREE);
    	otpDigitFour.sendKeys(otpDigitFOUR);
    	otpDigitFive.sendKeys(otpDigitFIVE);
    	otpDigitSix.sendKeys(otpDigitSIX);
    	
    	enterEmailForSelling(email);
    	
    	registerBtn.click();
    	
    }
    
    
    
    public boolean isInvalidOtpMsgDisplayed() {
    	WaitUtils.waitForElementToBeVisible(driver, invalidOtpErrorMsgElement, 10);
    	return true;
    }
}