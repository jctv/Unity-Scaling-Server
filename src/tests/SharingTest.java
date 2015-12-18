package tests;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import generic.BaseTest;
import pages.ClassRoster;
import pages.DashBoard;
import pages.Delivery;
import pages.HandScoring;
import pages.ItemImport;
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
	//String unitytestdata.getProperty("autoTeacher1") = "qa/nunitytestdata.getProperty("autoTeacher1")";
	//String stduent1 = "qa/nstudent1";
	//String unitytestdata.getProperty("genericPassword") = "12345";
	String defaultAutoAdmin = "at/admin";
	
	String defaultAutoPassword = "password";

	//String autoschoolAdmin = "at/autoschooladmin";
	
	//String autounitytestdata.getProperty("autoTeacher1") = "at/autounitytestdata.getProperty("autoTeacher1")";
	
	Properties unitytestdata;
	
	String lastSaharedTeacher;
	
	Login loginPage;
	DashBoard dashBoardPage;
	Items itemsPage;
	Users usersPage;
	ClassRoster classRosterPage;
	TestCreation testCreationPage;
	Schedule sechedulePage;
	Delivery deliveryPage;
	HandScoring handScoringPage;
	Reports reportsPage;
	Organization organizationPage;
	ItemsBank itemsBankPage;
	TestsBank testBankPage;
	ItemImport itemsImportPage;
	
	String itemBankName ;
	String itemName ;
	String copiedItemName ;

	String itemNameByTeacher;
	String testNameByTeacher;
	String itemDesc = "Auto Item";
	String itemBankDescription = "Auto desc";
	
	String interactionChoice = unitytestdata.getProperty("interactionChoice");
	String interactionTextEntry = unitytestdata.getProperty("interactionTextEntry");
	String simpleMatchScoreProfile = unitytestdata.getProperty("simpleMatchScoreProfile");
	String mapScoreProfile = unitytestdata.getProperty("mapScoreProfile");
	String handScoreProfile  = unitytestdata.getProperty("handScoreProfile");
	String choiceCorrectAnswer = unitytestdata.getProperty("choiceCorrectAnswer");
	String textEntryCorrcetAnswer = unitytestdata.getProperty("textEntryCorrcetAnswer");
	
	String testBankName ;
	String testName ;
	String testDesc = "Auto Test";
	String testBankDescription = "Auto test bank desc";
	
	String bulkItemImportFileName = "Bulk Item Upload.zip";
	
	String resources = "src" + File.separator + "resources"
			+ File.separator;
	
	String unitytestDataFile = resources + "unitytestdata.properties";
	
	String bulkItemImportFile = resources + bulkItemImportFileName;
	

	public SharingTest () {
		super();
		
	}
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unitytestDataFile);
		lastSaharedTeacher= unitytestdata.getProperty("autoTeacher1").substring(0, 1) + " " +unitytestdata.getProperty("autoTeacher1").substring(1);
	}
	
	@BeforeMethod
	public void setUp() {
		System.out.println("Load Unity url - " + url);
		driver.get(url);
		loginPage = new Login(driver);
		System.out.println("******** logging as  school admin  -- " + defaultAutoAdmin  + "******** " );
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoschoolAdmin"), unitytestdata.getProperty("genericPassword"));
		dashBoardPage.addTiles();
		
	}

	/**
	 * Test for checking the default Read access is disabled and check on permission screen for Read and also other availability of Other acl like Read ,admin ,Create etc.. 
	 * 
	 * 
	 */
	
	@Test(priority = 1)
	public void testVerifyDefaulltAclTrusteeForItemBank(){
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		customeWaitTime(5);
		Assert.assertFalse(itemsBankPage.aclTrusteeRead.isEnabled());
		Assert.assertTrue(itemsBankPage.aclTrusteeRead.isSelected());
		Assert.assertFalse(itemsBankPage.aclTrusteeWrite.isSelected());
		Assert.assertTrue(itemsBankPage.aclTrusteeWrite.isDisplayed());
		Assert.assertFalse(itemsBankPage.aclTrusteeCreate.isSelected());
		Assert.assertTrue(itemsBankPage.aclTrusteeCreate.isDisplayed());
		Assert.assertFalse(itemsBankPage.aclTrusteeDelete.isSelected());
		Assert.assertTrue(itemsBankPage.aclTrusteeDelete.isDisplayed());
		Assert.assertFalse(itemsBankPage.aclTrusteeAdmin.isSelected());
		Assert.assertTrue(itemsBankPage.aclTrusteeAdmin.isDisplayed());
		itemsBankPage.closeItemBankShareScreen();
		itemsBankPage.backToDashboard();
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		itemsBankPage.logOut();
	}
	
	/**
	 * Test for sharing the blank item bank by school admin with teacher after that  login  as a teacher and verifying the Item Bank shared with Read access
	 */
	
	@Test(priority = 2)
	public void testShareItemBankwithReadACL(){
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "READ");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);
		customeWaitTime(5);
		Assert.assertFalse(itemsBankPage.shareButton.isEnabled());
		assertItemBankStatisticsPanelContent();
		itemsBankPage.waitForElementAndClick(itemsBankPage.backLink);
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		itemsPage.searchItem(itemName);
		Assert.assertFalse(itemsPage.itemEditIcon.isEnabled());
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		itemsBankPage.logOut();
	}
	
	/**
	 * Test for sharing the blank item bank by school admin with teacher with Write Access  after that  login  as a teacher and verifying the Item Bank shared with Write access. 
	 */
	
	@Test(priority = 3)
	public void testShareItemBankWithWriteACL(){
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "WRITE");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);
		customeWaitTime(5);
		Assert.assertFalse(itemsBankPage.shareButton.isEnabled());
		assertItemBankStatisticsPanelContent();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		itemsPage.searchItem(itemName);
		Assert.assertTrue(itemsPage.itemEditIcon.isEnabled());
		itemsPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		itemsBankPage.logOut();
	}
	
	
	/**
	 * Test for sharing the blank item bank by school admin with teacher with Create  Access  after that  login  as a teacher and verifying the Item Bank shared with Create access. 
	 */
	
	@Test(priority = 4)
	public void testShareItemBankwithCreateACL(){
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_ " + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item Bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "CREATE");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,CREATE");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);
		customeWaitTime(5);
		Assert.assertEquals(itemsBankPage.shareButton.getAttribute("disabled"),"true","Verifying the Item bank share icon is disabled");
		assertItemBankStatisticsPanelContent();
		itemsBankPage.waitForElementAndClick(itemsBankPage.backLink);
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		itemsPage.searchItem(itemName);
		Assert.assertFalse(itemsPage.itemEditIcon.isEnabled());
		Assert.assertEquals(itemsPage.getSharedItemBank(itemBankName),itemBankName ,"Verifying Shared Item bank is available in Item bank drop down");
		itemsPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		itemsBankPage.logOut();
		
	}
	
	/**
	 * Test for sharing the blank item bank by school admin with teacher with Delete   Access  after that  login  as a teacher and verifying the Item Bank/item  shared with Delete  access. 
	 */
	@Test(priority = 5)
	public void testShareBlankItemBankwithDeleteACL(){
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_ " + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item Bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item  creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "DELETE");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,DELETE");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);
		customeWaitTime(5);
		Assert.assertEquals(itemsBankPage.shareButton.getAttribute("disabled"),"true","Verifying the Item bank share icon is disabled");
		assertItemBankStatisticsPanelContent();
		itemsBankPage.waitForElementAndClick(itemsBankPage.backLink);
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		itemsPage.searchItem(itemName);
		Assert.assertFalse(itemsPage.itemEditIcon.isEnabled());
        Assert.assertTrue(itemsPage.itemDeleteIcon.isEnabled());
        itemsPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		itemsBankPage.logOut();
		
	}
	
	/**
	 * Test for sharing the blank item bank by school admin with teacher with Admin Access  after that  login  as a teacher and verifying the Item Bank/item  shared with admin  access. 
	 */
	@Test(priority = 6)
	public void testShareItemBankwithAdminACL(){
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_ " + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item  Bank  creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,ADMIN");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);
		customeWaitTime(5);
		Assert.assertTrue(itemsBankPage.shareButton.isEnabled(),"Verifying the Item Bank share icon is editable");
		assertItemBankStatisticsPanelContent();
		itemsBankPage.waitForElementAndClick(itemsBankPage.backLink);
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		itemsPage.searchItem(itemName);
		Assert.assertTrue(itemsPage.itemEditIcon.isEnabled());
        Assert.assertFalse(itemsPage.itemDeleteIcon.isEnabled());
        itemsPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		itemsBankPage.logOut();

	}
	
	
	/**
	 * Test for sharing the blank item bank by school admin with teacher with all  permission  after that  login  as a teacher and verifying the Item Bank/item  shared with admin  access. 
	 */
	@Test(priority = 7)
	public void testShareItemBankwithAllACL(){
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_ " + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item  Bank  creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);
		customeWaitTime(5);
		Assert.assertTrue(itemsBankPage.shareButton.isEnabled(),"Verifying the Item Bank share icon is editable");
		assertItemBankStatisticsPanelContent();
		itemsBankPage.waitForElementAndClick(itemsBankPage.backLink);
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		itemsPage.searchItem(itemName);
		Assert.assertTrue(itemsPage.itemEditIcon.isEnabled());
        Assert.assertTrue(itemsPage.itemDeleteIcon.isEnabled());
		Assert.assertEquals(itemsPage.getSharedItemBank(itemBankName),itemBankName ,"Verifying Shared Item bank is available in Item bank drop down");
        dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		itemsBankPage.logOut();

	}
	
	
	/**
	 * School admin create Item bank with one item 
	 * Share Item Item bank with Teacher
	 * Teacher create item in Shared item bank by school admin 
	 * School admin checks added item in shared 
	 */
	@Test(priority = 8)
	public void testAddItemsInSharedItemBankwithAllACL(){
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_ " + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item  Bank  creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);
		customeWaitTime(5);
		Assert.assertTrue(itemsBankPage.shareButton.isEnabled(),"Verifying the Item Bank share icon is editable");
		assertItemBankStatisticsPanelContent();
		itemsBankPage.waitForElementAndClick(itemsBankPage.backLink);
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		itemsPage.searchItem(itemName);
		Assert.assertTrue(itemsPage.itemEditIcon.isEnabled());
        Assert.assertTrue(itemsPage.itemDeleteIcon.isEnabled());
		Assert.assertEquals(itemsPage.getSharedItemBank(itemBankName),itemBankName ,"Verifying Shared Item bank is available in Item bank drop down");
		itemNameByTeacher = "I1_"  + itemBankName;
		
		itemsPage.createItem(itemNameByTeacher , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
        dashBoardPage.logOut();	
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemsPage.searchItem(itemNameByTeacher);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		Assert.assertEquals(itemsBankPage.itemCount.getText(),"2","Verifying the Item count after adding item in shared bank");
		itemsBankPage.viewIcon.click();
		itemsBankPage.deleteItemBank(itemBankName);
		itemsBankPage.logOut();

	}
	
	
	/**
	 * School admin create Item bank with one item 
	 * Share Item Item bank with Teacher
	 * Teacher delete item in Shared item bank by school admin 
	 * School admin checks item is deleted  
	 */
	@Test(priority = 9)
	public void testDeleteItemsInSharedItemBankwithAllACL(){
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_ " + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item  Bank  creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);
		customeWaitTime(5);
		Assert.assertTrue(itemsBankPage.shareButton.isEnabled(),"Verifying the Item Bank share icon is editable");
		assertItemBankStatisticsPanelContent();
		itemsBankPage.waitForElementAndClick(itemsBankPage.backLink);
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		itemsPage.searchItem(itemName);
		Assert.assertTrue(itemsPage.itemEditIcon.isEnabled());
        Assert.assertTrue(itemsPage.itemDeleteIcon.isEnabled());
		Assert.assertEquals(itemsPage.getSharedItemBank(itemBankName),itemBankName ,"Verifying Shared Item bank is available in Item bank drop down");
		itemsPage.deleteItem(itemName);
        dashBoardPage.logOut();	
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemsPage.searchItem(itemName);
		Assert.assertEquals(itemsPage.itemResultCount.getText(),"0","Verifying the Item is deleted");
		itemsPage.backToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		Assert.assertEquals(itemsBankPage.itemCount.getText(),"0","Verifying the Item count after deleting item in shared bank");
		itemsBankPage.viewIcon.click();
		itemsBankPage.deleteItemBank(itemBankName);
		itemsBankPage.logOut();

	}
	
	
	/**
	 * Test for checking the default Read access is disabled and check on permission screen for Read and also other availability of Other acl like Read ,admin ,Create etc.. 
	 * 
	 * 
	 */
	
	@Test(priority = 10)
	public void testVerifyDefaulltAclTrusteeForTestBank(){
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, testBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);;
		customeWaitTime(5);
		testBankPage.openTestBankShareScreen();
		customeWaitTime(5);
		Assert.assertFalse(testBankPage.aclTrusteeRead.isEnabled());
		Assert.assertTrue(testBankPage.aclTrusteeRead.isSelected());
		Assert.assertFalse(testBankPage.aclTrusteeWrite.isSelected());
		Assert.assertTrue(testBankPage.aclTrusteeWrite.isDisplayed());
		Assert.assertFalse(testBankPage.aclTrusteeCreate.isSelected());
		Assert.assertTrue(testBankPage.aclTrusteeCreate.isDisplayed());
		Assert.assertFalse(testBankPage.aclTrusteeDelete.isSelected());
		Assert.assertTrue(testBankPage.aclTrusteeDelete.isDisplayed());
		Assert.assertFalse(testBankPage.aclTrusteeAdmin.isSelected());
		Assert.assertTrue(testBankPage.aclTrusteeAdmin.isDisplayed());
		testBankPage.closeTestBankShareScreen();
		testBankPage.backToDashboard();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.deleteTestBank(testBankName);
		testBankPage.logOut();
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
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "READ");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		itemsBankPage.backToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, testBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Need to update the create test bank method to pass test bank and item in method parameter after discussing with team
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);;
		customeWaitTime(5);
		testBankPage.openTestBankShareScreen();
		String sharedLastTeacher = testBankPage.shareTestBank(unitytestdata.getProperty("autoTeacher1"), "READ");
		customeWaitTime(5);
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPage.sharedAccess.getText().trim(), "Access: READ");
		testBankPage.closeTestBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);
		customeWaitTime(5);
		Assert.assertFalse(testBankPage.testBankShareButton.isEnabled());
		testBankPage.backLink.click();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.searchTest(testName);
		Assert.assertFalse(testCreationPage.testEditIcon.isEnabled());
		Assert.assertFalse(testCreationPage.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testViewIcon.isEnabled());
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		
		itemsBankPage.backToDashboard();
		
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.deleteTestBank(testBankName);
		testBankPage.logOut();
		
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
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "WRITE");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		itemsBankPage.backToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, testBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);;
		customeWaitTime(5);
		testBankPage.openTestBankShareScreen();
		String sharedLastTeacher = testBankPage.shareTestBank(unitytestdata.getProperty("autoTeacher1"), "WRITE");
		customeWaitTime(5);
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE");
		testBankPage.closeTestBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);
		customeWaitTime(5);
		Assert.assertFalse(testBankPage.testBankShareButton.isEnabled());
		testBankPage.backLink.click();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.searchTest(testName);
		Assert.assertTrue(testCreationPage.testEditIcon.isEnabled());
		Assert.assertFalse(testCreationPage.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testViewIcon.isEnabled());
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		
		itemsBankPage.backToDashboard();
		
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.deleteTestBank(testBankName);
		testBankPage.logOut();
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
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "CREATE");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,CREATE");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		itemsBankPage.backToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, testBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);;
		customeWaitTime(5);
		testBankPage.openTestBankShareScreen();
		String sharedLastTeacher = testBankPage.shareTestBank(unitytestdata.getProperty("autoTeacher1"), "CREATE");
		customeWaitTime(5);
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPage.sharedAccess.getText().trim(), "Access: READ,CREATE");
		testBankPage.closeTestBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);
		customeWaitTime(5);
		Assert.assertFalse(testBankPage.testBankShareButton.isEnabled());
		testBankPage.backLink.click();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.searchTest(testName);
		Assert.assertFalse(testCreationPage.testEditIcon.isEnabled());
		Assert.assertFalse(testCreationPage.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testViewIcon.isEnabled());
		Assert.assertEquals(testCreationPage.getSharedTestBank(testBankName),testBankName ,"Verifying Shared Test bank is available in Test bank drop down");
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		
		itemsBankPage.backToDashboard();
		
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.deleteTestBank(testBankName);
		testBankPage.logOut();
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
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "DELETE");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,DELETE");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		itemsBankPage.backToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, testBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);;
		customeWaitTime(5);
		testBankPage.openTestBankShareScreen();
		String sharedLastTeacher = testBankPage.shareTestBank(unitytestdata.getProperty("autoTeacher1"), "DELETE");
		customeWaitTime(5);
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPage.sharedAccess.getText().trim(), "Access: READ,DELETE");
		testBankPage.closeTestBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);
		customeWaitTime(5);
		Assert.assertFalse(testBankPage.testBankShareButton.isEnabled());
		testBankPage.backLink.click();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.searchTest(testName);
		Assert.assertFalse(testCreationPage.testEditIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testViewIcon.isEnabled());
		Assert.assertEquals(testCreationPage.getSharedTestBank(testBankName),testBankName ,"Verifying Shared Test bank is available in Test bank drop down");
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		
		itemsBankPage.backToDashboard();
		
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.deleteTestBank(testBankName);
		testBankPage.logOut();
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
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,ADMIN");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		itemsBankPage.backToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, testBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(5);
		returnToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);;
		customeWaitTime(5);
		testBankPage.openTestBankShareScreen();
		String sharedLastTeacher = testBankPage.shareTestBank(unitytestdata.getProperty("autoTeacher1"), "ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,ADMIN");
		testBankPage.closeTestBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);
		customeWaitTime(5);
		Assert.assertTrue(testBankPage.testBankShareButton.isEnabled());
		testBankPage.backLink.click();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.searchTest(testName);
		Assert.assertFalse(testCreationPage.testEditIcon.isEnabled());
		Assert.assertFalse(testCreationPage.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testViewIcon.isEnabled());
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		
		itemsBankPage.backToDashboard();
		
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.deleteTestBank(testBankName);
		testBankPage.logOut();
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
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		itemsBankPage.backToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, testBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(5);
		returnToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);;
		customeWaitTime(5);
		testBankPage.openTestBankShareScreen();
		String sharedLastTeacher = testBankPage.shareTestBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		testBankPage.closeTestBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);
		customeWaitTime(5);
		Assert.assertTrue(testBankPage.testBankShareButton.isEnabled());
		testBankPage.backLink.click();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.searchTest(testName);
		Assert.assertTrue(testCreationPage.testEditIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testViewIcon.isEnabled());
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		
		itemsBankPage.backToDashboard();
		
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.deleteTestBank(testBankName);
		testBankPage.logOut();
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
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		itemsBankPage.backToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, testBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(5);
		returnToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);;
		customeWaitTime(5);
		testBankPage.openTestBankShareScreen();
		String sharedLastTeacher = testBankPage.shareTestBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		testBankPage.closeTestBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);
		customeWaitTime(5);
		Assert.assertTrue(testBankPage.testBankShareButton.isEnabled());
		testBankPage.backLink.click();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.searchTest(testName);
		Assert.assertTrue(testCreationPage.testEditIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testViewIcon.isEnabled());
		testNameByTeacher = "T1_"  + testBankName;
		testCreationPage.createTest(testNameByTeacher , testBankName , itemName);
		returnToDashboard();
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.searchTest(testNameByTeacher);
		Assert.assertEquals(testCreationPage.testResultCount.getText(),"1","Verifying the Item is added");
		testCreationPage.backToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		
		itemsBankPage.backToDashboard();
		
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.deleteTestBank(testBankName);
		testBankPage.logOut();
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
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		itemsBankPage.backToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, testBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		//Temp comment -Need to update the create test bank method to pass test bank and item in method parameter after discussing with team and Camilo
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(5);
		returnToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);;
		customeWaitTime(5);
		testBankPage.openTestBankShareScreen();
		String sharedLastTeacher = testBankPage.shareTestBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		testBankPage.closeTestBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);
		customeWaitTime(5);
		Assert.assertTrue(testBankPage.testBankShareButton.isEnabled());
		testBankPage.backLink.click();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.searchTest(testName);
		Assert.assertTrue(testCreationPage.testEditIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testDeleteIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testCopyIcon.isEnabled());
		Assert.assertTrue(testCreationPage.testViewIcon.isEnabled());
		testCreationPage.deleteTest(testName);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(schooladmin1,
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.searchTest(testName);
		Assert.assertEquals(testCreationPage.testResultCount.getText(),"0","Verifying the Item is added");
		testCreationPage.backToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.deleteItemBank(itemBankName);
		
		itemsBankPage.backToDashboard();
		
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.deleteTestBank(testBankName);
		testBankPage.logOut();
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
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		itemsBankPage.backToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, testBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(5);
		returnToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);;
		customeWaitTime(5);
		testBankPage.openTestBankShareScreen();
		String sharedLastTeacher = testBankPage.shareTestBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		testBankPage.closeTestBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		testCreationPage.backToDashboard();
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(5);
		sechedulePage.scheduleTest("Euro Kids", "Automation", "N/A", testName, "Green", "120", "100%", "No");
		returnToDashboard();
		dashBoardPage.logOut();
		customeWaitTime(5);
		
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoStduent1"),
				unitytestdata.getProperty("genericPassword"));
		dashBoardPage.addTiles();
		customeWaitTime(5);
		deliveryPage = dashBoardPage.goToDelivery();
		customeWaitTime(5);
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		deliveryPage.takeTest(true , 4 , "Choice","a");
		Assert.assertEquals(testName, deliveryPage.getTestinHistoryTable(createdTestId));
		Assert.assertEquals("100%", deliveryPage.getTestPercentCorrect(createdTestId));
		Assert.assertEquals("1", deliveryPage.getTestNoOfItems(createdTestId));
		deliveryPage.logOut();
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
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "Auto_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPage.closeItemBankShareScreen();
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankName = "Auto_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, testBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		testCreationPage.createTest(testName , testBankName , itemName);
		returnToDashboard();
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		testCreationPage.backToDashboard();
		sechedulePage = dashBoardPage.goToSchedule();
		customeWaitTime(5);
		sechedulePage.scheduleTest("Euro Kids", "Automation", "N/A", testName, "Green", "120", "100%", "No");
		returnToDashboard();
		dashBoardPage.logOut();
		customeWaitTime(5);
		
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoStduent1"),
				unitytestdata.getProperty("genericPassword"));
		dashBoardPage.addTiles();
		customeWaitTime(5);
		deliveryPage = dashBoardPage.goToDelivery();
		customeWaitTime(5);
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		deliveryPage.takeTest(true , 4 , "Choice", "a");
		Assert.assertEquals(testName, deliveryPage.getTestinHistoryTable(createdTestId));
		Assert.assertEquals("100%", deliveryPage.getTestPercentCorrect(createdTestId));
		Assert.assertEquals("1", deliveryPage.getTestPercentCorrect(createdTestId));
		deliveryPage.logOut();
	}

	
	
	/**
	 * Defect 8323
	 * Login as school admin
	 * Create Item Bank and Item
	 * Create test bank 
	 * Create test by by using created item
	 * Share the Test bank with teacher having only View  access 
	 * Login as a teacher
	 * Access the shared test 
	 * Scheduled test 
	 * Login as student and attempt the test 
	 * 
	 */
	@Test(priority=21)
	public void  testScheduledEventForSharedTestWithViewAccess(){
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemBankName = "READ_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
		customeWaitTime(5);
		itemName = "I_" + itemBankName;
		System.out.println("******** " + itemName + "  Item creation ********");
		itemsPage.createItem(itemName , itemBankName);
		customeWaitTime(5);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(5);
		itemsBankPage.searchItemBank(itemBankName);;
		customeWaitTime(5);
		itemsBankPage.openItemBankShareScreen();
		String sharedTeacher = itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "READ");
		customeWaitTime(5);
		Assert.assertEquals(sharedTeacher, lastSaharedTeacher);
		Assert.assertEquals(itemsBankPage.sharedAccess.getText().trim(), "Access: READ");
		itemsBankPage.closeItemBankShareScreen();
		customeWaitTime(5);
		itemsBankPage.backToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankName = "READ_TB_" + System.currentTimeMillis();
		System.out.println("******** " + testBankName + "  Test bank creation ********");
		testBankPage.createBank(testBankName, testBankDescription);
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testName = "T_" + testBankName;
		System.out.println("******** " + testName + "  Test creation ********");
		testCreationPage.createTest(testName , testBankName , itemName);
		customeWaitTime(5);
		returnToDashboard();
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(5);
		testBankPage.searchTestBank(testBankName);;
		customeWaitTime(5);
		testBankPage.openTestBankShareScreen();
		String sharedLastTeacher = testBankPage.shareTestBank(unitytestdata.getProperty("autoTeacher1"), "READ");
		customeWaitTime(5);
		Assert.assertEquals(sharedLastTeacher, lastSaharedTeacher);
		Assert.assertEquals(testBankPage.sharedAccess.getText().trim(), "Access: READ");
		testBankPage.closeTestBankShareScreen();
		customeWaitTime(5);
		dashBoardPage.logOut();
		customeWaitTime(5);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		testCreationPage = dashBoardPage.goToTestCreation();
		testCreationPage.searchTest(testName);
		String createdTestId = testCreationPage.getTestId();
		Assert.assertFalse(testCreationPage.testDeleteIcon.isEnabled());
		Assert.assertFalse(testCreationPage.testEditIcon.isEnabled());
		customeWaitTime(5);
		sechedulePage = testCreationPage.navigateToScheduleFromListings();
		customeWaitTime(5);
		sechedulePage.scheduleTest("Euro Kids", "Automation", "N/A", testName, "Green", "120", "100%", "No");
		returnToDashboard();
		dashBoardPage.logOut();
		customeWaitTime(5);
		
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoStduent1"),
				unitytestdata.getProperty("genericPassword"));
		dashBoardPage.addTiles();
		customeWaitTime(5);
		deliveryPage = dashBoardPage.goToDelivery();
		customeWaitTime(5);
		System.out.println("******** Taking the scheduled test ********");
		Assert.assertEquals(testName, deliveryPage.getScheduledTest(createdTestId));
		deliveryPage.startScheduledTest(createdTestId);
		deliveryPage.takeTest(true , 4 , "Choice","a");
		Assert.assertEquals(testName, deliveryPage.getTestinHistoryTable(createdTestId));
		Assert.assertEquals("100%", deliveryPage.getTestPercentCorrect(createdTestId));
		Assert.assertEquals("1", deliveryPage.getTestNoOfItems(createdTestId));
		deliveryPage.logOut();
	}
	
	/**
	 * Login as  admin 
	 * Import the 20 items in item bank
	 * Share the item bank with teacher
	 * Login as a teacher 
	 * Go to the Item Bank page
	 * Verify the number of item in shared item bank.
	 * 
	 * 
	 */
	@Test(priority = 22)
	public void testItemBankSharingWithBulkImportedItems(){
		dashBoardPage.logOut();
		driver.get(url);
		dashBoardPage = loginPage.loginSuccess(defaultAutoAdmin, defaultAutoPassword);
		customeWaitTime(5);
		dashBoardPage.addTiles();
		itemsBankPage = dashBoardPage.goToItemsBank();
		itemBankName = "Bulk_Import_IB_" + System.currentTimeMillis();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		returnToDashboard();
		customeWaitTime(5);
		itemsImportPage = dashBoardPage.goToItemImport();
		itemsImportPage.importItem(bulkItemImportFile, itemBankName, unitytestdata.getProperty("qti_pkg "), unitytestdata.getProperty("define_lifecycle"));
		customeWaitTime(30);
		itemsImportPage.refreshPage();
		Assert.assertEquals(itemsImportPage.waitAndGetElementText(itemsImportPage.itemImportFileNameList)
				.trim(), bulkItemImportFileName.trim());
		Assert.assertEquals(itemsImportPage.waitAndGetElementText(itemsImportPage.itemImportFileStatusList)
				.trim(), "Completed");
		Assert.assertTrue(itemsImportPage.waitAndGetElementText(itemsImportPage.itemImportFileDetailsList).contains("Completed without error"));
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		itemsBankPage.searchItemBank(itemBankName);
		itemsBankPage.waitForElementAndClick(itemsBankPage.viewIcon);
		String itemCountOfBank = itemsBankPage.itemCount.getText();
		itemsBankPage.openItemBankShareScreen();
		itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPage.closeItemBankShareScreen();
		returnToDashboard();
		dashBoardPage.logOut();
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		itemsBankPage.searchItemBank(itemBankName);
		itemsBankPage.waitForElementAndClick(itemsBankPage.viewIcon);
		Assert.assertEquals(itemsBankPage.itemCount.getText().trim(),itemCountOfBank.trim(),"Verifying the Item count");
		itemsBankPage.waitForElementAndClick(itemsBankPage.viewIcon);
		itemsBankPage.logOut();

	}
	
	/**
	 * Login into the unity as admin 
	 * Go to the item tile
	 * create one item
	 * Create 20 copied in same item bank
	 * Share the item bank with teacher.
	 * Login as a teacher
	 * Verify  Item bank shared properly with its content	
	 * 
	 */
	
	@Test(priority = 23)
	public void testItemBankSharingWithBulkItems(){
		long timeStamp = System.currentTimeMillis();
		itemBankName = "Bulk__IB_" + timeStamp;
		itemsBankPage = dashBoardPage.goToItemsBank();
		System.out.println("******** " + itemBankName + "  Item bank creation ********");
		itemsBankPage.createBank(itemBankName, itemBankDescription);
		returnToDashboard();
		customeWaitTime(5);
		itemsPage = dashBoardPage.goToItems();
        itemName = "it" +  timeStamp;
		itemsPage.createItem(itemName, itemBankName ,interactionChoice , simpleMatchScoreProfile , choiceCorrectAnswer);
		itemsPage.searchItem(itemName);
		itemsPage.addStandards();
        copiedItemName = "copy" + itemName;
		itemsPage.copyMultipleItems(itemBankName, itemName, copiedItemName, 1, 20);
		returnToDashboard();
		itemsBankPage = dashBoardPage.goToItemsBank();
		itemsBankPage.searchItemBank(itemBankName);
		itemsBankPage.waitForElementAndClick(itemsBankPage.viewIcon);
		String itemCountOfBank = itemsBankPage.itemCount.getText();
		itemsBankPage.waitForElementAndClick(itemsBankPage.viewIcon);
		itemsBankPage.openItemBankShareScreen();
		itemsBankPage.shareItemBank(unitytestdata.getProperty("autoTeacher1"), "READ,WRITE,CREATE,DELETE,ADMIN");
		itemsBankPage.closeItemBankShareScreen();
		returnToDashboard();
		dashBoardPage.logOut();
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("autoTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		customeWaitTime(5);
		dashBoardPage.addTiles();
		customeWaitTime(5);
		itemsBankPage = dashBoardPage.goToItemsBank();
		itemsBankPage.searchItemBank(itemBankName);
		itemsBankPage.waitForElementAndClick(itemsBankPage.viewIcon);
		Assert.assertEquals(itemsBankPage.itemCount.getText().trim(),itemCountOfBank.trim(),"Verifying the Item count");
		itemsBankPage.waitForElementAndClick(itemsBankPage.viewIcon);
		itemsBankPage.logOut();

	}
	private void assertItemBankStatisticsPanelContent(){
		itemsBankPage.viewIcon.click();
		Assert.assertTrue(itemsBankPage.itemBankStatisticsPanel.isDisplayed(),"Verifying the Item Bank statisticsPanel is expanded");
		Assert.assertEquals(itemsBankPage.itemCount.getText(),"1","Verifying the Item count");
		Assert.assertEquals(itemsBankPage.mediaCount.getText(),"0","Verifying the media count");
		Assert.assertEquals(itemsBankPage.passageCount.getText(),"0","Verifying the passage count");
		Assert.assertEquals(itemsBankPage.rubricCount.getText(),"0","Verifying the rubrics count");
		
	}
}
