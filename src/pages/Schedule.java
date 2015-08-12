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
	
	@FindBy(xpath = "//*[@id='region-workspace']/div/div[1]/div[2]/div/table/tbody/tr/td/div[2]/div/div[3]/table/tbody/tr/td[5]/div/a/div[1]/div[1]")
	public WebElement createdEvent;

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
					 
	@FindBy(xpath = "//*[@id='region-workspace']/div/div[1]/div[1]/div[1]/div/button[2]")
	public WebElement nextWeekButton;

	public void scheduleTest() {
		try {
			waitTime();
			waitForElementAndClick(nextWeekButton);
			waitTime();
			(new Actions(driver)).doubleClick(calendar).build().perform();
			(new Actions(driver)).doubleClick().build().perform();
			System.out.println("click on calendar");

			
			waitForElementAndSendKeys(schoolName, "Automated Schoool");
			selectOption(schoolName, "Automated School");
			
			waitForElementAndSendKeys(className, "Auto test Roster #1");
			selectOption(className, "Auto test Roster #1");
			
			waitForElementAndSendKeys(contentLevel, "N/A");
			selectOption(contentLevel, "N/A");

			waitForElementAndSendKeys(name, "Automation test");
			selectOption(name, "Automation test");
			
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
