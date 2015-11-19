package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Help;
import pages.Login;
import generic.BaseTest;

public class HelpTest extends BaseTest{
	Login loginPage;
	DashBoard dashBoardPage;
	Help helpPage;
	
	String defaultQaUser = "qa/admin";
	String defaultQaPassword = "password";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	Properties unitymessages;
	
	public HelpTest() {
		super();
		
	}

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		
	}
	
	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(defaultQaUser,
				defaultQaPassword);
		waitTime();
		//dashBoardPageObject.addTiles();
		waitTime();
	}
	
    @Test
	public void testHelpAlertMessage(){
    	helpPage = dashBoardPage.goToHelp();
    	waitTime();
    	waitTime();
    	helpPage.waitForElementAndClick(helpPage.exportHelpLink);
    	waitTime();
    	waitTime();
		Assert.assertEquals(helpPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("helpNoContent").trim());
    	helpPage.waitForElementAndClick(helpPage.globalModalInfoOkButton);

	}
	
}
