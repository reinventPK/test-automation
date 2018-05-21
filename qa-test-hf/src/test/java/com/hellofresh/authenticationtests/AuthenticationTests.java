package com.hellofresh.authenticationtests;

import java.io.File;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hellofresh.authentication.Authentication;
import com.hellofresh.commonutilstests.CommonUtils;
import com.hellofresh.commonutilstests.Constants;

/* ===================================================================
 * This contains all authentication tests. It will create an instance
 * of "Authentication" class to access all selenium actions related to 
 * authentication.
 * ===================================================================*/


public class AuthenticationTests {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationTests.class);
	Authentication auth = new Authentication();

	@BeforeClass
	public void setup() {

	}
	
	/* =================================================================
	 * Test to validate sign in functionality. It will read test data 
	 * from NewUser.json file. 
	 * =================================================================*/

	@Test(priority = 0, enabled = true)
	public void signIn() {
		logger.info("signIn : validating sign in request...");
		List<NewUser> newUserList = CommonUtils.readNewUserDataFromJson(Constants.NEWUSERFILE);

		for (NewUser user : newUserList) {
			if (user.getFirstName().equals("Joe") && user.getLastName().equals("Black")) {
				auth.createAccount(user, getEmail());

				Assert.assertEquals(auth.getHeaderText(), Constants.MYACCOUNT);
				Assert.assertEquals(auth.getAccountText(), user.getFirstName() + " " + user.getLastName());
				Assert.assertTrue(auth.getInfoAccountText().contains("Welcome to your account."));
				Assert.assertTrue(auth.isLogoutDisplayed());
				Assert.assertTrue(auth.getUrl().contains("controller=my-account"));
				break;
			}
		}
	}

	public String getEmail() {
		String timestamp = String.valueOf(new Date().getTime());
		return "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
	}

	/* ===================================================================
	 * Test to validate login functionality. This test will use existing
	 * user details. 
	 * ===================================================================*/
	@Test(priority = 1, enabled = true)
	public void login() {
		logger.info("login : validating login request...");
		
		//Assuming email & password already exists in system
		auth.login("hf_challenge_1526798378419@hf378419.com","password@101");

		Assert.assertEquals(auth.getHeaderText(), Constants.MYACCOUNT);
		Assert.assertTrue(auth.getInfoAccountText().contains("Welcome to your account."));
		Assert.assertTrue(auth.isLogoutDisplayed());
		Assert.assertTrue(auth.getUrl().contains("controller=my-account"));

	}
	
	/* ====================================================================
	 * This method will execute after each test to capture screenshot in 
	 * case of failure.
	 * ====================================================================*/
	@AfterMethod
	public void OnTestFailure(ITestResult result) {
		String screenFile = System.getProperty("user.dir") + "/test-output/screenshots/" + result.getName()
				+ System.currentTimeMillis() + ".png";
		if (ITestResult.FAILURE == result.getStatus()) {
			auth.takeScreenShot(new File(screenFile));
		}
	}

	@AfterClass
	public void end() {
		 auth.closeWebDriver();
	}

}
