package tests;

import generic.BaseTest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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
import pages.Users;

/**
 * Make it configurable whether a test score is reported to student steps
 * 
 * Login into the unity as default admin
 * Create organization 
 * Create one teacher and one student
 * Login as created user 
 * Create class roster
 * Add created student student to class roster
 * Create Item bank 
 * Create multiple items =5 in the item bank
 * Create test bank 
 * Create test with above created items 
 * Schedule test for created roster
 * Login as created student #1
 * Start and complete test with all correct answer
 * Verify  test result in history 
 * Login as created student #2 
 * Start and complete test with 50 % correct answer
 * Verify  test result in history 
 * Login as teacher
 * verify test report
 * Login as admin
 * delete organization and Users
 */


public class ConfigurableDeliveryTest extends BaseTest {
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
	Domain domainPage;
	Role rolePage;
	Standards standardPage;
	
	long timestmap = System.currentTimeMillis();

	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator;
	String unityTestDataFile = resourcesLocation  + "unitytestdata.properties";
	
	Properties unitytestdata;
	
	String domain = "";
	String genericPassword;

	String school;
	String roster;
	String testName;
	String handScoredTestName;
	String nonReportableScoreTestName;
	String itemBank;
	String testBank;
	String itemName ;
	
	String bankDesc;
	String contentArea;
	String calenderRedColor;
	String testMaxTime;
	String yes;
	String choiceLast;
	String testMaxGoal;
	String testResultNonReportable;
	String testIsReportable;
	String testIsNotReportable;
	String[] createdUsers;
	
  	Map<Integer , List<String>> studentReport = new HashMap<Integer, List<String>>();    

	public ConfigurableDeliveryTest() {
		super();

	}

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		genericPassword = unitytestdata.getProperty("genericPassword");
		roster = unitytestdata.getProperty("classRoster") + timestmap;
		school = unitytestdata.getProperty("school") + timestmap;
		testName = unitytestdata.getProperty("testName");
		handScoredTestName = unitytestdata.getProperty("nonReportableHandScoredTestName");
		nonReportableScoreTestName = unitytestdata.getProperty("nonReportableScoreTestName");
		itemBank = unitytestdata.getProperty("itemBankName");
		testBank = unitytestdata.getProperty("testBankName");
		itemName = unitytestdata.getProperty("itemName");

		bankDesc = unitytestdata.getProperty("bankdesc");
		contentArea = unitytestdata.getProperty("testContentArea");
		calenderRedColor = unitytestdata.getProperty("calenderRedColor");
		testMaxTime = unitytestdata.getProperty("testMaxTime");
		yes = unitytestdata.getProperty("toolYes");
		choiceLast = unitytestdata.getProperty("choiceFour");
		testMaxGoal = unitytestdata.getProperty("testMaxGoal");
		testResultNonReportable = unitytestdata.getProperty("resultNonReportableScore");
		testIsReportable = unitytestdata.getProperty("testIsReportable");
		testIsNotReportable = unitytestdata.getProperty("testIsReportable");	
	}
	
	@BeforeClass
	public void setUp() {
		System.out.println("+++hub++++ "+hubAddress);
		System.out.println("Unity login url -- >  "+url);
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(domain + unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		customeWaitTime(3);
	}
	
	@Test (priority = 1)
	public void testDeliveryScoreIsReportable() {
		System.out.println("******** Creating a new organization ********");
		organizationPage = dashBoardPage.goToOrganization();
		customeWaitTime(5);
		organizationPage.createNewOrganization(school);
		returnToDashboard();
		System.out.println("************************************************");
		System.out.println("***** Student and teacher creation started *****");
		usersPage = dashBoardPage.goToUsers();
		waitTime();
		getPageLoadStatus();
		createdUsers = usersPage.createUser(school).split(",");

		ArrayList<String> createdUsersA = new ArrayList<String>();
		createdUsersA.add(createdUsers[0]);
		createdUsersA.add(createdUsers[1]);
		createdUsersA.add(createdUsers[2]);

		loginPage = usersPage.logOut();
		System.out.println("************************************************");
		waitTime();
		System.out.println("******** logging as the created teacher ********");
		dashBoardPage = loginPage.loginSuccess(domain + createdUsers[0], genericPassword);
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
		classRosterPage = dashBoardPage.goToClassRoster();
		waitTime();
		System.out.println("******** Class Roster creation ********");
		classRosterPage.createRoster(createdUsersA, school,roster);
		waitTime();
		classRosterPage.returnClassRosterHome();
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		System.out.println("******** Item bank creation ********");
		itemsBankPage.createBank(itemBank, bankDesc);
		waitTime();
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		waitTime();
		System.out.println("******** Items creation ********");
		itemsPage.createItem(itemName, itemBank);
		for (int x = 2; x < 6; x++) {
			Assert.assertTrue(itemsPage.copyItem(itemBank, itemName + x, 1), "Item Copied Successfully");
		}
		returnToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		waitTime();
		System.out.println("******** test bank creation ********");
		testBankPage.createBank(testBank, bankDesc);
		returnToDashboard();
		customeWaitTime(5);
		waitTime();
		testCreationPage = dashBoardPage.goToTestCreation();
		waitTime();
		System.out.println("******** Test creation ********");
		testCreationPage.createTestWithMultipleItems(testName,testBank, itemBank, 5);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
	    System.out.println("******** Event creation ********");
	    sechedulePage.scheduleTest(school, roster, contentArea, testName, calenderRedColor, testMaxTime,testMaxGoal, yes, testIsReportable);
		waitTime();
		loginPage = sechedulePage.logOut();
		customeWaitTime(5);
		System.out.println("******** logging as the first created student ********");
		dashBoardPage = loginPage.loginSuccess(domain + createdUsers[1], genericPassword);
		System.out.println(dashBoardPage.addTiles());
		waitTime();
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		deliveryPage.takeAndVefiryTestResults(testMaxGoal,"4,4,4,4,4");
		customeWaitTime(5);
		loginPage = deliveryPage.logOut();
	}
	
	@Test(priority = 2)
	public void testDeliveryScoreIsNonReportable() {
		System.out.println("******** logging as teacher ********");
		dashBoardPage = loginPage.loginSuccess(domain + createdUsers[0], genericPassword);
		waitTime();
		testCreationPage = dashBoardPage.goToTestCreation();
		waitTime();
		System.out.println("******** Test creation ********");
		testCreationPage.createTestWithMultipleItems(nonReportableScoreTestName,testBank, itemBank, 5);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
	    System.out.println("******** Event creation ********");
	    sechedulePage.scheduleTest(school, roster, contentArea, testName, calenderRedColor, testMaxTime,testMaxGoal, yes, testIsNotReportable);
		waitTime();
		loginPage = sechedulePage.logOut();
		customeWaitTime(5);
		//itemsPage.createHandScoringItem("item6", itemBank);
		System.out.println("******** logging as student ********");
		dashBoardPage = loginPage.loginSuccess(domain + createdUsers[1], genericPassword);
		System.out.println(dashBoardPage.addTiles());
		waitTime();
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Take test and verify result when score is not reportable ********");
		deliveryPage.takeAndVefiryTestResults(testResultNonReportable,"4,4,4,4,4");
		customeWaitTime(5);
		loginPage = deliveryPage.logOut();
	}
}