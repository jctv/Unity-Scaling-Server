package tests;

import generic.BaseTest;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Login;
import pages.Users;

public class UsersTests extends BaseTest {

	Login loginPageObject;
	DashBoard dashBoardPageObject;
	Users usersPageObject;
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	Properties unitymessages;
	
	String password = "12345";
	String passwordNotMatch = "a" + password; 
	

	public UsersTests() {
		super();
		
	}
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		//unitymessages.getProperty(key)
		
	}

	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPageObject = new Login(driver);
		dashBoardPageObject = loginPageObject.loginSuccess("admin",
				"@simple1");
		waitTime();
		//dashBoardPageObject.addTiles();
		waitTime();
	}
	
	@Test
	public void massiveUserCreation(){
		
		usersPageObject = dashBoardPageObject.goToUsers();
		waitTime();
		int users = 30;
		
		String[] usersArray = new String[30];

		String firstName = "Student";
		String organization = "Music";
		
		for(int x=0;x<users;x++){
			Date date = new Date();
			String lastName = date.toString().substring(8, 16)
					.replace(" ", "_").replace(":", "_");
			lastName = lastName + x;
			System.out.println("-------------------------------------------");
		usersArray[x] = usersPageObject.createSpecificUser(firstName, lastName, password ,firstName, organization);
		}
		System.out.println("Users creation done");
		
		for (String string : usersArray) {
			System.out.println(string+",12345");
		
		}
		System.out.println("Users creation done");
		
	}
	
	/**
	 * Login into the unity 
	 * go to the user tile
	 * Click on Create user navigation
	 * Enter not match password and verify the alert messages
	 * enter valid user information 
	 * validated user created message
	 * 
	 */
	
	@Test
	public void testUserCreationAlertMessages(){
		String firstName = "Student";
		String organization = "Auto School";
		String lastName = "lastname" + System.currentTimeMillis();
		usersPageObject = dashBoardPageObject.goToUsers();
		waitTime();
		waitTime();
		usersPageObject.waitForElementAndClick(usersPageObject.createUserLink);
		waitTime();
		waitTime();
		usersPageObject.enterUserInformation(firstName, lastName, password, passwordNotMatch ,firstName, organization);
		waitTime();
		waitTime();
		usersPageObject.waitForElementAndClick(usersPageObject.createUserLink);
		waitTime();
		Assert.assertEquals(usersPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("userpassworddontmatch"));
		usersPageObject.globalModalInfoOkButton.click();
		waitTime();
		usersPageObject.enterUserInformation(firstName, lastName, password, password ,firstName, organization);
		waitTime();
		waitTime();
		Assert.assertEquals(usersPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("usercreated").replace("first_name", firstName).replace("last_name", lastName).replace("user_name", firstName.substring(0, 1).toLowerCase()+ lastName));
		usersPageObject.globalModalInfoOkButton.click();
	}
}