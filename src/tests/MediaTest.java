package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.ItemsBank;
import pages.Login;
import pages.Media;
import generic.BaseTest;

public class MediaTest extends BaseTest {
	Login loginPage;
	DashBoard dashBoardPage;
	ItemsBank itemsBankPage;
	Media mediaPage;
	
	String defaultUser = "admin";
	String defaultPassword = "@simple1";
	
	String itemBankName;
	
	long timeStamp;
	
	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator + "media" + File.separator ;
	
	String unityTestDataFile = "src" + File.separator + "resources"
			+ File.separator + "unitytestdata.properties";
	
	String mediaFile = "media.jpg";

	String mediaUploadFile = resourcesLocation + mediaFile;
	
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	Properties unitymessages;
	Properties unitytestdata;
	
	
	
	public MediaTest() {
		super();
		
	}
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);;
	}
	
	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
		timeStamp =  System.currentTimeMillis();
		itemBankName = "Media" + timeStamp; 
    	itemsBankPage = dashBoardPage.goToItemsBank();
    	waitTime();
    	itemsBankPage.createBank(itemBankName, "Desc");
    	waitTime();
    	returnToDashboard();
		customeWaitTime(5);
    	mediaPage = dashBoardPage.goToMedia();
	}
	
	
	
	@Test(priority = 1)
	public void testuploadMedia(){
		mediaPage.uploadMedia(mediaUploadFile, itemBankName);
		mediaPage.searchMedia(mediaFile);
		customeWaitTime(2);
		
		mediaPage.deleteMedia(mediaFile);
	}
	
    @Test(enabled = false )
    public void testMediaAlertMessages(){
    	mediaPage.waitForElementAndClick(mediaPage.uploadMediaLink);
    	waitTime();
    	mediaPage.waitForElementAndClick(mediaPage.cancelUploadButton);
    	waitTime();
    	waitTime();
		Assert.assertEquals(mediaPage.fileUploadStatus.getText().trim(), unitymessages.getProperty("cancelUploadMedia").trim());
		waitTime();
		waitTime();
		mediaPage.uploadMedia(mediaUploadFile, itemBankName);
		waitTime();
		waitTime();
		Assert.assertEquals(mediaPage.fileUploadStatus.getText().trim(), unitymessages.getProperty("uploadMedia").trim());
		mediaPage.refreshPage();
		waitTime();
		waitTime();
		mediaPage.deleteMedia(mediaFile);
		Assert.assertEquals(mediaPage.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("deleteMedia").trim());
    	mediaPage.waitForElementAndClick(mediaPage.globalModalDeleteButton);
    	mediaPage.waitForElementAndClick(mediaPage.backToDashboard);
    	waitTime();
		waitTime();
    	itemsBankPage = dashBoardPage.goToItemsBank();
    	waitTime();
		waitTime();
    	itemsBankPage.deleteItemBank(itemBankName);

    }
	
}
