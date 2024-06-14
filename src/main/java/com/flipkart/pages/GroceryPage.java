package com.flipkart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.utils.WaitUtils;

public class GroceryPage {

	WebDriver driver;
	WebDriverWait wait;
	@FindBy(xpath = "//span[contains(text(),'Grocery')]")
	WebElement grocery;

	@FindBy(xpath = "//input[@placeholder='Enter pincode']")
	WebElement pincodeInput;

	@FindBy(xpath = "//input[@name='q']")
	WebElement searchBar;
	
	@FindBy(xpath = "(//button[@class='QqFHMw PxrzFS'])[1]")
	WebElement addItemBtn;
	
	@FindBy(xpath = "//span[normalize-space()='Cart']")
	WebElement groceryCart;
	
	@FindBy(xpath = "//div[@class='n8P8St']//div[1]//button[1]")
	WebElement removeItemBtn;

	public GroceryPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public void clickOnGrocery() {
		grocery.click();
	}

	public boolean isPincodeInputDisplayed() {
		clickOnGrocery();
		WaitUtils.waitForElementToBeVisible(driver, pincodeInput, 10);
		return pincodeInput.isDisplayed();
	}

	public void enterPincode(String pincode) {
		pincodeInput.sendKeys(pincode);
		pincodeInput.submit();

	}

	public boolean isDeliverable(String pincode) {
		String xpath = "//div[contains(text(),'Delivery to " + pincode + "')]";
		WebElement deliverable = driver.findElement(By.xpath(xpath));
		return deliverable.isDisplayed();
	}

	public boolean nonDeliverable(String pincode) {
		String xpath = "//div[@class='MmnNsC']";
		WebElement errorMsg = driver.findElement(By.xpath(xpath));
		return errorMsg.isDisplayed();
	}

	public boolean isGrocerySearchBarPresent() {
		WaitUtils.waitForElementToBeVisible(driver, searchBar, 10);
		return searchBar.isDisplayed();
	}

	public void searchForGrocery(String productName) {
		searchBar.sendKeys(productName);
		searchBar.submit();
	}

	public void addItemsInCart() {
		WaitUtils.waitForElementToBeVisible(driver, addItemBtn, 10);
		addItemBtn.click();
	}
	
	public void navigateToCart() {
		groceryCart.click();
	}
	
	public void removeItem() {
		WaitUtils.waitForElementToBeVisible(driver, removeItemBtn, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='n8P8St']//div[1]//button[1]")));
		removeItemBtn.click();
		
	}
	
	public boolean isCartEmpty() {
		By emptyCartMessage = By.xpath("//div[@class='eIDgeN']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
		return true;	
}
}