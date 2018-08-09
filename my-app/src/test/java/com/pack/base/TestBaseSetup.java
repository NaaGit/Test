package com.pack.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.config.Config;

/**
 * @author Bhargavi
 *
 */
public class TestBaseSetup {
	Config config = new Config();
	public WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browserType, String appURL) throws IOException {
		System.out.println("In setDriver()");
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}

	public WebDriver initChromeDriver(String appURL) throws IOException {
		System.out.println("Launching google chrome with new profile..");

		// try {
		System.setProperty("webdriver.chrome.driver", config.getPropValues("driverpath") + "chromedriver.exe");

		/*
		 * DesiredCapabilities capability = DesiredCapabilities.chrome();
		 * capability.setBrowserName("chrome");
		 * capability.setPlatform(Platform.WINDOWS); driver = new RemoteWebDriver(new
		 * URL("http://192.168.11.215:5557/wd/hub"), capability);
		 * 
		 * Reporter.log("Opening remote webdriver ",true); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

		Reporter.log("Set the property succesfully for chrome driver", true);
		driver = new ChromeDriver();
		Reporter.log("Opening chrome driver", true);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		Reporter.log("Opening the URL ", true);
		driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
		return driver;
	}

	public WebDriver initFirefoxDriver(String appURL) throws IOException {
		// try {
		System.setProperty("webdriver.gecko.driver", config.getPropValues("driverpath") + "geckodriver.exe");
		/*
		 * DesiredCapabilities capability = DesiredCapabilities.firefox();
		 * capability.setBrowserName("firefox");
		 * capability.setPlatform(Platform.WINDOWS);
		 * capability.setCapability("browser.startup.homepage", "about:blank");
		 * capability.setCapability("startup.homepage_welcome_url", "about:blank");
		 * capability.setCapability("startup.homepage_welcome_url.additional",
		 * "about:blank"); System.out.println("Launching Remote Firefox browser..");
		 * driver = new RemoteWebDriver(new URL("http://192.168.11.215:5544/wd/hub"),
		 * capability);
		 * 
		 * } catch (IOException e) {
		 * System.out.println("Error...opening Firefoxdriver"); e.printStackTrace(); }
		 */
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			setDriver(browserType, appURL);
		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	/*
	 * @AfterClass public void tearDown() { driver.quit(); }
	 */

}
