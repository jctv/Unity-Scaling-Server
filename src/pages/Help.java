package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class Help extends BasePage {

	public Help(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = "//a[text()='Add Help']")
	public WebElement addHelpLink;
	
	@FindBy(xpath = "//a[text()='Import Help ']")
	public WebElement importHelpLink;
	
	@FindBy(xpath = "//span[text()='Export Help']")
	public WebElement exportHelpLink;
	
	
	@FindBy(xpath = "//span[text()='View Help']")
	public WebElement viewHelpLink;
	
	

}
