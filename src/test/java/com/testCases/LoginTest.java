package com.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.Home;
import com.pageObjects.LoginPage;
import com.pageObjects.MyAccountPage;
import com.testBase.Base;

public class LoginTest extends Base {
	
	@Test(groups = {"sanity","master"})
	public void verify_login() {
		
		logger.info("started execution");
		try {
		Home h = new Home(wd);
		h.btn_myAccount1();
//		Thread.sleep(3000);
		logger.info("clicked myaccount");
		h.btn_login();
//		Thread.sleep(3000);
		logger.info("clicked login");
		
		logger.info("entering username and password");
		LoginPage lp = new LoginPage(wd);
		lp.enterUserName(p.getProperty("email"));
		lp.enterPassword(p.getProperty("password"));
//		Thread.sleep(3000);
		lp.clickLogin();
		
		logger.info("verification page");
		MyAccountPage macc = new MyAccountPage(wd);
		boolean targetPage = macc.isMyAccountPageExists();
		Assert.assertTrue(targetPage);
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("execution finished");
		
	}
	
	

}
