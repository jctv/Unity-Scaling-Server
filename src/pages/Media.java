package pages;

import java.io.File;

import generic.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Media extends BasePage{
	
	public Media(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[text()='Upload Media']")
	public WebElement uploadMediaLink;

	@FindBy(id = "upload-bank")
	public WebElement selectUploadItemBank;
	
	@FindBy(id = "name")
	public WebElement nameDomainField;
	
	@FindBy(xpath = "//tbody[@class='files']//td[1]/i")
	public WebElement mediaUploadSuccessfulIcon;
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backToDashboard;
	
	@FindBy(xpath = "//span[text()='MIME Type']")
	public WebElement mimeTypeFilterNav;
	
	@FindBy(xpath = ".//*[@id='searchFilter']/ul/li[1]/ul/li/div/input")
	public WebElement mimeTypeInputField;
	
	@FindBy(xpath = "//span[text()='Ref Object']")
	public WebElement refObjecteFilterNav;
	
	@FindBy(xpath = "//span[text()='item']/../i")
	public WebElement itemCheckBox;
	
	@FindBy(xpath = "//span[text()='passage']/../i")
	public WebElement passageCheckBox;
	
	@FindBy(xpath = "//span[text()='rubric']/../i")
	public WebElement rubricCheckBox;
	
	@FindBy(xpath = "//span[text()='Chart']/../i")
	public WebElement chartCheckBox;
	
	@FindBy(xpath = "//span[text()='Graph']/../i")
	public WebElement graphCheckBox;
	
	@FindBy(xpath = "//span[text()='Map']/../i")
	public WebElement mapCheckBox;
	
	@FindBy(xpath = "//span[text()='Other']/../i")
	public WebElement otherCheckBox;
	
	@FindBy(xpath = "//*[@id='object-select-titles']/div")
	public WebElement clickToSelectBank;
		
	@FindBy(xpath = "//span[text()='Type']")
	public WebElement typeFilterNav;
	
	@FindBy(xpath = "//span[text()='Banks']")
	public WebElement banksFilterNav;
	
	@FindBy(xpath = "//td[@class='watable-col-name']")
	public WebElement mediaNameInListing;
	
	@FindBy(xpath = "//td[@class='watable-col-mime_type']")
	public WebElement mimeTypeInListing;
	
	@FindBy(xpath = "//td[@class='watable-col-length']")
	public WebElement mediaLengthInListing;
	
	@FindBy(xpath = "//td[@class='watable-col-ref_types']")
	public WebElement mediaRefTypeInListing;
	
	@FindBy(xpath = "//td[@class='watable-col-type']")
	public WebElement mediaTypeInListing;
	
	@FindBy(xpath = "//td[@class='watable-col-item_banks']")
	public WebElement mediaItemBankNameInListing;
	
	
	
	/**
	 * 
	 * @param filepath
	 * @param itemBankName
	 * @return
	 */
	public boolean uploadMedia(String filepath , String itemBankName){
		boolean isMediaUploadSuccessful = false ;
		try{
	    	waitForElementAndClick(uploadMediaLink);
			File f = new File(filepath);
			String mediaUploadFilepath = f.getAbsolutePath();
			waitTime();
			selectOption(selectUploadItemBank, itemBankName);
			waitTime();
			fileupload.sendKeys(mediaUploadFilepath);
			waitTime();
			if(mediaUploadSuccessfulIcon.getAttribute("style").contains("green")){
				isMediaUploadSuccessful = true;
		    }
	    	waitForElementAndClick(globalModalUploadOkButton);
	    	waitTime();
		}catch(Exception e){
			
			System.out.println("Unable to upload the media  file  -->  "  + filepath);
		}
		return isMediaUploadSuccessful;
	}
	
	
	
	public void searchMedia(String media) {
		try {
			//searchAutoComplete.clear();
			//waitTime();
			waitForElementAndSendKeys(searchAutoComplete, media);
			waitForElementAndClick(searchButton);
			waitTime();
		} catch (Exception e) {

			System.out.println("Unable to find the Media  -->  " + media);

		}

	}
	
	public void deleteMedia(String itemName) {
		try {
			searchAutoComplete.clear();
			searchMedia(itemName);
			waitTime();
			waitForElementAndClick(deleteIconList);
			waitTime();
			waitTime();
		} catch (Exception e) {
			System.out.println("Unable to delete the Item   " + itemName);
		}

	}

	
 public void filterMediaByItemBank(String media , String itemBank){	 
	 try{
			waitForElementAndClick(banksFilterNav);
			customeWaitTime(2);
		 
	 }catch(Exception e ){
			System.out.println("Unable to filter the media " + media + "for item bank " + itemBank);

		 
	 }
	 
	 
  }
	
}
