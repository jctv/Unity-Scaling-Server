package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Login;
import pages.Role;
import generic.BaseTest;

public class RoleTest extends BaseTest{
	
	Login loginPage;
	DashBoard dashBoardPage;
	Role rolePage;
	
	String defaultUser = "admin";
	String defaultPassword = "@simple1";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	Properties unitymessages;
	
	public RoleTest() {
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
		dashBoardPage.addTiles();
		waitTime();
		waitTime();
	}

	@Test
	public void testRoleAlertMessages(){
		String roleName = "Auto"+ System.currentTimeMillis();
		rolePage = dashBoardPage.goToRole();
		waitTime();
		waitTime();
		rolePage.createRole(roleName);
		waitTime();
		waitTime();
		Assert.assertEquals(rolePage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("roleCreated").replace("role_name", roleName).trim());
		rolePage.waitForElementAndClick(rolePage.globalModalInfoOkButton);
		waitTime();
		waitTime();
		rolePage.deleteRole(roleName);
		waitTime();
		waitTime();
		Assert.assertEquals(rolePage.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("roleDeleted").replace("role_name", roleName).trim());
		rolePage.waitForElementAndClick(rolePage.globalModalDeleteButton);
		waitTime();
		waitTime();

	}

}
