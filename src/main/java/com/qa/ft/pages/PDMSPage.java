package com.qa.ft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.ft.utils.ElementUtil;

public class PDMSPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	private By globalicon = By.xpath("//a[@data-title='Global Menu']");

	public PDMSPage(WebDriver driver) {
		eleutil = new ElementUtil(driver);
	}
	
	
	
	public PDMSPage isLogoDisplayed() {
		eleutil.doIsDisplayed(globalicon);
		return new PDMSPage(driver);
	}

}
