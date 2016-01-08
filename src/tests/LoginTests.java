package tests;

import java.io.File;
import java.util.Properties;

import generic.BaseTest;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.commons.lang3.StringUtils;

import pages.Login;

public class LoginTests extends BaseTest {

	Login loginPage;
	
	String unityTestDataFile = "src" + File.separator + "resources"
			+ File.separator + "unitytestdata.properties";
	Properties unitymessages;
	
	Properties unitytestdata;

	public LoginTests() {
		super();

	}

	
		
		@BeforeTest
		public void loadUnityMessagesProperty(){
			unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		}

		@BeforeMethod
		public void setUp() {
			driver.get(url);
			loginPage = new Login(driver);
		}
		
		@Test(priority = 1)
		public void testInValidUserAndPasswordLoginFail(){
			loginPage.waitForElementAndSendKeys(loginPage.userField, unitytestdata.getProperty("invalidAdminUser"));
			loginPage.waitForElementAndSendKeys(loginPage.passwordField, unitytestdata.getProperty("invalidAdminPassword"));
			loginPage.waitForElementAndClick(loginPage.signIn);
			customeWaitTime(2);
			Assert.assertEquals(loginPage.waitAndGetElementText(loginPage.invalidUserAndPasswordLabel), unitytestdata.getProperty("invalidLoginMessage"));
			Assert.assertTrue(driver.getCurrentUrl().contains("login-fail."));
		}
		
		@Test(priority = 2)
		public void testInValidUserLoginFail(){
			loginPage.waitForElementAndSendKeys(loginPage.userField, unitytestdata.getProperty("defaultAdmin"));
			loginPage.waitForElementAndSendKeys(loginPage.passwordField, unitytestdata.getProperty("invalidAdminPassword"));
			loginPage.waitForElementAndClick(loginPage.signIn);
			customeWaitTime(2);
			Assert.assertEquals(loginPage.waitAndGetElementText(loginPage.invalidUserAndPasswordLabel), unitytestdata.getProperty("invalidLoginMessage"));
			Assert.assertTrue(driver.getCurrentUrl().contains("login-fail."));
		}
		
		@Test(priority = 3)
		public void testInValidPasswordLoginFail(){
			loginPage.waitForElementAndSendKeys(loginPage.userField, unitytestdata.getProperty("invalidAdminUser"));
			loginPage.waitForElementAndSendKeys(loginPage.passwordField, unitytestdata.getProperty("defaultPassword"));
			loginPage.waitForElementAndClick(loginPage.signIn);
			customeWaitTime(2);
			Assert.assertEquals(loginPage.waitAndGetElementText(loginPage.invalidUserAndPasswordLabel), unitytestdata.getProperty("invalidLoginMessage"));
			Assert.assertTrue(driver.getCurrentUrl().contains("login-fail."));
		}
		
		
	   @Test(priority = 4)
	   public void testLogout(){
		   loginPage.loginSuccess(unitytestdata.getProperty("defaultAdmin"), unitytestdata.getProperty("defaultPassword"));
		   customeWaitTime(5);
		   loginPage.logOut();
		   Assert.assertTrue(driver.getCurrentUrl().contains("logout"));
	   }
		

	@Test(enabled = false)
	public void login() {
/*		int[] a = new int[8];
		a[0] = -1;
		a[1] = 3;
		a[2] = -4;
		a[3] = 5;
		a[4] = 1;
		a[5] = -6;
		a[6] = 2;
		a[7] = 1;

		int P = a.length - 1;

		while (P >= 0) {

			if (getLHS(P, a) == getRHS(P, a)) {
				System.out.println("es indice equilibrio " + P);
				break;
			} else {
				P--;
			}

		}
*/ 
		
	int N = 6;							// 1
for(int i = 1; i <= N*3; i++){			// n
 System.out.println(StringUtils.repeat(" ", N - i) + StringUtils.repeat("#", i));
}
	}

	private int getLHS(int pindex, int[] a) {
		int sum = 0;
		for (int i = 0; i < pindex; i++) {
			sum += a[i];
		}
		 
		return sum;
	}
	

	private int getRHS(int pindex, int[] a) {
		int sum = 0;
		for (int i = pindex + 1; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}

}