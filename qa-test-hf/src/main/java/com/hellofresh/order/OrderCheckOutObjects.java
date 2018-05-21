package com.hellofresh.order;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellofresh.commonutils.WebDriverFactory;
import com.hellofresh.commonutilstests.CommonUtils;
import com.hellofresh.commonutilstests.Constants;

/* ==============================================================
 * This class contains object repository for Order page. 
 * Each method will return respective element locator. Locators 
 * will be used in actual business logic to provide test data.  
 * ============================================================== */

public class OrderCheckOutObjects {
	private static final Logger logger = LoggerFactory.getLogger(OrderCheckOutObjects.class);
	
	private WebDriver driver = null;	
	private WebDriverWait wait = null;
	
	public OrderCheckOutObjects(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	/* ==============================================================
	 * Methods to get Check Out functionality objects 
	 * ============================================================== */
	
	public WebElement getGenderTextElement() {
		//return wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("WOMEN")));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a")));
	}
	
	public WebElement getFadedShirtElement() {
		return driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li"));
		
	//	return driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/h5/a"));
	}
	
	public WebElement getOrderSubmitElement() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));
	}
	
	public WebElement getProceedToCheckoutElement() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")));
		//*[@id="add_to_cart"]/button/span
	
	}
	
	public WebElement getCartNavElement() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")));
	}
	
	public WebElement getProcessAddressElement() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("processAddress")));
	}
	
	public WebElement getUniformCgvElement() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv")));
	}
	
	public WebElement getProcessCarrierElement() {
		return driver.findElement(By.name("processCarrier"));
	}
	
	public WebElement getBankWireElement() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bankwire")));
	}

	public WebElement getCartNavButtonElement() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart_navigation']/button")));
	}
	
	public WebElement getOrderConfirmationHeaderElement() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
	}
	
	public WebElement getStepDoneElement() {
		return driver.findElement(By.xpath("//li[@class='step_done step_done_last four']"));
	}
	
	public WebElement getStepEndElement() {
		return driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']"));
	}
	
	public WebElement getChequeIndentElement() {
		return driver.findElement(By.xpath("//*[@class='cheque-indent']/strong"));
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public WebElement getAlreadyUserLoginElement() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
	}

	public WebElement getEmailElement() {
		return driver.findElement(By.id("email"));
	}
	
	public WebElement getEmailPasswordElement() {
		return driver.findElement(By.id("passwd"));
	}
	
	public WebElement getSubmitLoginElement() {
		return driver.findElement(By.id("SubmitLogin"));
	}
	
	public void takeScreenShot(File destFile) {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			logger.info("Error copying screenshot to file...");
		}
	}
	
	public void closeWebDriver() {
		driver.close();
	}
}
