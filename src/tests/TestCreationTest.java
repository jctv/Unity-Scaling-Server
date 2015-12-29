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
		copyTestBankName = "Copy_" + testBankName;	
		testName = "T_" + testBankName;
	    copiedTestName = "copy_" + testName;
	    testBankPageObject = dashBoardPageObject.goToTestsBank();
	    customeWaitTime(2);
		testBankPageObject.createBank(testBankName, "Desc");
	    customeWaitTime(2);
		testBankPageObject.createBank(copyTestBankName, "Desc");
		/*returnToDashboard();
		customeWaitTime(5);
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		customeWaitTime(5);
		testBankPageObject.createBank(copyTestBankName, "Desc");
		customeWaitTime(5);
	    returnToDashboard();*/
		returnToDashboard();
		customeWaitTime(5);
	    testCreationPageObject = dashBoardPageObject.goToTestCreation();
	    customeWaitTime(5);
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.createTestLink);
	    customeWaitTime(5);
	    if(testCreationPageObject.bankDropDown.isDisplayed()){
	    	testCreationPageObject.selectOption(testCreationPageObject.bankDropDown, testBankName);
	    }else{
		    testCreationPageObject.selectTestBank(testBankName);
	    }
	    customeWaitTime(2);
	    testCreationPageObject.waitForElementAndSendKeys(testCreationPageObject.contentCreateInputName, testName);
	    customeWaitTime(2);
	    testCreationPageObject.waitForElementAndSendKeys(testCreationPageObject.contentCreateInputDescription, "Description");
	    customeWaitTime(2);
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.createAndEditButton);
	    customeWaitTime(5);
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.saveTestButton);
	    customeWaitTime(5);
		Assert.assertEquals(testCreationPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("testNoChange").trim());
	    customeWaitTime(2);
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.globalModalInfoOkButton);
	    customeWaitTime(5);
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.testPrintButton);
	    customeWaitTime(5);
		Assert.assertEquals(testCreationPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("testPrintWithOutAddItem").trim());
		testCreationPageObject.waitForElementAndClick(testCreationPageObject.globalModalInfoOkButton);
	    customeWaitTime(5);
		testCreationPageObject.waitForElementAndClick(testCreationPageObject.homeLink);
	    customeWaitTime(5);
	    testCreationPageObject.searchTest(testName);
	    customeWaitTime(2);
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.copyIconList);
	    customeWaitTime(2);
	    testCreationPageObject.copyTest(copyTestBankName ,copiedTestName);
	    customeWaitTime(5);
	    Assert.assertEquals(testCreationPageObject.globalModalInfoTitle.getText().trim(), unitymessages.getProperty("testCopySuccess").trim());
		Assert.assertEquals(testCreationPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("testCreated").trim());
		testCreationPageObject.waitForElementAndClick(testCreationPageObject.globalModalInfoOkButton);
	    customeWaitTime(5);
	    testCreationPageObject.searchTest(testName);
	    customeWaitTime(2);
	    testCreationPageObject.waitForElementAndClick(testCreationPageObject.deleteIconList);
	    customeWaitTime(2);
		Assert.assertEquals(testCreationPageObject.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("testDelete").trim());
		testCreationPageObject.waitForElementAndClick(testCreationPageObject.globalModalDeleteButton);
	    customeWaitTime(2);
	    testCreationPageObject.deleteTest(copiedTestName);
	    testCreationPageObject.backToDashboard();
	    customeWaitTime(2);
		testBankPageObject = dashBoardPageObject.goToTestsBank();
	    customeWaitTime(2);
		testBankPageObject.deleteTestBank(testBankName);
	    customeWaitTime(2);
		testBankPageObject.deleteTestBank(copyTestBankName);
	}

	

}
