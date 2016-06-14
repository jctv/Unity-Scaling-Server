package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class AccommodationsReportDetail extends BasePage {
	
	public AccommodationsReportDetail(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//span[text()='First Name']")
	private WebElement firstNameFilter;

	@FindBy(xpath = "//input[@aria-label='Textbox Search student_first_name']")
	private WebElement firstNameSerachInputField;
	
	@FindBy(xpath = "//span[text()='Middle Name']")
	private WebElement middleNameFilter;
	
	@FindBy(xpath = "//input[@aria-label='Textbox Search student_middle_name']")
	private WebElement middleNameSearchInputField;
	
	@FindBy(xpath = "//span[text()='Last Name']")
	private WebElement lastNameFilter;
	
	@FindBy(xpath = "//input[@aria-label='Textbox Search student_last_name']")
	private WebElement lastNameSearchInputField;
	
	@FindBy(xpath = "//span[text()='Accommodation Status']")
	private WebElement accommodationStatusFilter;
	
	@FindBy(xpath = "//span[text()='Assigned']/../i")
	private WebElement accommodationAssignedCheckBox;
	
	@FindBy(xpath = "//span[text()='Not Assigned']/../i")
	private WebElement accommodationNotAssignedCheckBox;
	
	@FindBy(xpath = "//span[text()='Accommodation Type']")
	private WebElement accommodationTypeFilter;
	
	@FindBy(xpath = "//span[text()='Alternate Color Text/ Background']/../i")
	private WebElement alternateColorTextBackgroundCheckBox;
	
	@FindBy(xpath = "//span[text()='Answer Eliminator']/../i")
	private WebElement answerEliminatorCheckBox;
	
	@FindBy(xpath = "//span[text()='Answer Masking']/../i")
	private WebElement answerMaskingCheckBox;
	
	@FindBy(xpath = "//span[text()='Extended Time']/../i")
	private WebElement extendedTimeCheckBox;
	
	@FindBy(xpath = "//span[text()='Line Reader']/../i")
	private WebElement lineReaderCheckBox;
	
	@FindBy(xpath = "//span[text()='Magnification']/../i")
	private WebElement magnificationCheckBox;
	
	@FindBy(xpath = "//span[text()='Test Summary Report']")
	private WebElement testSummaryReportButton;
	
	

	public WebElement getTestSummaryReportButton() {
		return testSummaryReportButton;
	}

	public WebElement getMagnificationCheckBox() {
		return magnificationCheckBox;
	}
	
	public WebElement getFirstNameFilter() {
		return firstNameFilter;
	}

	public WebElement getFirstNameSerachInputField() {
		return firstNameSerachInputField;
	}

	public WebElement getMiddleNameFilter() {
		return middleNameFilter;
	}

	public WebElement getMiddleNameSearchInputField() {
		return middleNameSearchInputField;
	}

	public WebElement getLastNameFilter() {
		return lastNameFilter;
	}

	public WebElement getLastNameSearchInputField() {
		return lastNameSearchInputField;
	}

	public WebElement getAccommodationStatusFilter() {
		return accommodationStatusFilter;
	}

	public WebElement getAccommodationAssignedCheckBox() {
		return accommodationAssignedCheckBox;
	}

	public WebElement getAccommodationNotAssignedCheckBox() {
		return accommodationNotAssignedCheckBox;
	}

	public WebElement getAccommodationTypeFilter() {
		return accommodationTypeFilter;
	}

	public WebElement getAlternateColorTextBackgroundCheckBox() {
		return alternateColorTextBackgroundCheckBox;
	}

	public WebElement getAnswerEliminatorCheckBox() {
		return answerEliminatorCheckBox;
	}

	public WebElement getAnswerMaskingCheckBox() {
		return answerMaskingCheckBox;
	}

	public WebElement getExtendedTimeCheckBox() {
		return extendedTimeCheckBox;
	}

	public WebElement getLineReaderCheckBox() {
		return lineReaderCheckBox;
	}
	
	
	public Reports goToTestDetailReport(){
		waitForElementAndClick(getTestSummaryReportButton());
		return new Reports(driver);
	}
	
	
	public String getAccommodationStatus(String lastname , int index){
		WebElement accoommodation = driver.findElement(By
				.xpath("//td[text()='" + lastname + "']/following-sibling::td[" + index + "]"));
		return waitAndGetElementText(accoommodation);
	}
}
