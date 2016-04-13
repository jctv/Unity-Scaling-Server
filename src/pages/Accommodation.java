package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class Accommodation extends BasePage {
	public Accommodation(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

}
