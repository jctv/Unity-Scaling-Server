package pages;

import java.util.List;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.By;
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

	@FindBy(xpath = "(//input[@name='sprite_1'])[last()]")
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

		waitForElementAndClick(item0);
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

	public void takeTestReports(int z) {

		waitTime();
		try {

			waitForElementAndClick(startTestButton);
		} catch (Exception e) {

			waitForElementAndClick(resumeTestButton);
		}

		System.out.println("Sarting test");

		waitTime();
		for (int x = 0; x < 11; x++) {
			if(z == 1){
			waitForElementAndClick(item0);
			System.out.println(x +" correct answer ");
			}else{
				System.out.println(x +" wrong answer ");
			}
			waitForElementAndClick(btn);
			waitTime();
		}

		waitForElementAndClick(exitButton);
		waitTime();
		waitForElementAndClick(finishTestButton);
		waitTime();
		waitForElementAndClick(home);

		System.out.println("Test done as student");

	}
	/**
	 * It is temporary method after discussion with team will update /remove the existing method
	 * @param testId
	 */
	public void takeTest(String testId){
		
		startScheduledTest(testId);
		waitTime();
		waitForElementAndClick(item0);
		waitForElementAndClick(btn);
		waitTime();

		waitForElementAndClick(exitButton);
		waitTime();
		waitForElementAndClick(finishTestButton);
		waitTime();
		//waitForElementAndClick(menu);
		//waitForElementAndClick(home);
		System.out.println("Test done as student");
		
	}
	
	
	public String getScheduledTest(String testId){
		String scheduledTestName = null ;
		WebElement scheduleTest = driver
                .findElement(By
                        .xpath("//button[@data-id='" + testId + "']/../../../div[@class='col-sm-8']/div[1]"));
		scheduledTestName = scheduleTest.getText();
		return scheduledTestName;
	}
	
	public void startScheduledTest(String testId){
		WebElement scheduleTest = driver
                .findElement(By
                        .xpath("//button[@data-id='" + testId + "']"));
		scheduleTest.click();
		
	}
	
	
	public String getTestinHistoryTable(String testId){
		String historyTestName = null ;
		WebElement historytest = driver
                .findElement(By
                        .xpath("//td[@class='test-name' and @data-test='" + testId + "']"));
		 historyTestName = historytest.getText();
		 
		 return historyTestName;
	}
	
	
	public String getTestPercentCorrect(String testId){
		String testCorrectPercent = null ;
		WebElement testpercent = driver
                .findElement(By
                        .xpath("//td[@class='test-score' and @data-test='" + testId + "']"));
		testCorrectPercent = testpercent.getText();
		 
		 return testCorrectPercent;
	}
	
	public String getTestNoOfItems(String testId){
		String testNoOfItems = null ;
		WebElement testItems = driver
                .findElement(By
                        .xpath("//td[@class='test-score' and @data-test='" + testId + "']/following-sibling::td[1]"));
		testNoOfItems = testItems.getText();
		 
		 return testNoOfItems;
	}
	
}
