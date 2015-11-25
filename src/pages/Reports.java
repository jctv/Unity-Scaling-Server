package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Reports extends BasePage {

	BasePage base;
	BaseTest test;

	public Reports(WebDriver driver) {
		super(driver);
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

	@FindBy(xpath = "//span[text()='Class']/../../ul//div[text()='Click to Select']")
	public WebElement selectClassFilter;

	@FindBy(xpath = "//div[@class='layoutHorizontalLeftPane col-md-4']//input[@id='searchAutoComplete']")
	public WebElement searchClassFilterPopup;

	@FindBy(xpath = "//div[@class='layoutHorizontalLeftPane col-md-4']//span[@id='searchButton']")
	public WebElement searchButtonClassFilterPopup;

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
			customeWaitTime(2);
			waitForElementAndClick(resetSearchFilter);
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

}
