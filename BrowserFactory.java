package com.highwire.cochrane.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

/**
 * 
 * @author Sharadha Sharma
 *
 */
public class BrowserFactory {

	private static final Logger LOGGER = Logger.getLogger(BrowserFactory.class.getName());
	static String USER_DIR = System.getProperty("user.dir");
	public static Properties CONFIG = null;
	static WebDriver driver;

	/**
	 * creates the browser driver specified in the system property "browser" if
	 * no property is set then a firefox browser driver is created. The allow
	 * properties are firefox, safari and chrome e.g to run with safari, pass in
	 * the option -Dbrowser=safari at runtime
	 *
	 * @return WebDriver
	 */

	public static WebDriver getBrowser() {

		initConfig();

		String browser = CONFIG.getProperty("BROWSER");

		switch (browser.toLowerCase()) {

		case "chrome":
			LOGGER.log(Level.INFO, "Trying to open chrome driver");
			driver = createChromeDriver();
			break;
		case "ie32":
			LOGGER.log(Level.INFO, "Trying to open IE32 driver");
			driver = createIE32Driver();
			break;
		case "ie64":
			LOGGER.log(Level.INFO, "Trying to open IE64 driver");
			driver = createIE64Driver();
			break;
		case "safari":
			LOGGER.log(Level.INFO, "Trying to open Safari driver");
			driver = createSafariDriver();
			break;
		case "firefox":
			driver = createFirefoxDriver();
			break;
		default:
			LOGGER.log(Level.INFO, "Trying to open Firefox driver");
			driver = createFirefoxDriver();
			break;
		}
		addAllBrowserSetup(driver);
		return driver;
	}

	private static void initConfig() {
		CONFIG = new Properties();
		String CONFIG_PATH = "\\src\\com\\highwire\\chochrane\\properties\\Config.properties";
		File file = new File(USER_DIR + CONFIG_PATH);

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			CONFIG.load(fileInput);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static WebDriver createFirefoxDriver() {
		String GECKO_DRIVER_PROP = "webdriver.gecko.driver";
		String PATH_GECKO_DRIVER_EXE = "\\src\\com\\highwire\\chochrane\\resources\\geckodriver.exe";
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("capability.policy.default.Window.Quer‌​yInterface", "allAccess");
		profile.setPreference("capability.policy.default.Window.fram‌​eElement.get", "allAc‌​cess");
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		System.setProperty(GECKO_DRIVER_PROP, USER_DIR + PATH_GECKO_DRIVER_EXE);
		driver = new FirefoxDriver(capabilities);
		LOGGER.log(Level.INFO, "Trying to open FIREFOX driver");
		return driver;
	}

	private static WebDriver createSafariDriver() {
		return new SafariDriver();
	}

	private static WebDriver createChromeDriver() {
		String CHROME_DRIVER_PROP = "webdriver.chrome.driver";
		String PATH_CHROME_DRIVER_EXE = "\\src\\com\\highwire\\chochrane\\resources\\chromedriver.exe";

		System.setProperty(CHROME_DRIVER_PROP, USER_DIR + PATH_CHROME_DRIVER_EXE);
		driver = new ChromeDriver();

		LOGGER.log(Level.INFO, "Trying to open Chrome driver");
		return driver;
	}

	private static WebDriver createIE32Driver() {	
		String IE_DRIVER_PROP = "webdriver.ie.driver";
		String PATH_IE_DRIVER_EXE = "\\src\\com\\highwire\\chochrane\\resources\\IEDriverServer.exe";

		System.setProperty(IE_DRIVER_PROP,USER_DIR+PATH_IE_DRIVER_EXE);
		return new InternetExplorerDriver();
	}

	private static WebDriver createIE64Driver() {
		String IEDriverName;
		if ((System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)) {
			IEDriverName = "IEDriverServer64.exe";
		} else {
			IEDriverName = "IEDriverServer64";
		}
		StringBuffer sb = new StringBuffer();
		String path = BrowserFactory.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		sb.append(path).append(File.separatorChar).append(IEDriverName);
		System.setProperty("webdriver.ie.driver", sb.toString());
		return new InternetExplorerDriver();
	}

	private static void addAllBrowserSetup(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().maximize();
	}

}
