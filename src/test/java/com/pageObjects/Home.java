package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	
	WebDriver wd;
	
	public Home(WebDriver wd) {
		this.wd=wd;
		PageFactory.initElements(wd, this);
	}
	
	@FindBy(xpath="//a[@title='My Account']") WebElement myAccount1;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement register;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement login;
	
	public void btn_myAccount1() {
		myAccount1.click();
	};
	
	public void btn_register() {
		register.click();
	};
	
	public void btn_login() {
		login.click();
	}

}
