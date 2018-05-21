package com.hellofresh.authentication;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellofresh.authenticationtests.NewUser;
import com.hellofresh.commonutils.WebDriverFactory;
import com.hellofresh.commonutilstests.CommonUtils;
import com.hellofresh.commonutilstests.Constants;

public class Authentication {

	private static final Logger logger = LoggerFactory.getLogger(Authentication.class);

	static WebDriver driver = null;
	static WebDriverWait wait = null;
	AuthenticationObjects sign = null;

	public Authentication() {
		driver = WebDriverFactory.getDriver(CommonUtils.getBrowserType());
		wait = new WebDriverWait(driver, 10, 50);

		driver.get(Constants.URL);

		sign = new AuthenticationObjects(driver, wait);
	}

	/*
	 * =============================================================== Method to
	 * access all create account locators & perform actions to create a new account
	 * 
	 * Input : NewUser data read from NewUser.json file
	 * ===============================================================
	 */

	public void createAccount(NewUser newUser, String email) {
		logger.info("SignIn : Request to create an account...");

		// Step 1 - Provide Email Address
		sign.getLoginElement().click();
		sign.getEmailCreateElement().sendKeys(email);
		sign.getSubmitCreateElement().click();

		// Step 2 - Your Personal Information
		sign.getIdGender2().click();
		sign.getCustomerFirstNameElement().sendKeys(newUser.getFirstName());
		sign.getCustomerLastNameElement().sendKeys(newUser.getLastName());
		sign.getPasswordElement().sendKeys(newUser.getPassword());
		sign.getDay().selectByValue(newUser.getBirthDate().split("-")[0].toString());
		sign.getMonth().selectByValue(newUser.getBirthDate().split("-")[1].toString());
		sign.getYear().selectByValue(newUser.getBirthDate().split("-")[2].toString());

		// Step 3 - Your Address
		sign.getCompanyElement().sendKeys(newUser.getCompany());
		sign.getAddress1Element().sendKeys(newUser.getAddress1());
		sign.getAddress2Element().sendKeys(newUser.getAddress2());
		sign.getCityElement().sendKeys(newUser.getCity());
		sign.getState().selectByVisibleText(newUser.getState());
		sign.getPostalCodeElement().sendKeys(newUser.getPostalCode());
		sign.getOtherElement().sendKeys(newUser.getOtherInfo());
		sign.getPhoneElement().sendKeys(newUser.getPhone());
		sign.getPhoneMobileElement().sendKeys(newUser.getMobilePhone());
		sign.getAliasElement().sendKeys(newUser.getAlias());
		sign.getSubmitAccountElement().click();

		// CommonUtils.saveEmailInJson(email, newUser.getFirstName(),
		// newUser.getLastName());
		logger.info("Sign In : create account request completed...");
	}

	/*
	 * =========================================================== Method to get
	 * header text once SignIn is done
	 * ===========================================================
	 */
	public String getHeaderText() {
		return sign.getMyAccountHeadingElement().getText();
	}

	/*
	 * =========================================================== Method to get my
	 * account text once SignIn is done
	 * ===========================================================
	 */
	public String getAccountText() {
		return sign.getAccountElement().getText();
	}

	/*
	 * =========================================================== Method to get
	 * account information text once SignIn is done
	 * ===========================================================
	 */
	public String getInfoAccountText() {
		return sign.getAccountInfoElement().getText();
	}

	/*
	 * =========================================================== Method to check
	 * logout is displayed once SignIn is done
	 * ===========================================================
	 */
	public boolean isLogoutDisplayed() {
		return sign.getLogoutElement().isDisplayed();
	}

	public void logOut() {
		logger.info("Logout : request to logout...");
		sign.getLogoutElement().click();
		logger.info("Logout : request to logout is completed...");
	}

	/*
	 * =============================================================== Method to
	 * access all login locators & perform actions to login Input :
	 * ===============================================================
	 */
	public void login(String email, String password) {
		logger.info("Login : request to login...");

		// Check if user is already logged in. If yes, log out then validate login
		// functionality
		if (isLogoutDisplayed()) {
			logOut();
		}
		sign.getAlreadyUserLoginElement().click();
		sign.getEmailElement().sendKeys(email);
		sign.getEmailPasswordElement().sendKeys(password);
		sign.getSubmitLoginElement().click();

		logger.info("Login : login request is completed...");
	}

	public String getUrl() {
		return sign.getCurrentUrl();
	}

	public void takeScreenShot(File file) {
		sign.takeScreenShot(file);

	}

	public void closeWebDriver() {
		sign.closeWebDriver();
	}
}
