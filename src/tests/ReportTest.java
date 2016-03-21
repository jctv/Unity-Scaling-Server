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
	String itemName ;
	String testBankName ;
	String testName = "";
	
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
		System.out.println("******** logging as super administrator ********");
		dashBoardPage = loginPage.loginSuccess(userName, password);
		customeWaitTime(2);

		System.out.println("***** Student and teacher creation started *****");
		usersPage = dashBoardPage.goToUsers();
		waitTime();
		String[] createdUsers = usersPage.createUser(testschool).split(",");
		
		teacherUserName = createdUsers[0];
		autoStudent1 = createdUsers[1];
		autoStudent2 = createdUsers[2];
		
		ArrayList<String> createdUsersA = new ArrayList<String>();
		createdUsersA.add(teacherUserName);
		createdUsersA.add(autoStudent1);
		createdUsersA.add(autoStudent2);
		
		loginPage = dashBoardPage.logOut();
		System.out.println("******** logging as the teacher ********");
		dashBoardPage = loginPage.loginSuccess(domain + teacherUserName,
				genericPassword);
		waitTime();
		
		classRosterPage = dashBoardPage.goToClassRoster();
		waitTime();
		System.out.println("******** Class Roster creation ********");
		classRosterPage.createRoster(createdUsersA, "Automated School",
				"Automated Roster");
		waitTime();
		returnToDashboard();
		
		System.out.println("***** Before Class method completed *****");
		
	}

	@Test(enabled = false)
	public void runPath() {

		waitTime();
		/*
		 * File file = new File("log.txt"); FileOutputStream fos; try { fos =
		 * new FileOutputStream(file); PrintStream ps = new PrintStream(fos);
		 * System.setOut(ps); } catch (FileNotFoundException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		try {

			String csvFile = "C:/Users/juan.tribin/Desktop//ReportsUsers.txt";
			String csvFileInputs = "C:/Users/juan.tribin/Desktop//inputs.csv";
			BufferedReader br = null;
			BufferedReader br1 = null;
			String line = "";
			String[] rosters = null;
			String[] inputs = null;
			ArrayList<String> rosterOne = new ArrayList<String>();
			ArrayList<String> rosterTwo = new ArrayList<String>();
			ArrayList<String> rosterThree = new ArrayList<String>();
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();

			rosters = line.split(",");
			System.out.println(line);
			
			br.close();
			
			br1 = new BufferedReader(new FileReader(csvFileInputs));
			line = br1.readLine();
			inputs = line.split(",");
			System.out.println(line);
			br1.close();
			int x = 1;

			for (String value : rosters) {

				if (x < 31) {
					rosterOne.add(value);
					System.out.println("roster one " + value);
				}

				if (x >= 31) {
					rosterTwo.add(value);
					System.out.println("roster two " + value);
				}
				if (x >= 16) {
					rosterThree.add(value);
					System.out.println("roster three " + value);
				}
				x++;
			}
			waitTime();
			//classRosterPage = dashBoardPage.goToClassRoster();
			//classRosterPage.createRoster(rosterOne, "Reports School", "Class 1");
			//classRosterPage = dashBoardPage.goToClassRoster();
			//classRosterPage.createRoster(rosterTwo, "Reports School", "Class 1");
			//classRosterPage = dashBoardPage.goToClassRoster();
			//classRosterPage.createRoster(rosterThree, "Reports School", "Class 3");
			for(int y=1;y<3;y++){
			sechedulePage = dashBoardPage.goToSchedule();		
			customeWaitTime(2);
			sechedulePage.scheduleTestReports("Class "+y,(y));
			
			
			}
			
			returnToDashboard();
			dashBoardPage.logOut();
			
			waitTime();
			for(int z = 0; z <= inputs.length; z++){
				
				try{
			System.out
					.println("******** logging as "+rosters[z]+" ********");
			dashBoardPage = loginPage.loginSuccess(domain+rosters[z],
					genericPassword);
			
			System.out.println(dashBoardPage.addTiles());
			waitTime();
			deliveryPage = dashBoardPage.goToDelivery();
			waitTime();
			
			System.out.println("******** Taking the scheduled test ********");
			
			deliveryPage.takeTestReports(Integer.parseInt(inputs[z]));
			
			waitTime();
			dashBoardPage.logOut();
			System.out.println("************************************************");
			
				}catch(Exception e){
					System.out.println("Failed to access with " + rosters[z]);
					
				}			
			}		
			waitTime();
			System.out.println("******** logging as the teacher ********");
			dashBoardPage = loginPage.loginSuccess(domain + teacherUserName,
					genericPassword);
			waitTime();
			reportsPage = dashBoardPage.goToReports();
			waitTime();
			reportsPage.viewReport();
			waitTime();
			loginPage = dashBoardPage.logOut();

		} catch (Exception e) {
			System.out.println(" Failed " + e.getMessage());
		}

	}
	
	/**
	 * Login into the unity
	 * Create item
	 * Create test
	 * Schedule test 
	 * Student attempt  test with all correct answer
	 * Verify the report information
	 * 
	 */
	
	@Test(priority = 1)
	public void testVerifyReportWithAllCorrectAnswerForChoiceTypeItems(){
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(2);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, "desc");
		customeWaitTime(2);
		returnToDashboard();
		customeWaitTime(2);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(2);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
		String itemStrandCategory = itemsPage.getStrandCategory();
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(1);
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
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(2);
		returnToDashboard();
		customeWaitTime(2);
		testCreationPage = dashBoardPage.goToTestCreation();
		customeWaitTime(2);
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		testCreationPage.backToDashboard();
		customeWaitTime(2);
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(2);
		sechedulePage.scheduleTest(schoolName, rosterName, "N/A", testName, "Green", "110", "20%", "No");
		returnToDashboard();
		dashBoardPage.logOut();
		customeWaitTime(2);		
		//String createdTestId = "e54fb18e-7c9a-4160-857d-5eb41a7872c6";
		dashBoardPage = loginPage.loginSuccess(domain + autoStudent1,
				autoStudentPassword);
		customeWaitTime(2);
		dashBoardPage.addTiles();
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		deliveryPage.takeTest(true , 4 ,"Choice" , choiceCorrectAnswer);
		dashBoardPage.goToDelivery();
		System.out.println(testName.equalsIgnoreCase(deliveryPage.getTestinHistoryTable(createdTestId)));
		Assert.assertTrue(testName.equalsIgnoreCase(deliveryPage.getTestinHistoryTable(createdTestId)));
		Assert.assertEquals("100%", deliveryPage.getTestPercentCorrect(createdTestId));
		Assert.assertEquals("1", deliveryPage.getTestNoOfItems(createdTestId));
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
		Assert.assertEquals(testName, reportsPage.getTestName(testName));
		SoftAssert sAssert = new SoftAssert();
		sAssert.assertEquals(testName, reportsPage.getTestDuration(testName));
		sAssert.assertEquals(reportsPage.getNoOfStudentCompletedTest(testName) ,"1");
		sAssert.assertEquals(reportsPage.getNoOfStudentNotStartedTest(testName),"1");
		sAssert.assertEquals(reportsPage.getNoOfStudentStartedTest(testName),"0");
		sAssert.assertEquals(reportsPage.getNoOfStudentInQuantile(testName, 4, "All correct"),"1");
		//Assert.assertEquals(reportsPage.getReportCategory(testName, 1),itemStrandCategory);
		Assert.assertEquals(reportsPage.getReportCategoryPercent(testName, 1),"100%");


	}
	
	
	@Test(priority = 2)
	public void testVerifyReportWithAllWrongAnswerForChoiceTypeItems(){
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(2);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, "desc");
		customeWaitTime(2);
		returnToDashboard();
		customeWaitTime(2);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(2);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
		String itemStrandCategory = itemsPage.getStrandCategory();
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(1);
		itemsPage.backToDashboard();
		customeWaitTime(2);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(2);
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, "desc");
		waitTime();
		returnToDashboard();
		customeWaitTime(2);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(2);
		returnToDashboard();
		customeWaitTime(2);
		testCreationPage = dashBoardPage.goToTestCreation();
		customeWaitTime(2);
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		testCreationPage.backToDashboard();
		customeWaitTime(2);
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(2);
		sechedulePage.scheduleTest(schoolName, rosterName, "N/A", testName, "Green", "110", "20%", "No");
		returnToDashboard();
		dashBoardPage.logOut();
		customeWaitTime(2);		
		
		dashBoardPage = loginPage.loginSuccess(domain + autoStudent1,
				autoStudentPassword);
		customeWaitTime(2);
		dashBoardPage.addTiles();
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		deliveryPage.takeTest(false , 1 ,"Choice" , choiceCorrectAnswer);
		Assert.assertEquals(testName, deliveryPage.getTestinHistoryTable(createdTestId));
		Assert.assertEquals("0%", deliveryPage.getTestPercentCorrect(createdTestId));
		Assert.assertEquals("1", deliveryPage.getTestNoOfItems(createdTestId));
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
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		//String testName = "T_Auto_TB_1448424027107";
		Assert.assertEquals(testName, reportsPage.getTestName(testName));
		//Assert.assertEquals(testName, reportsPage.getTestDuration(testName));
		Assert.assertEquals(reportsPage.getNoOfStudentCompletedTest(testName) ,"1");
		Assert.assertEquals(reportsPage.getNoOfStudentNotStartedTest(testName),"1");
		Assert.assertEquals(reportsPage.getNoOfStudentStartedTest(testName),"0");
		Assert.assertEquals(reportsPage.getNoOfStudentInQuantile(testName, 1, "All Wrong"),"1");
		Assert.assertEquals(reportsPage.getReportCategory(testName, 1),itemStrandCategory);
		Assert.assertEquals(reportsPage.getReportCategoryPercent(testName, 1),"0%");
		
	}
	
	@Test(priority = 3)
	public void testVerifyReportForTestHavingMultipleItems(){
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(2);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, "desc");
		customeWaitTime(2);
		returnToDashboard();
		customeWaitTime(2);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(2);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
		itemsPage.copyItem(itemBankName ,"c1" +itemName ,1);
		itemsPage.copyItem(itemBankName ,"c1" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c3" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c4" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c2" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c6" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c7" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c8" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c9" +itemName,1);
		customeWaitTime(2);
		String itemStrandCategory = itemsPage.getStrandCategory();
		customeWaitTime(2);
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(1);
		itemsPage.backToDashboard();
		customeWaitTime(2);
		
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(2);
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, "desc");
		waitTime();
		returnToDashboard();
		customeWaitTime(2);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//testCreationPage.createTest(testName , testBankName , itemName);
		testCreationPage.createTestWithMultipleItems(testName , testBankName , itemBankName ,2);
		customeWaitTime(2);
		returnToDashboard();
		customeWaitTime(2);
		testCreationPage = dashBoardPage.goToTestCreation();
		customeWaitTime(2);
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		testCreationPage.backToDashboard();
		customeWaitTime(2);
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(2);
		sechedulePage.scheduleTest(schoolName, rosterName, "N/A", testName, "Green", "110", "20%", "No");
		returnToDashboard();
		dashBoardPage.logOut();
		customeWaitTime(2);		
		
		dashBoardPage = loginPage.loginSuccess(domain + autoStudent1,
				autoStudentPassword);
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		customeWaitTime(2);
		deliveryPage.takeTest(false , 1 ,"Choice" , choiceCorrectAnswer);
		Assert.assertEquals(testName, deliveryPage.getTestinHistoryTable(createdTestId));
		Assert.assertEquals("0%", deliveryPage.getTestPercentCorrect(createdTestId));
		Assert.assertEquals("2", deliveryPage.getTestNoOfItems(createdTestId));
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
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		//String testName = "T_Auto_TB_1448424027107";
		Assert.assertEquals(testName, reportsPage.getTestName(testName));
		//Assert.assertEquals(testName, reportsPage.getTestDuration(testName));
		Assert.assertEquals(reportsPage.getNoOfStudentCompletedTest(testName) ,"1");
		Assert.assertEquals(reportsPage.getNoOfStudentNotStartedTest(testName),"1");
		Assert.assertEquals(reportsPage.getNoOfStudentStartedTest(testName),"0");
		Assert.assertEquals(reportsPage.getNoOfStudentInQuantile(testName, 1, "All Wrong"),"1");
		//Assert.assertEquals(reportsPage.getReportCategory(testName, 1),itemStrandCategory);
		Assert.assertEquals(reportsPage.getReportCategoryPercent(testName, 1),"0%");
		
	}
	
	
	@Test(priority = 4)
	public void testVerifyReportForMultipleTextEntryItems(){
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(2);
		itemBankName = "Auto_Text_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, "desc");
		customeWaitTime(2);
		returnToDashboard();
		customeWaitTime(2);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(2);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionTextEntry , simpleMatchScoreProfile , textEntryCorrcetAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
		itemsPage.copyItem(itemBankName ,"c1_" +itemName ,1);
		itemsPage.copyItem(itemBankName ,"c1_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c3_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c4_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c2_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c6_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c7_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c8_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c9_" +itemName,1);
		customeWaitTime(2);
		String itemStrandCategory = itemsPage.getStrandCategory();
		customeWaitTime(2);
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(1);
		itemsPage.backToDashboard();
		customeWaitTime(2);
		
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(2);
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, "desc");
		waitTime();
		returnToDashboard();
		customeWaitTime(2);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//testCreationPage.createTest(testName , testBankName , itemName);
		testCreationPage.createTestWithMultipleItems(testName , testBankName , itemBankName ,2);
		customeWaitTime(2);
		returnToDashboard();
		customeWaitTime(2);
		testCreationPage = dashBoardPage.goToTestCreation();
		customeWaitTime(2);
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		testCreationPage.backToDashboard();
		customeWaitTime(2);
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(2);
		sechedulePage.scheduleTest(schoolName, rosterName, "N/A", testName, "Green", "110", "20%", "No");
		returnToDashboard();
		dashBoardPage.logOut();
		customeWaitTime(2);		
		
		dashBoardPage = loginPage.loginSuccess(domain + autoStudent1,
				autoStudentPassword);
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		customeWaitTime(2);
		deliveryPage.takeTest(true , 1 ,"Text Entry" , textEntryCorrcetAnswer);
		Assert.assertEquals(testName, deliveryPage.getTestinHistoryTable(createdTestId));
		Assert.assertEquals("20%", deliveryPage.getTestPercentCorrect(createdTestId));
		Assert.assertEquals("2", deliveryPage.getTestNoOfItems(createdTestId));
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
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		//String testName = "T_Auto_TB_1448424027107";
		Assert.assertEquals(testName, reportsPage.getTestName(testName));
		//Assert.assertEquals(testName, reportsPage.getTestDuration(testName));
		Assert.assertEquals(reportsPage.getNoOfStudentCompletedTest(testName) ,"1");
		Assert.assertEquals(reportsPage.getNoOfStudentNotStartedTest(testName),"1");
		Assert.assertEquals(reportsPage.getNoOfStudentStartedTest(testName),"0");
		Assert.assertEquals(reportsPage.getNoOfStudentInQuantile(testName, 4, "All Correct"),"1");
		//Assert.assertEquals(reportsPage.getReportCategory(testName, 1),itemStrandCategory);
		Assert.assertEquals(reportsPage.getReportCategoryPercent(testName, 1),"20%");
		
	}
	
	@Test(priority = 5)
	public void testVerifyReportForMultipleTextEntryItemsForInCorrectAnswer(){
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(2);
		itemBankName = "Auto_Text_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, "desc");
		customeWaitTime(2);
		returnToDashboard();
		customeWaitTime(2);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(2);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionTextEntry , simpleMatchScoreProfile , textEntryCorrcetAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
		itemsPage.copyItem(itemBankName ,"c1_" +itemName ,1);
		itemsPage.copyItem(itemBankName ,"c1_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c3_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c4_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c2_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c6_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c7_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c8_" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c9_" +itemName,1);
		customeWaitTime(2);
		String itemStrandCategory = itemsPage.getStrandCategory();
		customeWaitTime(2);
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(1);
		itemsPage.backToDashboard();
		customeWaitTime(2);
		
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(2);
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, "desc");
		waitTime();
		returnToDashboard();
		customeWaitTime(2);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//testCreationPage.createTest(testName , testBankName , itemName);
		testCreationPage.createTestWithMultipleItems(testName , testBankName , itemBankName ,2);
		customeWaitTime(2);
		returnToDashboard();
		customeWaitTime(2);
		testCreationPage = dashBoardPage.goToTestCreation();
		customeWaitTime(2);
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		testCreationPage.backToDashboard();
		customeWaitTime(2);
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(2);
		sechedulePage.scheduleTest(schoolName, rosterName, "N/A", testName, "Green", "110", "20%", "No");
		returnToDashboard();
		dashBoardPage.logOut();
		customeWaitTime(2);		
		
		dashBoardPage = loginPage.loginSuccess(domain + autoStudent1,
				autoStudentPassword);
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		customeWaitTime(2);
		deliveryPage.takeTest(false , 1 ,"Text Entry" , "in correct anwer");
		Assert.assertEquals(testName, deliveryPage.getTestinHistoryTable(createdTestId));
		Assert.assertEquals("0%", deliveryPage.getTestPercentCorrect(createdTestId));
		Assert.assertEquals("2", deliveryPage.getTestNoOfItems(createdTestId));
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
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		//String testName = "T_Auto_TB_1448424027107";
		Assert.assertEquals(testName, reportsPage.getTestName(testName));
		//Assert.assertEquals(testName, reportsPage.getTestDuration(testName));
		Assert.assertEquals(reportsPage.getNoOfStudentCompletedTest(testName) ,"1");
		Assert.assertEquals(reportsPage.getNoOfStudentNotStartedTest(testName),"1");
		Assert.assertEquals(reportsPage.getNoOfStudentStartedTest(testName),"0");
		Assert.assertEquals(reportsPage.getNoOfStudentInQuantile(testName, 1, "All In Correct"),"1");
		//Assert.assertEquals(reportsPage.getReportCategory(testName, 1),itemStrandCategory);
		Assert.assertEquals(reportsPage.getReportCategoryPercent(testName, 1),"0%");
		
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
		itemsBankPage = dashBoardPage.goToItemsBank();
		//customeWaitTime(2);
		long timeStamp = System.currentTimeMillis();
		itemBankName = "Auto_IB_" + timeStamp;
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, "desc");
		customeWaitTime(1);
		returnToDashboard();
		customeWaitTime(2);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(2);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(1);
		itemsPage.searchItem(itemName);
		customeWaitTime(1);
		itemsPage.addStandards();
		customeWaitTime(1);
		itemsPage.backToDashboard();
		customeWaitTime(2);
		testBankPage = dashBoardPage.goToTestsBank();
		waitTime();
		testBankName = "Assinged_TB_" + timeStamp;
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, "desc");
		returnToDashboard();
		customeWaitTime(2);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		testCreationPage.createTest(testName, testBankName, itemName);
		customeWaitTime(2);
		returnToDashboard();
		customeWaitTime(2);
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(2);
		sechedulePage.scheduleTest(schoolName, rosterName, "N/A", testName, "Green", "110", "20%", "No");
		returnToDashboard();
		reportsPage = dashBoardPage.goToReports();
		customeWaitTime(2);
		reportsPage.filterReportByContentArea("N/A");
		customeWaitTime(1);
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		reportsPage.openTestEventDetail(testName);
		Assert.assertEquals(testName, reportsPage.getTestEventTitle());
		Assert.assertEquals("not started".trim(), reportsPage.getTestEventDetail("utostudent1", 3, "Getting test status").trim());
		Assert.assertEquals("not started".trim(), reportsPage.getTestEventDetail("utostudent1", 3, "Getting test status").trim());
		reportsPage.waitForElementAndClick(reportsPage.backToReportLink);
		customeWaitTime(1);

	}
	
	@Test(priority = 7)
	public void testVerifyTestDetailReportForInProgressTest(){
		itemsBankPage = dashBoardPage.goToItemsBank();
		long timeStamp = System.currentTimeMillis();
		itemBankName = "Auto_IB_" + timeStamp;
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, "desc");
		customeWaitTime(1);
		returnToDashboard();
		customeWaitTime(2);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(2);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(2);
		itemsPage.searchItem(itemName);
		customeWaitTime(2);
		itemsPage.addStandards();
		customeWaitTime(2);
		itemsPage.backToDashboard();
		customeWaitTime(2);
		testBankPage = dashBoardPage.goToTestsBank();
		//customeWaitTime(2);
		waitTime();
		testBankName = "Auto_TB_" + timeStamp;
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, "desc");
		//waitTime();
		returnToDashboard();
		customeWaitTime(2);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		testCreationPage.createTest(testName, testBankName, itemName);
		customeWaitTime(2);
		returnToDashboard();
		customeWaitTime(2);
		testCreationPage = dashBoardPage.goToTestCreation();
		customeWaitTime(2);
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		testCreationPage.backToDashboard();
		customeWaitTime(2);
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(2);
		sechedulePage.scheduleTest(schoolName, rosterName, "N/A", testName, "Green", "110", "20%", "No");
		returnToDashboard();
		dashBoardPage.logOut();
		customeWaitTime(2);		
		dashBoardPage = loginPage.loginSuccess(domain + autoStudent1,
				autoStudentPassword);
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		customeWaitTime(2);
		deliveryPage.waitForElementAndClick(deliveryPage.exitButton);
		customeWaitTime(2);

		deliveryPage.backToDashboard();
		customeWaitTime(2);
		dashBoardPage.logOut();
		
		customeWaitTime(2);		
		dashBoardPage = loginPage.loginSuccess(domain + autoStudent1,
				autoStudentPassword);
		customeWaitTime(2);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
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
		dashBoardPage = loginPage.loginSuccess(domain + teacherUserName,
				genericPassword);
		reportsPage = dashBoardPage.goToReports();
		reportsPage.filterReportByContentArea("N/A");
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		reportsPage.openTestEventDetail(testName);
		Assert.assertEquals(testName, reportsPage.getTestEventTitle());
		Assert.assertEquals("in progress".trim(), reportsPage.getTestEventDetail("utostudent1", 3, "Getting test status").trim());
		Assert.assertEquals("in progress".trim(), reportsPage.getTestEventDetail("utostudent1", 3, "Getting test status").trim());
		reportsPage.waitForElementAndClick(reportsPage.backToReportLink);
		customeWaitTime(1);
	}
	
	@Test(priority = 8)
	public void testVerifyTestDetailReportForScoredTest(){
			itemsBankPage = dashBoardPage.goToItemsBank();
			long timeStamp = System.currentTimeMillis();
			itemBankName = "Auto_IB_" + timeStamp;
			System.out.println("******** " + itemBankName + "  Item bank creation ********");
			itemsBankPage.createBank(itemBankName, "desc");
			customeWaitTime(1);
			returnToDashboard();
			customeWaitTime(2);
			itemsPage = dashBoardPage.goToItems();
			customeWaitTime(2);
			itemName = "I_" + itemBankName;
			System.out.println("******** " + itemName + "  Item creation ********");
			itemsPage.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
			customeWaitTime(2);
			itemsPage.searchItem(itemName);
			customeWaitTime(2);
			itemsPage.addStandards();
			customeWaitTime(2);
			itemsPage.backToDashboard();
			customeWaitTime(2);
			testBankPage = dashBoardPage.goToTestsBank();
			waitTime();
			testBankName = "Auto_TB_" + timeStamp;
			System.out.println("******** " + testBankName + "  Test bank creation ********");
			testBankPage.createBank(testBankName, "desc");
			returnToDashboard();
			customeWaitTime(2);
			testCreationPage = dashBoardPage.goToTestCreation();
			testName = "T_" + testBankName;
			System.out.println("******** " + testName + "  Test creation ********");
			testCreationPage.createTest(testName, testBankName, itemName);
			customeWaitTime(2);
			returnToDashboard();
			customeWaitTime(2);
			testCreationPage = dashBoardPage.goToTestCreation();
			customeWaitTime(2);
			testCreationPage.searchTest(testName);
			String createdTestId = testCreationPage.getTestId();
			testCreationPage.backToDashboard();
			customeWaitTime(2);
			sechedulePage = dashBoardPage.goToSchedule();
			customeWaitTime(2);
			sechedulePage.scheduleTest(schoolName, rosterName, "N/A", testName, "Green", "110", "20%", "No");
			returnToDashboard();
			dashBoardPage.logOut();
			customeWaitTime(2);		
			dashBoardPage = loginPage.loginSuccess(domain + autoStudent1,
					autoStudentPassword);
			customeWaitTime(2);
			deliveryPage = dashBoardPage.goToDelivery();
			waitTime();
			System.out.println("******** Taking the scheduled test ********");
			Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
			deliveryPage.startScheduledTest(createdTestId);
			customeWaitTime(2);
			deliveryPage.takeTest(true , 4 ,"Choice" , choiceCorrectAnswer);
			deliveryPage.backToDashboard();
			customeWaitTime(2);
			dashBoardPage.logOut();
			dashBoardPage = loginPage.loginSuccess(domain + autoStudent1,
					autoStudentPassword);
			customeWaitTime(2);
			deliveryPage = dashBoardPage.goToDelivery();
			waitTime();
			System.out.println("******** Taking the scheduled test ********");
			Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
			deliveryPage.startScheduledTest(createdTestId);
			customeWaitTime(2);
			deliveryPage.takeTest(true , 4 ,"Choice" , choiceCorrectAnswer);
			deliveryPage.backToDashboard();
			customeWaitTime(2);
			dashBoardPage.logOut();
			customeWaitTime(2);	
			dashBoardPage = loginPage.loginSuccess(domain + teacherUserName,
					genericPassword);
			reportsPage = dashBoardPage.goToReports();
			reportsPage.filterReportByContentArea("N/A");
			reportsPage.filterReportByClassRoster("autoroster");
			customeWaitTime(2);
			reportsPage.openTestEventDetail(testName);
			Assert.assertEquals(testName, reportsPage.getTestEventTitle());
			Assert.assertEquals("scored".trim(), reportsPage.getTestEventDetail("utostudent1", 3, "Getting test status").trim());
			Assert.assertEquals("scored".trim(), reportsPage.getTestEventDetail("utostudent1", 3, "Getting test status").trim());
			reportsPage.waitForElementAndClick(reportsPage.backToReportLink);
		}
	
	@Test(priority = 9)
	public void testVerifyStudentsForTestByStatus(){
	    		itemsBankPage = dashBoardPage.goToItemsBank();
	    		customeWaitTime(2);
	    		itemBankName = "Auto_IB_" + System.currentTimeMillis();
	    		System.out.println("******** " + itemBankName + "  Item bank creation ********");
	    		itemsBankPage.createBank(itemBankName, "desc");
	    		customeWaitTime(2);
	    		returnToDashboard();
	    		customeWaitTime(2);
	    		itemsPage = dashBoardPage.goToItems();
	    		customeWaitTime(2);
	    		itemName = "I_" + itemBankName;
	    		System.out.println("******** " + itemName + "  Item creation ********");
	    		itemsPage.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
	    		customeWaitTime(2);
	    		itemsPage.searchItem(itemName);
	    		customeWaitTime(2);
	    		itemsPage.addStandards();
	    		customeWaitTime(2);
	    		itemsPage.copyItem(itemBankName ,"c1" +itemName ,1);
	    		itemsPage.copyItem(itemBankName ,"c1" +itemName,1);
	    		itemsPage.copyItem(itemBankName ,"c3" +itemName,1);
	    		itemsPage.copyItem(itemBankName ,"c4" +itemName,1);
	    		itemsPage.copyItem(itemBankName ,"c2" +itemName,1);
	    		itemsPage.copyItem(itemBankName ,"c6" +itemName,1);
	    		itemsPage.copyItem(itemBankName ,"c7" +itemName,1);
	    		itemsPage.copyItem(itemBankName ,"c8" +itemName,1);
	    		itemsPage.copyItem(itemBankName ,"c9" +itemName,1);
	    		customeWaitTime(2);
	    		String itemStrandCategory = itemsPage.getStrandCategory();
	    		customeWaitTime(2);
	    		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
	    		customeWaitTime(1);
	    		itemsPage.backToDashboard();
	    		customeWaitTime(2);
	    		
	    		testBankPage = dashBoardPage.goToTestsBank();
	    		customeWaitTime(2);
	    		waitTime();
	    		testBankName = "Auto_TB_" + System.currentTimeMillis();
	    		System.out.println("******** " + testBankName + "  Test bank creation ********");
	    		testBankPage.createBank(testBankName, "desc");
	    		waitTime();
	    		returnToDashboard();
	    		customeWaitTime(2);
	    		testCreationPage = dashBoardPage.goToTestCreation();
	    		testName = "T_" + testBankName;
	    		System.out.println("******** " + testName + "  Test creation ********");
	    		//testCreationPage.createTest(testName , testBankName , itemName);
	    		testCreationPage.createTestWithMultipleItems(testName , testBankName , itemBankName ,2);
	    		customeWaitTime(2);
	    		returnToDashboard();
	    		customeWaitTime(2);
	    		testCreationPage = dashBoardPage.goToTestCreation();
	    		customeWaitTime(2);
	    		testCreationPage.searchTest(testName);
	    		String createdTestId = testCreationPage.getTestId();
	    		testCreationPage.backToDashboard();
	    		customeWaitTime(2);
	    		sechedulePage = dashBoardPage.goToSchedule();
	    		customeWaitTime(2);
	    		sechedulePage.scheduleTest(schoolName, rosterName, "N/A", testName, "Green", "110", "20%", "No");
	    		returnToDashboard();
	    		dashBoardPage.logOut();
	    		customeWaitTime(2);		
	    		
	    		dashBoardPage = loginPage.loginSuccess(domain + autoStudent1,
	    				autoStudentPassword);
	    		customeWaitTime(2);
	    		deliveryPage = dashBoardPage.goToDelivery();
	    		waitTime();
	    		System.out.println("******** Taking the scheduled test ********");
	    		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
	    		deliveryPage.startScheduledTest(createdTestId);
	    		customeWaitTime(2);
	    		deliveryPage.takeTest(false , 1 ,"Choice" , choiceCorrectAnswer);
	    		Assert.assertEquals(testName, deliveryPage.getTestinHistoryTable(createdTestId));
	    		Assert.assertEquals("0%", deliveryPage.getTestPercentCorrect(createdTestId));
	    		Assert.assertEquals("2", deliveryPage.getTestNoOfItems(createdTestId));
	    		deliveryPage.backToDashboard();
	    		customeWaitTime(2);
	    		dashBoardPage.logOut();
	    		customeWaitTime(2);	
	    		dashBoardPage = loginPage.loginSuccess(domain + teacherUserName,
	    				genericPassword);
	    		customeWaitTime(2);
	    		reportsPage = dashBoardPage.goToReports();
	    		customeWaitTime(2);
	    		//reportsPage.waitForElementAndClick(reportsPage.resetSearchFilter);
	    		reportsPage.filterReportByContentArea("N/A");
	    		reportsPage.filterReportByClassRoster("autoroster");
	    		customeWaitTime(2);
	    		//String testName = "T_Auto_TB_1448424027107";
	    		Assert.assertEquals(testName, reportsPage.getTestName(testName));
	    		
	    		Assert.assertTrue(reportsPage.verifyStudentsByStatus("completed"));
	    		Assert.assertTrue(reportsPage.verifyStudentsByStatus("completed"));
	    		Assert.assertTrue(reportsPage.verifyStudentsByStatus("completed"));
	    		
	    		
	    	}
	
	@Test(priority = 10)
	public void testVerifySchoolAdminViewReportDetail(){
		driver.get(url);
		loginPage = new Login(driver);
		System.out.println("******** logging as super administrator ********");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testTeacher1"), unitytestdata.getProperty("genericPassword"));
		customeWaitTime(2);
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
		itemsBankPage = dashBoardPage.goToItemsBank();
		timestamp = System.currentTimeMillis();
		itemBankName = "AMT_" + timestamp;
		itemsBankPage.createBank(itemBankName, "desc");
		returnToDashboard();
		itemsPage = dashBoardPage.goToItems();
		itemName = "I_" + timestamp;
		itemsPage.createItem(itemName, itemBankName, interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);		
		itemsPage.searchItem(itemName);
		itemsPage.addStandards();
		copyItemName = "copy_" + itemName;
		itemsPage.copyMultipleItems(itemBankName, itemName, copyItemName, 1, itemCount -1);
		returnToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		testBankName = "AMT_" + timestamp;
		testBankPage.createBank(testBankName, "desc");
		returnToDashboard();
		customeWaitTime(5);
		waitTime();
		testCreationPage = dashBoardPage.goToTestCreation();
		waitTime();
		testName = "T_" + timestamp;
		System.out.println("******** Test creation ********");
		testCreationPage.createTestWithMultipleItems(testName,
				testBankName, itemBankName, itemCount);
		testid = testCreationPage.getTestId();
		returnToDashboard();
		customeWaitTime(5);
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
	    System.out.println("******** Event creation ********");
	   sechedulePage.scheduleTest(testschool,
			   testroster, "N/A", testName, "Red", "120",
		"100%", "Yes");
		waitTime();
	   loginPage = sechedulePage.logOut();
	   waitTime();
	   dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testSchooladmin"), unitytestdata.getProperty("genericPassword"));
	   customeWaitTime(2);
	   reportsPage = dashBoardPage.goToReports();
	   reportsPage.filterReportByContentArea("N/A");
	    reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(2);
		//String testName = "T_Auto_TB_1448424027107";
		Assert.assertEquals(testName, reportsPage.getTestName(testName));
		reportsPage.openTestEventDetail(testName);
	   
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