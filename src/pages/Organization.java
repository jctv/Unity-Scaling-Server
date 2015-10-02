package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sun.net.www.content.audio.wav;

public class Organization extends BasePage {

	BasePage base;
	BaseTest test;

	public Organization(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "treeNavCreate")
	public WebElement createNewOrganization;

	@FindBy(xpath = "//button[(@class ='btn btn-primary org-save')]")
	public WebElement createHerarchy;

	@FindBy(id = "globalModalInfoOkButton")
	public WebElement globalModalInfoOkButton;

	@FindBy(id = "tName")
	public WebElement tName;

	@FindBy(id = "tType")
	public WebElement tType;
	
	
	@FindBy(id = "globalModalOKCancelSaveButton")
	public WebElement globalModalOKCancelSaveButton;
	

	@FindBy(xpath = "//li[.//span[text()='Automated School' and @class = 'jqtree_common jqtree-title']][last()]")
	public WebElement schoolCreated;

	@FindBy(xpath = "//li[.//span[text()='Automated School']]//button[@title='Remove']")
	public WebElement deleteSchoolIcon;

	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backToDashboard;

	public void createOrganizationNode(String name, String type) {
		try {
			waitForElementAndClick(createNewOrganization);
			waitForElementAndSendKeys(tName, name);
			waitForElementAndSendKeys(tType, type);
			selectOption(tType, "country");

			waitForElementAndClick(globalModalInfoOkButton);

		} catch (Exception e) {
			System.out.println("Unable to Create the  " + type);
		}

		System.out.println(type + " Created");
	}

	public void createNewOrganization(String name) {
		try {
			waitForElementAndClick(createNewOrganization);

			waitForElementAndSendKeys(tName, name);
			selectOption(tType, "school");
			waitForElementAndClick(createHerarchy);
			waitForElementAndClick(globalModalInfoOkButton);
			System.out.println("School Created");
			waitTime();
			waitForElementAndClick(backToDashboard);
		} catch (Exception e) {
			System.out.println("Unable to Create the  " + "school");
		}

	}

	public void deleteCreatedOrganization() {
		try {
			waitTime();
			
			
			waitAndFocus(schoolCreated);
			waitForElementAndClick(deleteSchoolIcon);
			waitForElementAndClick(globalModalOKCancelSaveButton);
			
		} catch (Exception e) {

			System.out.println("Unable to delete the created school");
		}

	}
}
