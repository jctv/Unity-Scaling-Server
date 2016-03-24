package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;

import generic.BaseTest;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.ClassRoster;
import pages.DashBoard;
import pages.Delivery;
import pages.HandScoring;
import pages.Items;
import pages.ItemsBank;
import pages.Login;
import pages.Organization;
import pages.Reports;
import pages.Schedule;
import pages.TestCreation;
import pages.TestsBank;
import pages.Users;

public class ReportTest extends BaseTest {

	//HappyPathTest Nav;
	
	Login loginPage;
	DashBoard dashBoardPage;
	Items itemsPage;
	Users usersPage;
	ClassRoster classRosterPage;
	TestCreation testCreationPage;
	Schedule sechedulePage;
	Delivery deliveryPage;
	HandScoring handScoringPage;
	Reports reportsPage;
	Organization organizationPage;
	ItemsBank itemsBankPage;
	TestsBank testBankPage;
	
	Properties unitytestdata;

	String itemBankName ;
	String copiedItemName;
	String itemName ;
	String testBankName ;
	
	String testName1 , testName2 , testName3 ,testName4 ,testName5 ,testName6 , testName7 , testName8,testName9 ,testName10;
	String itemBankName1  , itemBankName2 , itemBankName3 , itemBankName4 , itemBankName5 , itemBankName6, itemBankName7 ,itemBankName8 , itemBankName9 ,itemBankName10 ;
	String createdTestId1 , createdTestId2 , createdTestId3 , createdTestId4 ,createdTestId5 ,createdTestId6 , createdTestId7 ,createdTestId8,createdTestId9,createdTestId10;

	String itemStrandCategory;
	
	String resources = "src" + File.separator + "resources"
			+ File.separator;
	String unitytestDataFile = resources + "unitytestdata.properties";
	
	String schoolName = "Auto School";
	String rosterName = "autoroster";
	
	String copyItemName;
	String interactionChoice;
	String interactionTextEntry;
	String simpleMatchScoreProfile;
	String mapScoreProfile;
	String handScoreProfile;
	String choiceCorrectAnswer;
	
	String testroster;
	String testschool;

	String testid;
	int itemCount =5;
	String  textEntryCorrcetAnswer = "Auto Text Entry";
	
	protected String teacherUserName = "";
	protected String genericPassword = "12345";
	protected String autoStudent1 = "S17_16_311";
	protected String autoStudent2 = "S17_16_322";
	protected String autoStudentPassword = "12345";
	
	long timestamp;


