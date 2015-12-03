package generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlSuite;

import pages.Login;

public class BaseTest {
	protected static WebDriver driver;
	protected XmlSuite xmlSuite;
	protected String browser, filePath, executionType, url, platform,
			hubAddress, userName, password;
	protected String waitingTime = "10";

	Properties prop = new Properties();
	InputStream input = null;

	// Constructor
	public BaseTest() {

	}

	@BeforeMethod
	public void setUp() {

		driver.get(url);

	}

	@AfterMethod
	public void tearDown() {
		// TODO Logout
	}

	@BeforeSuite
	public void suiteSetUp(ITestContext context) {
		// getXMLParameters(context);
	}

	@BeforeClass
	public void classSetUp(ITestContext context) {
		getXMLParameters(context);
		driverSetUp();

	}

	@AfterClass
	public static void classTearDown() {
		// driver.quit();
	}

	@FindBy(id = "navbarDrop1")
	public WebElement userDrop;

	@FindBy(xpath = "//*[@id='navigationUserName']/ul/li/a")
	public WebElement logOut;

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	protected void getXMLParameters(ITestContext context) {
		this.xmlSuite = context.getCurrentXmlTest().getSuite();
		this.browser = xmlSuite.getParameter("browser");
		this.filePath = xmlSuite.getParameter("filePath");
		this.url = xmlSuite.getParameter("url");
		this.executionType = xmlSuite.getParameter("type");
		this.platform = xmlSuite.getParameter("platform");
		this.hubAddress = xmlSuite.getParameter("hubAddress");
		this.userName = xmlSuite.getParameter("userName");
		this.password = xmlSuite.getParameter("password");
		this.waitingTime = xmlSuite.getParameter("waitingTime");
	}

	private void driverSetUp() {

		String profileLocation = ""; // TODO Read path from config file

		// For local execution
		System.out.println("executionType=" + executionType);
		if (executionType.equalsIgnoreCase("Local")) {
			if (browser.equalsIgnoreCase("Firefox")) {
				if (profileLocation.equalsIgnoreCase("")) {
					FirefoxProfile profile = new FirefoxProfile();
					profile.setEnableNativeEvents(true);
					driver = new FirefoxDriver(profile);
				} else {
					FirefoxProfile profile;
					File profileDir = new File(profileLocation);
					profile = new FirefoxProfile(profileDir);
					driver = new FirefoxDriver(profile);
				}
			} else if (browser.equalsIgnoreCase("InternetExplorer")) {
				DesiredCapabilities cap = DesiredCapabilities
						.internetExplorer();
				cap.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				cap.setCapability("ignoreZoomSetting", true);
				System.setProperty("webdriver.ie.driver", filePath);
				driver = new InternetExplorerDriver(cap);
			} else if (browser.equalsIgnoreCase("Chrome")) { // Refer to
																// http://code.google.com/p/selenium/wiki/ChromeDriver
																// for
																// additional
																// config info
				System.setProperty("webdriver.chrome.driver", filePath);
				driver = new ChromeDriver();
				driver.manage().window().maximize();

			} else if (browser.equalsIgnoreCase("Safari")) { // Refer to
																// http://code.google.com/p/selenium/wiki/SafariDriver
																// for
																// additional
																// config info
				DesiredCapabilities cap = DesiredCapabilities.safari();
				driver = new SafariDriver(cap);
			} else
				driver = new HtmlUnitDriver();

			// System.out.println("[ERROR] The provided browser was invalid");

		}

		// For execution on a Selenium Grid
		else if (executionType.equals("Remote")) {
			// Browser Setup
			DesiredCapabilities capability = null;

			if (browser.equalsIgnoreCase("Firefox"))
				capability = DesiredCapabilities.firefox();
			else if (browser.equalsIgnoreCase("InternetExplorer"))
				capability = DesiredCapabilities.internetExplorer();
			else if (browser.equalsIgnoreCase("Chrome"))
				capability = DesiredCapabilities.chrome();

			else if (browser.equalsIgnoreCase("Safari"))
				capability = DesiredCapabilities.safari();
			else
				System.out.println("[ERROR] The provided browser was invalid");

			// Platform Setup
			if (platform.equalsIgnoreCase(""))
				capability.setPlatform(Platform.ANY);
			else {
				if (platform.equalsIgnoreCase("LINUX"))
					capability.setPlatform(Platform.LINUX);
				else if (platform.equalsIgnoreCase("UNIX"))
					capability.setPlatform(Platform.UNIX);
				else if (platform.equalsIgnoreCase("MAC"))
					capability.setPlatform(Platform.MAC);
				else if (platform.equalsIgnoreCase("ANDROID"))
					capability.setPlatform(Platform.ANDROID);
				else if (platform.equalsIgnoreCase("VISTA"))
					capability.setPlatform(Platform.VISTA);
				else if (platform.equalsIgnoreCase("WINDOWS"))
					capability.setPlatform(Platform.WINDOWS);
				else if (platform.equalsIgnoreCase("XP"))
					capability.setPlatform(Platform.XP);
				else if (platform.equalsIgnoreCase("ANY"))
					capability.setPlatform(Platform.ANY);
			}

			// Grid Hub Setup
			try {
				if (hubAddress.equalsIgnoreCase(""))
					driver = new RemoteWebDriver(new URL(
							"http://192.168.56.1:4444/wd/hub"), capability);
				else
					driver = new RemoteWebDriver(new URL("http://" + hubAddress
							+ ":4444/wd/hub"), capability);
			} catch (MalformedURLException e) {
				System.out
						.println("[ERROR] There was a problem setting up the  WebDriver Grid hub");
			}
		} else
			System.out
					.println("[ERROR] The provided execution type was invalid");

	}

	public void waitTime() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

	}

	public void customeWaitTime(int seconds) {

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

	}

	public WebDriver emulateDevice(String device) {

		driver.quit();
		waitTime();
		// ////
		System.setProperty("webdriver.chrome.driver", filePath);
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", device);

		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		driver = new ChromeDriver(capabilities);

		driver.manage().window().maximize();
		// //////
		return driver;

	}

	public WebDriver chromeDriver() {

		System.setProperty("webdriver.chrome.driver", filePath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public Login logOut() {

		userDrop.click();
		logOut.click();

		return new Login(driver);
	}

	public void returnToDashboard() {

		driver.findElement(By.xpath("//*[@id='region-navigation']/div/a"))
				.click();

	}

	public Properties getUnityMessagesProperty(String unitymessageFileLoc) {

		try {
			input = new FileInputStream(unitymessageFileLoc);
			// load a properties file
			prop.load(input);
		} catch (Exception e) {

			System.out
					.println("Getting error while reading unity message property");

		}
		return prop;

	}
}
