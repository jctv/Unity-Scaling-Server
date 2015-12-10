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


	public DashBoard installStandards() {
		try {
			System.out.println(driver.getCurrentUrl());
			String	baseUrl = driver.getCurrentUrl();
			baseUrl = baseUrl.substring(0, baseUrl.indexOf("#"));
			driver.navigate().to(baseUrl + "ws/api/import/standard/opened?standard_group_id=2");
			customeWaitTime(5);
			driver.navigate().to(baseUrl + "ws/api/import/standard/opened?standard_group_id=2");
			driver.navigate().to(baseUrl + "#Dashboard");
							
		} catch (Exception e) {
			System.out.println("unable to instal the standards on the new domain ");
		}

		return new DashBoard(driver);
	}

}
