package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;


public class Items extends BasePage{

	                 
	@FindBy(xpath = "//*[@id='region-navigation']/div/span[1]")
	public WebElement createItemButton;
	
	@FindBy(id = "contentCreateInputName")
	public WebElement itemCreateInputName;
	
	@FindBy(id = "contentCreateInputDescription")
	public WebElement itemCreateInputDescription;
	                 
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
	
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backToItems;
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backToDashboard;

	
	@FindBy(xpath = "//*[@id='tinymce']/div[1]/div[1]/span")
	public WebElement tinymce;
	
	
	
	@FindBy(xpath = "//*[@id='itemScoringRegion']/div/div[1]/div/div[1]/div/div/div[1]/div/div[1]/div[2]/div[2]/label/input")
	public WebElement answerOne;
	
	
	
	@FindBy(xpath = "//*[@id='itemScoringRegion']/div/div[1]/div/div[1]/div/div/div[2]/span")
	public WebElement saveAnswer;
	
	
	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[2]/button[3]")
	public WebElement saveAndPublish;
	
	public Items(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void createItem(String name){	
		
		waitForElementAndClick(createItemButton);
		waitForElementAndSendKeys(itemCreateInputName, name);
		waitForElementAndSendKeys(itemCreateInputDescription, "Description");	
		waitForElementAndClick(itemCreateEditInputSubmit);
		waitTime();
		//waitForElementAndClick(testImage);
		
	
		waitForElementAndSendKeys(templates, "Choice");
		selectOption(templates, "Choice");
		
		
		try {
			waitForElementAndSendKeys(tinymce, "This is a test");
		} catch (Exception e) {
			// TODO: handle exception
		}
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
		waitTime();	
		waitForElementAndClick(backToDashboard);
	
	}
}
