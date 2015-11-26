package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Schedule extends BasePage {

	BasePage base;
	BaseTest test;

	public Schedule(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Tabs Ids
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement homeLink;
	
	@FindBy(xpath = "//*[@id='region-workspace']/div/div[1]/div[2]/div/table/tbody/tr/td/div[2]/div/div[2]/table")
	public WebElement calendar;
	
	@FindBy(xpath = "//div[@class='fc-time-grid-container']//div[@class='fc-slats']//tr[@class='fc-minor'][1]")
	public WebElement calendarArea;
	
	@FindBy(xpath = "//div[@class='fc-time-grid-container']//div[@class='fc-slats']//tr[@class='fc-minor'][1]/../../../../div[3]//div[@class='fc-title']")
	public WebElement scheduledTestEvent;
	
	
	
	@FindBy(xpath = "//*[@id='region-workspace']/div/div[1]/div[2]/div/table/tbody/tr/td/div[2]/div/div[3]/table/tbody/tr/td[5]/div/a/div[1]/div[1]")
	public WebElement createdEvent;

	@FindBy(xpath = "//button[text()='day']")
	public WebElement dayButton;
	

	@FindBy(id = "saveChanges")
	public WebElement saveChangesButton;
	
	@FindBy(id = "startNow")
	public WebElement startNowButton;
	
	@FindBy(id = "printAnswerSheet")
	public WebElement printAnswerSheetButton;
	
	@FindBy(id = "cancelChanges")
	public WebElement cancelChangesButton;
	
	@FindBy(id = "stopNow")
	public WebElement stopNowButton;
	
	@FindBy(id = "deleteEventClick")
	public WebElement deleteEventClickButton;
	
	
	@FindBy(id = "calType")
	public WebElement calType;

	@FindBy(id = "schoolName")
	public WebElement schoolName;

	@FindBy(id = "className")
	public WebElement className;

	@FindBy(id = "contentLevel")
	public WebElement contentLevel;

	@FindBy(id = "name")
	public WebElement name;

	@FindBy(id = "myColor")
	public WebElement myColor;

	@FindBy(id = "masterTimeSelect")
	public WebElement masterTimeSelect;
	
	@FindBy(id = "masterGoalSelect")
	public WebElement masterGoalSelect;

	@FindBy(id = "masterToolSelect")
	public WebElement masterToolSelect;

	@FindBy(id = "masterRulerSelect")
	public WebElement masterRulerSelect;
	
	@FindBy(id = "saveChanges")
	public WebElement btnCreate;

	@FindBy(id = "startNow")
	public WebElement startNowEventClick;
	                
	@FindBy(xpath = "//button[@class ='fc-next-button ui-button ui-state-default ui-corner-right']")
	public WebElement nextButton;
	
	@FindBy(xpath = "//button[@class ='fc-prev-button ui-button ui-state-default ui-corner-left']")
	public WebElement previousButton;
	
	@FindBy(id = "genericModalTitle")
	public WebElement genericModalTitle;
	
	@FindBy(id = "genericModalMessage")
	public WebElement genericModalMessage;
	
	@FindBy(id = "cancelPastEvent")
	public WebElement cancelPastEvent;

	/**
	 * @param school
	 * @param roster
	 * @param contetArea
	 * @param test
	 * @param eventColor
	 * @param time
	 * @param goal
	 * @param tools
	 */
	public void scheduleTest(String school, String roster , String contetArea , String test , String eventColor, String time , String goal, String tools) {
		try {
			waitTime();
			waitForElementAndClick(dayButton);
			customeWaitTime(5);
			waitForElementAndClick(nextButton);
			customeWaitTime(5);
			(new Actions(driver)).doubleClick(calendarArea).build().perform();
			(new Actions(driver)).doubleClick().build().perform();
			System.out.println("click on calendar");

			waitForElementAndSendKeys(schoolName, school);
			selectOption(schoolName, school);
			
			waitForElementAndSendKeys(className, roster);
			selectOption(className, roster);
			
			waitForElementAndSendKeys(contentLevel, contetArea);
			selectOption(contentLevel, contetArea);

			waitForElementAndSendKeys(name, test);
			selectOption(name, test);
			
			waitForElementAndSendKeys(myColor, eventColor);
			selectOption(myColor, eventColor);
			
			waitForElementAndSendKeys(masterTimeSelect, time);
			selectOption(masterTimeSelect, time);
			
			waitForElementAndSendKeys(masterGoalSelect, goal);
			selectOption(masterGoalSelect, goal);
			
			waitForElementAndSendKeys(masterToolSelect, tools);
			selectOption(masterToolSelect, tools);
			
			waitForElementAndClick(btnCreate);
			
			System.out.println(test + "event is created");
			waitTime();
			//waitForElementAndClick(createdEvent);
			waitForElementAndClick(scheduledTestEvent);
			System.out.println("click on event");
			waitTime();
			waitForElementAndClick(startNowEventClick);
			waitTime();
			waitForElementAndClick(homeLink);
			System.out.println(test + "Event Created success");
		} catch (Exception e) {
			System.out.println(test + "Event creation failed");
		}

		
	}
	
	public void scheduleTestReports(String roster, int eventNumber) {
		try {
			waitTime();
			for(int weeks = 0;weeks < eventNumber;weeks++){
			waitForElementAndClick(nextButton);
			}
			waitTime();
			
			(new Actions(driver)).doubleClick(calendar).build().perform();
			(new Actions(driver)).doubleClick().build().perform();
			System.out.println("click on calendar");

			
			
			
			waitForElementAndSendKeys(className, roster);
			selectOption(className, roster);
			
			waitForElementAndSendKeys(contentLevel, "N/A");
			selectOption(contentLevel, "N/A");
			
			waitForElementAndSendKeys(name, "test one Reports");
			selectOption(name, "test one Reports");
			
			waitForElementAndSendKeys(myColor, "Red");
			selectOption(myColor, "Red");
			
			waitForElementAndSendKeys(masterTimeSelect, "120");
			selectOption(masterTimeSelect, "120");
			
			waitForElementAndSendKeys(masterGoalSelect, "100%");
			selectOption(masterGoalSelect, "100%");
			
			waitForElementAndSendKeys(masterToolSelect, "Yes");
			selectOption(masterToolSelect, "Yes");
			
			waitForElementAndClick(btnCreate);
			
			System.out.println("event created");
			waitTime();
			waitForElementAndClick(createdEvent);
			System.out.println("click on event");
			waitForElementAndClick(startNowEventClick);
			waitTime();
			waitForElementAndClick(homeLink);
			System.out.println("Event Created success");
		} catch (Exception e) {
			System.out.println("Event creation failed");
		}

		
	}

}
