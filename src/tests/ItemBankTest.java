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
import generic.BaseTest;

public class ItemBankTest extends BaseTest {
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	ItemsBank itemBankPageObject ;
	
	String defaultUser = "admin";
	String defaultPassword = "@simple1";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	Properties unitymessages;
	
	public ItemBankTest() {
		super();
		
	}

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		
	}
	
	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPageObject = new Login(driver);
		dashBoardPageObject = loginPageObject.loginSuccess(defaultUser,
				defaultPassword);
		waitTime();
		//dashBoardPageObject.addTiles();
		waitTime();
	}
	
	
	@Test
	public void testItemBankAlertMessages(){
		String itemBankName = "Auto_IB_" + + System.currentTimeMillis();
		String description = "Desc" ;
		itemBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		waitTime();
		itemBankPageObject.createBank(itemBankName, description);
		waitTime();
		itemBankPageObject = dashBoardPageObject.goToItemsBank();
		waitTime();
		waitTime();
		itemBankPageObject.searchItemBank(itemBankName);
		waitTime();
		/*itemBankPageObject.waitForElementAndClick(itemBankPageObject.exportIconList);
		waitTime();
		Assert.assertEquals(itemBankPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("itemBankExport").trim());
		waitTime();
		itemBankPageObject.waitForElementAndClick(itemBankPageObject.globalModalInfoOkButton);
		waitTime();*/
		itemBankPageObject.waitForElementAndClick(itemBankPageObject.deleteIconList);
		waitTime();
		waitTime();
		Assert.assertEquals(itemBankPageObject.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("itemBankDelete").trim());
		itemBankPageObject.waitForElementAndClick(itemBankPageObject.globalModalDeleteButton);
		waitTime();

	}
	
}
