package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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
	
	String unityTestDataFile = "src" + File.separator + "resources"
			+ File.separator + "unitytestdata.properties";
	
	Properties unitymessages;
	Properties unitytestdata;
	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator;
	
	String mediaFile = resourcesLocation + "tutorial-test.mp4";
	String userGuideFile = resourcesLocation + "Import an SIS File.pdf";

	public HelpTest() {
		super();
		
	}

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);;
	}
	
	@BeforeClass
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		dashBoardPage.addTiles();
		waitTime();
		helpPage = dashBoardPage.goToHelp();
    	waitTime();
    	waitTime();
	}
	
    @Test(priority =1 )
	public void testHelpAlertMessage(){
    	
    	helpPage.waitForElementAndClick(helpPage.exportHelpLink);
    	waitTime();
    	waitTime();
		Assert.assertEquals(helpPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("helpNoContent").trim());
    	helpPage.waitForElementAndClick(helpPage.globalModalInfoOkButton);

	}
    
    @Test(priority = 0)
    public void testAddHelp(){
    	helpPage.addHelp("Item Import", "test hint", mediaFile, userGuideFile);
    	
    }
	
}
