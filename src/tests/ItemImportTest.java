package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.ItemImport;
import pages.ItemsBank;
import pages.Login;
import pages.Role;
import generic.BaseTest;
import pages.Items;

public class ItemImportTest extends BaseTest {

	Login loginPage;
	DashBoard dashBoardPage;
	String loggedUser = "qa/admin";
	String genericPassword = "password";
	ItemImport itemsImportPage;
	ItemsBank itemsBankPage;
	Role rolePage;
	Items itemsPage;
	String itemBankName;
	
	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator + "itemimport" + File.separator ;
	
	
	String importedFileName;
	String importFileLocation;
	String noManifestFile;
	String invalidNoManifestImportFileLocation;
	String importedItemName;
	String invalidIdentifierFile;
	String invalidIndentifierImportFileLocation;
	String invalidImportedFileName;
	String invalidImportFileLocation;
	

	String textEntryFile1;
	String textEntrySingleCorrectFileLocation;
	
	String textEntryFile2;
	
	String textEntryMultipleCorrectFileLocation;

	String textEntryItem1 = "text_entry_single_correct.xml";
	String textEntryItem2 = "text_entry_multiple_correct.xml";

	private static final String DEFINED_LIFECYCLE = "DEFINED";
	private static final String REVIEW_LIFECYCLE = "REVIEW";
	private static final String ACCEPTED_LIFECYCLE = "ACCEPTED";
	private static final String REJECTED_LIFECYCLE = "REJECTED";
	private static final String HOLD_LIFECYCLE = "HOLD";
	private static final String PUBLISH_LIFECYCLE = "PUBLISH";

	private static final String QTI_PACKAGE = "QTI";
	
	
	Properties unitymessages;
	Properties unitytestdata;
	Properties unityItemImportData;
	
	/*String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator + "media" + File.separator ;*/
	
	String unityTestDataFile = "src" + File.separator + "resources"
			+ File.separator + "unitytestdata.properties";
	
	String unityItemImportDataFile = "src" + File.separator + "resources"
			+ File.separator + "unityitemimportdata.properties";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	

	public ItemImportTest() {
		super();

	}

	@BeforeTest
	public void loadtestdata(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		unityItemImportData = getUnityMessagesProperty(unityItemImportDataFile);
		
		importedFileName =  unityItemImportData.getProperty("textEntryFile");
		
		importFileLocation = resourcesLocation + importedFileName;
		
		noManifestFile =  unityItemImportData.getProperty("textEntryWithoutManifest");
		
		invalidNoManifestImportFileLocation = resourcesLocation
				+ noManifestFile;
		
		importedItemName =  unityItemImportData.getProperty("itemToBeImported");
		
		invalidIdentifierFile =  unityItemImportData.getProperty("textentryFileWithoutIdentifier");

		 invalidIndentifierImportFileLocation = resourcesLocation
				+ invalidIdentifierFile;
		
		 invalidImportedFileName =  unityItemImportData.getProperty("invalidImportFileName");

		 invalidImportFileLocation = resourcesLocation
				+ invalidImportedFileName;

		 textEntryFile1 =  unityItemImportData.getProperty("textEntryWithSingleCorrectFile");

		
	     textEntrySingleCorrectFileLocation = resourcesLocation
				
				+ textEntryFile1;
	     
	     textEntryFile2 =  unityItemImportData.getProperty("textEntryWithMulplteCorrectFile");

		
		
		textEntryMultipleCorrectFileLocation = resourcesLocation
				+ textEntryFile2;

		textEntryItem1 =  unityItemImportData.getProperty("textEntrySingleCorrectXml");
		textEntryItem2 =  unityItemImportData.getProperty("textEntryMultiCorrectXml");

		
	}
	
	@BeforeMethod
	public void setUp() {
		System.out.println("Load Unity url - " + url);
		driver.get(url);
		loginPage = new Login(driver);
		System.out.println("******** logging as System Admin -- " + loggedUser
				+ "******** ");
		dashBoardPage = loginPage.loginSuccess(loggedUser, genericPassword);
		waitTime();
		dashBoardPage.addTiles();
		waitTime();

	}

	/**
	 * Login as System Admin Go to Role tile Enable the Item import tile for
	 * system Admin Go to the dash board page Validate Item import tile is
	 * available in Dashborad page
	 * 
	 */

