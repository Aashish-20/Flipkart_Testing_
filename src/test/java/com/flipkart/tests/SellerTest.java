package com.flipkart.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.flipkart.base.BaseTest;
import com.flipkart.pages.SellerPage;
import com.flipkart.utils.LoggerUtils;

public class SellerTest extends BaseTest {

	@Test(priority = 1, groups = {"seller"}, enabled = true)
	public void testSellerBtnPresence() {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testSellerBtnPresence");
			extentTest.log(Status.SKIP, "test skipped from hometest");
			throw new SkipException("test skipped due to execution requirement");

		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testSellerBtnPresence");
		extentTest = extentReports.createTest(methodName, "Test to check the presence of the seller button");
		SellerPage sellerPage = new SellerPage(driver);

		try {
			Assert.assertTrue(sellerPage.isSellerBtnPresent(), "seller button is not present on the home page!");
			LoggerUtils.logInfo("test passed for testSellerBtnPresence");
			extentTest.pass("seller button is present on the home page");

		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testSellerBtnPresence");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 2, groups = {"seller"}, enabled = true)
	public void testSellerLoginFunctionality() {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testSellerLoginFunctionality");
			extentTest.log(Status.SKIP, "test skipped from testSellerLoginFunctionality");
			throw new SkipException("test skipped due to execution requirement");

		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testSellerLoginFunctionality");
		extentTest = extentReports.createTest(methodName, "Test to check login on invalid crededntials");
		SellerPage sellerPage = new SellerPage(driver);

		String usernameAndPasswordData = getTestData(methodName);

		try {
			sellerPage.loginSeller(usernameAndPasswordData);
			Assert.assertTrue(sellerPage.isCredentialsValid());
			LoggerUtils.logInfo("test passed for testSellerLoginFunctionality");
			extentTest.pass("Invalid credentials. Can't login");

		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testSellerLoginFunctionality");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 3, groups = {"seller"}, enabled = true)
	public void testSellerResetPasswordFunctionality() {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testSellerResetPasswordFunctionality");
			extentTest.log(Status.SKIP, "test skipped from testSellerResetPasswordFunctionality");
			throw new SkipException("test skipped due to execution requirement");

		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testSellerResetPasswordFunctionality");
		extentTest = extentReports.createTest(methodName, "Test to check reset password");
		SellerPage sellerPage = new SellerPage(driver);

		String usernameAndPasswordData = getTestData("testSellerLoginFunctionality");
		String resetPasswordEmailData = getTestData(methodName);

		try {
			sellerPage.resetPasswordSeller(usernameAndPasswordData, resetPasswordEmailData);
			Assert.assertTrue(sellerPage.isSuccessMsgDisplayed());
			LoggerUtils.logInfo("test passed for testSellerResetPasswordFunctionality");
			extentTest.pass("Password sent to the registered email.");

		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testSellerResetPasswordFunctionality");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 4, groups = {"seller"}, enabled = true)
	public void testStartSellingBtnPresence() {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testStartSellingBtnPresence");
			extentTest.log(Status.SKIP, "test skipped from testStartSellingBtnPresence");
			throw new SkipException("test skipped due to execution requirement");

		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testStartSellingBtnPresence");
		extentTest = extentReports.createTest(methodName, "Test to check the presence of the start selling button");
		SellerPage sellerPage = new SellerPage(driver);

		try {
			sellerPage.sellerBtnClick();
			Assert.assertTrue(sellerPage.isStartSellingBtnPresent(),
					"start selling button is not present on the seller page!");
			LoggerUtils.logInfo("test passed for testStartSellingBtnPresence");
			extentTest.pass("start selling button is present on the seller page");

		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testStartSellingBtnPresence");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}
	
	

}
