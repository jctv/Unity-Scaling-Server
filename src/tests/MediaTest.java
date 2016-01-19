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
import pages.ItemsBank;
import pages.Login;
import pages.Media;
import pages.Passage;
import generic.BaseTest;

public class MediaTest extends BaseTest {
	Login loginPage;
	DashBoard dashBoardPage;
	ItemsBank itemsBankPage;
	Media mediaPage;
	Passage passagePage;
	
	Properties unitymessages;
	Properties unitytestdata;
	Properties unitymediadata;
	
	String itemBankName;
	String passageName;
	long timeStamp;
	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator + "media" + File.separator ;
	
	String unityTestDataFile = "src" + File.separator + "resources"
			+ File.separator + "unitytestdata.properties";
	
	String unityMediaDataFile = "src" + File.separator + "resources"
			+ File.separator + "unitymediadata.properties";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	String mediaJpgFile;;
	String mediaGifFile;
	String mediaPngFile;
	String mediaVideoFile;
	String mediapassageFile;
	String defaultRefObjcet;
	String passageRefObject;

	String mediaJpgUploadFile;
	
	String mediaGifUploadFile;

	String mediaPngUploadFile;

	String mediaVideoUploadFile;

	String mediaPassageUploadFile;
	
	public MediaTest() {
		super();
		
	}
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		unitymediadata = getUnityMessagesProperty(unityMediaDataFile);
		
		mediaJpgFile = unitymediadata.getProperty("mediaJpgFile");
		mediaGifFile = unitymediadata.getProperty("mediaGifFile");
		mediaPngFile = unitymediadata.getProperty("mediaPngFile");
		mediaVideoFile = unitymediadata.getProperty("mediaVideoFile");
		mediapassageFile = unitymediadata.getProperty("mediapassageFile");

		
		defaultRefObjcet = unitymediadata.getProperty("defaultRefObject");
		
		passageRefObject = unitymediadata.getProperty("passageRefObject");

		
		mediaJpgUploadFile = resourcesLocation + mediaJpgFile;
		mediaGifUploadFile = resourcesLocation + mediaGifFile;
		mediaPngUploadFile = resourcesLocation + mediaPngFile;
		mediaVideoUploadFile = resourcesLocation + mediaVideoFile;
		
