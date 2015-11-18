package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.ItemImport;
import pages.Items;
import pages.ItemsBank;
import pages.Login;
import generic.BaseTest;

public class ItemTest extends BaseTest{
	
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	ItemImport itemsImportPageObject;
	ItemsBank itemsBankPageObject;
	Items itemsPageObject;
	Properties unitymessages;

	String defaultUser = "admin";
	String defaultPassword = "@simple1";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	String loggedUser = "qa/admin";
	String genericPassword = "password";
	String itemBankName;
	String copyItemBankName ;
	String itemName;
	String copiedItemName;

	String updatedItemName;
	String updatedTitleName;
	String updatedItemContentArea;
	String updatedItemGrade;
	String updatedItemBloom;
	String updatedItemDok;
	String updatedItemDifficulty;
	String updatedItemLifeCycle;
	String updatedItemReadability;
	String interactionChoice = "Choice";
	String interactionTextEntry = "Text Entry";
	String simpleMatchScoreProfile = "Simple Match";
	String mapScoreProfile = "Map";
	String handScoreProfile  = "Hand Scoring";
	String choiceCorrectAnswer = "set D correct Answer";
	String  textEntryCorrcetAnswer = "Auto Text Entry";

	public ItemTest() {
		super();

	}
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		
	}
	
	@BeforeMethod
	public void setUp() {
		System.out.println("Load Unity url - " + url);
		driver.get(url);
		loginPageObject = new Login(driver);
		System.out.println("******** logging as System Admin -- " + loggedUser
				+ "******** ");
		dashBoardPageObject = loginPageObject.loginSuccess(loggedUser,
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
	}
	
	/**
	 * Login into the Unity
	 * Create Item bank 
	 * Create choice type Item
	 * Verify  item content and information in Listing page.
	 * Delete Item Bank
	 * Delete Item
	 */
	
	@Test(priority =1 )
	public void testCreateItemChoiceType(){
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemBankName = "Choice_IB_" + System.currentTimeMillis();
		itemsBankPageObject.createBank(itemBankName, "Desc");
		waitTime(); 
		itemsPageObject = dashBoardPageObject.goToItems();
		waitTime();
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPageObject.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		itemsPageObject.searchItem(itemName);
		waitTime();
		itemsPageObject.addStandards();
		waitTime();
		Assert.assertEquals(itemsPageObject.itemNameList.getText().trim(), itemName);
		Assert.assertEquals(itemsPageObject.itemTilteList.getText().trim(), "Description");
		Assert.assertEquals(itemsPageObject.itemPointsList.getText().trim(), "1");
		Assert.assertEquals(itemsPageObject.itemBankList.getText().trim(), itemBankName);
		itemsPageObject.deleteItem(itemName);
		itemsPageObject.backToDashboard();
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
	}
	
	
	/**
	 * Login into the Unity
	 * Create Item bank 
	 * Create Text Entry type Item
	 * Verify  item content and information in Listing page.
	 * Delete Item Bank
	 * Delete Item
	 */
	
	@Test(priority = 2)
	public void testCreateTextEntryItemWithMapScoring(){
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemBankName = "TextEntry_IB_" + System.currentTimeMillis();
		itemsBankPageObject.createBank(itemBankName, "Desc");
		waitTime(); 
		itemsPageObject = dashBoardPageObject.goToItems();
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPageObject.createItem(itemName, itemBankName ,interactionTextEntry , mapScoreProfile , textEntryCorrcetAnswer);
		itemsPageObject.searchItem(itemName);
		waitTime();
		itemsPageObject.addStandards();
		Assert.assertEquals(itemsPageObject.itemNameList.getText().trim(), itemName);
		Assert.assertEquals(itemsPageObject.itemTilteList.getText().trim(), "Description");
		Assert.assertEquals(itemsPageObject.itemPointsList.getText().trim(), "1");
		Assert.assertEquals(itemsPageObject.itemBankList.getText().trim(), itemBankName);
		itemsPageObject.deleteItem(itemName);
		itemsPageObject.backToDashboard();
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
	}
	
	
	/**
	 * Login into the Unity
	 * Create Item bank 
	 * Create choice type Item
	 * update the   item  information in Listing page.
	 * Verify updated item information 
	 * Delete Item Bank
	 * Delete updated Item
	 */
	
	@Test(priority = 3)
	public void testUpadteItemInListing(){
		String updated = "Updated";
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemBankName = "Choice_IB_" + System.currentTimeMillis();
		itemsBankPageObject.createBank(itemBankName, "Desc");
		waitTime(); 
		itemsPageObject = dashBoardPageObject.goToItems();
		waitTime();
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPageObject.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		//itemsPageObject = dashBoardPageObject.goToItems();
		waitTime(); 
		itemsPageObject.searchItem(itemName);
		itemsPageObject.addStandards();
		updatedItemName = updated + itemBankName;
		updatedTitleName = updated + "title" ;
		updatedItemContentArea = "Science";
		updatedItemGrade = "Any" ;
		updatedItemBloom = "updatedBloom";
		updatedItemDok = "III";
		updatedItemDifficulty = "HIGH";
		updatedItemLifeCycle = "DEFINED";
		updatedItemReadability = "Update Readability";
		Assert.assertEquals(itemsPageObject.updateItemName(updatedItemName), updatedItemName);
		Assert.assertEquals(itemsPageObject.updateItemTitle(updatedTitleName), updatedTitleName);
		Assert.assertEquals(itemsPageObject.updateItemContentArea(updatedItemContentArea), updatedItemContentArea);
		Assert.assertEquals(itemsPageObject.updateItemGrade(updatedItemGrade), updatedItemGrade);
		Assert.assertEquals(itemsPageObject.updateItemBloom(updatedItemBloom), updatedItemBloom);
		Assert.assertEquals(itemsPageObject.updateItemDOK(updatedItemDok), updatedItemDok);
		Assert.assertEquals(itemsPageObject.updateItemDifficulty(updatedItemDifficulty), updatedItemDifficulty);
		Assert.assertEquals(itemsPageObject.updateItemLifeCycle(updatedItemLifeCycle), updatedItemLifeCycle);
		Assert.assertEquals(itemsPageObject.updateItemReadability(updatedItemReadability), updatedItemReadability);
		Assert.assertEquals(itemsPageObject.itemPointsList.getText().trim(), "1");
		itemsPageObject.itemPointsList.click();
		waitTime();
		Assert.assertFalse(itemsPageObject.itemPointsListInput.isEnabled(), "Point colums is not editable");
		itemsPageObject.deleteItem(updatedItemName);
		itemsPageObject.backToDashboard();
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
	}
	
	/**
	 * Login into the Unity
	 * Go to the item bank tile
	 * Create two item bank
	 * Create item
	 * Edit item
	 * validate messages
	 * Search item
	 * Copy the item 
	 * validate messages
	 * Search item
	 * Delete item 
	 * validate item
	 * Delete both the Item Bank
	 */
	
	@Test(priority = 4)
	public void testItemAlertMessage(){
		itemBankName = "IB_" + System.currentTimeMillis();
		copyItemBankName = "Copy" + itemBankName;	
		itemName = "I" + itemBankName;
	    copiedItemName = "copy " + itemName;
		
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.createBank(itemBankName, "Desc");
		waitTime(); 
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
	    itemsBankPageObject.createBank(copyItemBankName, "Desc");
	    waitTime();
	    itemsPageObject = dashBoardPageObject.goToItems();
	    waitTime();
	    waitTime();
	    itemsPageObject.waitForElementAndClick(itemsPageObject.createItemButton);
	    waitTime();
	    waitTime();
	    itemsPageObject.selectItemBank(itemBankName);
	    waitTime();
	    itemsPageObject.waitForElementAndSendKeys(itemsPageObject.itemCreateInputName, itemName);
	    waitTime();
	    itemsPageObject.waitForElementAndSendKeys(itemsPageObject.itemCreateInputDescription, "Description");
	    waitTime();
	    itemsPageObject.waitForElementAndClick(itemsPageObject.itemCreateEditInputSubmit);
	    waitTime();
	    waitTime();
	    itemsPageObject.waitForElementAndClick(itemsPageObject.itemSaved);
	    waitTime();
	    waitTime();
		Assert.assertEquals(itemsPageObject.itemconfirmationMessageTitle.getText().trim(), unitymessages.getProperty("itemSave").trim());
		Assert.assertEquals(itemsPageObject.itemconfirmationMessageBody.getText().trim(), unitymessages.getProperty("itemContinueEditing").trim());
	    itemsPageObject.waitForElementAndClick(itemsPageObject.confirmationMessage);
	    waitTime();
	    itemsPageObject.waitForElementAndClick(itemsPageObject.confirmationMessage);
	    waitTime();
	    waitTime();
	    itemsPageObject.waitForElementAndClick(itemsPageObject.backToItems);
	    waitTime();
	    waitTime();
	    itemsPageObject.searchItem(itemName);
	    waitTime();
	    itemsPageObject.waitForElementAndClick(itemsPageObject.copyIconList);
	    waitTime();
	    itemsPageObject.copyItem(copyItemBankName ,copiedItemName);
	    waitTime();
	    waitTime();
	    Assert.assertEquals(itemsPageObject.globalModalInfoTitle.getText().trim(), unitymessages.getProperty("itemCopySuccess").trim());
		Assert.assertEquals(itemsPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("itemCreated").trim());
	    itemsPageObject.waitForElementAndClick(itemsPageObject.globalModalInfoOkButton);
	    waitTime();
	    waitTime();
	    itemsPageObject.searchItem(itemName);
	    waitTime();
	    itemsPageObject.waitForElementAndClick(itemsPageObject.deleteIconList);
	    waitTime();
		Assert.assertEquals(itemsPageObject.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("itemDelete").trim());
	    itemsPageObject.waitForElementAndClick(itemsPageObject.globalModalDeleteButton);
	    waitTime();
	    itemsPageObject.deleteItem(copiedItemName);
	    itemsPageObject.backToDashboard();
	    waitTime();
	    waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		itemsBankPageObject.deleteItemBank(itemBankName);
		waitTime();
		itemsBankPageObject.deleteItemBank(copyItemBankName);
	}

}
