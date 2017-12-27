package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;

import com.carousell.qe.mobile.base.BasePage;

public class CameraPage extends BasePage {

	
    By cameraFlashButtonBy = By.id("com.thecarousell.Carousell:id/button_camera_flash");
    By cameraSwitchButtonBy = By.id("com.thecarousell.Carousell:id/button_camera_switch");
    By cameraGridButtonBy = By.id("com.thecarousell.Carousell:id/button_camera_grid");
    By captureButtonBy = By.id("com.thecarousell.Carousell:id/button_capture");
	
	public void capturePhoto() {
     click(cameraSwitchButtonBy);
	 click(captureButtonBy);
	}

	
}
