package tests;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Accommodation;
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
import pages.SisImport;
import pages.Standards;
import pages.TestCreation;
import pages.TestsBank;
import pages.Users;
import generic.BaseTest;

public class AccommodationTest extends BaseTest{
	
	Login loginPage;
	DashBoard dashBoardPage;
	Accommodation accommodationPage;
	SisImport sisImportPage;
	Users usersPage;
	Role rolePage;
	ClassRoster classRosterPage;
	TestCreation testCreationPage;
	Schedule sechedulePage;
	Delivery deliveryPage;
	ItemsBank itemsBankPage;
	TestsBank testBankPage;
	Items itemsPage;

	public AccommodationTest() {
		super();
		
	}

	Properties unitytestdata;
	Properties unityuserdata;
	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator;
	
	String accommodation = "accommodation" + File.separator;
	String unityTestDataFile = resourcesLocation  + "unitytestdata.properties";
	String unityUserDataFile = resourcesLocation  + "unityUsersData.properties";
	String orgnizationFile= "organization.zip";
	String importUserFileLocation;
	String manualStudentF1;
	String manualStudentF2;
	String manualStudentL1;
	String manualStudentL2;
	String studentName1;
	String studentName2;
	String schoolName;
	String rosterName;
	String student = "Student";
	String genericPassword;
	String importStdudentL1;
	String testName;
	String itemBank;
	String testBank;
	String itemName;
	String copiedtest;
	String testid1 , testid2;
	long timeStamp= System.currentTimeMillis();
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		unityuserdata = getUnityMessagesProperty(unityUserDataFile);
		importUserFileLocation =  resourcesLocation + accommodation + orgnizationFile;
		
		manualStudentF1 = unityuserdata.getProperty("ackfirstname");
		manualStudentL1 = unityuserdata.getProperty("acklastname") + System.currentTimeMillis();
		
		manualStudentF2 = unityuserdata.getProperty("ackfirstname1");
		manualStudentL2 = unityuserdata.getProperty("acklastname1") + System.currentTimeMillis();
		
		schoolName = unityuserdata.getProperty("ackSchooName");
		
		importStdudentL1 = unityuserdata.getProperty("importStdudentL1");
		genericPassword = unitytestdata.getProperty("genericPassword");
		
