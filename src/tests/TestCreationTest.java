package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Delivery;
import pages.Items;
import pages.ItemsBank;
import pages.Login;
import pages.TestCreation;
import pages.TestsBank;
import generic.BaseTest;

public class TestCreationTest extends BaseTest {
	Login loginPage;
	DashBoard dashBoardPage;
	TestCreation testCreationPage;
	TestsBank testBankPage;
	Items itemsPage;
	ItemsBank itemsBankPage;
	Delivery previewTestPage;
	Properties unitymessages = null;
	Properties unitytestcreationdata = null ;

	String defaultUser = "nteacher1";
	String defaultPassword = "12345";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	String unityTestCreationDataFile = "src" + File.separator + "resources"
			+ File.separator + "unitytestcreationdata.properties";
	
	String testBankName;
	String copyTestBankName ;
	String testName;
	String copiedTestName;
	String itemBankName;
	String itemName;
	String extendedTextEntry = "Extended Text";
	String craseScoreProfile = "Crase";
	String  textEntryCorrcetAnswer = "Auto Text Entry";

	public TestCreationTest() {
		super();
	}
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		unitytestcreationdata = getUnityMessagesProperty(unityTestCreationDataFile);
	}
	
	@BeforeMethod
	public void setUp() {
		System.out.println("Load Unity url - " + url);
		driver.get(url);
		loginPage = new Login(driver);
		System.out.println("******** logging as Teacher -- " + defaultUser
				+ "******** ");
		dashBoardPage = loginPage.loginSuccess(defaultUser,
				defaultPassword);
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
	}
	
	
	/**
	 * Login into the Unity
	 * Go to the item bank tile
	 * Create two test bank
	 * Create test
	 * Edit test
	 * validate messages
	 * Search test
	 * Copy the test 
	 * validate messages
	 * Search test
	 * Delete test 
	 * validate messages
	 * Delete both the test Bank
	 */
	
	@Test(priority = 1)
	public void testItemAlertMessage(){
		long timestamp = System.currentTimeMillis();
		testBankName = unitytestcreationdata.getProperty("testBankName") + timestamp ;
		copyTestBankName = unitytestcreationdata.getProperty("copiedtestBank") + timestamp;	
		testName =  unitytestcreationdata.getProperty("testName") + timestamp;
	    copiedTestName = unitytestcreationdata.getProperty("copiedTestName") + timestamp;
	    testBankPage = dashBoardPage.goToTestsBank();
	    customeWaitTime(2);
		testBankPage.createBank(testBankName, "Desc");
	    customeWaitTime(2);
		testBankPage.createBank(copyTestBankName , "Desc");
		/*returnToDashboard();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.createBank(copyTestBankName, "Desc");
		customeWaitTime(5);
	    returnToDashboard();*/
		returnToDashboard();
		customeWaitTime(5);
	    testCreationPage = dashBoardPage.goToTestCreation();
	    customeWaitTime(5);
	    testCreationPage.waitForElementAndClick(testCreationPage.createTestLink);
	    customeWaitTime(5);
	    if(testCreationPage.bankDropDown.isDisplayed()){
	    	testCreationPage.selectOption(testCreationPage.bankDropDown, testBankName);
	    }else{
		    testCreationPage.selectTestBank(testBankName);
	    }
	    customeWaitTime(2);
	    testCreationPage.waitForElementAndSendKeys(testCreationPage.contentCreateInputName, testName);
	    customeWaitTime(2);
	    testCreationPage.waitForElementAndSendKeys(testCreationPage.contentCreateInputDescription, "Description");
	    customeWaitTime(2);
	    testCreationPage.waitForElementAndClick(testCreationPage.createAndEditButton);
	    customeWaitTime(5);
	    testCreationPage.waitForElementAndClick(testCreationPage.saveTestButton);
	    customeWaitTime(5);
		Assert.assertEquals(testCreationPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("testNoChange").trim());
	    customeWaitTime(2);
	    testCreationPage.waitForElementAndClick(testCreationPage.globalModalInfoOkButton);
	    customeWaitTime(5);
	    testCreationPage.waitForElementAndClick(testCreationPage.testPrintButton);
	    customeWaitTime(5);
		Assert.assertEquals(testCreationPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("testPrintWithOutAddItem").trim());
		testCreationPage.waitForElementAndClick(testCreationPage.globalModalInfoOkButton);
	    customeWaitTime(5);
		testCreationPage.waitForElementAndClick(testCreationPage.homeLink);
	    customeWaitTime(5);
	    testCreationPage.searchTest(testName);
	    
	    customeWaitTime(2);
	    
		testCreationPage.waitForElementAndClick(testCreationPage.editIconList);
		testCreationPage.selectOption(testCreationPage.testContentField, "N/A");
		testCreationPage.waitForElementAndClick(testCreationPage.homeLink);

		Assert.assertEquals(testCreationPage.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("unSavedData").trim());
		itemsPage.waitForElementAndClick(testCreationPage.globalModalOKCancelSaveButton);
    	customeWaitTime(2);
    	
	    testCreationPage.searchTest(testName);
	    
	    testCreationPage.waitForElementAndClick(testCreationPage.copyIconList);
	    customeWaitTime(2);
	    testCreationPage.copyTest(copyTestBankName ,copiedTestName);
	    customeWaitTime(5);
	    Assert.assertEquals(testCreationPage.globalModalInfoTitle.getText().trim(), unitymessages.getProperty("testCopySuccess").trim());
		Assert.assertEquals(testCreationPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("testCreated").trim());
		testCreationPage.waitForElementAndClick(testCreationPage.globalModalInfoOkButton);
	    customeWaitTime(5);
	    testCreationPage.searchTest(testName);
	    customeWaitTime(2);
	    testCreationPage.waitForElementAndClick(testCreationPage.deleteIconList);
	    customeWaitTime(2);
		Assert.assertEquals(testCreationPage.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("testDelete").trim());
		testCreationPage.waitForElementAndClick(testCreationPage.globalModalDeleteButton);
	    customeWaitTime(2);
	    testCreationPage.deleteTest(copiedTestName);
	    testCreationPage.backToDashboard();
	    customeWaitTime(2);
		testBankPage = dashBoardPage.goToTestsBank();
	    customeWaitTime(2);
		testBankPage.deleteTestBank(testBankName);
	    customeWaitTime(2);
		testBankPage.deleteTestBank(copyTestBankName);
	}
	
    @Test(priority = 0)
    public void testAddtest(){
    	long timestamp = System.currentTimeMillis();
		testBankName = unitytestcreationdata.getProperty("testBankName") + timestamp ;
		testName =  unitytestcreationdata.getProperty("testName") + timestamp;
		itemBankName = unitytestcreationdata.getProperty("itemBankName") + timestamp ;
		itemName =  unitytestcreationdata.getProperty("itemName") + timestamp;
		itemsBankPage = dashBoardPage.goToItemsBank();
		itemsBankPage.createBank(itemBankName, "desc");
		returnToDashboard();
		itemsPage = dashBoardPage.goToItems();
		System.out.println("itemsPage, line 188");
		itemsPage.createItem(itemName, itemBankName);
		returnToDashboard();
	    testBankPage = dashBoardPage.goToTestsBank();
	    customeWaitTime(2);
		testBankPage.createBank(testBankName, "Desc");
	    customeWaitTime(5);
	    returnToDashboard();
	    testCreationPage = dashBoardPage.goToTestCreation();
	    customeWaitTime(5);
	    testCreationPage.createTest(testName, testBankName, itemName);
	    testCreationPage.searchTest(testName);
		Assert.assertEquals(testCreationPage.waitAndGetElementText(testCreationPage.testNameList).trim(), testName);
		testCreationPage.backToDashboard();
    }
    
    @Test(priority = 2)
    public void testCreatTestWithAnswerEliminatorTool(){
    	long timestamp = System.currentTimeMillis();
    	String testWithAnswerEliminator;
    	testWithAnswerEliminator =  unitytestcreationdata.getProperty("testName") + timestamp;
    	testCreationPage =  dashBoardPage.goToTestCreation();
    	customeWaitTime(5);
    	testCreationPage.createTestWithAccommodationTool(testWithAnswerEliminator, testBankName, itemName, "answerEliminatorCheckBox");
    	customeWaitTime(5);
    	testCreationPage.searchTest(testWithAnswerEliminator);
		Assert.assertEquals(testCreationPage.waitAndGetElementText(testCreationPage.testNameList).trim(), testWithAnswerEliminator);
    	testCreationPage.waitForAnElementAndClick(testCreationPage.previewIconList);
    	customeWaitTime(5);
    	previewTestPage = testCreationPage.previewTest();
    	customeWaitTime(5);
    	Assert.assertTrue(previewTestPage.waitForElementVisible(previewTestPage.answerEliminatorIcon));
    	customeWaitTime(5);
    	previewTestPage.waitForAnElementAndClick(previewTestPage.exitButton);
    	dashBoardPage = testCreationPage.returnToDashboard();
    }
    
    @Test(priority = 3)
    public void testCreatTestWithHighLighTool(){
    	long timestamp = System.currentTimeMillis();
    	String testWithHighlight;
    	testWithHighlight =  unitytestcreationdata.getProperty("testName") + timestamp;
    	testCreationPage =  dashBoardPage.goToTestCreation();
    	customeWaitTime(5);
    	testCreationPage.createTestWithAccommodationTool(testWithHighlight, testBankName, itemName, "highlight");
    	customeWaitTime(5);
    	testCreationPage.searchTest(testWithHighlight);
		Assert.assertEquals(testCreationPage.waitAndGetElementText(testCreationPage.testNameList).trim(), testWithHighlight);
    	testCreationPage.waitForAnElementAndClick(testCreationPage.previewIconList);
    	customeWaitTime(5);
    	previewTestPage = testCreationPage.previewTest();
    	customeWaitTime(5);
    	Assert.assertTrue(previewTestPage.waitForElementVisible(previewTestPage.highlitghTool));
    	customeWaitTime(5);
    	previewTestPage.waitForAnElementAndClick(previewTestPage.exitButton);
    	dashBoardPage = testCreationPage.returnToDashboard();
    }
    
    @Test(priority = 4)
    public void testCreatTestWithNotePadTool(){
    	long timestamp = System.currentTimeMillis();
    	String testWithNotepad;
    	testWithNotepad =  unitytestcreationdata.getProperty("testName") + timestamp;
    	testCreationPage =  dashBoardPage.goToTestCreation();
    	customeWaitTime(5);
    	testCreationPage.createTestWithAccommodationTool(testWithNotepad, testBankName, itemName, "notepad");
    	customeWaitTime(5);
    	testCreationPage.searchTest(testWithNotepad);
		Assert.assertEquals(testCreationPage.waitAndGetElementText(testCreationPage.testNameList).trim(), testWithNotepad);
    	testCreationPage.waitForAnElementAndClick(testCreationPage.previewIconList);
    	customeWaitTime(5);
    	previewTestPage = testCreationPage.previewTest();
    	customeWaitTime(5);
    	Assert.assertTrue(previewTestPage.waitForElementVisible(previewTestPage.notepadTool));
    	customeWaitTime(5);
    	previewTestPage.waitForAnElementAndClick(previewTestPage.notepadTool);
    	customeWaitTime(5);
    	softAssert.assertTrue(previewTestPage.waitForElementVisible(previewTestPage.notepadDisplayed));
    	customeWaitTime(5);
    	
    }
    
    @Test(priority = 5)
    public void testCreatTestWithBookmarkTool(){
    	long timestamp = System.currentTimeMillis();
    	String testWithBookmark;
    	testWithBookmark =  unitytestcreationdata.getProperty("testName") + timestamp;
    	testCreationPage =  dashBoardPage.goToTestCreation();
    	customeWaitTime(5);
    	testCreationPage.createTestWithAccommodationTool(testWithBookmark, testBankName, itemName, "bookmark");
    	customeWaitTime(5);
    	testCreationPage.searchTest(testWithBookmark);
		Assert.assertEquals(testCreationPage.waitAndGetElementText(testCreationPage.testNameList).trim(), testWithBookmark);
    	testCreationPage.waitForAnElementAndClick(testCreationPage.previewIconList);
    	customeWaitTime(5);
    	previewTestPage = testCreationPage.previewTest();
    	customeWaitTime(5);
    	Assert.assertTrue(previewTestPage.waitForElementVisible(previewTestPage.bookmark));
    	customeWaitTime(5);
    	previewTestPage.waitForAnElementAndClick(previewTestPage.bookmark);
    	customeWaitTime(5);
    	softAssert.assertTrue(previewTestPage.waitForElementVisible(previewTestPage.bookmark));
    	customeWaitTime(5);
    }
    
    @Test(priority = 6)
    public void testCreateTestWithCraseProfileItem(){
    	long timestamp = System.currentTimeMillis();
		testBankName = unitytestcreationdata.getProperty("testBankName") + timestamp ;
		testName =  unitytestcreationdata.getProperty("testName") + timestamp;
		itemBankName = unitytestcreationdata.getProperty("itemBankName") + timestamp ;
		itemName =  unitytestcreationdata.getProperty("itemName") + timestamp;
		itemsBankPage = dashBoardPage.goToItemsBank();
		itemsBankPage.createBank(itemBankName, "desc");
		returnToDashboard();
		itemsPage = dashBoardPage.goToItems();
		itemsPage.createItem(itemName, itemBankName ,extendedTextEntry , craseScoreProfile , textEntryCorrcetAnswer);
		returnToDashboard();
	    testBankPage = dashBoardPage.goToTestsBank();
	    customeWaitTime(2);
		testBankPage.createBank(testBankName, "Desc");
	    customeWaitTime(5);
	    returnToDashboard();
	    testCreationPage = dashBoardPage.goToTestCreation();
	    customeWaitTime(5);
	    testCreationPage.createTest(testName, testBankName, itemName);
	    testCreationPage.searchTest(testName);
		Assert.assertEquals(testCreationPage.waitAndGetElementText(testCreationPage.testNameList).trim(), testName);
		testCreationPage.backToDashboard();
    }
	
}
