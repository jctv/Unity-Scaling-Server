package pages;

import java.io.File;

import generic.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Media extends BasePage{
	
	/**
	 * Contractor 
	 * @param driver
	 */
	
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
	
	@FindBy(xpath = "//div[@class='layoutHorizontalLeftPane col-md-4']//input[@id='searchAutoComplete']")
	public WebElement searchItemBankFilterPopup;

	@FindBy(xpath = "//div[@class='layoutHorizontalLeftPane col-md-4']//span[@id='searchButton']")
	public WebElement searchButtonItemBankFilterPopup;

	@FindBy(xpath = "//span[text()='Banks']/../../ul//div/div/div")
	public WebElement filteredItemBank;
	
	@FindBy(xpath = ".//*[@id='viewMediaPreview']/video")
	public WebElement previewVideoTumbnail;
	
	@FindBy(xpath = ".//*[@id='viewMediaPreview']/img")
	public WebElement previewImageTumbnail;
	
	
	
	
	/**
	 * This is the method for upload the media
	 * @param filepath
	 * @param itemBankName
	 * @return upload success status
	 */
	public boolean uploadMedia(String filepath , String itemBankName){
		boolean isMediaUploadSuccessful = false ;
		try{
	    	waitForElementAndClick(uploadMediaLink);
	    	customeWaitTime(5);
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
	    	customeWaitTime(5);
		}catch(Exception e){
			
			System.out.println("Unable to upload the media  file  -->  "  + filepath);
		}
		return isMediaUploadSuccessful;
	}
	
	
	/**
	 * This is the method for search the media
	 * @param media
	 */
	public void searchMedia(String media) {
		try {
			waitForElementAndSendKeys(searchAutoComplete, media);
			waitForElementAndClick(searchButton);
			waitTime();
		} catch (Exception e) {

			System.out.println("Unable to find the Media  -->  " + media);

		}

	}
	
	/**
	 * This is the method for deleting the media
	 * @param itemName
	 */
	public void deleteMedia(String itemName) {
		try {
			waitForElementAndClick(deleteIconList);
			customeWaitTime(2);
			waitForElementAndClick(globalModalDeleteButton);
			customeWaitTime(2);
			
		} catch (Exception e) {
			System.out.println("Unable to delete the Item   " + itemName);
		}

	}

	/**
	 * This is the method for filer media by item bank
	 * @param media
	 * @param itemBank
	 */
 public void filterMediaByItemBank(String media , String itemBank){	 
		try {
			waitForElementAndClick(banksFilterNav);
			customeWaitTime(2);
			waitForElementAndClick(clickToSelectBank);
			customeWaitTime(5);
			searchItemBankFilterPopup.clear();
			waitForElementAndDoubleClick(searchButtonItemBankFilterPopup);
			waitForElementAndSendKeys(searchItemBankFilterPopup, itemBank);
			waitForElementAndDoubleClick(searchButtonItemBankFilterPopup);
			WebElement serachedItembank = driver.findElement(By
					.xpath("//tr[@class='data-row']//td[text()='"
							+ itemBank + "']"));
			waitForElementAndDoubleClick(serachedItembank);
			customeWaitTime(5);
			waitForElementAndClick(globalModalOKCancelSaveButton);

		} catch (Exception e) {
			System.out.println("Unable to filter the media " + media
					+ "for item bank " + itemBank);

		}
  }
	
}
