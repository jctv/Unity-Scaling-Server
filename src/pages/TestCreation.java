package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCreation extends BasePage {

	BasePage base;
	BaseTest test;

	public TestCreation(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement homeLink;
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/span[1]")
	public WebElement createTestLink;

	@FindBy(id = "test-option-0")
	public WebElement assessmentRadio;

	@FindBy(id = "tName")
	public WebElement testNameField;

	@FindBy(id = "tContent")
	public WebElement testContentField;
	// *[@id="tContent"]/option[6]
	@FindBy(xpath = "//*[@id='tContent']/option[6]")
	public WebElement testContentOption;

	@FindBy(id = "tGrade")
	public WebElement testGradeField;
	// *[@id="tGrade"]/option[6]
	@FindBy(xpath = "//*[@id='tGrade']/option[6]")
	public WebElement testGradeOption;

	@FindBy(id = "searchAutoComplete")
	public WebElement searchAutoCompleteField;

	@FindBy(id = "searchButton")
	public WebElement searchButton;

	@FindBy(xpath = "//*[@id='sortable1']/li")
	public WebElement item1;

	@FindBy(id = "tDetails")
	public WebElement detailsButton;

	@FindBy(id = "class")
	public WebElement classField;

	@FindBy(id = "book")
	public WebElement bookField;

	@FindBy(xpath = "//*[@id='book']/option[7]")
	public WebElement bookOption;

	@FindBy(id = "keywords")
	public WebElement keywordsField;

	@FindBy(id = "description")
	public WebElement descriptionField;

	@FindBy(id = "std")
	public WebElement stdField;

	@FindBy(xpath = "//*[@id='treeNavigation']/ul/li[20]/div/span")
	public WebElement stdOption;

	@FindBy(id = "globalModalOKCancelSaveButton")
	public WebElement saveStdButton;

	@FindBy(xpath = "//*[@id='metadataFields']/div/form/div[3]/button")
	public WebElement saveDetailsButton;

	@FindBy(xpath = "//*[@id='mandatoryFields']/form/div[5]/button")
	public WebElement toolsButton;

	@FindBy(id = "calculator-check")
	public WebElement calculator_check;

	@FindBy(id = "ruler-check")
	public WebElement ruler_check;

	@FindBy(xpath = "//*[@id='globalModalViewBody']/div/form[9]/div/div/button")
	public WebElement saveToolsButton;

	@FindBy(id = "globalModalInfoOkButton")
	public WebElement okButton;

	@FindBy(id = "sortable2")
	public WebElement target;
	
	@FindBy(xpath = "//*[@id='region-workspace']/div/div[2]/div[3]/div/div[1]/div/div[2]/span/button[3]")
	public WebElement saveTestButton;

	@FindBy(xpath = "//*[@id='testSaved']/div/div/div[1]/button")
	public WebElement xbutton;
	                 
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement testsLink;
	
	@FindBy(id = "contentCreateInputName")
	public WebElement contentCreateInputName;
	
	@FindBy(id = "contentCreateInputDescription")
	public WebElement contentCreateInputDescription;
	
	

	@FindBy(xpath = "//*[@id='quickViewContentCreate']/div/form/button[1]")
	public WebElement  createAndEditButton;
	
	
	@FindBy(xpath = "//*[@id='quickViewContentCreate']/div/form/div[1]/select")
	public WebElement bankDropDown;
	
	
	
	
	public void createTest(String testName) {
		try {
			waitTime();
			waitForElementAndClick(createTestLink);
			waitForElementAndSendKeys(bankDropDown, "My Tests");
			waitForElementAndSendKeys(contentCreateInputName, testName);
			waitForElementAndSendKeys(contentCreateInputDescription, testName);
			waitForElementAndClick(createAndEditButton);
			

			waitForElementAndClick(item1);

			for (int x = 0; x < 1; x++) {
				String items = "item 2";
				
				//itemsArray[0] = items;
				waitForElementAndSendKeys(searchAutoCompleteField,
						items);
				waitForElementAndClick(searchButton);
				
				dragAndDrop(item1, target);
				clearSearchFilter();
			}
			System.out.println("Items added to the test " + testName);
			
			waitForElementAndSendKeys(testContentField, "N/A");
			selectOption(testContentField, "N/A");

			waitForElementAndSendKeys(testGradeField, "05");
			selectOption(testGradeField, "05");
			testGradeField.click();
			
			waitForElementAndClick(saveTestButton);
			waitForElementAndClick(xbutton);
			/*waitForElementAndClick(detailsButton);
			waitForElementAndSendKeys(keywordsField, testName);
			waitForElementAndClick(saveDetailsButton);
			waitForElementAndClick(okButton);*/

			try {
				waitForElementAndClick(xbutton);
			} catch (Exception e) {
				// TODO: handle exception
			}
			/*waitForElementAndClick(toolsButton);
			waitForElementAndClick(calculator_check);
			waitForElementAndClick(ruler_check);
			waitForElementAndClick(saveToolsButton);
			waitForElementAndClick(okButton);*/
			waitTime();
			System.out.println("Test created success");
			waitForElementAndClick(testsLink);
			waitTime();
			waitForElementAndClick(homeLink);
			try {
				waitForElementAndClick(homeLink);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			System.out.println("Test Creation Failed ");
		}
		 
	}
}
