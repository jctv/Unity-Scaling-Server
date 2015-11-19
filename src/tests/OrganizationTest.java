package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Login;
import pages.Organization;
import generic.BaseTest;

public class OrganizationTest extends BaseTest{
	
	Login loginPage;
	DashBoard dashBoardPage;
	Organization orgPage;
	
	String defaultUser = "qa/admin";
	String defaultPassword = "password";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
    private  static final String  TYPE_STATE   = "state";
    private  static final String  TYPE_DISTRICT   = "district";
    private  static final String  TYPE_SCHOOL   = "school";

	
	Properties unitymessages;
	
	public OrganizationTest() {
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
	
	/**
	 * Login into the Unity
	 * Click on Organization tile
	 * Create Organization 
	 * Validate message
	 * 
	 */
	
	@Test
	public void testOrganizationAlertMessages(){
		orgPage = dashBoardPage.goToOrganization();
		waitTime();
		waitTime();
		orgPage.waitForElementAndClick(orgPage.createNewOrganization);
		waitTime();
		waitTime();
		String orgStateName = "Auto_State" + System.currentTimeMillis();
		orgPage.waitForElementAndSendKeys(orgPage.tName, orgStateName);
		waitTime();
		orgPage.selectOption(orgPage.tType, TYPE_STATE);
		waitTime();
		orgPage.waitForElementAndClick(orgPage.createHerarchy);
		waitTime();
		waitTime();
		Assert.assertEquals(orgPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("orgCreated").replace("org_name", orgStateName).trim());
		orgPage.waitForElementAndClick(orgPage.globalModalInfoOkButton);

	}

}
