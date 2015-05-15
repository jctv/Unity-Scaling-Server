package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Delivery extends BasePage {

	BasePage base;
	BaseTest test;

	public Delivery(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@id='playground']/div/div[2]/div/div[1]/div[2]/div[3]/label/input")
	public WebElement item0;


	
	@FindBy(xpath = "//button[contains(@class,'btn btn-primary btn-sm btn-block start-test-link')]")
	public WebElement startTestButton;
					
	@FindBy(xpath = "//button[contains(@class,'resume')]")
	public WebElement resumeTestButton;

				    
	@FindBy(xpath = "//*[@id='itemDisplay']/div/div/div/div/div[2]/span")
	public WebElement btn;

	@FindBy(id = "HSAlgebra1")
	public WebElement hsAlgebra1Link;

	@FindBy(xpath = "//*[@id='HSAlgebra1']")
	public WebElement hsAlgebra1LinkXpath;
		
	@FindBy(xpath = "//*[@id='testDelivery']/div/div[1]/div[1]/div/div[3]")
	public WebElement exitButton;

	
	
	@FindBy(xpath = "//button[contains(@class,'finish-test-link')]")
	public WebElement finishTestButton;

	@FindBy(xpath = "/html/body/nav/div/div[1]/button")
	public WebElement menu;
	
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement home;
	
	public void takeTest() {

		waitTime();
		try {
			
			waitForElementAndClick(startTestButton);
		} catch (Exception e) {

			waitForElementAndClick(resumeTestButton);
		}

		System.out.println("Sarting test");

		waitTime();

		waitForElementAndSendKeys(item0, "test QA");
		waitForElementAndClick(btn);
		waitTime();

		waitForElementAndClick(exitButton);
		waitTime();
		waitForElementAndClick(finishTestButton);
		waitTime();
		waitForElementAndClick(menu);
		waitForElementAndClick(home);
		
		System.out.println("Test done as student");

	}

}
