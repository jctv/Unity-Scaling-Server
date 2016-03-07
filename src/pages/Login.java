package pages;

import generic.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

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
	
	@FindBy(id = "region-manual-title")
	public WebElement pageName;
	
	@FindBy(xpath = ".//*[@id='passwordFormGrp']/label")
	public WebElement invalidUserAndPasswordLabel;
	
	
	public DashBoard loginSuccess(String user, String password) {
		try {
			waitForElementAndSendKeys(userField, user);
			waitForElementAndSendKeys(passwordField, password);
			customeWaitTime(5);
			waitForElementAndClick(signIn);
			customeWaitTime(10);
			waitForJsProcess();
			if(pageName.isDisplayed()){
			System.out.println(user + "  login successfully");
			}

		} catch (Exception e) {
			takeScreenShot();
			System.out.println("Unable to login");
		}

		return new DashBoard(driver);
	}

}
