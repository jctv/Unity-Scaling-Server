package pages;

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
	
	
	/**
	 * Added this method as Item bank  drop  down is populating through plugin not a normal  select box 
	 * @param option
	 */
	public void selectItemBank(String option){
		waitForElementAndClick(itemBankDropdown);
		waitTime();
		waitTime();
		List<WebElement> itemBankoptions= driver.findElements(By.xpath("//div[@class='btn-group bootstrap-select content-bank select-search-by-name-item_bank open']//ul/li"));
		for (WebElement itemBank : itemBankoptions){
			try{
			if(itemBank.getText().equals(option)){
				waitTime();
				waitTime();
				itemBank.click();
			   break;
			}
			
			}catch(Exception e ){
				System.out.println(option + " is not available" );
			}
		}
		
	}
}
