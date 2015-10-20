package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import generic.BaseTest;
import pages.ClassRoster;
import pages.DashBoard;
import pages.Delivery;
import pages.HandScoring;
import pages.Items;
import pages.ItemsBank;
import pages.Login;
import pages.Organization;
import pages.Reports;
import pages.Schedule;
import pages.TestCreation;
import pages.TestsBank;
import pages.Users;

public class SharingTest extends BaseTest {
	 String schooladmin1 = "qa/nadmin";
	 String teacher1 = "qa/nteacher1";
	 String genericPassword = "12345";
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	Items itemsPageObject;
	Users usersPageObject;
	ClassRoster classRosterPageObject;
	TestCreation testCreationPageObject;
	Schedule sechedulePageObject;
	Delivery deliveryPageObject;
	HandScoring handScoringPageObject;
	Reports reportsPageObject;
	Organization organizationPageObject;
	ItemsBank itemsBankPageObject;
	TestsBank testBankPageObject;
	
	String itemBankName = "Automation " + System.currentTimeMillis();

	String itemBankDescription = "Automation Description";

	public SharingTest () {
		super();
		
	}
	
	@BeforeMethod
	public void setUp() {
		System.out.println("Load Unity url - " + url);
		driver.get(url);
		loginPageObject = new Login(driver);
		System.out.println("******** logging as Teacher ********");
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1, genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		System.out.println("******** Item bank creation ********");
		itemsBankPageObject.createBank(itemBankName, itemBankDescription);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
	}

	/**
	 * Test for checking the default Read access is disabled and check on permission screen for Read and also other availability of Other acl like Read ,admin ,Create etc.. 
	 * 
	 */
	@Test
	public void testVerifyDefaulltAclTrustee(){
		itemsBankPageObject.openItemBankShareScreen();
		waitTime();
		Assert.assertFalse(itemsBankPageObject.aclTrusteeRead.isEnabled());
		Assert.assertTrue(itemsBankPageObject.aclTrusteeRead.isSelected());
		Assert.assertFalse(itemsBankPageObject.aclTrusteeWrite.isSelected());
		Assert.assertTrue(itemsBankPageObject.aclTrusteeWrite.isDisplayed());
		Assert.assertFalse(itemsBankPageObject.aclTrusteeCREATE.isSelected());
		Assert.assertTrue(itemsBankPageObject.aclTrusteeCREATE.isDisplayed());
		Assert.assertFalse(itemsBankPageObject.aclTrusteeDELETE.isSelected());
		Assert.assertTrue(itemsBankPageObject.aclTrusteeDELETE.isDisplayed());
		Assert.assertFalse(itemsBankPageObject.aclTrusteeAdmin.isSelected());
		Assert.assertTrue(itemsBankPageObject.aclTrusteeAdmin.isDisplayed());
		itemsBankPageObject.closeItemBankShareScreen();
	}
	
	/**
	 * Test for sharing the blank item bank by school admin with teacher after that  login  as a teacher and verifying the Item Bank shared with Read access
	 */
	
	@Test
	public void testShareBlankItemBankwithReadACL(){
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBankWithTeacher("nteacher1");
		waitTime();
		Assert.assertEquals(sharedTeacher, "n teacher1");
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ");
		itemsBankPageObject.closeItemBankShareScreen();
		waitTime();
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(teacher1,
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		Assert.assertEquals(itemsBankPageObject.shareButton.getAttribute("disabled"),"true","Verifying the Item bank share icon is disabled");
		itemsBankPageObject.ViewIcon.click();
		Assert.assertTrue(itemsBankPageObject.itemBankStatisticsPanel.isDisplayed(),"Verifying the Item Bank statisticsPanel is expanded");
		Assert.assertEquals(itemsBankPageObject.itemCount.getText(),"0","Verifying the Item count");
		Assert.assertEquals(itemsBankPageObject.mediaCount.getText(),"0","Verifying the media count");
		Assert.assertEquals(itemsBankPageObject.passageCount.getText(),"0","Verifying the passage count");
		Assert.assertEquals(itemsBankPageObject.rubricCount.getText(),"0","Verifying the rubrics count");

		
		
	}
	
}
