package com.appium2.stepdefinitions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.appium2.core.Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.Before;


public class Hooks {
	
	@Before
	public void beforeScenario() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
		caps.setCapability(MobileCapabilityType.UDID, "1dbb7a42");
		caps.setCapability("appium:appPackage", "com.google.android.calculator");
		caps.setCapability("appium:appActivity", "com.android.calculator2.Calculator");
		caps.setCapability("appium:automationName", "UiAutomator2");
		try {
			AppiumDriver driver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			Base.driver = driver;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
