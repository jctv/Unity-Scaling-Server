package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.DashBoard;
import pages.Help;
import pages.ItemImport;
import pages.Login;
import pages.SisImport;
import generic.BaseTest;

public class HelpTest extends BaseTest{
	
	Login loginPage;
	DashBoard dashBoardPage;
	Help helpPage;
	ItemImport itemsImportPage;
	SisImport sisImportPage;

	Properties unitymessages;
	Properties unitytestdata;
	Properties unityhelpdata;
	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator;
	
	String help = "help" + File.separator;
	
	String unityMessageFile = resourcesLocation + "unitymessages.properties";
	String unityTestDataFile = resourcesLocation  + "unitytestdata.properties";
	String unityHelpDataFile = resourcesLocation + "unityhelpdata.properties";
	
	String mediaFile ;
	String userGuideFile;
	
	String uploadMedia;
	String uploadUserGuide;

	String helpName;
	String helpImport;
	
	String helpHint;
	
	String importHelpFile;
	
	String importDashbardHelp;
	
	String importHelpFileLocation;
	
	String importDashboardHelpFile;

	String updateHelpHint;
	
	String importDashboardHelpHint;
	long timestamp = System.currentTimeMillis();
	
	public HelpTest() {
		super();
		
	}

   private SoftAssert softAssert = new SoftAssert();

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		unityhelpdata = getUnityMessagesProperty(unityHelpDataFile);
		
		mediaFile =  unityhelpdata.getProperty("uploadTestVideo");
		userGuideFile =  unityhelpdata.getProperty("uploadUserGuide");
		
		helpImport = unityhelpdata.getProperty("helpImport");
		helpName  =  unityhelpdata.getProperty("helpName");
		helpHint = unityhelpdata.getProperty("helpHint");
		
		importDashboardHelpHint = unityhelpdata.getProperty("importDashboardHelphint");
		
		updateHelpHint = timestamp + "_"+unityhelpdata.getProperty("updatedhelphint");
		
		importHelpFile = unityhelpdata.getProperty("importHelpFile");
		
		importDashbardHelp = unityhelpdata.getProperty("importDashboardHelpFile");
		
		
		uploadMedia = resourcesLocation + help + mediaFile;
		uploadUserGuide = resourcesLocation + help + userGuideFile;
		importHelpFileLocation =  resourcesLocation + help + importHelpFile;
		
		importDashboardHelpFile = resourcesLocation + help + importDashbardHelp;

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
		
