package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.carousell.qe.mobile.enums.ConditionType;

import io.appium.java_client.AppiumDriver;

public class ItemDetailsInputPage {

	private AppiumDriver<WebElement> driver;
	
	By itemTitleTextBy = By.id("com.thecarousell.Carousell:id/text_title");
	By viewItemConditionButtonBy = By.id("com.thecarousell.Carousell:id/view_product_condition");
	By descriptionTextBy = By.id("com.thecarousell.Carousell:id/text_description");
	By submitDetailsButtonBy = By.id("com.thecarousell.Carousell:id/action_submit");
	
	public ItemDetailsInputPage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
	}

	public ItemDetailsInputPage setItemDetails(String itemTitleText, ConditionType condition, String descriptionText) {
	  setItemTitle(itemTitleText);
	  setCondition(condition);
	  setDescription(descriptionText);
	  
	  return this;
	 }

	public void setDescription(String descriptionText) {
		WebElement descriptionTextElement = driver.findElement(descriptionTextBy);
		descriptionTextElement.click();
		descriptionTextElement.sendKeys(descriptionText);
	}

	public void setCondition(ConditionType condition) {
		driver.findElement(viewItemConditionButtonBy).click();
		driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='"+condition+"']")).click();
	}

	public void setItemTitle(String itemTitleText) {
		driver.findElement(itemTitleTextBy).sendKeys(itemTitleText);
	}
	
	public void confirm()
	{
		driver.findElement(submitDetailsButtonBy).click();
	}

}
