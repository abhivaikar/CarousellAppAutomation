package com.carousell.qe.mobile.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.AppiumDriver;

/**
 * Base Test class as a parent class for all test classes.
 */
public class BaseTest {

	protected static AppiumDriver<WebElement> driver;
	public static String currentSuiteScreenshotsDirectoryPath;
	
	 @BeforeSuite
	  public void setUpBeforeSuite(ITestContext testContext) {
		  String userDir = System.getProperty("user.dir");
		  String screenshotsDirPath = userDir + "\\screenshots";
		  File screenshotsDirectory = new File(screenshotsDirPath);
		  
		  if(!screenshotsDirectory.exists())
			  screenshotsDirectory.mkdir();
		  
		  String testSuiteName = testContext.getSuite().getName();
		  String timeStamp = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
		  currentSuiteScreenshotsDirectoryPath = screenshotsDirPath + "\\" + testSuiteName + "-" + timeStamp;
		  
		  File currentSuiteScreenshotsDirectory = new File(currentSuiteScreenshotsDirectoryPath);
		  currentSuiteScreenshotsDirectory.mkdir();
	  }
	  
	  @BeforeMethod
	  public void setUp(ITestContext testContext) throws Exception {
		  DriverHelper.initDriver(testContext.getCurrentXmlTest().getLocalParameters());
		  driver = DriverHelper.getDriver();
	  }
	  
	  @AfterMethod
	  public void tearDown()
	  {
		  DriverHelper.stopDriver();
	  }

	  protected void grabScreenshot()
	  {
		  ScreenshotUtil.captureScreenshot(currentSuiteScreenshotsDirectoryPath);
	  }
}
