package com.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterAccount {
	
	WebDriver wd;
	
	public RegisterAccount(WebDriver wd) {
		this.wd=wd;
		PageFactory.initElements(wd, this);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement firstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement lastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement Email;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement Telephone ;
	@FindBy(xpath="//input[@id='input-password']") WebElement password;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement conpassword;
	@FindBy(xpath="//input[@name='agree']") WebElement agree;
	@FindBy(xpath="//input[@value='Continue']") WebElement contin;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msg;
	
	public void setFirstName(String fname) {
		firstName.sendKeys(fname);
	}
	public void setLastName(String lname) {
		lastName.sendKeys(lname);
	}
	public void setEmail(String email) {
		Email.sendKeys(email);
	}
	public void setTelephone(String phn) {
		Telephone.sendKeys(phn);
	}
	public void setPassword(String pass) {
		password.sendKeys(pass);
	}
	public void setConPassword(String pass) {
		conpassword.sendKeys(pass);
	}
	public void btnagree() {
		JavascriptExecutor js = (JavascriptExecutor) wd;
		js.executeScript("arguments[0].click();", agree);
	}
	public void btncontin() {
		JavascriptExecutor js = (JavascriptExecutor) wd;
		js.executeScript("arguments[0].click();", contin);
	}
	public String getMsg(){
		try {
		return (msg.getText());
		}
		catch(Exception e) {
			return (e.getMessage());
		}
	}

}
