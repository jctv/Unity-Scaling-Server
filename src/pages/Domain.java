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
	
	
	
	
	public void CreateDomain(String domainAbbreviation, String domainName) {
		try {
			waitTime();
			waitTime();
			if (domaintableRows.size() <= 5) {
				waitTime();
				waitForElementAndSendKeys(abbreviationDomainField,
						domainAbbreviation);
				waitTime();
				waitForElementAndSendKeys(abbreviationDomainField, domainName);
				waitTime();
				waitForElementAndClick(createDomainButton);
				waitTime();
				waitTime();
				waitTime();
				waitTime();

			} else {
				System.out
						.println("Already 5 domain exist so that not creating new Domain otherwaise cassendra will go down ");

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
			waitTime();
			waitTime();
			waitTime();
		}catch(Exception e){
			System.out.println("Unable to Delete domain ");

			
		}
		
		
		
		
	}
}
