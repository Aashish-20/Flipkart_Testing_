package com.flipkart.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.flipkart.base.BaseTest;
import com.flipkart.pages.HelpPage;
import com.flipkart.utils.LoggerUtils;

public class HelpPageTest extends BaseTest {

	@Test(priority = 1, groups = { "help" }, enabled = true)
	public void testHelpPageFeedbackYesBtnFunctionality() {

		if (driver == null) {

			LoggerUtils.logSkip("test skipped for testHelpPageFeedbackYesBtnFunctionality");
			extentTest.log(Status.SKIP, "test skipped from testHelpPageFeedbackYesBtnFunctionality");
			throw new SkipException("test skipped due to execution requirement");

		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testHelpPageFeedbackYesBtnFunctionality");
		extentTest = extentReports.createTest(methodName, "Test to check feedback");
		HelpPage helpPage = new HelpPage(driver);

		try {
			helpPage.customerCare1();
			Assert.assertTrue(helpPage.isFeedBackDisplayed());
			LoggerUtils.logInfo("test passed for testHelpPageFeedbackYesBtnFunctionality");
			extentTest.pass("Feedback displayed.");

		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testHelpPageFeedbackYesBtnFunctionality");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 2, groups = { "help" }, enabled = true)
	public void testHelpPageFeedbackNoBtnFunctionality() {

		if (driver == null) {
			LoggerUtils.logSkip("test skipped for testHelpPageFeedbackNoBtnFunctionality");
			extentTest.log(Status.SKIP, "test skipped from testHelpPageFeedbackNoBtnFunctionality");
			throw new SkipException("test skipped due to execution requirement");

		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		LoggerUtils.logInfo("test started for testHelpPageFeedbackNoBtnFunctionality");
		extentTest = extentReports.createTest(methodName, "Test to check feedback");
		HelpPage helpPage = new HelpPage(driver);

		try {
			helpPage.customerCare2();
			Assert.assertTrue(helpPage.isFeedBackDisplayed());
			LoggerUtils.logInfo("test passed for testHelpPageFeedbackNoBtnFunctionality");
			extentTest.pass("Feedback displayed.");

		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testHelpPageFeedbackNoBtnFunctionality");
			extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			throw e;
		}

	}
}
