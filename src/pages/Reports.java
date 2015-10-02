package pages;

import generic.BasePage;
import generic.BaseTest;

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

	public String viewReport() {
		try {

			takeScreenShot();
			waitForElementAndClick(backLink);
		} catch (Exception e) {
			System.out.print("Report revision Failed " + e.getMessage());
		}
		return "wiew report done";

	}
}
