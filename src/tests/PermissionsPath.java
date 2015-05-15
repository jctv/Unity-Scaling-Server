package tests;

import generic.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.Login;

public class PermissionsPath extends BaseTest {

	PermissionsPath Nav;
	public String user = "ctribin";
	String url = "http://uat.8pnds.com/#user,http://uat.8pnds.com/#adaptive_config,http://uat.8pnds.com/#blog,http://uat.8pnds.com/#roster,http://uat.8pnds.com/#delivery,http://uat.8pnds.com/#handscoring,http://uat.8pnds.com/#item_bank,http://uat.8pnds.com/#machine_profile,http://uat.8pnds.com/#media,http://uat.8pnds.com/#message,http://uat.8pnds.com/#organization,http://uat.8pnds.com/#passage,http://uat.8pnds.com/#report,http://uat.8pnds.com/#rubric,http://uat.8pnds.com/#calendar,http://uat.8pnds.com/#score_profile,http://uat.8pnds.com/#score_profile,http://uat.8pnds.com/#standard,http://uat.8pnds.com/#test_administration,http://uat.8pnds.com/#test_bank,http://uat.8pnds.com/#test,http://uat.8pnds.com/#testing_environment";
	//

	public String genericPassword = "12345";

	public PermissionsPath() {
		super();

	}

	@BeforeMethod
	public void setUp() {
		driver.get(url);
		PageFactory.initElements(driver, Login.class).loginSuccess(user,
				genericPassword);

	}

	@Test
	public void runPath() {
		String[] array = url.split(",");
		System.out.println("User permissions for : "+user);
		for (String dir : array) {
			// driver.get(dir);
			// waitTime();
			driver.navigate().to(dir);
			waitTime();

			
			try {
				
				System.out.println("Url "
						+ dir
						+ " is able for the user   "
						+ driver.findElement(
								By.xpath("//*[@id='region-navigation']/div/a"))
								.isDisplayed());
				try {
					System.out
							.println("-------------- User is able to edit  "
									+ driver.findElement(
											By.xpath("//*[@id='region-workspace']/div/div/div[2]/div/div/div[3]/table/tbody/tr[1]/td[2]/div/button[2]"))
											.isDisplayed());

				} catch (Exception e) {
					System.out
					.println("---********--- User is not able to Edit      ");
				}
				try {
					System.out
							.println("-------------- User is able to delete     "
									+ driver.findElement(
											By.xpath("//*[@id='region-workspace']/div/div/div[2]/div/div/div[3]/table/tbody/tr[1]/td[2]/div/button[3]"))
											.isDisplayed());

				} catch (Exception e) {

					System.out
							.println("---********--- User is not able to delete      ");

				}

				driver.get(url);

			} catch (Exception e) {
				System.out.println("Url " + dir + " is not able for the user ");
			}

		}

	}
}
