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
	String stduent1 = "qa/nstudent1";
	String genericPassword = "12345";
	String lastSaharedTeacher= teacher1.split("/")[1].substring(0, 1) + " " +teacher1.split("/")[1].substring(1);
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
	String itemNameByTeacher;
	String testNameByTeacher;
	String itemDesc = "Auto Item";
	String itemBankDescription = "Auto desc";
	
	String testBankName ;
	String testName ;
	String testDesc = "Auto Test";
	String testBankDescription = "Auto test bank desc";
	

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
	public void testVerifyDefaulltAclTrusteeForItemBank(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "READ");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "WRITE");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "CREATE");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "DELETE");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "ADMIN");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
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
	
	
	/**
	 * Test for sharing the blank item bank by school admin with teacher with all  permission  after that  login  as a teacher and verifying the Item Bank/item  shared with admin  access. 
	 */
	@Test(priority = 7)
	public void testShareItemBankwithAllACL(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "READ,WRITE,CREATE,DELETE,ADMIN");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
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
        Assert.assertTrue(itemsPageObject.itemDeleteIcon.isEnabled());
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
	 * School admin create Item bank with one item 
	 * Share Item Item bank with Teacher
	 * Teacher create item in Shared item bank by school admin 
	 * School admin checks added item in shared 
	 */
	@Test(priority = 8)
	public void testAddItemsInSharedItemBankwithAllACL(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "READ,WRITE,CREATE,DELETE,ADMIN");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
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
        Assert.assertTrue(itemsPageObject.itemDeleteIcon.isEnabled());
		Assert.assertEquals(itemsPageObject.getSharedItemBank(itemBankName),itemBankName ,"Verifying Shared Item bank is available in Item bank drop down");
		itemNameByTeacher = "I1_"  + itemBankName;
		
		itemsPageObject.createItem(itemNameByTeacher , itemBankName);
		waitTime();
        dashBoardPageObject.logOut();	
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		itemsPageObject = dashBoardPageObject.goToItems();
		waitTime();
		itemsPageObject.searchItem(itemNameByTeacher);
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		Assert.assertEquals(itemsBankPageObject.itemCount.getText(),"2","Verifying the Item count after adding item in shared bank");
		itemsBankPageObject.viewIcon.click();
		itemsBankPageObject.deleteItemBank(itemBankName);

	}
	
	
	/**
	 * School admin create Item bank with one item 
	 * Share Item Item bank with Teacher
	 * Teacher delete item in Shared item bank by school admin 
	 * School admin checks item is deleted  
	 */
	@Test(priority = 9)
	public void testDeleteItemsInSharedItemBankwithAllACL(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "READ,WRITE,CREATE,DELETE,ADMIN");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
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
        Assert.assertTrue(itemsPageObject.itemDeleteIcon.isEnabled());
		Assert.assertEquals(itemsPageObject.getSharedItemBank(itemBankName),itemBankName ,"Verifying Shared Item bank is available in Item bank drop down");
		itemsPageObject.deleteItem(itemName);
        dashBoardPageObject.logOut();	
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		itemsPageObject = dashBoardPageObject.goToItems();
		waitTime();
		itemsPageObject.searchItem(itemName);
		Assert.assertEquals(itemsPageObject.itemResultCount.getText(),"0","Verifying the Item is deleted");
		itemsPageObject.backToDashboard();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		Assert.assertEquals(itemsBankPageObject.itemCount.getText(),"0","Verifying the Item count after deleting item in shared bank");
		itemsBankPageObject.viewIcon.click();
		itemsBankPageObject.deleteItemBank(itemBankName);

	}
	
	
	/**
	 * Test for checking the default Read access is disabled and check on permission screen for Read and also other availability of Other acl like Read ,admin ,Create etc.. 
	 * 
	 * 
	 */
	
	@Test(priority = 10)
	public void testVerifyDefaulltAclTrusteeForTestBank(){
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPageObject.createBank(testBankName, testBankDescription);
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);;
		waitTime();
		testBankPageObject.openTestBankShareScreen();
		waitTime();
		Assert.assertFalse(testBankPageObject.aclTrusteeRead.isEnabled());
		Assert.assertTrue(testBankPageObject.aclTrusteeRead.isSelected());
		Assert.assertFalse(testBankPageObject.aclTrusteeWrite.isSelected());
		Assert.assertTrue(testBankPageObject.aclTrusteeWrite.isDisplayed());
		Assert.assertFalse(testBankPageObject.aclTrusteeCreate.isSelected());
		Assert.assertTrue(testBankPageObject.aclTrusteeCreate.isDisplayed());
		Assert.assertFalse(testBankPageObject.aclTrusteeDelete.isSelected());
		Assert.assertTrue(testBankPageObject.aclTrusteeDelete.isDisplayed());
		Assert.assertFalse(testBankPageObject.aclTrusteeAdmin.isSelected());
		Assert.assertTrue(testBankPageObject.aclTrusteeAdmin.isDisplayed());
		testBankPageObject.closeTestBankShareScreen();
		testBankPageObject.backToDashboard();
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.deleteTestBank(testBankName);
	}
	
	/**
	 * Login as school admin
	 * Create Item Bank and Item
	 * Create test bank 
	 * Create test by by using created item
	 * Share the Test bank with teacher having read access 
	 * Login as a teacher and validate the shared test bank
	 */
	@Test(priority = 11)
	public void testShareTestBankWithReadACL(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "READ");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ");
		itemsBankPageObject.closeItemBankShareScreen();
		waitTime();
		itemsBankPageObject.backToDashboard();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPageObject.createBank(testBankName, testBankDescription);
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Need to update the create test bank method to pass test bank and item in method parameter after discussing with team
		testCreationPageObject.createTest(testName , testBankName , itemName);
		waitTime();
		
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);;
		waitTime();
		testBankPageObject.openTestBankShareScreen();
		String sharedLastTeacher = testBankPageObject.shareTestBank(teacher1.split("/")[1], "READ");
		waitTime();
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPageObject.sharedAccess.getText().trim(), "Access: READ");
		testBankPageObject.closeTestBankShareScreen();
		waitTime();
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(teacher1,
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);
		waitTime();
		Assert.assertFalse(testBankPageObject.testBankShareButton.isEnabled());
		testBankPageObject.backLink.click();
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testCreationPageObject.searchTest(testName);
		Assert.assertFalse(testCreationPageObject.testEditIcon.isEnabled());
		Assert.assertFalse(testCreationPageObject.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testViewIcon.isEnabled());
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
		
		itemsBankPageObject.backToDashboard();
		
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.deleteTestBank(testBankName);
		
		
	}
	
	
	
	/**
	 * Login as school admin
	 * Create Item Bank and Item
	 * Create test bank 
	 * Create test by by using created item
	 * Share the Test bank with teacher having Write access 
	 * Login as a teacher and validate the shared test bank
	 */
	
	@Test(priority = 12)
	public void testShareTestBankWithWriteACL(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "WRITE");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE");
		itemsBankPageObject.closeItemBankShareScreen();
		waitTime();
		itemsBankPageObject.backToDashboard();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPageObject.createBank(testBankName, testBankDescription);
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPageObject.createTest(testName , testBankName , itemName);
		waitTime();
		
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);;
		waitTime();
		testBankPageObject.openTestBankShareScreen();
		String sharedLastTeacher = testBankPageObject.shareTestBank(teacher1.split("/")[1], "WRITE");
		waitTime();
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE");
		testBankPageObject.closeTestBankShareScreen();
		waitTime();
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(teacher1,
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);
		waitTime();
		Assert.assertFalse(testBankPageObject.testBankShareButton.isEnabled());
		testBankPageObject.backLink.click();
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testCreationPageObject.searchTest(testName);
		Assert.assertTrue(testCreationPageObject.testEditIcon.isEnabled());
		Assert.assertFalse(testCreationPageObject.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testViewIcon.isEnabled());
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
		
		itemsBankPageObject.backToDashboard();
		
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.deleteTestBank(testBankName);
	}
	
	
	/**
	 * Login as school admin
	 * Create Item Bank and Item
	 * Create test bank 
	 * Create test by by using created item
	 * Share the Test bank with teacher having Create access 
	 * Login as a teacher and validate the shared test bank
	 */
	
	@Test(priority = 13)
	public void testShareTestBankWithCreateACL(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "CREATE");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,CREATE");
		itemsBankPageObject.closeItemBankShareScreen();
		waitTime();
		itemsBankPageObject.backToDashboard();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPageObject.createBank(testBankName, testBankDescription);
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPageObject.createTest(testName , testBankName , itemName);
		waitTime();
		
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);;
		waitTime();
		testBankPageObject.openTestBankShareScreen();
		String sharedLastTeacher = testBankPageObject.shareTestBank(teacher1.split("/")[1], "CREATE");
		waitTime();
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPageObject.sharedAccess.getText().trim(), "Access: READ,CREATE");
		testBankPageObject.closeTestBankShareScreen();
		waitTime();
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(teacher1,
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);
		waitTime();
		Assert.assertFalse(testBankPageObject.testBankShareButton.isEnabled());
		testBankPageObject.backLink.click();
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testCreationPageObject.searchTest(testName);
		Assert.assertFalse(testCreationPageObject.testEditIcon.isEnabled());
		Assert.assertFalse(testCreationPageObject.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testViewIcon.isEnabled());
		Assert.assertEquals(testCreationPageObject.getSharedTestBank(testBankName),testBankName ,"Verifying Shared Test bank is available in Test bank drop down");
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
		
		itemsBankPageObject.backToDashboard();
		
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.deleteTestBank(testBankName);
	}
	
	
	
	/**
	 * Login as school admin
	 * Create Item Bank and Item
	 * Create test bank 
	 * Create test by by using created item
	 * Share the Test bank with teacher having Delete access 
	 * Login as a teacher and validate the shared test bank
	 */
	
	@Test(priority = 14)
	public void testShareTestBankWithDeleteACL(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "DELETE");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,DELETE");
		itemsBankPageObject.closeItemBankShareScreen();
		waitTime();
		itemsBankPageObject.backToDashboard();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPageObject.createBank(testBankName, testBankDescription);
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPageObject.createTest(testName , testBankName , itemName);
		waitTime();
		
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);;
		waitTime();
		testBankPageObject.openTestBankShareScreen();
		String sharedLastTeacher = testBankPageObject.shareTestBank(teacher1.split("/")[1], "DELETE");
		waitTime();
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPageObject.sharedAccess.getText().trim(), "Access: READ,DELETE");
		testBankPageObject.closeTestBankShareScreen();
		waitTime();
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(teacher1,
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);
		waitTime();
		Assert.assertFalse(testBankPageObject.testBankShareButton.isEnabled());
		testBankPageObject.backLink.click();
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testCreationPageObject.searchTest(testName);
		Assert.assertFalse(testCreationPageObject.testEditIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testViewIcon.isEnabled());
		Assert.assertEquals(testCreationPageObject.getSharedTestBank(testBankName),testBankName ,"Verifying Shared Test bank is available in Test bank drop down");
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
		
		itemsBankPageObject.backToDashboard();
		
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.deleteTestBank(testBankName);
	}
	
	
	
	
	/**
	 * Login as school admin
	 * Create Item Bank and Item
	 * Create test bank 
	 * Create test by by using created item
	 * Share the Test bank with teacher having Admin access 
	 * Login as a teacher and validate the shared test bank
	 */
	
	@Test(priority = 15)
	public void testShareTestBankWithAdminACL(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "ADMIN");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,ADMIN");
		itemsBankPageObject.closeItemBankShareScreen();
		waitTime();
		itemsBankPageObject.backToDashboard();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPageObject.createBank(testBankName, testBankDescription);
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPageObject.createTest(testName , testBankName , itemName);
		waitTime();
		
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);;
		waitTime();
		testBankPageObject.openTestBankShareScreen();
		String sharedLastTeacher = testBankPageObject.shareTestBank(teacher1.split("/")[1], "ADMIN");
		waitTime();
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,ADMIN");
		testBankPageObject.closeTestBankShareScreen();
		waitTime();
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(teacher1,
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);
		waitTime();
		Assert.assertTrue(testBankPageObject.testBankShareButton.isEnabled());
		testBankPageObject.backLink.click();
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testCreationPageObject.searchTest(testName);
		Assert.assertFalse(testCreationPageObject.testEditIcon.isEnabled());
		Assert.assertFalse(testCreationPageObject.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testViewIcon.isEnabled());
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
		
		itemsBankPageObject.backToDashboard();
		
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.deleteTestBank(testBankName);
	}
	
	/**
	 * Login as school admin
	 * Create Item Bank and Item
	 * Create test bank 
	 * Create test by by using created item
	 * Share the Test bank with teacher having all access 
	 * Login as a teacher and validate the shared test bank
	 */
	
	@Test(priority = 16)
	public void testShareTestBankWithAllACL(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "READ,WRITE,CREATE,DELETE,ADMIN");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPageObject.closeItemBankShareScreen();
		waitTime();
		itemsBankPageObject.backToDashboard();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPageObject.createBank(testBankName, testBankDescription);
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPageObject.createTest(testName , testBankName , itemName);
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);;
		waitTime();
		testBankPageObject.openTestBankShareScreen();
		String sharedLastTeacher = testBankPageObject.shareTestBank(teacher1.split("/")[1], "READ,WRITE,CREATE,DELETE,ADMIN");
		waitTime();
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		testBankPageObject.closeTestBankShareScreen();
		waitTime();
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(teacher1,
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);
		waitTime();
		Assert.assertTrue(testBankPageObject.testBankShareButton.isEnabled());
		testBankPageObject.backLink.click();
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testCreationPageObject.searchTest(testName);
		Assert.assertTrue(testCreationPageObject.testEditIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testViewIcon.isEnabled());
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
		
		itemsBankPageObject.backToDashboard();
		
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.deleteTestBank(testBankName);
	}
	
	
	/**
	 * Login as school admin
	 * Create Item Bank and Item
	 * Create test bank 
	 * Create test by by using created item
	 * Share the Test bank with teacher having all access 
	 * Login as a teacher
	 * Add test in shared bank
	 * Login as School admin and validate test added in shared test bank
	 */
	
	@Test(priority = 17)
	public void testAddTestinShareTestBankWithAllACL(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "READ,WRITE,CREATE,DELETE,ADMIN");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPageObject.closeItemBankShareScreen();
		waitTime();
		itemsBankPageObject.backToDashboard();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPageObject.createBank(testBankName, testBankDescription);
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPageObject.createTest(testName , testBankName , itemName);
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);;
		waitTime();
		testBankPageObject.openTestBankShareScreen();
		String sharedLastTeacher = testBankPageObject.shareTestBank(teacher1.split("/")[1], "READ,WRITE,CREATE,DELETE,ADMIN");
		waitTime();
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		testBankPageObject.closeTestBankShareScreen();
		waitTime();
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(teacher1,
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);
		waitTime();
		Assert.assertTrue(testBankPageObject.testBankShareButton.isEnabled());
		testBankPageObject.backLink.click();
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testCreationPageObject.searchTest(testName);
		Assert.assertTrue(testCreationPageObject.testEditIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testViewIcon.isEnabled());
		testNameByTeacher = "T1_"  + testBankName;
		testCreationPageObject.createTest(testNameByTeacher , testBankName , itemName);
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testCreationPageObject.searchTest(testNameByTeacher);
		Assert.assertEquals(testCreationPageObject.testResultCount.getText(),"1","Verifying the Item is added");
		testCreationPageObject.backToDashboard();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
		
		itemsBankPageObject.backToDashboard();
		
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.deleteTestBank(testBankName);
	}
	
	
	/**
	 * Login as school admin
	 * Create Item Bank and Item
	 * Create test bank 
	 * Create test by by using created item
	 * Share the Test bank with teacher having all access 
	 * Login as a teacher
	 * Delete test in shared bank
	 * Login as School admin and validate test deleted in shared test bank
	 */
	
	@Test(priority = 18)
	public void testDeleteTestinShareTestBankWithAllACL(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "READ,WRITE,CREATE,DELETE,ADMIN");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPageObject.closeItemBankShareScreen();
		waitTime();
		itemsBankPageObject.backToDashboard();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPageObject.createBank(testBankName, testBankDescription);
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPageObject.createTest(testName , testBankName , itemName);
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);;
		waitTime();
		testBankPageObject.openTestBankShareScreen();
		String sharedLastTeacher = testBankPageObject.shareTestBank(teacher1.split("/")[1], "READ,WRITE,CREATE,DELETE,ADMIN");
		waitTime();
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		testBankPageObject.closeTestBankShareScreen();
		waitTime();
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(teacher1,
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);
		waitTime();
		Assert.assertTrue(testBankPageObject.testBankShareButton.isEnabled());
		testBankPageObject.backLink.click();
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testCreationPageObject.searchTest(testName);
		Assert.assertTrue(testCreationPageObject.testEditIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPageObject.testViewIcon.isEnabled());
		testCreationPageObject.deleteTest(testName);
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(schooladmin1,
				genericPassword);
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testCreationPageObject.searchTest(testName);
		Assert.assertEquals(testCreationPageObject.testResultCount.getText(),"0","Verifying the Item is added");
		testCreationPageObject.backToDashboard();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.deleteItemBank(itemBankName);
		
		itemsBankPageObject.backToDashboard();
		
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.deleteTestBank(testBankName);
	}
	
	/**
	 * Login as school admin
	 * Create Item Bank and Item
	 * Create test bank 
	 * Create test by by using created item
	 * Share the Test bank with teacher having all access 
	 * Login as a teacher
	 * Create event on shared  test in shared bank
	 * Login as student and attempt the test 
	 * 
	 */
	@Test(priority=19)
	public void  testScheduledEventForSharedTestbySchoolAdmin(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "READ,WRITE,CREATE,DELETE,ADMIN");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPageObject.closeItemBankShareScreen();
		waitTime();
		itemsBankPageObject.backToDashboard();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPageObject.createBank(testBankName, testBankDescription);
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		testCreationPageObject.createTest(testName , testBankName , itemName);
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankPageObject.searchTestBank(testBankName);;
		waitTime();
		testBankPageObject.openTestBankShareScreen();
		String sharedLastTeacher = testBankPageObject.shareTestBank(teacher1.split("/")[1], "READ,WRITE,CREATE,DELETE,ADMIN");
		waitTime();
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		testBankPageObject.closeTestBankShareScreen();
		waitTime();
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(teacher1,
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testCreationPageObject.searchTest(testName);
		String createdTestId = testCreationPageObject.getTestId();
		testCreationPageObject.backToDashboard();
		sechedulePageObject = dashBoardPageObject.goToSchedule();
		waitTime();
		sechedulePageObject.scheduleTest("Euro Kids", "Automation", "N/A", testName, "Green", "120", "100%", "No");
		
		dashBoardPageObject.logOut();
		waitTime();
		
		dashBoardPageObject = loginPageObject.loginSuccess(stduent1,
				genericPassword);
		dashBoardPageObject.addTiles();
		waitTime();
		deliveryPageObject = dashBoardPageObject.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPageObject.getScheduledTest(createdTestId));
		deliveryPageObject.takeTest(createdTestId);
		Assert.assertEquals(testName, deliveryPageObject.getTestinHistoryTable(createdTestId));
		Assert.assertEquals("100%", deliveryPageObject.getTestPercentCorrect(createdTestId));
		Assert.assertEquals("1", deliveryPageObject.getTestNoOfItems(createdTestId));
	}
	
	
	/**
	 * Login as school admin
	 * Create Item Bank and Item
	 * Shared the item bank will all permission
	 * Login as a teacher
	 * Create test by by using shared item
	 * Schedule the test 
	 * Login as Student   
	 * Write the test
	 * Check the test result information in History Table
	 * 
	 */
	@Test(priority=20)
	public void  testScheduledEventForSharedItemBySchoolAdmin(){
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
		itemsPageObject.createItem(itemName , itemBankName);
		waitTime();
		itemsBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		itemsBankPageObject.searchItemBank(itemBankName);;
		waitTime();
		itemsBankPageObject.openItemBankShareScreen();
		String sharedTeacher = itemsBankPageObject.shareItemBank(teacher1.split("/")[1], "READ,WRITE,CREATE,DELETE,ADMIN");
		waitTime();
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPageObject.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPageObject.closeItemBankShareScreen();
		dashBoardPageObject.logOut();
		waitTime();
		dashBoardPageObject = loginPageObject.loginSuccess(teacher1,
				genericPassword);
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		testBankPageObject = dashBoardPageObject.goToTestsBank();
		waitTime();
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPageObject.createBank(testBankName, testBankDescription);
		waitTime();
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		testCreationPageObject.createTest(testName , testBankName , itemName);
		testCreationPageObject = dashBoardPageObject.goToTestCreation();
		testCreationPageObject.searchTest(testName);
		String createdTestId = testCreationPageObject.getTestId();
		testCreationPageObject.backToDashboard();
		sechedulePageObject = dashBoardPageObject.goToSchedule();
		waitTime();
		sechedulePageObject.scheduleTest("Euro Kids", "Automation", "N/A", testName, "Green", "120", "100%", "No");
		
		dashBoardPageObject.logOut();
		waitTime();
		
		dashBoardPageObject = loginPageObject.loginSuccess(stduent1,
				genericPassword);
		dashBoardPageObject.addTiles();
		waitTime();
		deliveryPageObject = dashBoardPageObject.goToDelivery();
		waitTime();
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPageObject.getScheduledTest(createdTestId));
		deliveryPageObject.takeTest(createdTestId);
		Assert.assertEquals(testName, deliveryPageObject.getTestinHistoryTable(createdTestId));
		Assert.assertEquals("100%", deliveryPageObject.getTestPercentCorrect(createdTestId));
		Assert.assertEquals("1", deliveryPageObject.getTestPercentCorrect(createdTestId));
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
