package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class CameraPhotoPreviewEditPage {
	
	private AppiumDriver<WebElement> driver;
	
    By moveForwardButton = By.id("com.thecarousell.Carousell:id/button_filter_forward");
	
    public CameraPhotoPreviewEditPage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
	}

	public void moveForward() {
		driver.findElement(moveForwardButton).click();
	}

}
