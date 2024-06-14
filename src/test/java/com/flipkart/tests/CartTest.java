package com.flipkart.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.flipkart.base.BaseTest;
import com.flipkart.pages.CartPage;
import com.flipkart.pages.HomePage;
import com.flipkart.pages.ProductPage;
import com.flipkart.utils.LoggerUtils;

public class CartTest extends BaseTest {

	@Test(priority = 1, groups = { "cart" }, enabled = true)
	public void testAddProductToCart() throws InterruptedException {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped from carttest");
			extentTest.log(Status.SKIP, "test skipped from carttest");
			throw new SkipException("test skipped due to execution requirement");
		}

		LoggerUtils.logInfo("test started for testAddProductToCart");
		extentTest = extentReports.createTest("Verify Add Product to Cart",
				"Test to verify adding a product to the cart");

		HomePage homePage = new HomePage(driver);
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);

		String productName = getTestData("testAddProductToCart");

		homePage.searchForProduct(productName);
		String originalWindow = driver.getWindowHandle();
		homePage.selectProductFromResults(productName);

		String expectedProductTitle = productPage.addToCart();
		String actualProductTitle = cartPage.getCartProductTitle();
		
		
		
		try {
			Assert.assertEquals(actualProductTitle, expectedProductTitle, "product title in the cart does not match");
			
			LoggerUtils.logInfo("test passed for testAddProductToCart");
			extentTest.log(Status.PASS, "Product has been successfully added to the cart");

			homePage.switchBackToOriginalWindow(originalWindow);
			
		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testAddProductToCart");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

		
	}

	@Test(priority = 2, groups = { "cart" }, enabled = true)
	public void testRemoveProductFromCart() throws InterruptedException {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testRemoveProductFromCart");
			extentTest.log(Status.SKIP, "test skipped from testRemoveProductFromCart");
			throw new SkipException("test skipped due to execution requirement");
		}

		LoggerUtils.logInfo("test started for testRemoveProductFromCart");
		extentTest = extentReports.createTest("Remove product from cart", "Test to remove product from cart");

		HomePage homePage = new HomePage(driver);
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);

		String productName = getTestData("testAddProductToCart");

		homePage.searchForProduct(productName);
		String originalWindow = driver.getWindowHandle();
		homePage.selectProductFromResults(productName);

		String expectedProductTitle = productPage.addToCart();
		String actualProductTitle = cartPage.getCartProductTitle();
		
		try {
			Assert.assertEquals(actualProductTitle, expectedProductTitle, "product title in the cart does not match");
			extentTest.log(Status.PASS, "Product has been successfully added to the cart");

			cartPage.removeProductFromCart();

			Assert.assertTrue(cartPage.isCartEmpty());
			LoggerUtils.logInfo("test passed for testRemoveProductFromCart");
			extentTest.log(Status.PASS, "Product has been successfully removed from the cart");

			homePage.switchBackToOriginalWindow(originalWindow);
			
		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testRemoveProductFromCart");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

		

	}

	@Test(priority = 3, groups = { "cart" }, enabled = true)
	public void testBuyNowBtnFunctionality() throws InterruptedException {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testBuyNowBtnFunctionality");
			extentTest.log(Status.SKIP, "test skipped from testBuyNowBtnFunctionality");
			throw new SkipException("test skipped due to execution requirement");
		}

		LoggerUtils.logInfo("test started for testBuyNowBtnFunctionality");
		extentTest = extentReports.createTest("Buy product", "Test to check the functionality of buy now button");

		HomePage homePage = new HomePage(driver);
		ProductPage productPage = new ProductPage(driver);

		String productName = getTestData("testAddProductToCart");

		try {
			homePage.searchForProduct(productName);
			String originalWindow = driver.getWindowHandle();
			homePage.selectProductFromResults(productName);

			productPage.buyNow();
			Assert.assertTrue(productPage.isDeliverAddressPresent(),
					"delivery address is not present. " + "Buy now buuton might be not working properly");
			
			LoggerUtils.logInfo("test passed for testBuyNowBtnFunctionality");

			extentTest.log(Status.PASS, "Buy now button is working properly. DElivery address is present");

			homePage.switchBackToOriginalWindow(originalWindow);
		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testBuyNowBtnFunctionality");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}
		

	}

}
