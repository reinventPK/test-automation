package com.hellofresh.commonutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverFactory {
	
	public static WebDriver driver = null;
	
	public static WebDriver getDriver(String driverType) {	
		if(driverType.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			return driver;
		} else if(driverType.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
			driver.manage().window().maximize();
		    driver =  new FirefoxDriver();
			return driver;
		} else if(driverType.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer");
			driver =  new InternetExplorerDriver();
			driver.manage().window().maximize();
			return driver;
		}
		return driver;
	}

}
