package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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
	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator;
	
	String unityMessageFile = resourcesLocation + "unitymessages.properties";
	String unityTestDataFile = resourcesLocation  + "unitytestdata.properties";
	String unityOrgDataFile = resourcesLocation  + "unityorganizationdata.properties";

    String  stateType;
    String distType;
    String schoolType;
	
	Properties unitymessagesdata;
	Properties unitytestdata;
	Properties unityorgdata;
	
	long timestamp;
	
	String orgStateName;
	String orgDistName;
	String orgSchoolName;

	public OrganizationTest() {
		super();
		
	}

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessagesdata = getUnityMessagesProperty(unityMessageFile);
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		unityorgdata = getUnityMessagesProperty(unityOrgDataFile);
		stateType = unityorgdata.getProperty("stateType");
		distType = unityorgdata.getProperty("distType");
		schoolType = unityorgdata.getProperty("schoolType");
		
		timestamp = System.currentTimeMillis();
		
		orgStateName = unityorgdata.getProperty("orgState") + timestamp;
		orgDistName = unityorgdata.getProperty("orgDist") + timestamp;
		orgSchoolName = unityorgdata.getProperty("orgSchool") + timestamp;

		
	}
	
	@BeforeClass
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		customeWaitTime(2);
		dashBoardPage.addTiles();
		customeWaitTime(2);
		orgPage = dashBoardPage.goToOrganization();
		customeWaitTime(10);

	}
	
	
	/**
	 * Login into the unity as admin
	 * Go the org tile
	 * Click on Create Hierarchy 
	 * Create org structure state > Dist > School
	 * 
	 */
	
	@Test(priority = 1)
	public void testCreateAndDeleteOrgHierarchy(){
		orgPage.addOrganization(orgStateName, "", "", stateType);
		customeWaitTime(2);
		orgPage.refreshPage();
		customeWaitTime(5);
		orgPage.addOrganization(orgStateName, orgDistName, "", distType);
		customeWaitTime(2);
		orgPage.refreshPage();
		customeWaitTime(5);
		orgPage.addOrganization(orgStateName, orgDistName, orgSchoolName, schoolType);
		customeWaitTime(2);
		orgPage.refreshPage();
		customeWaitTime(5);
		Assert.assertEquals(orgStateName, orgPage.getOrgNameInTree(orgStateName ,"", "" , stateType));
		customeWaitTime(5);
		Assert.assertEquals(orgStateName, orgPage.getOrgNameInTree(orgStateName ,orgDistName, "" , distType));
		customeWaitTime(5);
		Assert.assertEquals(orgStateName, orgPage.getOrgNameInTree(orgStateName ,orgDistName, orgSchoolName, schoolType));
		customeWaitTime(5);
		orgPage.deleteOrganization(orgStateName, orgDistName, orgSchoolName, schoolType);
		customeWaitTime(5);
		orgPage.deleteOrganization(orgStateName, orgDistName, "", distType);
		customeWaitTime(5);
		orgPage.deleteOrganization(orgStateName, "", "", stateType);
	}
	

	/**
	 * Login into the Unity
	 * Click on Organization tile
	 * Create Organization 
	 * Validate message
	 * 
	 */
	
	@Test(enabled = false)
	public void testOrganizationAlertMessages(){
		orgPage = dashBoardPage.goToOrganization();
		waitTime();
		waitTime();
		String orgStateName = "Auto_State" + System.currentTimeMillis();
		String orgDistName = "Dist" + orgStateName;
		String orgSchoolName = "School" + orgStateName;
		orgPage.addOrganization(orgStateName, "", "", stateType);
		waitTime();
		Assert.assertEquals(orgPage.globalModalInfoBody.getText().trim(), unitymessagesdata.getProperty("orgCreated").replace("org_name", orgStateName).trim());
		waitTime();
		orgPage.waitForElementAndClick(orgPage.globalModalInfoOkButton);
		waitTime();
		orgPage.refreshPage();
		waitTime();
		waitTime();
		orgPage.addOrganization(orgStateName, orgDistName, "", distType);
		Assert.assertEquals(orgPage.globalModalInfoBody.getText().trim(), unitymessagesdata.getProperty("orgCreated").replace("org_name", orgDistName).trim());
		waitTime();
		orgPage.waitForElementAndClick(orgPage.globalModalInfoOkButton);
		waitTime();
		orgPage.refreshPage();
		waitTime();
		waitTime();
		orgPage.addOrganization(orgStateName, orgDistName, orgSchoolName, schoolType);
		Assert.assertEquals(orgPage.globalModalInfoBody.getText().trim(), unitymessagesdata.getProperty("orgCreated").replace("org_name", orgSchoolName).trim());
		waitTime();
		orgPage.waitForElementAndClick(orgPage.globalModalInfoOkButton);
		waitTime();
		orgPage.refreshPage();
		waitTime();
		waitTime();
		orgPage.deleteOrganization(orgStateName, orgDistName, orgSchoolName, schoolType);
		waitTime();
		Assert.assertEquals(orgPage.globalModalOKCancelBody.getText().trim(), unitymessagesdata.getProperty("emptyOrgDelete").trim());
		waitTime();
		orgPage.waitForElementAndClick(orgPage.globalModalOKCancelSaveButton);
		waitTime();
		orgPage.refreshPage();
		waitTime();
		waitTime();
		
		orgPage.deleteOrganization(orgStateName, orgDistName, "", distType);
		waitTime();
		Assert.assertEquals(orgPage.globalModalOKCancelBody.getText().trim(), unitymessagesdata.getProperty("emptyOrgDelete").trim());
		waitTime();
		orgPage.waitForElementAndClick(orgPage.globalModalOKCancelSaveButton);
		waitTime();
		orgPage.refreshPage();
		waitTime();
		waitTime();
		orgPage.deleteOrganization(orgStateName, "", "", stateType);
		waitTime();
		Assert.assertEquals(orgPage.globalModalOKCancelBody.getText().trim(), unitymessagesdata.getProperty("emptyOrgDelete").trim());
		waitTime();
		orgPage.waitForElementAndClick(orgPage.globalModalOKCancelSaveButton);
		waitTime();
		
	}

}
