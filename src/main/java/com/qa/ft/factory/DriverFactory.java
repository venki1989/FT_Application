package com.qa.ft.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DriverFactory {

	public WebDriver driver; // public since this driver needs to be accessed by others
	public Properties prop;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static final Logger log = LogManager.getLogger(DriverFactory.class);
	public WebDriver Init_driver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("browser name : " + browserName);
		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			log.info(browserName + "is selected and intiated");
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());

		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();

		} else {
			System.out.println("Provide valid browser");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		return getDriver();

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;

		// mvn clean install -Denv="qa"
		String envName = System.getProperty("env");
		System.out.println("Running test on " + envName);
		if (envName == null) {
			System.out.println("running on default qa environment");
			try {
				ip = new FileInputStream("./src/test/resource/config/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resource/config/dev.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resource/config/uat.config.properties");
					break;
				default:
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
