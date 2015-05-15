package tests;

import generic.BaseTest;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;


import pages.HandScoring;

public class HandScoringTests extends BaseTest {
	HandScoring Nav;

	public HandScoringTests() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver.get(url);
		
		Nav = PageFactory.initElements(driver, HandScoring.class);
	}
	
	
}
