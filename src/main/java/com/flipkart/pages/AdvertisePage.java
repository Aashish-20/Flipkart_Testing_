package com.flipkart.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.utils.WaitUtils;

public class AdvertisePage {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//img[@alt='Dropdown with more help links']")
	WebElement hamburgerDotsClickBtnelElement;

	@FindBy(xpath = "//li[normalize-space()='Advertise']")
	WebElement advertiseElement;

	@FindBy(xpath = "//input[@placeholder='Enter email']")
	WebElement enterEmailElement;

	@FindBy(xpath = "//input[@placeholder='Enter password']")
	WebElement enterPasswordElement;

	@FindBy(xpath = "//div[contains(text(),'Login')]")
	WebElement loginButtonElement;

	@FindBy(xpath = "//span[@class='notification-text-content']")
	WebElement confirmationMessagePopup;

	@FindBy(xpath = "//div[contains(text(),'Forgot Password?')]")
	WebElement forgotPasswordElement;

	@FindBy(xpath = "//div[normalize-space()='Continue']")
	WebElement continueButtonElement;

	@FindBy(css = ".styles__Heading-sc-18s7njp-7.erLrxD")
	WebElement isResetPasswordPageDisplayedElement;
	
	@FindBy(xpath = "//span[@type='error']")
	WebElement errorMsgElement;

	public AdvertisePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public void enterEmailInput(String email) {
		enterEmailElement.sendKeys(email);
	}

	public void enterPasswordInput(String password) {
		enterPasswordElement.sendKeys(password);
	}

	public void loginAdvertiseAccount(String credentials) {
		String s[] = credentials.split(" ");
		String email = s[0];
		String password = s[1];

		hamburgerDotsClickBtnelElement.click();
		advertiseElement.click();
		enterEmailInput(email);
		enterPasswordInput(password);
		loginButtonElement.click();

	}

	public void forgotPassword(String email) {
		hamburgerDotsClickBtnelElement.click();
		advertiseElement.click();
		forgotPasswordElement.click();
		enterEmailInput(email);
		continueButtonElement.click();
	}

	public void forgotPassword() {
		hamburgerDotsClickBtnelElement.click();
		advertiseElement.click();
		forgotPasswordElement.click();
		continueButtonElement.click();
	}
	
	public boolean isWarningMessageDispalyedForEmptyField() {
		WaitUtils.waitForElementToBeVisible(driver, errorMsgElement, 10);
		return true;
	}
	
	public boolean isWarnMessageDisplayed() {
		WaitUtils.waitForElementToBeVisible(driver, confirmationMessagePopup, 10);
		return true;
	}

	public boolean isResetPasswordPageDisplayed() {
		WaitUtils.waitForElementToBeVisible(driver, isResetPasswordPageDisplayedElement, 10);
		return true;
	}

}
