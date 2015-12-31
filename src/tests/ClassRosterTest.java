package tests;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.ClassRoster;
import pages.DashBoard;
import pages.Login;
import pages.Users;
import generic.BaseTest;

public class ClassRosterTest extends BaseTest{
	
	Login loginPage;
	DashBoard dashBoardPage;
	ClassRoster classRosterPage;
	
	Users usersPage;

	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	String unityTestDataFile = "src" + File.separator + "resources"
			+ File.separator + "unitytestdata.properties";
	
	String unityRosterDataFile = "src" + File.separator + "resources"
			+ File.separator + "rosterdata.properties";
	
	Properties unitymessages;
	
	Properties unitytestdata;
	
	Properties unityRosterdata;

	
	
	
	String rosterName;
	String schoolName;
	String grade ;
	String desciption;
	
	public ClassRosterTest() {
		super();
		
	}
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		unityRosterdata = getUnityMessagesProperty(unityRosterDataFile);
	}

	@BeforeClass
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
		usersPage = dashBoardPage.goToUsers();
		long timestamp = System.currentTimeMillis();
		schoolName = unityRosterdata.getProperty("schoolName");
		String userMessage = usersPage.createSpecificUser(unityRosterdata.getProperty("s_FirstName"), unityRosterdata.getProperty("s_LastName") + timestamp , unityRosterdata.getProperty("password"), unityRosterdata.getProperty("password"), "Student", schoolName);
		String createdStudent = userMessage.split("user name - ")[1].split(" !")[0];
		returnToDashboard();
		rosterName = unityRosterdata.getProperty("rosterName") + timestamp;
		grade = unityRosterdata.getProperty("grade");
		desciption  = unityRosterdata.getProperty("rosterDesc");
		classRosterPage = dashBoardPage.goToClassRoster();
		ArrayList<String> createdUsersA = new ArrayList<String>();
		createdUsersA.add(createdStudent);
		classRosterPage.createRoster(createdUsersA, schoolName, rosterName);
		classRosterPage.backToDashboard();
		
	}
	
	
	/**
	 * Login into the unity 
	 * go to the Roster  tile
	 * Click on Create class roster  navigation
	 * validated Roster alerts created message
	 */
	
	@Test
	public void testRosterAlertMessages(){
		
		classRosterPage = dashBoardPage.goToClassRoster();
		waitTime();
		waitTime();
		classRosterPage.waitForElementAndClick(classRosterPage.createClassRosterLink);
		waitTime();
		waitTime();
		classRosterPage.waitForElementAndClick(classRosterPage.saveRosterButton);
		waitTime();
		waitTime();
		Assert.assertEquals(classRosterPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("rosterNoChanges").trim());
		classRosterPage.waitForElementAndClick(classRosterPage.globalModalInfoOkButton);
		waitTime();
		waitTime();
		classRosterPage.waitForElementAndClick(classRosterPage.cancelRosterButton);
		waitTime();
		waitTime();
		Assert.assertEquals(classRosterPage.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("rosterCancelEditing").trim());
		classRosterPage.waitForElementAndClick(classRosterPage.globalModalOKCancelSaveButton);
		waitTime();
		waitTime();
		classRosterPage.waitForElementAndClick(classRosterPage.rosterNameField);
		waitTime();
		waitTime();
		classRosterPage.waitForElementAndClick(classRosterPage.saveRosterButton);
    	waitTime();
		waitTime();
		Assert.assertEquals(classRosterPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("rosterInvalidData").trim());
		classRosterPage.waitForElementAndClick(classRosterPage.globalModalInfoOkButton);
		waitTime();
		waitTime();
		
		classRosterPage.waitForElementAndSendKeys(classRosterPage.rosterNameField, rosterName + 1);
		waitTime();
		classRosterPage.selectOption(classRosterPage.gradeField, grade);
		waitTime();
		
		classRosterPage.waitForElementAndSendKeys(classRosterPage.descriptionField, desciption);
		waitTime();
		classRosterPage.waitForElementAndClick(classRosterPage.saveRosterButton);
		waitTime();
		waitTime();
		Assert.assertEquals(classRosterPage.globalModalInfoTitle.getText().trim(), unitymessages.getProperty("rosterSave").trim());
		Assert.assertEquals(classRosterPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("rosterContinueEditing").trim());
		classRosterPage.waitForElementAndClick(classRosterPage.globalModalInfoOkButton);
		waitTime();
		waitTime();
		classRosterPage.selectOption(classRosterPage.selectSchoolField, schoolName);
		waitTime();
		classRosterPage.dragAndDrop(classRosterPage.element, classRosterPage.target);
		waitTime();
		classRosterPage.waitForElementAndClick(classRosterPage.removeStudentButton);
		waitTime();
		Assert.assertEquals(classRosterPage.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("rosterRemoveStudent").trim());
		classRosterPage.waitForElementAndClick(classRosterPage.globalModalOKCancelSaveButton);
		waitTime();
		classRosterPage.waitForElementAndClick(classRosterPage.saveRosterButton);
		waitTime();
		waitTime();
		Assert.assertEquals(classRosterPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("rosterUpdate").trim());
		Assert.assertEquals(classRosterPage.globalModalInfoTitle.getText().trim(), unitymessages.getProperty("rosterSave").trim());
		classRosterPage.waitForElementAndClick(classRosterPage.globalModalInfoOkButton);
		waitTime();
		classRosterPage.waitForElementAndClick(classRosterPage.classRosterHomeLink);
		waitTime();
		waitTime();
		classRosterPage.waitForElementAndClick(classRosterPage.deleteIconList);
		waitTime();
		waitTime();
		classRosterPage.searchRoster(rosterName);
		waitTime();
		Assert.assertEquals(classRosterPage.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("rosterDelete").trim());
		classRosterPage.waitForElementAndClick(classRosterPage.globalModalDeleteButton);

	}

}
