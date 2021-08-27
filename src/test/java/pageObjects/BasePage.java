package pageObjects;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pageObjects.android.HomeScreenPageAndroid;
import resources.android.AndroidBaseMethods;

public class BasePage {

	protected static AppiumDriver<?> driver = null;
	protected static String device = null;
	protected static String platform = null;
	protected static String platformVersion = null;
	protected AndroidBaseMethods androidBase;
	protected HomeScreenPageAndroid homePage;
	protected Map<String, String[]> copy = new LinkedHashMap<String, String[]>();
	protected Map<String, String[]> mobileElements = new LinkedHashMap<String, String[]>();
	
	public BasePage(AppiumDriver<?> driver) {
		initPageObject(driver);
	}
	
	public By getByElement(String[] element) {
		By returnedElement = By.id("null");
		switch(element[0]) {
		case "id":
			returnedElement = By.id(element[1]);
			break;
		case "className":
			returnedElement = By.className(element[1]);
			break;
		case "xpath":
			returnedElement = By.xpath(element[1]);
			break;
		case "name":
			returnedElement = By.name(element[1]);
			break;
		case "cssSelector":
			returnedElement = By.cssSelector(element[1]);
			break;
		}
		return returnedElement;
	}
	
	public By getByElementFromControlledList(String element1) {
		By returnedElement = By.id("null");
		if (mobileElements.size() > 0) {
			String[] element = new String[] {"null", "null"};
			element = mobileElements.get(element1);
			switch(element[0]) {
			case "id":
				returnedElement = By.id(element[1]);
				break;
			case "className":
				returnedElement = By.className(element[1]);
				break;
			case "xpath":
				returnedElement = By.xpath(element[1]);
				break;
			case "name":
				returnedElement = By.name(element[1]);
				break;
			case "cssSelector":
				returnedElement = By.cssSelector(element[1]);
				break;
			}
		}
		return returnedElement;
	}
	
	public By getByElementFromDefaultList(String element1) {
		By returnedElement = By.id("null");
		if (copy.size() > 0) {
			String[] element = new String[] {"null", "null"};
			element = copy.get(element1);
			switch(element[0]) {
			case "id":
				returnedElement = By.id(element[1]);
				break;
			case "className":
				returnedElement = By.className(element[1]);
				break;
			case "xpath":
				returnedElement = By.xpath(element[1]);
				break;
			case "name":
				returnedElement = By.name(element[1]);
				break;
			case "cssSelector":
				returnedElement = By.cssSelector(element[1]);
				break;
			}
		}
		return returnedElement;
	}
	
	private void initPageObject(AppiumDriver<?> driver) {
		BasePage.driver = driver;
		BasePage.device = (String) driver.getCapabilities().getCapability("deviceName");
		BasePage.platform = (String) driver.getCapabilities().getCapability("platformName");
		BasePage.platformVersion = (String) driver.getCapabilities().getCapability("platformVersion");
	}
	
	public By checkIfElementIsDisplayed(By by, int time) {
		androidBase = new AndroidBaseMethods();
		androidBase.isElementDisplayed(by, time, driver);
		return by;
	}
	
	public By checkIfElementIsDisplayed(By by, int time, boolean isWebElement) {
		androidBase = new AndroidBaseMethods();
		androidBase.isElementDisplayed(by, time, driver, isWebElement);
		return by;
	}
	
	public By checkIfElementIsPresent(By by, int time) {
		androidBase = new AndroidBaseMethods();
		androidBase.isElementPresent(by, time, driver);
		return by;
	}
	
	public boolean isElementDisplayed(By by, int time) {
		androidBase = new AndroidBaseMethods();
		boolean isVisible = false;
		try {
			androidBase.isElementDisplayed(by, time, driver);
			isVisible = true;
		} catch (Exception e) {
			isVisible = false;
		}
		return isVisible;
	}
	
