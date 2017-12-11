package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class WelcomePage {

	private AppiumDriver<WebElement> driver;
	
	By emailSignInButtonBy = By.id("com.thecarousell.Carousell:id/email_signin_button");
	
	public WelcomePage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
		
	}

	public void beginSignUpOrLoginWithEmail() {
		driver.findElement(emailSignInButtonBy).click();		
	}

}
