package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;

import com.carousell.qe.mobile.base.BasePage;

public class CameraPhotoPreviewEditPage extends BasePage {
	
	
    By moveForwardButton = By.id("com.thecarousell.Carousell:id/button_filter_forward");


	public void moveForward() {
		click(moveForwardButton);
	}

}
