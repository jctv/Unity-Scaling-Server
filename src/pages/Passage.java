package pages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class Passage extends BasePage{
	

	public Passage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='Create']")
	public WebElement createPassageLink;

	
	@FindBy(id = "contentCreateInputName")
	public WebElement passageInputNameField;
	
	@FindBy(id = "contentCreateInputDescription")
	public WebElement passageDescField;
	
	@FindBy(xpath = ".//*[@id='quickViewContentCreate']/div/form/button[1]")
	public WebElement passageCreateAndEditButton;
	
	@FindBy(xpath = ".//*[@id='quickViewContentCreate']/div/form/button[2]")
	public WebElement passageCreateButton;
	
	@FindBy(xpath = ".//*[@id='quickViewContentCreate']/div/form/div[1]/div/button")
	public WebElement itemBankDropdown;
	
	@FindBy(id = "global-object-name")
	public WebElement passageEditNameField;
	
	@FindBy(xpath = "//button[@class='btn btn-primary fileinput-button']")
	public WebElement passageUploadFileButton;
	
	@FindBy(xpath = ".//*[@id='bs-example-navbar-collapse-1']/form[2]/div/button")
	public WebElement passageSaveButton;
	
	@FindBy(xpath = "//tbody[@class='files']//td[1]/i")
	public WebElement passageUploadIcon;
	
	@FindBy(xpath = "//div[@class='dropdown-menu open']//input")
	public WebElement searchInputField;
	
	
	@FindBy(id = "lexileFilter")
	public WebElement lexileFilter;
	
	@FindBy(id = "FleschKincaidFilter")
	public WebElement FleschKincaidFilter;
	
	
	
	/**
	 * Added this method as Item bank  drop  down is populating through plugin not a normal  select box 
	 * @param option
	 */
	public void selectItemBank(String option){
		waitForElementAndClick(itemBankDropdown);
		customeWaitTime(5);
		waitForElementAndSendKeys(searchInputField, option);
		customeWaitTime(5);
		List<WebElement> itemBankoptions= driver.findElements(By.xpath("//div[@class='btn-group bootstrap-select content-bank select-search-by-name-item_bank open']//ul/li"));
		for (WebElement itemBank : itemBankoptions){
			try{
			if(itemBank.getText().equals(option)){
				customeWaitTime(5);
				itemBank.click();
			   break;
			}
			
			}catch(Exception e ){
				System.out.println(option + " is not available" );
			}
		}
		
	}
	
	
	public void createPassage(String itemBank , String passageName , String desc , String filepath ){
		try{
		boolean isPassageUploaded  =  false;	
		waitForElementAndClick(createPassageLink);
		customeWaitTime(5);
		selectItemBank(itemBank);
		customeWaitTime(1);
		waitForElementAndSendKeys(passageInputNameField, passageName);
		customeWaitTime(1);
		waitForElementAndSendKeys(passageDescField, desc);
		customeWaitTime(1);
		waitForElementAndClick(passageCreateAndEditButton);
		customeWaitTime(5);
		waitForElementAndClick(passageUploadFileButton);
		customeWaitTime(5);
		File f = new File(filepath);
		String passageUploadFilepath = f.getAbsolutePath();
		//waitForElementAndSendKeys(fileupload, passageUploadFilepath);
		fileupload.sendKeys(passageUploadFilepath);
		customeWaitTime(10);

		if(passageUploadIcon.getAttribute("style").contains("green")){
			isPassageUploaded = true;
	    }
		waitForElementAndClick(globalModalUploadOkButton);
		customeWaitTime(5);
		}catch(Exception e){
			System.out.println("Unable create the passage---- > " + passageName);

		}

	}
	
	
	public void searchPassage(String passage) {
		try {
			waitAndClearField(searchAutoComplete);
			customeWaitTime(5);
			waitForElementAndSendKeys(searchAutoComplete, passage);
			waitForElementAndClick(searchButton);
			customeWaitTime(5);
		} catch (Exception e) {

			System.out.println("Unable to find the passage -->  " + passage);

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
	public List<WebElement> listOfResults(String value){
		customeWaitTime(2);
		List<WebElement> recordsList = driver.findElements(By.xpath("//td[text()='"+value+"']"));
		
		return recordsList;
	} 
	
	public boolean filterByArgunent(String arg, String value) {

		switch (arg) {
		case "lexile":
			waitForElementAndClick(lexileFilter);
			waitForElementPresenceAndSendKeys("(//input[@class='form-control input-sm'])[1]",value);
			break;
		case "FleschKincaid":
			waitForElementAndClick(FleschKincaidFilter);
			waitForElementPresenceAndSendKeys("(//input[@class='form-control input-sm'])[2]",value);
			break;
		default:
			break;
		}
		
		return waitAndGetElementText(encouteredRecords).contains(Integer.toString(this.listOfResults(arg).size()));
	}

	
	/*public boolean filterByCheck(String arg, String value) {

		switch (arg) {
		case "role":
			waitForElementAndClick(roleBullet); 
			waitForElementPresenceAndClick("//i[./following-sibling::span[text()='"+value+"']]");
			

			break;
		case "grade":
			waitForElementAndClick(gradeBullet); 
			waitForElementPresenceAndClick("//i[./following-sibling::span[text()='"+value+"']]");
			break;
		default:
			break;
		}
		waitForElementAndClick(searchMine);
		return waitAndGetElementText(encouteredRecords).contains(Integer.toString(this.listOfResults().size()));
	}
*/
}
