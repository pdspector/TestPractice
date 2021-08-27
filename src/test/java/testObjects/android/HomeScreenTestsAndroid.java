package testObjects.android;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import pageObjects.android.BottomMenuPageAndroid;
import pageObjects.android.HomeScreenPageAndroid;
import resources.BaseMethods;
import resources.android.AndroidBaseMethods;
import testObjects.BaseTest;

public class HomeScreenTestsAndroid extends BaseTest {

	private HomeScreenPageAndroid homePage;
	private BottomMenuPageAndroid bottomMenu;
	private String newListName = "My shopping list";
	private String[] items = new String[] {"Apples", "Apricots", "Bananas"};
	private List<String> deletedItemsText = new ArrayList<String>();
	private List<MobileElement> itemsList = new ArrayList<MobileElement>();
	private List<MobileElement> itemsCheckboxesList = new ArrayList<MobileElement>();
	
	@BeforeMethod
    public void beforeMethod(Method method, ITestContext context) {
		androidBase = new AndroidBaseMethods();
		homePage = new HomeScreenPageAndroid(driver);
    }
	
	@SuppressWarnings("deprecation")
	@Test
	public void aCreateANewShoppingList() {
		homePage.tapOnElement(homePage.getBottomMenu());
		bottomMenu = new BottomMenuPageAndroid(driver);
		bottomMenu.tapOnElement(bottomMenu.getNewListButton());
		try {
			bottomMenu.writeTextOnElement(bottomMenu.getInputNewListName(), newListName);
		} catch (Exception e) {
			driver.getKeyboard().sendKeys(newListName);
		}
		bottomMenu.tapOnElement(bottomMenu.getAddListButton());		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void bAddItemsToShoppingList() {
		for (int i = 0; i < items.length; i++) {
			homePage.tapOnElement(homePage.getNewItemButton());
			try {
				homePage.writeTextOnElement(homePage.getInputNewItemName(), items[i]);
			} catch (Exception e) {
				driver.getKeyboard().sendKeys(items[i]);
			}
			homePage.tapOnElement(homePage.getAddItemButton());
		}
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Test
	public void cEditAllItemsAndAddQuantityToBuy() {
		itemsList = (List<MobileElement>) ((AndroidDriver<?>) driver).findElements(homePage.getItemListButtons());
		for (int i = 0; i < itemsList.size(); i++) {
			itemsList.get(i).click();
			homePage.tapOnElement(homePage.getEditItemButton());
			@SuppressWarnings("unused")
			String currentName = homePage.getTextOfElement(homePage.getInputEditItemName()).replaceAll(", Write item name here...",  "");
			String newName = " " + (int) ((Math.random()*10) + 1);
			
			try {
				homePage.clearTextOfElement(homePage.getInputEditItemName());
				homePage.writeTextOnElement(homePage.getInputEditItemName(), newName);
			} catch (Exception e) {
				driver.getKeyboard().sendKeys(newName);
				driver.hideKeyboard();
			}
			homePage.tapOnElement(homePage.getSaveEditItemNameButton());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void dDeleteTwoRandomItems() {
		for (int i = 0; i < 2; i++) {
			itemsList = (List<MobileElement>) ((AndroidDriver<?>) driver).findElements(homePage.getItemListButtons());
			MobileElement item = itemsList.get(1);
			item.click();
			homePage.tapOnElement(homePage.getEditItemButton());
			deletedItemsText.add(homePage.getTextOfElement(homePage.getInputEditItemName()).replaceAll(", Write item name here...",  ""));
			homePage.tapOnElement(homePage.getCancelEditItemNameButton());
			homePage.tapOnElement(homePage.getRemoveItemButton());
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void eVerifyDeletedItemsNoLongerDisplay() {
		boolean isVisible1 = false;
		boolean isVisible2 = false;
		try {
			WebElement element = driver.findElement(By.xpath("//*[contains(@text, '" + deletedItemsText.get(0) + "')]"));
			isVisible1 = true;
		} catch (Exception e) {
			isVisible1 = false;
		}
		try {
			WebElement element = driver.findElement(By.xpath("//*[contains(@text, '" + deletedItemsText.get(1) + "')]"));
			isVisible2 = true;
		} catch (Exception e) {
			isVisible2 = false;
		}
		Assert.assertTrue((isVisible1 == false) && (isVisible2 == false), 
				"Removed Item Elements were found after delete.");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void fCheckAllItems() {
		itemsCheckboxesList = (List<MobileElement>) ((AndroidDriver<?>) driver).findElements(homePage.getItemsCheckboxes());
		for (int i = 0; i < itemsCheckboxesList.size(); i++) {
			itemsCheckboxesList.get(i).click();
		}
	}
	
	@Test
	public void gVerifyItemsAreChecked() {
		for (int i = 0; i < itemsCheckboxesList.size(); i++) {
			Assert.assertTrue(itemsCheckboxesList.get(i).getAttribute("checked").contains("true"),
					"Non checked items were found. ");
		}
	}
	
	@Test
	public void hArchiveTheCompletedList() {
		homePage.tapOnElement(homePage.getThreeDotButton());
		homePage.tapOnElement(homePage.getArchiveListButton());
	}
	
	@Test
	public void iSwipeToArchivedListAndDeleteIt() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		homePage.tapOnElement(homePage.getBottomMenu());
		bottomMenu.androidSwipeFromElementTo(bottomMenu.getEmptyShoppingListImage(), "RIGHT", 1);
		@SuppressWarnings("rawtypes")
		TouchAction touchAction = new TouchAction(driver);
		Dimension screenDim = driver.manage().window().getSize();
		int x = (int)(0.95 * screenDim.getWidth());
		int y = (int)(0.28 * screenDim.getHeight());
		touchAction.tap(PointOption.point(x, y)).perform();
		//bottomMenu.tapOnElement(bottomMenu.getArchivedListButton("Show menu"));
		bottomMenu.tapOnElement(bottomMenu.getDeleteArchivedListtButton("Delete"));
		bottomMenu.tapOnElement(bottomMenu.getConfirmDeleteButton());
	}
	
	@SuppressWarnings("unused")
	@Test
	public void jVerifyListIsNoLongerDisplayed() {
		boolean isVisible1 = true;
		boolean isVisible2 = false;
		try {
			WebElement element = driver.findElement(By.xpath("//*[contains(@text, '" + newListName + "')]"));
		} catch (Exception e) {
			isVisible1 = false;
		}
		try {
			androidBase.isElementDisplayed((By) MobileBy.AccessibilityId("There are no archived lists."), 5, driver);
			isVisible2 = true;
		} catch (Exception e) {

		}
		Assert.assertTrue((isVisible1 == false) && isVisible2, 
				"Archived List was not empty.");
	}
	
	@AfterMethod
    public void afterMethod(ITestResult iTestResult) throws Exception {
		if (iTestResult.getStatus() != 1) {
	        String filename = iTestResult.getMethod().getMethodName();
	        if (iTestResult.getStatus() == 3) {
	        	filename = filename + "Skipped";
	        }
	        base = new BaseMethods();
	        base.getScreenshot(driver, filename);
	        if (iTestResult.getMethod().getMethodName().contains("jVerifyListIsNoLongerDisplayed") == false ) {
	        	restartIfFailed();
	        }
		}
	}
}
	