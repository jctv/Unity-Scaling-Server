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
	String testBank;
	String itemBank;
	String copiedItemName;
	
	String testid;
	
	String testroster;
	String testschool;
	
	String interactionChoice;
	String simpleMatchScoreProfile;
	String choiceCorrectAnswer;
	
	Schedule sechedulePage;
	
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
		System.out.println("******** logging as student  ********");
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
		customeWaitTime(5);
		testCreationPage.searchTest(testName);
		testCreationPage.waitForElementAndClick(testCreationPage.editIconList); 
		customeWaitTime(5);
		testid = testCreationPage.getTestId();
		testCreationPage.enableTestTools("Answer Masking");
		returnToDashboard();
		customeWaitTime(5);
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
	    System.out.println("******** Event creation ********");
	   sechedulePage.scheduleTest(testschool,
			   testroster, "N/A", testName, "Red", "120",
		"100%", "Yes");
		waitTime();
	   loginPage = sechedulePage.logOut();
	
	}
	
	@Test(priority = 1)
	public void  takeTest(){
		customeWaitTime(5);
		deliveryPage = dashBoardPage.goToDelivery();
		customeWaitTime(5);
		Assert.assertTrue(deliveryPage.takeAndVefiryTestResults("100%",
				"Escuela,1,Space with Chocolate,3,SPACE,4,School,5,Barrio,2"));
		
	}
	
	@Test(priority = 0)
	public void testAnswerMaskingTool(){
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
	
	/**
	 * Login as teacher 
	 * Create test 
	 * Schedule test
	 * Login as student
	 * Start test
	 * exit test without save the answer
	 * Verify the confirmation message
	 */
	
	@Test(priority = 3)
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
		sechedulePage.scheduleTest(school, roster, "N/A", testName, "Green", "120", "100%", "No");
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
