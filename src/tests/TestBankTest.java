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
import pages.Login;
import pages.TestsBank;
import generic.BaseTest;

public class TestBankTest extends BaseTest {
	
	Login loginPage;
	DashBoard dashBoardPage;
	TestsBank testBankPage;
	
	String defaultUser = "admin";
	String defaultPassword = "@simple1";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	String unityTestDataFile = "src" + File.separator + "resources"
			+ File.separator + "unitytestdata.properties";
	
	Properties unitymessages;
	Properties unitytestdata;
	
	String testBankName;
	
	public TestBankTest() {
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
		testBankName = "Auto_TB_" + timestamp;
		String description = "Desc" ;
		testBankPage = dashBoardPage.goToTestsBank();
		waitTime();
		testBankPage.createBank(testBankName, description);
		waitTime();
	}

	@Test(priority =1)
	public void testCreateTestBank(){
		testBankPage.waitForElementAndClick(testBankPage.resetSearchFilter);
		testBankPage.searchTestBank(testBankName);
		Assert.assertEquals(testBankPage.waitAndGetElementText(testBankPage.testBankNameField), testBankPage);
	}
	
	@Test(priority = 2)
	public void testFilterItemBank(){
		testBankPage.waitForElementAndClick(testBankPage.resetSearchFilter);
		waitTime();
		testBankPage.filterTestBank(testBankName);
		Assert.assertEquals(testBankPage.waitAndGetElementText(testBankPage.testBankNameField), testBankName);
	}
	
	@Test(priority =3)
	public void testBankAlertMessages(){
		testBankPage.waitForElementAndClick(testBankPage.resetSearchFilter);
		testBankPage.searchTestBank(testBankName);
		waitTime();
		testBankPage.waitForElementAndClick(testBankPage.deleteIconList);
		waitTime();
		Assert.assertEquals(testBankPage.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("testBankDelete").trim());
		testBankPage.waitForElementAndClick(testBankPage.globalModalDeleteCancelButton1);
		waitTime();
	}
	
	@AfterClass
	public void cleanupData(){
		testBankPage.deleteTestBank(testBankName);
	}
	
}