	public ReportTest() {
		super();

	}
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unitytestDataFile);
		interactionChoice = unitytestdata.getProperty("interactionChoice");
		simpleMatchScoreProfile = unitytestdata
				.getProperty("simpleMatchScoreProfile");
		choiceCorrectAnswer = unitytestdata.getProperty("choiceCorrectAnswer");
		
		interactionTextEntry = unitytestdata.getProperty("interactionTextEntry");
		mapScoreProfile = unitytestdata.getProperty("mapScoreProfile");
		handScoreProfile = unitytestdata.getProperty("handScoreProfile");

		testroster = unitytestdata.getProperty("testRoster");
		testschool =  unitytestdata.getProperty("testSchool");	
	}

	

	@BeforeClass
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		System.out.println("******** logging as Teacher ********");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testTeacher1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
				
		itemBankName1 = "IB1_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName1, "desc");

		itemBankName2 = "IB2_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName2, "desc");

		itemBankName3 = "IB3_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName3, "desc");

		itemBankName4 = "IB4_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName4, "desc");

		itemBankName5 = "IB5_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName5, "desc");

		itemBankName6 = "IB6_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName6, "desc");

		itemBankName7 = "IB7_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName7, "desc");

		itemBankName8 = "IB8_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName8, "desc");

		itemBankName9 = "IB9_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName9, "desc");

		itemBankName10 = "IB10_" + System.currentTimeMillis();
		itemsBankPage.createBank(itemBankName10, "desc");
		returnToDashboard();
		
		customeWaitTime(2);
		
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(2);
		itemName = "I1_" + itemBankName1;
		System.out.println("******** " + itemName+ "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName1 ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
		itemStrandCategory = itemsPage.getStrandCategory();
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(1);
		
		itemName = "I2_" + itemBankName2;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName2 ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
		itemStrandCategory = itemsPage.getStrandCategory();
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(1);
		
		
		
		itemName = "I3_" + itemBankName3;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName3 ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
		
		copiedItemName = "copy" + itemName;
		itemsPage.copyMultipleItems(itemBankName3, itemName, copiedItemName, 1,9);
		itemStrandCategory = itemsPage.getStrandCategory();
		customeWaitTime(2);
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(1);
		
		customeWaitTime(2);
		itemName = "I4_" + itemBankName4;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName4 ,interactionTextEntry , simpleMatchScoreProfile , textEntryCorrcetAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
        copiedItemName = "copy" + itemName;
		
		itemsPage.copyMultipleItems(itemBankName4, itemName, copiedItemName, 1,9);
		customeWaitTime(2);
		String itemStrandCategory = itemsPage.getStrandCategory();
		customeWaitTime(2);
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(1);
		
		
		
		itemName = "I5_" + itemBankName5;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName5 ,interactionTextEntry , simpleMatchScoreProfile , textEntryCorrcetAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
		itemsPage.copyMultipleItems(itemBankName5, itemName, copiedItemName, 1,9);
		customeWaitTime(2);
		itemStrandCategory = itemsPage.getStrandCategory();
		customeWaitTime(2);
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(1);
		
		
		itemName = "I6_" + itemBankName6;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName6 ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(1);
		itemsPage.searchItem(itemName);
		customeWaitTime(1);
		itemsPage.addStandards();
		customeWaitTime(1);
		
		itemName = "I7_" + itemBankName7;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName7 ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
		
		
		itemName = "I8_" + itemBankName8;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName8 ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
		
		
		itemName = "I9_" + itemBankName9;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName9 ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
		copiedItemName = "copy_" + itemName;

		itemsPage.copyMultipleItems(itemBankName9, itemName, copiedItemName, 1,9);
		customeWaitTime(2);
		itemStrandCategory = itemsPage.getStrandCategory();
		customeWaitTime(2);
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(1);
		
		itemName = "I10_" + itemBankName10;
		itemsPage.createItem(itemName, itemBankName10, interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);		
		itemsPage.searchItem(itemName);
		itemsPage.addStandards();
		copiedItemName = "copy_" + itemName;
		itemsPage.copyMultipleItems(itemBankName10, itemName, copiedItemName, 1, 4);
		
		
		itemsPage.backToDashboard();
		customeWaitTime(2);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(2);
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, "desc");
		returnToDashboard();
		waitTime();
		testCreationPage = dashBoardPage.goToTestCreation();
		waitTime();
		testName1 = "T1" + testBankName;
		testCreationPage.createTestWithMultipleItems(testName1 , testBankName , itemBankName ,1);
		
		testName2 = "T2" + testBankName;
		testCreationPage.createTestWithMultipleItems(testName2 , testBankName , itemBankName ,1);
		
		testName1 = "T3" + testBankName;
		testCreationPage.createTestWithMultipleItems(testName3 , testBankName , itemBankName ,1);
		testName1 = "T4" + testBankName;
		testCreationPage.createTestWithMultipleItems(testName4 , testBankName , itemBankName ,1);
		testName1 = "T5" + testBankName;
		testCreationPage.createTestWithMultipleItems(testName5 , testBankName , itemBankName ,1);
		testName1 = "T6" + testBankName;
		testCreationPage.createTestWithMultipleItems(testName6 , testBankName , itemBankName ,1);
		testName1 = "T7" + testBankName;
		testCreationPage.createTestWithMultipleItems(testName7 , testBankName , itemBankName ,1);
		testName1 = "T8" + testBankName;
		testCreationPage.createTestWithMultipleItems(testName8 , testBankName , itemBankName ,1);
		testName1 = "T9" + testBankName;
		testCreationPage.createTestWithMultipleItems(testName9 , testBankName , itemBankName ,1);
		testName1 = "T10" + testBankName;
				testCreationPage.createTestWithMultipleItems(testName10, testBankName , itemBankName ,1);

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
		testCreationPage.searchTest(testName6);
		createdTestId6 = testCreationPage.getTestId();		
		testCreationPage.searchTest(testName7);
		createdTestId7 = testCreationPage.getTestId();		
		testCreationPage.searchTest(testName8);
		createdTestId8 = testCreationPage.getTestId();		
		testCreationPage.searchTest(testName9);
		createdTestId9 = testCreationPage.getTestId();		
		testCreationPage.searchTest(testName10);
		createdTestId10 = testCreationPage.getTestId();		
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(2);
		sechedulePage.scheduleTest(testschool, testroster, "N/A", testName1, "Green", "110", "20%", "No");
		sechedulePage.scheduleTest(testschool, testroster, "N/A", testName2, "Green", "110", "20%", "No");
		sechedulePage.scheduleTest(testschool, testroster, "N/A", testName3, "Green", "110", "20%", "No");
		sechedulePage.scheduleTest(testschool, testroster, "N/A", testName4, "Green", "110", "20%", "No");
		sechedulePage.scheduleTest(testschool, testroster, "N/A", testName5, "Green", "110", "20%", "No");
		sechedulePage.scheduleTest(testschool, testroster, "N/A", testName6, "Green", "110", "20%", "No");
		sechedulePage.scheduleTest(testschool, testroster, "N/A", testName7, "Green", "110", "20%", "No");
		sechedulePage.scheduleTest(testschool, testroster, "N/A", testName8, "Green", "110", "20%", "No");
		sechedulePage.scheduleTest(testschool, testroster, "N/A", testName9, "Green", "110", "20%", "No");
		sechedulePage.scheduleTest(testschool, testroster, "N/A", testName10, "Green", "110", "100%", "Yes");

			
		
	}
	
	

	@Test(priority = 1)
	public void testVerifyReportWithAllCorrectAnswerForChoiceTypeItems(){
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		dashBoardPage.addTiles();
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName1, deliveryPage.getScheduledTest(createdTestId1));
		deliveryPage.startScheduledTest(createdTestId1);
		deliveryPage.takeTest(true , 4 ,"Choice" , choiceCorrectAnswer);
		dashBoardPage.goToDelivery();
		System.out.println(testName1.equalsIgnoreCase(deliveryPage.getTestinHistoryTable(createdTestId1)));
		Assert.assertTrue(testName1.equalsIgnoreCase(deliveryPage.getTestinHistoryTable(createdTestId1)));
		Assert.assertEquals("100%", deliveryPage.getTestPercentCorrect(createdTestId1));
		Assert.assertEquals("1", deliveryPage.getTestNoOfItems(createdTestId1));
		deliveryPage.backToDashboard();
		customeWaitTime(2);
		dashBoardPage.logOut();
		customeWaitTime(2);	
		dashBoardPage = loginPage.loginSuccess(domain + teacherUserName,
				password);
		customeWaitTime(2);
		reportsPage = dashBoardPage.goToReports();
		customeWaitTime(2);
		//reportsPage.waitForElementAndClick(reportsPage.resetSearchFilter);
		reportsPage.filterReportByContentArea("N/A");
		reportsPage.filterReportByClassRoster("autoRoster");
		customeWaitTime(2);
		Assert.assertEquals(testName1, reportsPage.getTestName(testName1));
		SoftAssert sAssert = new SoftAssert();
		sAssert.assertEquals(testName1, reportsPage.getTestDuration(testName1));
		sAssert.assertEquals(reportsPage.getNoOfStudentCompletedTest(testName1) ,"1");
		sAssert.assertEquals(reportsPage.getNoOfStudentNotStartedTest(testName1),"1");
		sAssert.assertEquals(reportsPage.getNoOfStudentStartedTest(testName1),"0");
		sAssert.assertEquals(reportsPage.getNoOfStudentInQuantile(testName1, 4, "All correct"),"1");
		//Assert.assertEquals(reportsPage.getReportCategory(testName, 1),itemStrandCategory);
		Assert.assertEquals(reportsPage.getReportCategoryPercent(testName1, 1),"100%");

	}
	
	
	@Test(priority = 2)
	public void testVerifyReportWithAllWrongAnswerForChoiceTypeItems(){
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		customeWaitTime(2);
		dashBoardPage.addTiles();
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName2, deliveryPage.getScheduledTest(createdTestId2));
		deliveryPage.startScheduledTest(createdTestId2);
		deliveryPage.takeTest(false , 1 ,"Choice" , choiceCorrectAnswer);
		Assert.assertEquals(testName2, deliveryPage.getTestinHistoryTable(createdTestId2));
		Assert.assertEquals("0%", deliveryPage.getTestPercentCorrect(createdTestId2));
		Assert.assertEquals("1", deliveryPage.getTestNoOfItems(createdTestId2));
		deliveryPage.backToDashboard();
		customeWaitTime(2);
		dashBoardPage.logOut();
		customeWaitTime(2);	
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testTeacher1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		reportsPage = dashBoardPage.goToReports();
		customeWaitTime(2);
		//reportsPage.waitForElementAndClick(reportsPage.resetSearchFilter);
		reportsPage.filterReportByContentArea("N/A");
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		//String testName = "T_Auto_TB_1448424027107";
		Assert.assertEquals(testName2, reportsPage.getTestName(testName2));
		//Assert.assertEquals(testName, reportsPage.getTestDuration(testName));
		Assert.assertEquals(reportsPage.getNoOfStudentCompletedTest(testName2) ,"1");
		Assert.assertEquals(reportsPage.getNoOfStudentNotStartedTest(testName2),"1");
		Assert.assertEquals(reportsPage.getNoOfStudentStartedTest(testName2),"0");
		Assert.assertEquals(reportsPage.getNoOfStudentInQuantile(testName2, 1, "All Wrong"),"1");
		Assert.assertEquals(reportsPage.getReportCategory(testName2, 1),itemStrandCategory);
		Assert.assertEquals(reportsPage.getReportCategoryPercent(testName2, 1),"0%");
		
	}
	
	@Test(priority = 3)
	public void testVerifyReportForTestHavingMultipleItems(){
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		customeWaitTime(2);
		dashBoardPage.addTiles();
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName3, deliveryPage.getScheduledTest(createdTestId3));
		deliveryPage.startScheduledTest(createdTestId3);
		customeWaitTime(2);
		deliveryPage.takeTest(false , 1 ,"Choice" , choiceCorrectAnswer);
		Assert.assertEquals(testName3, deliveryPage.getTestinHistoryTable(createdTestId3));
		Assert.assertEquals("0%", deliveryPage.getTestPercentCorrect(createdTestId3));
		Assert.assertEquals("2", deliveryPage.getTestNoOfItems(createdTestId3));
		deliveryPage.backToDashboard();
		customeWaitTime(2);
		dashBoardPage.logOut();
		customeWaitTime(2);	
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testTeacher1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		reportsPage = dashBoardPage.goToReports();
		customeWaitTime(2);
		//reportsPage.waitForElementAndClick(reportsPage.resetSearchFilter);
		reportsPage.filterReportByContentArea("N/A");
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		//String testName = "T_Auto_TB_1448424027107";
		Assert.assertEquals(testName3, reportsPage.getTestName(testName3));
		//Assert.assertEquals(testName, reportsPage.getTestDuration(testName));
		Assert.assertEquals(reportsPage.getNoOfStudentCompletedTest(testName3) ,"1");
		Assert.assertEquals(reportsPage.getNoOfStudentNotStartedTest(testName3),"1");
		Assert.assertEquals(reportsPage.getNoOfStudentStartedTest(testName3),"0");
		Assert.assertEquals(reportsPage.getNoOfStudentInQuantile(testName3, 1, "All Wrong"),"1");
		//Assert.assertEquals(reportsPage.getReportCategory(testName, 1),itemStrandCategory);
		Assert.assertEquals(reportsPage.getReportCategoryPercent(testName3, 1),"0%");
		
	}
	
	
	@Test(priority = 4)
	public void testVerifyReportForMultipleTextEntryItems(){
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		dashBoardPage.addTiles();
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName4, deliveryPage.getScheduledTest(createdTestId4));
		deliveryPage.startScheduledTest(createdTestId4);
		customeWaitTime(2);
		deliveryPage.takeTest(true , 1 ,"Text Entry" , textEntryCorrcetAnswer);
		Assert.assertEquals(testName4, deliveryPage.getTestinHistoryTable(createdTestId4));
		Assert.assertEquals("20%", deliveryPage.getTestPercentCorrect(createdTestId4));
		Assert.assertEquals("2", deliveryPage.getTestNoOfItems(createdTestId4));
		deliveryPage.backToDashboard();
		customeWaitTime(2);
		dashBoardPage.logOut();
		customeWaitTime(2);	
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testTeacher1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		reportsPage = dashBoardPage.goToReports();
		customeWaitTime(2);
		//reportsPage.waitForElementAndClick(reportsPage.resetSearchFilter);
		reportsPage.filterReportByContentArea("N/A");
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		//String testName = "T_Auto_TB_1448424027107";
		Assert.assertEquals(testName4, reportsPage.getTestName(testName4));
		//Assert.assertEquals(testName, reportsPage.getTestDuration(testName));
		Assert.assertEquals(reportsPage.getNoOfStudentCompletedTest(testName4) ,"1");
		Assert.assertEquals(reportsPage.getNoOfStudentNotStartedTest(testName4),"1");
		Assert.assertEquals(reportsPage.getNoOfStudentStartedTest(testName4),"0");
		Assert.assertEquals(reportsPage.getNoOfStudentInQuantile(testName4, 4, "All Correct"),"1");
		//Assert.assertEquals(reportsPage.getReportCategory(testName, 1),itemStrandCategory);
		Assert.assertEquals(reportsPage.getReportCategoryPercent(testName4, 1),"20%");
		
	}
	
	@Test(priority = 5)
	public void testVerifyReportForMultipleTextEntryItemsForInCorrectAnswer(){
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		dashBoardPage.addTiles();
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName5, deliveryPage.getScheduledTest(createdTestId5));
		deliveryPage.startScheduledTest(createdTestId5);
		customeWaitTime(2);
		deliveryPage.takeTest(false , 1 ,"Text Entry" , "in correct anwer");
		Assert.assertEquals(testName5, deliveryPage.getTestinHistoryTable(createdTestId5));
		Assert.assertEquals("0%", deliveryPage.getTestPercentCorrect(createdTestId5));
		Assert.assertEquals("2", deliveryPage.getTestNoOfItems(createdTestId5));
		deliveryPage.backToDashboard();
		customeWaitTime(2);
		dashBoardPage.logOut();
		customeWaitTime(2);	
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testTeacher1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		reportsPage = dashBoardPage.goToReports();
		customeWaitTime(2);
		//reportsPage.waitForElementAndClick(reportsPage.resetSearchFilter);
		reportsPage.filterReportByContentArea("N/A");
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		//String testName = "T_Auto_TB_1448424027107";
		Assert.assertEquals(testName5, reportsPage.getTestName(testName5));
		//Assert.assertEquals(testName, reportsPage.getTestDuration(testName));
		Assert.assertEquals(reportsPage.getNoOfStudentCompletedTest(testName5) ,"1");
		Assert.assertEquals(reportsPage.getNoOfStudentNotStartedTest(testName5),"1");
		Assert.assertEquals(reportsPage.getNoOfStudentStartedTest(testName5),"0");
		Assert.assertEquals(reportsPage.getNoOfStudentInQuantile(testName5, 1, "All In Correct"),"1");
		//Assert.assertEquals(reportsPage.getReportCategory(testName, 1),itemStrandCategory);
		Assert.assertEquals(reportsPage.getReportCategoryPercent(testName5, 1),"0%");
		
	}
	
	/**
	 * Login as admin
	 * Create item Bank 
	 * create few items 
	 * Create test bank
	 * Create test
	 * Schedule test 
	 * Go to the report page
	 * Go to test detail
	 * verify test status -  not started for assigned student
	 */
	
	@Test(priority = 6)
	public void testVerifyTestDetailReportForAssingedTest(){
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testTeacher1"), unitytestdata.getProperty("genericPassword"));
		reportsPage = dashBoardPage.goToReports();
		customeWaitTime(2);
		reportsPage.filterReportByContentArea("N/A");
		customeWaitTime(1);
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		reportsPage.openTestEventDetail(testName6);
		Assert.assertEquals(testName6, reportsPage.getTestEventTitle());
		Assert.assertEquals("not started".trim(), reportsPage.getTestEventDetail("utostudent1", 3, "Getting test status").trim());
		Assert.assertEquals("not started".trim(), reportsPage.getTestEventDetail("utostudent1", 3, "Getting test status").trim());
		reportsPage.waitForElementAndClick(reportsPage.backToReportLink);
		customeWaitTime(1);

	}
	
	@Test(priority = 7)
	public void testVerifyTestDetailReportForInProgressTest(){
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		dashBoardPage.addTiles();
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName7, deliveryPage.getScheduledTest(createdTestId7));
		deliveryPage.startScheduledTest(createdTestId7);
		customeWaitTime(2);
		deliveryPage.waitForElementAndClick(deliveryPage.exitButton);
		customeWaitTime(2);

		deliveryPage.backToDashboard();
		customeWaitTime(2);
		dashBoardPage.logOut();
		customeWaitTime(2);		
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testTeacher1"), unitytestdata.getProperty("genericPassword"));

		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName7, deliveryPage.getScheduledTest(createdTestId7));
		deliveryPage.startScheduledTest(createdTestId7);
		customeWaitTime(2);
		deliveryPage.waitForElementAndClick(deliveryPage.exitButton);
		try{
			customeWaitTime(1);
			deliveryPage.waitForElementAndClick(deliveryPage.testExitConfirmationButton);
		}catch(Exception e){
			//TODO
		}
		
		customeWaitTime(2);
		deliveryPage.backToDashboard();
		customeWaitTime(2);
		
		customeWaitTime(2);	
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testTeacher1"), unitytestdata.getProperty("genericPassword"));
		reportsPage = dashBoardPage.goToReports();
		reportsPage.filterReportByContentArea("N/A");
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		reportsPage.openTestEventDetail(testName7);
		Assert.assertEquals(testName7, reportsPage.getTestEventTitle());
		Assert.assertEquals("in progress".trim(), reportsPage.getTestEventDetail("utostudent1", 3, "Getting test status").trim());
		Assert.assertEquals("in progress".trim(), reportsPage.getTestEventDetail("utostudent1", 3, "Getting test status").trim());
		reportsPage.waitForElementAndClick(reportsPage.backToReportLink);
		customeWaitTime(1);
	}
	
	@Test(priority = 8)
	public void testVerifyTestDetailReportForScoredTest(){
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		dashBoardPage.addTiles();
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
			System.out.println("******** Taking the scheduled test ********");
			Assert.assertEquals(testName8, deliveryPage.getScheduledTest(createdTestId8));
			deliveryPage.startScheduledTest(createdTestId8);
			customeWaitTime(2);
			deliveryPage.takeTest(true , 4 ,"Choice" , choiceCorrectAnswer);
			deliveryPage.backToDashboard();
			customeWaitTime(2);
			customeWaitTime(2);
			dashBoardPage.logOut();
			customeWaitTime(2);	
			dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testTeacher1"), unitytestdata.getProperty("genericPassword"));
			reportsPage = dashBoardPage.goToReports();
			reportsPage.filterReportByContentArea("N/A");
			reportsPage.filterReportByClassRoster("autoroster");
			customeWaitTime(2);
			reportsPage.openTestEventDetail(testName8);
			Assert.assertEquals(testName8, reportsPage.getTestEventTitle());
			Assert.assertEquals("scored".trim(), reportsPage.getTestEventDetail("utostudent1", 3, "Getting test status").trim());
			Assert.assertEquals("scored".trim(), reportsPage.getTestEventDetail("utostudent1", 3, "Getting test status").trim());
			reportsPage.waitForElementAndClick(reportsPage.backToReportLink);
		}
	
	@Test(priority = 9)
	public void testVerifyStudentsForTestByStatus(){
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testStudent1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		dashBoardPage.addTiles();
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
	    		System.out.println("******** Taking the scheduled test ********");
	    		Assert.assertEquals(testName9, deliveryPage.getScheduledTest(createdTestId9));
	    		deliveryPage.startScheduledTest(createdTestId9);
	    		customeWaitTime(2);
	    		deliveryPage.takeTest(false , 1 ,"Choice" , choiceCorrectAnswer);
	    		Assert.assertEquals(testName9, deliveryPage.getTestinHistoryTable(createdTestId9));
	    		Assert.assertEquals("0%", deliveryPage.getTestPercentCorrect(createdTestId9));
	    		Assert.assertEquals("2", deliveryPage.getTestNoOfItems(createdTestId9));
	    		deliveryPage.backToDashboard();
	    		customeWaitTime(2);
	    		dashBoardPage.logOut();
	    		customeWaitTime(2);	
				dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testTeacher1"), unitytestdata.getProperty("genericPassword"));
	    		customeWaitTime(2);
	    		reportsPage = dashBoardPage.goToReports();
	    		customeWaitTime(2);
	    		//reportsPage.waitForElementAndClick(reportsPage.resetSearchFilter);
	    		reportsPage.filterReportByContentArea("N/A");
	    		reportsPage.filterReportByClassRoster("autoroster");
	    		customeWaitTime(2);
	    		//String testName = "T_Auto_TB_1448424027107";
	    		Assert.assertEquals(testName9, reportsPage.getTestName(testName9));
	    		
	    		Assert.assertTrue(reportsPage.verifyStudentsByStatus("completed"));
	    		Assert.assertTrue(reportsPage.verifyStudentsByStatus("completed"));
	    		Assert.assertTrue(reportsPage.verifyStudentsByStatus("completed"));
	    		
	    		
	    	}
	
	@Test(priority = 10)
	public void testVerifyAllSchoolUserViewReportDetail(){
		driver.get(url);
		loginPage = new Login(driver);
	    dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testSchooladmin"), unitytestdata.getProperty("genericPassword"));
	    customeWaitTime(2);
	    reportsPage = dashBoardPage.goToReports();
	    reportsPage.filterReportByContentArea("N/A");
	    reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		//String testName = "T_Auto_TB_1448424027107";
		Assert.assertEquals(testName10, reportsPage.getTestName(testName10));
		reportsPage.openTestEventDetail(testName10);
		Assert.assertEquals(testName10, reportsPage.waitAndGetElementText(reportsPage.testTitle));

		loginPage = reportsPage.logOut();
		waitTime();
	    dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testAdmin"), unitytestdata.getProperty("genericPassword"));
	    customeWaitTime(2);
		reportsPage = dashBoardPage.goToReports();
		reportsPage.filterReportByContentArea("N/A");
		reportsPage.filterReportByClassRoster("autoroster");
	    customeWaitTime(2);
			//String testName = "T_Auto_TB_1448424027107";
		Assert.assertEquals(testName10, reportsPage.getTestName(testName10));
		reportsPage.openTestEventDetail(testName10);
		Assert.assertEquals(testName10, reportsPage.waitAndGetElementText(reportsPage.testTitle));
		
		loginPage = reportsPage.logOut();
		waitTime();
	    dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testSchooladmin"), unitytestdata.getProperty("genericPassword"));
	    customeWaitTime(2);
		reportsPage = dashBoardPage.goToReports();
		reportsPage.filterReportByContentArea("N/A");
		reportsPage.filterReportByClassRoster("autoroster");
	    customeWaitTime(2);
			//String testName = "T_Auto_TB_1448424027107";
		Assert.assertEquals(testName10, reportsPage.getTestName(testName10));
		reportsPage.openTestEventDetail(testName10);
		Assert.assertEquals(testName10, reportsPage.waitAndGetElementText(reportsPage.testTitle));
		
		
		loginPage = reportsPage.logOut();
		waitTime();
	    dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testSchooladmin"), unitytestdata.getProperty("genericPassword"));
	    customeWaitTime(2);
		reportsPage = dashBoardPage.goToReports();
		reportsPage.filterReportByContentArea("N/A");
		reportsPage.filterReportByClassRoster("autoroster");
	    customeWaitTime(2);
			//String testName = "T_Auto_TB_1448424027107";
		Assert.assertEquals(testName10, reportsPage.getTestName(testName10));
		reportsPage.openTestEventDetail(testName10);
		Assert.assertEquals(testName10, reportsPage.waitAndGetElementText(reportsPage.testTitle));

	}
	    
	@AfterMethod
	public void cleanUp(){
		/*returnToDashboard();	
		customeWaitTime(2);
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(2);
		sechedulePage.waitForElementAndClick(sechedulePage.dayButton);
		customeWaitTime(2);
		sechedulePage.waitForElementAndClick(sechedulePage.nextButton);
		customeWaitTime(2);
		sechedulePage.waitForElementAndDoubleClick(sechedulePage.calendarArea);
		customeWaitTime(2);
		sechedulePage.waitForElementAndClick(sechedulePage.startNowButton);
		customeWaitTime(2);
		sechedulePage.backToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(2);
		itemsBankPage.deleteItemBank(itemBankName);
		customeWaitTime(2);
		itemsBankPage.backToDashboard();
		customeWaitTime(2);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(2);
		itemsPage.deleteItem(itemName);
		customeWaitTime(2);
		itemsPage.backToDashboard();
		customeWaitTime(2);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(2);
		testBankPage.deleteTestBank(testBankName);*/
		//sechedulePage.scheduleTest("Auto School", "autoroster", "N/A", testName, "Green", "110", "20%", "No");
		//returnToDashboard();
	}
}