	public boolean isElementDisplayed(MobileElement element, int time) {
		androidBase = new AndroidBaseMethods();
		boolean isVisible = false;
		try {
			androidBase.isElementDisplayed(element, time, driver);
			isVisible = true;
		} catch (Exception e) {
			isVisible = false;
		}
		return isVisible;
	}
	
	public boolean isElementPresent(By by, int time) {
		androidBase = new AndroidBaseMethods();
		boolean isPresent = false;
		try {
			androidBase.isElementPresent(by, time, driver);
			isPresent = true;
		} catch (Exception e) {
			isPresent = false;
		}
		return isPresent;
	}
	
	public boolean isElementClickable(By by, int time) {
		androidBase = new AndroidBaseMethods();
		boolean isClickable = false;
		try {
			androidBase.isElementClickable(by, time, driver);
			isClickable = true;
		} catch (Exception e) {
			isClickable = false;
		}
		return isClickable;
	}
	
	public String getTextOfElement(By by) {
		return driver.findElement(by).getText();
	}
	
	public String getTextOfElement(By by, boolean isWebElement) {
		return driver.findElement(by).getText();
	}
	
	public String getTextOfElement(MobileElement element) {
		return element.getText();
	}
	
	public String getTextOfElement(WebElement element) {
		return element.getText();
	}
	
	public String getAttributeTextOfElement(MobileElement element, String attribute) {
		return element.getAttribute(attribute);
	}
	
	public String getAttributeTextOfElement(By by, String attribute) {
		return driver.findElement(by).getAttribute(attribute);
	}
	
	public void tapOnElement(By by) {
		driver.findElement(by).click();
	}
	
	public void tapOnElement(MobileElement element) {
		element.click();
	}
	
	public void tapOnElement(WebElement element) {
		element.click();
	}
	
	public void tapOnElement(By by, boolean isWebElement) {
		driver.findElement(by).click();
	}
	
	public void tapOnElement(By by, By by2, int time) {
		androidBase = new AndroidBaseMethods();
		driver.findElement(by).click();
		try {
			androidBase.isElementPresent(by2, time, driver);
		} catch (Exception e) {
			driver.findElement(by).click();
			androidBase.isElementPresent(by2, time, driver);
		}
	}
	
	public void tapOnElement(MobileElement element, By by, int time) {
		androidBase = new AndroidBaseMethods();
		element.click();
		androidBase.isElementPresent(by, time, driver);
	}
	
	public void writeTextOnElement(By by, String text) {
		driver.findElement(by).sendKeys(text);
	}
	
	public void writeTextOnElement(MobileElement element, String text) {
		element.sendKeys(text);
	}
	
	public void clearTextOfElement(By by) {
		driver.findElement(by).clear();
	}
	
	public void clearTextOfElement(MobileElement element) {
		element.clear();
	}
	
	public void androidSwipeFromElementTo(By by, String direction, int times) {
		androidBase = new AndroidBaseMethods();
		androidBase.swipeElementAndroid((MobileElement) driver.findElement(by), direction, (AndroidDriver<?>) driver, times);
	}
	
	public void androidSwipeFromElementTo(MobileElement element, String direction, int times) {
		androidBase = new AndroidBaseMethods();
		androidBase.swipeElementAndroid(element, direction, (AndroidDriver<?>) driver, times);
	}
	
	public void androidSwipeFromElementTo(By by, String direction, int times, int reducedWidth) {
		androidBase = new AndroidBaseMethods();
		androidBase.swipeElementAndroid((MobileElement) driver.findElement(by), direction, (AndroidDriver<?>) driver, times, reducedWidth);
	}
	
	public void androidSwipeFromElementTo(MobileElement element, String direction, int times, int reducedWidth) {
		androidBase = new AndroidBaseMethods();
		androidBase.swipeElementAndroid(element, direction, (AndroidDriver<?>) driver, times, reducedWidth);
	}
	
	public boolean isRightScreen() {
		return false;
	}
	
	public void goToNextPage(String flow) {
		
	}
}
