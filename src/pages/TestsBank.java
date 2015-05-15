package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestsBank extends BasePage {

	BasePage base;
	BaseTest test;

	public TestsBank(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Tabs Ids
	@FindBy(xpath = "//*[@id='region-navigation']/div/span[1]")
	public WebElement createBankLink;

	@FindBy(id = "bankCreateInputName")
	public WebElement bankCreateInputName;
	
	@FindBy(id = "bankCreateInputDescription")
	public WebElement bankCreateInputDescription;
	
	
	@FindBy(id = "bankCreateInputSubmit")
	public WebElement bankCreateInputSubmit;


	public void createBank(String bankName, String descBank) {
		try {
			waitTime();
			waitForElementAndClick(createBankLink);
			waitForElementAndSendKeys(bankCreateInputName, bankName);
			waitForElementAndSendKeys(bankCreateInputDescription, descBank);
			waitForElementAndClick(bankCreateInputSubmit);
			System.out.println("Test Bank Created");
			backToDashboard();
		} catch (Exception e) {
			System.out.println("Unable to create the bank");
		}
		
	
	}

}
