package tests;

import java.io.File;
import java.util.Properties;

import generic.BaseTest;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Delivery;
import pages.HandScoring;
import pages.ItemImport;
import pages.Items;
import pages.ItemsBank;
import pages.Login;
import pages.Reports;
import pages.Role;
import pages.Schedule;
import pages.TestCreation;
import pages.TestsBank;	

public class HandScoringTests extends BaseTest {
	Login loginPage;
	DashBoard dashBoardPage;
	ItemsBank itemsBankPage;
	Items itemsPage;
	TestCreation testCreationPage;
	Schedule sechedulePage;
	Reports reportPage;
	TestsBank testBankPage;
	ItemImport itemsImportPage;
	HandScoring handScoringPage;
	Delivery deliveryPage;
	Role rolePage;
	
	String itemBankName , itemName ,copiedItemName, testBankName , testName1 , testName2, testName3, testName4, testName5, testName6, extendedTextEntry,
	testSchool, createdTestId1, createdTestId2, createdTestId3, createdTestId4, createdTestId5, notStartedTest, createdTestId6, extendedTextAnswer, testrosterName , handScoreProfile;
	
	int itemCount = 10;
		
	String desc = "description";
	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator + "media" + File.separator ;
	
	String unityTestDataFile = "src" + File.separator + "resources"
			+ File.separator + "unitytestdata.properties";
	
	Properties unitytestdata;
	long timestamp;
	


