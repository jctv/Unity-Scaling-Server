package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Accommodation;
import pages.DashBoard;
import pages.Login;
import pages.Role;
import pages.SisImport;
import pages.Users;
import generic.BaseTest;

public class AccommodationTest extends BaseTest{
	
	Login loginPage;
	DashBoard dashBoardPage;
	Accommodation accommodationPage;
	SisImport sisImportPage;
	Users usersPage;
	Role rolePage;

	public AccommodationTest() {
		super();
		
	}

	Properties unitytestdata;
	Properties unityuserdata;

	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator;
	
	String accommodation = "accommodation" + File.separator;
	
	String unityTestDataFile = resourcesLocation  + "unitytestdata.properties";
	
	String unityUserDataFile = resourcesLocation  + "unityUsersData.properties";

	
	String orgnizationFile= "organization.zip";
	
	String importUserFileLocation;
	
	String manualStudentF1;
	String manualStudentF2;
	
	String manualStudentL1;
	String manualStudentL2;
	
	String schoolName;
	
	String student = "Student";
	
	String genericPassword;
	
	String importStdudentL1;
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		unityuserdata = getUnityMessagesProperty(unityUserDataFile);
		importUserFileLocation =  resourcesLocation + accommodation + orgnizationFile;
		manualStudentF1 = unityuserdata.getProperty("ackfirstname");
		manualStudentL1 = unityuserdata.getProperty("acklastname") + System.currentTimeMillis();
		manualStudentF2 = unityuserdata.getProperty("ackfirstname1");
		manualStudentL2 = unityuserdata.getProperty("acklastname2") + System.currentTimeMillis();
		schoolName = unityuserdata.getProperty("ackSchooName");
		importStdudentL1 = unityuserdata.getProperty("importStdudentL1");
		genericPassword = unitytestdata.getProperty("genericPassword");
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
		usersPage = dashBoardPage.goToUsers();
		usersPage.createSpecificUser(manualStudentF1, manualStudentL1, genericPassword, genericPassword, student, schoolName);
		waitTime();
		usersPage.editStudentAccommodation(manualStudentL1, true);
		usersPage.createSpecificUser(manualStudentF2, manualStudentL2, genericPassword, genericPassword, student, schoolName);
		accommodationPage = dashBoardPage.goToAccomadation();
		
	}
	
	@Test(priority = 1)
	public void testverifyAccommodationForImportedUser(){
		softAssert.assertTrue(accommodationPage.searchStudent(importStdudentL1));
		accommodationPage.waitForAnElementAndClick(accommodationPage.editIconList);
		softAssert.assertTrue(accommodationPage.waitAndGetElementText(accommodationPage.studentNameInfo).contains(importStdudentL1));
		accommodationPage.waitForAnElementAndClick(accommodationPage.accommodationCloseButton);

	}
	
	@Test(priority = 2)
	public void testverifyAccommodationForManualUser(){
		softAssert.assertTrue(accommodationPage.searchStudent(manualStudentL1));
		accommodationPage.waitForAnElementAndClick(accommodationPage.editIconList);
		softAssert.assertTrue(accommodationPage.waitAndGetElementText(accommodationPage.studentNameInfo).contains(manualStudentL1));
		softAssert.assertTrue(accommodationPage.waitAndGetElementText(accommodationPage.studentNameInfo).contains(manualStudentF1));
		accommodationPage.waitForAnElementAndClick(accommodationPage.accommodationCloseButton);

	}
	
	@Test(priority = 3)
	public void testverifyAccommodationTilePermission(){
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		rolePage = dashBoardPage.goToRole();
		//Assert.assertEquals(false, rolePage.getTilePermission("Accommodations", "Teacher").get("Create"));
		;
	}
}
