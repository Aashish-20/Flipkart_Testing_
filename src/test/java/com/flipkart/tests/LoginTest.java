package com.flipkart.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.flipkart.base.BaseTest;
import com.flipkart.pages.LoginPage;
import com.flipkart.utils.LoggerUtils;

public class LoginTest extends BaseTest{
	
	@Test(priority = 1, groups = {"login"}, enabled = true)
    public void testInvalidLoginFunctionality() {
    
    	if(driver == null) {
    		LoggerUtils.logSkip("test skipped for testInvalidLoginFunctionality");
    		extentTest.log(Status.SKIP, "test skipped from hometest");
    		throw new SkipException("test skipped due to execution requirement");
 
    	}
    	String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
    	System.out.println(methodName+"logintest");
    	LoggerUtils.logInfo("test started for testInvalidLoginFunctionality");
    	extentTest = extentReports.createTest(methodName, "Test to verify the invalid login"); 
        LoginPage loginPage = new LoginPage(driver);
        
        try {
        	loginPage.clickLogin();
        	String searchData = getTestData(methodName);
        	loginPage.invalidLogin(searchData);
        	
        	Assert.assertTrue(loginPage.erroMsgDisplayed());
        	LoggerUtils.logInfo("test passed for testInvalidLoginFunctionality");
            extentTest.log(Status.PASS, "invalid credentials");
		} catch (AssertionError e) {
			LoggerUtils.logError("test failed for testInvalidLoginFunctionality");
	        extentTest.log(Status.FAIL, "Assertion failed: " + e.getMessage());
	        throw e;
		}
    }
}
