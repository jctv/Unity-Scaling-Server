package pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class Help extends BasePage {

	public Help(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = "//a[text()='Add Help']")
	public WebElement addHelpLink;
	
	@FindBy(xpath = "//a[text()='Import Help ']")
	public WebElement importHelpLink;
	
	@FindBy(xpath = "//span[text()='Export Help']")
	public WebElement exportHelpLink;
	
	
	@FindBy(xpath = "//span[text()='View Help']")
	public WebElement viewHelpLink;
	
	@FindBy(id = "tileName")
	public WebElement selectTile;
	
	@FindBy(id = "helpCreateInputSubmit")
	public WebElement helpSaveButton;
	
	@FindBy(xpath = ".//*[@id='addNewMedia']/button")
	public WebElement addTutorialButton;
	
	@FindBy(xpath = ".//*[@id='addNewGuide']/button")
	public WebElement addUserGuideButton;
	
	@FindBy(xpath = ".//*[@id='contentForm']/div[1]/div/input")
	public WebElement firstHintInputField;
	
	@FindBy(xpath = "//button[@class='btn btn-success btn-add']")
	public WebElement addHintButton;
	
	@FindBy(xpath = "//button[@class='btn btn-primary btn-add-child']")
	public WebElement addChildHintButton;
	
	@FindBy(xpath = ".//*[@id='createText']']")
	public WebElement createPermissionInputField;
	//Comment
	@FindBy(xpath = ".//*[@id='createText']']")
	public WebElement editPermissionInputField;
	
	
	public void addHelp(String tile , String helpHint , String mediafilePath, String userguideFilePath ){
		try{
        waitForElementAndClick(addHelpLink);
        selectOption(selectTile , tile);
        waitForElementAndClick(addTutorialButton);
        File f = new File(mediafilePath);
		String mediafile = f.getAbsolutePath();
		fileupload.sendKeys(mediafile);
        waitForElementAndClick(globalModalUploadOkButton);
        waitForElementAndClick(addUserGuideButton);
        File f1 = new File(userguideFilePath);
		String userGuidefile = f1.getAbsolutePath();
		fileupload.sendKeys(userGuidefile);
        waitForElementAndClick(globalModalUploadOkButton);
        waitForElementAndSendKeys(firstHintInputField ,helpHint);
        waitForElementAndClick(helpSaveButton);
		}catch(Exception e){
			System.out.println("Unable to add help for tile " + tile);
			
		}
	}

}
