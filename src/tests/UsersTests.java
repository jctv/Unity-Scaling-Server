package tests;

import generic.BaseTest;

import java.util.Date;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Login;
import pages.Users;

public class UsersTests extends BaseTest {

	
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	Users usersPageObject;
	

	public UsersTests() {
		super();
		
	}

	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPageObject = new Login(driver);
		dashBoardPageObject = loginPageObject.loginSuccess("admin",
				"@simple1");
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
		usersArray[x] = usersPageObject.createSpecificUser(firstName, lastName, "12345", firstName, organization);
		}
		System.out.println("Users creation done");
		
		for (String string : usersArray) {
			System.out.println(string+",12345");
		
		}
		System.out.println("Users creation done");
		
	}
}