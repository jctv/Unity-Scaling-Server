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
	
	String domain = "autom/";
	String genericPassword;

	String school;
	String roster;
	String testName;
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
	
  	Map<Integer , List<String>> studentReport = new HashMap<Integer, List<String>>();    

	public HappyPathTest() {
		super();

	}

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		genericPassword = unitytestdata.getProperty("genericPassword");
		roster = unitytestdata.getProperty("happyPathRoster") + timestmap;
		school = unitytestdata.getProperty("happyPathSchool") + timestmap;
		testName = unitytestdata.getProperty("happypathTestName");
		itemBank = unitytestdata.getProperty("happypathItemBankName");
		testBank = unitytestdata.getProperty("happypathTestBankName");
		itemName = unitytestdata.getProperty("happyPathItemName");

		bankDesc = unitytestdata.getProperty("bankdesc");
		contentArea = unitytestdata.getProperty("testContentArea");
		calenderRedColor = unitytestdata.getProperty("calenderRedColor");
		testMaxTime = unitytestdata.getProperty("testMaxTime");
		yes = unitytestdata.getProperty("toolYes");
		choiceLast = unitytestdata.getProperty("choiceFour");
		testMaxGoal = unitytestdata.getProperty("testMaxGoal");

		
	}
	@BeforeClass
	public void setUp() {
		System.out.println("+++hub++++ "+hubAddress);
		System.out.println("Unity login url -- >  "+url);
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(domain + unitytestdata.getProperty("defaultAutoAdmin"),
				unitytestdata.getProperty("defaultAutoPassword"));
		customeWaitTime(3);
	}

	@Test
	public void testHappyPath() {
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
 
		//driver.get(url);
		loginPage = usersPage.logOut();
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
		itemsBankPage.createBank(itemBank, bankDesc);
		waitTime();
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		waitTime();
		System.out.println("******** Items creation ********");
		itemsPage.createItem(itemName, itemBank);
		for (int x = 2; x < 11; x++) {
			Assert.assertTrue(
					itemsPage.copyItem(itemBank, itemName + x, 1),
					"Item Copied Successfully");
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

		testCreationPage.createTestWithMultipleItems(testName,
				testBank, itemBank, 10);
		customeWaitTime(5);
		//testCreationPage.goToTestDashBoard();
		returnToDashboard();
		customeWaitTime(5);
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
	    System.out.println("******** Event creation ********");
	   sechedulePage.scheduleTest(school,
				roster, contentArea, testName, calenderRedColor, testMaxTime,
				testMaxGoal, yes, "true");
		waitTime();
	//returnToDashboard();
	loginPage = sechedulePage.logOut();
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
       deliveryPage.takeAndVefiryTestResults(testMaxGoal,
    "4,4,4,4,4,4,4,4,4,4");
   reportsPage = deliveryPage.goToTestDetailReport(testName);
   customeWaitTime(5);
   studentReport =  reportsPage.getStudentReport();
   waitTime();
   for(int i= 1; i<= 10; i++){
   	softAssert.assertEquals(studentReport.get(i).get(2), unitytestdata.getProperty("strandType"));
   	softAssert.assertEquals(studentReport.get(i).get(3), unitytestdata.getProperty("pointRecieved"));
   	softAssert.assertEquals(studentReport.get(i).get(4), unitytestdata.getProperty("pointPossible"));
   	softAssert.assertEquals(studentReport.get(i).get(5), unitytestdata.getProperty("strandard"));
   	softAssert.assertEquals(studentReport.get(i).get(6), unitytestdata.getProperty("learnMore"));
   	softAssert.assertEquals(studentReport.get(i).get(7), unitytestdata.getProperty("exploreMore"));
   }
   
   loginPage = reportsPage.logOut();
   waitTime();
    /*returnToDashboard();
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

	  deliveryPage.takeAndVefiryTestResults("50%",
				"4,4,4,4,4,2,1,1,2,1");
     //returnToDashboard();
    // waitTime();
    // 
*/	  
      //loginPage = dashBoardPage.logOut();
 	  customeWaitTime(5);
       System.out.println("************************************************");
       
		System.out.println("******** logging as the created teacher ********");
		
		dashBoardPage = loginPage.loginSuccess(domain
				+ createdUsers[0], genericPassword);
		customeWaitTime(10);
		driver.navigate().refresh();
		customeWaitTime(20);
		reportsPage = dashBoardPage.goToReports();
		waitTime();
		//reportsPage.viewReport();
		waitTime();
	   //loginPage = dashBoardPage.logOut();
		
		/*driver.get(url);
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
		System.out.println("************************************************");*/
		
	}
}