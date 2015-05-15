package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemsBank extends BasePage {

	public ItemsBank(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Tabs Ids
	@FindBy(xpath = "//*[@id='region-navigation']/div/span[1]")
	public WebElement createItemBank;
	
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backLink;


	@FindBy(id = "bankCreateInputName")
	public WebElement bankCreateInputName;
	
	@FindBy(id = "bankCreateInputDescription")
	public WebElement bankCreateInputDescription;
	
	@FindBy(id = "bankCreateInputSubmit")
	public WebElement bankCreateInputSubmit;
	
	

	public void createBank(String bank, String description) {
		try {
			waitForElementAndClick(createItemBank);
			waitForElementAndSendKeys(bankCreateInputName, bank);
			waitForElementAndSendKeys(bankCreateInputDescription, description);
			waitForElementAndClick(bankCreateInputSubmit);
			System.out.println("Bank created");
			waitForElementAndClick(backLink);
			
		} catch (Exception e) {
			System.out.println("Unable to create the bank");
		}

	}

}
