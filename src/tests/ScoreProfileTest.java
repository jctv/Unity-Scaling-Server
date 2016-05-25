package tests;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.Domain;
import pages.Login;
import pages.ScoreProfile;
import generic.BaseTest;

public class ScoreProfileTest extends BaseTest {
	
	Login loginPage;
	DashBoard dashBoardPage;
	ScoreProfile scoreProfilePage;
	Domain domainPage;
	
	String defaultAdmin;
	String defaultPassword;
	String resourcesLocation = "src" + File.separator + "resources"
			+ File.separator;
	private static final String MAP_ENGINE = "Map";
	private static final String HAND_ENGINE = "Hand";
	private static final String MATCH_ENGINE = "Match";
	private static final String CRASE_ENGINE = "CRASE";
	private static final String MAP_PROFILE = "Map";
	private static final String HAND_PROFILE = "Hand";
	private static final String MATCH_PROFILE = "Match";
	private static final String CRASE_PROFILE = "Automated Scoring";
	
	String desc = "Score profile desc";

	
	
	String unityTestDataFile = resourcesLocation  + "unitytestdata.properties";

	Properties unitytestdata;

	
	public ScoreProfileTest() {
		super();
		
	}

	@BeforeTest
	public void loadUnityMessagesProperty(){
		unitytestdata = getUnityMessagesProperty(unityTestDataFile);
		defaultAdmin = unitytestdata.getProperty("defaultAdmin");
		defaultPassword = unitytestdata.getProperty("defaultPasswordq");
	}

	
	@BeforeMethod
	public void setUp() {
		try{
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(defaultAdmin,
				defaultPassword);
		waitTime();
		//dashBoardPage.addTiles();
		waitTime();
		
		}catch(Exception e){
			
			System.out.println("Failed - Score propfile set up");
		}
	}
	
	
	@Test
	public void testCreateCRASEScoreProfile(){
		dashBoardPage.addTiles();
		customeWaitTime(6);
		scoreProfilePage = dashBoardPage.goToScoreProfile();
		customeWaitTime(6);
		scoreProfilePage.createScoreProfile(CRASE_PROFILE, desc, CRASE_ENGINE);
		Assert.assertEquals(
				scoreProfilePage.getScoreProfileEngine(CRASE_ENGINE),
				CRASE_ENGINE);
		Assert.assertEquals(scoreProfilePage.getScoreProfileName(CRASE_PROFILE),
				CRASE_PROFILE);
	}
	
	
	/**
	   * Login into the Unity as a super admin
	   * Go to domain page
	   * Create new domain
	   * Login as newly  created domain system admin.
	   * go to the Score Profile page
	   * Validate  default profile like hand , Match and Map
	   * log out
	   * 
	   * login as super admin
	   * Go to domain page 
	   * Delete domain
	   */
	  
	 //Temparary disbled this test as it is creating domain
	@Test(enabled= false)
	public void testDefaultScoreProfileForNewDomain() {
		String domainAbbreviation = "auto"+ System.currentTimeMillis();
		String domainName = "Test Auto don't Delete manually "
				+ domainAbbreviation;
		domainPage = dashBoardPage.goToDomain();
		customeWaitTime(12);
		domainPage.createDomain(domainAbbreviation, domainName);
		customeWaitTime(12);
		domainPage.refreshPage();
		customeWaitTime(12);
		domainPage.isDomainExist(domainAbbreviation);
		domainPage.waitForElementAndClick(domainPage.backToDashboard);
		customeWaitTime(6);
		dashBoardPage.logOut();
		dashBoardPage = loginPage.loginSuccess(domainAbbreviation + "/"
				+ defaultAdmin, "password");
		waitTime();
		dashBoardPage.addTiles();
		customeWaitTime(6);
		scoreProfilePage = dashBoardPage.goToScoreProfile();
		customeWaitTime(6);
		Assert.assertEquals(scoreProfilePage.resultListCount.getText().trim(),
				"3");
		Assert.assertEquals(scoreProfilePage.getScoreProfileEngine(MAP_ENGINE),
				MAP_ENGINE);
		Assert.assertEquals(
				scoreProfilePage.getScoreProfileEngine(HAND_ENGINE),
				HAND_ENGINE);
		Assert.assertEquals(
				scoreProfilePage.getScoreProfileEngine(MATCH_ENGINE),
				MATCH_ENGINE);
		Assert.assertEquals(scoreProfilePage.getScoreProfileName(MAP_PROFILE),
				MAP_PROFILE);
		Assert.assertEquals(scoreProfilePage.getScoreProfileName(HAND_PROFILE),
				HAND_PROFILE);
		Assert.assertEquals(
				scoreProfilePage.getScoreProfileName(MATCH_PROFILE),
				MATCH_PROFILE);
		scoreProfilePage.backToDashboard();
		customeWaitTime(6);
		dashBoardPage.logOut();
		dashBoardPage = loginPage.loginSuccess(defaultAdmin, defaultPassword);
		waitTime();
		domainPage = dashBoardPage.goToDomain();
		customeWaitTime(12);
		domainPage.deleteDomain(domainAbbreviation);
	}
	

}
