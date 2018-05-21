package com.hellofresh.ordercheckout;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hellofresh.commonutilstests.Constants;
import com.hellofresh.order.OrderCheckOut;

/* ========================================================================
 * Class contains tests to validate check out. It will create an instance 
 * of "OrderCheckOut" class where all selenium actions are present. 
 * ========================================================================*/

public class OrderCheckOutTests {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderCheckOutTests.class);
	OrderCheckOut order = new OrderCheckOut();
	
	@BeforeTest
	public void setup() {
		
	}
	
	/* =========================================================================
	 * Test case to validate order check out.
	 * =========================================================================*/
	
	@Test(priority=0, enabled=true)
	public void validateCheckOut() {
		logger.info("validateCheckOut : request to validate the check out");
		order.checkOut();
		
		Assert.assertEquals(order.getOrderConfirmationText(), Constants.ORDERCONFIRMATION);
		Assert.assertTrue(order.getStepDone());
		Assert.assertTrue(order.getStepEnd());
		Assert.assertTrue(order.getChequeIndent().contains("Your order on My Store is complete."));
		Assert.assertTrue(order.getUrl().contains("controller=order-confirmation"));
	}
	
	
	/* ==========================================================================
	 * This method will execute after each test to take screenshot in case of 
	 * failure.
	 * ==========================================================================*/
	
	@AfterMethod
	public void OnTestFailure(ITestResult result) {
		String screenFile = System.getProperty("user.dir") + "/test-output/screenshots/" + result.getName() + System.currentTimeMillis() + ".png";
		if (ITestResult.FAILURE == result.getStatus()) {
			order.takeScreenShot(new File(screenFile));
		}
	}
	
	@AfterTest
	public void end() {
		order.closeDriver();
	}
	
}