	@Test(priority = 1)
	public void testverifyItemImportTileAdded() {
		rolePage = dashBoardPage.goToRole();
		waitTime();
		rolePage.enableTile("item_import");
		rolePage.enableCreatePermissionItemImportTile();
		dashBoardPage = rolePage.backToDashboard();
		String itemImport = dashBoardPage.getAvailableTile("Item Import");
		Assert.assertEquals(itemImport, "Item Import");
	}

	/**
	 * Login as System admin Import the item Verify the Item import summary
	 * 
	 */

	@Test(priority = 2)
	public void testItemImportSummary() {
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemBankName = "CDE_IB_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName, "Desc");
		waitTime();
		returnToDashboard();
		customeWaitTime(5);
		itemsImportPage = dashBoardPage.goToItemImport();
		waitTime();
		Assert.assertTrue(itemsImportPage.importItem(importFileLocation,
				itemBankName, QTI_PACKAGE, DEFINED_LIFECYCLE));
		itemsImportPage.refreshPage();
		waitTime();

		Assert.assertEquals(itemsImportPage.itemImportPackageFileNameList
				.getText().trim(), importedFileName);
		Assert.assertEquals(itemsImportPage.itemImportFileNameList.getText()
				.trim(), importedFileName.split(".zip")[0]);
		Assert.assertEquals(itemsImportPage.itemImportFileStatusList.getText()
				.trim(), "Completed without error");
		itemsImportPage.importItemPreviewButton.click();
		waitTime();
		Assert.assertEquals(itemsImportPage.itemImportSummary.getText().trim(),
				"Item Import Summary");
		Assert.assertEquals(itemsImportPage.itemImportSummaryItem.getText()
				.trim(), "1");
		Assert.assertEquals(itemsImportPage.itemImportSummaryMedia.getText()
				.trim(), "1");
		Assert.assertEquals(itemsImportPage.itemImportSummaryCss.getText()
				.trim(), "1");
		Assert.assertEquals(itemsImportPage.itemImportSummaryFileName.getText()
				.trim(), "File: " + importedFileName);
		itemsImportPage.backToDashboard();
		waitTime();
		itemsPage = dashBoardPage.goToItems();
		waitTime();
		itemsPage.filterItemBank(itemBankName);
		itemsPage.deleteItem(importedItemName);
		itemsBankPage.backToDashboard();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemsBankPage.deleteItemBank(itemBankName);
	}

	/**
	 * Navigate to Item tile. Import the CDE file Validate the Imported item in
	 * item list Edit the item content and verify its content Deleting the Item
	 * bank and Items
	 */
	@Test(priority = 3)
	public void testItemImportCDEType() {
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemBankName = "CDE_IB_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName, "Desc");
		waitTime();
		returnToDashboard();
		customeWaitTime(5);
		itemsImportPage = dashBoardPage.goToItemImport();
		waitTime();
		Assert.assertTrue(itemsImportPage.importItem(importFileLocation,
				itemBankName, QTI_PACKAGE, DEFINED_LIFECYCLE));
		waitTime();
		Assert.assertEquals(itemsImportPage.importedFileEntry.getText().trim(),
				importedFileName);
		itemsImportPage.backToDashboard();
		waitTime();
		itemsPage = dashBoardPage.goToItems();
		waitTime();
		itemsPage.filterItemBank(itemBankName);
		Assert.assertEquals(itemsPage.itemNameList.getText().trim(),
				importedItemName);
		Assert.assertEquals(itemsPage.itemTilteList.getText().trim(),
				importedItemName.split(".xml")[0]);
		Assert.assertEquals(itemsPage.itemContentAreaList.getText().trim(),
				"N/A");
		Assert.assertEquals(itemsPage.itemGradeList.getText().trim(), "N/A");
		Assert.assertEquals(itemsPage.itemGradeList.getText().trim(), "N/A");
		Assert.assertEquals(
				itemsPage.itemDepthOfKnowledgeList.getText().trim(), "N/A");
		Assert.assertEquals(itemsPage.itemDifficultyList.getText().trim(),
				"N/A");
		Assert.assertEquals(itemsPage.itemLifeCycleList.getText().trim(),
				"DEFINED");
		Assert.assertEquals(itemsPage.itemBankList.getText().trim(),
				itemBankName);
		itemsPage.itemEditIcon.click();
		waitTime();
		Assert.assertEquals(itemsPage.getInteractionType("Text Entry").trim(),
				"Text Entry");
		itemsPage.scoreTabButton.click();
		waitTime();
		Assert.assertEquals(
				itemsPage.getSelectedScoreProfile(itemsPage.selectScoreProfile),
				"Map Scoring Profile");
		itemsPage.setCorrectAnswer.click();
		waitTime();
		itemsPage.textEditorSaveButton.click();
		waitTime();
		itemsPage.confirmationMessage.click();
		waitTime();
		itemsPage.previewTabButton.click();
		waitTime();
		Assert.assertEquals(itemsPage.answerProfile.getText().trim(),
				"1. Map Scoring Profile");
		Assert.assertEquals(itemsPage.correctAnswerProfile.getText().trim(),
				"812");
		itemsPage.textEditorSaveButton.click();
		waitTime();
		itemsPage.confirmationMessage.click();
		waitTime();
		itemsPage.backToItems.click();
		waitTime();
		itemsPage.backToDashboard();
		itemsPage = dashBoardPage.goToItems();
		waitTime();
		itemsPage.filterItemBank(itemBankName);
		itemsPage.deleteItem(importedItemName);
		itemsBankPage.backToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemsBankPage.deleteItemBank(itemBankName);
	}

