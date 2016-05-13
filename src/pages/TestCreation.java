package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import generic.BasePage;
import generic.BaseTest;
import net.sourceforge.htmlunit.corejs.javascript.ast.SwitchCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gargoylesoftware.htmlunit.WaitingRefreshHandler;
import com.google.common.base.CaseFormat;
import com.thoughtworks.selenium.webdriven.commands.Highlight;

public class TestCreation extends BasePage {

	BasePage base;
	BaseTest test;

	public TestCreation(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = ".//*[@id='region-navigation']/ul/li[1]/span")
	public WebElement homeLink;
	
	@FindBy(xpath = "//span[text()='Create']")
	public WebElement createTestLink;

	@FindBy(id = "test-option-0")
	public WebElement assessmentRadio;
	
	@FindBy(id = "printTest")
	public WebElement testPrintButton;

	@FindBy(id = "tName")
	public WebElement testNameField;

	@FindBy(id = "tContent")
	public WebElement testContentField;
	
	@FindBy(xpath = "//*[@id='tContent']/option[6]")
	public WebElement testContentOption;

	@FindBy(id = "tGrade")
	public WebElement testGradeField;
	// *[@id="tGrade"]/option[6]
	@FindBy(xpath = "//*[@id='tGrade']/option[6]")
	public WebElement testGradeOption;

	@FindBy(xpath = "//*[@id='sortable1']/li")
	public WebElement item1;

	@FindBy(id = "tDetails")
	public WebElement detailsButton;
	
	@FindBy(id = "bookmark-check")
	public WebElement bookMarkCheck;
	
	@FindBy(id = "highlighter-check")
	public WebElement highlightCheck;
	
	@FindBy(id = "notepad-check")
	public WebElement notepadCheck;

	@FindBy(id = "class")
	public WebElement classField;

	@FindBy(id = "book")
	public WebElement bookField;
	
	@FindBy(id = "answer_masking-check")
	public WebElement answerMaskingToolCheckBox;
	
	@FindBy(id = "line_reader-check")
	public WebElement lineReaderToolCheckBox;
	
	@FindBy(id = "magnification-check")
	public WebElement magnificationCheckBox;
	
	@FindBy (id = "answer_eliminator-check")
	public WebElement answerEliminatorCheckBox;
	
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

	@FindBy(className = "tools-button")
	public WebElement toolsButton;

	@FindBy(id = "calculator-check")
	public WebElement calculator_check;

	@FindBy(id = "ruler-check")
	public WebElement ruler_check;

	@FindBy(xpath = "//*[@id='globalModalViewBody']/div/form[9]/div/div/button")
	public WebElement saveToolsButton;

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

	@FindBy(xpath = "//button[text() = 'Create & Edit']")
	public WebElement  createAndEditButton;
	
	@FindBy(xpath = ".//*[@id='globalModalViewBody']/div/form/div[1]/div/select")
	public WebElement bankDropDown;
	
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
	
	@FindBy(xpath = "//td[@class='watable-col-name']")
	public WebElement testNameList;
	
	@FindBy(xpath = "/html/body/div[22]/div[3]/div/button[1]/span")
	public WebElement confirmationMessage;
	
	@FindBy(css =".link i")
	public WebElement backToTestDashboardLink;
	
	@FindBy(className = "tools-save']")
	public WebElement toolSaveButton;
	
	@FindBy(id = "magnification-time")
	public WebElement selectMagnificationTime;
	
	@FindBy(css=".expandedRowContainer #viewTest i.fa-eye")
	public WebElement testItemsPreviewViewTestButton;
	
	@FindBy(id = "adjust_color-check")
	public WebElement alternateColorAndBackgroundCheckBox;
	
	@FindBy(id="notepad-check")
	public WebElement notepadCheckBox;
	
	@FindBy(className="fa-home")
	public WebElement homeButton;
	
	
	/**
	 * This is the method for creating the test
	 * @param testName
	 * @param testBankName
	 * @param itemName
	 */
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
			waitForElementAndSendKeys(searchAutoComplete,
					itemName);
			waitForElementAndClick(searchButton);
			waitForElementAndClick(item1);
			dragAndDrop(item1, target);
			clearSearchFilter();
			System.out.println("Items " + itemName + " added to the test " + testName);
			
			waitForElementAndSendKeys(testContentField, "N/A");
			selectOption(testContentField, "N/A");

			waitForElementAndSendKeys(testGradeField, "05");
			selectOption(testGradeField, "05");
			testGradeField.click();
			
			waitForElementAndClick(saveTestButton);
			waitForElementAndClick(xbutton);