	    /*helpPage.importHelp(helpImport, importHelpFileLocation);
    	helpPage.waitForElementAndClick(helpPage.backbutton);
    	helpPage.backToDashboard();*/
	}
	
	
	 @Test(priority = 1)
	    public void testVerifyHelpAddedForTile(){
		    waitTime();
	    	helpPage.addHelp(helpName, helpHint, uploadMedia, uploadUserGuide);
	    	helpPage.waitForElementAndClick(helpPage.backbutton);
	    	customeWaitTime(2);
	    	helpPage.backToDashboard();
		    itemsImportPage = dashBoardPage.goToItemImport();
		    itemsImportPage.waitForElementAndClick(itemsImportPage.helpNav);
	    	customeWaitTime(2);
	    	softAssert.assertEquals(itemsImportPage.waitAndGetElementAttribute(itemsImportPage.helpUserGuide, "download"), userGuideFile);
	    	softAssert.assertEquals(itemsImportPage.waitAndGetElementText(itemsImportPage.helpHintText), helpHint);
	    	softAssert.assertTrue(itemsImportPage.helpUserGuide.isDisplayed());
	    	itemsImportPage.waitForElementAndClick(itemsImportPage.globalModalInfoOkButton);
	    	itemsImportPage.backToDashboard();
	    	
	    }
	 
	  @Test(priority = 2)
	    public void testVerifyHelpImportedForTile(){
		    waitTime();
			helpPage = dashBoardPage.goToHelp();
		    helpPage.importHelp(helpImport, importHelpFileLocation);
	    	helpPage.waitForElementAndClick(helpPage.backbutton);
	    	helpPage.backToDashboard();
		    sisImportPage = dashBoardPage.goToSisImport(); 
		    sisImportPage.waitForElementAndClick(sisImportPage.helpNav);
		    customeWaitTime(2);
	    	softAssert.assertEquals(itemsImportPage.waitAndGetElementAttribute(itemsImportPage.helpUserGuide, "download"), userGuideFile);
	    	softAssert.assertEquals(itemsImportPage.waitAndGetElementText(itemsImportPage.helpHintText), helpHint);
	    	softAssert.assertTrue(itemsImportPage.helpUserGuide.isDisplayed());
	    	itemsImportPage.waitForElementAndClick(itemsImportPage.globalModalInfoOkButton);
	    	itemsImportPage.backToDashboard();
	    	
		   /* helpPage.importHelp(helpImport, importHelpFileLocation);
	    	helpPage.waitForElementAndClick(helpPage.backbutton);
	    	helpPage.addHelp(helpName, helpHint, uploadMedia, uploadUserGuide);*/
	    }
	 
	 
	@Test(priority = 3)
	public void testSearchHelp(){
		    waitTime();
		    helpPage = dashBoardPage.goToHelp();
			helpPage.waitForElementAndClick(helpPage.resetSearchFilter);
			waitTime();
			helpPage.searchHelp(helpName);
			softAssert.assertEquals(helpPage.waitAndGetElementText(helpPage.searchAutoComplete), helpName);
	}
	 
    @Test(priority = 4)
	public void testHelpAlertMessage(){
    	helpPage.waitForElementAndClick(helpPage.addHelpLink);
    	helpPage.selectOption(helpPage.selectTile , 1);
    	helpPage.waitForElementAndClick(helpPage.backbutton);
    	customeWaitTime(2);
    	softAssert.assertEquals(helpPage.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("unSavedData").trim());
    	helpPage.waitForElementAndClick(helpPage.globalModalOKCancelSaveButton);
    	customeWaitTime(2);
    	/*customeWaitTime(2);
    	helpPage.waitForElementAndClick(helpPage.exportHelpLink);
    	customeWaitTime(2);
		Assert.assertEquals(helpPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("helpNoContent").trim());
    	helpPage.waitForElementAndClick(helpPage.globalModalInfoOkButton);
        */
	}
    
    @Test(priority = 5)
    public void testUpdateHelpContentFromUI(){
    	helpPage.searchHelp("dashboard");
    	//helpPage.waitForElementAndClick(helpPage.backbutton);
    	helpPage.updateHelpHint("dashboard", updateHelpHint);
    	dashBoardPage = helpPage.backToDashboard();
    	customeWaitTime(5);
    	dashBoardPage.waitForElementAndClick(helpPage.helpNav);
    	softAssert.assertEquals(dashBoardPage.waitAndGetElementText(helpPage.helpHint1Content), updateHelpHint);
    	helpPage.waitForElementAndClick(dashBoardPage.globalModalInfoOkButton);
    }
    
    
    @Test(priority = 6)
    public void testUpdateHelpContentThroughImport(){
    	    waitTime();
			helpPage = dashBoardPage.goToHelp();
			customeWaitTime(5);
			helpPage.updateHelpHint("dashboard", updateHelpHint);
			customeWaitTime(2);
			helpPage.waitForElementAndClick(helpPage.backbutton);
			customeWaitTime(2);
		    helpPage.importHelp("dashboard", importDashboardHelpFile);
		    customeWaitTime(5);
		    helpPage.backToDashboard();
		    dashBoardPage.waitForElementAndClick(helpPage.helpNav);
	    	softAssert.assertEquals(dashBoardPage.waitAndGetElementText(helpPage.helpHint1Content), importDashboardHelpHint);
	    	helpPage.waitForElementAndClick(dashBoardPage.globalModalInfoOkButton);
    	
    }
    @AfterClass
	public void cleanupData(){
    	helpPage.deleteHelp(helpName);
    	helpPage.deleteHelp(helpImport);
	}
	
}
