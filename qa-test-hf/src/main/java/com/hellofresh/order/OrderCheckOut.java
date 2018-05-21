package com.hellofresh.order;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hellofresh.authentication.Authentication;
import com.hellofresh.commonutils.WebDriverFactory;
import com.hellofresh.commonutilstests.CommonUtils;
import com.hellofresh.commonutilstests.Constants;

/* ====================================================================
 * Class contains all selenium actions which will be called from 
 * TestNG tests. 
 * It will also get a driver using WebDriverFactory & initialize wait. 
 * ====================================================================*/

public class OrderCheckOut {
	private static final Logger logger = LoggerFactory.getLogger(Authentication.class);
	OrderCheckOutObjects order = null;
	Authentication auth = null;
	WebDriver driver = null;
	WebDriverWait wait = null;
	
	/* =================================================================
	 * Constructor to get driver & init wait.
	 * =================================================================*/
	public OrderCheckOut() {
		driver = WebDriverFactory.getDriver(CommonUtils.getBrowserType());
		driver.manage().window().maximize();
		driver.get(Constants.URL);
		
		wait = new WebDriverWait(driver, 10, 50);
		order = new OrderCheckOutObjects(driver, wait);
	}
	
	/* ==================================================================
	 * Method contains all selenium actions related to order check out.
	 * It will perform order check out using existing user details. 
	 * ==================================================================*/
	public void checkOut() {
		logger.info("OrderCheckOut : request to check out...");
		
		// Step 1 - Login 
		// Assuming email & password already exists in system
		login("hf_challenge_1526798378419@hf378419.com","password@101");

		// Step 2 - Select Order
		order.getGenderTextElement().click();
		order.getFadedShirtElement().click();
		order.getFadedShirtElement().click();
		order.getOrderSubmitElement().click();
		
		//Step 3 - Proceed to Check Out
		order.getProceedToCheckoutElement().click();
		order.getCartNavElement().click();
		order.getProcessAddressElement().click();
		order.getUniformCgvElement().click();
		order.getProcessCarrierElement().click();
		
		order.getBankWireElement().click();
		order.getCartNavButtonElement().click();
		
		logger.info("OrderCheckOut : request to check out is completed...");
	}
	
	/* ====================================================================
	 * Method to login before order checkout.
	 * ====================================================================*/
	private void login(String email, String password) {
		logger.info("Login : request to login to check out...");
		
		order.getAlreadyUserLoginElement().click();
		order.getEmailElement().sendKeys(email);
		order.getEmailPasswordElement().sendKeys(password);
		order.getSubmitLoginElement().click();
		
		logger.info("Login : login request is completed...");	
	}

	public String getOrderConfirmationText() {
		return order.getOrderConfirmationHeaderElement().getText();
	}
	
	public boolean getStepDone() {
		return order.getStepDoneElement().isDisplayed();
	}
	
	public boolean getStepEnd() {
		return order.getStepEndElement().isDisplayed();
	}
	
	public String getChequeIndent() {
		return order.getChequeIndentElement().getText();
	}
	
	public String getUrl() {
		return order.getCurrentUrl();
	}
	
	public void takeScreenShot(File destFile) {
		order.takeScreenShot(destFile);
	}

	public void closeDriver() {
		order.closeWebDriver();
		
	}
}
