package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class HomePage {

	private AppiumDriver<WebElement> driver;
	
	By getGooglePlayServicesPopupBy = By.xpath("//android.widget.TextView[@text='Get Google Play Service']");
	By inboxButtonBy = By.id("com.thecarousell.Carousell:id/action_inbox");
	By findInviteFriendsButtonBy = By.id("com.thecarousell.Carousell:id/action_social");
	By itemSearchButtonBy = By.id("com.thecarousell.Carousell:id/action_search");
	By startSellingButtonBy = By.id("com.thecarousell.Carousell:id/action_sell");
	
	By picFromCameraBy = By.id("com.thecarousell.Carousell:id/pic_camera");
	
	public HomePage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
	}
	
	public HomePage(AppiumDriver<WebElement> driver, boolean isInitializedFromLoginPage) {
		this.driver = driver;
		
		if(isInitializedFromLoginPage)
		{
			 try {
				  if(driver.findElement(getGooglePlayServicesPopupBy).isDisplayed())
					  driver.navigate().back();
			} catch (Exception e) {
			}
		}
	}
	
	
	public void startSellingWithPhotoFromCamera()
	{
		driver.findElement(startSellingButtonBy).click();
		driver.findElement(picFromCameraBy).click();
	}

}
