package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.ItemImport;
import pages.ItemsBank;
import pages.Login;
import pages.Role;
import generic.BaseTest;
import pages.Items;

public class ItemImportTest extends BaseTest {
	
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	String loggedUser="qa/admin";
	String genericPassword = "password";
	ItemImport itemsImportPageObject;
	ItemsBank itemsBankPageObject;
	Role rolePageObject;
	Items itemsPageObject;
	String itemBankName ;
	String importedFileName = "CDE_TextEntry.zip";
	String importedItemName = "5th2015-DM_CS-0010.xml" ;


	public ItemImportTest () {
		super();
		
	}
	
	@BeforeMethod
	public void setUp() {
		System.out.println("Load Unity url - " + url);
		driver.get(url);
		loginPageObject = new Login(driver);
		System.out.println("******** logging as System Admin -- " + loggedUser  + "******** " );
		dashBoardPageObject = loginPageObject.loginSuccess(loggedUser, genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		
	}
	
	
	/**
	 * Login as System Admin
	 * Go to Role tile
	 * Enable the Item import tile for system Admin
	 * Go to the dash board page 
	 * Validate Item import tile is available in Dashborad page
	 * 
	 */
	
	@Test(priority =1)
	public void testverifyItemImportTileAdded(){
		rolePageObject = dashBoardPageObject.goToRole();
		waitTime(); 
		rolePageObject.enableTile("item_import");
		rolePageObject.enableCreatePermissionItemImportTile();
		dashBoardPageObject = rolePageObject.backToDashboard();
		String itemImport = dashBoardPageObject.getAvailableTile("Item Import");
		Assert.assertEquals(itemImport, "Item Import");
	}
	
	
	/**
	 * Login as System admin
	 * Import the item 
	 * Verify the Item import summary
	 * 
	 */
	@Test(priority =2)
	public void testItemImportSummary(){
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		itemBankName = "CDE_IB_" + System.currentTimeMillis();
		itemsBankPageObject.createBank(itemBankName, "Desc");
		waitTime(); 
		itemsImportPageObject = dashBoardPageObject.goToItemImport();
		itemsImportPageObject.importItem(importedFileName ,itemBankName ,"CDE");
		itemsImportPageObject.refreshPage();
		waitTime();
		Assert.assertEquals(itemsImportPageObject.itemImportPackageFileNameList.getText().trim(), importedFileName);
		Assert.assertEquals(itemsImportPageObject.itemImportFileNameList.getText().trim(), importedFileName.split(".zip")[0]);
		Assert.assertEquals(itemsImportPageObject.itemImportFileStatusList.getText().trim(), "Completed without error");
		itemsImportPageObject.importItemPreviewButton.click();
		Assert.assertEquals(itemsImportPageObject.itemImportSummary.getText().trim(), "Item Import Summary");
		Assert.assertEquals(itemsImportPageObject.itemImportSummaryItem.getText().trim(), "1");
		Assert.assertEquals(itemsImportPageObject.itemImportSummaryMedia.getText().trim(), "1");
		Assert.assertEquals(itemsImportPageObject.itemImportSummaryCss.getText().trim(), "1");
		Assert.assertEquals(itemsImportPageObject.itemImportSummaryFileName.getText().trim(), "File: " + importedFileName);
		itemsPageObject.backToDashboard();
		itemsPageObject = dashBoardPageObject.goToItems();
		itemsPageObject.filterItemBank(itemBankName);
		itemsPageObject.deleteItem(importedItemName);
		itemsBankPageObject.backToDashboard();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		itemsBankPageObject.deleteItemBank(itemBankName);

	}

	/**
	 * Navigate to Item tile.
	 * Import the CDE file
	 * Validate the  Imported item in item list
	 * Edit  the item content and verify its content
	 * Deleting the Item bank and Items
	 */
	@Test(priority=3) 
	public void testItemImportCDEType(){ 
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		itemBankName = "CDE_IB_" + System.currentTimeMillis();
		itemsBankPageObject.createBank(itemBankName, "Desc");
		waitTime();
		itemsImportPageObject = dashBoardPageObject.goToItemImport();
		itemsImportPageObject.importItem(importedFileName ,itemBankName ,"CDE");
		waitTime();
		Assert.assertEquals(itemsImportPageObject.importedFileEntry.getText().trim(), importedFileName);
		itemsImportPageObject.backToDashboard();
		waitTime();
		itemsPageObject = dashBoardPageObject.goToItems();
		waitTime();
		itemsPageObject.filterItemBank(itemBankName);
		Assert.assertEquals(itemsPageObject.itemNameList.getText().trim(), importedItemName);
		Assert.assertEquals(itemsPageObject.itemTilteList.getText().trim(), importedItemName.split(".xml")[0]);
		Assert.assertEquals(itemsPageObject.itemContentAreaList.getText().trim(), "N/A");
		Assert.assertEquals(itemsPageObject.itemGradeList.getText().trim(), "N/A");
		Assert.assertEquals(itemsPageObject.itemGradeList.getText().trim(), "N/A");
		Assert.assertEquals(itemsPageObject.itemDepthOfKnowledgeList.getText().trim(), "N/A");
		Assert.assertEquals(itemsPageObject.itemDifficultyList.getText().trim(), "N/A");
		Assert.assertEquals(itemsPageObject.itemLifeCycleList.getText().trim(), "DEFINED");
		Assert.assertEquals(itemsPageObject.itemBankList.getText().trim(), itemBankName);
		itemsPageObject.itemEditIcon.click();
		waitTime();
		Assert.assertEquals(itemsPageObject.getInteractionType("Text Entry").trim(), "Text Entry");
		itemsPageObject.scoreTabButton.click();
		waitTime();
		Assert.assertEquals(itemsPageObject.getSelectedScoreProfile(itemsPageObject.selectScoreProfile), "Map Scoring Profile");
		itemsPageObject.previewTabButton.click();
		waitTime();
		Assert.assertEquals(itemsPageObject.answerProfile.getText().trim(), "1. Map Scoring Profile");
		Assert.assertEquals(itemsPageObject.correctAnswerProfile.getText().trim(), "812");
		itemsPageObject.textEditorSaveButton.click();
		waitTime();
		itemsPageObject.confirmationMessage.click();
		itemsPageObject.backToItems.click();
		waitTime();
		itemsPageObject.backToDashboard();
		itemsPageObject = dashBoardPageObject.goToItems();
		itemsPageObject.filterItemBank(itemBankName);
		itemsPageObject.deleteItem(importedItemName);
		itemsBankPageObject.backToDashboard();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		itemsBankPageObject.deleteItemBank(itemBankName);
		
	}
}
