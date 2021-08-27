package pageObjects.android;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import pageObjects.BasePage;

public class HomeScreenPageAndroid extends BasePage {
	
	private String[] bottomMenu = new String[] {"xpath", "(//android.widget.ImageView/android.view.View)[2]"};
	private String[] newItemButton = new String[] {"xpath", "(//android.widget.Button)[2]"};
	private String[] inputNewItemName = new String[] {"xpath", "//android.widget.EditText"};
	private String[] addItemButton = new String[] {"xpath", "(//android.widget.Button)[2]"};
	private String[] itemListButton = new String[] {"xpath", "//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View"};
	private String[] removeItemButton = new String[] {"xpath", "(//android.widget.Button)[2]"};
	private String[] editItemButton = new String[] {"xpath", "(//android.widget.Button)[3]"};
	private String[] inputEditItemName = new String[] {"xpath", "//android.widget.EditText"};
	private String[] cancelEditItemButton = new String[] {"xpath", "(//android.widget.Button)[3]"};
	private String[] saveEditItemButton = new String[] {"xpath", "(//android.widget.Button)[4]"};
	private String[] itemsCheckboxes = new String[] {"xpath", "//android.widget.CheckBox"};
	private String[] threeDotButton = new String[] {"xpath", "(//android.widget.Button)[1]"};
	private String[] archiveListButton = new String[] {"xpath", "(//android.widget.Button)[1]"};


	public HomeScreenPageAndroid(AppiumDriver<?> driver) {
		super(driver);
	}

	public void setBottomMenuLocator(String[] bottomMenu) {
		this.bottomMenu = bottomMenu;
	}

	public String[] getBottomMenuLocator() {
		return bottomMenu;
	}
	
	@Override
	public boolean isRightScreen() {
		return isBottomMenuVisible();
	}
	
	public By getBottomMenu() {
		return checkIfElementIsDisplayed(getByElement(bottomMenu), 5);
	}
	
	public boolean isBottomMenuVisible() {
		return isElementDisplayed(getByElement(bottomMenu), 5);
	}

	public String[] getNewItemButtonLocator() {
		return newItemButton;
	}

	public void setNewItemButtonLocator(String[] newItemButton) {
		this.newItemButton = newItemButton;
	}
	
	public By getNewItemButton() {
		return checkIfElementIsDisplayed(getByElement(newItemButton), 5);
	}
	
	public boolean isNewItemButtonVisible() {
		return isElementDisplayed(getByElement(newItemButton), 5);
	}

	public String[] getInputNewItemNameLocator() {
		return inputNewItemName;
	}

	public void setInputNewItemNameLocator(String[] inputNewItemName) {
		this.inputNewItemName = inputNewItemName;
	}
	
	public By getInputNewItemName() {
		return checkIfElementIsDisplayed(getByElement(inputNewItemName), 5);
	}
	
	public boolean isInputNewItemNameVisible() {
		return isElementDisplayed(getByElement(inputNewItemName), 5);
	}

	public String[] getAddItemButtonLocator() {
		return addItemButton;
	}

	public void setAddItemButtonLocator(String[] addItemButton) {
		this.addItemButton = addItemButton;
	}
	
	public By getAddItemButton() {
		return checkIfElementIsDisplayed(getByElement(addItemButton), 5);
	}
	
	public boolean isAddItemButtonVisible() {
		return isElementDisplayed(getByElement(addItemButton), 5);
	}

	public String[] getItemListButtonLocator() {
		return itemListButton;
	}

	public void setItemListButtonLocator(String[] itemListButton) {
		this.itemListButton = itemListButton;
	}
	
	public By getItemListButtons() {
		return checkIfElementIsDisplayed(getByElement(itemListButton), 5);
	}
	
	public boolean isItemListButtonsVisible() {
		return isElementDisplayed(getByElement(itemListButton), 5);
	}

