package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestsBank extends BasePage {

	BasePage base;
	BaseTest test;

	public TestsBank(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Tabs Ids
	@FindBy(xpath = ".//*[text()='Create Test Bank']")
	public WebElement createBankLink;
	
	@FindBy(id = "bankCreateInputName")
	public WebElement bankCreateInputName;
	
	@FindBy(id = "bankCreateInputDescription")
	public WebElement bankCreateInputDescription;
	
	
	@FindBy(id = "bankCreateInputSubmit")
	public WebElement bankCreateInputSubmit;
	
	@FindBy(id = "searchAutoComplete")
	public WebElement testBankSearchAutoCompleteField;
	
	@FindBy(id = "searchButton")
	public WebElement testBankSearchButton;
	
	@FindBy(xpath = "//button[@title='Share']")
	public WebElement testBankShareButton;

	@FindBy(xpath = "//button[@title='View']")
	public WebElement testBankViewIcon;
	
	@FindBy(xpath = "//button[@class='btn btn-sm btn-warning acl-close']")
	public WebElement testBankCloseShareButton;
	
	@FindBy(id = "acl-trustee")
	public WebElement aclTrustee;
	
	@FindBy(id = "acl-access-WRITE")
	public WebElement aclTrusteeWrite;
	
	@FindBy(id = "acl-access-READ")
	public WebElement aclTrusteeRead;
	
	
	@FindBy(id = "acl-access-CREATE")
	public WebElement aclTrusteeCreate;
	
	@FindBy(id = "acl-access-DELETE")
	public WebElement aclTrusteeDelete;
	
	@FindBy(id = "acl-access-ADMIN")
	public WebElement aclTrusteeAdmin;
	
	@FindBy(xpath = "//button[@class='btn btn-sm btn-primary acl-save']")
	public WebElement saveShareButton;
	
	
	@FindBy(xpath = "//button[@class='btn btn-sm btn-warning acl-close']")
	public WebElement closeShareButton;
	
	@FindBy(xpath = "(//div[@class = 'col-sm-4'])[last()]")
	public WebElement lastAddedUser;
	
	@FindBy(xpath = "//button[@class='btn btn-xs btn-link deleteRow']")
	public WebElement deleteTestBank;
	
	@FindBy(id = "globalModalDelete")
	public WebElement deleteTestBankPopUp;
	
	@FindBy(id = "globalModalDeleteButton")
	public WebElement deletebuttonTestBankPopUp;
	
	@FindBy(xpath = "//*[@id='acls_sortable']/li[1]//div[@class='col-sm-8']")
	public WebElement sharedAccess;
	
	@FindBy(xpath = "//td[@class='watable-col-name']")
	public WebElement testBankNameField;
	
	@FindBy(xpath = "//input[@class='form-control input-sm']")
	public WebElement inputTestBankNameField;
	
	@FindBy(xpath = "//span[text()='Name']")
	public WebElement testBankNameFilter;
	
	
	

	/**
	 * This is the method for create bank
	 * @param bankName
	 * @param descBank
	 */
	public void createBank(String bankName, String descBank) {
		try {
			waitTime();
			waitForElementAndClick(createBankLink);
			waitForElementAndSendKeys(bankCreateInputName, bankName);
			waitForElementAndSendKeys(bankCreateInputDescription, descBank);
			waitForElementAndClick(bankCreateInputSubmit);
			System.out.println("Test Bank Created");
			waitForElementAndClick(globalModalInfoOkButton);
			customeWaitTime(2);
		} catch (Exception e) {
			System.out.println("Unable to create the bank");
		}
		
	
	}
	
	
	/**
	 * This is the method for search test bank
	 * @param testBank
	 */
	public void searchTestBank(String testBank){
		try{
		  waitTime();
		  testBankSearchAutoCompleteField.clear();
		  waitTime();
		  waitForElementAndSendKeys(testBankSearchAutoCompleteField, testBank);
		  waitForElementAndClick(testBankSearchButton);
		  waitTime();
		}catch(Exception e){
			System.out.println("Unable to find the test bank "  + testBank);

		}
		
	}
	
	
	/**
	 * This is the method for opening the sharing screen
	 */
	public void openTestBankShareScreen(){
		 try{
			 waitForElementAndClick(testBankShareButton);
			 waitTime();

		 }catch(Exception e){
				System.out.println("Unable to open share test bank Window");
		 }
		
	}
	
	
	
	/**
	 * This is the method for closing the sharing screen
	 */
	public void closeTestBankShareScreen(){
		try{
			 waitForElementAndClick(testBankCloseShareButton);
			 waitTime();

		 }catch(Exception e){
				System.out.println("Unable to Close share test bank Window");
		 }
		
		
	}
	
	
	/**
	 * This is the method for sharing the test bank
	 * @param user
	 * @param permissions
	 * @return
	 */
	public String shareTestBank(String user, String permissions) {
        String selectedTeacher = null;
 
        try {
            String lastname = user.substring(1);
            String firsname = user.substring(0, 1);
            String firstuser = firsname + " " + lastname;
            waitForElementAndSendKeys(aclTrustee, user);
            waitTime();
            WebElement autoOptions = driver
                    .findElement(By
                            .xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all'][2]"));
            waitForElementVisible(autoOptions);
            List<WebElement> optionsToSelect = autoOptions.findElements(By
                    .tagName("li"));
            for (WebElement option : optionsToSelect) {
                if (option.getText().equals(firstuser)) {
                    System.out.println("Trying to select teacher: " + firstuser);
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
            waitTime();
            waitForElementAndClick(saveShareButton);
            waitTime();         
 
 
 
        } catch (NoSuchElementException e) {
            System.out.println(e.getStackTrace());
 
        } catch (Exception e) {
            System.out.println("Error selecting User " + user);
        }
        selectedTeacher = lastAddedUser.getText();
        return selectedTeacher;
    }
 
	
	/**
	 * This method for deleting the test bank
	 * @param testBank
	 */
	public void deleteTestBank(String testBank){
		try{
		searchTestBank(testBank);	
		waitTime();
		waitForElementAndClick(deleteTestBank);
		waitTime();
		if(deleteTestBankPopUp.isDisplayed()){
		   waitForElementAndClick(deletebuttonTestBankPopUp);
		}
			
		}catch(Exception e){
			 System.out.println("Unable to delete the Test Bank  " + testBank);
		}
		
	}

	/**
	 * This method for filter the test bank
	 * @param testBankName
	 */
	public void filterTestBank(String testBankName){
		try{
			customeWaitTime(5);
			waitForElementAndClick(testBankNameFilter);
			customeWaitTime(5);
			waitForElementAndSendKeys(inputTestBankNameField, testBankName);
			customeWaitTime(5);
		}catch(Exception e){
			 System.out.println("Unable to filter the Test Bank with Name  " + testBankName);

		}
		
	}
}
