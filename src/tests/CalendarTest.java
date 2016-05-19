package tests;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import generic.BaseTest;
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

public class CalendarTest extends BaseTest {

	Login loginPage;
	DashBoard dashBoardPage;
	Schedule sechedulePage;
	Users usersPage;
	ClassRoster classRosterPage;
	TestCreation testCreationPage;
	Delivery deliveryPage;
	HandScoring handScoringPage;
	Reports reportsPage;
	Organization organizationPage;
	ItemsBank itemsBankPage;
	TestsBank testBankPage;
	Items itemsPage;

	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	String unityTestDataFile = "src" + File.separator + "resources"
			+ File.separator + "unitytestdata.properties";

	Properties unitymessages;
	Properties unitytestdata;
	
	String studentFN = "student";
	//String studentFN2;
	//String studentFN3;
	String studentLN1 ="L1" + System.currentTimeMillis();
	String studentLN2 = "L2" + System.currentTimeMillis();
	String studentLN3 = "L3" +  System.currentTimeMillis();
	String roster = "Roster" + System.currentTimeMillis();
	String Student1 , Student2 , Student3;
	final String role = "Student";
	String studentUUID ;
	String euroschool;
	String genericPassword;
	
	long timestamp = System.currentTimeMillis();
	String itemBank = "IB" + timestamp;
	String item = "I" + timestamp;
	String testBank = "TB" + timestamp;
	String test = "T" + timestamp;
	String desc = "description";
	
	String contentArea;
	String calenderRedColor;
	String testMaxTime;
	String yes;
	String choiceLast;
	String testMaxGoal;

	public CalendarTest() {
		super();

	}

	@BeforeTest
	public void loadUnityMessagesProperty() {
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		euroschool = unitytestdata.getProperty("euroSchool") + System.currentTimeMillis();
		genericPassword = unitytestdata.getProperty("genericPassword");
		contentArea = unitytestdata.getProperty("testContentArea");
		calenderRedColor = unitytestdata.getProperty("calenderRedColor");
		testMaxTime = unitytestdata.getProperty("testMaxTime");
		yes = unitytestdata.getProperty("toolYes");
		choiceLast = unitytestdata.getProperty("choiceFour");
		testMaxGoal = unitytestdata.getProperty("testMaxGoal");
	}

	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(
				unitytestdata.getProperty("testDomainAdmin"),
				unitytestdata.getProperty("defaultAutoPassword"));
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
	}

	/**
	 * Login into the unity as systemAdmin, Create three student ,Create roster
	 * and add two student Create item bank Create few items Create test bank
	 * create test Schedule test Add new student to roster for scheduled Open
	 * scheduled test event and verify newly added student available Add time
	 * and goal for new student Login as new student Go to the delivery test
	 * should be available
	 * 
	 */

	@Test(priority = 0)
	public void testAddNewStudentToScheduledTest() {
		organizationPage = dashBoardPage.goToOrganization();
		customeWaitTime(5);
		organizationPage.createNewOrganization(euroschool);
		returnToDashboard();
		System.out.println("************************************************");
		System.out.println("***** Student and teacher creation started *****");
		usersPage = dashBoardPage.goToUsers();
		String student1CreationMessage =usersPage.createSpecificUser(studentFN, studentLN1, genericPassword, genericPassword, role,  euroschool);
		Student1 = student1CreationMessage.split("- ")[1].replace(".", "");
		
		String student2CreationMessage = usersPage.createSpecificUser(studentFN, studentLN2, genericPassword, genericPassword, role,  euroschool);
		Student2 = student2CreationMessage.split("- ")[1].replace(".", "");
		String student3CreationMessage = usersPage.createSpecificUser(studentFN, studentLN3, genericPassword, genericPassword, role,  euroschool);
		Student3 = student3CreationMessage.split("- ")[1].replace(".", "");
		
		studentUUID = usersPage.getUserUuid(Student3);
		ArrayList<String> createdStudents = new ArrayList<String>();
		createdStudents.add(Student1);
		createdStudents.add(Student2);
		usersPage.backToDashboard();
		classRosterPage = dashBoardPage.goToClassRoster();
		classRosterPage.createRoster(createdStudents, euroschool, roster);
		waitTime();
		classRosterPage.returnClassRosterHome();
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		System.out.println("******** Item bank creation ********");
		itemsBankPage.createBank(itemBank, desc);
		waitTime();
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		waitTime();
		System.out.println("******** Items creation ********");
		itemsPage.createItem(item, itemBank);
		returnToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		testBankPage.createBank(testBank, desc);
		returnToDashboard();
		customeWaitTime(5);
		waitTime();
		testCreationPage = dashBoardPage.goToTestCreation();
		waitTime();
		System.out.println("******** Test creation ********");
		testCreationPage.createTest(test, testBank, item);
		testCreationPage.searchTest(test);
		String createdTestId = testCreationPage.getTestId();
		returnToDashboard();
		customeWaitTime(5);
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
	    System.out.println("******** Event creation ********");
	    sechedulePage.saveTestEvent(euroschool, roster, contentArea, test, "Report Immediately", calenderRedColor, testMaxTime, testMaxGoal, yes);
	    sechedulePage.backToDashboard();
		classRosterPage = dashBoardPage.goToClassRoster();
		classRosterPage.editRosterAddStudent(roster, Student3);
		classRosterPage.backToDashboard();
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
	    System.out.println("******** Event creation ********");
	    sechedulePage.openTestEvent();
	    Assert.assertFalse(sechedulePage.getStudentCheckBoxStatus(studentUUID));
	    sechedulePage.addStudentToScheduledTest(studentUUID, testMaxGoal, yes);
	    sechedulePage.startTestEvent();
	    loginPage = sechedulePage.logOut();
		customeWaitTime(5);
		System.out
				.println("******** logging as the first created student ********");
		dashBoardPage = loginPage.loginSuccess(Student3, genericPassword);
		System.out.println(dashBoardPage.addTiles());
		waitTime();
	    deliveryPage = dashBoardPage.goToDelivery();
	    waitTime();
		Assert.assertEquals(test, deliveryPage.getScheduledTest(createdTestId));
	}

	/**
	 * Login into the Unity Go to the Calendar Tile GO to the previous week
	 * click on calendar Validate messages
	 * 
	 */

	@Test(priority = 1)
	public void testCalendarAlertMessgaes() {
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
		waitTime();
		sechedulePage.waitForElementAndClick(sechedulePage.previousButton);
		waitTime();
		waitTime();
		sechedulePage.waitForElementAndDoubleClick(sechedulePage.calendar);
		waitTime();
		waitTime();
		Assert.assertEquals(sechedulePage.genericModalTitle.getText().trim(),
				unitymessages.getProperty("calendarPastEvent").trim());
		Assert.assertEquals(sechedulePage.genericModalMessage.getText().trim(),
				unitymessages.getProperty("calendarCannotSchedule").trim());
		sechedulePage.waitForElementAndClick(sechedulePage.cancelPastEvent);

	}

}
