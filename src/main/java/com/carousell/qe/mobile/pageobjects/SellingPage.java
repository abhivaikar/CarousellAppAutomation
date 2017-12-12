package com.carousell.qe.mobile.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.carousell.qe.mobile.enums.Category;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

/**
 * Page Object for Selling page to enter all new listing details like category, item details, price, deal options etc.
 * @author Abhijeet
 *
 */
public class SellingPage {
	
	private AppiumDriver<WebElement> driver;
	
	By labelWithDetailsAsTextBy = By.xpath("//android.widget.TextView[@text='Details']");
	By categoryDropDownBy = By.id("com.thecarousell.Carousell:id/spinner_category");
	By viewItemDetailsButtonBy = By.id("com.thecarousell.Carousell:id/layout_item_details");
	By itemPriceBy = By.id("com.thecarousell.Carousell:id/text_sell_price");
	By viewDealOptionsButtonBy = By.id("com.thecarousell.Carousell:id/view_deal_option");
	By submitListingConfirmButtonBy = By.id("com.thecarousell.Carousell:id/action_submit");
	By listItButtonOnPopupBy = By.xpath("//android.widget.Button[@text='List it!']");
	
	public SellingPage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
		driver.findElement(labelWithDetailsAsTextBy).click();
	}

	public SellingPage setCategory(Category categoryToSelect) {
	  
	  driver.findElement(categoryDropDownBy).click();
	  boolean found = false;
	  WebElement categoryToSearch = null;
		
	  //This performs a looped swipe on the category list in order to find the category and click on it
		do
		{
			try {
				categoryToSearch = driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='"+categoryToSelect+"']"));
				found=true;
			} catch (Exception e) {
				WebElement categoryToSwipeFromInList = driver.findElement(By.xpath("//android.widget.CheckedTextView[6]"));
				WebElement categoryToSwipeToInList = driver.findElement(By.xpath("//android.widget.CheckedTextView[3]"));
				new TouchAction(driver).press(categoryToSwipeFromInList).waitAction(Duration.ofSeconds(2)).moveTo(categoryToSwipeToInList).release().perform();
		}
		}
		while(found==false);
		
		categoryToSearch.click();
		return this;
	}

	/**
	 * Taps on 'What are you selling' button and opens Item Details input page
	 * @return ItemDetailsInputPage
	 */
	public ItemDetailsInputPage getItemDetailsInputPage() {
		
		driver.findElement(viewItemDetailsButtonBy).click();
		return new ItemDetailsInputPage(driver);
		
	}

	public SellingPage setPrice(String itemPrice) {
		if(itemPrice.contains("."))
		{
			String priceBeforeDecimal = itemPrice.split("\\.")[0];
			String priceAfterDecimal = itemPrice.split("\\.")[1];
			itemPrice = "0." + priceBeforeDecimal + priceAfterDecimal;
		}
		else
			itemPrice = "0." + itemPrice + "00";
		
		driver.findElement(itemPriceBy).sendKeys(itemPrice);
		return this;
	}


	public DealDetailsInputPage getDealDetailsInputPage() {
		driver.findElement(viewDealOptionsButtonBy).click();
		return new DealDetailsInputPage(driver);
	}

	public void listIt() {
		driver.findElement(submitListingConfirmButtonBy).click();
		driver.findElement(listItButtonOnPopupBy).click();
	}

}
