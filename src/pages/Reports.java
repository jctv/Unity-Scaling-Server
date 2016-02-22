package pages;

import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Reports extends BasePage {

	BasePage base;
	BaseTest test;

	public Reports(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[@id='layoutHorizontalRightPane']/div/div/div[3]/table/tbody/tr[1]/td[2]/div/button[2]/i")
	public WebElement scoreTestIcon;

	@FindBy(xpath = "//*[@id='scores']/div/form/div[1]/div/select")
	public WebElement score1;

	@FindBy(xpath = "//*[@id='scores']/div/form/div[1]/div/select/option[3]")
	public WebElement scoreOption1;

	@FindBy(xpath = "//a[@href='#dashboard']")
	public WebElement backLink;

	@FindBy(id = "testType")
	public WebElement testTypeField;

	@FindBy(xpath = "//*[@id='testType']/option[2]")
	public WebElement testTypeOption;

	@FindBy(id = "className")
	public WebElement className;

	@FindBy(xpath = "//*[@id='className']/option[2]")
	public WebElement classNameOption;

	@FindBy(xpath = "//*[@id='contentLevel']")
	public WebElement contentLevelField;

	@FindBy(xpath = "//*[@id='contentLevel']/option[6]")
	public WebElement contentLevelOption;

	@FindBy(id = "name")
	public WebElement namelField;

	@FindBy(xpath = "//*[@id='name']/option[2]")
	public WebElement nameOption;

	@FindBy(id = "studentName")
	public WebElement studentNameField;

	@FindBy(xpath = "//*[@id='studentName']/option[2]")
	public WebElement studentNameOption;

	@FindBy(id = "testName")
	public WebElement testNameField;

	@FindBy(xpath = "//*[@id='testName']/option[2]")
	public WebElement testNameOption;

	@FindBy(id = "viewReport")
	public WebElement viewReportButton;

	@FindBy(xpath = "//span[text()='Class']")
	public WebElement classFilter;

	@FindBy(xpath = "//span[text()='Status']")
	public WebElement testStatus;

	@FindBy(xpath = "//span[text()='not started']/../i")
	public WebElement notStartedCheckbox;

	@FindBy(xpath = "//span[text()='in progress']/../i")
	public WebElement inProgressCheckbox;

	@FindBy(xpath = "//span[text()='completed']/../i")
	public WebElement completedCheckbox;

	@FindBy(xpath = "//span[text()='scored']/../i")
	public WebElement scoredCheckbox;

	@FindBy(xpath = "//span[text()='Performance Level']")
	public WebElement testPerformanceLevel;

	@FindBy(xpath = "//span[text()='Reporting Category']")
	public WebElement testReportingCategory;

	@FindBy(xpath = "//span[text()='Content Area']")
	public WebElement contentAreaFilter;

	@FindBy(xpath = "//span[text()='Class']/../../ul//div[text()='Click to Select']")
	public WebElement selectClassFilter;

	@FindBy(xpath = "//div[@class='layoutHorizontalLeftPane col-md-4']//input[@id='searchAutoComplete']")
	public WebElement searchClassFilterPopup;

	@FindBy(xpath = "//div[@class='layoutHorizontalLeftPane col-md-4']//span[@id='searchButton']")
	public WebElement searchButtonClassFilterPopup;

	@FindBy(xpath = "//h2[@class='page-title']")
	public WebElement testEventTitle;

	@FindBy(xpath = "//a/i")
	public WebElement backToReportLink;
	
	@FindBy(xpath = "//tr[@class=‘even’ or @class=‘odd’]")
	private List< WebElement > rows;
	
	@FindBy(xpath = "//h2[text()='Handscore This Test']")
	public WebElement HandscoreThisTestButton;
	

	public String viewReport() {
		try {

			takeScreenShot();
			waitForElementAndClick(backLink);
		} catch (Exception e) {
			System.out.print("Report revision Failed " + e.getMessage());
		}
		return "wiew report done";

	}

	public void SearchReport(String reportCriteria) {
		try {
			waitAndClearField(searchAutoComplete);
			customeWaitTime(2);
			waitForElementAndSendKeys(searchAutoComplete, reportCriteria);
			customeWaitTime(2);
			waitForElementAndClick(searchButton);
			customeWaitTime(2);

		} catch (Exception e) {
		}
		System.out.print("No reports are available for criteria "
				+ reportCriteria);
	}

	public void filterReportByContentArea(String contentArea) {
		try {
			waitForElementAndClick(contentAreaFilter);
			customeWaitTime(5);
			WebElement contentAreaCheckBox = driver
					.findElement(By
							.xpath("//div[@class='layoutHorizontalLeftPane col-md-2']//span[text()='"
									+ contentArea + "']/../i"));
			waitForElementAndClick(contentAreaCheckBox);

		} catch (Exception e) {

			System.out.print("No reports are available for Content area "
					+ contentArea);

		}

	}

	public void filterReportByClassRoster(String className) {
		try {
			customeWaitTime(10);
			waitForElementAndClick(classFilter);
			customeWaitTime(5);
			waitForElementAndClick(selectClassFilter);
			customeWaitTime(10);
			waitAndClearField(searchClassFilterPopup);
			waitForElementAndDoubleClick(searchClassFilterPopup);
			waitForElementAndSendKeys(searchClassFilterPopup, className);
			customeWaitTime(5);
			waitForElementAndDoubleClick(searchButtonClassFilterPopup);
			customeWaitTime(5);
			WebElement serachedItembank = driver.findElement(By
					.xpath("//tr[@class='data-row']//td[text()='" + className
							+ "']"));
			waitForElementAndDoubleClick(serachedItembank);
			customeWaitTime(5);
			waitForElementAndClick(globalModalOKCancelSaveButton);
			customeWaitTime(5);

		} catch (Exception e) {
			System.out.println("Unable to Filter the class  " + className);

		}
	}

	public String getTestName(String testName) {
		WebElement reportTestName = driver.findElement(By
				.xpath("//div[text()='" + testName + "']"));
		return reportTestName.getText();
	}

	public String getTestDuration(String testName) {
		WebElement reportTestDuration = driver.findElement(By
				.xpath("//div[text()='" + testName + "']/../div[2]//span[2]"));
		return reportTestDuration.getText();
	}

	public String getNoOfStudentCompletedTest(String testName) {
		WebElement countStudentCompletedTest = driver
				.findElement(By
						.xpath("//div[text()='"
								+ testName
								+ "']/../div[3]//div[text()='Completed']/../div[2]/span"));
		return countStudentCompletedTest.getText();
	}

	public String getNoOfStudentStartedTest(String testName) {
		WebElement countStudentStartedTest = driver
				.findElement(By.xpath("//div[text()='" + testName
						+ "']/../div[3]//div[text()='Started']/../div[2]/span"));
		return countStudentStartedTest.getText();
	}

	public String getNoOfStudentNotStartedTest(String testName) {
		WebElement countStudentNotStartedTest = driver
				.findElement(By
						.xpath("//div[text()='"
								+ testName
								+ "']/../div[3]//div[text()='Not Started']/../div[2]/span"));
		return countStudentNotStartedTest.getText();
	}

	public String getNoOfStudentInQuantile(String testName, int index,
			String desc) {
		WebElement studentCount = driver
				.findElement(By
						.xpath("//div[text()='"
								+ testName
								+ "']/../div[4]//div[@class='col-md-3 row-collapse']["
								+ index
								+ "]/../../../../../../../div[2]/div[2]//div[@class='col-md-3 row-collapse report-unit-quartile']["
								+ index + "]/span"));
		return studentCount.getText();
	}

	public String getTotalScore(String testName) {
		WebElement totalScore = driver
				.findElement(By
						.xpath("//div[text()='"
								+ testName
								+ "']/../div[4]/../../../div[3]//span[@class='test-summary-unit-total-percent']"));
		return totalScore.getText();
	}

	public String getReportCategory(String testName, int index) {
		WebElement reportCategory = driver
				.findElement(By
						.xpath("//div[text()='"
								+ testName
								+ "']/../div[4]/../../../div[5]/div/div/div["
								+ index
								+ "]//div[@class='progress test-summary-progress-bar row-collapse']/span[1]"));
		return reportCategory.getText();
	}

	public String getReportCategoryPercent(String testName, int index) {
		WebElement reportCategoryPercent = driver
				.findElement(By
						.xpath("//div[text()='"
								+ testName
								+ "']/../div[4]/../../../div[5]/div/div/div["
								+ index
								+ "]//div[@class='progress test-summary-progress-bar row-collapse']/span[2]"));
		return reportCategoryPercent.getText();
	}

	public void openTestEventDetail(String testEvent) {
		try{
		WebElement testEventName = driver.findElement(By.xpath("//div[text()='"
				+ testEvent + "']"));
		customeWaitTime(5);
		waitForElementAndClick(testEventName);
		customeWaitTime(5);
		
		}catch(Exception e){
			
			System.out.println("Unable to open the test detail page for test --- > " + testEvent);
		}
	}

	public String getTestEventTitle() {
		return waitAndGetElementText(testEventTitle);
	}

	public String getTestEventDetail(String lastName, int index, String desc) {
		customeWaitTime(5);
		WebElement testEventInfo = driver.findElement(By.xpath("//td[text()='"
				+ lastName + "']/following-sibling::td[" + index + "]"));
		return waitAndGetElementText(testEventInfo);

	}

	public String getTestClassAverageDetail(int index, String desc) {
		customeWaitTime(5);
		WebElement testEventInfo = driver.findElement(By
				.xpath("//td[text()='Class Average']/following-sibling::td["
						+ index + "]"));
		return waitAndGetElementText(testEventInfo);
	}

	public void filterTestDetailByPerformanceLevel(String quantile) {
		waitForElementAndClick(testPerformanceLevel);
		customeWaitTime(2);
		WebElement checkbox = driver.findElement(By.xpath("//span[text()='"
				+ quantile + "']/../i"));
		customeWaitTime(5);
		waitForElementAndClick(checkbox);
		customeWaitTime(5);
	}

	public void filterTestDetailByStatus(String status) {
		waitForElementAndClick(testStatus);
		customeWaitTime(2);
		WebElement checkbox = driver.findElement(By.xpath("//span[text()='"
				+ status + "']/../i"));
		customeWaitTime(5);
		waitForElementAndClick(checkbox);
		customeWaitTime(5);
	}

	public boolean verifyStudentsByStatus(String status) {
		int usersByStatus = Integer
				.parseInt(waitForElementPresenceAndGetText("(//div[@data-status='"
						+ status + "'])[1]"));
		waitForElementPresenceAndClick("(//div[@data-status='" + status
				+ "'])[1]");	
		customeWaitTime(2);
		waitForElementAndClick(backToReportLink);
		if (rows.size() == (usersByStatus - 1)) {
			return true;
		} else {
			return false;
		}
	}

	public HandScoring navigateToHandScore() {
		try{
		waitForElementAndClick(HandscoreThisTestButton);
		customeWaitTime(5);
		}catch(Exception e){
			//TODO
		}
		return new HandScoring(driver);
	}
	
	

 public String getScoreStatusinTestDetail(String studentLName){
	 String status = "";
	 try{
		 WebElement statusElement = driver.findElement(By.xpath("//td[text()='"+ studentLName +"']/following-sibling::td[3]"));
		 
		 status = statusElement.getText();
		 
	 }catch(Exception e ){
		 
	 }
	 return status;
 }	
 
public void verifyStudentHandScore(String studentLName , int itemCount , String score){
		for(int item = 1 ; item <= itemCount ; item ++ ){
			try{
			WebElement itemScoreElement = driver.findElement(By.xpath("//td[text()='"+ studentLName +"']/../td[@class='item-score item-score-CR']["+ item +"]/a"));
			
			if(itemScoreElement.getText().equals(score)){
				System.out.println("Score ---> " + score + " is updated succesfully");
				
			}else{
				System.out.println("Score is not updated properly");

			}
		}catch(Exception e){
			System.out.println("Error while getting the Score");

		}
		
 }
 }
}

