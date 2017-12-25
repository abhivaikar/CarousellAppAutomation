package com.carousell.qe.mobile.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.carousell.qe.mobile.base.BasePage;
import com.carousell.qe.mobile.enums.Category;

import io.appium.java_client.TouchAction;

/**
 * Page Object for Selling page to enter all new listing details like category, item details, price, deal options etc.
 * @author Abhijeet
 *
 */
public class SellingPage extends BasePage {
	
	
	By labelWithDetailsAsTextBy = By.xpath("//android.widget.TextView[@text='Details']");
	By categoryDropDownBy = By.id("com.thecarousell.Carousell:id/spinner_category");
	By viewItemDetailsButtonBy = By.id("com.thecarousell.Carousell:id/layout_item_details");
	By itemPriceBy = By.id("com.thecarousell.Carousell:id/text_sell_price");
	By viewDealOptionsButtonBy = By.id("com.thecarousell.Carousell:id/view_deal_option");
	By submitListingConfirmButtonBy = By.id("com.thecarousell.Carousell:id/action_submit");
	By listItButtonOnPopupBy = By.xpath("//android.widget.Button[@text='List it!']");
	
	public SellingPage() {
		click(labelWithDetailsAsTextBy);
	}

	public SellingPage setCategory(Category categoryToSelect) {
	  
	  click(categoryDropDownBy);
	  boolean found = false;
	  WebElement categoryToSearch = null;
		
	  //This performs a looped swipe on the category list in order to find the category and click on it
		do
		{
			try {
				categoryToSearch = findElement(By.xpath("//android.widget.CheckedTextView[@text='"+categoryToSelect+"']"));
				found=true;
			} catch (Exception e) {
				WebElement categoryToSwipeFromInList = findElement(By.xpath("//android.widget.CheckedTextView[6]"));
				WebElement categoryToSwipeToInList = findElement(By.xpath("//android.widget.CheckedTextView[3]"));
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
		
		findElement(viewItemDetailsButtonBy).click();
		return new ItemDetailsInputPage();	
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
		
		type(itemPriceBy,itemPrice);
		return this;
	}


	public DealDetailsInputPage getDealDetailsInputPage() {
		click(viewDealOptionsButtonBy);
		return new DealDetailsInputPage();
	}

	public void listIt() {
		click(submitListingConfirmButtonBy);
		click(listItButtonOnPopupBy);
	}

}
