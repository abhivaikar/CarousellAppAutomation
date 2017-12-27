package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;

import com.carousell.qe.mobile.base.BasePage;
import com.carousell.qe.mobile.enums.DealType;

public class DealDetailsInputPage extends BasePage {

	
	By submitDetailsButtonBy = By.id("com.thecarousell.Carousell:id/action_submit");
	By expandOrCollapseMeetupCheckedButtonBy = By.id("com.thecarousell.Carousell:id/option_meetup");
	By meetupDetailsTextBy = By.id("com.thecarousell.Carousell:id/text_meetup_detail");
	By viewLocationsButtonBy = By.id("com.thecarousell.Carousell:id/text_location");
	By dealLocationListBy = By.xpath("//android.widget.ListView[@resource-id='com.thecarousell.Carousell:id/list_venues']/android.widget.LinearLayout");
	
	public DealDetailsInputPage setDealDetails(DealType dealType, String optionalDetails) {
		
		if(dealType == DealType.MEETUP)
			setMeetupDetails(optionalDetails);
		//else mailing or delivery
		
		return this;
		//'return this' lets us call setDetails again in case if we want to set deal for delivery too in a chained fashion
	}

	private void setMeetupDetails(String optionalDetails) {

		 if(findElement(expandOrCollapseMeetupCheckedButtonBy).getAttribute("checked").equals("false"))
		  {
			  click(expandOrCollapseMeetupCheckedButtonBy);
			  click(viewLocationsButtonBy);
			  findElements(dealLocationListBy).get(0).click();
			  type(meetupDetailsTextBy,optionalDetails);
		  }
	}

	public void confirm() {
		click(submitDetailsButtonBy);
	}

}
