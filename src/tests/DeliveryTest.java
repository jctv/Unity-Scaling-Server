package tests;

import generic.BaseTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Delivery;
import pages.Login;

public class DeliveryTest extends BaseTest {
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	Delivery DeliveryPageObject;

	public DeliveryTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver.get(url);

		loginPageObject = new Login(driver);
		System.out.println("******** logging as student  ********");

		dashBoardPageObject = loginPageObject.loginSuccess("load/sre149c",
				"12345");
	

	}
	@Test
	public void  takeTest(){
		customeWaitTime(5);
		DeliveryPageObject = dashBoardPageObject.goToDelivery();
		customeWaitTime(5);
		Assert.assertTrue(DeliveryPageObject.takeAndVefiryTestResults("100%",
				"Escuela,1,Space with Chocolate,3,SPACE,4,School,5,Barrio,2"));
		
	}
	
}
