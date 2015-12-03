package pages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class ItemImport extends BasePage {

	public ItemImport(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//span[text()='Import']")
	public WebElement importLink;
	
	@FindBy(xpath = "//span[text()='Help']")
	public WebElement helpLink;
	
	@FindBy(xpath = "//td[@class='watable-col-package_file_name']")
	public WebElement fileName;
	
	@FindBy(xpath = "//td[@class='//td[@class='watable-col-sis_file_names']")
	public WebElement itemImportFiles;
	
	@FindBy(xpath = "//td[@class='watable-col-status']")
	public WebElement itemImportStatus;

	@FindBy(xpath = "//td[@class='watable-col-status_detail']")
	public WebElement itemImportDetail;
	
	@FindBy(xpath = "//td[@class='watable-col-start']")
	public WebElement itemImportStart;
	
	@FindBy(xpath = "//td[@class='watable-col-end']")
	public WebElement itemImporEnd;
	
	@FindBy(id = "fileupload")
	public WebElement addFileButton;
	
	@FindBy(xpath = "//button[@class='btn btn-warning cancel']")
	public WebElement cancelButton;
	
	@FindBy(xpath = "//button[@class='btn exit']")
	public WebElement exitButton;
	
	@FindBy(id = "item-bank")
	public WebElement selectItemBank;
	
	@FindBy(id = "vendorHacks")
	public WebElement selectPkgFormat;
	
	
	@FindBy(id = "vendorlifecycle")
	public WebElement selectLifeCycle;
	
	@FindBy(id = "addeditem")
	public WebElement overrideLifeCycleCheckBox;
	
	
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement homeLink;
	
	@FindBy(xpath = "//table[@role='presentation']/tbody//td[2]")
	public WebElement importedFileEntry;
	
	@FindBy(xpath = "//button[@data-id='item-bank']")
	public WebElement itemBankDropdown;
	
	@FindBy(xpath = "//button[@data-id='item-bank']/../div/ul")
	public WebElement  itemBankDropdownList;

	@FindBy(xpath = "//span[text()='Item Files']")
	public WebElement  filterItemFiles;
	
	@FindBy(xpath = "//tr[1]/td[@class='watable-col-package_file_name']")
	public WebElement itemImportPackageFileNameList;
	
	@FindBy(xpath = "//tr[1]/td[@class='watable-col-item_file_names']")
	public WebElement itemImportFileNameList;
	
	@FindBy(xpath = "//tr[1]/td[@class='watable-col-status']")
	public WebElement itemImportFileStatusList;
	
	@FindBy(xpath = "//tr[1]/td[@class='watable-col-status_detail']")
	public WebElement itemImportFileDetailsList;
	
	@FindBy(xpath = "//tr[1]/td[@class='watable-col-start]")
	public WebElement itemImportFileStartTimeList;
	
	@FindBy(xpath = "//tr[1]/td[@class='watable-col-end']")
	public WebElement itemImportFileEndTimeList;
	
	@FindBy(xpath = "//input[@class='form-control input-sm']")
	public WebElement itemFileInputField;
	
	@FindBy(xpath = "//span[text()='Status']")
	public WebElement filterItemImportStatus;
	
	@FindBy(xpath = "//span[text()='Completed']/../i")
	public WebElement filterItemImportCompleted;
	
	@FindBy(xpath = "//span[text()='In Progress']/../i")
	public WebElement filterItemImportInprogress;
	
	@FindBy(xpath = "//span[text()='Failed']/../i")
	public WebElement filterItemImportFailed;
	
	@FindBy(xpath = "//tr[1]/td[@class='watable-col-preview']//button[@class='btn btn-xs btn-link previewRow']")
	public WebElement importItemPreviewButton;
	
	@FindBy(xpath = "//td[text()='ITEM']/../td[2]")
	public WebElement itemCount;
	
	@FindBy(xpath = "//td[@class='media-count']")
	public WebElement mediaCount;
	
	@FindBy(xpath = "//td[@class='passage-count']")
	public WebElement passageCount;
	
	@FindBy(xpath = "//td[@class='rubric-count']")
	public WebElement rubricCount;
	
	@FindBy(xpath = "//div[@class='panel-heading']/h5")
	public WebElement itemImportSummary;
	
	@FindBy(xpath = "//div[@class='panel panel-primary']//tr[@class='active']/td[@colspan='5']")
	public WebElement itemImportSummaryFileName;
	
	@FindBy(xpath = "//td[text()='ITEM']/../td[2]")
	public WebElement itemImportSummaryItem;
	
	@FindBy(xpath = "//td[text()='MEDIA']/../td[2]")
	public WebElement itemImportSummaryMedia;
	
	@FindBy(xpath = "//td[text()='CSS']/../td[2]")
	public WebElement itemImportSummaryCss;
	
	@FindBy(xpath = "//tbody[@class='files']//td[1]/i")
	public WebElement itemImportSuccessfulIcon;
	
	@FindBy(xpath = "//div[@class='panel panel-primary']//tr[4]/td[1]")
	public WebElement itemImportError;
	
	@FindBy(xpath = "//div[@class='panel panel-primary']//tr[4]/td[2]")
	public WebElement itemImportErrorMessage;
	
	@FindBy(xpath = "//div[@class='panel panel-primary']//tr[9]/td[2]")
	public WebElement itemImportErrorMessage1;
	
	@FindBy(xpath = "//div[@class='panel panel-primary']//tr[10]/td[2]")
	public WebElement itemImportErrorMessage2;
	
	@FindBy(xpath = "//div[@class='panel panel-primary']//tr[11]/td[2]")
	public WebElement itemImportErrorMessage3;
	
	
	public DashBoard backToDashboard(){
		waitForElementAndClick(homeLink);
		customeWaitTime(5);
		return new DashBoard(driver);
	}
	
	public void searchItemImportFile(String fileName){
		try{
		  searchAutoComplete.clear();
		  customeWaitTime(5);
		  waitForElementAndSendKeys(searchAutoComplete, fileName);
		  waitForElementAndClick(searchButton);
		  customeWaitTime(5);
		}catch(Exception e){
			
			System.out.println("Unable to find the Item Import file  -->  "  + fileName);

		}
		
	}
	
	/**
	 * 
	 * @param filepath
	 * @param itemBankName
	 * @param authoringTool
	 * @return
	 */
	public boolean importItem(String filepath , String itemBankName , String pkgFormat , String itemLifeCycle){
		boolean isImportSuccessful = false ;
		try{
			File f = new File(filepath);
			String ItemImportFilepath = f.getAbsolutePath();
			waitForElementAndClick(importLink);
			customeWaitTime(5);
			selectItemBank(itemBankName);
			customeWaitTime(5);
			selectOption(selectPkgFormat, pkgFormat);
			customeWaitTime(5);
			selectOption(selectLifeCycle, itemLifeCycle);
			customeWaitTime(5);
			addFileButton.sendKeys(ItemImportFilepath);
			customeWaitTime(5);
			if(itemImportSuccessfulIcon.getAttribute("style").contains("green")){
				isImportSuccessful = true;
		    }
			customeWaitTime(10);
		}catch(Exception e){
			
			System.out.println("Unable to import the file  -->  "  + filepath);
		}
		return isImportSuccessful;
	}
	
	/**
	 * Added this method as Item bank  drop  down is populating through plugin not a normal  select box 
	 * @param option
	 */
	public void selectItemBank(String option){
		itemBankDropdown.click();
		customeWaitTime(5);
		List<WebElement> itemBankoptions= driver.findElements(By.xpath("//div[@class='btn-group bootstrap-select select-search-by-name-item_bank open']//ul//li"));
		for (WebElement itemBank : itemBankoptions){
			try{
			if(itemBank.getText().equals(option)){
				itemBank.click();
			   break;
			}
			
			}catch(Exception e ){
				System.out.println(option + " is not available" );
			}
		}
		
	}
}
