package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import generic.BaseTest;
import pages.DashBoard;
import pages.Login;
import pages.Schedule;

public class CalendarTest extends BaseTest {
	
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	Schedule sechedulePageObject;
	
	String defaultUser = "admin";
	String defaultPassword = "@simple1";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	Properties unitymessages;
	
	public CalendarTest() {
		super();
		
	}

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		
	}
	
	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPageObject = new Login(driver);
		dashBoardPageObject = loginPageObject.loginSuccess(defaultUser,
				defaultPassword);
		waitTime();
		//dashBoardPageObject.addTiles();
		waitTime();
	}
	
	/**
	 * Login into the Unity
	 * Go to the Calendar Tile
	 * GO to the previous week
	 * click  on calendar 
	 * Validate messages 
	 * 
	 */

	@Test
	public void testCalendarAlertMessgaes(){
		sechedulePageObject = dashBoardPageObject.goToSchedule();
		waitTime();
		waitTime();
		sechedulePageObject.waitForElementAndClick(sechedulePageObject.previousWeekButton);
		waitTime();
		waitTime();
		sechedulePageObject.waitForElementAndDoubleClick(sechedulePageObject.calendar);
		waitTime();
		waitTime();
		Assert.assertEquals(sechedulePageObject.genericModalTitle.getText().trim(), unitymessages.getProperty("calendarPastEvent").trim());
		Assert.assertEquals(sechedulePageObject.genericModalMessage.getText().trim(), unitymessages.getProperty("calendarCannotSchedule").trim());
		sechedulePageObject.waitForElementAndClick(sechedulePageObject.cancelPastEvent);
		
	}

}
