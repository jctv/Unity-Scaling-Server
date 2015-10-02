package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends BasePage {

	BasePage base;
	BaseTest test;

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
			System.out.println("User logged");
		} catch (Exception e) {
			System.out.println("Unable to login");
		}
		
		
		return new DashBoard(driver);
	}

}
