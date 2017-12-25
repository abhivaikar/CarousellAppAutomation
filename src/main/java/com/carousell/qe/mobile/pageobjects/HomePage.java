package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;

import com.carousell.qe.mobile.base.BasePage;

/**
 * Page Object for Home Page that contains the following components:
 * a. Header ()
 * b. Tab Bar
 * c. Tab pages
 * @author Abhijeet
 */
public class HomePage extends BasePage {
	
	By getGooglePlayServicesPopupBy = By.xpath("//android.widget.TextView[@text='Get Google Play Service']");
	By inboxButtonBy = By.id("com.thecarousell.Carousell:id/action_inbox");
	By findInviteFriendsButtonBy = By.id("com.thecarousell.Carousell:id/action_social");
	By itemSearchButtonBy = By.id("com.thecarousell.Carousell:id/action_search");
	By startSellingButtonBy = By.id("com.thecarousell.Carousell:id/action_sell");
    By picFromCameraBy = By.id("com.thecarousell.Carousell:id/pic_camera");
	
    By browseTabBy = By.xpath("//android.widget.TextView[@text='BROWSE']");

	public HomePage() {
		
		//App run on Genymotion emulators shows a popup with message "Get Google Play Service" as Google Play Services are not set on Genymotion
			 try {
				  if(isDisplayed(getGooglePlayServicesPopupBy))
					  pressBackButton();
			} catch (Exception e) {
				//Do Nothing
			}
	}
	
	
	public void startSellingWithPhotoFromCamera()
	{
		click(startSellingButtonBy);
		click(picFromCameraBy);
	}


	public BrowseTab getBrowseTab() {
		click(browseTabBy);
		return new BrowseTab();
	}

}