	/**
	 * Login as a System Admin Import invalid item import file which does not
	 * have manifest file validate error message in Item import summary page.
	 * 
	 */
	@Test(priority = 4)
	public void testItemImportErrorMessageForNoManifestFile() {
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemBankName = "No_Menifest_IB_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName, "Desc");
		waitTime();
		returnToDashboard();
		customeWaitTime(5);
		itemsImportPage = dashBoardPage.goToItemImport();
		waitTime();
		Assert.assertTrue(itemsImportPage.importItem(
				invalidNoManifestImportFileLocation, itemBankName, QTI_PACKAGE,
				DEFINED_LIFECYCLE));
		itemsImportPage.refreshPage();
		waitTime();
		Assert.assertEquals(itemsImportPage.itemImportPackageFileNameList
				.getText().trim(), noManifestFile);
		Assert.assertEquals(itemsImportPage.itemImportFileNameList.getText()
				.trim(), noManifestFile.split(".zip")[0]);
		Assert.assertEquals(itemsImportPage.itemImportFileStatusList.getText()
				.trim(), "Failed");
		itemsImportPage.importItemPreviewButton.click();
		waitTime();
		Assert.assertEquals(itemsImportPage.itemImportSummary.getText().trim(),
				"Item Import Summary");
		Assert.assertEquals(itemsImportPage.itemImportSummaryFileName.getText()
				.trim(), "File: " + noManifestFile);
		Assert.assertTrue(itemsImportPage.itemImportError.getText().trim()
				.contains("ERROR: Line null"));
		Assert.assertTrue(itemsImportPage.itemImportErrorMessage
				.getText()
				.trim()
				.contains(
						"Error while parsing item import manifest file java.io.FileNotFoundException"));
		itemsImportPage.backToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		itemsBankPage.deleteItemBank(itemBankName);

	}

	/**
	 * Login as a System Admin Import invalid item import file which does not
	 * have proper identifier for item xml file validate error message in Item
	 * import summary page.
	 * 
	 */

	@Test(priority = 5)
	public void testItemImportErrorMessageForInvalidIndentifier() {
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemBankName = "Invalid_Idenfifier_IB_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName, "Desc");
		waitTime();
		returnToDashboard();
		customeWaitTime(5);
		itemsImportPage = dashBoardPage.goToItemImport();
		waitTime();
		Assert.assertTrue(itemsImportPage.importItem(
				invalidIndentifierImportFileLocation, itemBankName,
				QTI_PACKAGE, DEFINED_LIFECYCLE));
		itemsImportPage.refreshPage();
		waitTime();
		Assert.assertEquals(itemsImportPage.itemImportPackageFileNameList
				.getText().trim(), invalidIdentifierFile);
		Assert.assertEquals(itemsImportPage.itemImportFileNameList.getText()
				.trim(), invalidIdentifierFile.split(".zip")[0]);
		Assert.assertEquals(itemsImportPage.itemImportFileStatusList.getText()
				.trim(), "Failed");
		itemsImportPage.importItemPreviewButton.click();
		waitTime();
		Assert.assertEquals(itemsImportPage.itemImportSummary.getText().trim(),
				"Item Import Summary");
		Assert.assertEquals(itemsImportPage.itemImportSummaryFileName.getText()
				.trim(), "File: " + invalidIdentifierFile);
		Assert.assertTrue(itemsImportPage.itemImportError.getText().trim()
				.contains("ERROR: Line null"));
		Assert.assertTrue(itemsImportPage.itemImportErrorMessage.getText()
				.trim().contains("Error during import"));
		Assert.assertTrue(itemsImportPage.itemImportErrorMessage.getText()
				.trim().contains("No such file or directory"));
		itemsImportPage.backToDashboard();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemsBankPage.deleteItemBank(itemBankName);
	}

