package com.qa.ft.tests;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.ft.base.BaseTest;
import com.qa.ft.factory.DriverFactory;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class LoginPageTest extends BaseTest{
	
	public static final Logger log = LogManager.getLogger(LoginPageTest.class);
	@Test
	@Epic("Login test")
	@Description("Login page test")
	@Severity(SeverityLevel.CRITICAL)
	public void doLogin()
	{
		homepage=loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Assert.assertTrue(homepage.isLogoDisplayed());
		log.info("Logo is displayed");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
}
