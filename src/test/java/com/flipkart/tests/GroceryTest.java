package com.flipkart.tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.flipkart.base.BaseTest;
import com.flipkart.pages.GroceryPage;
import com.flipkart.utils.LoggerUtils;

public class GroceryTest extends BaseTest {

	@Test(priority = 1, groups = { "grocery" }, enabled = true)
	public void testGroceryValidPincode() throws InterruptedException {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testGroceryValidPincode");
			extentTest.log(Status.SKIP, "test skipped from testGroceryValidPincode");
			throw new SkipException("test skipped due to execution requirement");
		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testGroceryValidPincode");
		extentTest = extentReports.createTest(methodName,
				"Test to check the presence and functionality of the pincode text bar");
		GroceryPage groceryPage = new GroceryPage(driver);

		try {

			Assert.assertTrue(groceryPage.isPincodeInputDisplayed(), "pincode bar is not present on the grocery page!");
			extentTest.pass("pincode text bar is present on the grocery page");

			String pincode = getTestData(methodName);
			groceryPage.enterPincode(pincode);
			Assert.assertTrue(groceryPage.isDeliverable(pincode));
			LoggerUtils.logInfo("test passed for testGroceryValidPincode");
			extentTest.pass("pincode is deliverable");

		} catch (AssertionError e) {
			LoggerUtils.logError("tets failed for testGroceryValidPincode");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 2, groups = { "grocery" }, enabled = true)
	public void testGroceryInValidPincode() throws InterruptedException {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testGroceryInValidPincode");
			extentTest.log(Status.SKIP, "test skipped from testGroceryInValidPincode");
			throw new SkipException("test skipped due to execution requirement");
		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testGroceryInValidPincode");
		extentTest = extentReports.createTest(methodName,
				"Test to check the presence and functionality of the pincode text bar");
		GroceryPage groceryPage = new GroceryPage(driver);

		try {
			Assert.assertTrue(groceryPage.isPincodeInputDisplayed(), "pincode bar is not present on the grocery page!");
			extentTest.pass("pincode text bar is present on the grocery page");

			String pincode = getTestData(methodName);
			groceryPage.enterPincode(pincode);
			Assert.assertTrue(groceryPage.nonDeliverable(pincode));
			LoggerUtils.logInfo("test passed for testGroceryInValidPincode");
			extentTest.pass("pincode is not deliverable");

		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testGroceryInValidPincode");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 3, groups = { "grocery" }, enabled = true)
	public void testGrocerySearchBarFunctionality() {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testGrocerySearchBarFunctionality");
			extentTest.log(Status.SKIP, "test skipped from testGrocerySearchBarFunctionality");
			throw new SkipException("test skipped due to execution requirement");

		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testGrocerySearchBarFunctionality");
		extentTest = extentReports.createTest(methodName,
				"Test to check the presence and functionality of the search bar");
		GroceryPage groceryPage = new GroceryPage(driver);

		try {
			groceryPage.clickOnGrocery();
			Assert.assertTrue(groceryPage.isGrocerySearchBarPresent(),
					"Search bar is not present on the grocery page!");
			LoggerUtils.logInfo("test passed for testGrocerySearchBarFunctionality");
			extentTest.pass("Search bar is present on the grocery page");

			String searchData = getTestData(methodName);
			groceryPage.searchForGrocery(searchData);
		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testGrocerySearchBarFunctionality");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 4, groups = { "grocery" }, enabled = false)
	public void testGroceryAddItems() throws InterruptedException {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testGroceryAddItems");
			extentTest.log(Status.SKIP, "test skipped from testGroceryAddItems");
			throw new SkipException("test skipped due to execution requirement");

		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testGroceryAddItems");
		extentTest = extentReports.createTest(methodName, "Test to add items on cart");
		GroceryPage groceryPage = new GroceryPage(driver);

		try {
			groceryPage.clickOnGrocery();

			String pincode = getTestData("testGroceryValidPincode");
			groceryPage.enterPincode(pincode);
			Assert.assertTrue(groceryPage.isDeliverable(pincode));

			Assert.assertTrue(groceryPage.isGrocerySearchBarPresent(),
					"Search bar is not present on the grocery page!");
			LoggerUtils.logInfo("test passed for testGroceryAddItems");
			extentTest.pass("Search bar is present on the grocery page");

			String searchData = getTestData(methodName);
			groceryPage.searchForGrocery(searchData);
			groceryPage.addItemsInCart();
			
			groceryPage.navigateToCart();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			
		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testGroceryAddItems");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 5, groups = { "grocery" }, enabled = false)
	public void testGroceryRemoveItems() throws InterruptedException {

		if (driver == null) {
			LoggerUtils.logInfo("test skipped for testGroceryRemoveItems");
			extentTest.log(Status.SKIP, "test skipped from testGroceryRemoveItems");
			throw new SkipException("test skipped due to execution requirement");

		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testGroceryRemoveItems");
		extentTest = extentReports.createTest(methodName, "Test to add items on cart");
		GroceryPage groceryPage = new GroceryPage(driver);

		try {
			groceryPage.clickOnGrocery();

			String pincode = getTestData("testGroceryValidPincode");
			groceryPage.enterPincode(pincode);
			Assert.assertTrue(groceryPage.isDeliverable(pincode));

			Assert.assertTrue(groceryPage.isGrocerySearchBarPresent(),
					"Search bar is not present on the grocery page!");
			extentTest.pass("Search bar is present on the grocery page");

			String searchData = getTestData(methodName);
			groceryPage.searchForGrocery(searchData);
			groceryPage.addItemsInCart();
			groceryPage.navigateToCart();

			groceryPage.removeItem();

			Assert.assertTrue(groceryPage.isCartEmpty());
			LoggerUtils.logInfo("test passed for testGroceryRemoveItems");
			extentTest.log(Status.PASS, "item has been successfully removed from the cart");

		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testGroceryRemoveItems");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}

}