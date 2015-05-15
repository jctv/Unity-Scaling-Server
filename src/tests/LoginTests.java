package tests;


import generic.BaseTest;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Login;

public class LoginTests  extends BaseTest {

	Login Nav;

	public LoginTests() {
		super();
		
	}

	@BeforeMethod
	public void setUp() {
		driver.get(url);		
		Nav = PageFactory.initElements(driver, 	Login.class);
		
	}
	
	@Test
	public void login(){
		Nav.loginSuccess("aadministrator", "12345").handScoringTile.isEnabled();
		
	}
}