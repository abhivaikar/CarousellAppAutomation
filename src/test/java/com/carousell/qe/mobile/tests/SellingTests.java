package com.carousell.qe.mobile.tests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.carousell.qe.mobile.base.BaseTest;
import com.carousell.qe.mobile.pageobjects.AuthPage;
import com.carousell.qe.mobile.pageobjects.HomePage;
import com.carousell.qe.mobile.pageobjects.WelcomePage;

import io.appium.java_client.TouchAction;

public class SellingTests extends BaseTest {
  
   
  @Test(description="Test to list an item and verify that it appears in search results based on filters applied")
  public void testNewListingAppearsInSearch() throws Exception {

	  new WelcomePage(driver).beginSignUpOrLoginWithEmail();
	  new AuthPage(driver).login("abhivaikar","password");	  
	  new HomePage(driver,true).startSellingWithPhotoFromCamera();
	  
	  
	  //Capture photo from camera and confirm
	  WebElement cameraTypeSwitchButton = driver.findElement(By.id("com.thecarousell.Carousell:id/button_camera_switch"));
	  cameraTypeSwitchButton.click();
	  
	  Thread.sleep(5000);
	  WebElement capturePhotoFromCameraButton = driver.findElement(By.xpath("//android.widget.ImageView[@clickable='true']"));
	  capturePhotoFromCameraButton.click();
	  
	  WebElement confirmClickedPhoto = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.ImageButton[2]"));
	  confirmClickedPhoto.click();
	  
	  WebElement detailsTextView = driver.findElement(By.xpath("//android.widget.TextView[@text='Details']"));
	  detailsTextView.click();
	  
	  //Select item category
	  Thread.sleep(2000);
	  WebElement selectCategoryButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Choose a category']"));
	  selectCategoryButton.click();
	  
	  
	  WebElement categoryToBeClicked = getCategoryOptionToBeSelectedFromCategoryListView("Everything Else");
	  categoryToBeClicked.click();
	  
	  
	  //Provide item title, condition and description
	  WebElement whatAreYouSellingButton = driver.findElement(By.xpath("//android.widget.TextView[@text='What are you selling?']"));
	  whatAreYouSellingButton.click();
	  
	  
	  String itemTitle = "Carousell Test Item";
	  WebElement itemTitleText = driver.findElement(By.id("com.thecarousell.Carousell:id/text_title"));
	  itemTitleText.sendKeys(itemTitle);
	  
	  WebElement viewProductConditionButton = driver.findElement(By.id("com.thecarousell.Carousell:id/view_product_condition"));
	  viewProductConditionButton.click();
	  
	  WebElement itemNewConditionCheckedTextView = driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='New']"));
	  itemNewConditionCheckedTextView.click();
	  
	  WebElement descriptionText = driver.findElements(By.xpath("//android.widget.EditText")).get(1);
	  descriptionText.click();
	  descriptionText.sendKeys("Brand New, Size: XL, Carousell Test Item");
	  
	  WebElement confirmItemDetailsButton = driver.findElement(By.id("com.thecarousell.Carousell:id/action_submit"));
	  confirmItemDetailsButton.click();
	  
	  //Set Item Price
	  WebElement itemPriceText = driver.findElement(By.xpath("//android.widget.EditText[@text='Set a price']"));
	  itemPriceText.sendKeys("0.1012");//This should enter price as 10.12. sendKeys("10.12") will not work
	  
	  
	//Choose delivery/meetup details
	  WebElement viewDealOptionsButton = driver.findElement(By.id("com.thecarousell.Carousell:id/view_deal_option"));
	  viewDealOptionsButton.click();
	  
