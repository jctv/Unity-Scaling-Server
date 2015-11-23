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
	
	Login loginPage;
	DashBoard dashBoardPage;
	ItemImport itemsImportPage;
	ItemsBank itemsBankPage;
	Items itemsPage;
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
		loginPage = new Login(driver);
		System.out.println("******** logging as System Admin -- " + loggedUser
				+ "******** ");
		dashBoardPage = loginPage.loginSuccess(loggedUser,
				genericPassword);
		waitTime();
		dashBoardPage.addTiles();
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
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemBankName = "Choice_IB_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName, "Desc");
		waitTime(); 
		itemsPage = dashBoardPage.goToItems();
		waitTime();
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		itemsPage.searchItem(itemName);
		waitTime();
		itemsPage.addStandards();
		waitTime();
		Assert.assertEquals(itemsPage.itemNameList.getText().trim(), itemName);
		Assert.assertEquals(itemsPage.itemTilteList.getText().trim(), "Description");
		Assert.assertEquals(itemsPage.itemPointsList.getText().trim(), "1");
		Assert.assertEquals(itemsPage.itemBankList.getText().trim(), itemBankName);
		itemsPage.deleteItem(itemName);
		itemsPage.backToDashboard();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemsBankPage.deleteItemBank(itemBankName);
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
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemBankName = "TextEntry_IB_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName, "Desc");
		waitTime(); 
		itemsPage = dashBoardPage.goToItems();
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionTextEntry , mapScoreProfile , textEntryCorrcetAnswer);
		itemsPage.searchItem(itemName);
		waitTime();
		itemsPage.addStandards();
		Assert.assertEquals(itemsPage.itemNameList.getText().trim(), itemName);
		Assert.assertEquals(itemsPage.itemTilteList.getText().trim(), "Description");
		Assert.assertEquals(itemsPage.itemPointsList.getText().trim(), "1");
		Assert.assertEquals(itemsPage.itemBankList.getText().trim(), itemBankName);
		itemsPage.deleteItem(itemName);
		itemsPage.backToDashboard();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemsBankPage.deleteItemBank(itemBankName);
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
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemBankName = "Choice_IB_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName, "Desc");
		waitTime(); 
		itemsPage = dashBoardPage.goToItems();
		waitTime();
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		//itemsPageObject = dashBoardPageObject.goToItems();
		waitTime(); 
		itemsPage.searchItem(itemName);
		itemsPage.addStandards();
		updatedItemName = updated + itemBankName;
		updatedTitleName = updated + "title" ;
		updatedItemContentArea = "Science";
		updatedItemGrade = "Any" ;
		updatedItemBloom = "updatedBloom";
		updatedItemDok = "III";
		updatedItemDifficulty = "HIGH";
		updatedItemLifeCycle = "DEFINED";
		updatedItemReadability = "Update Readability";
		Assert.assertEquals(itemsPage.updateItemName(updatedItemName), updatedItemName);
		Assert.assertEquals(itemsPage.updateItemTitle(updatedTitleName), updatedTitleName);
		Assert.assertEquals(itemsPage.updateItemContentArea(updatedItemContentArea), updatedItemContentArea);
		Assert.assertEquals(itemsPage.updateItemGrade(updatedItemGrade), updatedItemGrade);
		Assert.assertEquals(itemsPage.updateItemBloom(updatedItemBloom), updatedItemBloom);
		Assert.assertEquals(itemsPage.updateItemDOK(updatedItemDok), updatedItemDok);
		Assert.assertEquals(itemsPage.updateItemDifficulty(updatedItemDifficulty), updatedItemDifficulty);
		Assert.assertEquals(itemsPage.updateItemLifeCycle(updatedItemLifeCycle), updatedItemLifeCycle);
		Assert.assertEquals(itemsPage.updateItemReadability(updatedItemReadability), updatedItemReadability);
		Assert.assertEquals(itemsPage.itemPointsList.getText().trim(), "1");
		itemsPage.itemPointsList.click();
		waitTime();
		Assert.assertFalse(itemsPage.itemPointsListInput.isEnabled(), "Point colums is not editable");
		itemsPage.deleteItem(updatedItemName);
		itemsPage.backToDashboard();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemsBankPage.deleteItemBank(itemBankName);
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
		
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemsBankPage.createBank(itemBankName, "Desc");
		waitTime(); 
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
	    itemsBankPage.createBank(copyItemBankName, "Desc");
	    waitTime();
	    itemsPage = dashBoardPage.goToItems();
	    waitTime();
	    waitTime();
	    itemsPage.waitForElementAndClick(itemsPage.createItemButton);
	    waitTime();
	    waitTime();
	    itemsPage.selectItemBank(itemBankName);
	    waitTime();
	    itemsPage.waitForElementAndSendKeys(itemsPage.itemCreateInputName, itemName);
	    waitTime();
	    itemsPage.waitForElementAndSendKeys(itemsPage.itemCreateInputDescription, "Description");
	    waitTime();
	    itemsPage.waitForElementAndClick(itemsPage.itemCreateEditInputSubmit);
	    waitTime();
	    waitTime();
	    itemsPage.waitForElementAndClick(itemsPage.itemSaved);
	    waitTime();
	    waitTime();
		Assert.assertEquals(itemsPage.itemconfirmationMessageTitle.getText().trim(), unitymessages.getProperty("itemSave").trim());
		Assert.assertEquals(itemsPage.itemconfirmationMessageBody.getText().trim(), unitymessages.getProperty("itemContinueEditing").trim());
	    itemsPage.waitForElementAndClick(itemsPage.confirmationMessage);
	    waitTime();
	    itemsPage.waitForElementAndClick(itemsPage.confirmationMessage);
	    waitTime();
	    waitTime();
	    itemsPage.waitForElementAndClick(itemsPage.backToItems);
	    waitTime();
	    waitTime();
	    itemsPage.searchItem(itemName);
	    waitTime();
	    itemsPage.waitForElementAndClick(itemsPage.copyIconList);
	    waitTime();
	    itemsPage.copyItem(copyItemBankName ,copiedItemName);
	    waitTime();
	    waitTime();
	    Assert.assertEquals(itemsPage.globalModalInfoTitle.getText().trim(), unitymessages.getProperty("itemCopySuccess").trim());
		Assert.assertEquals(itemsPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("itemCreated").trim());
	    itemsPage.waitForElementAndClick(itemsPage.globalModalInfoOkButton);
	    waitTime();
	    waitTime();
	    itemsPage.searchItem(itemName);
	    waitTime();
	    itemsPage.waitForElementAndClick(itemsPage.deleteIconList);
	    waitTime();
		Assert.assertEquals(itemsPage.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("itemDelete").trim());
	    itemsPage.waitForElementAndClick(itemsPage.globalModalDeleteButton);
	    waitTime();
	    itemsPage.deleteItem(copiedItemName);
	    itemsPage.backToDashboard();
	    waitTime();
	    waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		itemsBankPage.deleteItemBank(itemBankName);
		waitTime();
		itemsBankPage.deleteItemBank(copyItemBankName);
	}

	
	/**
	 * Login into the Unity
	 * Create Item bank 
	 * Create Text Entry type Item with simple match scoring  
	 * Verify  item content and information in Listing page.
	 * Delete Item Bank
	 * Delete Item
	 */
	
	@Test(priority = 5)
	public void testCreateTextEntryItemWithSimpleMatchScoring(){
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		waitTime();
		itemBankName = "TextEntry_Hand_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName, "Hand Scoring");
		waitTime(); 
		itemsPage = dashBoardPage.goToItems();
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionTextEntry , simpleMatchScoreProfile , textEntryCorrcetAnswer);
		itemsPage.searchItem(itemName);
		waitTime();
		itemsPage.addStandards();
		Assert.assertEquals(itemsPage.itemNameList.getText().trim(), itemName);
		Assert.assertEquals(itemsPage.itemTilteList.getText().trim(), "Description");
		Assert.assertEquals(itemsPage.itemPointsList.getText().trim(), "1");
		Assert.assertEquals(itemsPage.itemBankList.getText().trim(), itemBankName);
		itemsPage.deleteItem(itemName);
		itemsPage.backToDashboard();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemsBankPage.deleteItemBank(itemBankName);
	}
	
	
	/**
	 * Login into the Unity
	 * Create Item bank 
	 * Create Text Entry type Item with Simple match scoring  
	 * Verify  item content and information in Listing page.
	 * Delete Item Bank
	 * Delete Item
	 */
	
	@Test(priority = 6)
	public void testCreateTextEntryItemWithHandScoring(){
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemBankName = "TextEntry_match_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName, "Hand Scoring");
		waitTime(); 
		itemsPage = dashBoardPage.goToItems();
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionTextEntry , mapScoreProfile , textEntryCorrcetAnswer);
		itemsPage.searchItem(itemName);
		waitTime();
		itemsPage.addStandards();
		Assert.assertEquals(itemsPage.itemNameList.getText().trim(), itemName);
		Assert.assertEquals(itemsPage.itemTilteList.getText().trim(), "Description");
		Assert.assertEquals(itemsPage.itemPointsList.getText().trim(), "1");
		Assert.assertEquals(itemsPage.itemBankList.getText().trim(), itemBankName);
		itemsPage.deleteItem(itemName);
		itemsPage.backToDashboard();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemsBankPage.deleteItemBank(itemBankName);
	}
	
	
	
}
