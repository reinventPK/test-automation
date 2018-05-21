package com.hellofresh.commonutils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteWebDriverFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(RemoteWebDriverFactory.class);

	RemoteWebDriver driver = null;
	DesiredCapabilities decap = null;
	
	public RemoteWebDriver getDriver(String browserName, String remoteUrl) {
		
		if(browserName.equals("firefox")) {	
			decap = new DesiredCapabilities().firefox();
			decap.setBrowserName("firefox");
			decap.setPlatform(Platform.WIN10);
			
			try {
				driver = new RemoteWebDriver(new URL(remoteUrl), decap);
			} catch (MalformedURLException e) {
				logger.error("Remote URL is incorrect"+e);
			}
		} else if(browserName.equals("chrome")) {
			decap = new DesiredCapabilities().chrome();
			decap.setBrowserName("chrome");
			decap.setPlatform(Platform.MAC);
			
			try {
				driver = new RemoteWebDriver(new URL(remoteUrl), decap);
			} catch (MalformedURLException e) {
				logger.error("Remote URL is incorrect"+e);
			}
		} else if(browserName.equals("ie")) {
			decap = new DesiredCapabilities().internetExplorer();
			decap.setBrowserName("ie");
			decap.setPlatform(Platform.WIN10);
			
			try {
				driver = new RemoteWebDriver(new URL(remoteUrl), decap);
			} catch (MalformedURLException e) {
				logger.error("Remote URL is incorrect"+e);
			}
		}
		return driver;
	}

}
