package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Accommodation;
import pages.AccommodationsReportDetail;
import pages.ClassRoster;
import pages.DashBoard;
import pages.Delivery;
import pages.Items;
import pages.ItemsBank;
import pages.Login;
import pages.Reports;
import pages.Role;
import pages.Schedule;
import pages.SisImport;
import pages.TestCreation;
import pages.TestsBank;
import pages.Users;
import generic.BaseTest;

public class AccommodationsReportDetailTest extends BaseTest{
	
	public AccommodationsReportDetailTest() {
		super();
		
	}
	
	Login loginPage;
	DashBoard dashBoardPage;
	Accommodation accommodationPage;
	ClassRoster classRosterPage;
	TestCreation testCreationPage;
	Schedule schedulePage;
	Delivery deliveryPage;
	ItemsBank itemsBankPage;
	TestsBank testBankPage;
	Items itemsPage;
	Reports reportsPage;
	AccommodationsReportDetail accommodationDetailPage;

	Properties unitytestdata;
	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator;
	String unityTestDataFile = resourcesLocation  + "unitytestdata.properties";

    String schoolName , eautoroster1 , eautoroster2 , eautostudent1 , eautostudent2 , eautostudent3, eautostudent4, eautoteacher1 , eautoteacher2;
    String testBank, testName1 , testName2, itemBank, itemName , genericPassword , testid1 , testid2 , testContentArea, calenderRedColor, testMaxTime, toolYes , testMaxGoal, student1LName, student2LName,student3LName,student4LName;
    long timeStamp;
    
    private static final String Y = "Y";
    
    private static final String N = "N";
    
    String domain = "qa36/";
    
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		schoolName = unitytestdata.getProperty("euroSchool");
		eautoroster1 = unitytestdata.getProperty("eautoroster1");
				
		eautoroster2 = unitytestdata.getProperty("eautoroster2");
		eautostudent1 = unitytestdata.getProperty("eautostudent1");
	    eautostudent2 = unitytestdata.getProperty("eautostudent2");
	    eautostudent3 = unitytestdata.getProperty("eautostudent3");
	    eautostudent4 = unitytestdata.getProperty("eautostudent4");
	    
	    eautoteacher1 = unitytestdata.getProperty("eautoteacher1");
	    eautoteacher2 = unitytestdata.getProperty("eautoteacher2");
		genericPassword = unitytestdata.getProperty("genericPassword");
		
		testContentArea = unitytestdata.getProperty("testContentArea");
		calenderRedColor = unitytestdata.getProperty("calenderRedColor");
		testMaxTime  = unitytestdata.getProperty("testMaxTime");
		toolYes = unitytestdata.getProperty("toolYes");
		testMaxGoal = unitytestdata.getProperty("testMaxGoal");
		
		timeStamp = System.currentTimeMillis();
		itemBank = "My Items";
		testBank = "My Tests";
		
		itemName = "I1_" + timeStamp;
		testName1 = "T1_" + timeStamp;
		testName2 = "T2_" + timeStamp;
		
