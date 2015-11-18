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
	
	

	public Items(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void createItem(String name, String itemBankName) {
		waitTime();
		waitForElementAndClick(createItemButton);
		waitTime();
		//waitForElementAndSendKeys(selectItemBank, itemBankName);
		//selectOption(selectItemBank, itemBankName);
		selectItemBank(itemBankName);
		waitTime();
		waitForElementAndSendKeys(itemCreateInputName, name);
		waitForElementAndSendKeys(itemCreateInputDescription, "Description");
		waitForElementAndClick(itemCreateEditInputSubmit);
		waitTime();
		// waitForElementAndSendKeys(templates, "Choice");
		selectOption(templates, "Choice");
		waitForElementAndClick(textEditorSaveButton);
		waitTime();
		clickOnConfirmationMessage();
		waitForElementAndClick(scoreTabButton);
		waitForElementAndClick(answerOne);
		waitForElementAndClick(saveAnswer);
		waitForElementAndClick(saveAndPublish);
		waitTime();
		clickOnConfirmationMessage();
		System.out.println("The Item " + name + " has been created");
		waitTime();
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
		waitTime();
		waitForElementAndClick(createItemButton);
		waitTime();
		selectItemBank(itemBankName);
		waitTime();
		waitForElementAndSendKeys(itemCreateInputName, name);
		waitForElementAndSendKeys(itemCreateInputDescription, "Description");
		waitForElementAndClick(itemCreateEditInputSubmit);
		waitTime();
		selectOption(templates, interactionType);
		waitTime();
		waitForElementAndClick(textEditorSaveButton);
		waitTime();
		clickOnConfirmationMessage();
		waitForElementAndClick(scoreTabButton);
		switch (interactionType) {
        case "Choice":
        	waitForElementAndClick(answerOne);
        	waitTime();
    		waitForElementAndClick(saveAnswer);
    		waitTime();
            break;
        case "Text Entry":
        	waitForElementAndSendKeys(inputTextEntry, setAnswer);
    		waitTime();
    		waitForElementAndClick(saveAnswer);
    		waitTime();
            break;
        case "Extended Text Entry ":
        	//TODO
            break;
        
        }
		selectOption(scoreProfile, scoringType);
		waitTime();
		waitForElementAndClick(saveAndPublish);
		waitTime();
		clickOnConfirmationMessage();
		System.out.println("The Item type >  " + interactionType  +"  name -> " + name +  " score profile >  " + scoringType + " has been created successfully");
		waitTime();
		waitForElementAndClick(backToItems);
		//searchItem(name);
		//this.addStandards();
		//waitForElementAndClick(backToDashboard);

	}

	public void createHandScoringItem(String name) {
		waitTime();
		waitForElementAndClick(createItemButton);
		waitForElementAndSendKeys(itemCreateInputName, name);
		waitForElementAndSendKeys(itemCreateInputDescription, "Description");
		waitForElementAndClick(itemCreateEditInputSubmit);
		waitTime();
		waitForElementAndSendKeys(templates, "Text Entry");
		waitTime();
		waitForElementAndClick(scoreTabButton);
		waitForElementAndClick(htmlTabButton);
		waitForElementAndClick(scoreTabButton);
		waitForElementAndSendKeys(scoreProfile, "Handscoring");
		waitForElementAndClick(saveAndPublish);

		waitTime();
		waitForElementAndClick(confirmationMessage);
		try {
			waitForElementAndClick(confirmationMessage);
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("The Item has been created");
		waitTime();
		waitForElementAndClick(backToItems);
		waitTime();
		this.addStandards();
		waitForElementAndClick(backToDashboard);

	}

	public void addStandards() {
		waitTime();
		waitForElementAndClick(standardColumn);
		waitForElementAndClick(standards1);
		waitForElementAndClick(standards2);
		waitForElementAndClick(standards3);
		waitForElementAndClick(standards4);
		waitForElementAndClick(standards5);
		waitForElementAndClick(globalModalOKCancelSaveButton);

	}

	public void searchItem(String item) {
		try {
			itemSearchAutoComplete.clear();
			waitTime();
			waitForElementAndSendKeys(itemSearchAutoComplete, item);
			waitForElementAndClick(itemSearchButton);
			waitTime();
		} catch (Exception e) {

			System.out.println("Unable to find the Item -->  " + item);

		}

	}
	
	
	public void copyItem(String itemBank ,String copyItemName) {
		try {
			
			selectOption(selectCopyBank, itemBank);
			waitTime();
			waitForElementAndSendKeys(copyitemBankField, copyItemName);
			waitTime();
			waitForElementAndClick(copyItemButton);
			waitTime();
		} catch (Exception e) {

			System.out.println("Unable to Copy  the Item -->  " + copyItemName);

		}

	}


	public String getSharedItemBank(String itemBankName) {
		String itemBank = null;
		waitTime();
		waitForElementAndClick(createItemButton);
		waitTime();
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
			waitTime();
			waitForElementAndClick(itemDeleteIcon);
			waitTime();
			if (deleteItemPopUp.isDisplayed()) {
				waitForElementAndClick(deletebuttonItemPopUp);
			}

		} catch (Exception e) {
			System.out.println("Unable to delete the Item   " + itemName);
		}

	}

	public String filterItemBank(String itemBankName) {
		try {
			waitTime();
			waitForElementVisible(resetSearchFilter);
			resetSearchFilter.click();
			waitTime();
			waitTime();
			waitForElementAndClick(bankFilter);
			waitTime();
			waitForElementAndClick(selectBankFilter);
			waitTime();
			searchItemBankFilterPopup.clear();
			waitForElementAndDoubleClick(searchButtonItemBankFilterPopup);
			waitForElementAndSendKeys(searchItemBankFilterPopup, itemBankName);
			waitTime();
			waitForElementAndDoubleClick(searchButtonItemBankFilterPopup);
			try {
				waitTime();
				waitForElementAndDoubleClick(searchButtonItemBankFilterPopup);

			} catch (Exception e) {

			}
			waitTime();
			WebElement serachedItembank = driver.findElement(By
					.xpath("//tr[@class='data-row']//td[text()='"
							+ itemBankName + "']"));
			waitForElementAndDoubleClick(serachedItembank);
			waitTime();
			waitForElementAndClick(globalModalOKCancelSaveButton);
			waitTime();

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
		waitTime();
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
		waitTime();
		List<WebElement> itemBankoptions= driver.findElements(By.xpath("//div[@class='btn-group bootstrap-select content-bank select-search-by-name-item_bank open']//ul/li"));
		for (WebElement itemBank : itemBankoptions){
			try{
			if(itemBank.getText().equals(option)){
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
			waitTime();
			waitForElementAndClick(confirmationMessage);
		} catch (Exception e) {

		}
		
		
	}
	
	public String updateItemName(String name ){
		itemNameList.click();
		waitTime();
		itemNameListInput.clear();
		waitTime();
		itemNameListInput.sendKeys(name);
		waitTime();
		itemNamePreviewColoumn.click();
		waitTime();
		return itemNameList.getText();
	}
	
	
	public String updateItemTitle(String title ){
		itemTilteList.click();
		waitTime();
		itemTitleListInput.clear();
		waitTime();
		itemTitleListInput.sendKeys(title);
		waitTime();
		itemNamePreviewColoumn.click();
		waitTime();
		return itemTilteList.getText();
	}
	
	
	public  String updateItemContentArea(String contentArea){
		itemContentAreaList.click();
		waitTime();
		selectOption(itemContentAreaSelectList , contentArea );
		waitTime();
		itemNamePreviewColoumn.click();
		waitTime();
		return itemContentAreaList.getText();
	}
	
	public  String updateItemGrade(String grade){
		itemGradeList.click();
		waitTime();
		selectOption(itemGradeSelectList , grade );
		waitTime();
		itemNamePreviewColoumn.click();
		waitTime();
		return itemGradeList.getText();
	}
	
	public String updateItemBloom(String bloom ){
		itemBloomList.click();
		waitTime();
		itemBloomListInput.clear();
		waitTime();
		itemBloomListInput.sendKeys(bloom);
		waitTime();
		itemNamePreviewColoumn.click();
		waitTime();
		return itemBloomList.getText();
	}
	
	public  String updateItemDOK(String dok){
		itemDepthOfKnowledgeList.click();
		waitTime();
		selectOption(itemDepthOfKnowledgeSelectList , dok );
		waitTime();
		itemNamePreviewColoumn.click();
		waitTime();
		return itemDepthOfKnowledgeList.getText();
	}
	
	
	public String updateItemDifficulty(String difficulty){
		itemDifficultyList.click();
		waitTime();
		selectOption(itemDifficultySelectList , difficulty );
		waitTime();
		itemNamePreviewColoumn.click();
		waitTime();
		return itemDifficultyList.getText();
	}
	
	public String updateItemLifeCycle(String lifeCycle){
		itemLifeCycleList.click();
		waitTime();
		selectOption(itemLifeCycleSelectList , lifeCycle);
		waitTime();
		itemNamePreviewColoumn.click();
		waitTime();
		return itemLifeCycleList.getText();
	}
	
	public String updateItemReadability(String readability ){
		itemReadabilityList.click();
		waitTime();
		itemReadabilityListInput.clear();
		waitTime();
		itemReadabilityListInput.sendKeys(readability);
		waitTime();
		itemNamePreviewColoumn.click();
		waitTime();
		return itemReadabilityList.getText();
	}
	
}
