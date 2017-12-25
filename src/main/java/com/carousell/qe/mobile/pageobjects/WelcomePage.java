package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;

import com.carousell.qe.mobile.base.BasePage;

public class WelcomePage extends BasePage {
	
	By emailSignInButtonBy = By.id("com.thecarousell.Carousell:id/email_signin_button");
	

	public void beginSignUpOrLoginWithEmail() {
		click(emailSignInButtonBy);		
	}

}