	/**
	 * Login as System Admin Create Item bank Import invalid import file for
	 * Validate the multiple error message in item import summary page
	 * 
	 */
	@Test(priority = 6)
	public void testItemImportMultipleErrorMessage() {
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemBankName = "Invalid_Import_IB_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName, "Desc");
		waitTime();
		returnToDashboard();
		customeWaitTime(5);
		itemsImportPage = dashBoardPage.goToItemImport();
		waitTime();
		Assert.assertTrue(itemsImportPage.importItem(invalidImportFileLocation,
				itemBankName, QTI_PACKAGE, DEFINED_LIFECYCLE));
		itemsImportPage.refreshPage();
		waitTime();
		waitTime();

		Assert.assertEquals(itemsImportPage.itemImportPackageFileNameList
				.getText().trim(), invalidImportedFileName);
		waitTime();
		Assert.assertEquals(itemsImportPage.itemImportFileNameList.getText()
				.trim(), invalidImportedFileName.split(".zip")[0]);
		waitTime();
		Assert.assertEquals(itemsImportPage.itemImportFileStatusList.getText()
				.trim(), "Failed");
		waitTime();

		itemsImportPage.importItemPreviewButton.click();

		waitTime();
		Assert.assertEquals(itemsImportPage.itemImportSummary.getText().trim(),
				"Item Import Summary");
		Assert.assertEquals(itemsImportPage.itemImportSummaryFileName.getText()
				.trim(), "File: " + invalidImportedFileName);
		Assert.assertTrue(itemsImportPage.itemImportErrorMessage1
				.getText()
				.trim()
				.contains(
						"Error during import: Error on line 32: The element type \"td\" must be terminated by the matching end-tag"));
		Assert.assertTrue(itemsImportPage.itemImportErrorMessage2.getText()
				.trim().contains("Error during import: Unexpected character"));
		Assert.assertTrue(itemsImportPage.itemImportErrorMessage3
				.getText()
				.trim()
				.contains(
						"Error during import: Un supported item iteraction type extendedTextEntry found in xml"));
		itemsImportPage.backToDashboard();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemsBankPage.deleteItemBank(itemBankName);
	}

	/**
	 * Login as System Admin Create item bank Import text entry type item having
	 * multiple correct Validate item attributes in listing Validate item
	 * attributes while editing items
	 * 
	 */
	@Test(priority = 7)
	public void testImportTextEntyItemWithSingleCorrect() {
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		waitTime();
		itemBankName = "Text_single_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName, "Desc");
		waitTime();
		returnToDashboard();
		customeWaitTime(5);
		itemsImportPage = dashBoardPage.goToItemImport();
		waitTime();
		Assert.assertTrue(itemsImportPage.importItem(
				textEntrySingleCorrectFileLocation, itemBankName, QTI_PACKAGE,
				DEFINED_LIFECYCLE));
		itemsImportPage.refreshPage();
		waitTime();
		waitTime();
		// Assert.assertEquals(itemsImportPage.itemImportPackageFileNameList.getText().trim(),
		// textEntryFile1);
		Assert.assertEquals(itemsImportPage.itemImportFileNameList.getText()
				.trim(), textEntryFile1.split(".zip")[0]);
		Assert.assertEquals(itemsImportPage.itemImportFileStatusList.getText()
				.trim(), "Successful");
		itemsImportPage.importItemPreviewButton.click();
		waitTime();
		Assert.assertEquals(itemsImportPage.itemImportSummary.getText().trim(),
				"Item Import Summary");
		Assert.assertEquals(itemsImportPage.itemImportSummaryItem.getText()
				.trim(), "1 Successful Records");
		Assert.assertEquals(itemsImportPage.itemImportSummaryFileName.getText()
				.trim(), "File: " + textEntryFile1);
		itemsImportPage.backToDashboard();
		waitTime();
		waitTime();
		itemsPage = dashBoardPage.goToItems();
		waitTime();
		waitTime();
		//itemsPage.searchItem(textEntryItem1);
		waitTime();
		waitTime();
		itemsPage.filterItemBank(itemBankName);
		itemsPage.waitForElementAndClick(itemsPage.editIconList);
		waitTime();
		waitTime();
		Assert.assertTrue(itemsPage.htmlTabButton.isEnabled());
		Assert.assertTrue(itemsPage.scoreTabButton.isEnabled());
		Assert.assertTrue(itemsPage.previewTabButton.isEnabled());

		Assert.assertTrue(itemsPage.ItemHtmlParagraph.getText().contains(
				"Identify the missing word"));
		itemsPage.waitForElementAndClick(itemsPage.scoreTabButton);
		waitTime();
		waitTime();
		Assert.assertTrue(itemsPage.ItemHtmlParagraph.getText().contains(
				"Identify the missing word"));
		Assert.assertEquals(
				itemsPage.getSelectedOption(itemsPage.selectScoreProfile),
				"Map");
		itemsPage.waitForElementAndClick(itemsPage.previewTabButton);
		waitTime();
		waitTime();
		Assert.assertTrue(itemsPage.answerProfile.getText().contains("Map"));
		Assert.assertTrue(itemsPage.correctAnswerProfile.getText().contains(
				"York"));
		itemsPage.waitForElementAndClick(itemsPage.backToItems);
		waitTime();
		waitTime();
		itemsPage.deleteItem(importedItemName);
		itemsBankPage.backToDashboard();
		waitTime();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		waitTime();
		itemsBankPage.deleteItemBank(itemBankName);

	}

