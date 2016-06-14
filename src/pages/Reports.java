package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@FindBy(xpath = "//button[text()='Handscore This Test']")
	public WebElement HandscoreThisTestButton;
	
	@FindBy(xpath = "//button[text()='Close']")
	public WebElement resourceCloseButton;
	
	@FindBy(css =".panel-group a:not(.collapsed)")
	public WebElement expandedStudentTestDetail;
	
	@FindBy(xpath = ".//*[@id='region-workspace']//div[2]/div[3]/div[2]/div/table/tbody/tr[1]/td[1]")
	public WebElement testReportTableOneEntry;
	
	@FindBy(xpath = "//h2[@class='page-title pull-left']")
	public WebElement testTitle;
	
	Map<Integer , List<String>> itemDetail = new HashMap<Integer, List<String>>();    

	List <String> itemInfo = new ArrayList <String> ();
	
	@FindBy(xpath = "//span[text()='Accommodations Report']")
	public WebElement AccommodationsReportButton;
	
	
	public AccommodationsReportDetail gotoAccommodationsReportDetail(){
		waitForElementAndClick(AccommodationsReportButton);
		return new AccommodationsReportDetail(driver);
	}
	
	
	/**
	 * This is the method for view the report
	 * @return
	 */
	public String viewReport() {
		try {

			takeScreenShot();
			waitForElementAndClick(backLink);
		} catch (Exception e) {
			System.out.print("Report revision Failed " + e.getMessage());
		}
		return "wiew report done";

	}

	/**
	 * This is the method for search  the report for a criteria
	 * @param reportCriteria
	 */
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

	/**
	 * This is the method for filter the report by content area
	 * @param contentArea
	 */
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

	/**
	 * This is the method for filter the report by Class Roster
	 * @param className
	 */
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

	/**
	 * This is the method to get the test name
	 * @param testName
	 * @return  test name
	 */
	public String getTestName(String testName) {
		WebElement reportTestName = driver.findElement(By
				.xpath("//div[text()='" + testName + "']"));
		return reportTestName.getText();
	}

	/**
	 * This is the method to get the test duration
	 * @param testName
	 * @return
	 */
	public String getTestDuration(String testName) {
		WebElement reportTestDuration = driver.findElement(By
				.xpath("//div[text()='" + testName + "']/../div[2]//span[2]"));
		return reportTestDuration.getText();
	}

	
	/**
	 * This the method to get the number of  student completed the test
	 * @param testName
	 * @return student name
	 */
	public String getNoOfStudentCompletedTest(String testName) {
		WebElement countStudentCompletedTest = driver
				.findElement(By
						.xpath("//div[text()='"
								+ testName
								+ "']/../div[3]//div[text()='Completed']/../div[2]/span"));
		return countStudentCompletedTest.getText();
	}

	/**
	 * This is the method to get the count of student for who started the test
	 * @param testName
	 * @return count student started 
	 */
	public String getNoOfStudentStartedTest(String testName) {
		WebElement countStudentStartedTest = driver
				.findElement(By.xpath("//div[text()='" + testName
						+ "']/../div[3]//div[text()='Started']/../div[2]/span"));
		return countStudentStartedTest.getText();
	}

	/**
	 * This is the method for get the count of not started student
	 * @param testName
	 * @return
	 */
	public String getNoOfStudentNotStartedTest(String testName) {
		WebElement countStudentNotStartedTest = driver
				.findElement(By
						.xpath("//div[text()='"
								+ testName
								+ "']/../div[3]//div[text()='Not Started']/../div[2]/span"));
		return countStudentNotStartedTest.getText();
	}

	/**
	 * This is the method to get the student quantile
	 * @param testName
	 * @param index
	 * @param desc
	 * @return
	 */
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

	/**
	 * This is method to get the toltal score
	 * @param testName
	 * @return
	 */
	public String getTotalScore(String testName) {
		WebElement totalScore = driver
				.findElement(By
						.xpath("//div[text()='"
								+ testName
								+ "']/../div[4]/../../../div[3]//span[@class='test-summary-unit-total-percent']"));
		return totalScore.getText();
	}

	
	/**
	 * This is the method for get the report category
	 * @param testName
	 * @param index
	 * @return
	 */
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

	/**
	 * This method to get report category
	 * @param testName
	 * @param index
	 * @return category
	 */
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

	/**
	 * This is the method to navigate the test detail 
	 * @param testEvent 
	 */
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

	/**
	 * This is the method for get the test event title
	 * @return
	 */
	public String getTestEventTitle() {
		return waitAndGetElementText(testEventTitle);
	}

	
	/**
	 * This is the method to get the test event detail
	 * @param lastName
	 * @param index
	 * @param desc
	 * @return
	 */
	public String getTestEventDetail(String lastName, int index, String desc) {
		customeWaitTime(5);
		WebElement testEventInfo = driver.findElement(By.xpath("//td[text()='"
				+ lastName + "']/following-sibling::td[" + index + "]"));
		return waitAndGetElementText(testEventInfo);

	}

	/**
	 * This is the method to get the class average detail
	 * @param index
	 * @param desc
	 * @return
	 */
	public String getTestClassAverageDetail(int index, String desc) {
		customeWaitTime(5);
		WebElement testEventInfo = driver.findElement(By
				.xpath("//td[text()='Class Average']/following-sibling::td["
						+ index + "]"));
		return waitAndGetElementText(testEventInfo);
	}

	
	/**
	 * This is the method to get the test detail by performace level
	 * @param quantile
	 */
	public void filterTestDetailByPerformanceLevel(String quantile) {
		waitForElementAndClick(testPerformanceLevel);
		customeWaitTime(2);
		WebElement checkbox = driver.findElement(By.xpath("//span[text()='"
				+ quantile + "']/../i"));
		customeWaitTime(5);
		waitForElementAndClick(checkbox);
		customeWaitTime(5);
	}

	/**
	 * This is the method for filter by test detail by status
	 * @param status
	 */
	public void filterTestDetailByStatus(String status) {
		waitForElementAndClick(testStatus);
		customeWaitTime(2);
		WebElement checkbox = driver.findElement(By.xpath("//span[text()='"
				+ status + "']/../i"));
		customeWaitTime(5);
		waitForElementAndClick(checkbox);
		customeWaitTime(5);
	}

	/**
	 * This is the method for
	 * @param status
	 * @return
	 */
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

	/**
	 * This is the method for navigate to Hand Score
	 * @return
	 */
	public HandScoring navigateToHandScore() {
		try{
		waitForElementAndClick(HandscoreThisTestButton);
		customeWaitTime(5);
		}catch(Exception e){
			//TODO
		}
		return new HandScoring(driver);
	}
	
	
