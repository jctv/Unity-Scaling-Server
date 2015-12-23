package pages;

import generic.BasePage;
import generic.BaseTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
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

	@FindBy(id = "searchAutoCompleteContainer")
	public WebElement searchAutoCompleteContainer;

	@FindBy(id = "searchButton")
	public WebElement searchButton;

	@FindBy(xpath = "//i[@class='fa fa-times']")
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
	
	
	@FindBy(id = "globalModalInfoBody")
	public WebElement globalModalInfoBody;
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

	@FindBy(xpath = "//*[@id='quickViewUserCreate']/div/div/div[3]/div[3]/div/div/button")
	public WebElement userCreateOrg;

	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement homeLink;

	@FindBy(xpath = "//button[@data-id = 'userCreateOrg']")
	public WebElement searchOrgButton;
	

	@FindBy(xpath = "//div[@class = 'btn-group bootstrap-select user-metadata-required select-search-by-name-organization open']//input")
	public WebElement searchOrgFieldInput;

	@FindBy(xpath = "//span[text()='Organization']")
	public WebElement organizationFilter;

	@FindBy(xpath = "//*[@id='object-select-titles']/div")
	public WebElement SelectOrglink;

	@FindBy(xpath = "//span[text()='Automated School' and @class = 'jqtree-title jqtree_common']")
	public WebElement AutomatedSchoolLink;

	@FindBy(id = "globalModalOKCancelSaveButton")
	public WebElement globalModalOKCancelSaveButton;
	
	@FindBy(xpath = "//button[text()='Delete']")
	public WebElement deleteButton;

	@FindBy(xpath = "/html/body/div[20]/div/ul/li[1]/a")
	public WebElement selectOrg;

	@FindBy(xpath = "//button[@data-id='userCreateOrg']")
	public WebElement selectOrgDropDown;

	@FindBy(id = "first_name")
	public WebElement userEditFirstName;

	@FindBy(id = "middle_name")
	public WebElement userEditMiddleName;

	@FindBy(id = "last_name")
	public WebElement userEditLastName;

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	String createdUsers = "";
	String statusMessage;
	private int usersToCreate = 2;

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
		for (int x = 0; x <= usersToCreate; x++) {
			waitTime();
			customeWaitTime(2);
			waitForElementAndClick(createUserLink);
			takeScreenShot();
			customeWaitTime(2);
			Date date = new Date();
			String datevalue = date.toString().substring(8, 16)
					.replace(" ", "_").replace(":", "_");
			if (x == 0) {
				waitForElementAndSendKeys(firstNameField, "teacher");
				waitForElementAndSendKeys(lastNameField, datevalue);
				waitForElementAndSendKeys(password, "12345");
				waitForElementAndSendKeys(retypePassword, "12345");
				selectOption(role, "Teacher");
				try {
					waitForElementAndClick(searchOrgButton);
					waitForElementAndSendKeys(searchOrgFieldInput, "Automated");
				} catch (Exception e) {
					System.out.println("Error selecting the School");
				}
				waitTime();
				waitForElementAndClick(submit);
				waitTime();
				System.out.println("T" + datevalue + "  Created");
				createdUsers = createdUsers + "T" + datevalue + ",";
			} else {

				waitForElementAndSendKeys(firstNameField, "student");
				waitForElementAndSendKeys(lastNameField, datevalue + x);
				waitForElementAndSendKeys(password, "12345");
				waitForElementAndSendKeys(retypePassword, "12345");
				selectOption(role, "Student");

				try {
					waitForElementAndClick(searchOrgButton);
					waitForElementAndSendKeys(searchOrgFieldInput, "Automated");
				} catch (Exception e) {
					System.out.println("Error selecting the School");
				}
				waitTime();
				waitForElementAndClick(submit);
				waitTime();
				System.out.println("S" + datevalue + x + "  Created");
				if (x < usersToCreate) {
					createdUsers = createdUsers + "S" + datevalue + x + ",";
				} else {
					createdUsers = createdUsers + "S" + datevalue + x;

				}
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

	public String createSpecificUser(String firstName, String lastName,
			String newPassword, String newRole, String organization) {
		try {
			waitTime();
			waitForElementAndClick(createUserLink);
			waitTime();
			waitForElementAndSendKeys(firstNameField, firstName);
			waitForElementAndSendKeys(lastNameField, lastName);
			waitForElementAndSendKeys(password, newPassword);
			waitForElementAndSendKeys(retypePassword, newPassword);
			waitForElementAndSendKeys(role, newRole);

			waitForElementAndClick(searchOrgButton);
			waitForElementAndSendKeys(searchOrgFieldInput, "Automated");
			waitTime();
			waitForElementAndClick(submit);

			System.out.println(firstName + " " + lastName + "  Created");
			try {
				statusMessage =globalModalInfoBody.getText();
				waitForElementAndClick(modalOk);
			} catch (Exception e) {
				System.out.println("Modal not found");
			}

		} catch (Exception e) {
			System.out.println("User creation Failed");
		}

		return statusMessage;
	}

	public String DeleteCreatedUsers(String[] createdUsers) {

		try {
			waitForElementAndClick(organizationFilter);
			waitForElementAndClick(SelectOrglink);
			waitForElementAndClick(AutomatedSchoolLink);
			waitForElementAndClick(globalModalOKCancelSaveButton);
			for (String user : createdUsers) {
				waitTime();
				waitForElementAndClick(deleteIcon);

				waitForElementAndClick(deleteButton);
			}
			statusMessage = "User Deleted";

		} catch (Exception e) {
			statusMessage = e.getMessage();
		}
		waitForElementAndClick(homeLink);
		return statusMessage;
	}

	public boolean editUserSucess(String User, String newPassword) {
		waitTime();
		searchUser(User);
		waitForElementAndClick(editIcon);
		waitForElementAndSendKeys(userEditInputPW1, newPassword);
		waitForElementAndSendKeys(userEditInputPW2, newPassword);
		waitForElementAndClick(genericUserEditModelUpdateButton);
		return validador;
	}

	public boolean editUserCancel(String User, String newPassword) {
		waitTime();
		searchUser(User);
		waitForElementAndClick(editIcon);
		waitForElementAndSendKeys(userEditInputPW1, newPassword);
		waitForElementAndSendKeys(userEditInputPW2, newPassword);
		waitForElementAndClick(genericUserEditModelCancelButton);
		return validador;
	}

	public void enterUserInformation(String firstName, String lastName,
			String newPassword, String repeatPassword, String newRole,
			String organization) {
		waitTime();
		waitTime();
		firstNameField.clear();
		waitForElementAndSendKeys(firstNameField, firstName);
		lastNameField.clear();
		waitForElementAndSendKeys(lastNameField, lastName);
		waitForElementAndSendKeys(password, newPassword);
		waitForElementAndSendKeys(retypePassword, repeatPassword);
		// waitForElementAndSendKeys(role, newRole);
		selectOption(role, newRole);
		waitTime();
		selectOrganization(organization);
		waitTime();
		// waitForElementAndSendKeys(searchOrgFieldInput, organization);
		waitTime();
		waitForElementAndClick(submit);
		waitTime();
	}

	/**
	 * Added this method as organization drop down is populating through plugin
	 * not a normal select box
	 * 
	 * @param option
	 */
	public void selectOrganization(String option) {
		selectOrgDropDown.click();
		waitTime();
		List<WebElement> organizations = driver
				.findElements(By
						.xpath("//div[@class='btn-group bootstrap-select user-metadata-required select-search-by-name-organization open']//ul[@class='dropdown-menu inner']/li"));
		for (WebElement org : organizations) {
			try {
				if (org.getText().equals(option)) {
					org.click();
					break;
				}

			} catch (Exception e) {
				System.out.println(option + " is not available");
			}
		}

	}

}
