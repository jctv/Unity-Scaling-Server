package pages;

import java.util.ArrayList;

import generic.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClassRoster extends BasePage {

	public ClassRoster(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Tabs Ids

	@FindBy(id = "loginButton")
	public WebElement signIn;
				
	@FindBy(xpath = "//*[@id='region-navigation']/ul/li[2]/a")
	public WebElement createClassRosterLink;
	
	@FindBy(xpath = ".//*[@id='region-navigation']/ul/li[1]/a")
	public WebElement classRosterHomeLink;
	
	@FindBy(id = "rosterSchool")
	public WebElement selectSchoolField;

	@FindBy(id = "searchAutoComplete")
	public WebElement searchAutoCompleteField;

	@FindBy(id = "searchButton")
	public WebElement searchButton;

	@FindBy(id = "rosterName")
	public WebElement rosterNameField;

	@FindBy(id = "rGrade")
	public WebElement gradeField;

	@FindBy(id = "rDesc")
	public WebElement descriptionField;

	@FindBy(id = "classRosterSave")
	public WebElement saveRosterButton;

	
	@FindBy(id = "classRosterCancel")
	public WebElement cancelRosterButton;
	
	@FindBy(xpath = "//ul[@id='sortable1']//li")
	public WebElement element;
	
	@FindBy(id = "sortable2")
	public WebElement target;

	@FindBy(xpath = "//ul[@id='sortable2']//button[@class='btn btn-warning btn-xs delete-item']")
	public WebElement removeStudentButton;
	
	
	@FindBy(id = "rosterSchool")
	public WebElement rosterSchool;

	@FindBy(id = "globalModaSaveRosterButton")
	public WebElement confirmSaveButton;

	@FindBy(id = "globalModalInfoOkButton")
	public WebElement confirmOkButton;
	
	@FindBy(xpath = "//*[@id='region-navigation']/ul/li[1]/a")
	public WebElement homeLink;

	@FindBy(xpath = "//*[@id='region-navigation']/div/a[1]")
	public WebElement dashBoardLink;

	@FindBy(xpath = "//*[@id='rGrade']/option[4]")
	public WebElement gradeSelect;
	
	@FindBy(xpath = "//span[@class='filter-option pull-left']")
	public WebElement schoolDropDown;
	
	@FindBy(xpath = "//input[@class='form-control']")
	public WebElement schoolSearchField;
	
	@FindBy(id = "rosterTeacher")
	public WebElement selectRosterTeacher;
	
	@FindBy(xpath = "//button[@data-id='rosterSchool']")
	public WebElement schoolDropDownButton;
	
	@FindBy(xpath = "//div[@class='btn-group bootstrap-select select-search-by-name-organization open']//input")
	public WebElement schoolSearchInputField;
	
	@FindBy(xpath = "//div[@class='btn-group bootstrap-select select-search-by-name-organization open']//ul/li[1]/a/span[1]")
	public WebElement selectedSchool;
	
	@FindBy(xpath = "//div[@class='panel-heading']")
	public WebElement studentRosterHeader;
	
	@FindBy(xpath = "//div[@class='panel-heading']/span")
	public WebElement rosterSchoolName;
	
	
	@FindBy(xpath = ".//*[contains(@id, 'DataTables_Table')]/tbody/tr/td[1]")
	public WebElement rosterStudentFirstName;
	
	@FindBy(xpath = ".//*[contains(@id, 'DataTables_Table')]/tbody/tr/td[3]")
	public WebElement rosterStudentLastName;
	
	@FindBy(xpath = ".//*[contains(@id, 'DataTables_Table')]/tbody/tr/td[4]")
	public WebElement rosterStudentRole;
	
	@FindBy(xpath = "//td[@class='watable-col-name']")
	public WebElement rosterNameList;
	
	@FindBy(xpath = "//td[@class='watable-col-description']")
	public WebElement rosterDescList;
	
	@FindBy(xpath = "//td[@class='watable-col-grade']")
	public WebElement rosterGradeList;
	
	@FindBy(xpath = "//td[@class='watable-col-created_by']")
	public WebElement rosterCreatedByList;
	
	@FindBy(xpath = "//td[@class='watable-col-school']")
	public WebElement rosterSchoolNameList;
	
	
	
	
	
	public void createRoster(ArrayList<String> students, String school, String name) {
		try {
			customeWaitTime(3);
			waitForElementAndClick(createClassRosterLink);
			//waitForElementAndClick(schoolDropDown);
			//waitForElementAndSendKeys(schoolSearchField, school);
			waitForElementAndSendKeys(rosterNameField, name);
			selectOption(gradeField, "Any");
			waitForElementAndSendKeys(descriptionField, "QA roster");
			//waitForElementAndSendKeys(selectSchoolField,"Automated School");
			//selectOption(selectSchoolField , "Automated School" );
			waitTime();
			System.out.println("Adding the created students");			
			for (String student : students) {
				try {
				waitTime();
				waitForElementAndSendKeys(searchAutoCompleteField, student);
				waitForElementAndClick(searchButton);
				customeWaitTime(5);
				waitAndFocus(element);
				waitForElementAndClick(element);
				customeWaitTime(5);
				dragAndDrop(element, target);
				customeWaitTime(5);
				clearSearchFilter();
				customeWaitTime(2);
				System.out.println("Student added to the rosters");
				
				} catch (Exception e) {
					clearSearchFilter();
				}
			}
			//waitForElementAndClick(gradeField);
			//waitForElementAndClick(gradeSelect);
			waitForElementAndClick(saveRosterButton);
			waitForElementAndClick(confirmOkButton);
			waitTime();
			waitForElementAndClick(homeLink);
			System.out.println("Class Roster Created");
			waitTime();
			//waitForElementAndClick(dashBoardLink);

		} catch (Exception e) {
			System.out.println("Class Roster Creation Failed");
			System.out.println(e.getMessage());
		}
	}
	
	
	public void searchRoster(String rosterName){
		waitTime();
		searchAutoComplete.clear();
		waitTime();
		waitForElementAndSendKeys(searchAutoComplete, rosterName);
		waitTime();
		waitForElementAndClick(searchButton);
		waitTime();
	}
	
	public void selectSchool(String schoolName){
		waitForElementAndClick(schoolDropDownButton);
		waitTime();
		waitForElementAndSendKeys(schoolSearchInputField, schoolName);
		waitTime();
		//waitForElementAndClick(selectedSchool);
		waitTime();
	}
	
	
	public String  getRosterSchoolName(){
		return waitAndGetElementText(rosterSchoolName).trim();
	}
	
	public String  getRosterStundentFirstName(){
		return waitAndGetElementText(rosterStudentFirstName).trim();
	}
	
	public String  getRosterStundentLastName(){
		return waitAndGetElementText(rosterStudentLastName).trim();
	}
	
	public String  getRosterStundentRole(){
		return waitAndGetElementText(rosterStudentRole).trim();
	}
	
	
}
