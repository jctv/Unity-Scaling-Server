package pages;

import generic.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Login extends BasePage {

	public Login(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Tabs Ids

	@FindBy(xpath = "//*[@id='region-workspace']/div/div/form/input[1]")
	public WebElement userField;

	@FindBy(xpath = "//*[@id='passwordFormGrp']/input")
	public WebElement passwordField;

	@FindBy(id = "loginButton")
	public WebElement signIn;

	public DashBoard loginSuccess(String user, String password) {
		try {
			waitForElementAndSendKeys(userField, user);
			waitForElementAndSendKeys(passwordField, password);
			waitForElementAndClick(signIn);
			customeWaitTime(3);
			Assert.assertTrue(backPageButton.isDisplayed(), "User Logged");
		} catch (Exception e) {
			System.out.println("Unable to login");
		}

		return new DashBoard(driver);
	}

}
