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
	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator;
	
	String mediaFile = "media.jpg";

	String mediaUploadFile = resourcesLocation + mediaFile;
	
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	Properties unitymessages;
	
	public MediaTest() {
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
		dashBoardPage = loginPage.loginSuccess(defaultUser,
				defaultPassword);
		waitTime();
		//dashBoardPageObject.addTiles();
		waitTime();
	}

    @Test
    public void testMediaAlertMessages(){
    	String itemBankName = "Media" + + System.currentTimeMillis();
    	itemsBankPage = dashBoardPage.goToItemsBank();
    	waitTime();
    	waitTime();
    	itemsBankPage.createBank(itemBankName, "Desc");
    	waitTime();
    	waitTime();
    	mediaPage = dashBoardPage.goToMedia();
    	waitTime();
    	waitTime();
    	mediaPage.waitForElementAndClick(mediaPage.uploadMediaLink);
    	waitTime();
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