		mediaPassageUploadFile = resourcesLocation + mediapassageFile;
		
	}
	
	@BeforeClass
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("teacher1"),
				unitytestdata.getProperty("genericPassword"));
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
		customeWaitTime(2);
    	mediaPage = dashBoardPage.goToMedia();
    	mediaPage.refreshPage();
    	customeWaitTime(10);
	}
	
	/**
	 * Loginas a Techer
	 * GO to the media tile
	 * Upload the jpg file and verify the image in listing  and preview mode
	 * Upload the gif file and verify the image in listing  and preview mode
	 * Upload the png file and verify the image in listing  and preview mode
	 * Upload the mp4 video file and verify the image in listing  and preview mode
	 * 
	 */
	@Test(priority = 1)
	public void testUploadDifferentTypeOfMedia(){
		mediaPage.uploadMedia(mediaJpgUploadFile, itemBankName);
		mediaPage.filterMediaByItemBank(mediaJpgFile, itemBankName);
		mediaPage.searchMedia(mediaJpgFile);
		customeWaitTime(2);
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaNameInListing), mediaJpgFile);
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mimeTypeInListing), "image/jpeg");
		Assert.assertFalse(mediaPage.waitAndGetElementText(mediaPage.mediaLengthInListing).isEmpty());
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaRefTypeInListing), defaultRefObjcet);  
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaItemBankNameInListing), itemBankName); 
		mediaPage.waitForElementAndClick(mediaPage.previewIconList);
		customeWaitTime(2);
		Assert.assertTrue(mediaPage.previewImageTumbnail.isDisplayed());
		mediaPage.waitForElementAndClick(mediaPage.previewIconList);
		customeWaitTime(2);
		mediaPage.deleteMedia(mediaJpgFile);
		mediaPage.waitForElementAndClick(mediaPage.resetSearchFilter);
		customeWaitTime(2);
		
		mediaPage.uploadMedia(mediaGifUploadFile, itemBankName);
		mediaPage.filterMediaByItemBank(mediaGifFile, itemBankName);
		mediaPage.searchMedia(mediaGifFile);
		customeWaitTime(2);
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaNameInListing), mediaGifFile);
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mimeTypeInListing), "image/gif");
		Assert.assertFalse(mediaPage.waitAndGetElementText(mediaPage.mediaLengthInListing).isEmpty());
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaRefTypeInListing), defaultRefObjcet);  
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaItemBankNameInListing), itemBankName);  
		mediaPage.waitForElementAndClick(mediaPage.previewIconList);
		customeWaitTime(2);
		Assert.assertTrue(mediaPage.previewImageTumbnail.isDisplayed());
		mediaPage.waitForElementAndClick(mediaPage.previewIconList);
		customeWaitTime(2);
		mediaPage.deleteMedia(mediaGifFile);
		mediaPage.waitForElementAndClick(mediaPage.resetSearchFilter);
		customeWaitTime(2);
		
		mediaPage.uploadMedia(mediaPngUploadFile, itemBankName);
		mediaPage.filterMediaByItemBank(mediaPngFile, itemBankName);
		mediaPage.searchMedia(mediaPngFile);
		customeWaitTime(2);
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaNameInListing), mediaPngFile);
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mimeTypeInListing), "image/png");
		Assert.assertFalse(mediaPage.waitAndGetElementText(mediaPage.mediaLengthInListing).isEmpty());
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaRefTypeInListing), defaultRefObjcet);  
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaItemBankNameInListing), itemBankName);  
		mediaPage.waitForElementAndClick(mediaPage.previewIconList);
		customeWaitTime(2);
		Assert.assertTrue(mediaPage.previewImageTumbnail.isDisplayed());
		mediaPage.waitForElementAndClick(mediaPage.previewIconList);
		customeWaitTime(2);
		mediaPage.deleteMedia(mediaPngFile);
		mediaPage.waitForElementAndClick(mediaPage.resetSearchFilter);
		customeWaitTime(2);
		
		mediaPage.uploadMedia(mediaVideoUploadFile, itemBankName);
		mediaPage.filterMediaByItemBank(mediaVideoFile, itemBankName);
		mediaPage.searchMedia(mediaVideoFile);
		customeWaitTime(2);
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaNameInListing), mediaVideoFile);
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mimeTypeInListing), "video/mp4");
		Assert.assertFalse(mediaPage.waitAndGetElementText(mediaPage.mediaLengthInListing).isEmpty());
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaRefTypeInListing), defaultRefObjcet);  
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaItemBankNameInListing), itemBankName);  
		mediaPage.waitForElementAndClick(mediaPage.previewIconList);
		customeWaitTime(2);
		Assert.assertTrue(mediaPage.previewVideoTumbnail.isDisplayed());
		mediaPage.waitForElementAndClick(mediaPage.previewIconList);
		customeWaitTime(2);
		mediaPage.deleteMedia(mediaVideoFile);
	}
	
 
    /**
	 * Login  as a teacher.
	 * create passage with media
	 * go to the media tile
	 * Search for passage media
	 * Verify media content in listing
	 * Delete the media
	 * 
	 */
	@Test(priority= 2)
	public void testUploadPassageMedia(){
		returnToDashboard();
		customeWaitTime(2);
		passagePage = dashBoardPage.goToPassage();
		customeWaitTime(2);
		passageName = "passage" + timeStamp;
		passagePage.createPassage(itemBankName, passageName, "desc", mediaPassageUploadFile);
		customeWaitTime(2);
		passagePage.waitForElementAndClick(passagePage.passageSaveButton);
		customeWaitTime(2);
		passagePage.waitForElementAndClick(passagePage.globalModalInfoOkButton);
		customeWaitTime(2);
		returnToDashboard();
		customeWaitTime(2);
    	mediaPage = dashBoardPage.goToMedia();
    	mediaPage.refreshPage();
    	customeWaitTime(10);
		
		//mediaPage.uploadMedia(mediaJpgUploadFile, itemBankName);
		mediaPage.filterMediaByItemBank(mediapassageFile, itemBankName);
		mediaPage.searchMedia(mediapassageFile);
		customeWaitTime(2);
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaNameInListing), mediapassageFile);
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mimeTypeInListing), "image/jpeg");
		Assert.assertFalse(mediaPage.waitAndGetElementText(mediaPage.mediaLengthInListing).isEmpty());
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaRefTypeInListing), passageRefObject);  
		Assert.assertEquals(mediaPage.waitAndGetElementText(mediaPage.mediaItemBankNameInListing), itemBankName); 
		mediaPage.waitForElementAndClick(mediaPage.previewIconList);
		customeWaitTime(2);
		Assert.assertTrue(mediaPage.previewImageTumbnail.isDisplayed());
		mediaPage.waitForElementAndClick(mediaPage.previewIconList);
		customeWaitTime(2);
		mediaPage.deleteMedia(mediapassageFile);
		customeWaitTime(2);
		mediaPage.waitForElementAndClick(mediaPage.resetSearchFilter);
		customeWaitTime(2);
	}
	
	    @Test(enabled = false)
	    public void testMediaAlertMessages(){
	    	mediaPage.waitForElementAndClick(mediaPage.uploadMediaLink);
	    	waitTime();
	    	mediaPage.waitForElementAndClick(mediaPage.cancelUploadButton);
	    	waitTime();
			Assert.assertEquals(mediaPage.fileUploadStatus.getText().trim(), unitymessages.getProperty("cancelUploadMedia").trim());
			waitTime();
			mediaPage.uploadMedia(mediaJpgUploadFile, itemBankName);
			waitTime();
			Assert.assertEquals(mediaPage.fileUploadStatus.getText().trim(), unitymessages.getProperty("uploadMedia").trim());
			mediaPage.refreshPage();
			waitTime();
			mediaPage.deleteMedia(mediaJpgFile);
			Assert.assertEquals(mediaPage.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("deleteMedia").trim());
	    	mediaPage.waitForElementAndClick(mediaPage.globalModalDeleteButton);
	    	/*mediaPage.waitForElementAndClick(mediaPage.backToDashboard);
	    	waitTime();
	    	itemsBankPage = dashBoardPage.goToItemsBank();
	    	waitTime();
	    	itemsBankPage.deleteItemBank(itemBankName);
*/
	    }
	    
    @AfterClass
    public void cleanUp(){
    	returnToDashboard();
    	itemsBankPage = dashBoardPage.goToItemsBank();
    	waitTime();
    	itemsBankPage.deleteItemBank(itemBankName);
    	waitTime();
    }
	
}
