package com.carousell.qe.mobile.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.google.common.io.Files;

import io.appium.java_client.android.AndroidDriver;

public class ScreenshotUtil {

	public static void captureScreenshot(String currentSuiteScreenshotsDirectoryPath) {
	
		try {
			AndroidDriver<WebElement> driver = DriverHelper.getDriver();
			File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			String fileName = "screenshot-" + new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date()) + ".png";
			Files.copy(screenshotFile, new File(currentSuiteScreenshotsDirectoryPath + "\\" + fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
