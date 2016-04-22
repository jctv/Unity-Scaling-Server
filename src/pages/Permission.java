package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import generic.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Permission extends BasePage{
	
	public Permission(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[text()='Organization']")
	public WebElement organizationTab;
	
	@FindBy(xpath = "//a[text()='Object']")
	public WebElement objectTab;
	
	@FindBy(id = "acls_title")
	public WebElement AclTitle;
	
	@FindBy(xpath = "//button[@class='btn btn-primary add-acl']")
	public WebElement addAclButton;
	
	@FindBy(id = "acl-name")
	public WebElement aclNameInputField;
	
	@FindBy(id = "acl-trustee")
	public WebElement selectAclTrustee;
	
	@FindBy(id = "objectName")
	public WebElement objectName;
	
	@FindBy(id = "objectType")
	public WebElement objectType;
	
	@FindBy(xpath = "//button[@class='btn btn-primary acl-save']")
	public WebElement aclSaveButton;
	
	/**
	 * This is the method for selecting the methods
	 * @param orgName
	 */
	public void selectOrganization(String orgName) {
		try {
			waitForElementAndClick(organizationTab);
			customeWaitTime(5);
			WebElement orgTobeSelected = driver.findElement(By
					.xpath(".//*[@id='treeNavigation']//span[text()='"
							+ orgName + "']"));
			waitForElementAndClick(orgTobeSelected);
			customeWaitTime(5);
			if(waitAndGetElementText(AclTitle).contains(orgName)){
				System.out.println(orgName + " is selected successfully.");

			}
		} catch (Exception e) {
			System.out.println("Unable to select " + orgName);
			customeWaitTime(5);
		}
	}
	

/**
 * This is method for selecting object
 * @param object
 * @param bankName
 */
	public void selectObject(String object, String bankName) {
		try {
			waitForElementAndClick(objectTab);
			customeWaitTime(5);
			selectOption(objectType, object);
			customeWaitTime(5);
			waitForElementAndSendKeys(objectName, bankName);
			customeWaitTime(5);
			WebElement bank = driver.findElement(By.xpath("//td[text()='"
					+ bankName + "']"));
			waitForElementAndClick(bank);
			customeWaitTime(5);
			if (waitAndGetElementText(AclTitle).contains(bankName)) {
				System.out.println(bankName + " is selected successfully.");

			}
		} catch (Exception e) {
			System.out.println("Unable to select " + bankName);

		}
	}

	/**
	 * This is the method for adding acl permission
	 * @param permissions
	 * @param orgName
	 */
	public void addAcl(String permissions, String orgName) {
		waitForElementAndClick(addAclButton);
		customeWaitTime(5);
		selectOption(selectAclTrustee, orgName);

		List<String> permissionsToAdd = new ArrayList<String>(
				Arrays.asList(permissions.split(",")));
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
				// Read is added by default
				break;
			}
		}
		
		waitForElementAndClick(aclSaveButton);
		customeWaitTime(5);
	}
	
	public void editACL(String aclName ,String permissions){
		WebElement orgTobeSelected = driver.findElement(By
				.xpath("//div[text()='ACL for "+ aclName +"']/../div[2]/button[@class='edit-acl btn btn-default btn-xs pull-right']"));
		waitForElementAndClick(orgTobeSelected);
		customeWaitTime(5);
		selectOption(selectAclTrustee, aclName);

		List<String> permissionsToAdd = new ArrayList<String>(
				Arrays.asList(permissions.split(",")));
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
				// Read is added by default
				break;
			}
		}
		
		waitForElementAndClick(aclSaveButton);
		customeWaitTime(5);

	}
	
	/**
	 * This is the delete acl
	 * @param aclName
	 */
	public void deleteAcl(String aclName){
		WebElement orgTobeSelected = driver.findElement(By
				.xpath("//div[text()='ACL for "+ aclName +"']/../div[2]/button[@class='delete-acl btn btn-warning btn-xs pull-right']"));
		waitForElementAndClick(orgTobeSelected);
		customeWaitTime(5);
		//waitForElementAndClick(globalModalInfoOkButton);
		//customeWaitTime(2);

	}

}