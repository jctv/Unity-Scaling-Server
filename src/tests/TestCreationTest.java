package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Login;
import pages.TestCreation;
import pages.TestsBank;
import generic.BaseTest;

public class TestCreationTest extends BaseTest {
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	TestCreation testCreationPageObject;
	TestsBank testBankPageObject;

	Properties unitymessages;

	String defaultUser = "admin";
	String defaultPassword = "@simple1";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	String testBankName;
	String copyTestBankName ;
	String testName;
	String copiedTestName;
	
	public TestCreationTest() {
		super();

	}
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		
	}
	
	@BeforeMethod
	public void setUp() {
		System.out.println("Load Unity url - " + url);
		driver.get(url);
		loginPageObject = new Login(driver);
		System.out.println("******** logging as System Admin -- " + defaultUser
				+ "******** ");
		dashBoardPageObject = loginPageObject.loginSuccess(defaultUser,
				defaultPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
	}
	
	
	/**
	 * Login into the Unity
	 * Go to the item bank tile
	 * Create two test bank
	 * Create test
	 * Edit test
	 * validate messages
	 * Search test
	 * Copy the test 
	 * validate messages
	 * Search test
	 * Delete test 
	 * validate messages
	 * Delete both the test Bank
	 */
	
	@Test(priority = 0)
	public void testItemAlertMessage(){
		testBankName = "TB_" + System.currentTimeMillis();
		copyTestBankName = "Copy" + testBankName;	
		testName = "T_" + testBankName;
	    copiedTestName = "copy " + testName;
		
	    testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.createBank(testBankName, "Desc");
		waitTime(); 
		returnToDashboard();
		customeWaitTime(5);
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.createBank(copyTestBankName, "Desc");
	    waitTime();
	    returnToDashboard();
		customeWaitTime(5);
	    testCreationPageObject = dashBoardPageObject.goToTestCreation();
	    waitTime();
	    waitTime();
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.createTestLink);
	    waitTime();
	    waitTime();
	    testCreationPageObject.selectTestBank(testBankName);
	    waitTime();
	    testCreationPageObject.waitForElementAndSendKeys(testCreationPageObject.contentCreateInputName, testName);
	    waitTime();
	    testCreationPageObject.waitForElementAndSendKeys(testCreationPageObject.contentCreateInputDescription, "Description");
	    waitTime();
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.createAndEditButton);
	    waitTime();
	    waitTime();
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.saveTestButton);
	    waitTime();
	    waitTime();
		Assert.assertEquals(testCreationPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("testNoChange").trim());
	    waitTime();
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.globalModalInfoOkButton);
	    waitTime();
	    waitTime();
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.testPrintButton);
	    waitTime();
	    waitTime();
		Assert.assertEquals(testCreationPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("testPrintWithOutAddItem").trim());
		testCreationPageObject.waitForElementAndClick(testCreationPageObject.globalModalInfoOkButton);
		waitTime();
		waitTime();
		testCreationPageObject.waitForElementAndClick(testCreationPageObject.homeLink);
	    waitTime();
	    waitTime();
	    testCreationPageObject.searchTest(testName);
	    waitTime();
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.copyIconList);
	    waitTime();
	    testCreationPageObject.copyTest(copyTestBankName ,copiedTestName);
	    waitTime();
	    waitTime();
	    Assert.assertEquals(testCreationPageObject.globalModalInfoTitle.getText().trim(), unitymessages.getProperty("testCopySuccess").trim());
		Assert.assertEquals(testCreationPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("testCreated").trim());
		testCreationPageObject.waitForElementAndClick(testCreationPageObject.globalModalInfoOkButton);
	    waitTime();
	    waitTime();
	    testCreationPageObject.searchTest(testName);
	    waitTime();
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.deleteIconList);
	    waitTime();
		Assert.assertEquals(testCreationPageObject.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("testDelete").trim());
		testCreationPageObject.waitForElementAndClick(testCreationPageObject.globalModalDeleteButton);
	    waitTime();
	    testCreationPageObject.deleteTest(copiedTestName);
	    testCreationPageObject.backToDashboard();
	    waitTime();
	    waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
	    waitTime();
		testBankPageObject.deleteTestBank(testBankName);
		waitTime();
		testBankPageObject.deleteTestBank(copyTestBankName);
	}

	

}
