package com.carousell.qe.mobile.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.carousell.qe.mobile.base.BasePage;
import com.carousell.qe.mobile.enums.Category;

import io.appium.java_client.TouchAction;

public class BrowseTab extends BasePage {

	public void scrollAndSelectCategoryTile(Category categoryName) {
		boolean found = false;
		WebElement categoryTileToSelect = null;
		
		do
		{
			try {
				categoryTileToSelect = findElement(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.thecarousell.Carousell:id/list_collection']/android.widget.FrameLayout/android.widget.TextView[@text='"+categoryName+"']"));
				found=true;
			} catch (Exception e) {
				WebElement categoryTileToScrollFrom = findElement(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.thecarousell.Carousell:id/list_collection']/android.widget.FrameLayout[7]"));
				WebElement categoryTileToScrollTo = findElement(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.thecarousell.Carousell:id/list_collection']/android.widget.FrameLayout[1]"));
				new TouchAction(driver).press(categoryTileToScrollFrom).waitAction(Duration.ofSeconds(2)).moveTo(categoryTileToScrollTo).release().perform();
		}
		}
		while(found==false);
		
		categoryTileToSelect.click();
	}
	
}
