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
	
	long timestamp;
	
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
		waitTime();
		 timestamp = System.currentTimeMillis();
		schoolName = unityRosterdata.getProperty("schoolName");
		String userMessage = usersPage.createSpecificUser(unityRosterdata.getProperty("s_FirstName"), unityRosterdata.getProperty("s_LastName") + timestamp , unityRosterdata.getProperty("password"), unityRosterdata.getProperty("password"), "Student", schoolName);
		createdStudent = userMessage.split("user name - ")[1].split(" !")[0];
		returnToDashboard();
		rosterName = unityRosterdata.getProperty("rosterName") + timestamp;
		grade = unityRosterdata.getProperty("grade");
		desciption  = unityRosterdata.getProperty("rosterDesc");
		classRosterPage = dashBoardPage.goToClassRoster();
		createdUsersA.add(createdStudent);
		classRosterPage.createRoster(createdUsersA, schoolName, rosterName);
		//classRosterPage.backToDashboard();
		
	}
	
	
	
	@Test(priority =1 )
	public void testSearchAndVerifyRosterListings(){
		classRosterPage.waitForElementAndClick(classRosterPage.resetSearchFilter);
		customeWaitTime(5);
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
		classRosterPage.waitForElementAndClick(classRosterPage.resetSearchFilter);
		customeWaitTime(5);
		classRosterPage.searchRoster(rosterName);
		customeWaitTime(2);
		classRosterPage.waitForElementAndClick(classRosterPage.previewIconList);
		customeWaitTime(5);
		Assert.assertTrue(classRosterPage.getRosterSchoolName().contains(schoolName));
		Assert.assertEquals(classRosterPage.getRosterStundentFirstName(), unityRosterdata.getProperty("s_FirstName"));
		Assert.assertEquals(classRosterPage.getRosterStundentLastName(), unityRosterdata.getProperty("s_LastName") + timestamp);
		Assert.assertEquals(classRosterPage.getRosterStundentRole(), "Student");

	}
	
	@Test(priority = 3)
	public void testfilterRoster(){
		classRosterPage.waitForElementAndClick(classRosterPage.resetSearchFilter);
		customeWaitTime(5);
		classRosterPage.filterRosterBySchool(schoolName);
		customeWaitTime(2);
		classRosterPage.searchRoster(rosterName);
		customeWaitTime(2);
		Assert.assertEquals(classRosterPage.waitAndGetElementText(classRosterPage.rosterNameList), rosterName);
		Assert.assertEquals(classRosterPage.waitAndGetElementText(classRosterPage.rosterDescList), "QA roster");
		Assert.assertEquals(classRosterPage.waitAndGetElementText(classRosterPage.rosterGradeList), "Any");
		Assert.assertEquals(classRosterPage.waitAndGetElementText(classRosterPage.rosterCreatedByList), "Admin Admin");
		Assert.assertEquals(classRosterPage.waitAndGetElementText(classRosterPage.rosterSchoolNameList), schoolName);

	}
	
	/**
	 * Login into the unity 
	 * go to the Roster  tile
	 * Click on Create class roster  navigation
	 * validated Roster alerts created message
	 */
	
	@Test(priority = 4)
	public void testRosterAlertMessages(){
		classRosterPage.waitForElementAndClick(classRosterPage.resetSearchFilter);
		customeWaitTime(5);
		//classRosterPage.waitForElementAndClick(classRosterPage.previewIconList);
		//waitTime();
		classRosterPage.searchRoster(rosterName);
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
		if(classRosterPage.confirmationAlertButton.isDisplayed()){
			classRosterPage.waitForElementAndClick(classRosterPage.confirmationAlertButton);
		}
		
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
