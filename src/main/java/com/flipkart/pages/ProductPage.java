package com.flipkart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By buyNowButton = By.xpath("//button[normalize-space()='Buy Now']");
    private By addToCartButton = By.xpath("(//button[normalize-space()='Add to cart'])[1]");
    private By productTitle = By.xpath("(//img[@class='_53J4C-'])[1]") ;
    private By deliveryAddress = By.xpath("//span[normalize-space()='Delivery Address']");
    
    
    public String addToCart() {
        WebElement addToCartBtn = driver.findElement(addToCartButton);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (!isElementInView(addToCartBtn)) {
            js.executeScript("window.scrollBy(0, 250);");
        }

        removeOverlays();

        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        
        WebElement titlElement = driver.findElement(productTitle);
        String title = titlElement.getText();
        System.out.println(title);

        addToCartBtn.click();
        
        return title;
    }
    
    public void buyNow() {
        WebElement buyNowBtn = driver.findElement(buyNowButton);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (!isElementInView(buyNowBtn)) {
            js.executeScript("window.scrollBy(0, 250);");
        }

        removeOverlays();
        wait.until(ExpectedConditions.elementToBeClickable(buyNowBtn));
        buyNowBtn.click();
    }
    
    public boolean isDeliverAddressPresent() {
    	try {
			wait.until(ExpectedConditions.presenceOfElementLocated(deliveryAddress));
			return true;
		} catch (Exception e) {
			return false;
		}
    }

    private boolean isElementInView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (boolean) js.executeScript(
            "var rect = arguments[0].getBoundingClientRect(); " +
            "return (rect.top >= 0 && rect.left >= 0 && " +
            "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
            "rect.right <= (window.innerWidth || document.documentElement.clientWidth));",
            element);
    }

    private void removeOverlays() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "var overlays = document.getElementsByClassName('overlay-class-name');" +
            "for (var i = 0; i < overlays.length; i++) {" +
            "  overlays[i].style.display = 'none';" +
            "}");
    }
}
