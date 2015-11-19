package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Login;
import pages.TestsBank;
import generic.BaseTest;

public class TestBankTest extends BaseTest {
	
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	TestsBank testBankPageObject;
	
	String defaultUser = "admin";
	String defaultPassword = "@simple1";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	Properties unitymessages;
	
	public TestBankTest() {
		super();
		
	}

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		
	}
	
	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPageObject = new Login(driver);
		dashBoardPageObject = loginPageObject.loginSuccess(defaultUser,
				defaultPassword);
		waitTime();
		//dashBoardPageObject.addTiles();
		waitTime();
	}


	@Test
	public void testBankAlertMessages(){
		String testBankName = "Auto_TB_" + + System.currentTimeMillis();
		String description = "Desc" ;
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		waitTime();
		testBankPageObject.createBank(testBankName, description);
		waitTime();
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);
		waitTime();
		testBankPageObject.waitForElementAndClick(testBankPageObject.deleteIconList);
		waitTime();
		waitTime();
		Assert.assertEquals(testBankPageObject.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("testBankDelete").trim());
		testBankPageObject.waitForElementAndClick(testBankPageObject.globalModalDeleteButton);
		waitTime();
		
	}
}
