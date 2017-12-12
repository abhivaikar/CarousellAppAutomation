package com.carousell.qe.mobile.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.carousell.qe.mobile.enums.Category;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

public class BrowseTab {

	private AppiumDriver<WebElement> driver;
	
	public BrowseTab(AppiumDriver<WebElement> driver) {
		this.driver = driver;
	}

	public void scrollAndSelectCategoryTile(Category categoryName) {
		boolean found = false;
		WebElement categoryTileToSelect = null;
		
		do
		{
			try {
				categoryTileToSelect = driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.thecarousell.Carousell:id/list_collection']/android.widget.FrameLayout/android.widget.TextView[@text='"+categoryName+"']"));
				found=true;
			} catch (Exception e) {
				WebElement categoryTileToScrollFrom = driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.thecarousell.Carousell:id/list_collection']/android.widget.FrameLayout[7]"));
				WebElement categoryTileToScrollTo = driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.thecarousell.Carousell:id/list_collection']/android.widget.FrameLayout[1]"));
				new TouchAction(driver).press(categoryTileToScrollFrom).waitAction(Duration.ofSeconds(2)).moveTo(categoryTileToScrollTo).release().perform();
		}
		}
		while(found==false);
		
		categoryTileToSelect.click();
	}
	
}
