package tests;

import java.io.File;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import pages.Accommodation;
import pages.DashBoard;
import pages.Login;
import pages.SisImport;
import pages.Users;
import generic.BaseTest;

public class AccommodationTest extends BaseTest{
	
	Login loginPage;
	DashBoard dashBoardPage;
	Accommodation accommodationPage;
	SisImport sisImportPage;
	Users usersPage;

	public AccommodationTest() {
		super();
		
	}

	Properties unitytestdata;
	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator;
	String accommodation = "accommodation" + File.separator;
	
	String unityTestDataFile = resourcesLocation  + "unitytestdata.properties";
	
	String orgnizationFile= "organization.zip";
	
	String importUserFileLocation;

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		importUserFileLocation =  resourcesLocation + accommodation + orgnizationFile;
		

	}
	
	@BeforeClass
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		dashBoardPage.addTiles();
		waitTime();
		sisImportPage = dashBoardPage.goToSisImport();
		waitTime();
		sisImportPage.sisImport(importUserFileLocation);
		sisImportPage.refreshPage();
		sisImportPage.backToDashboard();
		accommodationPage = dashBoardPage.goToAccomadation();
		
	}
	
	
}
