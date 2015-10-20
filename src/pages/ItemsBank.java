package pages;

import java.util.List;

import generic.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@SuppressWarnings("unused")
public class ItemsBank extends BasePage {

	public ItemsBank(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	// Tabs Ids      
	@FindBy(xpath = "//*[@id='region-navigation']/div/span[1]")
	public WebElement createItemBank;
	
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backLink;


	@FindBy(id = "bankCreateInputName")
	public WebElement bankCreateInputName;
	
	@FindBy(id = "bankCreateInputDescription")
	public WebElement bankCreateInputDescription;
	
	@FindBy(id = "bankCreateInputSubmit")
	public WebElement bankCreateInputSubmit;
	
	@FindBy(id = "searchAutoComplete")
	public WebElement searchAutoCompleteField;
	
	@FindBy(id = "searchButton")
	public WebElement searchButton;
	
	@FindBy(xpath = "//button[@title='Share']")
	public WebElement shareButton;

	@FindBy(xpath = "//button[@title='View']")
	public WebElement ViewIcon;
	
	
	@FindBy(xpath = "//td[@class='watable-col-name']")
	public WebElement itemBankNameField;
	
	@FindBy(id = "globalModalViewBody")
	public WebElement shareWindow;
	
	@FindBy(id = "acl-trustee")
	public WebElement aclTrustee;
	
	@FindBy(id = "acl-access-WRITE")
	public WebElement aclTrusteeWrite;
	
	@FindBy(id = "acl-access-READ")
	public WebElement aclTrusteeRead;
	
	
	@FindBy(id = "acl-access-CREATE")
	public WebElement aclTrusteeCREATE;
	
	@FindBy(id = "acl-access-DELETE")
	public WebElement aclTrusteeDELETE;
	
	@FindBy(id = "acl-access-ADMIN")
	public WebElement aclTrusteeAdmin;
	
	@FindBy(xpath = "//button[@class='btn btn-sm btn-primary acl-save']")
	public WebElement saveShareButton;
	
	
	@FindBy(xpath = "//button[@class='btn btn-sm btn-warning acl-close']")
	public WebElement closeShareButton;
	
	
	@FindBy(xpath = "//*[@id='acls_sortable']/li[1]//div[@class='col-sm-8']")
	public WebElement sharedAccess;
	
	@FindBy(xpath = "//span[@class='section-title']")
	public WebElement itemBankStatisticsTitle;
	
	@FindBy(xpath = "//td[@class='expandedRowContainer']")
	public WebElement itemBankStatisticsPanel;
	
	
	@FindBy(xpath = "//td[@class='item-count']")
	public WebElement itemCount;
	
	@FindBy(xpath = "//td[@class='media-count']")
	public WebElement mediaCount;
	
	@FindBy(xpath = "//td[@class='passage-count']")
	public WebElement passageCount;
	
	@FindBy(xpath = "//td[@class='rubric-count']")
	public WebElement rubricCount;
	
	
	
	
	public void createBank(String bank, String description) {
		try {
			waitTime();
			waitForElementAndClick(createItemBank);
			
			waitForElementAndSendKeys(bankCreateInputName, bank);
			waitForElementAndSendKeys(bankCreateInputDescription, description);
			waitForElementAndClick(bankCreateInputSubmit);
			System.out.println("Bank created");
			waitForElementAndClick(backLink);
			
		} catch (Exception e) {
			System.out.println("Unable to create the bank");
		}

	}
	
	public void searchItemBank(String itemBank){
		try{
		  waitTime();
		  waitForElementAndSendKeys(searchAutoCompleteField, itemBank);
		  waitForElementAndClick(searchButton);
		  waitTime();
		}catch(Exception e){
			
			System.out.println("Unable to find the Item bank "  + itemBank);

		}
		
	}
	
	public void openItemBankShareScreen(){
		 try{
			 waitForElementAndClick(shareButton);
			 waitTime();

		 }catch(Exception e){
				System.out.println("Unable to open share item bank Window");
		 }
		
	}
	
	
	public void closeItemBankShareScreen(){
		try{
			 waitForElementAndClick(closeShareButton);
			 waitTime();

		 }catch(Exception e){
				System.out.println("Unable to Close share item bank Window");
		 }
		
		
	}
	
       public String shareItemBankWithTeacher(String selectTeacher) {
    	   String selectedTeacher = null;
    	   WebDriverWait wait = new WebDriverWait(driver, 60);
    	   
   		try {
   			String lastname = selectTeacher.substring(1);
 		    String firsname = selectTeacher.substring(0, 1);
 		    String firstuser = firsname + " "+lastname; 
   		    waitForElementAndSendKeys(aclTrustee , "nteacher1");
 		    waitTime();
   			WebElement autoOptions = driver.findElement(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all'][2]"));
   			
   			wait.until(ExpectedConditions.visibilityOf(autoOptions));
   			List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));
   			for(WebElement option : optionsToSelect){
   		        if(option.getText().equals(firstuser)) {
   		        	System.out.println("Trying to select: "+firstuser);
   		        	wait.until(ExpectedConditions.elementToBeClickable(option));
   		        	option.click();
   		            break;
   		        }
   		    }
   			waitTime();
			waitForElementAndClick(saveShareButton);
			waitTime();
   			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='acls_sortable']/li[1]//div[@class='col-sm-4']")));
   			selectedTeacher = driver.findElement(By.xpath("//*[@id='acls_sortable']/li[1]//div[@class='col-sm-4']")).getText();
			
   		} catch (NoSuchElementException e) {
   			System.out.println(e.getStackTrace());
   		}
   		catch (Exception e) {
   			System.out.println(e.getStackTrace());
   		}
		return selectedTeacher;
   	}
	
       
       
       
}
