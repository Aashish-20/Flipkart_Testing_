package com.flipkart.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.utils.WaitUtils;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//img[@title='Flipkart']")
    WebElement flipkartLogo;
    
    @FindBy(xpath = "//input[@name='q']")
    WebElement searchBar;

    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isFlipkartLogoVisible() {
        WaitUtils.waitForElementToBeVisible(driver, flipkartLogo, 10);
        return flipkartLogo.isDisplayed();
    }
    
    public boolean isSearchBarPresent() {
    	WaitUtils.waitForElementToBeVisible(driver, searchBar, 10);
    	return searchBar.isDisplayed();
    }
    
    public void searchForProduct(String productName) {
    	searchBar.sendKeys(productName);
    	searchBar.submit();
    }
    
    public void selectProductFromResults(String productName) {
        String originalWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        
        driver.findElement(productLink(productName)).click();
        
        // Wait for new window to open and switch to it
        String newWindow = driver.getWindowHandles().stream()
                .filter(windowHandle -> !oldWindows.contains(windowHandle))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("New window did not open"));
        
        driver.switchTo().window(newWindow);
    }

    public void switchBackToOriginalWindow(String originalWindow) {
        driver.close();
        driver.switchTo().window(originalWindow);
    }
    
    private By productLink(String productName) {
    	
        return By.xpath("(//img[@class='_53J4C-'])[1]");
    }
    
    public boolean isFirstProductDisplayed() {
    	try {
			By firstProduct = By.xpath("(//img[@class='_53J4C-'])[1]");
			wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
			return true;
		} catch (Exception e) {
			return false;
		}
    }
  
}