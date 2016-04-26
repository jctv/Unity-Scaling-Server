package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class ScoreProfile extends BasePage{
	
	@FindBy(xpath = "//span[text()='Create']")
	public WebElement creatScoreProfileButton;
	
	
	@FindBy(name = "name")
	public WebElement scoreProfileNameField;
	
	@FindBy(name = "description")
	public WebElement scoreProfileDescField;
	
	@FindBy(name = "engine")
	public WebElement selectScoreEngine;
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backToDashboard;

	

	public ScoreProfile(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	/**
	 * This method to get the score profile Name
	 * @param ProfileName
	 * @return name 
	 */
	public String  getScoreProfileName(String ProfileName){
		waitTime();
		WebElement scoreProfileNameRow = driver
                .findElement(By
                        .xpath("//td[@class='watable-col-name' and text() ='"+ ProfileName +"']"));
		
		return scoreProfileNameRow.getText();
	}
	
	
/**
 * This is the method to get the score engine
 * @param profileEngine
 * @return engine
 */
public String getScoreProfileEngine(String profileEngine){
	waitTime();
	WebElement scoreProfileEngineRow = driver
            .findElement(By
                    .xpath("//td[@class='watable-col-engine'and text() ='"+ profileEngine +"']"));
	
	return scoreProfileEngineRow.getText();
		
	}

}
