package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HandScoring extends BasePage {

	BasePage base;
	BaseTest test;

	public HandScoring(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	//button[contains(@class,'editRow') and ancestor::tr/td[text()='Auto test Roster #1']])[last()]
	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[3]/table/tbody/tr[1]/td[2]/div/button[2]/i")
	public WebElement scoreTestIcon;
                          
	@FindBy(xpath = "//*[@id='scores']/div/form/div[1]/div/select")
	public WebElement score;

	@FindBy(xpath = "//button[@Class ='btn btn-primary btn-sm response-save btn-danger']")
	public WebElement scoreOption;

	@FindBy(xpath = "//*[@id='scores']/div/form/div[2]/div/button")
	public WebElement saveScoreButton;

	@FindBy(id = "globalModalInfoOkButton")
	public WebElement globalModalInfoOkButton;

	@FindBy(xpath = "//*[@id='globalModalInfo']/div/div/div[1]/button")
	public WebElement okButton;

	@FindBy(xpath = "//*[@id='itemNav']/div/div[3]/button")
	public WebElement nextAnswer;
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backLink;
	
	@FindBy(id = "show-all-items")
	public WebElement allItems;
	
	@FindBy(id = "hsItem0")
	public WebElement hsItem0;
	
	@FindBy(xpath = "//td[@class='watable-col-roster']")
	public WebElement rosterInListing;
	
	@FindBy(xpath = "//td[@class='watable-col-test']")
	public WebElement testInListing;
	
	@FindBy(xpath = "//td[@class='watable-col-content_area']")
	public WebElement contentAreaInListing;
	
	@FindBy(xpath = "//td[@class='watable-col-calendar_event.start']")
	public WebElement startEventInListing;
	
	@FindBy(xpath = "//td[@class='watable-col-calendar_event.end']")
	public WebElement endEventInListing;
	
	@FindBy(xpath = "//span[text()='Class']")
	public WebElement classFilter;
	
	@FindBy(xpath = "//span[text()='Class']/../..//div[text()='Click to Select']")
	public WebElement classClickToSelectFilter;
	
	@FindBy(xpath = "//span[text()='fields.test']")
	public WebElement testFilter;
	
	@FindBy(xpath = "//span[text()='Class']/../..//div[text()='Click to Select']")
	public WebElement testClickToSelectFilter;
	
	@FindBy(xpath = "//span[text()='Content Area']")
	public WebElement contentAreaFilter;
	
	@FindBy(xpath = "//span[text()='Math']/../i")
	public WebElement matchFilterCheckBox;
	
	@FindBy(xpath = "//span[text()='Language Arts']/../i")
	public WebElement languageFilterCheckBox;
	
	@FindBy(xpath = "//span[text()='Science']/../i")
	public WebElement scienceFilterCheckBox;
	
	@FindBy(xpath = "//span[text()='Social Studies']/../i")
	public WebElement socialStudiesFilterCheckBox;
	
	
	@FindBy(xpath = "//span[text()='N/A']/../i")
	public WebElement nAFilterCheckBox;
	
	@FindBy(xpath = "//span[text()='Multiple']/../i")
	public WebElement multipleFilterCheckBox;
	
	@FindBy(xpath = "//span[text()='Any']/../i")
	public WebElement anyFilterCheckBox;
	
	
	public String scoreTest() {
		waitForElementAndClick(scoreTestIcon);
		waitTime();
		try {
			waitForElementAndDoubleClick(globalModalInfoOkButton);
			
			waitForElementAndClick(allItems);
			waitForElementAndClick(hsItem0);
			
			
		} catch (Exception e) {
			
		}
		
		for (int x = 0; x < 1; x++) {
			waitTime();
			selectOption(score, "1");
			
			waitForElementAndDoubleClick(saveScoreButton);
			
			try {
				//waitForElementAndDoubleClick(globalModalInfoOkButton);
				waitForElementAndClick(okButton);
				waitForElementAndClick(backLink);
			} catch (Exception e) {
				waitForElementAndClick(backLink);
			}
			waitTime();
			waitForElementAndClick(backLink);
		}
		
		
		return "test scored";

	}
}
