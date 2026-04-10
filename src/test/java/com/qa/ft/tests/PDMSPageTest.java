package com.qa.ft.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.ft.base.BaseTest;

public class PDMSPageTest extends BaseTest {
	
	
	@BeforeClass //Before class : Perform this before any @Test methods
	public void pdmspageformSetup() {
		homepage=loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println(homepage.getGlobalMenuText());
	}

	@Test
	public void viewlogo() {
		pdmspage.isLogoDisplayed();
	}

}
