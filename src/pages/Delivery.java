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
	
	@FindBy(xpath = ".//*[@id='itemDisplay']/div/div/div/div/div[2]/span[2]")
	public WebElement btn;
	
	@FindBy(xpath = "//span[@class='btn-next']/i")
	public WebElement nextbtn;
	
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
	
	@FindBy(xpath = ".//*[@id='playground']/div/div[2]/div[2]/div/textarea")
	public WebElement extendedTextAreaField;
	
	@FindBy(xpath = ".//*[@id='slideshowStatus']")
	public WebElement slideshowStatus;
	
	@FindBy(css = "#history button")
	public List<WebElement> viewScoredTestReport;
	
	@FindBy(className ="test-name")
	public List<WebElement> testName;
	
	@FindBy(id = "testHistory")
	public WebElement testHistoryPanel;
	
	@FindBy(xpath = "//i[@class='button_opt fa fa-list-ul fa-2x answerMask']")
	public WebElement answerMaskingIcon;
	
	@FindBy(xpath = "//i[@class='button_opt fa fa-minus-square-o fa-2x lineReader']")
	public WebElement lineReaderIcon;
	
	@FindBy(xpath = "//i[@class='fa fa-chevron-right']")
	public WebElement rightArrow;
	
	@FindBy(xpath = "//div[@class='access-answer-mask']")
	public WebElement accessAnswerMask;
	
	@FindBy(xpath = "//div[@class='i-choice']/div")
	public List<WebElement> itemsAnswerChoiceCount;
	
	@FindBy(xpath = "//i[@class='fa fa-step-backward fa-2x pm-arrows firstItem']")
	public WebElement firstItem;
	
	@FindBy(xpath = "//div[@class='access-line-reader-container']")
	public WebElement lineReaderBlock;
	
	

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
				String getItemsDisplay= waitAndGetElementText(slideshowStatus);
				String getMaxItemCount = getItemsDisplay.split("  ")[2];
				int totalItems = Integer.parseInt(getMaxItemCount);
				
				for (int item = 1; item <= totalItems ; item++) {
					WebElement itemToBeAnswered = driver.findElement(By
							.xpath("//div[@class='i-choice']//div[" + itemIndex
									+ "]//input"));
					if (isCorrectAnswer) {
						waitForElementAndClick(itemToBeAnswered);
						customeWaitTime(5);
						waitForElementAndClick(btn);
						customeWaitTime(5);

					} else {
						waitForElementAndClick(itemToBeAnswered);
						customeWaitTime(5);
						waitForElementAndClick(btn);
						customeWaitTime(5);
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

		case "Extended Text":
			try{
			String getItemsDisplay= waitAndGetElementText(slideshowStatus);
			String getMaxItemCount = getItemsDisplay.split("  ")[2];
			int totalItems = Integer.parseInt(getMaxItemCount);
			for (int item = 1; item <= totalItems ; item++) {
				waitForElementAndSendKeys(extendedTextAreaField, answer);
				customeWaitTime(1);
				waitForElementAndClick(btn);
				customeWaitTime(1);
			}
			 }catch(Exception e ){
				
			}
			break;
		}

		waitForElementAndClick(exitButton);
		customeWaitTime(5);
		waitForElementAndClick(finishTestButton);
		customeWaitTime(5);

		System.out.println("Test done as student");

	}

	/**
	 * method to take a tests(multiple Choice and TextEntry) and verify the score on the History section
	 */
	public boolean takeAndVefiryTestResults(String expectedScore, String answers){
	try{	
	List<String> answersList = Arrays.asList(answers.split(","));
	try {
		waitForElementAndClick(startTestButton);
		customeWaitTime(5);
	} catch (Exception e) {
		waitForElementAndClick(resumeTestButton);
	}
	customeWaitTime(3);
		for (String answer : answersList) {
			if(NumberUtils.isNumber(answer)){
				customeWaitTime(2);
				waitForElementAndClick(driver.findElement(By.xpath("(//input[@type='radio' and @name = 'sprite_1'])["+answer+"]")));
				customeWaitTime(10);
			}else{
				waitForElementAndSendKeys(driver.findElement(By.xpath("//input[@data-interaction='textEntry']")), answer);
				customeWaitTime(2);
			}
			waitForElementAndClick(btn);
			customeWaitTime(10);
		}

        customeWaitTime(3);
		if(btn.isEnabled()){
			waitForElementAndClick(btn);
		}
		waitForElementAndClick(exitButton);
		customeWaitTime(5);
		waitForElementAndClick(finishTestButton);
		customeWaitTime(5);
		waitForElementVisible(lastSocredTest);
		System.out.println("/////********///// "+lastSocredTest.getText().equals(expectedScore));
		
     }catch(Exception e){
    	 takeScreenShot();
  		System.out.println("Error while taking test");

	  }
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
		try{
		WebElement scheduleTest = driver.findElement(By
				.xpath("//button[@data-id='" + testId + "']"));
		waitForElementAndClick(scheduleTest);
		
		}catch(Exception e){
			System.out.println("Unable to stard the test ");

		}
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
					.xpath("//td[@class='test-name' and @data-test='" + testId
							+ "']/following-sibling::td[2]"));
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
				.xpath("//td[@class='test-name' and @data-test='" + testId
						+ "']/following-sibling::td[3]"));
		testNoOfItems = testItems.getText();
		} catch (Exception e) {
			System.out.println("error trying to get the test NoOfItems");
		}

		return testNoOfItems;
	}
	
	public boolean  getTestCompletedStatus(String testId) {
		boolean istestCompleted = false;
		try {
		WebElement testcompleted = driver.findElement(By
				.xpath("//td[@class='test-name' and @data-test='" + testId
						+ "']/following-sibling::td[4]//i[@class='fa fa-check-circle']"));
		
		/*WebElement testNotcompleted = driver.findElement(By
				.xpath("//td[@class='test-name' and @data-test='" + testId
						+ "']/following-sibling::td[4]//i[@class='fa fa-circle-o']"));
		*/
		
		if(testcompleted.isDisplayed()){
			istestCompleted = true;
			System.out.println("Test completed");

		}else {
			istestCompleted = false;
			System.out.println("Test not completed");
			
		 }
		} catch (Exception e) {
			System.out.println("Unable to get the test complete status");
		}

		return istestCompleted;
	}
	
	
	public void exitAndFinishTest(){
		try{
			waitForElementAndClick(exitButton);
			waitForElementAndClick(globalModalOKCancelSaveButton);
			waitForElementAndClick(finishTestButton);
		}catch(Exception e){
			
			
		}
	}
	
	
	public Reports goToTestDetailReport(int index){

		if(index>0){
			keyDownOnElement(testHistoryPanel);
			waitForElementAndClick(viewScoredTestReport.get(index));
			customeWaitTime(2);
			waitForJsProcess();
		}
		
		return new Reports(driver);
	}
	
	//Overloaded method for go to student detail repot
	
	public Reports goToTestDetailReport(String testName){
		   try{
			   WebElement viewReport = driver.findElement(By
						.xpath("//td[text()= '" + testName + "']/..//button[text()='View Report']"));
			waitForElementAndClick(viewReport);
			waitForJsProcess();
		   }catch(Exception e ){
			   
		   }
		return new Reports(driver);
	}
	
	
	
	
	public int getScoredTestsCount(){
		return viewScoredTestReport.size();
	}
	
	public String getTestNameFromHistory(int index){
		return testName.get(index).getText();
	}
	
	
	public boolean verifyByDefaultAnswerUnMasked() {
		validator = false;
		try {
			for (int item = 1; item <= itemsInTest.size(); item++) {
				waitForElementAndClick(itemsInTest.get(item - 1));
				customeWaitTime(2);
				if (!waitForElementVisible(accessAnswerMask)) {
					validator = true;
				} else {
					validator = false;

				}
			}

		} catch (Exception e) {

		}
		return validator;

	}
	
	
	public boolean verifyAnswerMasking() {
		validator = false;
		try {
			for (int item = 1; item <= itemsInTest.size(); item++) {
				waitForElementAndClick(itemsInTest.get(item - 1));
				customeWaitTime(2);
				waitForElementAndClick(answerMaskingIcon);
				customeWaitTime(2);
				if (waitForElementVisible(accessAnswerMask)) {
					validator = true;
				} else {
					validator = false;

				}
			}

		} catch (Exception e) {

		}
		return validator;

	}
	
	
	public boolean verifyAnswerUnMasking(){
		validator = false;
		try {
			for (int item = 1; item <= itemsInTest.size(); item++) {
				waitForElementAndClick(itemsInTest.get(item - 1));
				customeWaitTime(2);
				waitForElementAndClick(answerMaskingIcon);
				customeWaitTime(2);
				waitForElementVisible(accessAnswerMask);
				customeWaitTime(2);
				waitForElementAndClick(answerMaskingIcon);
				customeWaitTime(2);
				if (!waitForElementVisible(accessAnswerMask)) {
					validator = true;
				} else {
					validator = false;
				}
			}

		} catch (Exception e) {

		}
		return validator;
	}
	
	
	public void verifyAnswerMaskToggling() {
		try {
			for (int item = 1; item <= itemsInTest.size(); item++) {
				waitForElementAndClick(itemsInTest.get(item - 1));
				customeWaitTime(2);
				waitForElementAndClick(answerMaskingIcon);
				customeWaitTime(2);
				if (waitForElementVisible(accessAnswerMask)) {
					for (int answer = 1; answer <= itemsAnswerChoiceCount
							.size(); answer++) {
						WebElement maskEyeButton = driver
								.findElement(By
										.xpath("//div[@class='i-choice']/div["
												+ answer
												+ "]//span[@class='btn btn-default mask-button']"));
						waitForElementAndClick(maskEyeButton);
						customeWaitTime(1);
						WebElement maskBlind = driver.findElement(By
								.xpath("//div[@class='i-choice']/div[" + answer
										+ "]//span[@class='mask-blind']"));
						if (waitAndGetElementAttribute(maskBlind, "style")
								.contains("hidden")) {
							System.out.println(answer
									+ "  unmasked successfully");

						} else {
							System.out
									.println("ERROR - while unmasking the answer  >> "
											+ answer);
						}

						waitForElementAndClick(maskEyeButton);
						customeWaitTime(1);
						if (waitAndGetElementAttribute(maskBlind, "style")
								.contains("visible")) {
							System.out.println(answer
									+ "  masked again successfully");

						} else {
							System.out
									.println("ERROR - while masking the answer  >> "
											+ answer);
						}

					}

				} else {
					System.out.println("ERROR - while Masking all the answers");

				}

			}

		} catch (Exception e) {

		}
	}
	
	
	public void  verifyLineReaderPopUpToggling() {
		try {
			for (int item = 1; item <= itemsInTest.size(); item++) {
				waitForElementAndClick(itemsInTest.get(item - 1));
				customeWaitTime(2);
				waitForElementAndClick(lineReaderIcon);
				customeWaitTime(2);
				if (waitForElementVisible(lineReaderBlock)) {
					System.out.println("Line Reader tool appear ");

				} else {
					System.out.println("Error - Line Reader tool not pop ups");

				}
				
				waitForElementAndClick(lineReaderIcon);
				customeWaitTime(2);
				if (!waitForElementVisible(lineReaderBlock)) {
					System.out.println("Line Reader tool - hide");

				} else {
					System.out.println("Error - while hiding line reader tool");

				}
				
			}

		} catch (Exception e) {

		}

	}

}
