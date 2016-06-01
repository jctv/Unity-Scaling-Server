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
import org.openqa.selenium.support.FindBys;
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
	
	@FindBy(xpath = ".//*[@id='region-navigation']/ul/li[1]/a")
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
	
	@FindBy(xpath = "//span[text()='Test']")
	public WebElement testFilter;
	
	@FindBy(xpath = "//span[text()='Test']/../..//div[text()='Click to Select']")
	public WebElement testClickToSelectFilter;
	
	@FindBy(xpath = "//span[text()='Content Area']")
	public WebElement contentAreaFilter;
	
	@FindBy(xpath = ".//span[contains(text(),'Handscoring')]")
	public WebElement handScoringFilter;
	
	@FindBy(xpath = ".//span[text()='yes']")
	public WebElement yesFilterCheckBox;
	
	@FindBy(xpath = ".//span[text()='no']")
	public WebElement noFilterCheckBox;
	
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
	
	@FindBy(xpath = "//input[@class='response-scores ui-spinner-input']")
	public WebElement setScoreInputField;
	
	@FindBy(xpath = "//a[text()='View Detail Report for this test']")
	public WebElement ViewDetailReportForThisTestLink;
	
	@FindBy(xpath = "//button[@class='btn btn-xs btn-primary finish-handscoring']")
	public WebElement releaseScoreButton;
	
	@FindBy(xpath = "//li[text()='Ready for Scoring']")
	public WebElement ReadyToScoringTab;
	
	@FindBy(xpath = "//li[text()='In Progress']")
	public WebElement InProgressScoringTab;
	
	@FindBy(xpath = "//li[text()='Scoring Complete']")
	public WebElement ScoringCompletedTab;
	
	@FindBy(xpath = "//button[text()='Release All']")
	public WebElement FinishAllButton;
	
	@FindBy(xpath = "//tbody[@class='table-data']/tr")
	public WebElement nostudentForHandScoreRow;
	
	@FindBy(css = ".watable-col-test")
	private List<WebElement> testsFoundList;
	
	@FindBy(css = ".fa-edit")
	private List<WebElement> editIconsList;
	
	@FindBy(css = ".hs-hand-scored-item")
	private List<WebElement> handScoredItemsList;
	
	@FindBy(xpath = ".//div[label[text()='Type']]/div/p")
	private WebElement itemType; 
	
	@FindBy(xpath = "//td[@class='watable-col-roster']")
	public List<WebElement> rosterFoundList;
	
	@FindBy(className = "filtered-list-stats-total")
	public WebElement totalResultCount;
	
	@FindBy(className = "watable-col-content_area")
	public List <WebElement> contentAreaFoundList;
	
	
	
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
	
	public void filterHandScoringItems(String isHandScoring){
		try{
			switch (isHandScoring){
			case "yes":
				waitForElementAndClick(yesFilterCheckBox);
				break;
			case "no":
				waitForElementAndClick(noFilterCheckBox);
				break;
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
				customeWaitTime(2);
				WebElement itemToBeScored = driver.findElement(By
						.xpath("//div[@id='numElement']//button[" + y + "]"));
				waitForElementAndClick(itemToBeScored);
				// Need to change this as drop down  will change to text input box
				//selectOption(score, scorePoint);
				waitForElementAndSendKeys(setScoreInputField, scorePoint);
				customeWaitTime(5);
				waitForElementAndDoubleClick(saveScoreButton);
			}
			
				//waitForElementAndClick(okButton);
				waitTime();
				backToListing();
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
	
	
	public Reports navigateToTestReportDetail() {
		try{
		waitForElementAndClick(ViewDetailReportForThisTestLink);
		customeWaitTime(5);
		}catch(Exception e){
			//TODO
		}
		return new Reports(driver);
	}
	
	public void goToNextAnswer(){
		waitForElementAndClick(nextAnswer);
	}
	
	public int getCountOfTestsFound(){
		return testsFoundList.size();
	}
	
	public void clickOnEditTest(int index){
		editIconsList.get(index).click();
	}
	
	public int getCountOfHandScoringItems(){
		return handScoredItemsList.size();
	}
	
	public String getAnswerItemType(){
		return itemType.getText();
	}
	
	public void clickOnNextStudentAnswer(int index){
		handScoredItemsList.get(index).click();
	}
	
	public List <WebElement> getRosterList(){
		return rosterFoundList;
	}
	
	public String getResult(){
		return waitAndGetElementText(totalResultCount);
	}
	
	public List <WebElement> getContentAreaList(){
		return contentAreaFoundList;
	}
	
}
