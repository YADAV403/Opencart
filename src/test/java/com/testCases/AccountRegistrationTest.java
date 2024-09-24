package com.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.Home;
import com.pageObjects.RegisterAccount;
import com.testBase.Base;

public class AccountRegistrationTest extends Base{

	@Test(groups = {"regression","master"})
	public void action() throws InterruptedException {
		
		logger.info("Execution Started");
		try {
		Home hme = new Home(wd);
		hme.btn_myAccount1();;
		logger.info("Clicked on MyAccount");
		hme.btn_register();
		logger.info("Clicked on Register");

		RegisterAccount ra =new RegisterAccount(wd);
		logger.info("Entering all the details");
		ra.setFirstName(randString().toUpperCase());
		ra.setLastName(randString().toUpperCase());
		ra.setEmail(randString().toLowerCase()+"@gmail.com");
		ra.setTelephone(randNum2());
		String P = randString()+"@"+randNum1();
		ra.setPassword(P);
		ra.setConPassword(P);
		ra.btnagree();
		ra.btncontin();
		String confmsg =ra.getMsg();
		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test failed");
			logger.debug("Debug Logs");
			Assert.assertTrue(false);
		}
//		Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");	
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("Execution Finished");
	}
}