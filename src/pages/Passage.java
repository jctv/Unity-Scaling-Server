package pages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class Passage extends BasePage {

	public Passage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Create']")
	public WebElement createPassageLink;

	@FindBy(id = "contentCreateInputName")
	public WebElement passageInputNameField;

	@FindBy(id = "contentCreateInputDescription")
	public WebElement passageDescField;

	@FindBy(xpath = ".//*[@id='globalModalViewBody']/div/form/div[5]/div/button[1]")
	public WebElement passageCreateAndEditButton;

	@FindBy(xpath = ".//*[@id='quickViewContentCreate']/div/form/button[2]")
	public WebElement passageCreateButton;

	@FindBy(xpath = ".//*[@id='globalModalViewBody']/div/form/div[1]/div/div/button")
	public WebElement itemBankDropdown;

	@FindBy(id = "global-object-name")
	public WebElement passageEditNameField;

	@FindBy(xpath = "//button[@class='btn btn-primary fileinput-button']")
	public WebElement passageUploadFileButton;

	@FindBy(xpath = ".//*[@id='bs-example-navbar-collapse-1']/form[2]/div/button")
	public WebElement passageSaveButton;

	@FindBy(xpath = "//tbody[@class='files']//td[1]/i")
	public WebElement passageUploadIcon;

	@FindBy(xpath = ".//*[@id='globalModalViewBody']/div/form/div[1]/div/div/div/div/input")
	public WebElement searchInputField;

	@FindBy(id = "lexileFilter")
	public WebElement lexileFilter;

	@FindBy(id = "FleschKincaidFilter")
	public WebElement FleschKincaidFilter;

	@FindBy(xpath = "(//a[@class='jqtree_common jqtree-toggler'])[1]")
	public WebElement contentArea;

	@FindBy(xpath = "(//a[@class='jqtree_common jqtree-toggler'])[2]")
	public WebElement grade;

	@FindBy(xpath = "(//a[@class='jqtree_common jqtree-toggler'])[3]")
	public WebElement lifecycle;

	@FindBy(xpath = "(//ul[@class='list-group asset-list']//img")
	public WebElement firstImage;
	
	@FindBy(xpath = "(.//*[@id='region-navigation']/ul/li[1]/a")
	public WebElement backToPassage;
	
	
	/**
	 * Added this method as Item bank drop down is populating through plugin not
	 * a normal select box
	 * 
	 * @param option
	 */
	public void selectItemBank(String option) {
		waitForElementAndClick(itemBankDropdown);
		customeWaitTime(5);
		waitForElementAndSendKeys(searchInputField, option);
		customeWaitTime(5);
		List<WebElement> itemBankoptions = driver
				.findElements(By
						.xpath(".//*[@id='globalModalViewBody']/div/form/div[1]/div/div/div/ul/li[1]/a/span[1]"));
		for (WebElement itemBank : itemBankoptions) {
			try {
				if (itemBank.getText().equals(option)) {
					customeWaitTime(5);
					itemBank.click();
					break;
				}

			} catch (Exception e) {
				System.out.println(option + " is not available");
			}
		}

	}

	public boolean createPassage(String itemBank, String passageName,
			String desc, String filepath) {
		try {
			boolean isPassageUploaded = false;
			waitForElementAndClick(createPassageLink);
			selectItemBank(itemBank);
			waitForElementAndSendKeys(passageInputNameField, passageName);
			waitForElementAndSendKeys(passageDescField, desc);
			waitForElementAndClick(passageCreateAndEditButton);
			waitForElementAndClick(passageUploadFileButton);
			if (filepath.equals("")) {
				System.out.println("No image available to be uploaded");
			} else {
				File f = new File(filepath);
				String passageUploadFilepath = f.getAbsolutePath();
				// waitForElementAndSendKeys(fileupload, passageUploadFilepath);
				fileupload.sendKeys(passageUploadFilepath);
				customeWaitTime(5);

				if (passageUploadIcon.getAttribute("style").contains("green")) {
					isPassageUploaded = true;
				}

			}
			waitForElementAndClick(globalModalUploadOkButton);
			
			customeWaitTime(5); 
			if(this.searchPassage(passageName).contains(passageName)){
				return true;
			}else{
				return false;
			}			
		} catch (Exception e) {
			System.out
					.println("Unable create the passage---- > " + passageName);
			return false;
		}

	}

	public String searchPassage(String passage) {
		try {
			waitAndClearField(searchAutoComplete);
			customeWaitTime(5);
			waitForElementAndSendKeys(searchAutoComplete, passage);
			waitForElementAndClick(searchButton);
			customeWaitTime(5);
			return waitForElementPresenceAndGetText("//td[@class='watable-col-name' and text() ='"
					+ passage + "']");
		} catch (Exception e) {

			System.out.println("Unable to find the passage -->  " + passage);
			return "Unable to find the passage";
		}

	}

	public void deletePassage(String passage) {
		try {
			searchPassage(passage);
			customeWaitTime(5);
			waitForElementAndClick(deleteIconList);
			customeWaitTime(5);
			if (globalModalDeleteBody.isDisplayed()) {
				waitForElementAndClick(globalModalDeleteButton);
			}

		} catch (Exception e) {
			System.out.println("Unable to delete the passage   " + passage);
		}

	}

	public List<WebElement> listOfResults(String value) {
		customeWaitTime(2);
		List<WebElement> recordsList = driver.findElements(By
				.xpath("//td[text()='" + value + "']"));

		return recordsList;
	}

	public boolean filterByArgunent(String arg, String value) {

		switch (arg) {
		case "lexile":
			waitForElementAndClick(lexileFilter);
			waitForElementPresenceAndSendKeys(
					"(//input[@class='form-control input-sm'])[1]", value);
			break;
		case "FleschKincaid":
			waitForElementAndClick(FleschKincaidFilter);
			waitForElementPresenceAndSendKeys(
					"(//input[@class='form-control input-sm'])[2]", value);
			break;
		default:
			break;
		}

		return waitAndGetElementText(encouteredRecords).contains(
				Integer.toString(this.listOfResults(value).size()));
	}

	public boolean filterByCheck(String arg, String value) {

		switch (arg) {
		case "contentArea":
			waitForElementAndClick(contentArea);
			waitForElementPresenceAndClick("//i[./following-sibling::span[text()='"
					+ value + "']]");
			break;
		case "grade":
			waitForElementAndClick(grade);
			waitForElementPresenceAndClick("//i[./following-sibling::span[text()='"
					+ value + "']]");
			break;
		case "lifecycle":
			waitForElementAndClick(lifecycle);
			waitForElementPresenceAndClick("//i[./following-sibling::span[text()='"
					+ value + "']]");
			break;
		default:
			break;
		}
		return waitAndGetElementText(encouteredRecords).contains(
				Integer.toString(this.listOfResults(value).size()));
	}

}
