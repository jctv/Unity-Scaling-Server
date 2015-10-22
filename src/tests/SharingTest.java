package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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
	String itemBankName ;
	String itemName ;
	String itemDesc = "Auto Item";
	String itemBankDescription = "Auto desc";

	public SharingTest () {
		super();
		
	}
	
	@BeforeMethod
	public void setUp() {
		System.out.println("Load Unity url - " + url);
		driver.get(url);
		loginPageObject = new Login(driver);
		System.out.println("******** logging as Scool Admin -- " + schooladmin1  + "******** " );
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1, genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		
	}

	/**
	 * Test for checking the default Read access is disabled and check on permission screen for Read and also other availability of Other acl like Read ,admin ,Create etc.. 
	 * 
	 * 
	 */
	
	@Test(priority = 1)
	public void testVerifyDefaulltAclTrustee(){
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPageObject.createBank(itemBankName, itemBankDescription);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		waitTime();
		Assert.assertFalse(itemsBankPageObject.aclTrusteeRead.isEnabled());
		Assert.assertTrue(itemsBankPageObject.aclTrusteeRead.isSelected());
		Assert.assertFalse(itemsBankPageObject.aclTrusteeWrite.isSelected());
		Assert.assertTrue(itemsBankPageObject.aclTrusteeWrite.isDisplayed());
		Assert.assertFalse(itemsBankPageObject.aclTrusteeCreate.isSelected());
		Assert.assertTrue(itemsBankPageObject.aclTrusteeCreate.isDisplayed());
		Assert.assertFalse(itemsBankPageObject.aclTrusteeDelete.isSelected());
		Assert.assertTrue(itemsBankPageObject.aclTrusteeDelete.isDisplayed());
		Assert.assertFalse(itemsBankPageObject.aclTrusteeAdmin.isSelected());
		Assert.assertTrue(itemsBankPageObject.aclTrusteeAdmin.isDisplayed());
		itemsBankPageObject.closeItemBankShareScreen();
		itemsBankPageObject.backToDashboard();
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
	}
	
	/**
	 * Test for sharing the blank item bank by school admin with teacher after that  login  as a teacher and verifying the Item Bank shared with Read access
	 */
	
	@Test(priority = 2)
	public void testShareItemBankwithReadACL(){
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPageObject.createBank(itemBankName, itemBankDescription);
		waitTime();
		itemsPageObject = dashBoardPageObject.goToItems();
		waitTime();
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPageObject.createItem(itemName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "READ");
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
		itemsBankPageObject.searchItemBank(itemBankName);
		waitTime();
		Assert.assertFalse(itemsBankPageObject.shareButton.isEnabled());
		assertItemBankStatisticsPanelContent();
		itemsBankPageObject.backLink.click();
		waitTime();
		itemsPageObject = dashBoardPageObject.goToItems();
		itemsPageObject.searchItem(itemName);
		Assert.assertFalse(itemsPageObject.itemEditIcon.isEnabled());
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
	}
	
	/**
	 * Test for sharing the blank item bank by school admin with teacher with Write Access  after that  login  as a teacher and verifying the Item Bank shared with Write access. 
	 */
	
	@Test(priority = 3)
	public void testShareItemBankWithWriteACL(){
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPageObject.createBank(itemBankName, itemBankDescription);
		waitTime();
		itemsPageObject = dashBoardPageObject.goToItems();
		waitTime();
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPageObject.createItem(itemName);
		waitTime();
		
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "WRITE");
		waitTime();
		Assert.assertEquals(sharedTeacher, "n teacher1");
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE");
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
		itemsBankPageObject.searchItemBank(itemBankName);
		waitTime();
		Assert.assertFalse(itemsBankPageObject.shareButton.isEnabled());
		assertItemBankStatisticsPanelContent();
		waitTime();
		itemsPageObject = dashBoardPageObject.goToItems();
		itemsPageObject.searchItem(itemName);
		Assert.assertTrue(itemsPageObject.itemEditIcon.isEnabled());
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
	}
	
	
	/**
	 * Test for sharing the blank item bank by school admin with teacher with Create  Access  after that  login  as a teacher and verifying the Item Bank shared with Create access. 
	 */
	
	@Test(priority = 4)
	public void testShareItemBankwithCreateACL(){
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemBankName = "Auto_IB_ " + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item Bank creation ********");
		itemsBankPageObject.createBank(itemBankName, itemBankDescription);
		waitTime();
		itemsPageObject = dashBoardPageObject.goToItems();
		waitTime();
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPageObject.createItem(itemName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "CREATE");
		waitTime();
		Assert.assertEquals(sharedTeacher, "n teacher1");
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,CREATE");
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
		itemsBankPageObject.searchItemBank(itemBankName);
		waitTime();
		Assert.assertEquals(itemsBankPageObject.shareButton.getAttribute("disabled"),"true","Verifying the Item bank share icon is disabled");
		assertItemBankStatisticsPanelContent();
		itemsBankPageObject.backLink.click();
		waitTime();
		itemsPageObject = dashBoardPageObject.goToItems();
		itemsPageObject.searchItem(itemName);
		Assert.assertFalse(itemsPageObject.itemEditIcon.isEnabled());
		Assert.assertEquals(itemsPageObject.getSharedItemBank(itemBankName),itemBankName ,"Verifying Shared Item bank is available in Item bank drop down");
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
		
	}
	
	/**
	 * Test for sharing the blank item bank by school admin with teacher with Delete   Access  after that  login  as a teacher and verifying the Item Bank/item  shared with Delete  access. 
	 */
	@Test(priority = 5)
	public void testShareBlankItemBankwithDeleteACL(){
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemBankName = "Auto_IB_ " + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item Bank creation ********");
		itemsBankPageObject.createBank(itemBankName, itemBankDescription);
		waitTime();
		itemsPageObject = dashBoardPageObject.goToItems();
		waitTime();
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item  creation ********");
		itemsPageObject.createItem(itemName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "DELETE");
		waitTime();
		Assert.assertEquals(sharedTeacher, "n teacher1");
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,DELETE");
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
		itemsBankPageObject.searchItemBank(itemBankName);
		waitTime();
		Assert.assertEquals(itemsBankPageObject.shareButton.getAttribute("disabled"),"true","Verifying the Item bank share icon is disabled");
		assertItemBankStatisticsPanelContent();
		itemsBankPageObject.backLink.click();
		waitTime();
		itemsPageObject = dashBoardPageObject.goToItems();
		itemsPageObject.searchItem(itemName);
		Assert.assertFalse(itemsPageObject.itemEditIcon.isEnabled());
        Assert.assertTrue(itemsPageObject.itemDeleteIcon.isEnabled());
        dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
		
	}
	
	/**
	 * Test for sharing the blank item bank by school admin with teacher with Admin Access  after that  login  as a teacher and verifying the Item Bank/item  shared with admin  access. 
	 */
	@Test(priority = 6)
	public void testShareItemBankwithAdminACL(){
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemBankName = "Auto_IB_ " + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item  Bank  creation ********");
		itemsBankPageObject.createBank(itemBankName, itemBankDescription);
		waitTime();
		itemsPageObject = dashBoardPageObject.goToItems();
		waitTime();
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPageObject.createItem(itemName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "ADMIN");
		waitTime();
		Assert.assertEquals(sharedTeacher, "n teacher1");
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,ADMIN");
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
		itemsBankPageObject.searchItemBank(itemBankName);
		waitTime();
		Assert.assertTrue(itemsBankPageObject.shareButton.isEnabled(),"Verifying the Item Bank share icon is editable");
		assertItemBankStatisticsPanelContent();
		itemsBankPageObject.backLink.click();
		waitTime();
		itemsPageObject = dashBoardPageObject.goToItems();
		itemsPageObject.searchItem(itemName);
		Assert.assertTrue(itemsPageObject.itemEditIcon.isEnabled());
        Assert.assertFalse(itemsPageObject.itemDeleteIcon.isEnabled());
        dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);

	}
	
	
	private void assertItemBankStatisticsPanelContent(){
		itemsBankPageObject.viewIcon.click();
		Assert.assertTrue(itemsBankPageObject.itemBankStatisticsPanel.isDisplayed(),"Verifying the Item Bank statisticsPanel is expanded");
		Assert.assertEquals(itemsBankPageObject.itemCount.getText(),"1","Verifying the Item count");
		Assert.assertEquals(itemsBankPageObject.mediaCount.getText(),"0","Verifying the media count");
		Assert.assertEquals(itemsBankPageObject.passageCount.getText(),"0","Verifying the passage count");
		Assert.assertEquals(itemsBankPageObject.rubricCount.getText(),"0","Verifying the rubrics count");
		
		
	}
}
