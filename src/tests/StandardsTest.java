package tests;

import java.io.File;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.DashBoard;
import pages.Login;
import pages.Standards;
import generic.BaseTest;

public class StandardsTest extends BaseTest{
	
	Login loginPage;
	DashBoard dashBoardPage;
	Standards standardsPage;
	
	Properties unitytestdata;
	Properties unitystandarddata;
	
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator;
	
	String help = "help" + File.separator;
	
	String unityTestDataFile = resourcesLocation  + "unitytestdata.properties";
	String unityStandardDataFile = resourcesLocation + "unitystandarddata.properties";
	
	
	public StandardsTest() {
		super();
	}

	private SoftAssert softAssert = new SoftAssert();

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		unitystandarddata = getUnityMessagesProperty(unityStandardDataFile);
	}
	
	@BeforeClass
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		dashBoardPage.addTiles();
		customeWaitTime(5);
		standardsPage = dashBoardPage.goToStandards();
	}
	
	@Test
	public void testViewInstructionalResources(){
		standardsPage.openViewInstructionalResources();
		customeWaitTime(2);
		standardsPage.selectOption(standardsPage.selectResource, "Game");
		customeWaitTime(5);
		standardsPage.verifyAllResources();
        }	
}
