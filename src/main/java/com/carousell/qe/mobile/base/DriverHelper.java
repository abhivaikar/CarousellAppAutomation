package com.carousell.qe.mobile.base;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

/**
 * Helper class to isolate Appium driver initialization and killing operations.
 * Makes it easier to get instances of driver in both BaseTest and BasePage classes.
 * @author Abhijeet
 */
public class DriverHelper {

    private static AndroidDriver<WebElement> driver;
	
	public static void initDriver(Map<String, String> localTestParameters) throws Exception {
		 try {
			  String userDir = System.getProperty("user.dir");
			  String localApp = localTestParameters.get("app");
			  String appPath = Paths.get(userDir, localApp).toAbsolutePath().toString();
			  URL appiumServerAddress = new URL(localTestParameters.get("appiumServerAddress"));
			  
			  DesiredCapabilities capabilities = new DesiredCapabilities();
			  
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

	public static AndroidDriver<WebElement> getDriver() {
		return driver;
	}

	public static void stopDriver() {
		if(driver!=null)
			driver.quit();
	}

}
