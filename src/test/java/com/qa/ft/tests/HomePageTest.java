package com.qa.ft.tests;

import java.time.Duration;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.ft.base.BaseTest;

public class HomePageTest extends BaseTest {

	@BeforeClass //Before class : Perform this before any @Test methods
	public void pdmspageSetup() {
		homepage =loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

//	@Test
//	public void viewPdmsInfocenter() {
//		System.out.println(homepage.getGlobalMenuText());
//	}

	@Test
	public void clickglobalText() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		homepage.clickPDMS();

	}
}
