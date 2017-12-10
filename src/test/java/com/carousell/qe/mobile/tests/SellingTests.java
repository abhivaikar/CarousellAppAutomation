package com.carousell.qe.mobile.tests;

import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class SellingTests {
  
  private AppiumDriver<WebElement> driver;
	
  
  @BeforeTest
  public void setUp() throws Exception {
	  String userDir = System.getProperty("user.dir");
	  String localApp = "/apps/Carousell-test-engineering-app.apk";
	  String appPath = Paths.get(userDir, localApp).toAbsolutePath().toString();
	  URL appiumServerAddress = new URL("http://127.0.0.1:4723/wd/hub");
	  
	  DesiredCapabilities capabilities = new DesiredCapabilities();
	  capabilities.setCapability("deviceName","device");
      capabilities.setCapability("platformName","Android");
      capabilities.setCapability("app", appPath);
      capabilities.setCapability("appPackage", "com.thecarousell.Carousell");
      capabilities.setCapability("appWaitActivity", "com.thecarousell.*");
      capabilities.setCapability("fullReset", true);
      capabilities.setCapability("newCommandTimeout", "1200");
      capabilities.setCapability("autoGrantPermissions", true);
      capabilities.setCapability("clearSystemFiles", true);
      driver = new AndroidDriver<WebElement>(appiumServerAddress, capabilities);
      driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
  }
  
  @Test(description="Test to list an item and verify that it appears in search results based on filters applied")
  public void testNewListingAppearsInSearch() throws Exception {
	  /*
	   *1. Open app.
	    2. Login
	    3. Tap on 'Sell' button on top right.
	    4. Select an option out of 'Take photo from camera' or 'Choose an existing photo from gallery'
	    5. Select photo from gallery.
	    6. Specify all details of the listing (Category,Price, Listing name, Deal Options)
	    7. Tap on ✔
	    8. Tap on 'List it!' on Submit Listing popup
	    9. Tap on Search button in header
	    10. Select category with 'Everything Else'
	    11. Apply 'Recent' Filter
	    12. Verify item appears in the results.*/  
	  WebElement loginWithEmailButton = driver.findElement(By.xpath("//android.widget.Button[@text='Email']"));
	  loginWithEmailButton.click();
	  WebElement loginTabButton = driver.findElement(By.xpath("//android.widget.TextView[@text='LOGIN']"));
	  loginTabButton.click();
	  
	  WebElement usernameTextField = driver.findElement(By.xpath("//android.widget.EditText[@text='email or username']"));
	  usernameTextField.sendKeys("abhivaikar");
	  WebElement passwordTextField = driver.findElement(By.xpath("//android.widget.EditText[@NAF='true']"));
	  passwordTextField.sendKeys("password");
	  
	  WebElement loginButton = driver.findElementByAccessibilityId("Login");
	  loginButton.click();
	  
	  
	  try {
		  if(driver.findElement(By.xpath("//android.widget.TextView[@text='Get Google Play Service']")).isDisplayed())
			  driver.navigate().back();
	} catch (Exception e) {
	}
	  
	  
	  WebElement sellButton = driver.findElement(By.xpath("//android.widget.TextView[@text='SELL']"));
	  sellButton.click();
	  
	  WebElement chooseExistingPhotoFromGalleryButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Take photo from camera']"));
	  chooseExistingPhotoFromGalleryButton.click();
	  
	  
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
	  
	  
	  String itemTitle = "Carousell Test Item - Do Not Buy - " + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
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
	  
	  WebElement viewDealOptionsButton = driver.findElement(By.id("com.thecarousell.Carousell:id/view_deal_option"));
	  viewDealOptionsButton.click();
	  
	  //Select meetup details
	  //WebElement meetupOptionCheckedTextView = driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Meet-up']"));
	  WebElement meetupOptionCheckedTextView = driver.findElement(By.id("com.thecarousell.Carousell:id/view_meetup"));
	  meetupOptionCheckedTextView.click();
	  
	  WebElement dealLocationText = driver.findElement(By.id("com.thecarousell.Carousell:id/text_location"));
	  dealLocationText.click();
	  
	  WebElement dealLocation = driver.findElements(By.xpath("//android.widget.ListView[@resource-id='com.thecarousell.Carousell:id/list_venues']/android.widget.LinearLayout")).get(0);
	  dealLocation.click();
	  
	  WebElement meetupDetailsText = driver.findElement(By.id("com.thecarousell.Carousell:id/text_meetup_detail"));
	  meetupDetailsText.sendKeys("Let's meet at 5 PM.");
	  
	  WebElement confirmDealLocationDetailsButton = driver.findElement(By.id("com.thecarousell.Carousell:id/action_submit"));
	  confirmDealLocationDetailsButton.click();
	  
	  WebElement submitListingButton = driver.findElement(By.id("com.thecarousell.Carousell:id/action_submit"));
	  submitListingButton.click();
	  
	  WebElement listItButton = driver.findElement(By.xpath("//android.widget.Button[@text='List it!']"));
	  listItButton.click();
	  
	  
	  //Go to Browse
	  WebElement browseTab = driver.findElement(By.xpath("//android.widget.TextView[@text='BROWSE']"));
	  browseTab.click();
	  
	  WebElement categoryTileToSelect = getCategoryTileToSelectInBrowseTab("EVERYTHING ELSE");
	  categoryTileToSelect.click();
	  
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
				WebElement sixthCategoryInCurrentList = driver.findElement(By.xpath("//android.widget.CheckedTextView[7]"));
				WebElement fourthCategoryInCurrentList = driver.findElement(By.xpath("//android.widget.CheckedTextView[1]"));
				new TouchAction(driver).press(sixthCategoryInCurrentList).waitAction(Duration.ofSeconds(2)).moveTo(fourthCategoryInCurrentList).release().perform();
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

  @AfterTest
  public void tearDown()
  {
	  if(driver!=null)
		  driver.quit();
  }
}
