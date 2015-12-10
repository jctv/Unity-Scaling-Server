package pages;

import generic.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Standards extends BasePage {

	public Standards(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}


	// Tabs Ids
	@FindBy(xpath = "//span[text()='Create Role']")
	public WebElement createRoleLink;

	@FindBy(xpath = "//td[text()='System Administrator']/..//td[@class='watable-col-add_tile']/button")
	public WebElement systemAdminAddTileButton;


	public void installStandards() {
		try {
			
			String	baseUrl = driver.getCurrentUrl();
			baseUrl = baseUrl.substring(0, baseUrl.indexOf("#"));
			driver.get(baseUrl + "ws/api/import/standard/opened?standard_group_id=2");
			customeWaitTime(5);
			driver.navigate().to(baseUrl + "ws/api/import/standard/opened?standard_group_id=4");
			customeWaitTime(5);
			driver.navigate().to(baseUrl + "#Dashboard");
			System.out.println("Standards installed correctly ");
		} catch (Exception e) {
			System.out.println("unable to install the standards on the new domain ");
		}

		
	}

}
