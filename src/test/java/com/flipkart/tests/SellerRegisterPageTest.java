package com.flipkart.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.flipkart.base.BaseTest;
import com.flipkart.pages.SellerPage;
import com.flipkart.pages.SellerRegisterPage;
import com.flipkart.utils.LoggerUtils;

public class SellerRegisterPageTest extends BaseTest {

	@Test(priority = 1, groups = { "sellerRegister" }, enabled = true)
	public void testInvalidOtpFunctionality() {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testInvalidOtpFunctionality");
			extentTest.log(Status.SKIP, "test skipped from testInvalidOtpFunctionality");
			throw new SkipException("test skipped due to execution requirement");

		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testInvalidOtpFunctionality");
		extentTest = extentReports.createTest(methodName, "Test to check Registration with invalid otp");
		SellerPage sellerPage = new SellerPage(driver);

		SellerRegisterPage sellerRegisterPage = new SellerRegisterPage(driver);

		String testData = getTestData(methodName);

		try {
			sellerPage.sellerBtnClick();
			sellerRegisterPage.registerForSelling(testData);
			Assert.assertTrue(sellerRegisterPage.isInvalidOtpMsgDisplayed());
			LoggerUtils.logInfo("test passed for testInvalidOtpFunctionality");
			extentTest.pass("Invalid otp registration was unsuccessful.");

		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testInvalidOtpFunctionality");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}
}
