package com.carousell.qe.mobile.base;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 *
 * Base Test class as a parent class for all test classes.
 */
public class BaseTest {

	protected static AppiumDriver<WebElement> driver;
	private DesiredCapabilities capabilities;
	private String userDir;
	private String localApp;
    private String appPath;
	private URL appiumServerAddress;
	  
	  @BeforeTest
	  public void setUp(ITestContext testContext) throws Exception {
		  initDriver(testContext.getCurrentXmlTest().getLocalParameters());
	  }
	  
	  @AfterTest
	  public void tearDown()
	  {
		  if(driver!=null)
			  driver.quit();
	  }
	  
	  /**
	   * Helper method to initialize Appium driver with provided configuration parameters
	   * @param localTestParameters
	   * @throws Exception
	   */
	  private void initDriver(Map<String, String> localTestParameters) throws Exception {
		  
		  try {
			  userDir = System.getProperty("user.dir");
			  localApp = localTestParameters.get("app");
			  appPath = Paths.get(userDir, localApp).toAbsolutePath().toString();
			  appiumServerAddress = new URL(localTestParameters.get("appiumServerAddress"));
			  
			  capabilities = new DesiredCapabilities();
			  
			  capabilities.setCapability("deviceName",localTestParameters.get("deviceName"));
		      capabilities.setCapability("platformName",localTestParameters.get("platformName"));
		      capabilities.setCapability("app", appPath);
		      capabilities.setCapability("appPackage", localTestParameters.get("appPackage"));
		      capabilities.setCapability("appWaitActivity", localTestParameters.get("appWaitActivity"));
		      capabilities.setCapability("fullReset", Boolean.valueOf(localTestParameters.get("fullReset")));
		      capabilities.setCapability("newCommandTimeout", localTestParameters.get("newCommandTimeOut"));
		      capabilities.setCapability("autoGrantPermissions", Boolean.valueOf(localTestParameters.get("autoGrantPermissions")));
		      capabilities.setCapability("clearSystemFiles", Boolean.valueOf(localTestParameters.get("clearSystemFiles")));
		      
		      driver = new AndroidDriver<WebElement>(appiumServerAddress, capabilities);
		      driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new Exception("Could not initialize Appium driver. Cause: " + e.getStackTrace());
		}
		  
	}

	
}