	public HandScoringTests() {
		super();
	}

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		extendedTextEntry = unitytestdata.getProperty("interactionExtededText"); 
		handScoreProfile = unitytestdata.getProperty("handScoreProfile");
		testSchool = unitytestdata.getProperty("testSchool");
		testrosterName = unitytestdata.getProperty("testrosterName");
		extendedTextAnswer = unitytestdata.getProperty("extendedTextAnswer");

	}
	
	@BeforeClass
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		
		System.out.println("******** logging as the  admin  ********");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainAdmin"),
				unitytestdata.getProperty("defaultAutoPassword"));
		dashBoardPage.addTiles();
		waitTime();
		rolePage = dashBoardPage.goToRole();
		rolePage.enableTile("Teacher", "handscoring", true);
		
		rolePage.navigateToHomePageByBackButton();
		
		loginPage = rolePage.logOut();
		
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		timestamp = System.currentTimeMillis();
		
		itemBankName = "IB_" + timestamp;
		itemsBankPage.createBank(itemBankName, desc);
		returnToDashboard();
		itemsPage = dashBoardPage.goToItems();
		itemName = "I_" + timestamp;
		itemsPage.createItem(itemName, itemBankName, extendedTextEntry, handScoreProfile, "Not required");
		itemsPage.searchItem(itemName);
		itemsPage.addStandards();
        copiedItemName = "copy_" + itemName;
		itemsPage.copyMultipleItems(itemBankName, itemName, copiedItemName, 1, itemCount -1);
		returnToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		testBankName = "TB_" + timestamp;
		testBankPage.createBank(testBankName, desc);
		returnToDashboard();
		testName1 = "T_1_" + timestamp;
		testName2 = "T_2_" + timestamp;
		testName3 = "T_3_" + timestamp;
		testName4 = "Dont start_4_" + timestamp;
		
		testName5 = "HandScore" + timestamp;
		
		testName6 = "TestSearch_"  + timestamp;


		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.createTestWithMultipleItems(testName1,
				testBankName, itemBankName, itemCount);
		testCreationPage.createTestWithMultipleItems(testName2,
				testBankName, itemBankName, itemCount);
		testCreationPage.createTestWithMultipleItems(testName3,
				testBankName, itemBankName, itemCount);
		testCreationPage.createTestWithMultipleItems(testName4,
				testBankName, itemBankName, itemCount);
		
		testCreationPage.createTestWithMultipleItems(testName5,
				testBankName, itemBankName, itemCount);
		testCreationPage.createTestWithMultipleItems(testName6,
				testBankName, itemBankName, itemCount);
		customeWaitTime(5);
		
		testCreationPage.searchTest(testName1);
		createdTestId1 = testCreationPage.getTestId();
		testCreationPage.searchTest(testName2);
		createdTestId2 = testCreationPage.getTestId();
		testCreationPage.searchTest(testName3);
		createdTestId3 = testCreationPage.getTestId();
		testCreationPage.searchTest(testName4);
		createdTestId4 = testCreationPage.getTestId();
		
		testCreationPage.searchTest(testName5);
		createdTestId5 = testCreationPage.getTestId();
		
		returnToDashboard();
		customeWaitTime(5);
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
		System.out.println("******** Event creation ********");
		sechedulePage.scheduleTest(testSchool,
				testrosterName, "N/A", testName1, "Red", "120",
				"100%", "Yes","true");
		sechedulePage.scheduleTest(testSchool,
				testrosterName, "N/A", testName2, "Red", "120",
				"100%", "Yes","true");
		sechedulePage.scheduleTest(testSchool,
				testrosterName, "N/A", testName3, "Red", "120",
				"100%", "Yes","true");
		sechedulePage.scheduleTest(testSchool,
				testrosterName, "N/A", testName4, "Red", "120",
				"100%", "Yes","true");
		sechedulePage.scheduleTest(testSchool,
				testrosterName, "N/A", testName5, "Red", "120",
				"100%", "Yes","true");
		sechedulePage.scheduleTest(testSchool,
				testrosterName, "N/A", testName6, "Red", "120",
				"100%", "Yes","true");
		waitTime();
		returnToDashboard();
		loginPage = dashBoardPage.logOut();
		System.out.println("************************************************");
		waitTime();
		System.out
				.println("******** logging as the first  student ********");
		
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStdudent11"),
				unitytestdata.getProperty("genericPassword"));
		
		System.out.println(dashBoardPage.addTiles());
		waitTime();
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		notStartedTest =  deliveryPage.getScheduledTest(createdTestId4);
		waitTime();
		deliveryPage.startScheduledTest(createdTestId1);
		deliveryPage.takeTest(true , 1 ,extendedTextEntry , extendedTextAnswer);
		deliveryPage.startScheduledTest(createdTestId2);
		deliveryPage.takeTest(true , 1 ,extendedTextEntry , extendedTextAnswer);
		deliveryPage.startScheduledTest(createdTestId3);
		deliveryPage.exitAndFinishTest();
		
		deliveryPage.startScheduledTest(createdTestId4);
		deliveryPage.takeTest(true , 1 ,extendedTextEntry , extendedTextAnswer);
		//
		
		softAssert.assertEquals(testName1, deliveryPage.getTestinHistoryTable(createdTestId1));
		softAssert.assertEquals("Score Pending", deliveryPage.getTestPercentCorrect(createdTestId1));
		softAssert.assertEquals("10", deliveryPage.getTestNoOfItems(createdTestId1));
		softAssert.assertTrue(deliveryPage.getTestCompletedStatus(createdTestId1));
		
		softAssert.assertEquals(testName2, deliveryPage.getTestinHistoryTable(createdTestId2));
		softAssert.assertEquals("Score Pending", deliveryPage.getTestPercentCorrect(createdTestId2));
		softAssert.assertEquals("10", deliveryPage.getTestNoOfItems(createdTestId2));
		softAssert.assertTrue(deliveryPage.getTestCompletedStatus(createdTestId2));
		
		softAssert.assertEquals(testName3, deliveryPage.getTestinHistoryTable(createdTestId3));
		softAssert.assertEquals("Score Pending", deliveryPage.getTestPercentCorrect(createdTestId3));
		softAssert.assertEquals("10", deliveryPage.getTestNoOfItems(createdTestId3));
		softAssert.assertTrue(deliveryPage.getTestCompletedStatus(createdTestId3));
		returnToDashboard();
		waitTime();
		loginPage = dashBoardPage.logOut();
		System.out.println("************************************************");
		
		
 }
	
	/**
	 * Login into the unity as a valid teacher
	 * Create the item bank 
	 * Create extended text entry item
	 * Create 10 copied items
	 * Create test by using above items
	 * Schedule the test 
	 * Login as valid roster student 
	 * Start and complete the test
	 * See the history report
	 * Login as teacher 
	 * go to the test report detail page
	 * Click  the hand scoring this button
	 * give the score in hadn score tile
	 * Go to the test detail report page 
	 * see the report for updated report
	 */
	@Test(priority = 1)
	public void testHandScoreFromReportDetail(){
		System.out.println("******** logging as the created teacher ********");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		reportPage = dashBoardPage.goToReports();
		waitTime();
		//reportPage.viewReport();
		waitTime();
		reportPage.filterReportByClassRoster(testrosterName);
		waitTime();
		reportPage.filterReportByContentArea("N/A");
		waitTime();
		reportPage.openTestEventDetail(testName1);
		softAssert.assertEquals("completed", reportPage.getScoreStatusinTestDetail("urostudent1"));
		reportPage.verifyStudentHandScore("urostudent1", 10, "P");
		handScoringPage = reportPage.navigateToHandScore();
		handScoringPage.scoreTest(testName1, 1, 10, "1");
		handScoringPage.backToDashboard();
		reportPage = dashBoardPage.goToReports();
		//reportPage.viewReport();
		waitTime();
		reportPage.filterReportByClassRoster(testrosterName);
		waitTime();
		reportPage.filterReportByContentArea("N/A");
		waitTime();
		reportPage.openTestEventDetail(testName1);
		customeWaitTime(5);
		softAssert.assertEquals("scored", reportPage.getScoreStatusinTestDetail("urostudent1"));
		reportPage.verifyStudentHandScore("urostudent1", 10, "1");
		reportPage.logOut();
		
		
		
		
	}
	
	/**
	 * Login into the unity as a valid teacher
	 * Create the item bank 
	 * Create extended text entry item
	 * Create 10 copied items
	 * Create test by using above items
	 * Schedule the test 
	 * Login as valid roster student 
	 * Start and complete the test
	 * See the history report
	 * Login as teacher 
	 * go to the test report detail page
	 * go to the hand scoring tile
	 * give the score 
	 * Go to the test detail report page 
	 * see the report for updated report
	 */
	
	@Test(priority = 2)
	public void testHandScoring(){
		System.out.println("************************************************");
		System.out.println("******** logging as the created teacher ********");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		handScoringPage = dashBoardPage.goToHandScoring();
		handScoringPage.startHandScoring(testName2);
		handScoringPage.backToDashboard();
		reportPage = dashBoardPage.goToReports();
		//reportPage.viewReport();
		waitTime();
		reportPage.filterReportByClassRoster(testrosterName);
		waitTime();
		reportPage.filterReportByContentArea("N/A");
		waitTime();
		reportPage.openTestEventDetail(testName2);
		customeWaitTime(5);
		softAssert.assertEquals("scored", reportPage.getScoreStatusinTestDetail("urostudent1"));
		reportPage.verifyStudentHandScore("urostudent1", 10, "1");
		reportPage.logOut();

	}
	
	/**
	 * Login as teacher 
	 * Create test having extended text items
	 * Schedule test
	 * Login as student
	 * Start test 
	 * Exit test test without answer
	 * Finish test
	 * Login  as teacher
	 * Click on Hand Score tile 
	 * Hand score for item
	 * Finish hand score
	 * Check the report
	 * 
	 */
	
	@Test(priority = 3)
	public void testHandScoreForExitTestWithOutAnswer(){
		System.out.println("************************************************");
		System.out.println("******** logging as the created teacher ********");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		handScoringPage = dashBoardPage.goToHandScoring();
		handScoringPage.startHandScoring(testName3);
		handScoringPage.backToDashboard();
		reportPage = dashBoardPage.goToReports();
		reportPage.viewReport();
		waitTime();
		reportPage.filterReportByClassRoster(testrosterName);
		waitTime();
		reportPage.filterReportByContentArea("N/A");
		waitTime();
		reportPage.openTestEventDetail(testName3);
		customeWaitTime(5);
		softAssert.assertEquals("scored", reportPage.getScoreStatusinTestDetail("urostudent1"));
		reportPage.verifyStudentHandScore("urostudent1", 10, "1");
		reportPage.logOut();

	}
	
	
	
	/**
	 * Login as teacher
	 * Create item bank 
	 * Create Items
	 * Create Test
	 * Schedule test
	 * Login as student dont start test
	 * Login as teacher
	 * Go to the hand score tile
	 * Search the not started test
	 * Preview the test
	 * Student list should not appear
	 * 
	 */
	
	@Test(priority = 4)
	public  void testHandScoreListingForNotStartedTest(){
		System.out.println("************************************************");
		System.out.println("******** logging as the created teacher ********");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		handScoringPage = dashBoardPage.goToHandScoring();
		waitTime();
		handScoringPage.filterTest(testName4);
		waitTime();
		handScoringPage.waitForElementAndClick(handScoringPage.previewIconList);
		softAssert.assertTrue(handScoringPage.waitForElementInVisible("//tbody[@class='table-data']/tr"));
		reportPage.logOut();
	}
	
	@Test(priority = 5)
	public void testFilterHandScoringTests(){
		System.out.println("************************************************");
		System.out.println("******** logging as the created teacher ********");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		handScoringPage = dashBoardPage.goToHandScoring();
		waitTime();
		
		for(int i=0; i<handScoringPage.getCountOfTestsFound(); i++){
			handScoringPage.clickOnEditTest(i);
			
		}
	}
	
	@Test(priority = 5)
	public void testHandScoreLinkEnabled(){
		/*System.out.println("******** logging as the  admin  ********");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainAdmin"),
				unitytestdata.getProperty("defaultAutoPassword"));
		dashBoardPage.addTiles();
		waitTime();
		rolePage = dashBoardPage.goToRole();
		rolePage.enableTile("Teacher", "handscoring", true);
		
		rolePage.navigateToHomePageByBackButton();
		
		loginPage = rolePage.logOut();*/
		
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		dashBoardPage.addTiles();
		reportPage = dashBoardPage.goToReports();
		waitTime();
		reportPage.filterReportByClassRoster(testrosterName);
		waitTime();
		reportPage.filterReportByContentArea("N/A");
		waitTime();
		reportPage.openTestEventDetail(testName4);
		customeWaitTime(5);
		softAssert.assertTrue(reportPage.waitForElementVisible(reportPage.HandscoreThisTestButton));

		
	}
	
	@Test(priority = 6)
	public void testHandScoreLinkDisbled(){
		System.out.println("******** logging as the  admin  ********");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainAdmin"),
				unitytestdata.getProperty("defaultAutoPassword"));
		dashBoardPage.addTiles();
		waitTime();
		rolePage = dashBoardPage.goToRole();
		rolePage.enableTile("Teacher", "handscoring", false);
		rolePage.navigateToHomePageByBackButton();
		loginPage = rolePage.logOut();
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		dashBoardPage.addTiles();
		reportPage = dashBoardPage.goToReports();
		waitTime();
		reportPage.filterReportByClassRoster(testrosterName);
		waitTime();
		reportPage.filterReportByContentArea("N/A");
		waitTime();
		reportPage.openTestEventDetail(testName4);
		customeWaitTime(5);
		softAssert.assertFalse(reportPage.waitForElementVisible(reportPage.HandscoreThisTestButton));
		
	}
	
	@Test(priority = 7)
	public void testFilterHandScoreTestbyName(){
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		handScoringPage = dashBoardPage.goToHandScoring();
		waitTime();
		handScoringPage.filterTest(testName6);
		softAssert.assertTrue(handScoringPage.waitAndGetElementText(handScoringPage.testInListing).contains(testName6));
	}
	
	@Test(priority = 8)
	public void testFilterHandScoreTestbyRoster(){
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		handScoringPage = dashBoardPage.goToHandScoring();
		waitTime();
		handScoringPage.filterClass(testrosterName);
		customeWaitTime(5);
		for(int i = 0 ; i < handScoringPage.getRosterList().size(); i++){
			softAssert.assertTrue(handScoringPage.waitAndGetElementText(handScoringPage.getRosterList().get(i)).contains(testrosterName));
		}
	}
	
	@Test(priority = 9)
	public void testFilterHandScoreTestbyContentArea(){
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		handScoringPage = dashBoardPage.goToHandScoring();
		waitTime();
		handScoringPage.filterContentArea("N/A");
		customeWaitTime(5);
		for(int i = 0 ; i < handScoringPage.getContentAreaList().size(); i++){
			softAssert.assertTrue(handScoringPage.waitAndGetElementText(handScoringPage.getContentAreaList().get(i)).contains(testrosterName));
		}
	}
	
	@Test(priority = 10)
	public void testFilterHandScoreTestbyItems(){
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		handScoringPage = dashBoardPage.goToHandScoring();
		waitTime();
		handScoringPage.filterHandScoringItems("Yes");
		customeWaitTime(5);
		softAssert.assertFalse(handScoringPage.getResult().isEmpty());
	}
}