/**
 * This is the method to get the score status  
 * @param studentLName
 * @return status
 */
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
	  try{
	    customeWaitTime(10);
		for(int item = 1 ; item <= itemCount ; item ++ ){
			//WebElement itemScoreElement = driver.findElement(By.xpath("//td[text()='"+ studentLName +"']/../td[@class='item-score item-score-CR']["+ item +"]/a"));
			WebElement itemScoreElement = driver.findElement(By.xpath("//table[contains(@id,'DataTables')]/tbody/tr[1]/td[text()='"+ studentLName +"']/following-sibling::td[contains(@class,'item-score-CR')]"));
			
			if(itemScoreElement.getText().equals(score)){
				System.out.println("Score ---> " + score + " is updated succesfully");
				
			}else{
				System.out.println("Score is not updated properly");

			}
		}
		
	  }catch(Exception e){
			System.out.println("Error while getting the Score");

		}
 }


  public void openStudentTestReport(String testName){
	  try{
		  WebElement studentTestRow = driver.findElement(By
					.xpath("//h4[@class='panel-title']//a[contains(text(), '"+ testName +"')]"));
			waitForElementAndClick(studentTestRow);
			customeWaitTime(5);
			
	  }catch(Exception e){
		  
	  }
	
   }
  
  public Standards openExploreStrand(String testName){
	  try{
		  WebElement strandExplorebutton = driver.findElement(By
					.xpath("//h4/a[contains(text(),'"+ testName +"')]/../../..//div[2]//tr[1]//button[@class='btn btn-primary ir-open-resource' and @data-type='Game']"));
			waitForElementAndClick(strandExplorebutton);
			customeWaitTime(5);
			
	  }catch(Exception e){
		  
	  }
	  return new Standards(driver);
   }
  
  public Standards openLearnStrand(String testName){
	  try{
		  WebElement strandLearnbutton = driver.findElement(By
					.xpath("//h4/a[contains(text(),'"+ testName +"')]/../../..//div[2]//tr[1]//button[@class='btn btn-primary ir-open-resource' and @data-type='Video']"));
		  
			waitForElementAndClick(strandLearnbutton);
			customeWaitTime(5);
			
	  }catch(Exception e){
		  
	  }
	 return new Standards(driver);
	
   }
  
  
  public String getStudentTestDetailName(){
	  return expandedStudentTestDetail.getText().split(" ")[0];
  }
  
  public void verifyAllResources() {
		try {
			WebElement resourceGroup = driver.findElement(By
					.xpath(".//*[@id='ir-result-container']/div/ul"));
			waitForElementVisible(resourceGroup);
			customeWaitTime(5);
			List<WebElement> resoureList = resourceGroup.findElements(By
					.tagName("li"));
			for (WebElement resource : resoureList) {
				customeWaitTime(2);
				String resourceName = resource.getText();
				waitForElementAndClick(resource);
				customeWaitTime(10);
				waitAndSwitchTOFrame("ir-resource-view");
				//driver.switchTo().frame("ir-resource-view");
				customeWaitTime(2);
				if (resourceName.equals(waitAndGetElementAttribute(
						resourceImage, "alt"))) {
					String resoureHref = waitAndGetElementAttribute(
							openResourceLink, "href");
					if (!resoureHref.isEmpty()) {
						System.out.println("Resoreces link - - --> "
								+ resoureHref);
					}
				}
				
				//driver.switchTo().defaultContent();
				waitAndSwitchToDefaultContent();
			}
		} catch (Exception e) {
			System.out
					.println("Error ### while getting the reosource inforamtion");

		}

	}
  
	public Map <Integer , List<String>> getStudentReport() {
		WebElement Webtable = driver.findElement(By.xpath("//*[@id='DataTables_Table_0']"));
		List<WebElement> TotalRowCount = Webtable.findElements(By
				.xpath("//*[@id='DataTables_Table_0']/tbody/tr"));

		System.out.println("No. of Rows in the WebTable: "
				+ TotalRowCount.size());
		// Now we will Iterate the Table and print the Values
		int rowIndex = 1;
		for (WebElement rowElement : TotalRowCount) {
			List<WebElement> TotalColumnCount = rowElement.findElements(By
					.xpath("td"));
			int ColumnIndex = 1;
			for (WebElement colElement : TotalColumnCount) {
				System.out.println("Row " + rowIndex + " Column " + ColumnIndex
						+ " Data " + colElement.getText());
				itemInfo.add(colElement.getText());
				ColumnIndex = ColumnIndex + 1;
			}
			itemDetail.put(rowIndex,itemInfo);
			rowIndex = rowIndex + 1;

		}
		return itemDetail;
	}
  
}

