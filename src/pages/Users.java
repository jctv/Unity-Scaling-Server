package pages;

import generic.BasePage;
import generic.BaseTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Users extends BasePage {

	BasePage base;
	BaseTest test;

	public Users(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Tabs Ids

	@FindBy(id = "searchAutoComplete")
	public WebElement searchAutoComplete;

	@FindBy(id = "searchButton")
	public WebElement searchButton;

	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[2]/div/div/div[3]/table/tbody/tr[1]/td[7]/button/i")
	public WebElement deleteIcon;

	@FindBy(id = "globalModalDeleteButton")
	public WebElement globalModalDeleteButton;
					
	@FindBy(xpath = "//*[@id='region-navigation']/div/span[1]")
	public WebElement createUserLink;

	@FindBy(id = "userCreateInputFName")
	public WebElement firstNameField;

	@FindBy(id = "userCreateInputLName")
	public WebElement lastNameField;

	@FindBy(id = "userCreateInputPW1")
	public WebElement password;

	@FindBy(id = "userCreateInputPW2")
	public WebElement retypePassword;
	
	@FindBy(id = "userCreateInputRoles")
	public WebElement role;
		
	@FindBy(id = "userEditInputPW1")
	public WebElement userEditInputPW1;
	
	@FindBy(id = "userEditInputPW2")
	public WebElement userEditInputPW2;
	
			      
	@FindBy(id = "userCreateInputSubmit")
	public WebElement submit;

	@FindBy(id = "userCreateInputCancel")
	public WebElement userCreateInputCancel;
	
	@FindBy(id = "genericUserEditModelUpdateButton")
	public WebElement genericUserEditModelUpdateButton;
	
	@FindBy(id = "genericUserEditModelCancelButton")
	public WebElement genericUserEditModelCancelButton;
	

	@FindBy(id = "globalModalInfoOkButton")
	public WebElement modalOk;

	public boolean validador = false;

	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[2]/div/div/div[3]/table/tbody/tr[1]")
	public WebElement rowOneGrid;
	
	
	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[2]/div/div/div[3]/table/tbody/tr[1]/td[2]/div/button[1]/i")
	public WebElement editIcon;
	
	@FindBy(xpath = "//*[@id='searchFilter']/ul/li[1]/div/a")
	public List<WebElement> groupsFilter;

	@FindBy(xpath = "//*[@id='searchFilter']/ul/li[2]/div/a")
	public List<WebElement> firstNameFilter;

	@FindBy(xpath = "//*[@id='searchFilter']/ul/li[3]/div/a")
	public List<WebElement> lastNameFilter;

	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[2]/div/div/div[3]/table/tbody/tr[1]/td[6]")
	public WebElement groupColumn;

	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[2]/div/div/div[3]/table/tbody/tr[1]/td[4]")
	public WebElement firstNameColumn;

	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[2]/div/div/div[3]/table/tbody/tr[1]/td[5]")
	public WebElement lastNameColumn;

	@FindBy(id = "userCreateOrg")
	public WebElement userCreateOrg;
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement homeLink;

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	String createdUsers = "";
	String statusMessage;

	public boolean searchUser(String criteria) {
		try {
			waitTime();
			searchAutoComplete.sendKeys(criteria);
			searchAutoComplete.sendKeys(Keys.ENTER);

			waitTime();
			validador = rowOneGrid.isDisplayed();
		} catch (Exception e) {
			System.out.println("Record not found");
		}
		return validator;
	}

	public boolean searchByCheck() {
		try {
			waitTime();
			validator = base.searchFilters(groupsFilter, groupColumn, "");
			waitTime();
			System.out.println("filter testing finished");
		} catch (Exception e) {
			System.out.println("Record not found");
		}
		return validator;
	}

	public boolean searchByCriteria(String criteria) {
		try {
			waitTime();
			validator = base.searchFilters(firstNameFilter, firstNameColumn,
					criteria);

			waitTime();
			System.out.println("filter testing finished");
		} catch (Exception e) {
			System.out.println("Record not found");
		}
		return validator;
	}

	public String createUser() {
		for (int x = 0; x < 2; x++) {
			waitTime();
			waitForElementAndClick(createUserLink);
			waitTime();
			Date date = new Date();
			String datevalue = date.toString().substring(8, 16)
					.replace(" ", "_").replace(":", "_");

			switch (x) {
			case 0:
				
				waitForElementAndSendKeys(firstNameField, "teacher");
				waitForElementAndSendKeys(lastNameField, datevalue);
				waitForElementAndSendKeys(password, "12345");
				waitForElementAndSendKeys(retypePassword, "12345");
				selectOption(role, "Teacher");
				selectOption(userCreateOrg, "West Sacramento School");
				waitForElementAndClick(submit);
				waitTime();
				System.out.println("T" + datevalue + "  Created");
				createdUsers = createdUsers + "T" + datevalue + ",";
				break;
			case 1:

				waitForElementAndSendKeys(firstNameField, "student");
				waitForElementAndSendKeys(lastNameField, datevalue);
				waitForElementAndSendKeys(password, "12345");
				waitForElementAndSendKeys(retypePassword, "12345");
				selectOption(role, "Student");
				selectOption(userCreateOrg, "West Sacramento School");
				waitForElementAndClick(submit);
				waitTime();
				System.out.println("S" + datevalue + "  Created");
				createdUsers = createdUsers + "S" + datevalue;
				break;
			}
		
			try {
				
				waitForElementAndClick(modalOk);
			} catch (Exception e) {
				System.out.println("Modal not found");
			}

		}
		waitTime();
		waitForElementAndClick(homeLink);
		return createdUsers;
	}

	public boolean createSpecificUser(String firstName, String lastName,
			String newPassword, String newRole, String Organization,
			String button) {
		try {			
			waitForElementAndClick(createUserLink);
			waitForElementAndSendKeys(firstNameField, firstName);
			waitForElementAndSendKeys(lastNameField, lastName);
			waitForElementAndSendKeys(password, newPassword);
			waitForElementAndSendKeys(retypePassword, newPassword);
			waitForElementAndSendKeys(role, newRole);
			waitForElementAndSendKeys(userCreateOrg,Organization);
			waitForElementAndClick(submit);

			System.out.println(firstName + " " + lastName + "  Created");
			if (button.contains("submit")) {
				submit.click();
				try {
					waitTime();
					driver.switchTo().alert().accept();
					validador = false;
				} catch (Exception e) {
					System.out.println("User creation done ");
					validador = true;
				}

			} else {
				waitForElementAndClick(userCreateInputCancel);
				System.out.println("Creation canceled ");
				validador = false;
			}

		} catch (Exception e) {
			System.out.println("User creation Failed");
		}

		return validador;
	}

	public String DeleteCreatedUsers(String[] createdUsers) {

		try {

			for (String user : createdUsers) {
				waitForElementAndSendKeys(searchAutoComplete, user);
				waitForElementAndClick(searchButton);
				waitForElementAndClick(deleteIcon);
				waitForElementAndClick(globalModalDeleteButton);
				clearSearchFilter();
			}
			statusMessage = "User Deleted";

		} catch (Exception e) {
			statusMessage = e.getMessage();
		}

		return statusMessage;
	}
	
	public boolean editUserSucess(String User, String newPassword ){
		waitTime();
		searchUser(User);
		waitForElementAndClick(editIcon);
		waitForElementAndSendKeys(userEditInputPW1, newPassword);
		waitForElementAndSendKeys(userEditInputPW2, newPassword);
		waitForElementAndClick(genericUserEditModelUpdateButton);
		return validador;
	}
	
	public boolean editUserCancel(String User, String newPassword ){
		waitTime();
		searchUser(User);
		waitForElementAndClick(editIcon);
		waitForElementAndSendKeys(userEditInputPW1, newPassword);
		waitForElementAndSendKeys(userEditInputPW2, newPassword);
		waitForElementAndClick(genericUserEditModelCancelButton);
		return validador;
	}

}
