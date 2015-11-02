package tests;

import generic.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Login;
import pages.MachineProfile;

public class MachineProfileTest extends BaseTest{
	
	
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	String loggedUser="admin";
	MachineProfile machineProfilePageObject;

	
	public  MachineProfileTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		System.out.println("Load Unity url - " + url);
		driver.get(url);
		loginPageObject = new Login(driver);
		System.out.println("******** logging as Scool Admin -- " + loggedUser  + "******** " );
		dashBoardPageObject = loginPageObject.loginSuccess(loggedUser, "@simple1");
		waitTime();
		dashBoardPageObject.addTiles();
		waitTime();
		
		
	}
	
	@Test(priority=1)
	public void createProfile(){
		machineProfilePageObject = dashBoardPageObject.goMachineProfile();
		waitTime();
		Assert.assertTrue((machineProfilePageObject.createProfile("Chrome", "Delivery", "Web", "100x100", "Windows 7", "Chrome", "40").equals("Chrome")), "Profile Created correctly");	

		
	}
	
	@Test(priority=2)
	public void editProfile(){
		machineProfilePageObject = dashBoardPageObject.goMachineProfile();
		waitTime();
		Assert.assertTrue((machineProfilePageObject.editProfile("Chrome", "Delivery", "Web", "100x100", "Chrome", "40").equals("Chrome")), "Profile edited correctly");	
	}
	
	
	
	@Test(priority=3)
	public void deleteProfile(){
		machineProfilePageObject = dashBoardPageObject.goMachineProfile();
		waitTime();
		Assert.assertTrue(machineProfilePageObject.deleteProfile("Chrome").contains("deleted"),"Profile deleted correctly");
		
	}
	

	
	@Test(priority=4)
	public void createAndTestProfileCWin7(){
		machineProfilePageObject = dashBoardPageObject.goMachineProfile();
		waitTime();
		Assert.assertTrue((machineProfilePageObject.createProfile("Chrome", "Delivery", "Web", "100x100", "Windows 7", "Chrome", "40").equals("Chrome")), "Profile Created correctly");	
		dashBoardPageObject.logOut();
		loginPageObject.loginSuccess("iiris", "12345");		
		Assert.assertTrue(dashBoardPageObject.goToTile("Delivery"));
		
		
	}
	
	@Test(priority=5)
	public void createAndTestProfileIpad(){
		machineProfilePageObject = dashBoardPageObject.goMachineProfile();
		waitTime();
		Assert.assertTrue((machineProfilePageObject.createProfile("AppleIpad", "Delivery", "Web", "768x1024", "Mac OS", "Safari", "9537").equals("AppleIpad")), "Profile Created correctly");	
		dashBoardPageObject.logOut();
		driver.quit();
		WebDriver driver = emulateDevice("Apple Ipad");
		driver.get(url); 
		loginPageObject.loginSuccess("iiris", "12345");		
		Assert.assertTrue(dashBoardPageObject.goToTile("Delivery"));
		
		
	}
	
	@Test(priority=6)
	public void createAndTestProfileFWin8(){
		machineProfilePageObject = dashBoardPageObject.goMachineProfile();
		waitTime();
		Assert.assertTrue((machineProfilePageObject.createProfile("Firefox", "Delivery", "Web", "768x1024", "Windows 8", "Firefox", "41").equals("Firefox")), "Profile Created correctly");	
		dashBoardPageObject.logOut();
		driver.quit();
		WebDriver driver = new FirefoxDriver();
		driver.get(url); 
		loginPageObject.loginSuccess("iiris", "12345");		
		Assert.assertTrue(dashBoardPageObject.goToTile("Delivery"));
		driver.quit();	
	}
	@Test(priority=7)
	public void createAndTestProfileFWin7(){
		machineProfilePageObject = dashBoardPageObject.goMachineProfile();
		waitTime();
		Assert.assertTrue((machineProfilePageObject.createProfile("Firefox", "Delivery", "Web", "768x1024", "Windows 7", "Firefox", "41").equals("Firefox")), "Profile Created correctly");	
		dashBoardPageObject.logOut();
		driver.quit();
		WebDriver driver = new FirefoxDriver();
		driver.get(url); 
		loginPageObject.loginSuccess("iiris", "12345");		
		Assert.assertTrue(dashBoardPageObject.goToTile("Delivery"));
		driver.quit();
		
	}
	@Test(priority=8)
	public void createAndTestProfileNexus7(){
		machineProfilePageObject = dashBoardPageObject.goMachineProfile();
		waitTime();
		Assert.assertTrue((machineProfilePageObject.createProfile("Google Nexus7", "Delivery", "Web", "768x1024", "Linux", "Chrome", "42.0").equals("Google Nexus7")), "Profile Created correctly");	
		dashBoardPageObject.logOut();
		driver.quit();
		WebDriver driver = emulateDevice("Google Nexus 7");
		driver.get(url); 
		loginPageObject.loginSuccess("iiris", "12345");		
		Assert.assertTrue(dashBoardPageObject.goToTile("Delivery"));
		
		
	}
	

	
}
