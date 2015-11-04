package tests;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.ItemImport;
import pages.ItemsBank;
import pages.Login;
import generic.BaseTest;
import pages.Items;

public class ItemImportTest extends BaseTest {
	
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	String loggedUser="qa/admin";
	String genericPassword = "password";
	ItemImport itemsImportPageObject;
	ItemsBank itemsBankPageObject;
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
		System.out.println("******** logging as Scool Admin -- " + loggedUser  + "******** " );
		dashBoardPageObject = loginPageObject.loginSuccess(loggedUser, genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		
	}

	/**
	 * Navigate to Item tile.
	 * Import the CDE file
	 * Validate the  Imported item in item list
	 * Edit  the item content and verify its content
	 * Deleting the Item bank and Items
	 */
	@Test 
	public void testItemImportCDEType(){ 
		//For time being  going to item page instead of item import since changes are not available in QA. Will modify this method once latest change will be available
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