			try {
				waitForElementAndClick(xbutton);
			} catch (Exception e) {
			
			}
			customeWaitTime(5);
			System.out.println("Test "+ testName +" created successfully ");
			backToListing();
			customeWaitTime(5);
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
			customeWaitTime(2);
			selectOption(bankDropDown, testBankName);
			//selectTestBank(testBankName);
			waitForElementAndSendKeys(contentCreateInputName, testName);
			waitForElementAndSendKeys(contentCreateInputDescription, testName);
			waitForElementAndClick(createAndEditButton);
			filterItemBank(itemBank);
			/*customeWaitTime(2);
			waitForElementAndClick(resetSearchFilter);*/
			customeWaitTime(2);
			List <WebElement>  totalItems = driver.findElements(By.xpath("//ul[@id='sortable1']//li"));
			for (int i=totalItems.size(); i >= 1; i--){
				customeWaitTime(1);
				WebElement itemToSelect = driver.findElement(By.xpath("//ul[@id='sortable1']//li["+ i +"]"));
				customeWaitTime(1);
				dragAndDrop(itemToSelect, target);
				System.out.println("Adding the item number " +i);
				
			}
			waitForElementAndSendKeys(testContentField, "N/A");
			waitForElementAndSendKeys(testGradeField, "05");
			waitForElementAndClick(saveTestButton);
			customeWaitTime(2);
			waitForElementAndClick(xbutton);
			customeWaitTime(2);
			try {
				waitForElementAndClick(xbutton);
			} catch (Exception e) {
				// TODO: handle exception
			}
			customeWaitTime(2);
			System.out.println("Test "+ testName +" created successfully ");
			backToListing();
			customeWaitTime(2);
		} catch (Exception e) {
			System.out.println("Test "+ testName +" Creation Failed ");
		} 
	}
	
	/**
	 * 
	 * @param testName
	 * @param testBankName
	 * @param itemName
	 */
	public void createTestWithAccommodationTool(String testName , String testBankName ,  
												String itemName, String accommodationTool) 
	{
			try {
				waitForElementAndClick(createTestLink);
				customeWaitTime(10);
				if(bankDropDown.isDisplayed()){
					selectOption(bankDropDown, testBankName);

				}else{
					selectTestBank(testBankName);
				}
				customeWaitTime(2);
				waitForElementAndSendKeys(contentCreateInputName, testName);
				waitForElementAndSendKeys(contentCreateInputDescription, testName);
				waitForElementAndClick(createAndEditButton);
				clearSearchFilter();
				waitForElementAndSendKeys(searchAutoComplete,
						itemName);
				waitForElementAndClick(searchButton);
				waitForElementAndClick(item1);
				dragAndDrop(item1, target);
				clearSearchFilter();
				System.out.println("Items " + itemName + " added to the test " + testName);
				waitForElementAndSendKeys(testContentField, "N/A");
				selectOption(testContentField, "N/A");
				waitForElementAndSendKeys(testGradeField, "05");
				selectOption(testGradeField, "05");
				testGradeField.click();
				waitForElementAndClick(toolsButton);
				switch(accommodationTool){
				case "answerEliminator":
					waitForAnElementAndClick(answerEliminatorCheckBox);
					break;
				case "highlight":
					waitForAnElementAndClick(highlightCheck);
					break;
				case "bookmark":
					waitForAnElementAndClick(bookMarkCheck);
					break;
				case "notepad":
					waitForAnElementAndClick(notepadCheck);
					break;
				case "maskinganswer":
					waitForAnElementAndClick(answerMaskingToolCheckBox);
					break;
				}
				waitForAnElementAndClick(toolSaveButton);
				waitForElementAndClick(saveTestButton);
				waitForElementAndClick(xbutton);

				try {
					waitForElementAndClick(xbutton);
				} catch (Exception e) {
				}
			
			} catch (Exception e) {
				System.out.println("Test "+ testName +" Creation Failed ");
			}
	}
	
	/**
	 * 
	 * @param test
	 */
	public void searchTest(String test){
		try{
		  waitForElementAndSendKeys(searchAutoComplete, test);
		  waitForElementAndClick(searchButton);
		  customeWaitTime(5);
		}catch(Exception e){
			System.out.println("Unable to find the Test  -->  "  + test);
		}		
	}
	/**
	 * 
	 * @param testBankName
	 * @return
	 */
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
	
	/**
	 * 
	 * @param testName
	 */
	public void deleteTest(String testName){
		try{
		searchTest(testName);	
		customeWaitTime(5);
		waitForElementAndClick(deleteIconList);
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
		try{
		testId =  waitAndGetElementAttribute(previewIconList,"data-id" );
		 System.out.println("Created  Test  id is >>  " + testId);
		
		}catch(Exception e){
			 System.out.println("Unable to get  the Test  id >>  " + testId);
		}
		return testId;
	}
	
	public Schedule navigateToScheduleFromListings() {
		waitForElementAndClick(previewIconList);
		customeWaitTime(5);
		waitForElementAndClick(scheduleTestLink);
		customeWaitTime(5);
		try {
			waitForElementAndClick(previewIconList);
			customeWaitTime(5);
			waitForElementAndClick(scheduleTestLink);
			customeWaitTime(5);			
		} catch (Exception e) {
			
		}
		return new Schedule(driver);

	}
	
	/**
	 * 
	 * @param testBank
	 * @param copyTestName
	 */
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
	 * 
	 * @param testBank
	 * @param testName
	 * @param copyTestName
	 * @param itemIndex
	 * @param copiedTestCount
	 */
	public void copyMultipleTest(String testBank , String testName , String copyTestName , int itemIndex , int copiedTestCount){
    	int testCount = 0;
    	try{
    		
    		for(testCount = 1 ; testCount <= copiedTestCount ; testCount ++){
    			WebElement elementToCopy = driver.findElement(By.xpath("//td[text()='"+ testName +"']/../td[@class='watable-col-preview']//button[@class='btn btn-xs btn-link copyRow']"));
    			waitForElementAndClick(elementToCopy);
    			customeWaitTime(3);
    			selectOption(selectCopyTestBank, testBank);
    			customeWaitTime(2);
    			waitAndClearField(copyTestField);
    			waitForElementAndSendKeys(copyTestField, String.valueOf(testCount) +"_" + copyTestName);
    			customeWaitTime(2);
    			waitForElementAndClick(copyTestButton);
    			customeWaitTime(2);
    			waitForElementAndClick(globalModalInfoOkButton);
    			customeWaitTime(3);
    		}
    		
    	}catch(Exception e){
    		
			System.out.println("Unable to Copy  the Item -->  " + copyTestName + String.valueOf(testCount));

    	}
    	
    	
    }

	
	/**
	 * Added this method as Item bank  drop  down is populating through plugin not a normal  select box 
	 * @param option
	 */
	public void selectTestBank(String option){
		waitForElementAndClick(testBankDropdown);
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
	
	/**
	 * 
	 * @param itemBankName
	 * @return
	 */
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

	/**
	 * 
	 * @param testBank
	 * @return
	 */
	public String filterTestByTestBank(String testBank) {
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
			waitForElementAndSendKeys(searchItemBankFilterPopup, testBank);
			customeWaitTime(5);
			WebElement serachedItembank = driver.findElement(By
					.xpath("//tr[@class='data-row']//td[text()='"
							+ testBank + "']"));
			waitForElementAndDoubleClick(serachedItembank);
			customeWaitTime(5);
			waitForElementAndClick(globalModalOKCancelSaveButton);
			customeWaitTime(5);

		} catch (Exception e) {
			System.out.println("Unable to Filter the Test Bank  "
					+ testBank);
		}

		return filteredItemBank.getText();

	}
	
	
	public void goToTestDashBoard(){
		waitForElementAndClick(backToTestDashboardLink); 
	}
	
	public Delivery previewTest(){
		waitForAnElementAndClick(testItemsPreviewViewTestButton);
		return new Delivery(driver);
	}
	
	public DashBoard returnToDashboard(){
		 waitForAnElementAndClick(homeButton);
		 return new DashBoard(driver);
	}
	
	/**
	 * 
	 * @param tools
	 */
	public void enableTestTools(String tools) {
		try {
			waitForElementAndClick(toolsButton); 
			customeWaitTime(2);
			List<String> toolToAdd = new ArrayList<String>(Arrays.asList(tools
					.split(",")));
			for (String tool : toolToAdd) {

				switch (tool) {
				case "Answer Masking":
					waitForElementAndClick(answerMaskingToolCheckBox);
					break;
				case "Calculator":
					waitForElementAndClick(calculator_check);
					break;
				case "Ruler":
					waitForElementAndClick(ruler_check);
					break;
				case "Protractor":
					waitForElementAndClick(aclTrusteeAdmin);
					break;
				case "Line Reader":
					waitForElementAndClick(lineReaderToolCheckBox);
					break;
				case "Magnification":
					waitForElementAndClick(magnificationCheckBox);
					break;	
				case "Answer Eliminator":
					waitForAnElementAndClick(answerEliminatorCheckBox);
					break;
				case "Alternate Color Text/Background":
					waitForAnElementAndClick(alternateColorAndBackgroundCheckBox);
					break;	
				case "Additional Tools":
					// TODO
					break;
				}
			}
			customeWaitTime(5);
			waitForElementAndClick(toolSaveButton);
			customeWaitTime(5);
			waitForElementAndClick(globalModalInfoOkButton);

		} catch (Exception e) {

		}

	}
	
	/**
	 * This is the method for set magnification scale
	 * @param scale
	 */
	public void setMagnificationScale(String scale){
		try{
		  waitForElementAndClick(toolsButton); 
		  selectOption(selectMagnificationTime , scale);
		  customeWaitTime(5);
		  waitForElementAndClick(toolSaveButton);
		 customeWaitTime(5);
		 waitForElementAndClick(globalModalInfoOkButton);
		  
		}catch(Exception e){
			
			
		}
		
	}

}
