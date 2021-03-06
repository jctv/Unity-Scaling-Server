package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath = "//*[@id='region-workspace']/div/div[1]/div[2]/div/table/tbody/tr/td/div[2]/div/div[2]/table/tbody/tr[1]/td[2]")
	public WebElement calendarArea;
	
	@FindBy(xpath = "//div[@class= 'fc-time']")
	public WebElement scheduledTestEvent;
	
	@FindBy(id="isStudentScoreReportable")
	public WebElement studentReportableDropDown;
	
	@FindBy(xpath = "//*[@id='region-workspace']/div/div[1]/div[2]/div/table/tbody/tr/td/div[2]/div/div[3]/table/tbody/tr/td[2]/div/a/div[1]/div[1]")
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
	
	@FindBy(id = "timeLimit")
	public WebElement timeLimitSelect;
	

	@FindBy(id = "masterToolSelect")
	public WebElement masterToolSelect;

	@FindBy(id = "masterRulerSelect")
	public WebElement masterRulerSelect;
	
	@FindBy(id = "saveChanges")
	public WebElement btnCreate;

	@FindBy(id = "startNow")
	public WebElement startNowEventClick;
	
	@FindBy(xpath = "//*[@id='region-workspace']/div/div[1]/div[1]/div[1]/div/button[2]")
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
	 * This is the method for schedule the test
	 * @param school
	 * @param roster
	 * @param contetArea
	 * @param test
	 * @param eventColor
	 * @param time
	 * @param goal
	 * @param tools
	 */
	public void scheduleTest(String school, String roster , String contetArea , String test , 
							 String eventColor, String time , String goal, String tools,
							 String isScoreReportable) {
		try {
			waitTime();
			waitForElementAndClick(dayButton);
			
			while(waitForElementVisible(scheduledTestEvent)){
				waitForElementAndClick(nextButton);
			}
			waitForElementAndClick(nextButton);
			customeWaitTime(1);
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
			
			switch (isScoreReportable) {
			case "true":
				waitForElementAndClick(studentReportableDropDown);
				selectOption(studentReportableDropDown, 1);
				break;
			case "false":
				waitForElementAndClick(studentReportableDropDown);
				waitForElementAndSendKeys(studentReportableDropDown, "W");
				break;
			}
			
			waitForElementAndSendKeys(timeLimitSelect, time);
			selectOption(timeLimitSelect, time);
			
			waitForElementAndSendKeys(masterGoalSelect, goal);
			selectOption(masterGoalSelect, goal);
			
			waitForElementAndSendKeys(masterToolSelect, tools);
			selectOption(masterToolSelect, tools);
			waitForElementAndClick(btnCreate);
			System.out.println(test + "event is created");
			customeWaitTime(1);
			//waitForElementAndClick(createdEvent);
			waitForElementAndClick(scheduledTestEvent);
			System.out.println("click on event");
			
			waitForElementAndClick(startNowEventClick);
			customeWaitTime(2);
			//waitForElementAndClick(homeLink);
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
			
			waitForElementAndSendKeys(timeLimitSelect, "120");
			selectOption(timeLimitSelect, "120");
			
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
			//waitForElementAndClick(homeLink);
			System.out.println("Event Created success");
		} catch (Exception e) {
			System.out.println("Event creation failed");
		}

		
	}
	
	/**
	 * This is the method for save  the test event
	 * @param school
	 * @param roster
	 * @param contetArea
	 * @param test
	 * @param eventColor
	 * @param time
	 * @param goal
	 * @param tools
	 */
	public void saveTestEvent(String school, String roster , String contetArea , String test , String reportScore , String eventColor, String time , String goal, String tools) {
		try {
			waitTime();
			waitForElementAndClick(dayButton);
			
			while(waitForElementVisible(scheduledTestEvent)){
				waitForElementAndClick(nextButton);
			}
			waitForElementAndClick(nextButton);
			customeWaitTime(1);
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
			
			waitForElementAndSendKeys(timeLimitSelect, time);
			selectOption(timeLimitSelect, time);
			
			waitForElementAndSendKeys(masterGoalSelect, goal);
			selectOption(masterGoalSelect, goal);
			
			waitForElementAndSendKeys(masterToolSelect, tools);
			selectOption(masterToolSelect, tools);
			waitForElementAndClick(btnCreate);
			System.out.println(test + "event is created & Saved");
		} catch (Exception e) {
			System.out.println(test + "Event creation failed");
		}

		
	}
	
	public void startTestEvent(){
		try{
			waitForElementAndClick(scheduledTestEvent);
			System.out.println("click on event");
			customeWaitTime(5);
			waitForElementAndClick(startNowEventClick);
			waitTime();
			//waitForElementAndClick(homeLink);
			System.out.println("Test Event started");
			
		}catch(Exception e){
			
			System.out.println("Event creation failed");

		}
		
		
	}
	
	public void openTestEvent(){
		try{
			waitForElementAndClick(scheduledTestEvent);
			waitTime();
		}catch(Exception e){
			
		}
	}
	
	
	public boolean getStudentCheckBoxStatus(String studentuuid){
		WebElement studentCheckbox = null;

		try{
		studentCheckbox = driver.findElement(By
				.xpath("//input[@value='" + studentuuid + "']"));
		
		}catch(Exception e){
			
		}
		return studentCheckbox.isSelected();
	}
	
	public void addStudentToScheduledTest(String studentuuid , String goal , String tools ){
		try{
			WebElement studentCheckbox = driver.findElement(By
					.xpath("//input[@value='" + studentuuid + "']"));
			if(!studentCheckbox.isSelected()){
				System.out.println("By default student check is not  selected");
				
			}else{
				System.out.println("Error - By default student check was selected ");
			}
			
			waitForElementAndClick(studentCheckbox);
			WebElement stududentGoalSelect = driver.findElement(By
					.xpath("//select [@data-uuid='" + studentuuid + "' and contains(@id,'goalSelect')]"));
			selectOption(stududentGoalSelect , goal);
			
			WebElement stududentToolSelect = driver.findElement(By
					.xpath("//select [@data-uuid='" + studentuuid + "' and contains(@id,'toolSelect')]"));
			selectOption(stududentToolSelect , goal);

			waitForElementAndClick(btnCreate);

		}catch(Exception e ){
			
			System.out.println("Error - while adding the new  student for scheduled roster  ");
			
		}
		
	}

}
