package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
	
	String defaultUser = "admin";
	String defaultPassword = "@simple1";
	
	private static final String MAP_ENGINE = "Map";
	private static final String HAND_ENGINE = "Hand";
	private static final String MATCH_ENGINE = "Match";
	private static final String MAP_PROFILE = "Map";
	private static final String HAND_PROFILE = "Hand";
	private static final String MATCH_PROFILE = "Match";
	
	  
	
	public ScoreProfileTest() {
		super();
		
	}

	
	@BeforeMethod
	public void setUp() {
		driver.get(url);
		loginPage = new Login(driver);
		dashBoardPage = loginPage.loginSuccess(defaultUser,
				defaultPassword);
		waitTime();
		//dashBoardPage.addTiles();
		waitTime();
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
	  
	@Test
	public void testDefaultScoreProfileForNewDomain() {
		String domainAbbreviation = "auto"+ System.currentTimeMillis();
		String domainName = "Test Auto don't Delete manually "
				+ domainAbbreviation;
		domainPage = dashBoardPage.goToDomain();
		waitTime();
		waitTime();
		waitTime();
		waitTime();
		domainPage.createDomain(domainAbbreviation, domainName);
		waitTime();
		waitTime();
		waitTime();
		waitTime();
		waitTime();
		waitTime();
		domainPage.refreshPage();
		waitTime();
		waitTime();
		waitTime();
		waitTime();
		domainPage.isDomainExist(domainAbbreviation);
		domainPage.waitForElementAndClick(domainPage.backToDashboard);
		waitTime();
		waitTime();
		dashBoardPage.logOut();
		dashBoardPage = loginPage.loginSuccess(domainAbbreviation + "/"
				+ defaultUser, "password");
		waitTime();
		dashBoardPage.addTiles();
		waitTime();
		waitTime();
		scoreProfilePage = dashBoardPage.goToScoreProfile();
		waitTime();
		waitTime();
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
		waitTime();
		waitTime();
		dashBoardPage.logOut();
		dashBoardPage = loginPage.loginSuccess(defaultUser, defaultPassword);
		waitTime();
		domainPage = dashBoardPage.goToDomain();
		waitTime();
		waitTime();
		waitTime();
		waitTime();
		domainPage.deleteDomain(domainAbbreviation);
	}

}
