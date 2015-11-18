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

	@FindBy(xpath = "//*[@id='region-navigation']/div/a[2]")
	public WebElement createClassRosterLink;
	
	@FindBy(xpath = "//a[text()=' Class Roster Home']")
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
	
	@FindBy(xpath = "//*[@id='sortable1']/li")
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

	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
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
	

	public void createRoster(ArrayList<String> students, String school, String name) {
		try {
			waitTime();
			waitTime();
			waitForElementAndClick(createClassRosterLink);
			//waitForElementAndClick(schoolDropDown);
			//waitForElementAndSendKeys(schoolSearchField, school);
			waitForElementAndSendKeys(rosterNameField, name);

			waitForElementAndSendKeys(descriptionField, "QA roster");
			waitForElementAndSendKeys(selectSchoolField,"Automated School");
			//selectOption(selectSchoolField , "Automated School" );
			for (String student : students) {
				try {
					

				
				waitTime();
				waitForElementAndSendKeys(searchAutoCompleteField, student);
				waitForElementAndClick(searchButton);
				waitTime();	
				waitForElementAndClick(element);
				
				dragAndDrop(element, target);
				clearSearchFilter();
				System.out.println("Student added to the rosters");
				
				} catch (Exception e) {
					clearSearchFilter();
				}
			}
			waitForElementAndClick(gradeField);
			waitForElementAndClick(gradeSelect);
			waitForElementAndClick(saveRosterButton);
			waitForElementAndClick(confirmOkButton);
			waitTime();
			waitForElementAndClick(homeLink);
			System.out.println("Class Roster Created");
			waitTime();
			waitForElementAndClick(dashBoardLink);

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
}
