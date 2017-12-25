package com.carousell.qe.mobile.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.carousell.qe.mobile.base.BasePage;
import com.carousell.qe.mobile.enums.SearchResultSort;

import io.appium.java_client.TouchAction;

public class SearchPage extends BasePage {
	
	By currentSelectedLocationPopupBy = By.xpath("//android.widget.TextView[@text='OK, Got it!']");
	By filterBarOptionBy = By.id("com.thecarousell.Carousell:id/bar_filter");
	By sortByRecentOptionBy = By.id("com.thecarousell.Carousell:id/text_sort_recent");
	By applyFilterButton = By.id("com.thecarousell.Carousell:id/button_apply_filter");
	
	public SearchPage() {
	
		try {
		    click(currentSelectedLocationPopupBy);
		} catch (Exception e) {
			//Do Nothing
		}
	}

	public void sortBy(SearchResultSort sortBy) {
		click(filterBarOptionBy);
		 
		if(sortBy == SearchResultSort.RECENT)
		   click(sortByRecentOptionBy);
		  
		click(applyFilterButton);
	}

	public boolean isItemVisibleInSearchResults(String itemTitle) {
		boolean found=false;
		WebElement itemToFind = null;
		int count=0;
		
		
		do
		{
			try {
				itemToFind = findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.thecarousell.Carousell:id/view_product']/android.widget.TextView[@text='"+itemTitle+"']"));
				found=true;
			} catch (Exception e) {
				WebElement itemTileToScrollFrom = findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.thecarousell.Carousell:id/view_product'][4]"));
				WebElement itemTileToScrollTo = findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.thecarousell.Carousell:id/view_product'][2]"));
				new TouchAction(driver).press(itemTileToScrollFrom).waitAction(Duration.ofSeconds(1)).moveTo(itemTileToScrollTo).release().perform();
				count++;
		}
		}
		while(found==false && count<10);
		//Keeping a count since the search results view is an infinite scroll view. We cannot just keep on going on
	
	return found;
	}

}
