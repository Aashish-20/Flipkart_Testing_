package com.flipkart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.utils.WaitUtils;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By productNameInCart = By.xpath("//div[contains(@class, 'product-name')]");
    private By cartProductTitle = By.xpath("(//img[@class='DByuf4'])[1]");
    private By removeButton = By.xpath("(//div[normalize-space()='Remove'])[1]");
    private By removeButtonPopup = By.xpath("//div[@class='sBxzFz fF30ZI A0MXnh']");
    private By emptyCartMessage = By.xpath("//div[@class='eIDgeN']");
    

    public String getCartProductTitle() {
        WebElement titleElement = driver.findElement(cartProductTitle);
        System.out.println(titleElement.getText());
        return titleElement.getText();
    }

    public void removeProductFromCart() {
    	WebElement removeBtn = driver.findElement(removeButton);
    	removeBtn.click();
    	
    	WebElement removeBtnPopup = driver.findElement(removeButtonPopup);
    	WaitUtils.waitForElementToBeVisible(driver, removeBtnPopup, 10);
    	removeBtnPopup.click();
    	
    }
    
    public boolean isCartEmpty() {
			wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
			return true;	
    }
    
    public boolean isProductInCart(String productName) {
        return driver.findElement(productNameInCart).getText().contains(productName);
    }

}