package pages;

import java.util.ArrayList;
import java.util.Arrays;
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
	@FindBy(xpath = "//a[text()='Create Item Bank']")
	public WebElement createItemBank;
	
	@FindBy(xpath = ".//*[@id='region-navigation']/ul/li[1]/a")
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
	public WebElement viewIcon;
	
	
	@FindBy(xpath = "//td[@class='watable-col-name']")
	public WebElement itemBankNameField;
	
	@FindBy(id = "globalModalViewBody")
	public WebElement shareWindow;
	
	
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
	
	@FindBy(xpath = "(//div[@class = 'col-sm-4'])[last()]")
	public WebElement lastAddedUser;
	
	@FindBy(xpath = "//button[@class='btn btn-xs btn-link deleteRow']")
	public WebElement deleteItemBank;
	
	@FindBy(id = "globalModalDelete")
	public WebElement deleteItemBankPopUp;
	
	@FindBy(id = "globalModalDeleteButton")
	public WebElement deletebuttonItemBankPopUp;
	
	@FindBy(xpath = "//input[@class='form-control input-sm']")
	public WebElement inputItemBankNameField;
	
	@FindBy(xpath = "//span[text()='Name']")
	public WebElement itemBankNameFilter;
	
	
	
	public void createBank(String bank, String description) {
		try {
			customeWaitTime(5);
			waitForElementAndClick(createItemBank);
			waitForElementAndSendKeys(bankCreateInputName, bank);
			waitForElementAndSendKeys(bankCreateInputDescription, description);
			waitForElementAndClick(bankCreateInputSubmit);
			System.out.println("Bank created");
			//waitForElementAndClick(backLink);
			customeWaitTime(2);
			waitForElementAndClick(globalModalInfoOkButton);
			customeWaitTime(2);
		} catch (Exception e) {
			System.out.println("Unable to create the bank");
		}

	}
	
	public void searchItemBank(String itemBank){
		try{
		  customeWaitTime(5);
		  searchAutoCompleteField.clear();
		  customeWaitTime(5);
		  waitForElementAndSendKeys(searchAutoCompleteField, itemBank);
		  waitForElementAndClick(searchButton);
		  customeWaitTime(5);
		}catch(Exception e){
			System.out.println("Unable to find the Item bank "  + itemBank);

		}
		
	}
	
	public void openItemBankShareScreen(){
		 try{
			 waitForElementAndClick(shareButton);
			 customeWaitTime(5);

		 }catch(Exception e){
				System.out.println("Unable to open share item bank Window");
		 }
		
	}
	
	
	public void closeItemBankShareScreen(){
		try{
			 waitForElementAndClick(closeShareButton);
			 customeWaitTime(5);

		 }catch(Exception e){
				System.out.println("Unable to Close share item bank Window");
		 }
		
		
	}
	
	//For time being  commenting this method since we have anathor method after testing that  methods i will remove this method
	
       /*public String shareItemBankWithTeacher(String selectTeacher) {
    	   String selectedTeacher = null;
    	   WebDriverWait wait = new WebDriverWait(driver, 60);
    	   
   		try {
   			String lastname = selectTeacher.substring(1);
 		    String firsname = selectTeacher.substring(0, 1);
 		    String firstuser = firsname + " "+lastname; 
   		    waitForElementAndSendKeys(aclTrustee , "nteacher1");
 		    customeWaitTime(5);
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
   			customeWaitTime(5);
			waitForElementAndClick(saveShareButton);
			customeWaitTime(5);
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
	*/
       
   
	public String shareItemBank(String user, String permissions) {
        String selectedTeacher = null;
 
 
        try {
            String lastname = user.substring(1);
            String firsname = user.substring(0, 1);
            String firstuser = firsname + " " + lastname;
            waitForElementAndSendKeys(aclTrustee, user);
            customeWaitTime(5);
            WebElement autoOptions = driver
                    .findElement(By
                            .xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all'][2]"));
            waitForElementVisible(autoOptions);
            customeWaitTime(5);
            List<WebElement> optionsToSelect = autoOptions.findElements(By
                    .tagName("li"));
            for (WebElement option : optionsToSelect) {
            	customeWaitTime(5);
                if (option.getText().equals(firstuser)) {
                    System.out.println("Trying to select: " + firstuser);
                    waitForElementAndClick(option);
 
                    break;
                }
            }
 
            List<String> permissionsToAdd = new ArrayList<String>(Arrays.asList(permissions.split(",")));
            for (String permission : permissionsToAdd) {
 
                switch (permission) {
                case "WRITE":
                    waitForElementAndClick(aclTrusteeWrite);
                    break;
                case "CREATE":
                    waitForElementAndClick(aclTrusteeCreate);
                    break;
                case "DELETE":
                    waitForElementAndClick(aclTrusteeDelete);
                    break;
                case "ADMIN":
                    waitForElementAndClick(aclTrusteeAdmin);
                    break;
                case "READ":
                    //Read is added by default 
                    break;
                }
            }
            customeWaitTime(5);
            waitForElementAndClick(saveShareButton);
            customeWaitTime(5);         
 
        } catch (NoSuchElementException e) {
            System.out.println(e.getStackTrace());
 
        } catch (Exception e) {
            System.out.println("Error selecting User " + user);
        }
        selectedTeacher = lastAddedUser.getText();
        return selectedTeacher;
    }
 
	
	public void deleteItemBank(String itemBank){
		try{
		searchItemBank(itemBank);	
		customeWaitTime(5);
		waitForElementAndClick(deleteItemBank);
		customeWaitTime(10);
		if(deleteItemBankPopUp.isDisplayed()){
		   waitForElementAndClick(deletebuttonItemBankPopUp);
		}
			
		}catch(Exception e){
			 System.out.println("Unable to delete the Item Bank  " + itemBank);
		}
		
	}
	
	
	public void filterItemBank(String itemName){
		try{
			customeWaitTime(5);
			waitForElementAndClick(itemBankNameFilter);
			customeWaitTime(5);
			waitForElementAndSendKeys(inputItemBankNameField, itemName);
			customeWaitTime(5);
		}catch(Exception e){
			 System.out.println("Unable to filter the Item Bank with Name  " + itemName);

		}
		
		
	}
}
