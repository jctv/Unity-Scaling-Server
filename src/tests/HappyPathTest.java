package tests;

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
	public String user = "jarredondo";
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
		dashBoardPageObject = loginPageObject.loginSuccess(user,
				genericPassword);
		waitTime();

		System.out.println(dashBoardPageObject.addTiles());

	}

	@Test
	public void runPath() {


		waitTime();
		/*
		 * /organizationPageObject = dashBoardPageObject.goToOrganization();
		 * waitTime(); /*organizationPageObject
		 * .createNewOrganization("School Created by Automated test"); /*
		 * organizationPageObject.createOrganizationNode("Automated State",
		 * "state");
		 * organizationPageObject.createOrganizationNode("Automated District",
		 * "district");
		 * organizationPageObject.createOrganizationNode("Automated School",
		 * "school");
		 */
		waitTime();

		usersPageObject = dashBoardPageObject.goToUsers();
		waitTime();
		String[] createdUsers = usersPageObject.createUser().split(",");
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(createdUsers[0],
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.createBank("My item bank", "QA");

		waitTime();

		itemsPageObject = dashBoardPageObject.goToItems();
		waitTime();
		itemsPageObject.createItem("item 1");

		waitTime();
		/*
		 * itemsPageObject = dashBoardPageObject.goToItems(); waitTime();
		 * itemsPageObject.createItem("item 2");
		 * 
		 * waitTime();
		 */
		classRosterPageObject = dashBoardPageObject.goToClassRoster();
		waitTime();
		classRosterPageObject.createRoster(createdUsers[1],
				"West Sacramento School");
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.createBank("My test bank", "QA");
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		waitTime();
		testCreationPageObject.createTest("Automation test");
		waitTime();
		sechedulePageObject = dashBoardPageObject.goToSchedule();
		waitTime();
		System.out.println(sechedulePageObject.scheduleTest());
		waitTime();
		dashBoardPageObject.logOut();

		driver.quit();
		// //////////////////////////////////////////////

		WebDriver driver = emulateDevice("Apple iPad 3 / 4");
		driver.get(url);

		
		System.out.println("emulando");
		waitTime();
		loginPageObject = new Login(driver);

		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(createdUsers[1],
				genericPassword);
		System.out.println(dashBoardPageObject.addTiles());
		waitTime();
		deliveryPageObject = dashBoardPageObject.goToDelivery();
		waitTime();
		deliveryPageObject.takeTest();
		waitTime();
		dashBoardPageObject.logOut();

		driver.quit();
		// //////////////////////////////////////////
		waitTime();
		
		// *************************************************
		driver = chromeDriver();
		driver.get(url);
		loginPageObject = new Login(driver);

		/*dashBoardPageObject = loginPageObject.loginSuccess(createdUsers[0],
				genericPassword);*/
		// **************************************************

		waitTime();
/*		handScoringPageObject = dashBoardPageObject.goToHandScoring();
		waitTime();
		handScoringPageObject.scoreTest();
		waitTime();
		reportsPageObject = dashBoardPageObject.goToReports();
		waitTime();
		reportsPageObject.viewReport();
		waitTime();
		dashBoardPageObject.logOut();
		waitTime();*/
		dashBoardPageObject = loginPageObject.loginSuccess(user, genericPassword);
		waitTime();
		dashBoardPageObject.goToUsers();
		waitTime();
		usersPageObject.DeleteCreatedUsers(createdUsers);
		dashBoardPageObject.logOut();

	}
}