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

	String itemBankName ;
	String itemName ;
	String copiedItemName;
	String testBankName;
	String testName;
	String extendedTextEntry;
	String testSchool;
	String createdTestId;
	
	String extendedTextAnswer;
	
	String testrosterName;
	
	int itemCount = 10;
	
	String handScoreProfile;
	
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
	
	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
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
		testName = "T_" + timestamp;
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.createTestWithMultipleItems(testName,
				testBankName, itemBankName, itemCount);
		customeWaitTime(5);
		testCreationPage.searchTest(testName);
		createdTestId = testCreationPage.getTestId();
		returnToDashboard();
		customeWaitTime(5);
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
		System.out.println("******** Event creation ********");
		
		sechedulePage.scheduleTest(testSchool,
				testrosterName, "N/A", testName, "Red", "120",
				"100%", "Yes");
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
		deliveryPage.startScheduledTest(createdTestId);
		deliveryPage.takeTest(true , 1 ,extendedTextEntry , extendedTextAnswer);
		
		
	
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
		softAssert.assertEquals(testName, deliveryPage.getTestinHistoryTable(createdTestId));
		softAssert.assertEquals("Score Pending", deliveryPage.getTestPercentCorrect(createdTestId));
		softAssert.assertEquals("10", deliveryPage.getTestNoOfItems(createdTestId));
		softAssert.assertTrue(deliveryPage.getTestCompletedStatus(createdTestId));
		returnToDashboard();
		waitTime();
		loginPage = dashBoardPage.logOut();
		System.out.println("************************************************");
		System.out.println("******** logging as the created teacher ********");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		reportPage = dashBoardPage.goToReports();
		waitTime();
		reportPage.viewReport();
		waitTime();
		reportPage.filterReportByClassRoster(testrosterName);
		waitTime();
		reportPage.filterReportByContentArea("N/A");
		waitTime();
		reportPage.openTestEventDetail(testName);
		softAssert.assertEquals("completed", reportPage.getScoreStatusinTestDetail("student11"));
		reportPage.verifyStudentHandScore("student11", 10, "P");
		handScoringPage = reportPage.navigateToHandScore();
		handScoringPage.scoreTest(testName, 1, 10, "1");
		handScoringPage.backToDashboard();
		reportPage = dashBoardPage.goToReports();
		reportPage.viewReport();
		waitTime();
		reportPage.filterReportByClassRoster(testrosterName);
		waitTime();
		reportPage.filterReportByContentArea("N/A");
		waitTime();
		reportPage.openTestEventDetail(testName);
		customeWaitTime(5);
		softAssert.assertEquals("scored", reportPage.getScoreStatusinTestDetail("student11"));
		reportPage.verifyStudentHandScore("student11", 10, "1");
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
		softAssert.assertEquals(testName, deliveryPage.getTestinHistoryTable(createdTestId));
		softAssert.assertEquals("Score Pending", deliveryPage.getTestPercentCorrect(createdTestId));
		softAssert.assertEquals("10", deliveryPage.getTestNoOfItems(createdTestId));
		softAssert.assertTrue(deliveryPage.getTestCompletedStatus(createdTestId));
		returnToDashboard();
		waitTime();
		loginPage = dashBoardPage.logOut();
		System.out.println("************************************************");
		System.out.println("******** logging as the created teacher ********");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainTeacher"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		handScoringPage = dashBoardPage.goToHandScoring();
		handScoringPage.startHandScoring(testName);
		handScoringPage.backToDashboard();
		reportPage = dashBoardPage.goToReports();
		reportPage.viewReport();
		waitTime();
		reportPage.filterReportByClassRoster(testrosterName);
		waitTime();
		reportPage.filterReportByContentArea("N/A");
		waitTime();
		reportPage.openTestEventDetail(testName);
		customeWaitTime(5);
		softAssert.assertEquals("scored", reportPage.getScoreStatusinTestDetail("student11"));
		reportPage.verifyStudentHandScore("student11", 10, "1");
	}
}