		testName = unitytestdata.getProperty("accommodationTestName") + timeStamp;
		itemBank = unitytestdata.getProperty("accommodationItemBankName") + timeStamp;
		testBank = unitytestdata.getProperty("accommodationTestBankName") + timeStamp;
		itemName = unitytestdata.getProperty("accommodationItemName") + timeStamp;
	}
	
	@BeforeClass
	public void setUp() {
		try{
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		dashBoardPage.addTiles();
		waitTime();
		
		/*sisImportPage = dashBoardPage.goToSisImport();
		waitTime();
		sisImportPage.sisImport(importUserFileLocation);
		sisImportPage.refreshPage();
		sisImportPage.backToDashboard();*/
		
		usersPage = dashBoardPage.goToUsers();
		usersPage.createSpecificUser(manualStudentF1, manualStudentL1, genericPassword, genericPassword, student, schoolName);
		customeWaitTime(5);
		studentName1 = manualStudentF1.substring(0, 1) + manualStudentL1;
		studentName2 = manualStudentF2.substring(0, 1)  + manualStudentL2;
		usersPage.editStudentAccommodation(studentName1, true);
		usersPage.createSpecificUser(manualStudentF2, manualStudentL2, genericPassword, genericPassword, student, schoolName);
		ArrayList<String> createdStudent = new ArrayList<String>();
		createdStudent.add(studentName1);
		createdStudent.add(studentName2);
		customeWaitTime(5);
		dashBoardPage = usersPage.backToDashboard();
		customeWaitTime(5);
		classRosterPage = dashBoardPage.goToClassRoster();
		waitTime();
		System.out.println("******** Class Roster creation ********");
		rosterName = "CR" + timeStamp;
		classRosterPage.createRoster(createdStudent, schoolName,
				rosterName);
		classRosterPage.returnClassRosterHome();
		returnToDashboard();
	   accommodationPage = dashBoardPage.goToAccomadation();
	   accommodationPage.searchStudent(manualStudentL1);
	   accommodationPage.waitForAnElementAndClick(accommodationPage.editIconList);
	   accommodationPage.checkAlternateColorTextBackground(true);
	   accommodationPage.checkAnswerEliminator(true);
	   accommodationPage.checkAnswerMasking(true);
	   accommodationPage.checkLineReader(true);
	   accommodationPage.saveAccommodationAssignment();
	   customeWaitTime(5);

		}catch(Exception e){
		    System.out.println("Error ## -  While setting the Accommodaton test data");

		}
	}
	
	@Test(enabled = false)
	public void testverifyAccommodationForImportedUser(){
		softAssert.assertTrue(accommodationPage.searchStudent(importStdudentL1));
		accommodationPage.waitForAnElementAndClick(accommodationPage.editIconList);
		softAssert.assertTrue(accommodationPage.waitAndGetElementText(accommodationPage.studentNameInfo).contains(importStdudentL1));
		accommodationPage.waitForAnElementAndClick(accommodationPage.accommodationCloseButton);

	}
	 
	@Test(priority = 2)
	public void testverifyAccommodationForManualUser(){
		customeWaitTime(5);
		softAssert.assertTrue(accommodationPage.searchStudent(manualStudentL1));
		accommodationPage.waitForAnElementAndClick(accommodationPage.editIconList);
		softAssert.assertTrue(accommodationPage.waitAndGetElementText(accommodationPage.studentNameInfo).contains(manualStudentL1));
		softAssert.assertTrue(accommodationPage.waitAndGetElementText(accommodationPage.studentNameInfo).contains(manualStudentF1));
		accommodationPage.waitForAnElementAndClick(accommodationPage.accommodationCloseButton);

	}
	
	@Test(priority = 3)
	public void testverifyAccommodationTilePermission(){
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		customeWaitTime(5);
		rolePage = dashBoardPage.goToRole();
		customeWaitTime(5);
		//Assert.assertFalse(rolePage.getTilePermission("Accommodations", "Teacher").get("Create"));
		Assert.assertFalse(rolePage.getTilePermissionStatus("Accommodations", "Teacher","edit"));

	}
	

    @Test(priority = 4)
	public void testVerifyAccomationToolsOnTestDelivery(){
    	driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("ackSchooladmin"),
				unitytestdata.getProperty("genericPassword"));
		dashBoardPage.addTiles();
		//
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		System.out.println("******** Item bank creation ********");
		itemsBankPage.createBank(itemBank, "QA");
		waitTime();
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		waitTime();
		System.out.println("******** Items creation ********");
		itemsPage.createItem(itemName, itemBank);
		returnToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		waitTime();
		System.out.println("******** test bank creation ********");
		testBankPage.createBank(testBank, "QA");
		returnToDashboard();
		customeWaitTime(5);
		waitTime();
		testCreationPage = dashBoardPage.goToTestCreation();
		waitTime();
		System.out.println("******** Test creation ********");
		testCreationPage.createTest(testName, testBank, itemName);
		customeWaitTime(5);
		copiedtest = "copy_" + testName;
		testCreationPage.searchTest(testName);
		testCreationPage.waitForElementAndClick(testCreationPage.copyIconList);
		customeWaitTime(2);
		testCreationPage.copyTest(testBank, copiedtest);
		testCreationPage.waitForElementAndClick(testCreationPage.globalModalInfoOkButton);
		testCreationPage.searchTest(testName);
		customeWaitTime(2);
		testid1 = testCreationPage.getTestId();
		testCreationPage.searchTest(copiedtest);
		testid2 = testCreationPage.getTestId();
		//testCreationPage.goToTestDashBoard();
		returnToDashboard();
		customeWaitTime(5);
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
	    System.out.println("******** Event creation ********");
	    sechedulePage.scheduleTest(schoolName,
				rosterName, "N/A", testName, "Red", "120",
		"100%", "Yes", "true");
	   
	   sechedulePage.scheduleTest(schoolName,
				rosterName, "N/A", copiedtest, "Red", "120",
		"100%", "No", "true");
	   
		waitTime();
		loginPage = sechedulePage.logOut();	
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(studentName1,
				unitytestdata.getProperty("genericPassword"));
		dashBoardPage.addTiles();
		deliveryPage = dashBoardPage.goToDelivery();
		customeWaitTime(5);
		deliveryPage.startScheduledTest(testid1);
		customeWaitTime(2);
		softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.answerMaskingIcon));
		softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.answerEliminatorIcon));
		softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.adjustColorIcon));
		softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.lineReaderIcon));
		deliveryPage.exitAndFinishTest();
		customeWaitTime(5);
		deliveryPage.startScheduledTest(testid2);
		customeWaitTime(2);
		softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.answerMaskingIcon));
		softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.answerEliminatorIcon));
		softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.adjustColorIcon));
		softAssert.assertTrue(deliveryPage.waitForElementVisible(deliveryPage.lineReaderIcon));
		deliveryPage.exitAndFinishTest();
		
	}
    
    @Test(priority = 5)
    public void testVerifyAccommodationToolDisabledInTestDelivery(){
    	driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(studentName2,
				unitytestdata.getProperty("genericPassword"));
		dashBoardPage.addTiles();
		customeWaitTime(5);
		deliveryPage = dashBoardPage.goToDelivery();
		customeWaitTime(5);
		deliveryPage.startScheduledTest(testid1);
		customeWaitTime(2);
		softAssert.assertFalse(deliveryPage.waitForElementVisible(deliveryPage.answerMaskingIcon));
		softAssert.assertFalse(deliveryPage.waitForElementVisible(deliveryPage.answerEliminatorIcon));
		softAssert.assertFalse(deliveryPage.waitForElementVisible(deliveryPage.adjustColorIcon));
		softAssert.assertFalse(deliveryPage.waitForElementVisible(deliveryPage.lineReaderIcon));
		deliveryPage.exitAndFinishTest();
		customeWaitTime(5);
		deliveryPage.startScheduledTest(testid2);
		customeWaitTime(2);
		softAssert.assertFalse(deliveryPage.waitForElementVisible(deliveryPage.answerMaskingIcon));
		softAssert.assertFalse(deliveryPage.waitForElementVisible(deliveryPage.answerEliminatorIcon));
		softAssert.assertFalse(deliveryPage.waitForElementVisible(deliveryPage.adjustColorIcon));
		softAssert.assertFalse(deliveryPage.waitForElementVisible(deliveryPage.lineReaderIcon));
		deliveryPage.exitAndFinishTest();
    }
}
