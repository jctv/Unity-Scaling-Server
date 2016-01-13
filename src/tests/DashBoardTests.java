package tests;

import java.io.File;
import java.util.Properties;

import generic.BaseTest;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Login;

public class DashBoardTests extends BaseTest {

	Login loginPage;
	DashBoard dashBoardPage;

	String unityTestDataFile = "src" + File.separator + "resources"
			+ File.separator + "unitytestdata.properties";
	
	Properties unitytestdata;
	public DashBoardTests() {
		super();
	}

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		
	}
	
	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
	}

	@Test(priority = 1 )
	public void testAddQuickLinksForSystemAdmin() {
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		dashBoardPage.addTiles();
		dashBoardPage.waitForElementAndClick(dashBoardPage.addTile);
		dashBoardPage.addQuickLinkToTiles();
	}
	
	@Test(priority = 2)
	public void testAddQuickLinksForSchoolAdmin() {
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("schooladmin1"),
				unitytestdata.getProperty("genericPassword"));
		dashBoardPage.addTiles();
		dashBoardPage.waitForElementAndClick(dashBoardPage.addTile);
		dashBoardPage.addQuickLinkToTiles();
	}
	
	@Test(priority = 3)
	public void testAddQuickLinksForTeacher() {
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("teacher1"),
				unitytestdata.getProperty("genericPassword"));
		dashBoardPage.addTiles();
		dashBoardPage.waitForElementAndClick(dashBoardPage.addTile);
		dashBoardPage.addQuickLinkToTiles();
	}
	
	@Test(priority = 4)
	public void testAddQuickLinksForStudent() {
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("stduent1"),
				unitytestdata.getProperty("genericPassword"));
		dashBoardPage.addTiles();
		dashBoardPage.waitForElementAndClick(dashBoardPage.addTile);
		dashBoardPage.addQuickLinkToTiles();
	}
}
