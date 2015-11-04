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
	public WebElement selectAuthoringTool;
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement homeLink;
	
	@FindBy(xpath = "//table[@role='presentation']/tbody//td[2]")
	public WebElement importedFileEntry;
	
	@FindBy(xpath = "//button[@data-id='item-bank']")
	public WebElement itemBankDropdown;
	
	@FindBy(xpath = "//button[@data-id='item-bank']/../div/ul")
	public WebElement  itemBankDropdownList;
	
	
	public DashBoard backToDashboard(){
		waitForElementAndClick(homeLink);
		return new DashBoard(driver);
	}
	
	public void searchItemImportFile(String fileName){
		try{
		  searchAutoComplete.clear();
		  waitTime();
		  waitForElementAndSendKeys(searchAutoComplete, fileName);
		  waitForElementAndClick(searchButton);
		  waitTime();
		}catch(Exception e){
			
			System.out.println("Unable to find the Item Import file  -->  "  + fileName);

		}
		
	}
	
	
	
	public void importItem(String filepath , String itemBankName , String authoringTool){
		
		try{
			
			File f = new File(filepath);
			String ItemImportFilepath = f.getAbsolutePath();
			waitForElementAndClick(importLink);
			waitTime();
			selectItemBank(itemBankName);
			waitTime();
			selectOption(selectAuthoringTool, authoringTool);
			waitTime();
			addFileButton.sendKeys(ItemImportFilepath);
			waitTime();

		}catch(Exception e){
			
			System.out.println("Unable to import the file  -->  "  + filepath);

		}
		
	}
	
	/**
	 * Added this method as Item bank  drop  down is populating through plugin not a normal  select box 
	 * @param option
	 */
	public void selectItemBank(String option){
		itemBankDropdown.click();
		waitTime();
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
