package com.flipkart.base;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.flipkart.config.Config;
import com.flipkart.utils.ExcelUtils;
import com.flipkart.utils.LoggerUtils;
import com.flipkart.utils.ScreenshotUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;
	Config config;

	protected boolean shouldInitializeDriver;
	protected ExtentSparkReporter extentSparkReporter;
	protected static ExtentReports extentReports;
	protected ExtentTest extentTest;
	private static Map<String, Boolean> executionMap;
	private static Map<String, String> testDataMap;

	@BeforeSuite(alwaysRun = true)
	public void beforesuite() {

		if (extentReports == null) {

			extentSparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
			extentReports = new ExtentReports();
			extentReports.attachReporter(extentSparkReporter);

			ExcelUtils excel = new ExcelUtils("src/test/resources/TestData.xlsx", "Sheet1");
			executionMap = excel.getExecutionMap();
			testDataMap = excel.getTestDataMap();
		}
	}

	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun = true)
	public void setup(Method method) {

		String methodName = method.getName();

		if (extentReports == null) {
			extentReports = new ExtentReports();
			extentReports.attachReporter(new ExtentSparkReporter("test-output/ExtentReport.html"));
		}

		extentTest = extentReports.createTest(methodName);

		if (executionMap == null || !executionMap.getOrDefault(methodName, false)) {
			System.out.println("skipping test :" + methodName);
			extentTest.log(Status.SKIP, "test skipped");
			throw new SkipException("test skipped due to execution requirement");
		}

		config = new Config();
		String browser = config.getProperty("browser");
		boolean headless = Boolean.parseBoolean(config.getProperty("headless"));

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			if (headless) {
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("--window-size=1920,1080");
			}
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (headless) {
				firefoxOptions.addArguments("--headless");
				firefoxOptions.addArguments("--disable-gpu");
				firefoxOptions.addArguments("--window-size=1920,1080");
			}
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(config.getProperty("url"));

		LoggerUtils.logInfo("Browser setup completed and navigated to URL");
	}

	@AfterMethod(alwaysRun = true)
	public void teardown(ITestResult result) {
		if (extentTest != null) {

			if (result.getStatus() == ITestResult.FAILURE) {
				String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getName());
				extentTest.fail(result.getThrowable());
				extentTest.addScreenCaptureFromPath(screenshotPath);
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				extentTest.pass("Test passed");
			} else if (result.getStatus() == ITestResult.SKIP) {
				extentTest.skip("Test skipped");
			}
		}
		if (driver != null) {
			driver.quit();
			LoggerUtils.logInfo("Browser closed");
		}

		extentReports.flush();
	}

	protected String getTestData(String testName) {
		return testDataMap.getOrDefault(testName, "");
	}

}