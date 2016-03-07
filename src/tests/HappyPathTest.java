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
	String choiceCorrectAnswer = "set D correct Answer";
	
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

	/*@BeforeMethod
	public void setUp() {
		System.out.println("+++hub++++ "+hubAddress);
		driver.get(url);
		loginPage = new Login(driver);
	}*/

	@Test
	public void runPath() {
		
		System.out.println("+++hub++++ "+hubAddress);
		driver.get(url);
		loginPage = new Login(driver);
		System.out.println("******** logging as super administrator ********");

		dashBoardPage = loginPage.loginSuccess(domain + user,
				adminPassword);
		customeWaitTime(3);

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
		String[] createdUsers = usersPage.createUser(school).split(",");

		ArrayList<String> createdUsersA = new ArrayList<String>();
		createdUsersA.add(createdUsers[0]);
		createdUsersA.add(createdUsers[1]);
		createdUsersA.add(createdUsers[2]);

		
		driver.get(url);
		loginPage = new Login(driver);

		System.out.println("************************************************");
		waitTime();
		System.out.println("******** logging as the created teacher ********");
		dashBoardPage = loginPage.loginSuccess(domain
				+ createdUsers[0], genericPassword);
		waitTime();
		dashBoardPage.addTiles();
		waitTime();

		classRosterPage = dashBoardPage.goToClassRoster();
		waitTime();
		System.out.println("******** Class Roster creation ********");
		classRosterPage.createRoster(createdUsersA, school,
				roster);
		waitTime();
		classRosterPage.returnClassRosterHome();
		returnToDashboard();
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
		itemsPage.createItem("item 1", itemBank);
		for (int x = 2; x < 11; x++) {
			Assert.assertTrue(
					itemsPage.copyItem(itemBank, "item " + x, 1),
					"Item Copied Successfully");
		}
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

		testCreationPage.createTestWithMultipleItems(testName,
				testBank, itemBank, 10);
		customeWaitTime(5);
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		
		returnToDashboard();
		customeWaitTime(5);
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
	    System.out.println("******** Event creation ********");
	   sechedulePage.scheduleTest(school,
				roster, "N/A", testName, "Red", "120",
		"100%", "Yes");
		waitTime();
	returnToDashboard();
	//loginPage = dashBoardPage.logOut();
	
	driver.get(url);
	loginPage = new Login(driver);
	System.out.println("************************************************");
		/*	 * driver.quit();
		 * System.out.println("********** Starting  mobile emulation **********"
		 * ); WebDriver driver = emulateDevice("Apple iPad 3 / 4");
		 	* driver.get(url);
		 * 
		 *  waitTime(); loginPage = new Login(driver);
	 */

	customeWaitTime(5);
	System.out
			.println("******** logging as the first created student ********");
	dashBoardPage = loginPage.loginSuccess(domain
			+ createdUsers[1], genericPassword);
	System.out.println(dashBoardPage.addTiles());
	waitTime();
    deliveryPage = dashBoardPage.goToDelivery();
    waitTime();
    System.out.println("******** Taking the scheduled test ********");
    
    Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
	deliveryPage.startScheduledTest(createdTestId);
	customeWaitTime(5);
    
	deliveryPage.takeTest(true , 4 ,"Choice" , choiceCorrectAnswer);

   /* deliveryPage.takeAndVefiryTestResults("100%",
    "4,4,4,4,4,4,4,4,4,4");*/
    returnToDashboard();
    waitTime();
	driver.get(url);
	driver.manage().deleteAllCookies();
	driver.navigate().refresh();
    //dashBoardPage.logOut();
	loginPage = new Login(driver);

    customeWaitTime(20);
    
    System.out.println("************************************************");
	customeWaitTime(5);
    
     System.out
		.println("******** logging as the second created student ********");
	 dashBoardPage = loginPage.loginSuccess(domain
			+ createdUsers[2], genericPassword);
      System.out.println(dashBoardPage.addTiles());
      waitTime();
      deliveryPage = dashBoardPage.goToDelivery();
      waitTime();
      System.out.println("******** Taking the scheduled test ********");
      Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
	  deliveryPage.startScheduledTest(createdTestId);
		customeWaitTime(5);
  	  deliveryPage.takeTest(false , 1 ,"Choice" , choiceCorrectAnswer);

	 /* deliveryPage.takeAndVefiryTestResults("50%",
				"4,4,4,4,4,2,1,1,2,1");*/
     //returnToDashboard();
    // waitTime();
    // loginPage = dashBoardPage.logOut();
	  
	  driver.get(url);
	  loginPage = new Login(driver);

 	 customeWaitTime(5);

     System.out.println("************************************************");
       
		System.out.println("******** logging as the created teacher ********");
		
		dashBoardPage = loginPage.loginSuccess(domain
				+ createdUsers[0], genericPassword);
        
		customeWaitTime(10);
		driver.navigate().refresh();
		customeWaitTime(20);

		/*
		 * handScoringPage = dashBoardPage.goToHandScoring();
		 * waitTime(); handScoringPage.scoreTest(); waitTime();
		 */

		reportsPage = dashBoardPage.goToReports();
		waitTime();
		//reportsPage.viewReport();
		waitTime();
	   //loginPage = dashBoardPage.logOut();
		
		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.manage().logs().getAvailableLogTypes();
		driver.navigate().refresh();
		System.out.println("************************************************");
		customeWaitTime(20);
		loginPage = new Login(driver);
		System.out.println("******** logging as super administrator ********");
		dashBoardPage = loginPage.loginSuccess(domain + user, adminPassword);
		customeWaitTime(10);
		dashBoardPage.takeScreenShot();
		driver.navigate().refresh();
		customeWaitTime(20);
		usersPage = dashBoardPage.goToUsers();
		waitTime();
		System.out.println("******** Deleting the created users ********");
		usersPage.deleteCreatedUsers(createdUsers , school);
		waitTime();
		System.out.println("******** Deleting the created School ********");
	   organizationPage = dashBoardPage.goToOrganization();
	   organizationPage.deleteCreatedOrganization(school);

	   dashBoardPage.logOut();
		System.out.println("************************************************");
	}
}