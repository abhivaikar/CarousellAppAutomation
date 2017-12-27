package com.carousell.qe.mobile.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class BasePage {

	protected static AndroidDriver<WebElement> driver;
	
	public BasePage()
	{
		driver = DriverHelper.getDriver();
	}
	
	protected void type(By by, String textToEnter) {
		driver.findElement(by).sendKeys(textToEnter);
	}
	
	protected void click(By by)
	{
		driver.findElement(by).click();
	}
	
	protected boolean isDisplayed(By by)
	{
		return driver.findElement(by).isDisplayed();
	}
	
	protected void pressBackButton()
	{
		driver.navigate().back();
	}
	
	protected WebElement findElement(By by)
	{
       return driver.findElement(by);
	}
	
	protected List<WebElement> findElements(By by)
	{
		return driver.findElements(by);
	}
}
