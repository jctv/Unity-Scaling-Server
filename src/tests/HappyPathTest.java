package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import generic.BaseTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

public class HappyPathTest extends BaseTest {

	HappyPathTest Nav;
	public String user = "admin";
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

	public HappyPathTest() {
		super();

	}

	@BeforeMethod
	public void setUp() {

		driver.get(url);
		
		loginPageObject = new Login(driver);
		System.out.println("******** logging as super administrator ********");
		
		dashBoardPageObject = loginPageObject.loginSuccess(user, "@simple1");
		//driver.get(url + "#dashboard");
		waitTime();

		// System.out.println(dashBoardPageObject.addTiles());

	}

	@Test
	public void runPath() {

		/*
		 * File file = new File("log.txt"); FileOutputStream fos; try { fos =
		 * new FileOutputStream(file); PrintStream ps = new PrintStream(fos);
		 * System.setOut(ps); } catch (FileNotFoundException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		System.out.println("******** Creating a new organization ********");
		organizationPageObject = dashBoardPageObject.goToOrganization();
		waitTime();
		organizationPageObject.createNewOrganization("Automated School");
		System.out.println("************************************************");

		waitTime();

		System.out.println("***** Student and teacher creation started *****");
		usersPageObject = dashBoardPageObject.goToUsers();
		waitTime();
		String[] createdUsers = usersPageObject.createUser().split(",");

		ArrayList<String> createdUsersA = new ArrayList<String>();
		createdUsersA.add(createdUsers[0]);
		createdUsersA.add(createdUsers[1]);
		createdUsersA.add(createdUsers[2]);

		dashBoardPageObject.logOut();
		System.out.println("************************************************");
		waitTime();
		System.out.println("******** logging as the created teacher ********");
		dashBoardPageObject = loginPageObject.loginSuccess(createdUsers[0],
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		
		classRosterPageObject = dashBoardPageObject.goToClassRoster();
		waitTime();
		System.out.println("******** Class Roster creation ********");
		classRosterPageObject.createRoster(createdUsersA, "Automated School", "Automated Roster");
		waitTime();
		
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		System.out.println("******** Item bank creation ********");
		itemsBankPageObject.createBank("My item bank", "QA");

		waitTime();

		itemsPageObject = dashBoardPageObject.goToItems();
		waitTime();
		System.out.println("******** Items creation ********");
		itemsPageObject.createItem("item 2" , "My item bank");

		waitTime();

		/*
		 * itemsPageObject = dashBoardPageObject.goToItems(); waitTime();
		 * itemsPageObject.createItem("item 1"); waitTime();
		 */

		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		System.out.println("******** test bank creation ********");
		testBankPageObject.createBank("My test bank", "QA");
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		waitTime();
		System.out.println("******** Test creation ********");
		testCreationPageObject.createTest("Automation test" , "My test bank" , "item 2");
		waitTime();
		sechedulePageObject = dashBoardPageObject.goToSchedule();
		waitTime();
		System.out.println("******** Event creation ********");
		sechedulePageObject.scheduleTest();
		waitTime();

		loginPageObject = dashBoardPageObject.logOut();
		System.out.println("************************************************");
		/* driver.quit();
		System.out.println("********** Starting  mobile emulation **********");
		WebDriver driver = emulateDevice("Apple iPad 3 / 4");
		driver.get(url); 

		waitTime();
		loginPageObject = new Login(driver); */

		waitTime();
		System.out
				.println("******** logging as the first created student ********");
		dashBoardPageObject = loginPageObject.loginSuccess(createdUsers[1],
				genericPassword);
		System.out.println(dashBoardPageObject.addTiles());
		waitTime();
		deliveryPageObject = dashBoardPageObject.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		deliveryPageObject.takeTest();
		waitTime();
		dashBoardPageObject.logOut();
		System.out.println("************************************************");

		waitTime();
		System.out
				.println("******** logging as the second created student ********");
		dashBoardPageObject = loginPageObject.loginSuccess(createdUsers[2],
				genericPassword);
		System.out.println(dashBoardPageObject.addTiles());
		waitTime();
		deliveryPageObject = dashBoardPageObject.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		deliveryPageObject.takeTest();
		waitTime();
		loginPageObject = dashBoardPageObject.logOut();
		System.out.println("************************************************");
/*
		driver.quit();

		waitTime();

		driver = chromeDriver();
		driver.get(url);
		loginPageObject = new Login(driver);
		*/
		System.out.println("******** logging as the created teacher ********");
		dashBoardPageObject = loginPageObject.loginSuccess(createdUsers[0],
				genericPassword);

		waitTime();
		waitTime();
		/*
		 * handScoringPageObject = dashBoardPageObject.goToHandScoring();
		 * waitTime(); handScoringPageObject.scoreTest(); waitTime();
		 */

		reportsPageObject = dashBoardPageObject.goToReports();
		waitTime();
		reportsPageObject.viewReport();
		waitTime();
		loginPageObject = dashBoardPageObject.logOut();
		
		System.out.println("************************************************");
		waitTime();

		System.out.println("******** logging as super administrator ********");
		
		loginPageObject.loginSuccess(user, "@simple1");

		waitTime();
		waitTime();
		waitTime();
		usersPageObject = dashBoardPageObject.goToUsers();
		waitTime();

		System.out.println("******** Deleting the created users ********");
		usersPageObject.DeleteCreatedUsers(createdUsers);
		waitTime();
		System.out.println("******** Deleting the created School ********");
		organizationPageObject = dashBoardPageObject.goToOrganization();
		organizationPageObject.deleteCreatedOrganization();

		dashBoardPageObject.logOut();
		System.out.println("************************************************");
	}
}