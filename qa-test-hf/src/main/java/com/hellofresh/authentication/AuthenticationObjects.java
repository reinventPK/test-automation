package com.hellofresh.authentication;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* ==============================================================
 * This class contains object repository for SignIn page. 
 * Each method will return respective element locator. Locators 
 * will be used in actual business logic to provide test data.  
 * ============================================================== */

public class AuthenticationObjects {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationObjects.class);
	private WebDriver driver = null;	
	private WebDriverWait wait = null; 
	
	public AuthenticationObjects(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	/* ==============================================================
	 * Methods to get Sign In functionality objects 
	 * ============================================================== */
	
	public WebElement getLoginElement() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
	}

	public WebElement getEmailCreateElement() {
		return driver.findElement(By.id("email_create"));
	}

	public WebElement getSubmitCreateElement() {
		return driver.findElement(By.id("SubmitCreate"));
	}

	public WebElement getIdGender2() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2")));
	}
	
	public WebElement getCustomerFirstNameElement() {
		return driver.findElement(By.id("customer_firstname"));
	}

	public WebElement getCustomerLastNameElement() {
		return driver.findElement(By.id("customer_lastname"));
	}

	public WebElement getPasswordElement() {
		return driver.findElement(By.id("passwd"));
	}
	
	public Select getDay() {
		return new Select(getDayElement());
	}

	public WebElement getDayElement() {
		return driver.findElement(By.id("days"));
	}
	
	public Select getMonth() {
		return new Select(getMonthElement());
	}
	
	public WebElement getMonthElement() {
		return driver.findElement(By.id("months"));
	}
	
	public Select getYear() {
		return new Select(getYearElement());
	}
	
	public WebElement getYearElement() {
		return driver.findElement(By.id("years"));
	}
	
	public WebElement getCompanyElement() {
		return driver.findElement(By.id("company"));
	}

	public WebElement getAddress1Element() {
		return driver.findElement(By.id("address1"));
	}

	public WebElement getAddress2Element() {
		return driver.findElement(By.id("address2"));
	}

	public WebElement getCityElement() {
		return driver.findElement(By.id("city"));
	}
	
	public Select getState() {
		return new Select(getStateElement());
	}

	public WebElement getStateElement() {
		return driver.findElement(By.id("id_state"));
	}
	
	public WebElement getPostalCodeElement() {
		return driver.findElement(By.id("postcode"));
	}

	public WebElement getOtherElement() {
		return driver.findElement(By.id("other"));
	}

	public WebElement getPhoneElement() {
		return driver.findElement(By.id("phone"));
	}

	public WebElement getPhoneMobileElement() {
		return driver.findElement(By.id("phone_mobile"));
	}

	public WebElement getAliasElement() {
		return driver.findElement(By.id("alias"));
	}

	public WebElement getSubmitAccountElement() {
		return driver.findElement(By.id("submitAccount"));
	}

	public WebElement getMyAccountHeadingElement() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
	}
	
	public WebElement getAccountElement() {
		return driver.findElement(By.className("account"));
	}

	public WebElement getAccountInfoElement() {
		return driver.findElement(By.className("info-account"));
	}

	public WebElement getLogoutElement() {
		return driver.findElement(By.className("logout"));
	}
	
	public void takeScreenShot(File file) {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, file);
		} catch (IOException e) {
			logger.info("Error copying screenshot to file...");
		}	
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	
	/* ==============================================================
	 * Methods to get Login functionality objects 
	 * ============================================================== */
	 
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
	
	public void closeWebDriver() {
		driver.close();
	}


}
