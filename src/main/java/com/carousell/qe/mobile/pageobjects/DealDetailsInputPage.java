package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.carousell.qe.mobile.enums.DealType;

import io.appium.java_client.AppiumDriver;

public class DealDetailsInputPage {

	private AppiumDriver<WebElement> driver;
	
	By submitDetailsButtonBy = By.id("com.thecarousell.Carousell:id/action_submit");
	By expandOrCollapseMeetupCheckedButtonBy = By.id("com.thecarousell.Carousell:id/option_meetup");
	By meetupDetailsTextBy = By.id("com.thecarousell.Carousell:id/text_meetup_detail");
	By viewLocationsButtonBy = By.id("com.thecarousell.Carousell:id/text_location");
	By dealLocationListBy = By.xpath("//android.widget.ListView[@resource-id='com.thecarousell.Carousell:id/list_venues']/android.widget.LinearLayout");
	
	public DealDetailsInputPage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
	}

	public DealDetailsInputPage setDealDetails(DealType dealType, String optionalDetails) {
		
		if(dealType == DealType.MEETUP)
			setMeetupDetails(optionalDetails);
		//else mailing or delivery
		
		return this;
		//'return this' lets us call setDetails again in case if we want to set deal for delivery too in a chained fashion
	}

	private void setMeetupDetails(String optionalDetails) {

		 if(driver.findElement(expandOrCollapseMeetupCheckedButtonBy).getAttribute("checked").equals("false"))
		  {
			  driver.findElement(expandOrCollapseMeetupCheckedButtonBy).click();
			  driver.findElement(viewLocationsButtonBy).click();
			  driver.findElements(dealLocationListBy).get(0).click();
			  driver.findElement(meetupDetailsTextBy).sendKeys(optionalDetails);
		  }
	}

	public void confirm() {
		driver.findElement(submitDetailsButtonBy).click();
	}

}
