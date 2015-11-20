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
		String orgStateName = "Auto_State" + System.currentTimeMillis();
		String orgDistName = "Dist" + orgStateName;
		String orgSchoolName = "School" + orgStateName;
		orgPage.addOrganization(orgStateName, "", "", TYPE_STATE);
		waitTime();
		Assert.assertEquals(orgPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("orgCreated").replace("org_name", orgStateName).trim());
		waitTime();
		orgPage.waitForElementAndClick(orgPage.globalModalInfoOkButton);
		waitTime();
		orgPage.refreshPage();
		waitTime();
		waitTime();
		orgPage.addOrganization(orgStateName, orgDistName, "", TYPE_DISTRICT);
		Assert.assertEquals(orgPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("orgCreated").replace("org_name", orgDistName).trim());
		waitTime();
		orgPage.waitForElementAndClick(orgPage.globalModalInfoOkButton);
		waitTime();
		orgPage.refreshPage();
		waitTime();
		waitTime();
		orgPage.addOrganization(orgStateName, orgDistName, orgSchoolName, TYPE_SCHOOL);
		Assert.assertEquals(orgPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("orgCreated").replace("org_name", orgSchoolName).trim());
		waitTime();
		orgPage.waitForElementAndClick(orgPage.globalModalInfoOkButton);
		waitTime();
		orgPage.refreshPage();
		waitTime();
		waitTime();
		orgPage.deleteOrganization(orgStateName, orgDistName, orgSchoolName, TYPE_SCHOOL);
		waitTime();
		Assert.assertEquals(orgPage.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("emptyOrgDelete").trim());
		waitTime();
		orgPage.waitForElementAndClick(orgPage.globalModalOKCancelSaveButton);
		waitTime();
		orgPage.refreshPage();
		waitTime();
		waitTime();
		
		orgPage.deleteOrganization(orgStateName, orgDistName, "", TYPE_DISTRICT);
		waitTime();
		Assert.assertEquals(orgPage.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("emptyOrgDelete").trim());
		waitTime();
		orgPage.waitForElementAndClick(orgPage.globalModalOKCancelSaveButton);
		waitTime();
		orgPage.refreshPage();
		waitTime();
		waitTime();
		orgPage.deleteOrganization(orgStateName, "", "", TYPE_STATE);
		waitTime();
		Assert.assertEquals(orgPage.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("emptyOrgDelete").trim());
		waitTime();
		orgPage.waitForElementAndClick(orgPage.globalModalOKCancelSaveButton);
		waitTime();
		
	}

}
