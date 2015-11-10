package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
	
	String loggedUser = "qa/admin";
	String genericPassword = "password";
	String itemBankName;
	String itemName;
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
		itemsPageObject = dashBoardPageObject.goToItems();
		itemsPageObject.searchItem(itemName);
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
		itemsPageObject = dashBoardPageObject.goToItems();
		itemsPageObject.searchItem(itemName);
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
}
