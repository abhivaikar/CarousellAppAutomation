package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class CameraPage {

	private AppiumDriver<WebElement> driver;
	
    By cameraFlashButtonBy = By.id("com.thecarousell.Carousell:id/button_camera_flash");
    By cameraSwitchButtonBy = By.id("com.thecarousell.Carousell:id/button_camera_switch");
    By cameraGridButtonBy = By.id("com.thecarousell.Carousell:id/button_camera_grid");
    By captureButtonBy = By.id("com.thecarousell.Carousell:id/button_capture");
	
	public CameraPage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
	}

	public void capturePhoto() {
      driver.findElement(cameraSwitchButtonBy).click();
	  driver.findElement(captureButtonBy).click();
	}

	
}
