package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import generic.BasePage;
import generic.BaseTest;

import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sun.jna.platform.win32.NTSecApi.LSA_FOREST_TRUST_RECORD.UNION.ByReference;

public class Delivery extends BasePage {

	BasePage base;
	BaseTest test;

	public Delivery(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//input[@name='sprite_1'])[last()]")
	public WebElement itemD;

	@FindBy(xpath = "//input[@name='sprite_1']")
	public WebElement itemA;

	@FindBy(xpath = "//button[contains(@class,'btn btn-primary btn-sm btn-block start-test-link')]")
	public WebElement startTestButton;

	@FindBy(xpath = "//button[contains(@class,'resume')]")
	public WebElement resumeTestButton;
	
	@FindBy(xpath = "//span[text()='Save']")
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

	@FindBy(xpath = "//div[@id='selectionButtons']/div/div[contains(@id,'itemNav')]")
	public List<WebElement> itemsInTest;
	
	@FindBy(xpath = "(//td[@class='test-score'])[1]")
	public WebElement lastSocredTest;
	
	@FindBy(xpath = "//div[@class='ui-dialog-buttonset']/button[1]")
	public WebElement testExitConfirmationButton;
	

	public void takeTest() {

		customeWaitTime(5);
		try {

			waitForElementAndClick(startTestButton);
		} catch (Exception e) {

			waitForElementAndClick(resumeTestButton);
		}

		System.out.println("Sarting test");

		customeWaitTime(5);

		waitForElementAndClick(itemD);
		waitForElementAndClick(btn);
		customeWaitTime(5);

		waitForElementAndClick(exitButton);
		customeWaitTime(5);
		waitForElementAndClick(finishTestButton);
		customeWaitTime(5);
		waitForElementAndClick(menu);
		waitForElementAndClick(home);

		System.out.println("Test done as student");

	}

	public void takeTestReports(int z) {

		customeWaitTime(5);
		try {

			waitForElementAndClick(startTestButton);
		} catch (Exception e) {

			waitForElementAndClick(resumeTestButton);
		}

		System.out.println("Sarting test");

		customeWaitTime(5);
		for (int x = 0; x < 11; x++) {
			if (z == 1) {
				waitForElementAndClick(itemD);
				System.out.println(x + " correct answer ");
			} else {
				System.out.println(x + " wrong answer ");
			}
			waitForElementAndClick(btn);
			customeWaitTime(5);
		}

		waitForElementAndClick(exitButton);
		customeWaitTime(5);
		waitForElementAndClick(finishTestButton);
		customeWaitTime(5);
		waitForElementAndClick(home);

		System.out.println("Test done as student");

	}

	/**
	 * It is temporary method after discussion with team will update /remove the
	 * existing method
	 * 
	 * @param testId
	 */
	public void takeTest(boolean isCorrectAnswer, int itemIndex,
			String itemType, String answer) {
		customeWaitTime(10);
		switch (itemType) {
		case "Choice":
			try {
				for (int item = 1; item <= itemsInTest.size(); item++) {
					WebElement itemToBeAnswered = driver.findElement(By
							.xpath("//div[@class='i-choice']//div[" + itemIndex
									+ "]//input"));
					if (isCorrectAnswer) {
						waitForElementAndClick(itemToBeAnswered);
						customeWaitTime(2);
						waitForElementAndClick(btn);
						customeWaitTime(1);

					} else {
						waitForElementAndClick(itemToBeAnswered);
						customeWaitTime(2);
						waitForElementAndClick(btn);
						customeWaitTime(1);
					}
				}
			} catch (Exception e) {
				// TODO
			}

			break;
		case "Text Entry":
			try {
				for (int item = 1; item <= itemsInTest.size(); item++) {
					WebElement itemToBeAnswered = driver
							.findElement(By
									.xpath("//div[@id='playground']//div["
											+ itemIndex
											+ "]//input[@class='i-text-entry form-control form-control-unity']"));
					if (isCorrectAnswer) {
						waitForElementAndSendKeys(itemToBeAnswered, answer);
						customeWaitTime(2);
						waitForElementAndClick(btn);
						customeWaitTime(5);

					} else {
						waitForElementAndSendKeys(itemToBeAnswered, answer);
						customeWaitTime(1);
						waitForElementAndClick(btn);
						customeWaitTime(1);
					}
				}
			} catch (Exception e) {
				// TODO
			}

			break;

		case "Extended Text Entry ":
			// TODO
			break;

		}

		waitForElementAndClick(exitButton);
		customeWaitTime(2);
		waitForElementAndClick(finishTestButton);
		customeWaitTime(2);

		System.out.println("Test done as student");

	}

	/**
	 * method to take a tests(multiple Choice and TextEntry) and verify the score on the History section
	 */
	public boolean takeAndVefiryTestResults(String expectedScore, String answers){
	List<String> answersList = Arrays.asList(answers.split(","));
	try {
		waitForElementAndClick(startTestButton);
	} catch (Exception e) {
		waitForElementAndClick(resumeTestButton);
	}
	customeWaitTime(3);
		for (String answer : answersList) {
			customeWaitTime(5);
			if(NumberUtils.isNumber(answer)){
				waitForElementAndClick(driver.findElement(By.xpath("(//input[@type='radio' and @name = 'sprite_1'])["+answer+"]")));		
			}else{
				waitForElementAndSendKeys(driver.findElement(By.xpath("//input[@data-interaction='textEntry']")), answer);
			}
			waitForElementAndClick(btn);
		}
		waitForElementAndClick(exitButton);
		waitForElementAndClick(finishTestButton);
		waitForElementVisible(lastSocredTest);
		System.out.println("/////********///// "+lastSocredTest.getText().equals(expectedScore));
		return lastSocredTest.getText().equals(expectedScore);
	}

	public String getScheduledTest(String testId) {
		String scheduledTestName = "";
		WebElement scheduleTest = driver.findElement(By
				.xpath("//button[@data-id='" + testId
						+ "']/../../../div[@class='col-sm-8']/div[1]"));
		scheduledTestName = scheduleTest.getText();
		return scheduledTestName;
	}

	public void startScheduledTest(String testId) {
		WebElement scheduleTest = driver.findElement(By
				.xpath("//button[@data-id='" + testId + "']"));
		scheduleTest.click();

	}

	public String getTestinHistoryTable(String testId) {
		String historyTestName = "";
		try {
		WebElement historytest = driver.findElement(By
				.xpath("//td[@class='test-name' and @data-test='" + testId
						+ "']"));
		historyTestName = historytest.getText();
		} catch (Exception e) {
			System.out.println("error trying to get the getTestinHistoryTable");
		}

		return historyTestName;
	}

	public String getTestPercentCorrect(String testId) {
		String testCorrectPercent = "";
		try {
			WebElement testpercent = driver.findElement(By
					.xpath("//td[@class='test-score' and @data-test='" + testId
							+ "']"));
			testCorrectPercent = testpercent.getText();
		} catch (Exception e) {
			System.out.println("error trying to get the test percent");
		}
		

		return testCorrectPercent;
	}

	public String getTestNoOfItems(String testId) {
		String testNoOfItems = "";
		try {
		WebElement testItems = driver.findElement(By
				.xpath("//td[@class='test-score' and @data-test='" + testId
						+ "']/following-sibling::td[1]"));
		testNoOfItems = testItems.getText();
		} catch (Exception e) {
			System.out.println("error trying to get the test NoOfItems");
		}

		return testNoOfItems;
	}

}
