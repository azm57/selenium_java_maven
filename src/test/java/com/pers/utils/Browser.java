package com.pers.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Optional;

import java.io.IOException;

public class Browser {

	public static WebDriver Driver;
	private static ThreadLocal<WebDriver> ThreadDriver = null;
	public static String baseUrl;
//	public static String username;
//	public static String password;
	public static String intMinWait;
	public static String intMidWait;
	public static long intMaxWait;
	public static long strPollingEvery;
	public static String browserHandle;

	public static void IntilizeBrowser(String strBrowserType) throws IOException {

		try {
			ReadPropertiesData();

			// Thread local is the class which creates separate instance of
			// webdriver for every thread.
			ThreadDriver = new ThreadLocal<>();
			Driver = ThreadDriver.get();

			if (Driver == null) {
				if (strBrowserType.equalsIgnoreCase("Chrome")) {
					//System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--incognito", "--window-size=1920,1080");
					//options.addArguments("--incognito", "--window-size=1920,1080", "--headless=new");
					Driver = new ChromeDriver(options);
					ThreadDriver.set(Driver);
				} else if (strBrowserType.equalsIgnoreCase("IE")) {
					System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriver.exe");
					Driver = new InternetExplorerDriver();
					ThreadDriver.set(Driver);
				} else if (strBrowserType.equalsIgnoreCase("safari")) {
					System.setProperty("webdriver.ie.driver", "src/test/resources/SafariDriver.exe");
					Driver = new SafariDriver();
					// ThreadDriver.set(Driver);
				} else if (strBrowserType.equalsIgnoreCase("firefox")) {
					// String driverPath = "C://Users//" + System.getProperty("user.name")
					// + "//AppData//Local//Mozilla Firefox//firefox.exe";
					String driverPath = "C://Program Files//Mozilla Firefox//firefox.exe";
					System.setProperty("webdriver.firefox.bin", driverPath);
					Driver = new FirefoxDriver();
					ThreadDriver.set(Driver);
				}
			}
			NavigateToURL(baseUrl);
		} catch (WebDriverException we) {
			System.out.println(we.getMessage() + " Occurred....");
		}
	}

	public static WebDriver getDriver() {

		return ThreadDriver.get();
	}

	public static void NavigateToURL(String baseUrl) {
		Driver.navigate().to(baseUrl);
		Driver.manage().window().maximize();
		System.out.println(Driver.manage().window().getSize());
	}

	public static String strGetWindowTitle() {
		return Driver.getTitle();
	}

	public static void ReadPropertiesData() {
		try {

			String resourceName = "GenericConfig.properties";
			ConfigReader.readConfigFile(resourceName);
			intMinWait = ConfigReader.configMap.get("gMinWait");
			intMidWait = ConfigReader.configMap.get("gMidWait");
//			intMidWait = Integer.valueOf(ConfigReader.configMap.get("gMidWait"));
			intMaxWait = Long.valueOf(ConfigReader.configMap.get("gMaxWait"));
			strPollingEvery = Long.valueOf(ConfigReader.configMap.get("gPollTime"));
			baseUrl = ConfigReader.configMap.get("gAppURL");

		} catch (Exception e) {
			System.out.println(e.getMessage() + "occurred...");
		}

	}

	// @BeforeTest
	// @Parameters({"browser"})
	public static void setup(@Optional("Firefox") String browser) throws IOException {

		IntilizeBrowser(browser);
		browserHandle = getDriver().getWindowHandle();

	}

}
