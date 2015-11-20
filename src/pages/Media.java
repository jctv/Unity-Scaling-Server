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
	
	@FindBy(xpath = "//span[text()='Upload Media']")
	public WebElement uploadMediaLink;

	@FindBy(id = "upload-bank")
	public WebElement selectUploadItemBank;
	
	@FindBy(id = "name")
	public WebElement nameDomainField;
	
	@FindBy(xpath = "//tbody[@class='files']//td[1]/i")
	public WebElement mediaUploadSuccessfulIcon;
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backToDashboard;
	
	
	
	/**
	 * 
	 * @param filepath
	 * @param itemBankName
	 * @return
	 */
	public boolean uploadMedia(String filepath , String itemBankName){
		boolean isMediaUploadSuccessful = false ;
		try{
			File f = new File(filepath);
			String mediaUploadFilepath = f.getAbsolutePath();
			waitTime();
			selectOption(selectUploadItemBank, itemBankName);
			waitTime();
			waitTime();
			fileupload.sendKeys(mediaUploadFilepath);
			waitTime();
			if(mediaUploadSuccessfulIcon.getAttribute("style").contains("green")){
				isMediaUploadSuccessful = true;
		    }
			
		}catch(Exception e){
			
			System.out.println("Unable to upload the media  file  -->  "  + filepath);
		}
		return isMediaUploadSuccessful;
	}
	
	
	public void searchMedia(String media) {
		try {
			searchAutoComplete.clear();
			waitTime();
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

	

}
