package com.carousell.qe.mobile.pageobjects;

import org.openqa.selenium.By;

import com.carousell.qe.mobile.base.BasePage;

public class AuthPage extends BasePage {

    By loginTabBy = By.xpath("//android.widget.TextView[@text='LOGIN']");
    By userNameTextFieldBy = By.id("com.thecarousell.Carousell:id/text_username");
	By passwordTextFieldBy = By.id("com.thecarousell.Carousell:id/text_password");
	By loginButtonBy = By.id("com.thecarousell.Carousell:id/action_signin");
	

	public void login(String userName, String password) {
		click(loginTabBy);
		type(userNameTextFieldBy,userName);
		type(passwordTextFieldBy,password);
		click(loginButtonBy);
	}

}
