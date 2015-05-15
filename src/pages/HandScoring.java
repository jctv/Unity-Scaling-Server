package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HandScoring extends BasePage {

	BasePage base;
	BaseTest test;

	public HandScoring(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "(//button[contains(@class,'editRow') and ancestor::tr/td[text()='Auto test Roster #1']])[last()]")
	public WebElement scoreTestIcon;

	@FindBy(xpath = "//*[@id='scores']/div/form/div[1]/div/select")
	public WebElement score;

	@FindBy(xpath = "//*[@id='scores']/div/form/div[1]/div/select/option[3]")
	public WebElement scoreOption;

	@FindBy(xpath = "//*[@id='scores']/div/form/div[2]/div/button")
	public WebElement saveScoreButton;

	@FindBy(id = "globalModalInfoOkButton")
	public WebElement globalModalInfoOkButton;

	@FindBy(id = "globalModalInfoOkButton")
	public WebElement okButton;

	@FindBy(xpath = "//*[@id='itemNav']/div/div[3]/button")
	public WebElement nextAnswer;

	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backLink;

	public String scoreTest() {
		waitForElementAndClick(scoreTestIcon);
		waitTime();
		for (int x = 0; x < 5; x++) {
			waitTime();
			waitForElementAndClick(score);
			waitForElementAndClick(scoreOption);
			waitForElementAndClick(saveScoreButton);
			waitTime();
			try {
				waitForElementAndClick(globalModalInfoOkButton);
				waitForElementAndClick(nextAnswer);
				waitForElementAndClick(nextAnswer);
				System.out.println("test scored");
			} catch (Exception e) {
				System.out.println(x);
			}

		}
		waitForElementAndClick(backLink);
		waitForElementAndClick(backLink);
		return "test scored";

	}
}
