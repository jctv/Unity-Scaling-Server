package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.ClassRoster;
import pages.DashBoard;
import pages.Login;
import generic.BaseTest;

public class ClassRosterTest extends BaseTest{
	
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	ClassRoster classRosterPageObject;
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	Properties unitymessages;
	
	public ClassRosterTest() {
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
		dashBoardPageObject = loginPageObject.loginSuccess("admin",
				"@simple1");
		waitTime();
		//dashBoardPageObject.addTiles();
		waitTime();
	}
	
	
	/**
	 * Login into the unity 
	 * go to the Roster  tile
	 * Click on Create class roster  navigation
	 * validated Roster alerts created message
	 */
	
	@Test
	public void testRosterAlertMessages(){
		String rosterName = "Auto Roster" + System.currentTimeMillis();
		String schoolName = "Auto School";
		String grade = "Any";
		String desciption  = "Desc";
		
		classRosterPageObject = dashBoardPageObject.goToClassRoster();
		waitTime();
		waitTime();
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.createClassRosterLink);
		waitTime();
		waitTime();
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.saveRosterButton);
		waitTime();
		waitTime();
		Assert.assertEquals(classRosterPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("rosterNoChanges").trim());
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.globalModalInfoOkButton);
		waitTime();
		waitTime();
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.cancelRosterButton);
		waitTime();
		waitTime();
		Assert.assertEquals(classRosterPageObject.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("rosterCancelEditing").trim());
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.globalModalOKCancelSaveButton);
		waitTime();
		waitTime();
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.rosterNameField);
		waitTime();
		waitTime();
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.saveRosterButton);
    	waitTime();
		waitTime();
		Assert.assertEquals(classRosterPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("rosterInvalidData").trim());
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.globalModalInfoOkButton);
		waitTime();
		waitTime();
		
		classRosterPageObject.waitForElementAndSendKeys(classRosterPageObject.rosterNameField, rosterName);
		waitTime();
		classRosterPageObject.selectOption(classRosterPageObject.gradeField, grade);
		waitTime();
		
		classRosterPageObject.waitForElementAndSendKeys(classRosterPageObject.descriptionField, desciption);
		waitTime();
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.saveRosterButton);
		waitTime();
		waitTime();
		Assert.assertEquals(classRosterPageObject.globalModalInfoTitle.getText().trim(), unitymessages.getProperty("rosterSave").trim());
		Assert.assertEquals(classRosterPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("rosterContinueEditing").trim());
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.globalModalInfoOkButton);
		waitTime();
		waitTime();
		classRosterPageObject.selectOption(classRosterPageObject.selectSchoolField, schoolName);
		waitTime();
		classRosterPageObject.dragAndDrop(classRosterPageObject.element, classRosterPageObject.target);
		waitTime();
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.removeStudentButton);
		waitTime();
		Assert.assertEquals(classRosterPageObject.globalModalOKCancelBody.getText().trim(), unitymessages.getProperty("rosterRemoveStudent").trim());
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.globalModalOKCancelSaveButton);
		waitTime();
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.saveRosterButton);
		waitTime();
		waitTime();
		Assert.assertEquals(classRosterPageObject.globalModalInfoBody.getText().trim(), unitymessages.getProperty("rosterUpdate").trim());
		Assert.assertEquals(classRosterPageObject.globalModalInfoTitle.getText().trim(), unitymessages.getProperty("rosterSave").trim());
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.globalModalInfoOkButton);
		waitTime();
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.classRosterHomeLink);
		waitTime();
		waitTime();
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.deleteIconList);
		waitTime();
		waitTime();
		classRosterPageObject.searchRoster(rosterName);
		waitTime();
		Assert.assertEquals(classRosterPageObject.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("rosterDelete").trim());
		classRosterPageObject.waitForElementAndClick(classRosterPageObject.globalModalDeleteButton);

	}

}
