package pageObjects.android;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import pageObjects.BasePage;

public class BottomMenuPageAndroid extends BasePage {
	
	private String[] newListButton = new String[] {"xpath", "(//android.widget.Button)[2]"};
	private String[] inputNewListName = new String[] {"xpath", "//android.widget.EditText"};
	private String[] addListButton = new String[] {"xpath", "(//android.widget.Button)[2]"};
	private String[] emptyShoppingListImage = new String[] {"xpath", "//android.widget.ImageView"};
	private String[] archivedListButton = new String[] {"xpath", "//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View"};
	private String[] deleteArchivedListButton = new String[] {"xpath", "(//android.widget.Button)[3]"};
	private String[] confirmDeletList = new String[] {"xpath", "(//android.widget.Button)[2]"};

	
	public BottomMenuPageAndroid(AppiumDriver<?> driver) {
		super(driver);
	}

	public void setNewListButtonLocator(String[] bottomMenu) {
		this.newListButton = bottomMenu;
	}

	public String[] getNewListButtonLocator() {
		return newListButton;
	}
	
	@Override
	public boolean isRightScreen() {
		return isNewListButtonVisible();
	}
	
	public By getNewListButton() {
		return checkIfElementIsDisplayed(getByElement(newListButton), 5);
	}
	
	public boolean isNewListButtonVisible() {
		return isElementDisplayed(getByElement(newListButton), 5);
	}

	public String[] getInputNewListNameLocator() {
		return inputNewListName;
	}

	public void setInputNewListNameLocator(String[] inputNewListName) {
		this.inputNewListName = inputNewListName;
	}
	
	public By getInputNewListName() {
		return checkIfElementIsDisplayed(getByElement(inputNewListName), 5);
	}
	
	public boolean isInputNewListNameVisible() {
		return isElementDisplayed(getByElement(inputNewListName), 5);
	}

	public String[] getAddListButtonLocator() {
		return addListButton;
	}

	public void setAddListButtonLocator(String[] addListButton) {
		this.addListButton = addListButton;
	}
	
	public By getAddListButton() {
		return checkIfElementIsDisplayed(getByElement(addListButton), 5);
	}
	
	public boolean isAddListButtonVisible() {
		return isElementDisplayed(getByElement(addListButton), 5);
	}
	
	public String[] getEmptyShoppingListImageLocator() {
		return emptyShoppingListImage;
	}

	public void setEmptyShoppingListImageLocator(String[] emptyShoppingListImage) {
		this.emptyShoppingListImage = emptyShoppingListImage;
	}
	
	public By getEmptyShoppingListImage() {
		return checkIfElementIsDisplayed(getByElement(emptyShoppingListImage), 5);
	}
	
	public boolean isEmptyShoppingListImageVisible() {
		return isElementDisplayed(getByElement(emptyShoppingListImage), 5);
	}

	public String[] getArchivedListButtonLocator() {
		return archivedListButton;
	}

	public void setArchivedListButtonLocator(String[] archivedListButton) {
		this.archivedListButton = archivedListButton;
	}
	
	public By getArchivedListButton(String id) {
		return checkIfElementIsDisplayed(MobileBy.AccessibilityId(id), 5);
	}
	
	public boolean isArchivedListButtonVisible(String id) {
		return isElementDisplayed(MobileBy.AccessibilityId(id), 5);
	}

	public String[] getDeleteArchivedListButtonLocator() {
		return deleteArchivedListButton;
	}

	public void setDeleteArchivedListButtonLocator(String[] deleteArchivedListButton) {
		this.deleteArchivedListButton = deleteArchivedListButton;
	}
	
	public By getDeleteArchivedListtButton(String id) {
		return checkIfElementIsDisplayed(MobileBy.AccessibilityId(id), 5);
	}
	
	public boolean isDeleteArchivedListtButtonVisible(String id) {
		return isElementDisplayed(MobileBy.AccessibilityId(id), 5);
	}

	public String[] getConfirmDeletListLocator() {
		return confirmDeletList;
	}

	public void setConfirmDeletListLocator(String[] confirmDeletList) {
		this.confirmDeletList = confirmDeletList;
	}
	
	public By getConfirmDeleteButton() {
		return checkIfElementIsDisplayed(getByElement(confirmDeletList), 5);
	}
	
	public boolean isConfirmDeleteButtonVisible() {
		return isElementDisplayed(getByElement(confirmDeletList), 5);
	}

}
