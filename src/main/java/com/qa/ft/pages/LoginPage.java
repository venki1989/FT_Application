package com.qa.ft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.ft.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	private By usrname = By.id("username");
	private By pwd = By.id("passwordHolder");
	private By loginButton = By.id("passwordLoginBtn");
	private By rememberMeCheckbox = By.id("rememberMe");
	private By forgotPasswordLink = By.linkText("Forgot Password?");
	private By troubleSigningInLink = By.linkText("Having trouble signing in?");
	private By usernameLabel = By.xpath("//label[@for='username']");
	private By passwordLabel = By.xpath("//label[@for='password']");
	private By vendorlink = By.xpath("//font[contains(text(), 'Vendors (Third-Party Risk Management) - Click here to access')]");

	public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleutil = new ElementUtil(driver);
    }

	public void enterUsername(String username) {
	 eleutil.doSendKeys(usrname, username);
	}

	public void enterPassword(String password) {
		eleutil.doSendKeys(pwd, password);
	}

	public void clickLoginButton() {
		eleutil.doActionsClick(loginButton);
	}

	public void checkRememberMe() {
		WebElement checkbox = eleutil.getElement(rememberMeCheckbox);
		if (!checkbox.isSelected()) {
			checkbox.click();
		}
	}

	public void uncheckRememberMe() {
		WebElement checkbox = eleutil.getElement(rememberMeCheckbox);
		if (checkbox.isSelected()) {
			checkbox.click();
		}
	}

	public void clickForgotPassword() {
		eleutil.getElement(forgotPasswordLink);
	}

	public void clickTroubleSigningIn() {
		eleutil.getElement(troubleSigningInLink);
	}

	public String getUsernameLabel() {
		return eleutil.doElementGetText(usernameLabel);
	}

	public String getPasswordLabel() {
		return eleutil.doElementGetText(passwordLabel);
	}

	public boolean isLoginButtonDisplayed() {
		return eleutil.doIsDisplayed(loginButton);
	}

	public boolean isRememberMeChecked() {
		return  eleutil.getElement(rememberMeCheckbox).isSelected();
	}

	public HomePage login(String username, String password) {
	    eleutil.waitForElementPresent(vendorlink, 5);
		eleutil.doClick(vendorlink);
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
		return new HomePage(driver);
		
	}

	public void loginWithRememberMe(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		checkRememberMe();
		clickLoginButton();
	}
}
