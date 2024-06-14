package com.flipkart.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.flipkart.base.BaseTest;
import com.flipkart.pages.AdvertisePage;
import com.flipkart.utils.LoggerUtils;

public class AdvertisePageTest extends BaseTest{

	
	@Test(priority = 1, groups = {"advertise"}, enabled = true)
	public void testInvalidLoginFunctionalityonAdsPage() {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped from advertise page test");
			extentTest.log(Status.SKIP, "test skipped from advertise page test");
			throw new SkipException("test skipped due to execution requirement");

		}

		
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		LoggerUtils.logInfo("test started for "+methodName);
		extentTest = extentReports.createTest(methodName, "Test to check Invalid login attempt in advertisement page");
		AdvertisePage advertisePage = new AdvertisePage(driver);

		
		
		String testData = getTestData(methodName);

		try {
			advertisePage.loginAdvertiseAccount(testData);
			Assert.assertTrue(advertisePage.isWarnMessageDisplayed());
			LoggerUtils.logInfo("test passed for "+methodName);
			extentTest.pass("Invalid login attempt was unsuccessful.");

		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for "+methodName);
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}
	
	@Test(priority = 2, groups = {"advertise"}, enabled = true)
	public void testAdsResetPasswordFunctionality() {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped from advertise page test");
			extentTest.log(Status.SKIP, "test skipped from advertise page test");
			throw new SkipException("test skipped due to execution requirement");

		}

		
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		LoggerUtils.logInfo("test started for "+methodName);
		extentTest = extentReports.createTest(methodName, "Test to reset password in advertisement page");
		AdvertisePage advertisePage = new AdvertisePage(driver);

		
		
		String testData = getTestData(methodName);

		try {
			advertisePage.forgotPassword(testData);
			Assert.assertTrue(advertisePage.isResetPasswordPageDisplayed());
			LoggerUtils.logInfo("test passed for "+methodName);
			extentTest.pass("Invalid login attempt was unsuccessful.");

		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for "+methodName);
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}
	
	@Test(priority = 3, groups = {"advertise"}, enabled = true)
	public void testAdsEmptyResetPasswordFunctionality() {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped from advertise page test");
			extentTest.log(Status.SKIP, "test skipped from advertise page test");
			throw new SkipException("test skipped due to execution requirement");

		}

		
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		LoggerUtils.logInfo("test started for "+methodName);
		extentTest = extentReports.createTest(methodName, "Test to reset password in advertisement page");
		AdvertisePage advertisePage = new AdvertisePage(driver);

				

		try {
			advertisePage.forgotPassword();
			Assert.assertTrue(advertisePage.isWarningMessageDispalyedForEmptyField());
			LoggerUtils.logInfo("test passed for "+methodName);
			extentTest.pass("Invalid login attempt was unsuccessful.");

		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for "+methodName);
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}
}
