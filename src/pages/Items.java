package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class Items extends BasePage {

	@FindBy(xpath = "//span[text()='Create']")
	public WebElement createItemButton;

	@FindBy(id = "contentCreateInputName")
	public WebElement itemCreateInputName;


	@FindBy(id = "searchAutoComplete")
	public WebElement itemSearchAutoComplete;

	@FindBy(id = "searchButton")
	public WebElement itemSearchButton;

	@FindBy(id = "contentCreateInputDescription")
	public WebElement itemCreateInputDescription;

	@FindBy(xpath = "//select[@name='bank']")
	public WebElement selectItemBank;

	@FindBy(xpath = "//*[@id='quickViewContentCreate']/div/form/button[1]")
	public WebElement itemCreateEditInputSubmit;

	@FindBy(id = "itemCreateInputSubmit")
	public WebElement itemCreateInputSubmit;

	@FindBy(xpath = "//*[@id='assetList']/li/div")
	public WebElement testImage;

	@FindBy(id = "templateTabButton")
	public WebElement templateTabButton;

	@FindBy(id = "templates")
	public WebElement templates;

	@FindBy(id = "scoringTabButton")
	public WebElement scoreTabButton;

	@FindBy(id = "addScoreButton")
	public WebElement addScoreButton;

	@FindBy(xpath = "//*[@id='scoreView']/div/div[1]/div[1]/div/textarea")
	public WebElement answerInputField;

	@FindBy(xpath = "//*[@id='scoreView']/div/div[1]/div[2]/div/input")
	public WebElement scoreProfileInputField;

	@FindBy(id = "saveScoreButton")
	public WebElement saveScoreButton;

	@FindBy(id = "textEditorSaveButton")
	public WebElement textEditorSaveButton;

	@FindBy(xpath = "//*[@id='itemSaved']/div/div/div[1]/button")
	public WebElement confirmationMessage;

	@FindBy(xpath = "//*[@id='itemSaved']/div/div/div[1]/h4")
	public WebElement itemconfirmationMessageTitle;


	@FindBy(xpath = "//*[@id='itemSaved']/div/div/div[2]/p")
	public WebElement itemconfirmationMessageBody;


	@FindBy(id = "itemSaved")
	public WebElement itemSaved;

	@FindBy(id = "copy-bank")
	public WebElement selectCopyBank;

	@FindBy(id = "copyName")
	public WebElement copyitemBankField;

	@FindBy(xpath = "//button[@class='btn btn-primary object-copy']")
	public WebElement copyItemButton;


	@FindBy(id = "htmlTabButton")
	public WebElement htmlTabButton;

	@FindBy(id = "previewTabButton")
	public WebElement previewTabButton;

	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backToItems;

	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backToDashboard;

	@FindBy(xpath = "//*[@id='tinymce']/div[1]/div[1]/span")
	public WebElement tinymce;

	@FindBy(xpath = "(//input[@name='sprite_1'])[last()]")
	public WebElement answerOne;

	@FindBy(xpath = "//*[@id='itemScoringRegion']/div/div[1]/div/div[1]/div/div/div[2]/span")
	public WebElement saveAnswer;

	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[2]/button[3]")
	public WebElement saveAndPublish;

	@FindBy(xpath = "//*[@id='itemInteraction']/div/div[2]/div/div/div[1]/div/div/select")
	public WebElement scoreProfile;

	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[2]/div/div/div[3]/table/tbody/tr[1]/td[14]")
	public WebElement standardColumn;

	@FindBy(xpath = "//*[@id='object-select']/div/div/div/div/div[1]/ul/li[2]/div/label/input")
	public WebElement standards1;

	@FindBy(xpath = "//*[@id='object-select']/div/div/div/div/div[2]/ul/li[2]/div/label/input")
	public WebElement standards2;

	@FindBy(xpath = "//*[@id='object-select']/div/div/div/div/div[3]/ul/li[2]/div/label/input")
	public WebElement standards3;

	@FindBy(xpath = "//*[@id='object-select']/div/div/div/div/div[4]/ul/li[2]/div/label/input")
	public WebElement standards4;

	@FindBy(xpath = "//*[@id='object-select']/div/div/div/div/div[4]/ul/li[2]/div/label/input/../span")
	public WebElement standardsDomain;

	@FindBy(xpath = "//*[@id='object-select']/div/div/div/div/div[5]/ul/li[2]/div/label/input")
	public WebElement standards5;

	@FindBy(id = "globalModalOKCancelSaveButton")
	public WebElement globalModalOKCancelSaveButton;

	@FindBy(xpath = "//button[@class='btn btn-xs btn-link editRow']")
	public WebElement itemEditIcon;

	@FindBy(xpath = "//button[@class='btn btn-xs btn-link deleteRow']")
	public WebElement itemDeleteIcon;

	@FindBy(id = "globalModalDelete")
	public WebElement deleteItemPopUp;

	@FindBy(id = "globalModalDeleteButton")
	public WebElement deletebuttonItemPopUp;

	@FindBy(xpath = "//span[@class='filtered-list-stats-total']")
	public WebElement itemResultCount;

	@FindBy(xpath = "//td[@class='watable-col-preview']")
	public WebElement itemNamePreviewColoumn;



	@FindBy(xpath = "//td[@class='watable-col-name']")
	public WebElement itemNameList;

	@FindBy(xpath = "//td[@class='watable-col-name']/input")
	public WebElement itemNameListInput;

	@FindBy(xpath = "//td[@class='watable-col-title']")
	public WebElement itemTilteList;

	@FindBy(xpath = "//td[@class='watable-col-title']/input")
	public WebElement itemTitleListInput;

	@FindBy(xpath = "//td[@class='watable-col-content_area']")
	public WebElement itemContentAreaList;

	@FindBy(xpath = "//td[@class='watable-col-content_area']/select")
	public WebElement itemContentAreaSelectList;


	@FindBy(xpath = "//td[@class='watable-col-grade']")
	public WebElement itemGradeList;

	@FindBy(xpath = "//td[@class='watable-col-grade']/select")
	public WebElement itemGradeSelectList;

	@FindBy(xpath = "//td[@class='watable-col-bloom']")
	public WebElement itemBloomList;

	@FindBy(xpath = "//td[@class='watable-col-bloom']/input")
	public WebElement itemBloomListInput;

	@FindBy(xpath = "//td[@class='watable-col-depth_of_knowledge']")
	public WebElement itemDepthOfKnowledgeList;

	@FindBy(xpath = "//td[@class='watable-col-depth_of_knowledge']/select")
	public WebElement itemDepthOfKnowledgeSelectList;

	@FindBy(xpath = "//td[@class='watable-col-difficulty']")
	public WebElement itemDifficultyList;

	@FindBy(xpath = "//td[@class='watable-col-difficulty']/select")
	public WebElement itemDifficultySelectList;

	@FindBy(xpath = "//td[@class='watable-col-lifecycle']")
	public WebElement itemLifeCycleList;

	@FindBy(xpath = "//td[@class='watable-col-lifecycle']/select")
	public WebElement itemLifeCycleSelectList;

	@FindBy(xpath = "//td[@class='watable-col-points']")
	public WebElement itemPointsList;

	@FindBy(xpath = "//td[@class='watable-col-points']/input")
	public WebElement itemPointsListInput;

	@FindBy(xpath = "//td[@class='watable-col-readability_level']")
	public WebElement itemReadabilityList;

	@FindBy(xpath = "//td[@class='watable-col-readability_level']/input")
	public WebElement itemReadabilityListInput;


	@FindBy(xpath = "//td[@class='watable-col-std']")
	public WebElement itemStandardList;

	@FindBy(xpath = "//td[@class='watable-col-passages']")
	public WebElement itemPassageList;

	@FindBy(xpath = "//td[@class='watable-col-item_banks']")
	public WebElement itemBankList;

	@FindBy(xpath = "//tr[@class='data-row']/td[1]")
	public WebElement searchItemBankFilterPopUp;

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

	@FindBy(xpath = "//select[@class='form-control item-scoring-profile']")
	public WebElement selectScoreProfile;

	@FindBy(xpath = ".//*[@id='scoreViewAnswer']//h4")
	public WebElement answerProfile;

	@FindBy(xpath = ".//*[@id='scoreViewAnswer']/div/p/span")
	public WebElement correctAnswerProfile;

	@FindBy(xpath = "//span[text()='Set Correct']")
	public WebElement setCorrectAnswer;

	@FindBy(xpath = ".//*[@id='quickViewContentCreate']/div/form/div[1]/div/button")
	public WebElement itemBankDropdown;

	@FindBy(xpath = "//input[@data-interaction='textEntry']")
	public WebElement inputTextEntry;

	@FindBy(xpath = "//div[@data-body='body']/p")
	public WebElement ItemHtmlParagraph;



	public Items(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void createItem(String name, String itemBankName) {
		customeWaitTime(5);
		waitForElementAndClick(createItemButton);
		customeWaitTime(5);
		//waitForElementAndSendKeys(selectItemBank, itemBankName);
		//selectOption(selectItemBank, itemBankName);
		selectItemBank(itemBankName);
		customeWaitTime(5);
		waitForElementAndSendKeys(itemCreateInputName, name);
		waitForElementAndSendKeys(itemCreateInputDescription, "Description");
		waitForElementAndClick(itemCreateEditInputSubmit);
		customeWaitTime(5);
		// waitForElementAndSendKeys(templates, "Choice");
		selectOption(templates, "Choice");
		waitForElementAndClick(textEditorSaveButton);
		customeWaitTime(5);
		clickOnConfirmationMessage();
		waitForElementAndClick(scoreTabButton);
		waitForElementAndClick(answerOne);
		waitForElementAndClick(saveAnswer);
		waitForElementAndClick(saveAndPublish);
		customeWaitTime(5);
		clickOnConfirmationMessage();
		System.out.println("The Item " + name + " has been created");
		customeWaitTime(5);
		waitForElementAndClick(backToItems);
		searchItem(name);
		this.addStandards();
		waitForElementAndClick(backToDashboard);

	}


	/**
	 * It is overloaded method  after testing will remove the other method
	 *
	 * @param name
	 * @param itemBankName
	 * @param interactionType
	 * @param scoringType
	 * @param setAnswer
	 */
	public void createItem(String name, String itemBankName ,String interactionType , String scoringType , String setAnswer) {
		
		try{
		customeWaitTime(5);
		waitForElementAndClick(createItemButton);
		customeWaitTime(5);
		//selectItemBank(itemBankName);
		selectOption(selectItemBank, itemBankName);
		customeWaitTime(5);
		waitForElementAndSendKeys(itemCreateInputName, name);
		waitForElementAndSendKeys(itemCreateInputDescription, "Description");
		waitForElementAndClick(itemCreateEditInputSubmit);
		customeWaitTime(10);
		selectOption(templates, interactionType);
		customeWaitTime(5);
		waitForElementAndClick(textEditorSaveButton);
		customeWaitTime(5);
		clickOnConfirmationMessage();
		waitForElementAndClick(scoreTabButton);
		switch (interactionType) {
        case "Choice":
        	waitForElementAndClick(answerOne);
    		customeWaitTime(5);
    		waitForElementAndClick(saveAnswer);
    		customeWaitTime(5);
            break;
        case "Text Entry":
        	waitForElementAndSendKeys(inputTextEntry, setAnswer);
    		customeWaitTime(5);
    		waitForElementAndClick(saveAnswer);
    		customeWaitTime(5);
            break;
        case "Extended Text Entry ":
        	//TODO
            break;

        }
		selectOption(scoreProfile, scoringType);
		customeWaitTime(5);
		waitForElementAndClick(saveAndPublish);
		customeWaitTime(5);
		clickOnConfirmationMessage();
		System.out.println("The Item type >  " + interactionType  +"  name -> " + name +  " score profile >  " + scoringType + " has been created successfully");
		customeWaitTime(5);
		waitForElementAndClick(backToItems);
		//searchItem(name);
		//this.addStandards();
		//waitForElementAndClick(backToDashboard);
		
		}catch(Exception e ){
			System.out.println("Unalbe to create item ");
			
		}

	}

	public void createHandScoringItem(String name) {
		customeWaitTime(5);
		waitForElementAndClick(createItemButton);
		waitForElementAndSendKeys(itemCreateInputName, name);
		waitForElementAndSendKeys(itemCreateInputDescription, "Description");
		waitForElementAndClick(itemCreateEditInputSubmit);
		customeWaitTime(5);
		waitForElementAndSendKeys(templates, "Text Entry");
		customeWaitTime(5);
		waitForElementAndClick(scoreTabButton);
		waitForElementAndClick(htmlTabButton);
		waitForElementAndClick(scoreTabButton);
		waitForElementAndSendKeys(scoreProfile, "Handscoring");
		waitForElementAndClick(saveAndPublish);

		customeWaitTime(5);
		waitForElementAndClick(confirmationMessage);
		try {
			waitForElementAndClick(confirmationMessage);
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("The Item has been created");
		customeWaitTime(5);
		waitForElementAndClick(backToItems);
		customeWaitTime(5);
		this.addStandards();
		waitForElementAndClick(backToDashboard);

	}

	public void addStandards() {
		customeWaitTime(5);
		waitForElementAndClick(standardColumn);
		waitForElementAndClick(standards1);
		waitForElementAndClick(standards2);
		waitForElementAndClick(standards3);
		waitForElementAndClick(standards4);
		waitForElementAndClick(standards5);
		waitForElementAndClick(globalModalOKCancelSaveButton);

	}



	public String getStrandCategory(){
		waitForElementAndClick(standardColumn);
		customeWaitTime(10);
		waitForElementAndClick(standards1);
		customeWaitTime(2);
		waitForElementAndClick(standards2);
		customeWaitTime(2);
		waitForElementAndClick(standards3);
		customeWaitTime(2);
		waitForElementAndClick(standards4);
		return standardsDomain.getText();
	}
	public void searchItem(String item) {
		try {
			itemSearchAutoComplete.clear();
			customeWaitTime(5);
			waitForElementAndSendKeys(itemSearchAutoComplete, item);
			waitForElementAndClick(itemSearchButton);
			customeWaitTime(5);
		} catch (Exception e) {

			System.out.println("Unable to find the Item -->  " + item);

		}

	}


	public boolean copyItem(String itemBank ,String copyItemName, int itemIndex) {
			try {
				WebElement elementToCopy = driver.findElement(By.xpath("(//button[@title='Copy'])["+itemIndex+"]"));
				waitForElementAndClick(elementToCopy);
				selectOption(selectCopyBank, itemBank);
				customeWaitTime(5);
				waitAndClearField(copyitemBankField);
				waitForElementAndSendKeys(copyitemBankField, copyItemName);
				customeWaitTime(5);
				waitForElementAndClick(copyItemButton);
				customeWaitTime(5);
				waitForElementAndClick(globalModalInfoOkButton);
				customeWaitTime(5);
				return driver.findElement(By.xpath("//td[@class = 'watable-col-name' and text()='"+copyItemName+"']")).isDisplayed();
			} catch (Exception e) {

				System.out.println("Unable to Copy  the Item -->  " + copyItemName);
				return false;
			}
	}


	public String getSharedItemBank(String itemBankName) {
		String itemBank = null;
		customeWaitTime(5);
		waitForElementAndClick(createItemButton);
		customeWaitTime(5);
		List<WebElement> itemBankOptions = getDropDownOptions(selectItemBank);
		for (WebElement itemBankOption : itemBankOptions) {
			try {
				if (itemBankOption.getText().equals(itemBankName)) {
					System.out.println(itemBankOption.getText());
					itemBank = itemBankOption.getText();
					break;
				}

			} catch (Exception e) {
				System.out.println(itemBankName + " is not available");
			}
		}
		return itemBank;
	}

	public void deleteItem(String itemName) {
		try {
			itemSearchAutoComplete.clear();
			searchItem(itemName);
			customeWaitTime(5);
			waitForElementAndClick(itemDeleteIcon);
			customeWaitTime(5);
			if (deleteItemPopUp.isDisplayed()) {
				waitForElementAndClick(deletebuttonItemPopUp);
			}

		} catch (Exception e) {
			System.out.println("Unable to delete the Item   " + itemName);
		}

	}

	public String filterItemBank(String itemBankName) {
		try {
			customeWaitTime(5);
			waitForElementVisible(resetSearchFilter);
			resetSearchFilter.click();
			customeWaitTime(5);
			customeWaitTime(5);
			waitForElementAndClick(bankFilter);
			customeWaitTime(5);
			waitForElementAndClick(selectBankFilter);
			customeWaitTime(5);
			customeWaitTime(5);
			searchItemBankFilterPopup.clear();
			waitForElementAndDoubleClick(searchButtonItemBankFilterPopup);
			waitForElementAndSendKeys(searchItemBankFilterPopup, itemBankName);
			customeWaitTime(5);
			customeWaitTime(5);
			waitForElementAndDoubleClick(searchButtonItemBankFilterPopup);
			try {
				customeWaitTime(5);
				customeWaitTime(5);
				waitForElementAndDoubleClick(searchButtonItemBankFilterPopup);

			} catch (Exception e) {

			}
			customeWaitTime(5);
			customeWaitTime(5);
			WebElement serachedItembank = driver.findElement(By
					.xpath("//tr[@class='data-row']//td[text()='"
							+ itemBankName + "']"));
			waitForElementAndDoubleClick(serachedItembank);
			customeWaitTime(5);
			customeWaitTime(5);
			waitForElementAndClick(globalModalOKCancelSaveButton);
			customeWaitTime(5);
			customeWaitTime(5);

		} catch (Exception e) {
			System.out.println("Unable to Filter the Item Bank  "
					+ itemBankName);
		}

		return filteredItemBank.getText();

	}

	public String getSelectedScoreProfile(WebElement dropDown) {
		String selectedOption = null;
		try {
			selectedOption = getSelectedOption(dropDown).getText();

		} catch (Exception e) {

			System.out
					.println("Unable to find the selected optionthe Item Bank  "
							+ selectedOption);

		}
		return selectedOption;

	}

	public String getInteractionType(String interactionType) {
		String textEntry = null;
		customeWaitTime(5);
		List<WebElement> templatesTypes = getDropDownOptions(templates);
		for (WebElement templateType : templatesTypes) {
			try {
				if (templateType.getText().equals(interactionType)) {
					System.out.println(templateType.getText());
					textEntry = templateType.getText();
					break;
				}

			} catch (Exception e) {
				System.out.println(textEntry + " template is not available");
			}
		}
		return textEntry;
	}

	/**
	 * Added this method as Item bank  drop  down is populating through plugin not a normal  select box
	 * @param option
	 */
	public void selectItemBank(String option){
		itemBankDropdown.click();
		customeWaitTime(5);
		customeWaitTime(5);
		List<WebElement> itemBankoptions= driver.findElements(By.xpath("//div[@class='btn-group bootstrap-select content-bank select-search-by-name-item_bank open']//ul/li"));
		for (WebElement itemBank : itemBankoptions){
			try{
			if(itemBank.getText().equals(option)){
				customeWaitTime(5);
				customeWaitTime(5);
				itemBank.click();
			   break;
			}

			}catch(Exception e ){
				System.out.println(option + " is not available" );
			}
		}

	}


	public void clickOnConfirmationMessage(){
		waitForElementAndClick(confirmationMessage);
		try {
			customeWaitTime(5);
			waitForElementAndClick(confirmationMessage);
		} catch (Exception e) {

		}


	}

	public String updateItemName(String name ){
		itemNameList.click();
		customeWaitTime(5);
		itemNameListInput.clear();
		customeWaitTime(5);
		itemNameListInput.sendKeys(name);
		customeWaitTime(5);
		itemNamePreviewColoumn.click();
		customeWaitTime(5);
		return itemNameList.getText();
	}


	public String updateItemTitle(String title ){
		itemTilteList.click();
		customeWaitTime(5);
		itemTitleListInput.clear();
		customeWaitTime(5);
		itemTitleListInput.sendKeys(title);
		customeWaitTime(5);
		itemNamePreviewColoumn.click();
		customeWaitTime(5);
		return itemTilteList.getText();
	}


	public  String updateItemContentArea(String contentArea){
		itemContentAreaList.click();
		customeWaitTime(5);
		selectOption(itemContentAreaSelectList , contentArea );
		customeWaitTime(5);
		itemNamePreviewColoumn.click();
		customeWaitTime(5);
		return itemContentAreaList.getText();
	}

	public  String updateItemGrade(String grade){
		itemGradeList.click();
		customeWaitTime(5);
		selectOption(itemGradeSelectList , grade );
		customeWaitTime(5);
		itemNamePreviewColoumn.click();
		customeWaitTime(5);
		return itemGradeList.getText();
	}

	public String updateItemBloom(String bloom ){
		itemBloomList.click();
		customeWaitTime(5);
		itemBloomListInput.clear();
		customeWaitTime(5);
		itemBloomListInput.sendKeys(bloom);
		customeWaitTime(5);
		itemNamePreviewColoumn.click();
		customeWaitTime(5);
		return itemBloomList.getText();
	}

	public  String updateItemDOK(String dok){
		itemDepthOfKnowledgeList.click();
		customeWaitTime(5);
		selectOption(itemDepthOfKnowledgeSelectList , dok );
		customeWaitTime(5);
		itemNamePreviewColoumn.click();
		customeWaitTime(5);
		return itemDepthOfKnowledgeList.getText();
	}


	public String updateItemDifficulty(String difficulty){
		itemDifficultyList.click();
		customeWaitTime(5);
		selectOption(itemDifficultySelectList , difficulty );
		customeWaitTime(5);
		itemNamePreviewColoumn.click();
		customeWaitTime(5);
		return itemDifficultyList.getText();
	}

	public String updateItemLifeCycle(String lifeCycle){
		itemLifeCycleList.click();
		customeWaitTime(5);
		selectOption(itemLifeCycleSelectList , lifeCycle);
		customeWaitTime(5);
		itemNamePreviewColoumn.click();
		customeWaitTime(5);
		return itemLifeCycleList.getText();
	}

	public String updateItemReadability(String readability ){
		itemReadabilityList.click();
		customeWaitTime(5);
		itemReadabilityListInput.clear();
		customeWaitTime(5);
		itemReadabilityListInput.sendKeys(readability);
		customeWaitTime(5);
		itemNamePreviewColoumn.click();
		customeWaitTime(5);
		return itemReadabilityList.getText();
	}

}
