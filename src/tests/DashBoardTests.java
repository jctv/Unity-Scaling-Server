package tests;

import generic.BaseTest;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Login;

public class DashBoardTests extends BaseTest {

	DashBoard Nav;


	public DashBoardTests() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver.get(url);

		PageFactory.initElements(driver, Login.class).loginSuccess("aadministrator","12345");
		Nav = PageFactory.initElements(driver, DashBoard.class);

	}

	@Test
	public void selectUsers() {
		
		System.out.println(Nav.goToUsers().getTitle());
	}
}
