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
import generic.BaseTest;

public class ItemBankTest extends BaseTest {
	Login loginPage;
	DashBoard dashBoardPage;
	ItemsBank itemBankPage ;
	
	String defaultUser = "admin";
	String defaultPassword = "@simple1";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	String unityTestDataFile = "src" + File.separator + "resources"
			+ File.separator + "unitytestdata.properties";
	Properties unitymessages;
	
	Properties unitytestdata;
	
	String itemBankName;
	
	String itemBankDesc;
	
	

	
	public ItemBankTest() {
		super();
		
	}

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
	}
	
	@BeforeClass
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		dashBoardPage.addTiles();
		long timestamp = System.currentTimeMillis();
		waitTime();
		itemBankName = "Auto_IB_" + timestamp;
		itemBankDesc = "Desc" + timestamp ;
		itemBankPage = dashBoardPage.goToItemsBank();
		waitTime();
		itemBankPage.createBank(itemBankName, itemBankDesc);
		waitTime();
	}
	
	@Test(priority =1)
	public void testCreateAndPreviewItemBank(){
		itemBankPage.waitForElementAndClick(itemBankPage.resetSearchFilter);
		itemBankPage.searchItemBank(itemBankName);
		Assert.assertEquals(itemBankPage.waitAndGetElementText(itemBankPage.itemBankNameField), itemBankName);
		itemBankPage.waitForElementAndClick(itemBankPage.previewIconList);
		waitTime();
		Assert.assertEquals(itemBankPage.waitAndGetElementText(itemBankPage.itemCount), "0");
		Assert.assertEquals(itemBankPage.waitAndGetElementText(itemBankPage.mediaCount), "0");
		Assert.assertEquals(itemBankPage.waitAndGetElementText(itemBankPage.passageCount), "0");
		Assert.assertEquals(itemBankPage.waitAndGetElementText(itemBankPage.rubricCount), "0");
		itemBankPage.waitForElementAndClick(itemBankPage.previewIconList);
		waitTime();
	}
	
	
	@Test(priority = 2)
	public void testFilterItemBank(){
		itemBankPage.waitForElementAndClick(itemBankPage.resetSearchFilter);
		waitTime();
		itemBankPage.filterItemBank(itemBankName);
		Assert.assertEquals(itemBankPage.waitAndGetElementText(itemBankPage.itemBankNameField), itemBankName);
	}
	
	@Test(priority = 3)
	public void testItemBankAlertMessages(){
		itemBankPage.waitForElementAndClick(itemBankPage.resetSearchFilter);
		itemBankPage.searchItemBank(itemBankName);
		waitTime();
		/*itemBankPage.waitForElementAndClick(itemBankPage.exportIconList);
		waitTime();
		Assert.assertEquals(itemBankPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("itemBankExport").trim());
		waitTime();
		itemBankPage.waitForElementAndClick(itemBankPage.globalModalInfoOkButton);
		waitTime();*/
		itemBankPage.waitForElementAndClick(itemBankPage.deleteIconList);
		waitTime();
		waitTime();
		Assert.assertEquals(itemBankPage.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("itemBankDelete").trim());
		itemBankPage.waitForElementAndClick(itemBankPage.globalModalDeleteCancelButton1);
		waitTime();
	}
 
	@AfterClass
	public void cleanupData(){
		itemBankPage.deleteItemBank(itemBankName);
	}
	
	
}
