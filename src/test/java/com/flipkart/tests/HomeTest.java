package com.flipkart.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.flipkart.base.BaseTest;
import com.flipkart.pages.HomePage;
import com.flipkart.utils.LoggerUtils;

public class HomeTest extends BaseTest {

	@Test(priority = 1, groups = { "home" }, enabled = true)
	public void testFlipkartLogoVisibility() {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testFlipkartLogoVisibility");
			extentTest.log(Status.SKIP, "test skipped from testFlipkartLogoVisibility");
			throw new SkipException("test skipped due to execution requirement");

		}
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testFlipkartLogoVisibility");
		extentTest = extentReports.createTest(methodName,
				"Test to verify the visibility of flipkart logo on the home page");
		HomePage homePage = new HomePage(driver);

		try {
			Assert.assertTrue(homePage.isFlipkartLogoVisible(), "Flipkart logo is not visible on the home page!");
			LoggerUtils.logInfo("test passed for testFlipkartLogoVisibility");
			extentTest.log(Status.PASS, "flipkart logo is visible on the home page");
		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testFlipkartLogoVisibility");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 2, groups = { "home" }, enabled = true)
	public void testSearchBarFunctionality() {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testSearchBarFunctionality");
			extentTest.log(Status.SKIP, "test skipped from testSearchBarFunctionality");
			throw new SkipException("test skipped due to execution requirement");

		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		LoggerUtils.logInfo("test started for testSearchBarFunctionality");
		extentTest = extentReports.createTest(methodName,
				"Test to check the presence and functionality of the search bar");
		HomePage homePage = new HomePage(driver);

		try {
			Assert.assertTrue(homePage.isSearchBarPresent(), "Search bar is not present on the home page!");
			LoggerUtils.logInfo("test passed for testSearchBarFunctionality");
			extentTest.pass("Search bar is present on the home page");

			String searchData = getTestData(methodName);
			homePage.searchForProduct(searchData);
		} catch (AssertionError e) {
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}

	// Failing this test_case deliberately.
	@Test(priority = 3, groups = { "home" }, enabled = true)
	public void testSearchWithMisspelledQueries() {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testSearchWithMisspelledQueries");
			extentTest.log(Status.SKIP, "test skipped from testSearchWithMisspelledQueries");
			throw new SkipException("test skipped due to execution requirement");

		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testSearchWithMisspelledQueries");
		extentTest = extentReports.createTest(methodName, "Test to check search with misspelled queries");
		HomePage homePage = new HomePage(driver);

		try {
			String misspelledData = getTestData(methodName);
			homePage.searchForProduct(misspelledData);
			Assert.assertFalse(homePage.isFirstProductDisplayed(),
					"first product is not displayed " + "for the search query" + misspelledData);
			LoggerUtils.logInfo("test passed for testSearchWithMisspelledQueries");
			extentTest.log(Status.PASS, "search with misspelled queries works correctly");
		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testSearchWithMisspelledQueries");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}
	}
}