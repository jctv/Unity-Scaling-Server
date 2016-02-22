package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath = "//span[text()='fields.test']/../..//div[text()='Click to Select']")
	public WebElement testClickToSelectFilter;
	
	@FindBy(xpath = "//span[text()='Content Area']")
	public WebElement contentAreaFilter;
	
	@FindBy(xpath = "//span[text()='Math']/../i")
	public WebElement mathFilterCheckBox;
	
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
	
	@FindBy(xpath = ".//*[@id='region-navigation']/ul/li[1]/a")
	public WebElement backToHandScore;
	
	@FindBy(xpath = "//nav[@id='hs_navbar']//div")
	public List <WebElement> noOfStudents;
	
	@FindBy(xpath = "//div[@id='numElement']//button")
	public List <WebElement> noOfItems;
	
	
	public void filterClass(String className ){
	try{
		waitForElementAndClick(classFilter); 
		waitForElementAndClick(classClickToSelectFilter); 
		waitForElementAndSendKeys(filterSearchInputField, className);
		customeWaitTime(1);
		waitForElementAndDoubleClick(filersearchButton);
		customeWaitTime(2);
		WebElement searchedClass = driver.findElement(By
				.xpath("//tr[@class='data-row']//td[text()='"
						+ className + "']"));
		waitForElementAndDoubleClick(searchedClass);
		customeWaitTime(2);
		waitForElementAndClick(globalModalOKCancelSaveButton);
		customeWaitTime(2);
	
	}catch(Exception e ){
		
	   }
	}
	
	public void filterTest(String testName ){
		try{
			waitForElementAndClick(testFilter); 
			waitForElementAndClick(testClickToSelectFilter); 
			waitForElementAndSendKeys(filterSearchInputField, testName);
			customeWaitTime(1);
			waitForElementAndDoubleClick(filersearchButton);
			customeWaitTime(2);
			WebElement searchedTest = driver.findElement(By
					.xpath("//tr[@class='data-row']//td[text()='"
							+ testName + "']"));
			waitForElementAndDoubleClick(searchedTest);
			customeWaitTime(2);
			waitForElementAndClick(globalModalOKCancelSaveButton);
			customeWaitTime(2);
		}catch(Exception e ){
			
		}

  }
	
	public void filterContentArea(String contentAreas){
		try{
			 List<String> contentAreaToFilter = new ArrayList<String>(Arrays.asList(contentAreas.split(",")));
	            for (String contentArea : contentAreaToFilter) {
	 
	                switch (contentArea) {
	                case "Math":
	                    waitForElementAndClick(mathFilterCheckBox);
	                    break;
	                case "Language Arts":
	                    waitForElementAndClick(languageFilterCheckBox);
	                    break;
	                case "Science":
	                    waitForElementAndClick(scienceFilterCheckBox);
	                    break;
	                case "Social Studies":
	                    waitForElementAndClick(socialStudiesFilterCheckBox);
	                    break;
	                case "N/A":
	                    waitForElementAndClick(nAFilterCheckBox);
	                    break;
	                case "Multiple":
	                    waitForElementAndClick(multipleFilterCheckBox);
	                    break;
	                case "Any":
	                    waitForElementAndClick(anyFilterCheckBox);
	                    break;
	                }
	            }
			
		}catch(Exception e){
			
			
		}

		
	}
	
	public String scoreTest(String testName , int studentCount , int itemCount , String scorePoint) {
		try {
			waitForElementAndClick(allItems);
		for (int x = 1; x <= studentCount; x++) {
			WebElement studentTobeScored = driver.findElement(By
					.xpath("//nav[@id='hs_navbar']//div[" + x + "]/button"));
			waitTime();
			waitForElementAndClick(studentTobeScored);

			for (int y = 1; y <= itemCount; y++) {
				WebElement itemToBeScored = driver.findElement(By
						.xpath("//div[@id='numElement']//button[" + y + "]"));
				waitForElementAndClick(itemToBeScored);
				// Need to change this as drop down  will change to text input box
				selectOption(score, scorePoint);
				waitForElementAndDoubleClick(saveScoreButton);
			}
			
				waitForElementAndClick(okButton);
				waitForElementAndClick(backLink);
			
			waitTime();
			waitForElementAndClick(backLink);
		}
		
		} catch (Exception e) {

		}

		return "test scored";

	}
	
	public void startHandScoring(String testName){
		try{
			  waitForElementAndSendKeys(searchAutoComplete, testName);
			  waitForElementAndClick(searchButton);
			  customeWaitTime(5);
			  waitForElementAndClick(editIconList);
			  waitTime();
			}catch(Exception e){
				System.out.println("Unable to start  the hand score for test   -->  "  + testName);
			}
	}
}
