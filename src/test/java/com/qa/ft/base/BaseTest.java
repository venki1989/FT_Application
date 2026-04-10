package com.qa.ft.base;


import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.ft.factory.DriverFactory;
import com.qa.ft.pages.HomePage;
import com.qa.ft.pages.LoginPage;
import com.qa.ft.pages.PDMSPage;

public class BaseTest {

	public DriverFactory df;
	public static WebDriver driver;
	public static LoginPage loginpage;
	public HomePage homepage;
	public PDMSPage pdmspage;
	public static Properties prop;

	@BeforeTest
	public void setup() {
		df = new DriverFactory();  //Object Created
		prop = df.init_prop();     //Call properties, initiated
		driver = df.Init_driver(prop); //driver selected based on properties and initiated
		loginpage = new LoginPage(driver); //driver given to the loginapge, create object
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
