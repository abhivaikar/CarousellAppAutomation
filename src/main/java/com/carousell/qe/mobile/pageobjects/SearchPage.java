package com.carousell.qe.mobile.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.carousell.qe.mobile.enums.SearchResultSort;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

public class SearchPage {

	private AppiumDriver<WebElement> driver;
	
	By currentSelectedLocationPopupBy = By.xpath("//android.widget.TextView[@text='OK, Got it!']");
	By filterBarOptionBy = By.id("com.thecarousell.Carousell:id/bar_filter");
	By sortByRecentOptionBy = By.id("com.thecarousell.Carousell:id/text_sort_recent");
	By applyFilterButton = By.id("com.thecarousell.Carousell:id/button_apply_filter");
	
	public SearchPage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
		
		try {
			driver.findElement(currentSelectedLocationPopupBy).click();
		} catch (Exception e) {
			//Do Nothing
		}
	}

	public void sortBy(SearchResultSort sortBy) {
		driver.findElement(filterBarOptionBy).click();
		 
		if(sortBy == SearchResultSort.RECENT)
		   driver.findElement(sortByRecentOptionBy).click();
		  
		driver.findElement(applyFilterButton).click();
	}

	public boolean isItemVisibleInSearchResults(String itemTitle) {
		boolean found=false;
		WebElement itemToFind = null;
		int count=0;
		
		
		do
		{
			try {
				itemToFind = driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.thecarousell.Carousell:id/view_product']/android.widget.TextView[@text='"+itemTitle+"']"));
				
				found=true;
			} catch (Exception e) {
				WebElement itemTileToScrollFrom = driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.thecarousell.Carousell:id/view_product'][4]"));
				WebElement itemTileToScrollTo = driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.thecarousell.Carousell:id/view_product'][2]"));
				new TouchAction(driver).press(itemTileToScrollFrom).waitAction(Duration.ofSeconds(1)).moveTo(itemTileToScrollTo).release().perform();
				count++;
		}
		}
		while(found==false && count<10);
		//Keeping a count since the search results view is an infinite scroll view. We cannot just keep on going on
	
	return found;
	}

}
