package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class AuthPage {

	private AppiumDriver<WebElement> driver;
	
    By loginTabBy = By.xpath("//android.widget.TextView[@text='LOGIN']");
    By userNameTextFieldBy = By.id("com.thecarousell.Carousell:id/text_username");
	By passwordTextFieldBy = By.id("com.thecarousell.Carousell:id/text_password");
	By loginButtonBy = By.id("com.thecarousell.Carousell:id/action_signin");
	
	public AuthPage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
	}

	public void login(String userName, String password) {
		driver.findElement(loginTabBy).click();
		driver.findElement(userNameTextFieldBy).sendKeys(userName);
		driver.findElement(passwordTextFieldBy).sendKeys(password);
		driver.findElement(loginButtonBy).click();
	}

}
