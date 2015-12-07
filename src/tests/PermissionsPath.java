package tests;

import java.io.File;
import java.util.Properties;

import generic.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.ItemsBank;
import pages.Login;
import pages.Permission;
import pages.TestsBank;

public class PermissionsPath extends BaseTest {
	
	Login loginPage;
	DashBoard dashBoardPage;
	ItemsBank itemsBankPage;
	TestsBank testBankPage;
	Permission permissionPage;
	//PermissionsPath Nav;
	public String user = "ctribin";
	//String url = "http://uat.8pnds.com/#user,http://uat.8pnds.com/#adaptive_config,http://uat.8pnds.com/#blog,http://uat.8pnds.com/#roster,http://uat.8pnds.com/#delivery,http://uat.8pnds.com/#handscoring,http://uat.8pnds.com/#item_bank,http://uat.8pnds.com/#machine_profile,http://uat.8pnds.com/#media,http://uat.8pnds.com/#message,http://uat.8pnds.com/#organization,http://uat.8pnds.com/#passage,http://uat.8pnds.com/#report,http://uat.8pnds.com/#rubric,http://uat.8pnds.com/#calendar,http://uat.8pnds.com/#score_profile,http://uat.8pnds.com/#score_profile,http://uat.8pnds.com/#standard,http://uat.8pnds.com/#test_administration,http://uat.8pnds.com/#test_bank,http://uat.8pnds.com/#test,http://uat.8pnds.com/#testing_environment";
	//

	public String genericPassword = "12345";
	
	String defaultAutoUser = "at/admin";
	String defaultAutoUserPassword = "password";
	String itemBankName ;
	String testBankName ;

	public PermissionsPath() {
		super();

	}
	

	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	Properties unitymessages;
	

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
		
	}

	@BeforeMethod
	public void setUp() {
		System.out.println("Load Unity url - " + url);
		driver.get(url);
		loginPage = new Login(driver);
		System.out.println("******** logging as System Admin -- " + defaultAutoUser
				+ "******** ");
		dashBoardPage = loginPage.loginSuccess(defaultAutoUser, defaultAutoUserPassword);
		customeWaitTime(5);

	}

	//@Test
	public void runPath() {
		String[] array = url.split(",");
		System.out.println("User permissions for : " + user);
		for (String dir : array) {
			// driver.get(dir);
			// customeWaitTime(5);
			driver.navigate().to(dir);
			customeWaitTime(5);

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
	
	@Test
	public void testAclAlertMessages(){
		long timestamp = System.currentTimeMillis();
		itemBankName = "acl_IB_" + timestamp;
		itemsBankPage = dashBoardPage.goToItemsBank();
		customeWaitTime(10);
		itemsBankPage.createBank(itemBankName, "desc");
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		/*
		testBankPage = dashBoardPage.goToTestsBank();
		customeWaitTime(10);
		testBankName = "acl_TB_" + timestamp;
		testBankPage.createBank(testBankName, "desc");
		customeWaitTime(5);
		returnToDashboard();
		customeWaitTime(5);
		*/
		permissionPage = dashBoardPage.goToPermission();
		customeWaitTime(10);
		//permissionPage.selectOrganization("Auto State");
		permissionPage.selectObject("item_bank", itemBankName);
		permissionPage.addAcl("WRITE", "school: Auto School");
		Assert.assertEquals(permissionPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("aclSave").trim());
		permissionPage.waitForElementAndClick(permissionPage.globalModalInfoOkButton);
		customeWaitTime(5);
		permissionPage.deleteAcl("school: Auto School");
		Assert.assertEquals(permissionPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("aclRemoved").trim());
		permissionPage.waitForElementAndClick(permissionPage.globalModalInfoOkButton);
		
	}
}
