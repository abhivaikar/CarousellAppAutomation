package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.carousell.qe.mobile.base.BasePage;
import com.carousell.qe.mobile.enums.ConditionType;

public class ItemDetailsInputPage extends BasePage {
	
	By itemTitleTextBy = By.id("com.thecarousell.Carousell:id/text_title");
	By viewItemConditionButtonBy = By.id("com.thecarousell.Carousell:id/view_product_condition");
	By descriptionTextBy = By.id("com.thecarousell.Carousell:id/text_description");
	By submitDetailsButtonBy = By.id("com.thecarousell.Carousell:id/action_submit");
	

	public ItemDetailsInputPage setItemDetails(String itemTitleText, ConditionType condition, String descriptionText) {
	  setItemTitle(itemTitleText);
	  setCondition(condition);
	  setDescription(descriptionText);
	  
	  return this;
	 }

	public void setDescription(String descriptionText) {
		WebElement descriptionTextElement = findElement(descriptionTextBy);
		descriptionTextElement.click();
		descriptionTextElement.sendKeys(descriptionText);
	}

	public void setCondition(ConditionType condition) {
		click(viewItemConditionButtonBy);
		click(By.xpath("//android.widget.CheckedTextView[@text='"+condition+"']"));
	}

	public void setItemTitle(String itemTitleText) {
		type(itemTitleTextBy,itemTitleText);
	}
	
	public void confirm()
	{
		click(submitDetailsButtonBy);
	}

}
