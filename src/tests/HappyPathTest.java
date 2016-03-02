package tests;

import generic.BaseTest;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

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
 * Happy path test steps
 * 
 * Login into the unity as default admin
 * Create organization 
 * Create one teacher and two student
 * Login as created user 
 * Create class roster
 * Add created student student to class roster
 * Create Item bank 
 * Create multiple items =10 in the item bank
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


public class HappyPathTest extends BaseTest {

	HappyPathTest Nav;
	public String user = "admin";
	public String adminPassword = "@simple1";
	public String domain = "";
	public String genericPassword = "12345";
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	Items itemsPageObject;
	Users usersPageObject;
	ClassRoster classRosterPageObject;
	TestCreation testCreationPageObject;
	Schedule sechedulePageObject;
	Delivery deliveryPageObject;
	HandScoring handScoringPageObject;
	Reports reportsPageObject;
	Organization organizationPageObject;
	ItemsBank itemsBankPageObject;
	TestsBank testBankPageObject;
	Domain domainPageObject;
	Role rolePageObject;
	Standards standardPageObject;
	
	long timestmap = System.currentTimeMillis();

	String school = "Automated School" + timestmap;
	String roster  = "Automated Roster" + timestmap;
	String testName= "Automation test";
	
	//String itemName;
	
	String itemBank = "My item bank";
	
	String testBank = "My test bank";
	
	public HappyPathTest() {
		super();

	}

	@BeforeMethod
	public void setUp() {
		System.out.println("+++hub++++ "+hubAddress);
		driver.get(url);
		loginPageObject = new Login(driver);
		

	}

	@Test
	public void runPath() {

		/*
		 * File file = new File("log.txt"); FileOutputStream fos; try { fos =
		 * new FileOutputStream(file); PrintStream ps = new PrintStream(fos);
		 * System.setOut(ps); } catch (FileNotFoundException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		System.out.println("******** logging as super administrator ********");

		dashBoardPageObject = loginPageObject.loginSuccess(domain + user,
				adminPassword);
		customeWaitTime(3);

		System.out.println("******** Creating a new organization ********");
		organizationPageObject = dashBoardPageObject.goToOrganization();
		customeWaitTime(5);
		organizationPageObject.createNewOrganization(school);
		returnToDashboard();
		System.out.println("************************************************");
		
		System.out.println("***** Student and teacher creation started *****");
		usersPageObject = dashBoardPageObject.goToUsers();
		waitTime();
		getPageLoadStatus();
		String[] createdUsers = usersPageObject.createUser(school).split(",");

		ArrayList<String> createdUsersA = new ArrayList<String>();
		createdUsersA.add(createdUsers[0]);
		createdUsersA.add(createdUsers[1]);
		createdUsersA.add(createdUsers[2]);

		dashBoardPageObject.logOut();
		System.out.println("************************************************");
		waitTime();
		System.out.println("******** logging as the created teacher ********");
		dashBoardPageObject = loginPageObject.loginSuccess(domain
				+ createdUsers[0], genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();

		classRosterPageObject = dashBoardPageObject.goToClassRoster();
		waitTime();
		System.out.println("******** Class Roster creation ********");
		classRosterPageObject.createRoster(createdUsersA, school,
				roster);
		waitTime();
		classRosterPageObject.returnClassRosterHome();
		returnToDashboard();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		System.out.println("******** Item bank creation ********");
		itemsBankPageObject.createBank(itemBank, "QA");
		waitTime();
		returnToDashboard();
		customeWaitTime(5);
		itemsPageObject = dashBoardPageObject.goToItems();
		waitTime();
		System.out.println("******** Items creation ********");
		itemsPageObject.createItem("item 1", itemBank);
		for (int x = 2; x < 11; x++) {
			Assert.assertTrue(
					itemsPageObject.copyItem(itemBank, "item " + x, 1),
					"Item Copied Successfully");
		}
		returnToDashboard();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		System.out.println("******** test bank creation ********");
		testBankPageObject.createBank(testBank, "QA");
		returnToDashboard();
		customeWaitTime(5);
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		waitTime();
		System.out.println("******** Test creation ********");

		testCreationPageObject.createTestWithMultipleItems(testName,
				testBank, itemBank, 10);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		sechedulePageObject = dashBoardPageObject.goToSchedule();
		waitTime();
	    System.out.println("******** Event creation ********");
	   sechedulePageObject.scheduleTest(school,
				roster, "N/A", testName, "Red", "120",
		"100%", "Yes");
		waitTime();
	returnToDashboard();
	loginPageObject = dashBoardPageObject.logOut();
	System.out.println("************************************************");
		/*	 * driver.quit();
 * System.out.println("********** Starting  mobile emulation **********"
* ); WebDriver driver = emulateDevice("Apple iPad 3 / 4");
* driver.get(url);
		 * 
		 *  waitTime(); loginPageObject = new Login(driver);
	 */

		waitTime();
	System.out
			.println("******** logging as the first created student ********");
	dashBoardPageObject = loginPageObject.loginSuccess(domain
			+ createdUsers[1], genericPassword);
	System.out.println(dashBoardPageObject.addTiles());
	waitTime();
    deliveryPageObject = dashBoardPageObject.goToDelivery();
    waitTime();
    System.out.println("******** Taking the scheduled test ********");
    deliveryPageObject.takeAndVefiryTestResults("100%",
    "4,4,4,4,4,4,4,4,4,4");
    returnToDashboard();
    waitTime();
    dashBoardPageObject.logOut();
    System.out.println("************************************************");
    waitTime();
    
    /*
   System.out
		.println("******** logging as the second created student ********");
	dashBoardPageObject = loginPageObject.loginSuccess(domain
			+ createdUsers[2], genericPassword);
     System.out.println(dashBoardPageObject.addTiles());
     waitTime();
     deliveryPageObject = dashBoardPageObject.goToDelivery();
     waitTime();
     System.out.println("******** Taking the scheduled test ********");

	deliveryPageObject.takeAndVefiryTestResults("50%",
				"4,4,4,4,4,2,1,1,2,1");
     returnToDashboard();
     waitTime();
     loginPageObject = dashBoardPageObject.logOut();
     System.out.println("************************************************");
       
	 * driver.quit();
		 * 
 		 * waitTime();
	 * 
		 * driver = chromeDriver(); driver.get(url); loginPageObject = new
		 * Login(driver);
		 */
		System.out.println("******** logging as the created teacher ********");
		dashBoardPageObject = loginPageObject.loginSuccess(domain
				+ createdUsers[0], genericPassword);

		waitTime();
		waitTime();
		/*
		 * handScoringPageObject = dashBoardPageObject.goToHandScoring();
		 * waitTime(); handScoringPageObject.scoreTest(); waitTime();
		 */

		reportsPageObject = dashBoardPageObject.goToReports();
		waitTime();
		//reportsPageObject.viewReport();
		waitTime();
	   loginPageObject = dashBoardPageObject.logOut();

		System.out.println("************************************************");
		waitTime();

		System.out.println("******** logging as super administrator ********");

	   loginPageObject.loginSuccess(domain + user, adminPassword);

		customeWaitTime(8);
		usersPageObject = dashBoardPageObject.goToUsers();
		waitTime();
		System.out.println("******** Deleting the created users ********");
		usersPageObject.deleteCreatedUsers(createdUsers , school);
		waitTime();
		System.out.println("******** Deleting the created School ********");
	  organizationPageObject = dashBoardPageObject.goToOrganization();
	  organizationPageObject.deleteCreatedOrganization(school);

				dashBoardPageObject.logOut();
		System.out.println("************************************************");
	}
}