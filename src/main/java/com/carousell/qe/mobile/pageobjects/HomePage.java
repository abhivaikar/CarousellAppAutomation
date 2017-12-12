package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

/**
 * Page Object for Home Page that contains the following components:
 * a. Header ()
 * b. Tab Bar
 * c. Tab pages
 * @author Abhijeet
 */
public class HomePage {

	private AppiumDriver<WebElement> driver;
	
	By getGooglePlayServicesPopupBy = By.xpath("//android.widget.TextView[@text='Get Google Play Service']");
	By inboxButtonBy = By.id("com.thecarousell.Carousell:id/action_inbox");
	By findInviteFriendsButtonBy = By.id("com.thecarousell.Carousell:id/action_social");
	By itemSearchButtonBy = By.id("com.thecarousell.Carousell:id/action_search");
	By startSellingButtonBy = By.id("com.thecarousell.Carousell:id/action_sell");
    By picFromCameraBy = By.id("com.thecarousell.Carousell:id/pic_camera");
	
    By browseTabBy = By.xpath("//android.widget.TextView[@text='BROWSE']");

	public HomePage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
		
		//App run on Genymotion emulators shows a popup with message "Get Google Play Service" as Google Play Services are not set on Genymotion
			 try {
				  if(driver.findElement(getGooglePlayServicesPopupBy).isDisplayed())
					  driver.navigate().back();
			} catch (Exception e) {
			}
	}
	
	
	public void startSellingWithPhotoFromCamera()
	{
		driver.findElement(startSellingButtonBy).click();
		driver.findElement(picFromCameraBy).click();
	}


	public BrowseTab getBrowseTab() {
		driver.findElement(browseTabBy).click();
		return new BrowseTab(driver);
	}

}
