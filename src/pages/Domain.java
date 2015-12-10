package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class Domain extends BasePage{

	public Domain(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = "//span[text()='Create Domain']")
	public WebElement createDomainLink;

	@FindBy(id = "abbreviation")
	public WebElement abbreviationDomainField;
	
	@FindBy(id = "name")
	public WebElement nameDomainField;
	
	@FindBy(id = "createButton")
	public WebElement createDomainButton;
	
	@FindBy(xpath = "//table[@class='watable table table-striped table-hover table-bordered table-condensed']/tbody/tr")
	public List <WebElement>  domaintableRows;
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backToDashboard;
	
	
	
	
	public void createDomain(String domainAbbreviation, String domainName) {
		try {
			customeWaitTime(6);
			if (domaintableRows.size() <= 6) {
				waitForElementAndClick(createDomainLink);
				customeWaitTime(6);
				waitForElementAndSendKeys(abbreviationDomainField,
						domainAbbreviation);
				waitTime();
				waitForElementAndSendKeys(nameDomainField, domainName);
				waitTime();
				waitForElementAndClick(createDomainButton);
				customeWaitTime(12);
				waitForElementAndClick(globalModalInfoOkButton);
				customeWaitTime(6);
			} else {
				System.out
						.println("Already 6 domains exist so that not creating new Domain otherwaise cassendra will go down ");

			}
		} catch (Exception e) {
			System.out.println("Unable to create domain ");

		}

	}
	
	public void deleteDomain(String domainAbbreviation){
		try{
			waitTime();
			WebElement domainDeleteIcon = driver.findElement(By.xpath("//td[@class='watable-col-abbreviation' and text () = '" + domainAbbreviation + "']/../td[2]//button[@class='btn btn-xs btn-link deleteRow']"));
			waitTime();
			waitForElementAndClick(domainDeleteIcon);
			customeWaitTime(15);			
			waitForElementAndClick(globalModalDeleteButton);
			customeWaitTime(15);
			waitForElementAndClick(globalModalInfoOkButton);
			customeWaitTime(4);
			
		}catch(Exception e){
			System.out.println("Unable to Delete domain ");
			
		}
		
	}
	
	public boolean isDomainExist(String domainAbbreviation) {
		boolean isDomainExist = false;
		try {
			WebElement domain = driver
					.findElement(By
							.xpath("//td[@class='watable-col-abbreviation' and text () = '"
									+ domainAbbreviation + "']"));
			if (domain.isDisplayed()) {
				isDomainExist = true;
			}
		} catch (Exception e) {
			System.out.println("Domain " + domainAbbreviation
					+ " does not exist");

		}

		return isDomainExist;

	}
}
