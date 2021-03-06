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
import pages.Passage;
import generic.BaseTest;

public class PassageTest extends BaseTest {

	Login loginPage;
	DashBoard dashBoardPage;
	Passage passagePage;
	ItemsBank itemsBankPage;

	String loggedUser = "tcamilo";
	String genericPassword = "12345";

	String mediaFileName = "media.jpg";
	String resources = "src" + File.separator + "resources" + File.separator;
	String mediaFileLocation = resources + mediaFileName;
	String itemBankName;
	String passageName;

	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";

	Properties unitymessages;

	@BeforeTest
	public void loadUnityMessagesProperty() {
		unitymessages = getUnityMessagesProperty(unityMessageFile);

	}

	public PassageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		System.out.println("Load Unity url - " + url);
		driver.get(url);
		loginPage = new Login(driver);
		System.out.println("******** logging as System Admin -- " + loggedUser
				+ "******** ");
		dashBoardPage = loginPage.loginSuccess(loggedUser, genericPassword);
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
		passagePage = dashBoardPage.goToPassage();
	}

	@Test(priority = 1)
	public void testPassageAlertsMessages() {
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(20);
		itemBankName = "Passage_IB_" + System.currentTimeMillis();
		passageName = "p_" + itemBankName;
		itemsBankPage.createBank(itemBankName, "Desc");
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		passagePage = dashBoardPage.goToPassage();
		customeWaitTime(20);
		passagePage.createPassage(itemBankName, passageName, "desc",
				mediaFileLocation);
		customeWaitTime(5);
		passagePage.waitForElementAndClick(passagePage.passageSaveButton);
		customeWaitTime(5);
		Assert.assertEquals(passagePage.globalModalInfoBody.getText().trim(),
				unitymessages.getProperty("passageSave").trim());
		passagePage.waitForElementAndClick(passagePage.globalModalInfoOkButton);
		customeWaitTime(5);
		passagePage.searchPassage(passageName);
		
		passagePage.waitForElementAndClick(passagePage.editIconList);

		passagePage.waitForElementAndClick(passagePage.firstImage);
		
		passagePage.waitForElementAndClick(passagePage.backToPassage);
		
		Assert.assertEquals(passagePage.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("unSavedData").trim());
		passagePage.waitForElementAndClick(passagePage.globalModalOKCancelSaveButton);
    	customeWaitTime(2);
		passagePage.waitForElementAndClick(passagePage.deleteIconList);
		customeWaitTime(5);
		Assert.assertEquals(passagePage.globalModalDeleteBody.getText().trim(),
				unitymessages.getProperty("passageDelete").trim());

	}

	@Test(priority = 2)
	public void testCreatePassage() {
		
	softAssert.assertTrue(passagePage.createPassage("My Items", "passage Automation", "Automated passage", ""), "Passage created succesfully");

	}
}
