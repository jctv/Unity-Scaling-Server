package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
	
	//String defaultQaUser = "qa/admin";
	//String defaultQaPassword = "password";
	
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
	
	String helpName;

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
    	helpName = "itemBank";
	}
	
    @Test(priority =1 )
	public void testHelpAlertMessage(){
    	helpPage.waitForElementAndClick(helpPage.addHelpLink);
    	helpPage.selectOption(helpPage.selectTile , 1);
    	helpPage.waitForElementAndClick(helpPage.backToHelp);
		Assert.assertEquals(helpPage.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("unSavedData").trim());
    	helpPage.waitForElementAndClick(helpPage.globalModalOKCancelSaveButton);
    	customeWaitTime(2);
    	helpPage.waitForElementAndClick(helpPage.exportHelpLink);
    	customeWaitTime(2);
		Assert.assertEquals(helpPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("helpNoContent").trim());
    	helpPage.waitForElementAndClick(helpPage.globalModalInfoOkButton);

	}
    
    @Test(priority = 2)
    public void testAddHelp(){
    	helpPage.addHelp("Item Import", "test hint", mediaFile, userGuideFile);
    	
    }
    
    
    @Test(priority = 3)
	public void testSearchHelp(){
		helpPage.waitForElementAndClick(helpPage.resetSearchFilter);
		waitTime();
		helpPage.searchHelp(helpName);
		Assert.assertEquals(helpPage.waitAndGetElementText(helpPage.searchAutoComplete), helpName);
	}
    
    
    
    @AfterClass
	public void cleanupData(){
    	helpPage.deleteHelp(helpName);
	}
	
}
