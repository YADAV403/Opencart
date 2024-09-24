package com.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.Home;
import com.pageObjects.LoginPage;
import com.pageObjects.MyAccountPage;
import com.testBase.Base;
import com.utilities.DataProviders;

public class LoginDDT extends Base {


	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "dataDriven")
	public void verify_loginddt(String email, String pwd, String exp){

		logger.info("Started execution");
		try {
			Home h = new Home(wd);
			h.btn_myAccount1();
			logger.info("clicked myaccount");
			h.btn_login();
			logger.info("clicked login");

			logger.info("entering username and password");
			LoginPage lp = new LoginPage(wd);
			lp.enterUserName(email);
			lp.enterPassword(pwd);
			lp.clickLogin();

			logger.info("entered myaccount page");
			MyAccountPage macc = new MyAccountPage(wd);
			boolean targetPage = macc.isMyAccountPageExists();

			if(exp.equalsIgnoreCase("valid")) {

				if(targetPage==true) {
					macc.clicklogout();
					Assert.assertTrue(true);

				}
				else {
					Assert.assertTrue(false);	
				}
			}

			if(exp.equalsIgnoreCase("invalid")) {

				if(targetPage==true) {
					macc.clicklogout();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);
				}
			}
		}
		
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("Finished execution");
	}
}
