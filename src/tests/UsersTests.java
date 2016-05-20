package tests;

import generic.BasePage;
import generic.BaseTest;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.DashBoard;
import pages.Login;
import pages.Users;

public class UsersTests extends BaseTest {

	Login loginPage;
	DashBoard dashBoardPage;
	Users usersPage;

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
		lastName = unityUsersData.getProperty("tOneLastName")+ System.currentTimeMillis();
		genericPassword = unityUsersData.getProperty("genericPassword");
		role = unityUsersData.getProperty("tOneName");
		// unitymessages.getProperty(key)

	}

	@BeforeClass
	public void setUp() {	
		System.out.println("Before Method");
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess("admin", "@simple1");
		customeWaitTime(5);
	}


	/**
	 * Login into the unity go to the user tile Click on Create user navigation
	 * Enter not match password and verify the alert messages,then enter valid
	 * user information validated user created message
	 * 
	 */

	@Test(priority = 1)
	public void testUserAlertMessages() {
		//this.setUp();
		usersPage = dashBoardPage.goToUsers();
		customeWaitTime(5);
		softAssert.assertEquals(usersPage.createSpecificUser(firstName,
				lastName, genericPassword, "12345789", role, organization),
				unitymessages.getProperty("userpassworddontmatch"));
		customeWaitTime(2);
		System.out.println(unitymessages
				.getProperty("usercreated")
				.replace("first_name", firstName)
				.replace("last_name", lastName)
				.replace("user_name",
						firstName.substring(0, 1).toLowerCase() + lastName));
		softAssert.assertTrue(usersPage.createSpecificUser(firstName,
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
		customeWaitTime(5);
		usersPage.searchUser(firstName.toLowerCase().substring(0, 1)
				+ lastName);
		waitTime();
		usersPage.waitForElementAndClick(usersPage.editIconList);
		waitTime();
		usersPage.waitForElementAndClick(usersPage.saveButton);
		waitTime();
		Assert.assertTrue((usersPage.globalModalInfoBody.getText().trim())
				.contains(unitymessages.getProperty("usernochange")));
		usersPage
				.waitForElementAndClick(usersPage.globalModalInfoOkButton);
		waitTime();
		usersPage.last_name.clear();
		usersPage.waitForElementAndClick(usersPage.saveButton);
		customeWaitTime(2);
		Assert.assertTrue(usersPage.globalModalInfoBody.getText().trim()
				.contains(unitymessages.getProperty("userinvaliddata")));
		usersPage
				.waitForElementAndClick(usersPage.globalModalInfoOkButton);
		waitTime();
		usersPage.waitForElementAndSendKeys(usersPage.last_name,
				lastName);
		waitTime();
		waitTime();
		usersPage.waitForElementAndClick(usersPage.userSaveButton);
		waitTime();
		waitTime();
		Assert.assertTrue(usersPage.globalModalInfoBody.getText().trim()
				.contains(unitymessages.getProperty("usersaved")));
		usersPage.waitForElementAndClick(usersPage.globalModalInfoOkButton);
		
		/*usersPage.searchUser(firstName.toLowerCase().substring(0, 1)
				+ lastName);
		waitTime();
		waitTime();*/
		customeWaitTime(5);
		usersPage.waitForElementAndClick(usersPage.deleteIconList);
		waitTime();
		waitTime();
		
		softAssert.assertTrue(usersPage.globalModalDeleteBody.getText().contains(unitymessages.getProperty("userDelete").replace("first_name", firstName).replace("last_name", lastName)));
		waitTime();

		usersPage
				.waitForElementAndClick(usersPage.globalModalDeleteButton);
		
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

	@Test(enabled = false)
	public void testUserEditingAlertMessages() {
		waitTime();
		usersPage.searchUser(firstName.toLowerCase().substring(0, 1)
				+ lastName);
		waitTime();
		usersPage.waitForElementAndClick(usersPage.editIconList);
		waitTime();
		usersPage.waitForElementAndClick(usersPage.saveButton);
		waitTime();
		Assert.assertTrue((usersPage.globalModalInfoBody.getText().trim())
				.contains(unitymessages.getProperty("usernochange")));
		usersPage
				.waitForElementAndClick(usersPage.globalModalInfoOkButton);
		waitTime();
		usersPage.last_name.clear();
		usersPage.waitForElementAndClick(usersPage.saveButton);
		customeWaitTime(2);
		Assert.assertTrue(usersPage.globalModalInfoBody.getText().trim()
				.contains(unitymessages.getProperty("userinvaliddata")));
		usersPage
				.waitForElementAndClick(usersPage.globalModalInfoOkButton);
		waitTime();
		usersPage.waitForElementAndSendKeys(usersPage.last_name,
				lastName);
		waitTime();
		waitTime();
		usersPage.waitForElementAndClick(usersPage.userSaveButton);
		waitTime();
		waitTime();
		Assert.assertTrue(usersPage.globalModalInfoBody.getText().trim()
				.contains(unitymessages.getProperty("usersaved")));
		usersPage.waitForElementAndClick(usersPage.globalModalInfoOkButton);
		
	}

	/**
	 * Login into the unity' Create user Search user Delete user Validate
	 * message "Are you certain you want to delete the user"
	 * 
	 */

	@Test(enabled = false)
	public void testUserDeleteAlertMessage() {
		usersPage.searchAutoComplete.clear();
		waitTime();
		usersPage.searchUser(firstName.toLowerCase().substring(0, 1)
				+ lastName);
		waitTime();
		waitTime();
		usersPage.waitForElementAndClick(usersPage.deleteIconList);
		waitTime();
		waitTime();
		
		softAssert.assertTrue(usersPage.globalModalDeleteBody.getText().contains(unitymessages.getProperty("userDelete").replace("first_name", firstName).replace("last_name", lastName)));
		waitTime();

		usersPage
				.waitForElementAndClick(usersPage.globalModalDeleteButton);
	}

	
	@Test(enabled =false)
	public void testFilterUserByMyUsersFilter(){
	softAssert.assertFalse(foundRecords.equals(usersPage.filterMyUsers()),"Filter by my users is not working");
	usersPage.waitForElementAndClick(usersPage.resetSearchFilter);
	}
	@Test(enabled =false)
	public void testFilterUserByFirstName(){
	softAssert.assertTrue(usersPage.filterByArgunent("firstName","Teacher" ),"Filter by my firstName is not working");
	usersPage.waitForElementAndClick(usersPage.resetSearchFilter);
	}
	@Test(enabled =false)
	public void testFilterUserBymiddleName(){
	softAssert.assertTrue(usersPage.filterByArgunent("middleName","middle" ),"Filter by my middleName is not working");
	usersPage.waitForElementAndClick(usersPage.resetSearchFilter);
	}
	@Test(enabled =false)
	public void testFilterUserByLastNameName(){
	softAssert.assertTrue(usersPage.filterByArgunent("lastName","camilo" ),"Filter by my lastName is not working");
	usersPage.waitForElementAndClick(usersPage.resetSearchFilter);
	}
	@Test(enabled =false)
	public void testFilterUserByStateId(){
	softAssert.assertTrue(usersPage.filterByArgunent("stateId","123" ),"Filter by my statedId is not working");
	usersPage.waitForElementAndClick(usersPage.resetSearchFilter);
	}
	
	@Test(enabled =false)
	public void testFilterUserByGrade(){
	softAssert.assertTrue(usersPage.filterByCheck("grade","05" ),"Filter by my grade is not working");
	usersPage.waitForElementAndClick(usersPage.resetSearchFilter);
	}
	@Test(enabled =false)
	public void testFilterUserByRole(){
	softAssert.assertTrue(usersPage.filterByCheck("role","Teacher" ),"Filter by my role is not working");
	usersPage.waitForElementAndClick(usersPage.resetSearchFilter);
	}
}
