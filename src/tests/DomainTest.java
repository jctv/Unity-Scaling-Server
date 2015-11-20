package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Domain;
import pages.Login;
import generic.BaseTest;

public class DomainTest extends BaseTest{
	Login loginPage;
	DashBoard dashBoardPage;
	Domain domainPage;
	String defaultUser = "admin";
	String defaultPassword = "@simple1";
	
	String unityMessageFile = "src" + File.separator + "resources"
			+ File.separator + "unitymessages.properties";
	
	Properties unitymessages;
	
	public DomainTest() {
		super();
		
	}

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitymessages = getUnityMessagesProperty(unityMessageFile);
	}
	
	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(defaultUser,
				defaultPassword);
		waitTime();
		//dashBoardPageObject.addTiles();
		waitTime();
	}

  //Since domain creation is critical so that for time being disabling this test
  @Test(enabled = false)
  public void testDomainAlertMessages(){
	  String domainName = "Auto" + System.currentTimeMillis();
	  String domainAbbreviation = "auto" ;
	  domainPage = dashBoardPage.goToDomain();
	  waitTime();
	  waitTime();
	  domainPage.CreateDomain(domainAbbreviation, domainName);
	  waitTime();
	  waitTime();
	  Assert.assertEquals(domainPage.globalModalInfoBody.getText().trim(), unitymessages.getProperty("domainCreate").trim());
	  domainPage.waitForElementAndClick(domainPage.globalModalInfoOkButton);
	  waitTime();
	  waitTime();
	  domainPage.deleteDomain(domainAbbreviation);
	  waitTime();
	  waitTime();
	  Assert.assertEquals(domainPage.globalModalDeleteBody.getText().trim(), unitymessages.getProperty("domainDelete").trim());
	  domainPage.waitForElementAndClick(domainPage.globalModalDeleteCancelButton);
	  
  }	
}
