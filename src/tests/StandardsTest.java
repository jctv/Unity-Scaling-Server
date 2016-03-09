package tests;

import java.io.File;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.DashBoard;
import pages.Delivery;
import pages.HandScoring;
import pages.ItemImport;
import pages.Items;
import pages.ItemsBank;
import pages.Login;
import pages.Reports;
import pages.Schedule;
import pages.Standards;
import pages.TestCreation;
import pages.TestsBank;
import generic.BaseTest;

public class StandardsTest extends BaseTest{
	
	Login loginPage;
	DashBoard dashBoardPage;
	Standards standardsPage;
	ItemsBank itemsBankPage;
	Items itemsPage;
	TestCreation testCreationPage;
	Schedule sechedulePage;
	Reports reportPage;
	TestsBank testBankPage;
	ItemImport itemsImportPage;
	HandScoring handScoringPage;
	Delivery deliveryPage;
	
	Properties unitytestdata;
	Properties unitystandarddata;
	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator;
	
	String help = "help" + File.separator;
	
	String unityTestDataFile = resourcesLocation  + "unitytestdata.properties";
	String unityStandardDataFile = resourcesLocation + "unitystandarddata.properties";
	
	String optionVideo;
	String optionGames;
	
	String itemBank;
	String itemName;
	String testBank;
	String testName;
	String copyItemName;
	String rosterName;
	String SchoolName;
	
	String interactionChoice;
	String simpleMatchScoreProfile;
	String choiceCorrectAnswer;
	String createdTestId;

	long timeStamp;
	int itemCount = 10;
	public StandardsTest() {
		super();
	}

	private SoftAssert softAssert = new SoftAssert();

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		unitystandarddata = getUnityMessagesProperty(unityStandardDataFile);
		optionVideo = unitystandarddata.getProperty("resourceVideo");
		optionGames = unitystandarddata.getProperty("resourceGames");
		optionGames = unitystandarddata.getProperty("resourceGames");
		
		timeStamp = System.currentTimeMillis();
		itemBank = unitystandarddata.getProperty("itemBank") + timeStamp;
		itemName = unitystandarddata.getProperty("itemName") + timeStamp;
		testBank = unitystandarddata.getProperty("testBank") + timeStamp;
		testName = unitystandarddata.getProperty("testName") + timeStamp;
		
		copyItemName = unitystandarddata.getProperty("copyItemName") + timeStamp;
		
		rosterName = unitystandarddata.getProperty("rosterName") ;
		SchoolName = unitystandarddata.getProperty("SchoolName");
		
		interactionChoice = unitytestdata.getProperty("interactionChoice");
		simpleMatchScoreProfile = unitytestdata.getProperty("simpleMatchScoreProfile");
		choiceCorrectAnswer = unitytestdata.getProperty("choiceCorrectAnswer");

		
		
		
	}
	
	@BeforeClass
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		dashBoardPage.addTiles();
		customeWaitTime(5);
		standardsPage = dashBoardPage.goToStandards();
	}
	
	@Test(priority=1)
	public void testViewInstructionalResources(){
		standardsPage.openViewInstructionalResources();
		customeWaitTime(2);
		softAssert.assertTrue(standardsPage.getDropDownOptions(standardsPage.selectResource).get(0).getText().contains(optionVideo));
		softAssert.assertTrue(standardsPage.getDropDownOptions(standardsPage.selectResource).get(1).getText().contains(optionGames));
		softAssert.assertTrue(standardsPage.resourceList.isDisplayed());
		standardsPage.selectOption(standardsPage.selectResource, "Game");
		customeWaitTime(5);
		softAssert.assertTrue(standardsPage.resourceList.isDisplayed());
		//standardsPage.verifyAllResources();
		standardsPage.waitForElementAndClick(standardsPage.closeButton);
		standardsPage.backToDashboard();
		dashBoardPage.logOut();
		customeWaitTime(5);
        }
	
	@Test(priority = 2)
	public void testVerifyStandardResourcesForStudent(){
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("teacher1"),
				unitytestdata.getProperty("genericPassword"));
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
		itemsPage.createItem(itemName, itemBank ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		itemsPage.searchItem(itemName);
		itemsPage.addStandards();
		itemsPage.copyMultipleItems(itemBank, itemName, copyItemName, 1, itemCount -1);
		returnToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		testBankPage.createBank(testBank, "desc");
		returnToDashboard();
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.createTestWithMultipleItems(testName,
				testBank, itemBank, itemCount);
		customeWaitTime(5);
		testCreationPage.searchTest(testName);
		createdTestId = testCreationPage.getTestId();
		returnToDashboard();
		customeWaitTime(5);
		sechedulePage = dashBoardPage.goToSchedule();
		waitTime();
		System.out.println("******** Event creation ********");
		sechedulePage.scheduleTest(SchoolName,
				rosterName, "N/A", testName, "Red", "120",
				"100%", "Yes");
		waitTime();
		returnToDashboard();
		loginPage = dashBoardPage.logOut();
		System.out.println("************************************************");
		waitTime();
		System.out
				.println("******** logging as the first  student ********");
		
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("stduent1"),
				unitytestdata.getProperty("genericPassword"));
		
		System.out.println(dashBoardPage.addTiles());
		waitTime();
		deliveryPage.startScheduledTest(createdTestId);
		deliveryPage.takeTest(true , 4 , interactionChoice,"a");
		deliveryPage.backToDashboard();
		customeWaitTime(5);
		reportPage = dashBoardPage.goToReports();
		reportPage.openStudentTestReport(testName);
		customeWaitTime(2);
		reportPage.openLearnStrand(testName);
		softAssert.assertTrue(standardsPage.getDropDownOptions(standardsPage.selectResource).get(0).getText().contains(optionVideo));
		softAssert.assertTrue(standardsPage.getDropDownOptions(standardsPage.selectResource).get(1).getText().contains(optionGames));
		softAssert.assertTrue(standardsPage.resourceList.isDisplayed());
		standardsPage.selectOption(standardsPage.selectResource, optionGames);
		customeWaitTime(5);
		softAssert.assertTrue(standardsPage.resourceList.isDisplayed());
		reportPage.closeResourcePopUP();
		customeWaitTime(5);
		reportPage.openExploreStrand(testName);
		softAssert.assertTrue(standardsPage.getDropDownOptions(standardsPage.selectResource).get(0).getText().contains(optionGames));
		softAssert.assertTrue(standardsPage.getDropDownOptions(standardsPage.selectResource).get(1).getText().contains(optionVideo));
		softAssert.assertTrue(standardsPage.resourceList.isDisplayed());
		standardsPage.selectOption(standardsPage.selectResource, optionVideo);
		customeWaitTime(5);
		reportPage.closeResourcePopUP();
	}
}
