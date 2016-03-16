package pages;

import java.util.List;

import generic.BasePage;

import org.openqa.selenium.By;
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
	@FindBy(xpath = "//span[text()='Create Role']")
	public WebElement createRoleLink;

	@FindBy(xpath = "//td[text()='System Administrator']/..//td[@class='watable-col-add_tile']/button")
	public WebElement systemAdminAddTileButton;
	
	@FindBy(xpath = "//span[text()='OpenEd']")
	public WebElement openEdFolderNav;
	
	@FindBy(xpath = "//span[text()='Common Core Language Arts']")
	public WebElement commonCoreLanguageArtFolderNav;
	
	@FindBy(xpath = "//span[text()='Common Core Math']")
	public WebElement commonCoreMathFolderNav;
	
	@FindBy(xpath = "//h3[@id='itemTitle']")
	public WebElement stradCode;
	
	@FindBy(xpath = ".//*[@id='strand_name']")
	public WebElement strandName;
	
	@FindBy(xpath = ".//*[@id='description']")
	public WebElement strandDesc;
	
	@FindBy(xpath = "//button[@class='btn btn-primary view-resources']")
	public WebElement ViewInstructionalResourcesButton;
	

	@FindBy(xpath = "//div[@id='globalModalInstructionalResourceBody']")
	public WebElement globalModalInstructionalResourceBody;
	
	@FindBy(id = "irStandard")
	public WebElement irStandard;
	
	@FindBy(xpath = "//button[text()='Close']")
	public WebElement closeButton;
	
	@FindBy(xpath = "//span[text()='Common Core Language Arts']/../../ul/li[1]")
	public WebElement firstArtSchoolNav;
	
	@FindBy(xpath = "//span[text()='Common Core Language Arts']/../../ul/li[1]/ul/li")
	public WebElement firstArtDomainNav;
	
	@FindBy(xpath = "//span[text()='Common Core Language Arts']/../../ul/li[1]/ul/li/ul/li[1]")
	public WebElement firstArtStrandNav;
	
	@FindBy(xpath = "//div[contains(@id,'ember')]/div/div[3]/div/a/div[1]/img")
	public WebElement resourcePic;
	
	public void installStandards() {
		try {
			
			String	baseUrl = driver.getCurrentUrl();
			baseUrl = baseUrl.substring(0, baseUrl.indexOf("#"));
			driver.get(baseUrl + "ws/api/import/standard/opened?standard_group_id=2");
			customeWaitTime(5);
			driver.navigate().to(baseUrl + "ws/api/import/standard/opened?standard_group_id=4");
			customeWaitTime(5);
			driver.navigate().to(baseUrl + "#Dashboard");
			System.out.println("Standards installed correctly ");
		} catch (Exception e) {
			System.out.println("unable to install the standards on the new domain ");
		}

		
	}
	
	
	public void openViewInstructionalResources(){
		try{
			waitForElementAndClick(openEdFolderNav);
			customeWaitTime(2);
			waitForElementAndClick(commonCoreLanguageArtFolderNav);
			customeWaitTime(2);
			waitForElementAndClick(firstArtSchoolNav);
			customeWaitTime(2);
			waitForElementAndClick(firstArtDomainNav);
			customeWaitTime(2);
			waitForElementAndClick(firstArtStrandNav);
			customeWaitTime(2);
			waitForElementAndClick(ViewInstructionalResourcesButton);
			customeWaitTime(5);
		}catch(Exception e){
			System.out.println("unable to open the view InstructionalResources for strand");
		}
		
	}
	
	
	public void verifyAllResources() {
		try {
			WebElement resourceGroup = driver.findElement(By
					.xpath(".//*[@id='ir-result-container']/div/ul"));
			waitForElementVisible(resourceGroup);
			customeWaitTime(5);
			List<WebElement> resoureList = resourceGroup.findElements(By
					.tagName("li"));
			for (WebElement resource : resoureList) {
				customeWaitTime(2);
				String resourceName = resource.getText();
				waitForElementAndClick(resource);
				customeWaitTime(10);
				waitAndSwitchTOFrame("ir-resource-view");
				//driver.switchTo().frame("ir-resource-view");
				customeWaitTime(2);
				if (resourceName.equals(waitAndGetElementAttribute(
						resourceImage, "alt"))) {
					String resoureHref = waitAndGetElementAttribute(
							openResourceLink, "href");
					if (!resoureHref.isEmpty()) {
						System.out.println("Resoreces link - - --> "
								+ resoureHref);
					}
				}
				
				//driver.switchTo().defaultContent();
				waitAndSwitchToDefaultContent();
			}
		} catch (Exception e) {
			System.out
					.println("Error ### while getting the reosource inforamtion");

		}

	}
	
	 public Reports closeResourcePopUP(){
		  try{
			waitForElementAndClick(closeButton);
			customeWaitTime(10);
		  }catch(Exception e){
			  
		  }
	     return new Reports(driver);

	  }

}
