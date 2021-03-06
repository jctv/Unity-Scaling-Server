package tests;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
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

    ArrayList<String> createdUsersA = new ArrayList<String>();

    String createdStudent;
	String rosterName;
	String schoolName;
	String grade ;
	String desciption;
	String studentF1;
	String studentL1;
    String userPassword;
	
	long timestamp;
	
	public ClassRosterTest() {
		super();
		
	}
	
	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		unityRosterdata = getUnityMessagesProperty(unityRosterDataFile);
		timestamp = System.currentTimeMillis();
		schoolName = unityRosterdata.getProperty("schoolName")+ timestamp;
		studentF1 =unityRosterdata.getProperty("s_FirstName");
		studentL1 = unityRosterdata.getProperty("s_LastName") + timestamp;
		rosterName = unityRosterdata.getProperty("rosterName") + timestamp;
		userPassword = unityRosterdata.getProperty("password");
		grade = unityRosterdata.getProperty("grade");
		desciption  = unityRosterdata.getProperty("rosterDesc");

	}

	@BeforeClass
	public void setUp() {
		try{
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"),
				unitytestdata.getProperty("defaultPassword"));
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
		usersPage = dashBoardPage.goToUsers();
		customeWaitTime(5);
		usersPage.createSpecificUser(studentF1, studentL1 , userPassword, userPassword, "Student", schoolName);
		createdStudent = studentF1.substring(0, 1) + studentL1;
		returnToDashboard();
		classRosterPage = dashBoardPage.goToClassRoster();
		createdUsersA.add(createdStudent);
		classRosterPage.createRoster(createdUsersA, schoolName, rosterName);
		//classRosterPage.backToDashboard();
		classRosterPage.waitForElementAndClick(classRosterPage.backToRoster);
		}catch(Exception e){
			System.out.println("Error # while setting up the class roster data");
		}
	}
	
	
	
	@Test(priority =1 )
	public void testSearchAndVerifyRosterListings(){
		classRosterPage.searchRoster(rosterName);
		customeWaitTime(2);
		Assert.assertEquals(classRosterPage.waitAndGetElementText(classRosterPage.rosterNameList), rosterName);
		Assert.assertEquals(classRosterPage.waitAndGetElementText(classRosterPage.rosterDescList), "QA roster");
		Assert.assertEquals(classRosterPage.waitAndGetElementText(classRosterPage.rosterGradeList), "Any");
		Assert.assertEquals(classRosterPage.waitAndGetElementText(classRosterPage.rosterCreatedByList), "Admin Admin");
		Assert.assertEquals(classRosterPage.waitAndGetElementText(classRosterPage.rosterSchoolNameList), schoolName);
	}

	@Test(priority = 2)
	public void testVerifyRosterInformationInPreview(){
		classRosterPage.searchRoster(rosterName);
		customeWaitTime(2);
		classRosterPage.waitForElementAndClick(classRosterPage.previewIconList);
		customeWaitTime(5);
		Assert.assertTrue(classRosterPage.getRosterSchoolName().contains(schoolName));
		Assert.assertEquals(classRosterPage.getRosterStundentFirstName(), unityRosterdata.getProperty("s_FirstName"));
		Assert.assertEquals(classRosterPage.getRosterStundentLastName(), unityRosterdata.getProperty("s_LastName"));
		Assert.assertEquals(classRosterPage.getRosterStundentRole(), "Student");

	}
	
	/**
	 * Login into the unity 
	 * go to the Roster  tile
	 * Click on Create class roster  navigation
	 * validated Roster alerts created message
	 */
	
	@Test(priority = 3)
	public void testRosterAlertMessages(){
		//classRosterPage.waitForElementAndClick(classRosterPage.previewIconList);
		//waitTime();
		//classRosterPage.searchRoster(rosterName);
		//waitTime();
		
		classRosterPage.waitForElementAndClick(classRosterPage.createClassRosterLink);
		customeWaitTime(2);
		classRosterPage.waitForElementAndSendKeys(classRosterPage.descriptionField, desciption);
		classRosterPage.waitForElementAndClick(classRosterPage.backToRoster);
		Assert.assertEquals(classRosterPage.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("unSavedData").trim());
		classRosterPage.waitForElementAndClick(classRosterPage.globalModalOKCancelSaveButton);
		customeWaitTime(2);
		
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
		//classRosterPage.selectOption(classRosterPage.selectSchoolField, schoolName);
		classRosterPage.selectSchool(schoolName);
		customeWaitTime(5);
		classRosterPage.waitForElementAndSendKeys(classRosterPage.searchAutoCompleteField, createdStudent);
		customeWaitTime(2);
		classRosterPage.waitForElementAndClick(classRosterPage.searchButton);
		customeWaitTime(5);	
		classRosterPage.waitAndFocus(classRosterPage.element);
		classRosterPage.waitForElementAndClick(classRosterPage.element);
		customeWaitTime(5);
		classRosterPage.dragAndDrop(classRosterPage.element, classRosterPage.target);
		customeWaitTime(2);
		classRosterPage.waitForElementAndClick(classRosterPage.removeStudentButton);
		customeWaitTime(5);
		Assert.assertEquals(classRosterPage.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("rosterRemoveStudent").trim());
		classRosterPage.waitForElementAndClick(classRosterPage.globalModalOKCancelSaveButton);
		waitTime();
		classRosterPage.waitForElementAndClick(classRosterPage.saveRosterButton);
		waitTime();
		waitTime();
		Assert.assertEquals(classRosterPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("rosterUpdate").trim());
		Assert.assertEquals(classRosterPage.globalModalInfoTitle.getText().trim(), unitymessages.getProperty("rosterSave").trim());
		classRosterPage.waitForElementAndClick(classRosterPage.globalModalInfoOkButton);
		customeWaitTime(2);
		classRosterPage.waitForElementAndClick(classRosterPage.classRosterHomeLink);
		
	}
	
	
	@AfterClass
	public void cleanUpData(){
		customeWaitTime(2);
		classRosterPage.searchRoster(rosterName);
		customeWaitTime(2);
		classRosterPage.waitForElementAndClick(classRosterPage.deleteIconList);
		customeWaitTime(2);
		Assert.assertEquals(classRosterPage.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("rosterDelete").trim());
		classRosterPage.waitForElementAndClick(classRosterPage.globalModalDeleteButton);

		
	}

}
