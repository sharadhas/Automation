package com.highwire.cochrane.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.highwire.cochrane.utils.BrowserDriver;

/**
 * 
 * @author Sharadha Sharma
 *
 */
public class TestUtil {
	public static WebDriver driver = null;
	public static Properties CONFIG = null;
	public static Properties OBJECT_REPOSITORY = null;
	public static boolean isLoggedIn = false;
	private static TestUtil testUtilInstance = null;
	private static final Logger LOGGER = Logger.getLogger(TestUtil.class.getName());

	private TestUtil() {
		initConfig();

		if (driver == null) {
			driver = BrowserDriver.getCurrentDriver();
			driver.get(OBJECT_REPOSITORY.getProperty("URL"));
		}
	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static TestUtil getInstance() {
		if (testUtilInstance == null) {
			testUtilInstance = new TestUtil();
		}
		return testUtilInstance;
	}

	/**
	 * This method loads the properties file having element locators.
	 */
	private void initConfig() {
		
	String orPath="\\src\\com\\highwire\\chochrane\\properties\\OR.properties";
	String configPath="\\src\\com\\highwire\\chochrane\\properties\\CONFIG.properties";
		OBJECT_REPOSITORY = new Properties();
		String path = System.getProperty("user.dir");
		File fileOr = new File(path + orPath);

		FileInputStream orFileInput = null;
		try {
			orFileInput = new FileInputStream(fileOr);
			OBJECT_REPOSITORY.load(orFileInput);
		} catch (IOException ioException) {
			LOGGER.log(Level.SEVERE, "Exception occured during loading of object repository");
			ioException.printStackTrace();
		}

		/*CONFIG = new Properties();
		File fileConfig = new File(path+configPath );

		FileInputStream configFileInput = null;
		try {
			configFileInput = new FileInputStream(fileConfig);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			CONFIG.load(configFileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	/**
	 * Accepts xpath as an argument and clicks on the relevant button
	 * 
	 * @param xpathKey
	 */
	public static void click(String xpathKey) {
		try {
			driver.findElement(By.xpath(OBJECT_REPOSITORY.getProperty(xpathKey))).click();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Accepts link text as a key and clicks on the relevant link
	 * 
	 * @param linkKey
	 */

	public static void clickLink(String linkKey) {
		try {
			driver.findElement(By.linkText(OBJECT_REPOSITORY.getProperty(linkKey))).click();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Accepts xpath and the text to be provided in the input box
	 * 
	 * @param xpathKey
	 * @param text
	 */

	
	public static void input(String xpathKey, String text) {
		try {
			driver.findElement(By.xpath(OBJECT_REPOSITORY.getProperty(xpathKey))).sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Accepts xpath as an argument and verifies if the element is present on
	 * the page
	 * 
	 * @param xpathKey
	 * @return
	 */

	public static boolean isElementPresent(String xpathKey) {
		boolean isElementPresent = true;
		try {
			driver.findElement(By.xpath(OBJECT_REPOSITORY.getProperty(xpathKey)));
		} catch (Exception tException) {
			isElementPresent = false;
			return isElementPresent;
		}

		return isElementPresent;
	}

	/**
	 * Verifies if the provided link is present on the page
	 * 
	 * @param linkText
	 * @return
	 */

	public static boolean isLinkPresent(String linkText) {
		boolean isElementPresent = true;
		try {
			driver.findElement(By.linkText(OBJECT_REPOSITORY.getProperty(linkText)));
		} catch (Exception e) {
			isElementPresent = false;
			e.printStackTrace();
			return isElementPresent;
		}

		return isElementPresent;
	}
	
	
	public static void switchtoFrame()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().activeElement();
		driver.switchTo().defaultContent();
		
	}

	public static boolean isLinkDisplayed(String linkText) {
		boolean isLinkDisplayed = false;
		try {
			isLinkDisplayed = driver.findElement(By.linkText(OBJECT_REPOSITORY.getProperty(linkText))).isDisplayed();

		} catch (Exception tEx) {
			tEx.printStackTrace();
			return isLinkDisplayed;
		}

		return isLinkDisplayed;
	}

	public boolean isElementDisplayed(String xpathKey) {
		boolean isELementDisplayed = false;
		try {
			isELementDisplayed = driver.findElement(By.xpath(xpathKey)).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			return isELementDisplayed;
		}
		return isELementDisplayed;
	}

	/**
	 * Accepts a filename as an argument and creates a file with the screenshot
	 * 
	 * @param fileName
	 */

	public static void takeScreenshot(String fileName) {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\screenshots\\" + fileName));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/**
	 * loads the page with provided url
	 * 
	 * @param url
	 */

	public static void loadPage(String url) {
		BrowserDriver.getCurrentDriver();
		LOGGER.info("Java version: " + Runtime.class.getPackage().getImplementationVersion());
		LOGGER.info("Directing browser to:" + url);

		BrowserDriver.getCurrentDriver().get(url);
	}

	/**
	 * returns the title of the page
	 * 
	 * @return
	 */

	public static String getPageTitle() {
		return BrowserDriver.getCurrentDriver().getTitle();
	}

	/**
	 * reload the page with specified url
	 * 
	 * @param url
	 */
	public static void reopenAndLoadPage(String url) {
		driver = BrowserDriver.getCurrentDriver();
		driver = null;
		loadPage(url);
	}

	/**
	 * 
	 * @param element
	 * @return
	 */

	public static WebElement getParent(WebElement element) {
		return element.findElement(By.xpath(".."));
	}

	/**
	 * returns the dropdown options for the specified webelement
	 * 
	 * @param webElement
	 * @return
	 */

	public static List<WebElement> getDropDownOptions(WebElement webElement) {
		Select select = new Select(webElement);
		return select.getOptions();
	}

	/**
	 * 
	 * @param webElement
	 * @param value
	 * @return
	 */
	public static WebElement getDropDownOption(WebElement webElement, String value) {
		WebElement option = null;
		List<WebElement> options = getDropDownOptions(webElement);
		for (WebElement element : options) {
			if (element.getAttribute("value").equalsIgnoreCase(value)) {
				option = element;
				break;
			}
		}
		return option;
	}

	/**
	 * 
	 * @param eidentifier
	 * @param type
	 * @return
	 */
	public static WebElement getElementByIdentifier(String eidentifier, String type) {

		if (type == "CSS") {
			WebElement findElement = driver.findElement(By.cssSelector(eidentifier));
			return findElement;
		} else if (type == "ID") {
			return driver.findElement(By.id((eidentifier)));
		} else
			return null;

	}

	/**
	 * 
	 * @param element
	 * @param newElementCSS
	 * @param Identifier
	 */
	public static void performMouseOver_and_moveToNewElement(WebElement element, String newElementCSS,
			String Identifier) {
		Actions action = new Actions(driver);
		action.moveToElement(element).moveToElement(getElementByIdentifier(newElementCSS, Identifier)).click().build()
				.perform();

	}

	/**
	 * 
	 * @param xpath
	 */
	public static void performMouseClick(String xpath) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(xpath))).click().perform();
	}

	/**
	 * 
	 * @param ele
	 * @param val
	 */
	public static void selectDropDownValue(WebElement ele, String val) {
		try {
			LOGGER.info("Entering the drop down value");
			LOGGER.info(ele.toString() + val.toString());
			Select clickThis = new Select(ele);
			clickThis.selectByVisibleText(val);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selection from drop down failed");
		}
	}


	/**
	 * 
	 * @param table
	 * @return
	 */
	
	public static WebElement readSelectElementFromTable(WebElement table) {
		List<WebElement> rows = table.findElements(By.tagName("select"));
		// java.util.Iterator<WebElement> i = rows.iterator();
		// while (i.hasNext()) {
		// WebElement row = i.next();
		// System.out.println("*******************" + row.getTagName());
		// }
		return rows.get(0);
	}

	// candidate for generalisation
	/**
	 * 
	 * @param table
	 * @return
	 */
	
	public static WebElement readElementFromTable(WebElement table) {
		List<WebElement> rows = table.findElements(By.tagName("td"));
		// java.util.Iterator<WebElement> i = rows.iterator();
		// while (i.hasNext()) {
		// WebElement row = i.next();
		// System.out.println("*******************" + row.getTagName());
		// }
		return rows.get(0);
	}
}
