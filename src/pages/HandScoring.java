package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HandScoring extends BasePage {

	BasePage base;
	BaseTest test;

	public HandScoring(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	//button[contains(@class,'editRow') and ancestor::tr/td[text()='Auto test Roster #1']])[last()]
	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[3]/table/tbody/tr[1]/td[2]/div/button[2]/i")
	public WebElement scoreTestIcon;
                          
	@FindBy(xpath = "//*[@id='scores']/div/form/div[1]/div/select")
	public WebElement score;

	@FindBy(xpath = "//button[@Class ='btn btn-primary btn-sm response-save btn-danger']")
	public WebElement scoreOption;

	@FindBy(xpath = "//*[@id='scores']/div/form/div[2]/div/button")
	public WebElement saveScoreButton;

	@FindBy(id = "globalModalInfoOkButton")
	public WebElement globalModalInfoOkButton;

	@FindBy(xpath = "//*[@id='globalModalInfo']/div/div/div[1]/button")
	public WebElement okButton;

	@FindBy(xpath = "//*[@id='itemNav']/div/div[3]/button")
	public WebElement nextAnswer;
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backLink;
	
	@FindBy(id = "show-all-items")
	public WebElement allItems;
	
	@FindBy(id = "hsItem0")
	public WebElement hsItem0;
	
	
	public String scoreTest() {
		waitForElementAndClick(scoreTestIcon);
		waitTime();
		try {
			waitForElementAndDoubleClick(globalModalInfoOkButton);
			
			waitForElementAndClick(allItems);
			waitForElementAndClick(hsItem0);
			
			
		} catch (Exception e) {
			
		}
		
		for (int x = 0; x < 1; x++) {
			waitTime();
			selectOption(score, "1");
			
			waitForElementAndDoubleClick(saveScoreButton);
			
			try {
				//waitForElementAndDoubleClick(globalModalInfoOkButton);
				waitForElementAndClick(okButton);
				waitForElementAndClick(backLink);
			} catch (Exception e) {
				waitForElementAndClick(backLink);
			}
			waitTime();
			waitForElementAndClick(backLink);
		}
		
		
		return "test scored";

	}
}
