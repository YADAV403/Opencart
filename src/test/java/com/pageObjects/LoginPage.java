package com.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver wd;

	public LoginPage(WebDriver wd) {
		this.wd=wd;
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath="//input[@id='input-email']") WebElement userName;
	@FindBy(xpath="//input[@id='input-password']") WebElement password;
	@FindBy(xpath="//input[@value='Login']") WebElement login;

	public void enterUserName(String uname) {
		userName.sendKeys(uname);
	}

	public void enterPassword(String pass) {
		password.sendKeys(pass);
	}

	public void clickLogin() {
		JavascriptExecutor js = (JavascriptExecutor) wd;
		js.executeScript("arguments[0].click();", login);
	}

}