	@Test(priority = 8)
	public void testImportTextEntyItemWithMultipleCorrect() {
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		waitTime();
		itemBankName = "Text_Multiple_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName, "Desc");
		waitTime();
		returnToDashboard();
		customeWaitTime(5);
		itemsImportPage = dashBoardPage.goToItemImport();
		waitTime();
		Assert.assertTrue(itemsImportPage.importItem(
				textEntryMultipleCorrectFileLocation, itemBankName, QTI_PACKAGE,
				DEFINED_LIFECYCLE));
		itemsImportPage.refreshPage();
		waitTime();
		waitTime();
		 Assert.assertEquals(itemsImportPage.itemImportPackageFileNameList.getText().trim(),
		 textEntryFile2);
		Assert.assertEquals(itemsImportPage.itemImportFileNameList.getText()
				.trim(), textEntryFile2.split(".zip")[0]);
		Assert.assertEquals(itemsImportPage.itemImportFileStatusList.getText()
				.trim(), "Successful");
		itemsImportPage.importItemPreviewButton.click();
		waitTime();
		Assert.assertEquals(itemsImportPage.itemImportSummary.getText().trim(),
				"Item Import Summary");
		Assert.assertEquals(itemsImportPage.itemImportSummaryItem.getText()
				.trim(), "1 Successful Records");
		Assert.assertEquals(itemsImportPage.itemImportSummaryFileName.getText()
				.trim(), "File: " + textEntryFile2);
		itemsImportPage.backToDashboard();
		waitTime();
		waitTime();
		itemsPage = dashBoardPage.goToItems();
		waitTime();
		waitTime();
		//itemsPage.searchItem(textEntryItem2);
		waitTime();
		waitTime();
		itemsPage.filterItemBank(itemBankName);
		itemsPage.waitForElementAndClick(itemsPage.editIconList);
		waitTime();
		waitTime();
		Assert.assertTrue(itemsPage.htmlTabButton.isEnabled());
		Assert.assertTrue(itemsPage.scoreTabButton.isEnabled());
		Assert.assertTrue(itemsPage.previewTabButton.isEnabled());

		Assert.assertTrue(itemsPage.ItemHtmlParagraph.getText().contains(
				"Identify the missing word"));
		itemsPage.waitForElementAndClick(itemsPage.scoreTabButton);
		waitTime();
		waitTime();
		Assert.assertTrue(itemsPage.ItemHtmlParagraph.getText().contains(
				"Identify the missing word"));
		Assert.assertEquals(
				itemsPage.getSelectedOption(itemsPage.selectScoreProfile),
				"Map");
		itemsPage.waitForElementAndClick(itemsPage.previewTabButton);
		waitTime();
		waitTime();
		Assert.assertTrue(itemsPage.answerProfile.getText().contains("Map"));
		Assert.assertTrue(itemsPage.correctAnswerProfile.getText().contains(
				"York,york"));
		itemsPage.waitForElementAndClick(itemsPage.backToItems);
		waitTime();
		waitTime();
		itemsPage.deleteItem(importedItemName);
		itemsBankPage.backToDashboard();
		waitTime();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		waitTime();
		itemsBankPage.deleteItemBank(itemBankName);

	}
}