	  if(driver.findElement(By.id("com.thecarousell.Carousell:id/option_meetup")).getAttribute("checked").equals("false"))
	  {
		  WebElement meetupOptionCheckedTextView = driver.findElement(By.id("com.thecarousell.Carousell:id/option_meetup"));
		  meetupOptionCheckedTextView.click();
		  
		  WebElement dealLocationText = driver.findElement(By.id("com.thecarousell.Carousell:id/text_location"));
		  dealLocationText.click();
		  
		  WebElement dealLocation = driver.findElements(By.xpath("//android.widget.ListView[@resource-id='com.thecarousell.Carousell:id/list_venues']/android.widget.LinearLayout")).get(0);
		  dealLocation.click();
		  
		  WebElement meetupDetailsText = driver.findElement(By.id("com.thecarousell.Carousell:id/text_meetup_detail"));
		  meetupDetailsText.sendKeys("Let's meet at 5 PM.");
	  }
	  
	  
	  
	  WebElement confirmDealLocationDetailsButton = driver.findElement(By.id("com.thecarousell.Carousell:id/action_submit"));
	  confirmDealLocationDetailsButton.click();
	  
	  WebElement submitListingButton = driver.findElement(By.id("com.thecarousell.Carousell:id/action_submit"));
	  submitListingButton.click();
	  
	  WebElement listItButton = driver.findElement(By.xpath("//android.widget.Button[@text='List it!']"));
	  listItButton.click();
	  
	  
	  //Go to Browse Tab and click on "Everything Else" category tile
	  WebElement browseTab = driver.findElement(By.xpath("//android.widget.TextView[@text='BROWSE']"));
	  browseTab.click();
	  
	  WebElement categoryTileToSelect = getCategoryTileToSelectInBrowseTab("Everything Else");
	  categoryTileToSelect.click();
	  
	  //Filter results by Recent
	  try {
		driver.findElement(By.xpath("//android.widget.TextView[@text='OK, Got it!']")).click();
	} catch (Exception e) {
		//Do Nothing
	}
	  
	  WebElement filterBarInHeader = driver.findElement(By.id("com.thecarousell.Carousell:id/bar_filter"));
	  filterBarInHeader.click();
	  
	  WebElement filterOptionForRecentItems = driver.findElement(By.id("com.thecarousell.Carousell:id/text_sort_recent"));
	  filterOptionForRecentItems.click();
	  
	  WebElement applyFilterButton = driver.findElement(By.id("com.thecarousell.Carousell:id/button_apply_filter"));
	  applyFilterButton.click();
	  
	  Assert.assertTrue(isListedItemSeenInTheList("Carousell Test Item"));
  }
  
  
  private boolean isListedItemSeenInTheList(String itemTitle) {
	  boolean found=false;
		WebElement itemToFind = null;
		
		do
		{
			try {
				itemToFind = driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.thecarousell.Carousell:id/view_product']/android.widget.TextView[@text='"+itemTitle+"']"));
				
				found=true;
			} catch (Exception e) {
				WebElement itemTileToScrollFrom = driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.thecarousell.Carousell:id/view_product'][4]"));
				WebElement itemTileToScrollTo = driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.thecarousell.Carousell:id/view_product'][2]"));
				new TouchAction(driver).press(itemTileToScrollFrom).waitAction(Duration.ofSeconds(1)).moveTo(itemTileToScrollTo).release().perform();
		}
		}
		while(found==false);
	
	return found;
}

private WebElement getCategoryTileToSelectInBrowseTab(String categoryName) {
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
	return categoryTileToSelect;
}

private WebElement getCategoryOptionToBeSelectedFromCategoryListView(String categoryToSelect) {
	
	boolean found = false;
	WebElement categoryToSearch = null;
	
	do
	{
		try {
			categoryToSearch = driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='"+categoryToSelect+"']"));
			found=true;
		} catch (Exception e) {
			WebElement sixthCategoryInCurrentList = driver.findElement(By.xpath("//android.widget.CheckedTextView[6]"));
			WebElement fourthCategoryInCurrentList = driver.findElement(By.xpath("//android.widget.CheckedTextView[3]"));
			new TouchAction(driver).press(sixthCategoryInCurrentList).waitAction(Duration.ofSeconds(2)).moveTo(fourthCategoryInCurrentList).release().perform();
	}
	}
	while(found==false);
	
	return categoryToSearch;
}

}
