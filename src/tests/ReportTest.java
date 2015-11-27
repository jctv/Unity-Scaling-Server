package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;

import generic.BaseTest;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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

public class ReportTest extends BaseTest {

	HappyPathTest Nav;
	public String user = "t9222015";
	public String genericPassword = "12345";
	
	public String autoSystemAdmin = "auto25/admin";
	public String autoPassword = "password";
	public String autoStudent = "auto25/autostudent1";
	public String autoStudentPassword = "12345";
	
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
	
	String itemBankName ;
	String itemName ;
	
	String testBankName ;
	String testName ;
	
	String interactionChoice = "Choice";
	String interactionTextEntry = "Text Entry";
	String simpleMatchScoreProfile = "Match";
	String mapScoreProfile = "Map";
	String handScoreProfile  = "Hand Scoring";
	String choiceCorrectAnswer = "set D correct Answer";
	String  textEntryCorrcetAnswer = "Auto Text Entry";

	public ReportTest() {
		super();

	}

	@BeforeMethod
	public void setUp() {

		driver.get(url);

		loginPage = new Login(driver);
		/*System.out.println("******** logging as super administrator ********");
		dashBoardPage = loginPage.loginSuccess(autoSystemAdmin, autoPassword);
		// driver.get(url + "#dashboard");
		customeWaitTime(10);
		dashBoardPage.addTiles();
		customeWaitTime(10);
		// System.out.println(dashBoardPage.addTiles());
*/
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
			BufferedReader br2 = null;
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
			
			br2 = new BufferedReader(new FileReader(csvFileInputs));
			line = br2.readLine();
			inputs = line.split(",");
			System.out.println(line);
			br2.close();
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
			//classRosterPage.createRoster(rosterTwo, "Reports School", "Class 2");
			//classRosterPage = dashBoardPage.goToClassRoster();
			//classRosterPage.createRoster(rosterThree, "Reports School", "Class 3");
			for(int y=1;y<3;y++){
			sechedulePage = dashBoardPage.goToSchedule();		
			sechedulePage.scheduleTestReports("Class "+y,(y));
			
			
			}
			dashBoardPage.logOut();
			
			waitTime();
			for(int z = 0; z <= inputs.length; z++){
				
				try{
			System.out
					.println("******** logging as "+rosters[z]+" ********");
			dashBoardPage = loginPage.loginSuccess(rosters[z],
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
			dashBoardPage = loginPage.loginSuccess(user,
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
		customeWaitTime(10);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, "desc");
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(10);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(10);
		itemsPage.searchItem(itemName);
		customeWaitTime(5);
		itemsPage.addStandards();
		customeWaitTime(5);
		String itemStrandCategory = itemsPage.getStrandCategory();
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(2);
		itemsPage.backToDashboard();
		customeWaitTime(10);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(10);
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, "desc");
		waitTime();
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(10);
		testCreationPage = dashBoardPage.goToTestCreation();
		customeWaitTime(10);
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		testCreationPage.backToDashboard();
		customeWaitTime(10);
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(10);
		sechedulePage.scheduleTest("Auto School", "autoRoster", "N/A", testName, "Green", "120", "100%", "No");
		dashBoardPage.logOut();
		customeWaitTime(10);		
		
		dashBoardPage = loginPage.loginSuccess(autoStudent,
				autoStudentPassword);
		customeWaitTime(10);
		dashBoardPage.addTiles();
		customeWaitTime(10);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		deliveryPage.takeTest(true , 4 ,"Choice");
		Assert.assertEquals(testName, deliveryPage.getTestinHistoryTable(createdTestId));
		Assert.assertEquals("100%", deliveryPage.getTestPercentCorrect(createdTestId));
		Assert.assertEquals("1", deliveryPage.getTestNoOfItems(createdTestId));
		deliveryPage.backToDashboard();
		customeWaitTime(10);
		dashBoardPage.logOut();
		customeWaitTime(10);	
		dashBoardPage = loginPage.loginSuccess(autoSystemAdmin,
				autoPassword);
		customeWaitTime(10);
		reportsPage = dashBoardPage.goToReports();
		customeWaitTime(10);
		//reportsPage.waitForElementAndClick(reportsPage.resetSearchFilter);
		reportsPage.filterReportByContentArea("N/A");
		reportsPage.filterReportByClassRoster("autoRoster");
		customeWaitTime(10);
		//String testName = "T_Auto_TB_1448454057207";
		Assert.assertEquals(testName, reportsPage.getTestName(testName));
		Assert.assertEquals(testName, reportsPage.getTestDuration(testName));
		Assert.assertEquals(reportsPage.getNoOfStudentCompletedTest(testName) ,"1");
		Assert.assertEquals(reportsPage.getNoOfStudentNotStartedTest(testName),"1");
		Assert.assertEquals(reportsPage.getNoOfStudentStartedTest(testName),"0");
		Assert.assertEquals(reportsPage.getNoOfStudentInQuantile(testName, 4, "All correct"),"1");
		Assert.assertEquals(reportsPage.getReportCategory(testName, 1),itemStrandCategory);
		Assert.assertEquals(reportsPage.getReportCategoryPercent(testName, 1),"100%");


	}
	
	
	@Test(priority = 2)
	public void testVerifyReportWithAllWrongAnswerForChoiceTypeItems(){
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(10);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, "desc");
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(10);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(10);
		itemsPage.searchItem(itemName);
		customeWaitTime(5);
		itemsPage.addStandards();
		customeWaitTime(10);
		String itemStrandCategory = itemsPage.getStrandCategory();
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(2);
		itemsPage.backToDashboard();
		customeWaitTime(10);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(10);
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, "desc");
		waitTime();
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(10);
		testCreationPage = dashBoardPage.goToTestCreation();
		customeWaitTime(10);
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		testCreationPage.backToDashboard();
		customeWaitTime(10);
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(10);
		sechedulePage.scheduleTest("Auto School", "autoroster", "N/A", testName, "Green", "120", "100%", "No");
		dashBoardPage.logOut();
		customeWaitTime(10);		
		
		dashBoardPage = loginPage.loginSuccess(autoStudent,
				autoStudentPassword);
		customeWaitTime(10);
		dashBoardPage.addTiles();
		customeWaitTime(10);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		deliveryPage.takeTest(false , 1 ,"Choice");
		Assert.assertEquals(testName, deliveryPage.getTestinHistoryTable(createdTestId));
		Assert.assertEquals("0%", deliveryPage.getTestPercentCorrect(createdTestId));
		Assert.assertEquals("1", deliveryPage.getTestNoOfItems(createdTestId));
		deliveryPage.backToDashboard();
		customeWaitTime(10);
		dashBoardPage.logOut();
		customeWaitTime(10);	
		dashBoardPage = loginPage.loginSuccess(autoSystemAdmin,
				autoPassword);
		customeWaitTime(10);
		reportsPage = dashBoardPage.goToReports();
		customeWaitTime(10);
		//reportsPage.waitForElementAndClick(reportsPage.resetSearchFilter);
		reportsPage.filterReportByContentArea("N/A");
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(10);
		//String testName = "T_Auto_TB_1448454057207";
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
		itemBankName = "Auto_IB_1448617320485"; 
		
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(10);
		//itemBankName = "Auto_IB_" + System.currentTimeMillis();
		itemBankName = "Auto_IB_1448617320485"; 
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, "desc");
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(10);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		customeWaitTime(10);
		itemsPage.searchItem(itemName);
		customeWaitTime(5);
		itemsPage.addStandards();
		customeWaitTime(5);
		itemsPage.copyItem(itemBankName ,"c1" +itemName ,1);
		itemsPage.copyItem(itemBankName ,"c2" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c3" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c4" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c5" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c6" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c7" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c8" +itemName,1);
		itemsPage.copyItem(itemBankName ,"c9" +itemName,1);
		customeWaitTime(10);
		String itemStrandCategory = itemsPage.getStrandCategory();
		customeWaitTime(5);
		itemsPage.waitForElementAndClick(itemsPage.closeIcon);
		customeWaitTime(2);
		itemsPage.backToDashboard();
		customeWaitTime(10);
		
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(10);
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, "desc");
		waitTime();
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//testCreationPage.createTest(testName , testBankName , itemName);
		testCreationPage.createTestWithMultipleItems(testName , testBankName , itemBankName ,10);
		customeWaitTime(10);
		testCreationPage = dashBoardPage.goToTestCreation();
		customeWaitTime(10);
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		testCreationPage.backToDashboard();
		customeWaitTime(10);
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(10);
		sechedulePage.scheduleTest("Auto School", "autoroster", "N/A", testName, "Green", "120", "100%", "No");
		dashBoardPage.logOut();
		customeWaitTime(10);		
		
		dashBoardPage = loginPage.loginSuccess(autoStudent,
				autoStudentPassword);
		customeWaitTime(10);
		deliveryPage = dashBoardPage.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		deliveryPage.takeTest(false , 1 ,"Choice");
		Assert.assertEquals(testName, deliveryPage.getTestinHistoryTable(createdTestId));
		Assert.assertEquals("0%", deliveryPage.getTestPercentCorrect(createdTestId));
		Assert.assertEquals("1", deliveryPage.getTestNoOfItems(createdTestId));
		deliveryPage.backToDashboard();
		customeWaitTime(10);
		dashBoardPage.logOut();
		customeWaitTime(10);	
		dashBoardPage = loginPage.loginSuccess(autoSystemAdmin,
				autoPassword);
		customeWaitTime(10);
		reportsPage = dashBoardPage.goToReports();
		customeWaitTime(10);
		//reportsPage.waitForElementAndClick(reportsPage.resetSearchFilter);
		reportsPage.filterReportByContentArea("N/A");
		reportsPage.filterReportByClassRoster("autoroster");
		customeWaitTime(10);
		//String testName = "T_Auto_TB_1448454057207";
		Assert.assertEquals(testName, reportsPage.getTestName(testName));
		//Assert.assertEquals(testName, reportsPage.getTestDuration(testName));
		Assert.assertEquals(reportsPage.getNoOfStudentCompletedTest(testName) ,"1");
		Assert.assertEquals(reportsPage.getNoOfStudentNotStartedTest(testName),"1");
		Assert.assertEquals(reportsPage.getNoOfStudentStartedTest(testName),"0");
		Assert.assertEquals(reportsPage.getNoOfStudentInQuantile(testName, 1, "All Wrong"),"1");
		//Assert.assertEquals(reportsPage.getReportCategory(testName, 1),itemStrandCategory);
		Assert.assertEquals(reportsPage.getReportCategoryPercent(testName, 1),"0%");
		
	}
	
	@AfterMethod
	public void cleanUp(){
		reportsPage.backToDashboard();	
		customeWaitTime(5);
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(10);
		sechedulePage.waitForElementAndClick(sechedulePage.dayButton);
		customeWaitTime(5);
		sechedulePage.waitForElementAndClick(sechedulePage.nextButton);
		customeWaitTime(5);
		sechedulePage.waitForElementAndDoubleClick(sechedulePage.calendarArea);
		customeWaitTime(5);
		sechedulePage.waitForElementAndClick(sechedulePage.startNowButton);
		customeWaitTime(5);
		sechedulePage.backToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(10);
		itemsBankPage.deleteItemBank(itemBankName);
		customeWaitTime(5);
		itemsBankPage.backToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemsPage.deleteItem(itemName);
		customeWaitTime(5);
		itemsPage.backToDashboard();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(10);
		testBankPage.deleteTestBank(testBankName);
		//sechedulePage.scheduleTest("Auto School", "autoroster", "N/A", testName, "Green", "120", "100%", "No");
		
		
	}
}