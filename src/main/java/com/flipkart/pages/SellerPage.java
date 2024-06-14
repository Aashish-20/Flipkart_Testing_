package com.flipkart.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.utils.WaitUtils;

public class SellerPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(xpath = "//span[contains(text(),'Become a Seller')]")
    WebElement sellerButtonElement;
    
    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement loginBtnElement;
    
    @FindBy(xpath = "//input[@placeholder='Username or phone number or email']")
    WebElement enterUsernameElement;
    
    @FindBy(xpath = "//input[@placeholder='Enter password']")
    WebElement enterPasswordElement;
    
    @FindBy(xpath = "//span[normalize-space()='Next']")
    WebElement nextBtnElement;
    
    @FindBy(xpath = "//span[normalize-space()='Login']")
    WebElement loginElement;
    
    @FindBy(xpath = "//div[normalize-space()='Wrong user details. Please enter correct details to log in.*']")
    WebElement errorCredentialsMsgeElement;
    
    @FindBy(xpath = "//button[normalize-space()='Forgot Password?']")
    WebElement forgotPasswordElememnt;
    
    @FindBy(xpath = "//input[@placeholder='Registered Email Address']")
    WebElement enterEmailInputElement;
    
    @FindBy(xpath = "//span[normalize-space()='Send Password Reset link']")
    WebElement resetPasswordBtn;
    
    @FindBy(xpath = "//div[@class='styles__ContentWrapperForgotNote-sc-yobpp6-9 cKWDRE']")
    WebElement restPasswordSuccessMsg;
    
    @FindBy(xpath = "//button[normalize-space()='Start Selling']")
    WebElement startSellingBtnElement;
    
    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number']")
    WebElement registerEmailInputElement;
    
    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number']")
    WebElement enterMobileNumberInputElement;

    public SellerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isSellerBtnPresent() {
    	WaitUtils.waitForElementToBeVisible(driver, sellerButtonElement, 10);
    	return sellerButtonElement.isDisplayed();
    }
    
    
    
    public void sellerBtnClick() {
    	sellerButtonElement.click();
    }
    
    public void clickSellerLoginBtn() {
    	loginBtnElement.click();
    }
    
    public void enterUsernameOrEmail(String username) {
    	enterUsernameElement.sendKeys(username);
    	nextBtnElement.click();
    }
    
    public void enterPassword(String password) {
    	enterPasswordElement.sendKeys(password);
    	loginElement.click();
    }
    
    public void forgotPasswordClick() {
    	forgotPasswordElememnt.click();
    }
    
    public void enterEmailForPasswordReset(String email) {
		enterEmailInputElement.sendKeys(email);
		resetPasswordBtn.click();
		
	}
    
    public boolean isSuccessMsgDisplayed() {
    	WaitUtils.waitForElementToBeVisible(driver, restPasswordSuccessMsg, 10);
    	return true;
    }
    
    public void loginSeller(String usernameAndPassowrd) {
    	String s[]=usernameAndPassowrd.split(" ");
    	sellerBtnClick();
    	clickSellerLoginBtn();
    	enterUsernameOrEmail(s[0]);
    	enterPassword(s[1]);
    }
    
    public void resetPasswordSeller(String credentials, String resetPasswordEmail) {
    	loginSeller(credentials);
    	forgotPasswordClick();
    	enterEmailForPasswordReset(resetPasswordEmail);
    	
    }
    
    public boolean isCredentialsValid() {
    	WaitUtils.waitForElementToBeVisible(driver, errorCredentialsMsgeElement, 10);
    	return true;
    }
    
    public boolean isStartSellingBtnPresent() {
    	WaitUtils.waitForElementToBeVisible(driver, startSellingBtnElement, 10);
    	return startSellingBtnElement.isDisplayed();
    }
    
    public void clickStartSellingBtn() {
    	startSellingBtnElement.click();
    }
    
    public void enterEmailForSelling(String email) {
    	enterEmailInputElement.sendKeys(email);
    }
    
    public void registerForSelling() {
    	clickStartSellingBtn();
    }
}