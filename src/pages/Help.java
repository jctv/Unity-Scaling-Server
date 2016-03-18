package pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class Help extends BasePage {

	public Help(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = "//a[text()='Add Help']")
	public WebElement addHelpLink;
	
	@FindBy(xpath = ".//*[@id='region-navigation']/ul/li[4]/span")
	public WebElement importHelpLink;
	
	@FindBy(xpath = "//a[text()='Export Help']")
	public WebElement exportHelpLink;
	
	
	@FindBy(xpath = "//a[text()='View Help']")
	public WebElement viewHelpLink;
	
	@FindBy(id = "tileName")
	public WebElement selectTile;
	
	@FindBy(id = "helpCreateInputSubmit")
	public WebElement helpSaveButton;
	
	@FindBy(xpath = ".//*[@id='addNewMedia']/button")
	public WebElement addTutorialButton;
	
	@FindBy(xpath = ".//*[@id='addNewGuide']/button")
	public WebElement addUserGuideButton;
	
	@FindBy(xpath = ".//*[@id='contentForm']/div[1]/div[1]/input")
	public WebElement firstHintInputField;
	
	@FindBy(xpath = "//button[@class='btn btn-success btn-add']")
	public WebElement addHintButton;
	
	@FindBy(xpath = "//button[@class='btn btn-primary btn-add-child']")
	public WebElement addChildHintButton;
	
	@FindBy(xpath = ".//*[@id='createText']']")
	public WebElement createPermissionInputField;
	
	@FindBy(xpath = ".//*[@id='createText']']")
	public WebElement editPermissionInputField;
	
	@FindBy(id = "searchAutoComplete")
	public WebElement searchAutoCompleteField;
	
	@FindBy(xpath = "//button[@class='btn btn-xs btn-link deleteRow']")
	public WebElement deleteHelp;
	
	@FindBy(id = "globalModalDelete")
	public WebElement deleteHelpPopUp;
	
	@FindBy(id = "globalModalDeleteButton")
	public WebElement deletebuttonHelpPopUp;
	
	@FindBy(xpath = ".//*[@id='region-navigation']/ul/li[2]/span")
	public WebElement backToHelp;
	
	@FindBy(xpath = "//tbody[@class='files']//td[1]/i")
	public WebElement helpImportSuccessfulIcon;
	
	@FindBy(xpath = "//td[@class='watable-col-package_file_name']")
	public WebElement helpFileNameInListings;
	
	@FindBy(xpath = "//td[@class='watable-col-status']")
	public WebElement helpStatusInListings;
	
	@FindBy(xpath = "//td[@class='watable-col-status_detail']")
	public WebElement helpDetailInListings;
	
	@FindBy(xpath = ".//*[@id='contentListTemplate']/div/ol/li[1]/a/b")
	public WebElement helpHint1Content;
	
	
	
	public void addHelp(String tile , String helpHint , String mediafilePath, String userguideFilePath ){
		try{
        waitForElementAndClick(addHelpLink);
        selectOption(selectTile , tile);
        waitForElementAndClick(addTutorialButton);
        File f = new File(mediafilePath);
		String mediafile = f.getAbsolutePath();
		fileupload.sendKeys(mediafile);
    	customeWaitTime(2);
        waitForElementAndClick(globalModalUploadOkButton);
    	customeWaitTime(2);
        waitForElementAndClick(addUserGuideButton);
    	customeWaitTime(2);
        File f1 = new File(userguideFilePath);
		String userGuidefile = f1.getAbsolutePath();
		fileupload.sendKeys(userGuidefile);
    	customeWaitTime(2);
        waitForElementAndClick(globalModalUploadOkButton);
    	customeWaitTime(2);
        waitForElementAndSendKeys(firstHintInputField ,helpHint);
        waitForElementAndClick(helpSaveButton);
    	customeWaitTime(2);
        waitForElementAndClick(globalModalInfoOkButton);
    	customeWaitTime(2);

		}catch(Exception e){
			System.out.println("Unable to add help for tile " + tile);
			
		}
	}
	
	public void searchHelp(String help){
		try{
		  customeWaitTime(5);
		  waitForElementAndSendKeys(searchAutoCompleteField, help);
		  waitForElementAndClick(searchButton);
		  customeWaitTime(5);
		}catch(Exception e){
			System.out.println("Unable to find the Help "  + help);

		}
		
	}
	
	public void deleteHelp(String help){
		try{
		searchHelp(help);	
		customeWaitTime(5);
		waitForElementAndClick(deleteHelp);
		customeWaitTime(10);
		if(deleteHelpPopUp.isDisplayed()){
		   waitForElementAndClick(deletebuttonHelpPopUp);
		}
			
		}catch(Exception e){
			 System.out.println("Unable to delete the Help  " + help);
		}
		
	}
	
	public boolean importHelp(String tileName, String filepath ){
		boolean isHelpImportSuccessful = false ;
		try{
			File f = new File(filepath);
			String helpImportFilepath = f.getAbsolutePath();
			waitForElementAndClick(importHelpLink);
			customeWaitTime(5);
			fileupload.sendKeys(helpImportFilepath);
			if(helpImportSuccessfulIcon.getAttribute("style").contains("green")){
				isHelpImportSuccessful = true;
		    }
			waitForElementAndClick(globalModalUploadOkButton);
			customeWaitTime(2);
		}catch(Exception e){
			 System.out.println("Unable to import  the Help for tile  " + tileName);
		}
		return isHelpImportSuccessful;

	}
	
	
	public void updateHelpHint(String tileName , String updateHint){
		try{
			waitForElementAndClick(editIconList);
			customeWaitTime(2);
	        waitForElementAndSendKeys(firstHintInputField ,updateHint);
	        waitForElementAndClick(helpSaveButton);
	        customeWaitTime(2);
	        waitForElementAndClick(globalModalInfoOkButton);
	    	customeWaitTime(2);
		}catch(Exception e){
			
		}
		
		
	}
	

}
