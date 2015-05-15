package pages;

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
	
	@FindBy(css = "#region-navigation > div > a:nth-child(2)")
	public WebElement createClassRosterLink;

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

	@FindBy(xpath = "//*[@id='sortable1']/li")
	public WebElement element;
	@FindBy(id = "sortable2")
	public WebElement target;
	
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

	public void createRoster(String student, String school) {
		try {
			waitTime();
			waitForElementAndClick(createClassRosterLink);
			waitForElementAndSendKeys(rosterSchool, school);
			selectOption(rosterSchool, school);
			waitForElementAndSendKeys(rosterNameField, "Auto test Roster #1");
			waitForElementAndClick(gradeField);
			waitForElementAndClick(gradeSelect);
			waitForElementAndSendKeys(descriptionField, "QA roster");
			waitForElementAndSendKeys(selectSchoolField, "West Sacramento School");
			waitForElementAndSendKeys(searchAutoCompleteField, student);
			waitForElementAndClick(searchButton);	
			waitTime();
			waitForElementAndClick(element);
			dragAndDrop(element, target);
			System.out.println("Student added to the rosters");		
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

}
