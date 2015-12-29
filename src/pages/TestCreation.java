package pages;

import java.util.List;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath = ".//*[@id='region-navigation']/ul/li[1]/a")
	public WebElement homeLink;
	
	@FindBy(xpath = "//a[text()='Create']")
	public WebElement createTestLink;

	@FindBy(id = "test-option-0")
	public WebElement assessmentRadio;
	
	@FindBy(id = "printTest")
	public WebElement testPrintButton;
	

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
	                 
	@FindBy(xpath = ".//*[@id='region-navigation']/ul/li[1]/a")
	public WebElement testsLink;
	
	@FindBy(id = "contentCreateInputName")
	public WebElement contentCreateInputName;
	
	@FindBy(id = "contentCreateInputDescription")
	public WebElement contentCreateInputDescription;
	
	

	@FindBy(xpath = "//*[@id='quickViewContentCreate']/div/form/button[1]")
	public WebElement  createAndEditButton;
	
	
	@FindBy(xpath = "//*[@id='quickViewContentCreate']/div/form/div[1]/select")
	public WebElement bankDropDown;
	
	@FindBy(xpath = "//button[@class='btn btn-xs btn-link editRow']")
	public WebElement testEditIcon;
	
	@FindBy(xpath = "//button[@class='btn btn-xs btn-link deleteRow']")
	public WebElement testDeleteIcon;
	
	
	@FindBy(xpath = "//button[@class='btn btn-xs btn-link copyRow']")
	public WebElement testCopyIcon;
	
	@FindBy(xpath = "//button[@class='btn btn-xs btn-link previewRow']")
	public WebElement testViewIcon;
	
	@FindBy(xpath = "//select[@name='bank']")
	public WebElement selectTestBank;
	
	@FindBy(xpath = "//span[@class='filtered-list-stats-total']")
	public WebElement testResultCount;
	
	@FindBy(id = "globalModalDelete")
	public WebElement deleteTestPopUp;
	
	@FindBy(id = "globalModalDeleteButton")
	public WebElement deleteButtonTestPopUp;
	
	@FindBy(xpath = "//span[@id='scheduleTest']")
	public WebElement scheduleTestLink;
	
	@FindBy(id = "copy-bank")
	public WebElement selectCopyTestBank;
	
	@FindBy(id = "copyName")
	public WebElement copyTestField;
	
	@FindBy(xpath = "//button[@class='btn btn-primary object-copy']")
	public WebElement copyTestButton;
	
	@FindBy(xpath = ".//*[@id='quickViewContentCreate']/div/form/div[1]/div/button")
	public WebElement testBankDropdown;
	
	@FindBy(xpath = "//span[text()='Banks']")
	public WebElement bankFilter;
	
	@FindBy(xpath = "//span[text()='Banks']/../../ul//div[text()='Click to Select']")
	public WebElement selectBankFilter;

	@FindBy(xpath = "//div[@class='layoutHorizontalLeftPane col-md-4']//input[@id='searchAutoComplete']")
	public WebElement searchItemBankFilterPopup;

	@FindBy(xpath = "//div[@class='layoutHorizontalLeftPane col-md-4']//span[@id='searchButton']")
	public WebElement searchButtonItemBankFilterPopup;

	@FindBy(xpath = "//span[text()='Banks']/../../ul//div/div/div")
	public WebElement filteredItemBank;
	
	@FindBy(xpath = "//input[@id='item-add-count']")
	public WebElement itemAddCountField;
	
	@FindBy(xpath = "//button[@class='btn btn-default btn-sm add-items']")
	public WebElement itemAddButton;
	
	@FindBy(xpath = "//div[@class='dropdown-menu open']//input")
	public WebElement searchItemBankInputField;
	
	public void createTest(String testName , String testBankName ,  String itemName) {
		try {
			waitForElementAndClick(createTestLink);
			customeWaitTime(10);
			if(bankDropDown.isDisplayed()){
				selectOption(bankDropDown, testBankName);

			}else{
				selectTestBank(testBankName);
			}
			//selectTestBank(testBankName);
			customeWaitTime(2);
			//waitForElementAndSendKeys(bankDropDown, "My Tests");
			waitForElementAndSendKeys(contentCreateInputName, testName);
			waitForElementAndSendKeys(contentCreateInputDescription, testName);
			waitForElementAndClick(createAndEditButton);
			clearSearchFilter();
			waitForElementAndSendKeys(searchAutoCompleteField,
					itemName);
			waitForElementAndClick(searchButton);
			waitForElementAndClick(item1);
			dragAndDrop(item1, target);
			clearSearchFilter();
			/*for (int x = 0; x < 1; x++) {
				String items = "item 2";
				
				//itemsArray[0] = items;
				waitForElementAndSendKeys(searchAutoCompleteField,
						items);
				waitForElementAndClick(searchButton);
				
				dragAndDrop(item1, target);
				clearSearchFilter();
			}*/
			System.out.println("Items " + itemName + " added to the test " + testName);
			
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
			customeWaitTime(5);
			System.out.println("Test "+ testName +" created successfully ");
			waitForElementAndClick(testsLink);
			customeWaitTime(5);
			/*waitForElementAndClick(homeLink);
			try {
				waitForElementAndClick(homeLink);
			} catch (Exception e) {
				// TODO: handle exception
			}*/
		} catch (Exception e) {
			System.out.println("Test "+ testName +" Creation Failed ");
		}
		 
	}
	
	/**
	 * It is overloaded method
	 * 
	 * @param testName
	 * @param testBankName
	 * @param itemName
	 */
	
	public void createTestWithMultipleItems(String testName , String testBankName ,  String itemBank , int itemCount) {
		try {
			waitForElementAndClick(createTestLink);
			customeWaitTime(5);
			//selectOption(bankDropDown, testBankName);
			selectTestBank(testBankName);
			customeWaitTime(5);
			waitForElementAndSendKeys(contentCreateInputName, testName);
			waitForElementAndSendKeys(contentCreateInputDescription, testName);
			waitForElementAndClick(createAndEditButton);
			filterItemBank(itemBank);
			List <WebElement>  totalItems = driver.findElements(By.xpath("//ul[@id='sortable1']//li"));
			for (int i=totalItems.size(); i >= 1; i--){
				WebElement itemToSelect = driver.findElement(By.xpath("//ul[@id='sortable1']//li["+ i +"]"));
				customeWaitTime(1);
				dragAndDrop(itemToSelect, target);
				customeWaitTime(1);
				
				List <WebElement>  totalDropedItems = driver.findElements(By.xpath("//ul[@id='sortable2']//li"));
				
				
			}
			waitForElementAndSendKeys(testContentField, "N/A");
			selectOption(testContentField, "N/A");

			waitForElementAndSendKeys(testGradeField, "05");
			selectOption(testGradeField, "05");
			waitForElementAndClick(saveTestButton);
			customeWaitTime(5);
			waitForElementAndClick(xbutton);
			customeWaitTime(5);
			try {
				waitForElementAndClick(xbutton);
			} catch (Exception e) {
				// TODO: handle exception
			}
			customeWaitTime(5);
			System.out.println("Test "+ testName +" created successfully ");
			waitForElementAndClick(testsLink);
			customeWaitTime(5);
			/*waitForElementAndClick(homeLink);
			try {
				waitForElementAndClick(homeLink);
			} catch (Exception e) {
				// TODO: handle exception
			}*/
		} catch (Exception e) {
			System.out.println("Test "+ testName +" Creation Failed ");
		}
		 
	}
	
	public void searchTest(String test){
		try{
		 searchAutoComplete.clear();
		  customeWaitTime(5);
		  waitForElementAndSendKeys(searchAutoComplete, test);
		  waitForElementAndClick(searchButton);
		  customeWaitTime(5);
		}catch(Exception e){
			System.out.println("Unable to find the Test  -->  "  + test);

		}
		
	}
	
	
	public String getSharedTestBank(String testBankName){
		String testBank = null ;
		customeWaitTime(5);
		waitForElementAndClick(createTestLink);
		customeWaitTime(5);
		List<WebElement > itemBankOptions = getDropDownOptions(selectTestBank);
		for (WebElement itemBankOption : itemBankOptions){
			try{
			if(itemBankOption.getText().equals(testBankName)){
			   System.out.println(itemBankOption.getText());
			   testBank = itemBankOption.getText();
			   break;
			}
			
			}catch(Exception e ){
				System.out.println(testBankName + " is not available in Test bank drop down" );
			}
		}
		return testBank;
	}
	
	
	public void deleteTest(String testName){
		try{
		searchAutoCompleteField.clear();
		searchTest(testName);	
		customeWaitTime(5);
		waitForElementAndClick(testDeleteIcon);
		customeWaitTime(5);
		if(deleteTestPopUp.isDisplayed()){
		   waitForElementAndClick(deleteButtonTestPopUp);
		}
			
		}catch(Exception e){
			 System.out.println("Unable to delete the Test   " + testName);
		}
		
	}
	
	public String getTestId(){
		String testId = null;
		return testId =testViewIcon.getAttribute("data-id");
		
	}
	
	public Schedule navigateToScheduleFromListings() {
		waitForElementAndClick(testViewIcon);
		customeWaitTime(5);
		waitForElementAndClick(scheduleTestLink);
		customeWaitTime(5);
		try {
			waitForElementAndClick(testViewIcon);
			customeWaitTime(5);
			waitForElementAndClick(scheduleTestLink);
			customeWaitTime(5);			
		} catch (Exception e) {
			
		}
		return new Schedule(driver);

	}
	
	
	public void copyTest(String testBank ,String copyTestName) {
		try {
			selectOption(selectCopyTestBank, testBank);
			customeWaitTime(5);
			copyTestField.clear();
			customeWaitTime(5);
			waitForElementAndSendKeys(copyTestField, copyTestName);
			customeWaitTime(5);
			waitForElementAndClick(copyTestButton);
			customeWaitTime(5);
		} catch (Exception e) {

			System.out.println("Unable to Copy  the Item -->  " + copyTestName);

		}

	}

	
	/**
	 * Added this method as Item bank  drop  down is populating through plugin not a normal  select box 
	 * @param option
	 */
	public void selectTestBank(String option){
		testBankDropdown.click();
		customeWaitTime(5);
		waitForElementAndSendKeys(searchItemBankInputField, option);
		customeWaitTime(5);
		List<WebElement> testBankoptions= driver.findElements(By.xpath("//div[@class='btn-group bootstrap-select content-bank select-search-by-name-test_bank open']//ul/li"));
		for (WebElement testBank : testBankoptions){
			try{
			if(testBank.getText().equals(option)){
				testBank.click();
			   break;
			}
			
			}catch(Exception e ){
				System.out.println(option + " is not available" );
			}
		}
		
		
	}
	
	public String filterItemBank(String itemBankName) {
		try {
			customeWaitTime(5);
			waitForElementAndClick(resetSearchFilter);
			customeWaitTime(5);
			waitForElementAndClick(bankFilter);
			customeWaitTime(5);
			waitForElementAndClick(selectBankFilter);
			customeWaitTime(10);
			waitForElementAndDoubleClick(searchButtonItemBankFilterPopup);
			customeWaitTime(5);
			waitAndClearField(searchItemBankFilterPopup);
			customeWaitTime(5);
			waitForElementAndSendKeys(searchItemBankFilterPopup, itemBankName);
			
			/*customeWaitTime(10);
			waitForElementAndDoubleClick(searchButtonItemBankFilterPopup);
			try {
				customeWaitTime(10);
				waitForElementAndDoubleClick(searchButtonItemBankFilterPopup);

			} catch (Exception e) {

			}*/
			customeWaitTime(10);
			WebElement serachedItembank = driver.findElement(By
					.xpath("//tr[@class='data-row']//td[text()='"
							+ itemBankName + "']"));
			waitForElementAndDoubleClick(serachedItembank);
			customeWaitTime(5);
			waitForElementAndClick(globalModalOKCancelSaveButton);
			customeWaitTime(5);

		} catch (Exception e) {
			System.out.println("Unable to Filter the Item Bank  "
					+ itemBankName);
		}

		return filteredItemBank.getText();

	}

		
}
