package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organization extends BasePage {

	BasePage base;
	BaseTest test;

	public Organization(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//*[@id='treeNavCreate']/i")
	public WebElement createNewOrganization;
	
					
	@FindBy(id = "treeNavCreate")
	public WebElement createHerarchy;
	
	
	

	
	@FindBy(id = "globalModalOKCancelSaveButton")
	public WebElement globalModalOKCancelSaveButton;
	
	@FindBy(xpath = "//input[contains(@class, 'form-control nodeName cloneItem')]")
	public WebElement tName;
	
	@FindBy(xpath = "//select[contains(@class, 'form-control nodeType cloneItem')]")
	public WebElement tType;
	
	
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backToDashboard;


	public void  createOrganizationNode(String name, String type) {
		try {
		waitForElementAndClick(createNewOrganization);	
		waitForElementAndSendKeys(tName, name);
		waitForElementAndSendKeys(tType, type);
		selectOption(tType, "country");
		
		waitForElementAndClick(globalModalOKCancelSaveButton);
		
		} catch (Exception e) {
			System.out.println("Unable to Create the  " + type);
		}
		
		
		System.out.println(type +" Created");
	}
	

	
	public void  createNewOrganization(String name) {
		try {
		waitForElementAndClick(createHerarchy);	
		
	
		waitForElementAndSendKeys(tName, name);
		waitForElementAndSendKeys(tType, "school");
		
		
		waitForElementAndClick(globalModalOKCancelSaveButton);
		System.out.println("School Created");
		waitTime();
		waitForElementAndClick(backToDashboard);
		} catch (Exception e) {
			System.out.println("Unable to Create the  " + "school");
		}

	}
		
}
