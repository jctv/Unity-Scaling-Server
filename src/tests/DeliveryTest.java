package tests;

import java.io.File;
import java.util.Properties;

import generic.BaseTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.ClassRoster;
import pages.DashBoard;
import pages.Delivery;
import pages.Domain;
import pages.HandScoring;
import pages.Items;
import pages.ItemsBank;
import pages.Login;
import pages.Organization;
import pages.Reports;
import pages.Role;
import pages.Schedule;
import pages.Standards;
import pages.TestCreation;
import pages.TestsBank;

public class DeliveryTest extends BaseTest {
	Login loginPage;
	DashBoard dashBoardPage;
	Delivery deliveryPage;
	ClassRoster classRosterPage;
	ItemsBank itemsBankPage;
	TestsBank testBankPage;
	Properties unitytestdata;
	
	Items itemsPage;
	TestCreation testCreationPage;

	String resources = "src" + File.separator + "resources"
			+ File.separator;
	String unitytestDataFile = resources + "unitytestdata.properties";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	Properties unitymessages;
	String teacher1;
	String student1;
	String genericPassword;
	String roster;
	String school;
	String itemName ;
	String testName;
	String testName1;
	
	String testName2;
	String testName3;
	String testName4;
	String testName5;
	String testName6;
	String testName7;

	String testBank;
	String itemBank;
	String copiedItemName;
	
	String testid;
	String testid1;
	String testid2;
	String testid3;
	String testid4;
	String testid5;
	String testid6;
	String testid7;
	
	String testroster;
	String testschool;
	
	String interactionChoice;
	String simpleMatchScoreProfile;
	String choiceCorrectAnswer;
	
	Schedule sechedulePage;
	
	String scale_1_5x = "1.5";
	String scale_2_5x = "2.5";
	String scale_2_0x = "2.0";

	
	int itemCount = 5;
	long timestamp;
	