	public String[] getRemoveItemButtonLocator() {
		return removeItemButton;
	}

	public String[] getEditItemButtonLocator() {
		return editItemButton;
	}
	
	public By getRemoveItemButton() {
		return checkIfElementIsDisplayed(getByElement(removeItemButton), 5);
	}
	
	public boolean isRemoveItemButtonVisible() {
		return isElementDisplayed(getByElement(removeItemButton), 5);
	}
	
	public By getEditItemButton() {
		return checkIfElementIsDisplayed(getByElement(editItemButton), 5);
	}
	
	public boolean isEditItemButtonVisible() {
		return isElementDisplayed(getByElement(editItemButton), 5);
	}

	public void setEditItemButtonLocator(String[] editItemButton) {
		this.editItemButton = editItemButton;
	}

	public void setRemoveItemButtonLocator(String[] removeItemButton) {
		this.removeItemButton = removeItemButton;
	}

	public String[] getInputEditItemNameLocator() {
		return inputEditItemName;
	}

	public void setInputEditItemNameLocator(String[] inputEditItemName) {
		this.inputEditItemName = inputEditItemName;
	}
	
	public By getInputEditItemName() {
		return checkIfElementIsDisplayed(getByElement(inputEditItemName), 5);
	}
	
	public boolean isInputEditItemNameVisible() {
		return isElementDisplayed(getByElement(inputEditItemName), 5);
	}

	public String[] getCancelEditItemButtonLocator() {
		return cancelEditItemButton;
	}

	public void setCancelEditItemButtonLocator(String[] cancelEditItemButton) {
		this.cancelEditItemButton = cancelEditItemButton;
	}
	
	public By getCancelEditItemNameButton() {
		return checkIfElementIsDisplayed(getByElement(cancelEditItemButton), 5);
	}
	
	public boolean isCancelEditItemNameButtonVisible() {
		return isElementDisplayed(getByElement(cancelEditItemButton), 5);
	}

	public String[] getSaveEditItemButtonLocator() {
		return saveEditItemButton;
	}

	public void setSaveEditItemButtonLocator(String[] saveEditItemButton) {
		this.saveEditItemButton = saveEditItemButton;
	}
	
	public By getSaveEditItemNameButton() {
		return checkIfElementIsDisplayed(getByElement(saveEditItemButton), 5);
	}
	
	public boolean isSaveEditItemNameButtonVisible() {
		return isElementDisplayed(getByElement(saveEditItemButton), 5);
	}

	public String[] getItemsCheckboxesLocator() {
		return itemsCheckboxes;
	}

	public void setItemsCheckboxesLocator(String[] itemsCheckboxes) {
		this.itemsCheckboxes = itemsCheckboxes;
	}
	
	public By getItemsCheckboxes() {
		return checkIfElementIsDisplayed(getByElement(itemsCheckboxes), 5);
	}
	
	public boolean isItemsCheckboxesVisible() {
		return isElementDisplayed(getByElement(itemsCheckboxes), 5);
	}

	public String[] getThreeDotButtonLocator() {
		return threeDotButton;
	}

	public void setThreeDotButtonLocator(String[] threeDotButton) {
		this.threeDotButton = threeDotButton;
	}
	
	public By getThreeDotButton() {
		return checkIfElementIsDisplayed(getByElement(threeDotButton), 5);
	}
	
	public boolean isThreeDotButtonVisible() {
		return isElementDisplayed(getByElement(threeDotButton), 5);
	}

	public String[] getArchiveListButtonLocator() {
		return archiveListButton;
	}

	public void setArchiveListButtonLocator(String[] archiveListButton) {
		this.archiveListButton = archiveListButton;
	}
	
	public By getArchiveListButton() {
		return checkIfElementIsDisplayed(getByElement(archiveListButton), 5);
	}
	
	public boolean isArchiveListButtonVisible() {
		return isElementDisplayed(getByElement(archiveListButton), 5);
	}
	
}
