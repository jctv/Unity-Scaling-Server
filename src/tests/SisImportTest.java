package tests;

import java.io.File;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Login;
import pages.SisImport;
import pages.Users;
import generic.BaseTest;

public class SisImportTest extends BaseTest  {
	Login loginPage;
	DashBoard dashBoardPage;
	SisImport sisImportPage;
	Users usersPage;

	
	Properties unitytestdata;

	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator;
	String unityTestDataFile = resourcesLocation +  "unitytestdata.properties";
	
	String sisFile = resourcesLocation + "organization.zip";
	
	public SisImportTest() {
		super();
	}
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		
	}
	

	@BeforeMethod
	public void setUp() {
		System.out.println("Load Unity url - " + url);
		driver.get(url);
		loginPage = new Login(driver);
		System.out.println("******** logging as System Admin ******** ");
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainAdmin"),
				unitytestdata.getProperty("defaultAutoPassword"));
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
		sisImportPage = dashBoardPage.goToSisImport();
		waitTime();
		sisImportPage.sisImport(sisFile);
		sisImportPage.refreshPage();
		customeWaitTime(10);
	}
	
	@Test
	public void testImportedUserLogin(){
    	softAssert.assertEquals(sisImportPage.waitAndGetElementText(sisImportPage.sisFilePackageName), "organization.zip");
    	softAssert.assertEquals(sisImportPage.waitAndGetElementText(sisImportPage.sisImportedFiles), "organization");
    	softAssert.assertEquals(sisImportPage.waitAndGetElementText(sisImportPage.sisImportedFilestatus), "Completed without error");
    	loginPage = sisImportPage.logOut();
    	dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("sisimportSchooladmin"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		loginPage = dashBoardPage.logOut();
    	dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("sisimportTeacher1"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		
		loginPage = dashBoardPage.logOut();
    	dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("sisimportTeacher2"),
				unitytestdata.getProperty("genericPassword"));
		waitTime();
		loginPage = dashBoardPage.logOut();

		
	}
	
	@AfterMethod
	public void cleanUpData(){
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("testDomainAdmin"),
				unitytestdata.getProperty("defaultAutoPassword"));
		waitTime();
		dashBoardPage.addTiles();
		usersPage = dashBoardPage.goToUsers();
		usersPage.deleteUser(unitytestdata.getProperty("sisimportSchooladmin"));
		usersPage.deleteUser(unitytestdata.getProperty("sisimportTeacher1"));
		usersPage.deleteUser(unitytestdata.getProperty("sisimportTeacher2"));
		
	}

}
