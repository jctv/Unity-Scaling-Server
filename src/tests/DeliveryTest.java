package tests;

import java.io.File;
import java.util.Properties;

import generic.BaseTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Delivery;
import pages.Items;
import pages.Login;
import pages.Schedule;
import pages.TestCreation;

public class DeliveryTest extends BaseTest {
	Login loginPage;
	DashBoard dashBoardPage;
	Delivery DeliveryPage;
	Properties unitytestdata;
	
	Items itemsPage;
	TestCreation testCreationPage;

	String resources = "src" + File.separator + "resources"
			+ File.separator;
	String unitytestDataFile = resources + "unitytestdata.properties";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	
	Properties unitymessages;
	
	String teacher1;
	String student1;
	String genericPassword;
	String roster;
	String school;
	String itemName ;
	String testName;
	
	Schedule sechedulePage;
	Delivery deliveryPage;
	
	
	public DeliveryTest() {
		super();
	}
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unitytestDataFile);
		unitymessages = getUnityMessagesProperty(unityMessageFile);

		teacher1 = unitytestdata.getProperty("teacher1");
		student1 = unitytestdata.getProperty("stduent1");
		genericPassword = unitytestdata.getProperty("genericPassword");
		roster = unitytestdata.getProperty("roster");
		school = unitytestdata.getProperty("school");
	}

	@BeforeMethod
	public void setUp() {
		driver.get(url);

		loginPage = new Login(driver);
		System.out.println("******** logging as student  ********");

		dashBoardPage = loginPage.loginSuccess("load/sre149c",
				"12345");
	

	}
	
	@Test(priority = 1)
	public void  takeTest(){
		customeWaitTime(5);
		DeliveryPage = dashBoardPage.goToDelivery();
		customeWaitTime(5);
		Assert.assertTrue(DeliveryPage.takeAndVefiryTestResults("100%",
				"Escuela,1,Space with Chocolate,3,SPACE,4,School,5,Barrio,2"));
		
	}
	
	/**
	 * Login as teacher 
	 * Create test 
	 * Schedule test
	 * Login as student
	 * Start test
	 * exit test without save the answer
	 * Verify the confirmation message
	 */
	
	@Test(priority = 0)
	public void testVerifyExitMessgage(){
		driver.get(url);
		loginPage = new Login(driver);
		System.out.println("******** logging as teacher   ********");
		dashBoardPage = loginPage.loginSuccess(teacher1,
				genericPassword);
		itemsPage = dashBoardPage.goToItems();
		
		long timestamp = System.currentTimeMillis();
		itemName = "I_" + timestamp;
		testName = "T_"+ timestamp;
		
		itemsPage.createItem(itemName, "My Items");
		itemsPage.backToDashboard();
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.createTest(testName, "My Tests", itemName);
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		testCreationPage.backToDashboard();
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(5);
		sechedulePage.scheduleTest(school, roster, "N/A", testName, "Green", "120", "100%", "No");
		returnToDashboard();
		dashBoardPage.logOut();
		customeWaitTime(5);
		System.out.println("******** logging as student   ********");
		dashBoardPage = loginPage.loginSuccess(student1,genericPassword);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		deliveryPage = dashBoardPage.goToDelivery();
		customeWaitTime(5);
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		deliveryPage.waitForElementAndClick(deliveryPage.exitButton);
		customeWaitTime(2);
		Assert.assertEquals(deliveryPage.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("testExit").trim());
		deliveryPage.waitForElementAndClick(deliveryPage.globalModalOKCancelSaveButton);
    	customeWaitTime(2);
		
	}
	

	
}
