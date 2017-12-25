package com.carousell.qe.mobile.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.carousell.qe.mobile.base.BaseTest;
import com.carousell.qe.mobile.enums.Category;
import com.carousell.qe.mobile.enums.ConditionType;
import com.carousell.qe.mobile.enums.DealType;
import com.carousell.qe.mobile.enums.SearchResultSort;
import com.carousell.qe.mobile.pageobjects.AuthPage;
import com.carousell.qe.mobile.pageobjects.CameraPage;
import com.carousell.qe.mobile.pageobjects.CameraPhotoPreviewEditPage;
import com.carousell.qe.mobile.pageobjects.HomePage;
import com.carousell.qe.mobile.pageobjects.SearchPage;
import com.carousell.qe.mobile.pageobjects.SellingPage;
import com.carousell.qe.mobile.pageobjects.WelcomePage;

public class SellingTests extends BaseTest {
  
   
  @Test(description="Test to list an item and verify that it appears in search results based on filters applied")
  public void testNewListingAppearsInSearch() throws Exception {

	  new WelcomePage().beginSignUpOrLoginWithEmail();
	  new AuthPage().login("abhivaikar","password");	  
	  new HomePage().startSellingWithPhotoFromCamera();
	  new CameraPage().capturePhoto();
	  new CameraPhotoPreviewEditPage().moveForward();
	  
	  SellingPage sellingPage = new SellingPage();
	  sellingPage.setCategory(Category.EVERYTHING_ELSE);
	  sellingPage.getItemDetailsInputPage().setItemDetails("Carousell Test Item",ConditionType.NEW,"Brand New, Size: XL, Carousell Test Item").confirm();
	  sellingPage.setPrice("10.12");
	  sellingPage.getDealDetailsInputPage().setDealDetails(DealType.MEETUP,"Lets meetup at 5 PM").confirm();
	  sellingPage.listIt();
	  grabScreenshot();
	  
	  new HomePage().getBrowseTab().scrollAndSelectCategoryTile(Category.EVERYTHING_ELSE);
	  
	  SearchPage searchPage = new SearchPage();
	  searchPage.sortBy(SearchResultSort.RECENT);
	  Assert.assertTrue(searchPage.isItemVisibleInSearchResults("Carousell Test Item"),"Item was not found in search results");
	  grabScreenshot();
	  Reporter.log("Item is visible in search results",true);
  }

}