		student1LName = eautostudent1.substring(1, eautostudent1.length());
		student2LName = eautostudent2.substring(1, eautostudent2.length());
		student3LName = eautostudent3.substring(1, eautostudent3.length());
		student4LName = eautostudent4.substring(1, eautostudent4.length());
	}
	
	@Test
	public void testAccommdationReportEnabledByTile() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(domain + eautoteacher1 , genericPassword);
		dashBoardPage.addTiles();
		waitTime();
		itemsPage = dashBoardPage.goToItems();
		waitTime();
		itemsPage.createItem(itemName, itemBank);
		dashBoardPage = itemsPage.backToDashboard();
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.createTest(testName1, testBank, itemName);
		//testid1 = testCreationPage.getTestId();
		dashBoardPage = testCreationPage.backToDashboard();
		customeWaitTime(5);
		schedulePage = dashBoardPage.goToSchedule();
		waitTime();
	    System.out.println("******** Event creation ********");
	    schedulePage.scheduleTest(schoolName,
	    		eautoroster1, testContentArea, testName1, calenderRedColor, testMaxTime,
				testMaxGoal, toolYes, "true");
	    loginPage = schedulePage.logOut();
	    dashBoardPage = loginPage.loginSuccess(domain + eautostudent1 , genericPassword);
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
		deliveryPage = dashBoardPage.goToDelivery();
		//deliveryPage.startScheduledTest(testid1);
		deliveryPage.takeAndVefiryTestResults(testMaxGoal,
				    "1");
		loginPage =deliveryPage.logOut(); 
		dashBoardPage = loginPage.loginSuccess(domain + eautoteacher1 , genericPassword);
		
		reportsPage =dashBoardPage.goToReports();
		
		reportsPage.filterReportByContentArea(testContentArea);
		reportsPage.filterReportByClassRoster(eautoroster1);
		customeWaitTime(2);
		reportsPage.openTestEventDetail(testName1);
		customeWaitTime(2);
		accommodationDetailPage = reportsPage.gotoAccommodationsReportDetail();
		customeWaitTime(2);
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 1).contains(Y), "Verify-  Alternate Color Text/ Background - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 2).contains(Y), "Verify-  Answer Eliminator - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 3).contains(Y), "Verify-  Answer Masking - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 4).contains(Y), "Verify-  Extended Time - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 5).contains(Y), "Verify-  Line Reader - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 6).contains(Y), "Verify-  Magnification - Status - Enabled");

		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 1).contains(N), "Verify-  Alternate Color Text/ Background - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 2).contains(N), "Verify-  Answer Eliminator - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 3).contains(N), "Verify-  Answer Masking - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 4).contains(N), "Verify-  Extended Time - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 5).contains(N), "Verify-  Line Reader - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 6).contains(N), "Verify-  Magnification - Status - Disabled");
	}	
	
	@Test
	public void testVeriyAccommdationReportEnabledByTestEvent() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(domain + eautoteacher2 , genericPassword);
		dashBoardPage.addTiles();
		waitTime();
		itemsPage = dashBoardPage.goToItems();
		waitTime();
		itemsPage.createItem(itemName, itemBank);
		dashBoardPage = itemsPage.backToDashboard();
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.createTest(testName2, testBank, itemName);
		testCreationPage.searchTest(testName2);
		testid2 = testCreationPage.getTestId();
		testCreationPage.waitForElementAndClick(testCreationPage.editIconList); 
		customeWaitTime(5);
		testCreationPage.enableTestTools("Alternate Color Text/Background,Answer Eliminator,Answer Masking,Line Reader,Magnification,");
		dashBoardPage = testCreationPage.backToDashboard();
		customeWaitTime(5);
		schedulePage = dashBoardPage.goToSchedule();
		waitTime();
	    System.out.println("******** Event creation ********");
	    schedulePage.scheduleTest(schoolName,
	    		eautoroster2, testContentArea, testName2, calenderRedColor, testMaxTime,
				testMaxGoal, toolYes, "true");
	    loginPage = schedulePage.logOut();
	    dashBoardPage = loginPage.loginSuccess(domain + eautostudent3 , genericPassword);
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
		deliveryPage = dashBoardPage.goToDelivery();
		//deliveryPage.startScheduledTest(testid1);
		deliveryPage.takeAndVefiryTestResults(testMaxGoal,
				    "1");
		loginPage =deliveryPage.logOut(); 
		dashBoardPage = loginPage.loginSuccess(domain + eautoteacher2 , genericPassword);
		reportsPage =dashBoardPage.goToReports();
		customeWaitTime(2);
		reportsPage.filterReportByContentArea(testContentArea);
		reportsPage.filterReportByClassRoster(eautoroster2);
		customeWaitTime(2);
		reportsPage.openTestEventDetail(testName2);
		customeWaitTime(2);
		accommodationDetailPage = reportsPage.gotoAccommodationsReportDetail();
		customeWaitTime(2);
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student3LName, 1).contains(Y), "Verify-  Alternate Color Text/ Background - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student3LName, 2).contains(Y), "Verify-  Answer Eliminator - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student3LName, 3).contains(Y), "Verify-  Answer Masking - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student3LName, 5).contains(Y), "Verify-  Line Reader - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student3LName, 6).contains(Y), "Verify-  Magnification - Status - Enabled");

		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student4LName, 1).contains(N), "Verify-  Alternate Color Text/ Background - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student4LName, 2).contains(N), "Verify-  Answer Eliminator - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student4LName, 3).contains(N), "Verify-  Answer Masking - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student4LName, 5).contains(N), "Verify-  Line Reader - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student4LName, 6).contains(N), "Verify-  Magnification - Status - Disabled");
		
	}
	
	@Test(dependsOnMethods = { "testAccommdationReportEnabledByTile" })
	public void testAccommodationReportFilter(){
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(domain + eautoteacher1 , genericPassword);
		dashBoardPage.addTiles();
        reportsPage =dashBoardPage.goToReports();
		reportsPage.filterReportByContentArea(testContentArea);
		reportsPage.filterReportByClassRoster(eautoroster1);
		customeWaitTime(2);
		reportsPage.openTestEventDetail(testName1);
		customeWaitTime(2);
		accommodationDetailPage = reportsPage.gotoAccommodationsReportDetail();
		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.getLastNameFilter());
		accommodationDetailPage.waitForElementAndSendKeys(accommodationDetailPage.getLastNameSearchInputField(), student1LName);
		customeWaitTime(2);
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 1).contains(Y), "Verify-  Alternate Color Text/ Background - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 2).contains(Y), "Verify-  Answer Eliminator - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 3).contains(Y), "Verify-  Answer Masking - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 4).contains(Y), "Verify-  Extended Time - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 5).contains(Y), "Verify-  Line Reader - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 6).contains(Y), "Verify-  Magnification - Status - Enabled");
		
		accommodationDetailPage.waitForElementAndSendKeys(accommodationDetailPage.getLastNameSearchInputField(), student2LName);
		customeWaitTime(2);
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 1).contains(N), "Verify-  Alternate Color Text/ Background - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 2).contains(N), "Verify-  Answer Eliminator - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 3).contains(N), "Verify-  Answer Masking - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 4).contains(N), "Verify-  Extended Time - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 5).contains(N), "Verify-  Line Reader - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 6).contains(N), "Verify-  Magnification - Status - Disabled");
		
		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.resetSearchFilter);
		customeWaitTime(2);
		
		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.getAccommodationStatusFilter());
		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.getAccommodationAssignedCheckBox());
		customeWaitTime(2);
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 1).contains(Y), "Verify-  Alternate Color Text/ Background - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 2).contains(Y), "Verify-  Answer Eliminator - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 3).contains(Y), "Verify-  Answer Masking - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 4).contains(Y), "Verify-  Extended Time - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 5).contains(Y), "Verify-  Line Reader - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 6).contains(Y), "Verify-  Magnification - Status - Enabled");

		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.getAccommodationStatusFilter());
		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.getAccommodationNotAssignedCheckBox());
		customeWaitTime(2);
		customeWaitTime(2);
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 1).contains(N), "Verify-  Alternate Color Text/ Background - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 2).contains(N), "Verify-  Answer Eliminator - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 3).contains(N), "Verify-  Answer Masking - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 4).contains(N), "Verify-  Extended Time - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 5).contains(N), "Verify-  Line Reader - Status - Disabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student2LName, 6).contains(N), "Verify-  Magnification - Status - Disabled");
		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.resetSearchFilter);
		customeWaitTime(5);

		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.getAccommodationTypeFilter());
		
		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.getAlternateColorTextBackgroundCheckBox());
		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.getAnswerEliminatorCheckBox());
		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.getAnswerMaskingCheckBox());
		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.getExtendedTimeCheckBox());
		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.getLineReaderCheckBox());
		accommodationDetailPage.waitForElementAndClick(accommodationDetailPage.getMagnificationCheckBox());
		
		customeWaitTime(2);
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 1).contains(Y), "Verify-  Alternate Color Text/ Background - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 2).contains(Y), "Verify-  Answer Eliminator - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 3).contains(Y), "Verify-  Answer Masking - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 4).contains(Y), "Verify-  Extended Time - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 5).contains(Y), "Verify-  Line Reader - Status - Enabled");
		Assert.assertTrue(accommodationDetailPage.getAccommodationStatus(student1LName, 6).contains(Y), "Verify-  Magnification - Status - Enabled");
		
	}
}
