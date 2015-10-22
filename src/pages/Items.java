package pages;

import java.util.List;

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

	@FindBy(id = "itemSaved")
	public WebElement itemSaved;

	@FindBy(id = "htmlTabButton")
	public WebElement htmlTabButton;

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
	
	public Items(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void createItem(String name) {
		waitTime();
		waitForElementAndClick(createItemButton);
		waitForElementAndSendKeys(itemCreateInputName, name);
		waitForElementAndSendKeys(itemCreateInputDescription, "Description");
		waitForElementAndClick(itemCreateEditInputSubmit);
		waitTime();
		//waitForElementAndSendKeys(templates, "Choice");
		selectOption(templates, "Choice");
		waitForElementAndClick(textEditorSaveButton);
		waitTime();

		waitForElementAndClick(confirmationMessage);
		try {
			waitTime();
			waitForElementAndClick(confirmationMessage);
		} catch (Exception e) {

		}

		waitForElementAndClick(scoreTabButton);

		waitForElementAndClick(answerOne);

		waitForElementAndClick(saveAnswer);

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
		searchItem(name);
		this.addStandards();
		waitForElementAndClick(backToDashboard);

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
	
	public void searchItem(String item){
		try{
		  waitTime();
		  waitForElementAndSendKeys(itemSearchAutoComplete, item);
		  waitForElementAndClick(itemSearchButton);
		  waitTime();
		}catch(Exception e){
			
			System.out.println("Unable to find the Item -->  "  + item);

		}
		
	}
	
	public String getSharedItemBank(String itemBankName){
		String itemBank = null ;
		waitTime();
		waitForElementAndClick(createItemButton);
		waitTime();
		List<WebElement > itemBankOptions = getDropDownOptions(selectItemBank);
		for (WebElement itemBankOption : itemBankOptions){
			try{
			if(itemBankOption.getText().equals(itemBankName)){
			   System.out.println(itemBankOption.getText());
			   itemBank = itemBankOption.getText();
			   break;
			}
			
			}catch(Exception e ){
				System.out.println(itemBankName + " is not available" );
			}
		}
		return itemBank;
	}
}
