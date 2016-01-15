package tests;

import generic.BaseTest;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.DashBoard;
import pages.Login;
import pages.Users;

public class UsersTests extends BaseTest {

	Login loginPageObject;
	DashBoard dashBoardPageObject;
	Users usersPageObject;

	Properties unitymessages;
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";

	Properties unityUsersData = null;
	String unityUsersDataFile = "src" + File.separator + "resources"
			+ File.separator + "unityUsersData.properties";
	String firstName;
	String organization;
	String lastName;
	String foundRecords;

	String genericPassword;
	String role;
	String passwordNotMatch = "a" + password;


	public UsersTests() {
		super();
		 
	}

	@BeforeTest
	public void loadUnityMessagesProperty() {
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		unityUsersData = getUnityMessagesProperty(unityUsersDataFile);
		firstName = unityUsersData.getProperty("tOneName");
		organization = unityUsersData.getProperty("school");
		lastName = unityUsersData.getProperty("tOneLastName");
		genericPassword = unityUsersData.getProperty("genericPassword");
		role = unityUsersData.getProperty("tOneName");
		// unitymessages.getProperty(key)

	}

	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPageObject = new Login(driver);
		dashBoardPageObject = loginPageObject.loginSuccess("admin", "@simple1");
		waitTime();
		// dashBoardPageObject.addTiles();
		waitTime();
	}

	/**
	 * Login into the unity go to the user tile Click on Create user navigation
	 * Enter not match password and verify the alert messages,then enter valid
	 * user information validated user created message
	 * 
	 */

	@Test(priority = 1)
	public void testUserCreationAlertMessages() {

		usersPageObject = dashBoardPageObject.goToUsers();
		waitTime();
		
		softAssert.assertEquals(usersPageObject.createSpecificUser(firstName,
				lastName, genericPassword, "12345789", role, organization),
				unitymessages.getProperty("userpassworddontmatch"));
		customeWaitTime(2);
		System.out.println(unitymessages
				.getProperty("usercreated")
				.replace("first_name", firstName)
				.replace("last_name", lastName)
				.replace("user_name",
						firstName.substring(0, 1).toLowerCase() + lastName));
		softAssert.assertTrue(usersPageObject.createSpecificUser(firstName,
				lastName, genericPassword, genericPassword, role, organization)
				.equalsIgnoreCase(
						unitymessages
								.getProperty("usercreated")
								.replace("first_name", firstName)
								.replace("last_name", lastName)
								.replace(
										"user_name",
										firstName.substring(0, 1).toLowerCase()
												+ lastName)));

	}

	/**
	 * Login into the unity go to the user tile Click on Create user navigation
	 * Search User Edit the user Click on save Validate message
	 * "User has not changed. No save needed" enter invalid user information
	 * Validate the message
	 * "User has invalid data, please correct before saving" enter the valid
	 * first name save the user information Validate message "User Saved!"
	 * 
	 */

	@Test(priority = 2)
	public void testUserEditingAlertMessages() {
		waitTime();
		usersPageObject.searchUser(firstName.toLowerCase().substring(0, 1)
				+ lastName);
		waitTime();
		usersPageObject.waitForElementAndClick(usersPageObject.editIconList);
		waitTime();
		usersPageObject.waitForElementAndClick(usersPageObject.saveButton);
		waitTime();
		Assert.assertTrue((usersPageObject.globalModalInfoBody.getText().trim())
				.equalsIgnoreCase(unitymessages.getProperty("usernochange")));
		usersPageObject
				.waitForElementAndClick(usersPageObject.globalModalInfoOkButton);
		waitTime();
		usersPageObject.last_name.clear();
		usersPageObject.waitForElementAndClick(usersPageObject.saveButton);
		customeWaitTime(2);
		Assert.assertTrue(usersPageObject.globalModalInfoBody.getText().trim()
				.equalsIgnoreCase(unitymessages.getProperty("userinvaliddata")));
		usersPageObject
				.waitForElementAndClick(usersPageObject.globalModalInfoOkButton);
		waitTime();
		usersPageObject.waitForElementAndSendKeys(usersPageObject.last_name,
				lastName);
		waitTime();
		waitTime();
		usersPageObject.waitForElementAndClick(usersPageObject.saveButton);
		waitTime();
		waitTime();
		Assert.assertTrue(usersPageObject.globalModalInfoBody.getText().trim()
				.equalsIgnoreCase(unitymessages.getProperty("usersaved")));

	}

	/**
	 * Login into the unity' Create user Search user Delete user Validate
	 * message "Are you certain you want to delete the user"
	 * 
	 */

	@Test(priority = 3)
	public void testUserDeleteAlertMessage() {
		usersPageObject.searchAutoComplete.clear();
		waitTime();
		usersPageObject.searchUser(firstName.toLowerCase().substring(0, 1)
				+ lastName);
		waitTime();
		waitTime();
		usersPageObject.waitForElementAndClick(usersPageObject.deleteIconList);
		waitTime();
		waitTime();
		Assert.assertEquals(
				usersPageObject.globalModalDeleteBody.getText().trim(),
				unitymessages.getProperty("userDelete")
						.replace("first_name", firstName)
						.replace("last_name", lastName));
		waitTime();

		usersPageObject
				.waitForElementAndClick(usersPageObject.globalModalDeleteButton);
	}

	
	@Test(priority =4)
	public void testFilterUserByMyUsersFilter(){
	softAssert.assertFalse(foundRecords.equals(usersPageObject.filterMyUsers()),"Filter by my users is not working");
	usersPageObject.waitForElementAndClick(usersPageObject.resetSearchFilter);
	}
	@Test(priority =5)
	public void testFilterUserByFirstName(){
	softAssert.assertTrue(usersPageObject.filterByArgunent("firstName","Teacher" ),"Filter by my firstName is not working");
	usersPageObject.waitForElementAndClick(usersPageObject.resetSearchFilter);
	}
	@Test(priority =6)
	public void testFilterUserBymiddleName(){
	softAssert.assertTrue(usersPageObject.filterByArgunent("middleName","middle" ),"Filter by my middleName is not working");
	usersPageObject.waitForElementAndClick(usersPageObject.resetSearchFilter);
	}
	@Test(priority =7)
	public void testFilterUserByLastNameName(){
	softAssert.assertTrue(usersPageObject.filterByArgunent("lastName","camilo" ),"Filter by my lastName is not working");
	usersPageObject.waitForElementAndClick(usersPageObject.resetSearchFilter);
	}
	@Test(priority =8)
	public void testFilterUserByStateId(){
	softAssert.assertTrue(usersPageObject.filterByArgunent("stateId","123" ),"Filter by my statedId is not working");
	usersPageObject.waitForElementAndClick(usersPageObject.resetSearchFilter);
	}
	
	@Test(priority =9)
	public void testFilterUserByGrade(){
	softAssert.assertTrue(usersPageObject.filterByCheck("grade","05" ),"Filter by my grade is not working");
	usersPageObject.waitForElementAndClick(usersPageObject.resetSearchFilter);
	}
	@Test(priority =10)
	public void testFilterUserByRole(){
	softAssert.assertTrue(usersPageObject.filterByCheck("role","Teacher" ),"Filter by my role is not working");
	usersPageObject.waitForElementAndClick(usersPageObject.resetSearchFilter);
	}
}
