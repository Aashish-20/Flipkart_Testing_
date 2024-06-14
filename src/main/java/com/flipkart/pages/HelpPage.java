package com.flipkart.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.utils.WaitUtils;

public class HelpPage {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//img[@alt='Dropdown with more help links']")
	WebElement hamburgerDotsClickBtnelElement;
	
	@FindBy(xpath = "//li[normalize-space()='24x7 Customer Care']")
	WebElement helpBtnElement;
	
	@FindBy(xpath = "//span[normalize-space()='Delivery related']")
	WebElement helpTopicsBtnElement;
	
	@FindBy(xpath = "//p[contains(text(),'Can I take the shipment after opening and checking')]")
	WebElement helpIssueBtnElement;
	
	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement wasthisHelpFullYESElement;
	
	@FindBy(xpath = "//button[normalize-space()='No']")
	WebElement wasthisHelpFullNoElement;
	
	@FindBy(xpath = "//span[@class='HPOC3V']")
	WebElement feedbackElement;
	
	@FindBy(xpath = "//button[normalize-space()='Information content is not helpful.']")
	WebElement whatWentWrongElement;
	
	public HelpPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	public void customerCare1() {
		hamburgerDotsClickBtnelElement.click();
		helpBtnElement.click();
		helpTopicsBtnElement.click();
		helpIssueBtnElement.click();
		wasthisHelpFullYESElement.click();
	}
	
	public void customerCare2() {
		hamburgerDotsClickBtnelElement.click();
		helpBtnElement.click();
		helpTopicsBtnElement.click();
		helpIssueBtnElement.click();
		wasthisHelpFullNoElement.click();
		whatWentWrongElement.click();
	}
	
	public boolean isFeedBackDisplayed() {
		WaitUtils.waitForElementToBeVisible(driver, feedbackElement, 10);
		return true;
	}
}