	public DeliveryTest() {
		super();
	}
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unitytestDataFile);
		unitymessages = getUnityMessagesProperty(unityMessageFile);

		teacher1 = unitytestdata.getProperty("teacher1");
		student1 = unitytestdata.getProperty("stduent1");
		genericPassword = unitytestdata.getProperty("genericPassword");
		roster = unitytestdata.getProperty("roster");
		school = unitytestdata.getProperty("school");
		
		interactionChoice = unitytestdata.getProperty("interactionChoice");
		simpleMatchScoreProfile = unitytestdata
				.getProperty("simpleMatchScoreProfile");
		choiceCorrectAnswer = unitytestdata.getProperty("choiceCorrectAnswer");

		testroster = unitytestdata.getProperty("testRoster");
		testschool =  unitytestdata.getProperty("testSchool");	
	}

	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		System.out.println("******** logging as teacher  ********");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testTeacher1"),unitytestdata.getProperty("genericPassword"));
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		timestamp = System.currentTimeMillis();
		itemBank= "AMT_" + timestamp;
		itemsBankPage.createBank(itemBank, "desc");
		returnToDashboard();
		itemsPage = dashBoardPage.goToItems();
		itemName = "I_" + timestamp;
		itemsPage.createItem(itemName, itemBank, interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);		
		itemsPage.searchItem(itemName);
		itemsPage.addStandards();
        copiedItemName = "copy_" + itemName;
		itemsPage.copyMultipleItems(itemBank, itemName, copiedItemName, 1, itemCount -1);
		returnToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		testBank = "AMT_" + timestamp;
		testBankPage.createBank(testBank, "desc");
		returnToDashboard();
		customeWaitTime(5);
		waitTime();
		testCreationPage = dashBoardPage.goToTestCreation();
		waitTime();
		testName = "T_" + timestamp;
		System.out.println("******** Test creation ********");
		testCreationPage.createTestWithMultipleItems(testName,
				testBank, itemBank, itemCount);
		
		testName1 =  "T_Line_" + timestamp;
		
		testCreationPage.createTestWithMultipleItems(testName1,
				testBank, itemBank, itemCount);
		customeWaitTime(5);
		
        testName2 =  "T_Magnifier1_5x_" + timestamp;
		
		testCreationPage.createTestWithMultipleItems(testName2,
				testBank, itemBank, itemCount);
		customeWaitTime(5);
		
		testName3 =  "T_Magnifier2_0x_" + timestamp;
		testCreationPage.createTestWithMultipleItems(testName3,
					testBank, itemBank, itemCount);
		customeWaitTime(5);
			
		testName4 =  "T_Magnifier2_5x_" + timestamp;
		testCreationPage.createTestWithMultipleItems(testName4,
					testBank, itemBank, itemCount);
		customeWaitTime(5);
		
		testName5 =  "T_Alternate_Color_background" + timestamp;
		testCreationPage.createTestWithMultipleItems(testName5,
					testBank, itemBank, itemCount);
		customeWaitTime(5);
		
		testName6 = "T_With_Reportable_Score" + timestamp;
		testCreationPage.createTestWithMultipleItems(testName6,
				testBank, itemBank, itemCount);
		
		testName7 = "T_With_Non_Reportable_Score" + timestamp;
		testCreationPage.createTestWithMultipleItems(testName7,
				testBank, itemBank, itemCount);
		
		testCreationPage.searchTest(testName);
		testid = testCreationPage.getTestId();
		testCreationPage.waitForElementAndClick(testCreationPage.editIconList); 
		customeWaitTime(5);
		testCreationPage.enableTestTools("Answer Masking");
		
		testCreationPage.searchTest(testName1);
		testid1 = testCreationPage.getTestId();
		testCreationPage.waitForElementAndClick(testCreationPage.editIconList); 
		testCreationPage.enableTestTools("Line Reader");
		
		testCreationPage.searchTest(testName2);
		testid2 = testCreationPage.getTestId();
		testCreationPage.waitForElementAndClick(testCreationPage.editIconList); 
		testCreationPage.enableTestTools("Magnification");
		testCreationPage.setMagnificationScale(scale_1_5x);
		
		testCreationPage.searchTest(testName3);
		testid3 = testCreationPage.getTestId();
		testCreationPage.waitForElementAndClick(testCreationPage.editIconList); 
		testCreationPage.enableTestTools("Magnification");
		testCreationPage.setMagnificationScale(scale_2_0x);
		
		testCreationPage.searchTest(testName4);
		testid4 = testCreationPage.getTestId();
		testCreationPage.waitForElementAndClick(testCreationPage.editIconList); 
		testCreationPage.enableTestTools("Magnification");
		testCreationPage.setMagnificationScale(scale_2_5x);
		
		testCreationPage.searchTest(testName5);
		testid5 = testCreationPage.getTestId();
		testCreationPage.waitForElementAndClick(testCreationPage.editIconList); 
		testCreationPage.enableTestTools("Alternate Color Text/Background");
		
		testCreationPage.searchTest(testName6);
		testid6 = testCreationPage.getTestId();
		
		testCreationPage.searchTest(testName7);
		testid7 = testCreationPage.getTestId();
		returnToDashboard();
		customeWaitTime(5);
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
	    System.out.println("******** Event creation ********");
	     sechedulePage.scheduleTest(testschool, testroster, "N/A", testName, "Red", "120","100%", "Yes", "true");
		waitTime();
		sechedulePage.scheduleTest(testschool, testroster, "N/A", testName1, "Red","120","100%", "Yes", "true");
		waitTime();	
		sechedulePage.scheduleTest(testschool,testroster,"N/A", testName2, "Red", "120","100%", "Yes", "true");
		waitTime();		
		sechedulePage.scheduleTest(testschool,testroster, "N/A", testName3, "Red", "120","100%", "Yes", "true");
		waitTime();
		sechedulePage.scheduleTest(testschool,testroster, "N/A", testName4, "Red", "120","100%", "Yes", "true");
		waitTime();		
		sechedulePage.scheduleTest(testschool,testroster, "N/A", testName5, "Red", "120","100%", "Yes","true");
		waitTime();
		sechedulePage.scheduleTest(testschool,testroster, "N/A", testName6, "Red", "120","100%", "Yes","true");
		waitTime();
		sechedulePage.scheduleTest(testschool,testroster, "N/A", testName7, "Red", "120","100%", "Yes","false");
		waitTime();
					
	   loginPage = sechedulePage.logOut();
	
	}
	
	@Test(priority = 1)
	public void  takeTest(){
		driver.get(url);
	    loginPage = new Login(driver);
		customeWaitTime(5);
		deliveryPage = dashBoardPage.goToDelivery();
		customeWaitTime(5);
		Assert.assertTrue(deliveryPage.takeAndVefiryTestResults("100%",
				"Escuela,1,Space with Chocolate,3,SPACE,4,School,5,Barrio,2"));
		
	}
	
	@Test(priority = 2)
	public void testAnswerMaskingTool(){
			driver.get(url);
			loginPage = new Login(driver);
		    customeWaitTime(5);
		    System.out.println("******** logging as the student ********");
		    dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"),unitytestdata.getProperty("genericPassword"));
			waitTime();
			dashBoardPage.addTiles();
			waitTime();
			deliveryPage = dashBoardPage.goToDelivery();
			deliveryPage.startScheduledTest(testid);
			softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.answerMaskingIcon));
			softAssert.assertTrue(deliveryPage.verifyByDefaultAnswerUnMasked());
			deliveryPage.waitForAnElementAndClick(deliveryPage.firstItem);
			waitTime();
			softAssert.assertTrue(deliveryPage.verifyAnswerMasking());
			deliveryPage.waitForAnElementAndClick(deliveryPage.firstItem);
			waitTime();
			deliveryPage.verifyAnswerMaskToggling();

			
	}
	
	
	@Test(priority = 3)
	public void testLineReaderTool(){
		driver.get(url);
	    loginPage = new Login(driver);
		    customeWaitTime(5);
		    System.out.println("******** logging as the student ********");
		    dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"),unitytestdata.getProperty("genericPassword"));
			waitTime();
			dashBoardPage.addTiles();
			waitTime();
			deliveryPage = dashBoardPage.goToDelivery();
			deliveryPage.startScheduledTest(testid1);
			softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.lineReaderIcon));
			deliveryPage.verifyLineReaderPopUpToggling();
			deliveryPage.waitForAnElementAndClick(deliveryPage.firstItem);
			deliveryPage.waitForAnElementAndClick(deliveryPage.lineReaderIcon);

	}
	
	@Test(priority = 4)
	public void testMagnifierTool(){
		    driver.get(url);
		    loginPage = new Login(driver);
		    customeWaitTime(5);
		    System.out.println("******** logging as the student ********");
		    dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"),unitytestdata.getProperty("genericPassword"));
			waitTime();
			dashBoardPage.addTiles();
			waitTime();
			deliveryPage = dashBoardPage.goToDelivery();
			deliveryPage.startScheduledTest(testid2);
			softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.magifierIcon));
			deliveryPage.verifyMagnifierPopUpToggling(scale_1_5x);
			deliveryPage.startScheduledTest(testid3);
			softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.magifierIcon));
			deliveryPage.verifyMagnifierPopUpToggling(scale_2_0x.split(".")[0]);
			deliveryPage.startScheduledTest(testid4);
			softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.magifierIcon));
			deliveryPage.verifyMagnifierPopUpToggling(scale_2_5x);
	}
	
	
	@Test(priority = 5)
	public void testAlternateColorTextAndBackgroundTool (){
		driver.get(url);
	    loginPage = new Login(driver);
		    customeWaitTime(5);
		    System.out.println("******** logging as the student ********");
		    dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"),unitytestdata.getProperty("genericPassword"));
			waitTime();
			dashBoardPage.addTiles();
			waitTime();
			deliveryPage = dashBoardPage.goToDelivery();
			deliveryPage.startScheduledTest(testid5);
			softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.adjustColorIcon));
			
			deliveryPage.selectColorAndBackGround("Black on White");
			softAssert.assertTrue(deliveryPage.waitAndGetElementAttribute(deliveryPage.displayedItem, "style").contains(unitytestdata.getProperty("blackColor")));
			softAssert.assertTrue(deliveryPage.waitAndGetElementAttribute(deliveryPage.displayedItem, "style").contains(unitytestdata.getProperty("whiteBGColor")));
			
			
			deliveryPage.selectColorAndBackGround("White on Black");
			softAssert.assertTrue(deliveryPage.waitAndGetElementAttribute(deliveryPage.displayedItem, "style").contains(unitytestdata.getProperty("whiteColor")));
			softAssert.assertTrue(deliveryPage.waitAndGetElementAttribute(deliveryPage.displayedItem, "style").contains(unitytestdata.getProperty("blackBGColor")));
			
			deliveryPage.selectColorAndBackGround("Black on Cream");
			softAssert.assertTrue(deliveryPage.waitAndGetElementAttribute(deliveryPage.displayedItem, "style").contains(unitytestdata.getProperty("blackColor")));
			softAssert.assertTrue(deliveryPage.waitAndGetElementAttribute(deliveryPage.displayedItem, "style").contains(unitytestdata.getProperty("creamBGColor")));
			
			deliveryPage.selectColorAndBackGround("Black on Light Blue");
			softAssert.assertTrue(deliveryPage.waitAndGetElementAttribute(deliveryPage.displayedItem, "style").contains(unitytestdata.getProperty("blackColor")));
			softAssert.assertTrue(deliveryPage.waitAndGetElementAttribute(deliveryPage.displayedItem, "style").contains(unitytestdata.getProperty("lightBlueBGColor")));
			
			deliveryPage.selectColorAndBackGround("Black on Light Magenta");
			softAssert.assertTrue(deliveryPage.waitAndGetElementAttribute(deliveryPage.displayedItem, "style").contains(unitytestdata.getProperty("blackColor")));
			softAssert.assertTrue(deliveryPage.waitAndGetElementAttribute(deliveryPage.displayedItem, "style").contains(unitytestdata.getProperty("lightmagentaBGColor")));

			
			deliveryPage.selectColorAndBackGround("Yellow on Blue");
			softAssert.assertTrue(deliveryPage.waitAndGetElementAttribute(deliveryPage.displayedItem, "style").contains(unitytestdata.getProperty("yellowColor")));
			softAssert.assertTrue(deliveryPage.waitAndGetElementAttribute(deliveryPage.displayedItem, "style").contains(unitytestdata.getProperty("blueBGColor")));

	}
	
	@Test(priority = 6)
	public void testDeliveryWithReportableScore(){
		driver.get(url);
	    loginPage = new Login(driver);
		    customeWaitTime(5);
		    System.out.println("******** logging as the student ********");
		    dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"),unitytestdata.getProperty("genericPassword"));
			waitTime();
			dashBoardPage.addTiles();
			waitTime();
			deliveryPage = dashBoardPage.goToDelivery();
			deliveryPage.startScheduledTest(testid6);
	
	}
	
	@Test(priority = 7)
	public void testDeliveryWithNonReportableScore(){
		driver.get(url);
	    loginPage = new Login(driver);
		    customeWaitTime(5);
		    System.out.println("******** logging as the student ********");
		    dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"),unitytestdata.getProperty("genericPassword"));
			waitTime();
			dashBoardPage.addTiles();
			waitTime();
			deliveryPage = dashBoardPage.goToDelivery();
			deliveryPage.startScheduledTest(testid7);
	
	}
	/**
	 * Login as teacher 
	 * Create test 
	 * Schedule test
	 * Login as student
	 * Start test
	 * exit test without save the answer
	 * Verify the confirmation message
	 */
	
	@Test(priority = 8)
	public void testVerifyExitMessgage(){
		driver.get(url);
		loginPage = new Login(driver);
		System.out.println("******** logging as teacher   ********");
		dashBoardPage = loginPage.loginSuccess(teacher1,
				genericPassword);
		itemsPage = dashBoardPage.goToItems();
		
		long timestamp = System.currentTimeMillis();
		itemName = "I_" + timestamp;
		testName = "T_"+ timestamp;
		
		itemsPage.createItem(itemName, "My Items");
		itemsPage.backToDashboard();
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.createTest(testName, "My Tests", itemName);
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		testCreationPage.backToDashboard();
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(5);
		sechedulePage.scheduleTest(school, roster, "N/A", testName, "Green", "120", "100%", "No", "true");
		returnToDashboard();
		dashBoardPage.logOut();
		customeWaitTime(5);
		System.out.println("******** logging as student   ********");
		dashBoardPage = loginPage.loginSuccess(student1,genericPassword);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		deliveryPage = dashBoardPage.goToDelivery();
		customeWaitTime(5);
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		deliveryPage.waitForElementAndClick(deliveryPage.exitButton);
		customeWaitTime(2);
		Assert.assertEquals(deliveryPage.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("testExit").trim());
		deliveryPage.waitForElementAndClick(deliveryPage.globalModalOKCancelSaveButton);
    	customeWaitTime(2);
		
	}
	

	
}
