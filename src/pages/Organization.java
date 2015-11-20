package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sun.net.www.content.audio.wav;

public class Organization extends BasePage {

	BasePage base;
	BaseTest test;

	public Organization(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "treeNavCreate")
	public WebElement createNewOrganization;

	@FindBy(xpath = "//button[(@class ='btn btn-primary org-save')]")
	public WebElement createHerarchy;

	@FindBy(id = "globalModalInfoOkButton")
	public WebElement globalModalInfoOkButton;

	@FindBy(id = "tName")
	public WebElement tName;

	@FindBy(id = "tType")
	public WebElement tType;

	@FindBy(id = "globalModalOKCancelSaveButton")
	public WebElement globalModalOKCancelSaveButton;

	@FindBy(xpath = "//li[.//span[text()='Automated School' and @class = 'jqtree_common jqtree-title']][last()]")
	public WebElement schoolCreated;

	@FindBy(xpath = "//li[.//span[text()='Automated School']]//button[@title='Remove']")
	public WebElement deleteSchoolIcon;

	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backToDashboard;

	public void createOrganizationNode(String name, String type) {
		try {
			waitForElementAndClick(createNewOrganization);
			waitForElementAndSendKeys(tName, name);
			waitForElementAndSendKeys(tType, type);
			selectOption(tType, "country");

			waitForElementAndClick(globalModalInfoOkButton);

		} catch (Exception e) {
			System.out.println("Unable to Create the  " + type);
		}

		System.out.println(type + " Created");
	}

	public void createNewOrganization(String name) {
		try {
			waitForElementAndClick(createNewOrganization);

			waitForElementAndSendKeys(tName, name);
			selectOption(tType, "school");
			waitForElementAndClick(createHerarchy);
			waitForElementAndClick(globalModalInfoOkButton);
			System.out.println("School Created");
			waitTime();
			waitForElementAndClick(backToDashboard);
		} catch (Exception e) {
			System.out.println("Unable to Create the  " + "school");
		}

	}

	public void deleteCreatedOrganization() {
		try {
			waitTime();

			waitAndFocus(schoolCreated);
			waitForElementAndClick(deleteSchoolIcon);
			waitForElementAndClick(globalModalOKCancelSaveButton);

		} catch (Exception e) {

			System.out.println("Unable to delete the created school");
		}

	}

	public void moveToOrganization(String orgName) {
		try {
			waitTime();
			WebElement organization = driver
					.findElement(By
							.xpath("//li[.//span[text()='"
									+ orgName
									+ "' and @class = 'jqtree_common jqtree-title']][last()]"));
			waitAndFocus(organization);
			waitTime();
		} catch (Exception e) {

			System.out.println("Unable to Create the  " + "school");

		}

	}

	public void addOrganization(String stateOrgName, String distOrgName,
			String schoolOrgName, String orgType) {
		try {
			switch (orgType) {
			case "state":
				try {
					waitForElementAndClick(createNewOrganization);
					waitTime();
					waitForElementAndSendKeys(tName, stateOrgName);
					waitTime();
					selectOption(tType, orgType);
					waitTime();
					waitForElementAndClick(createHerarchy);
					waitTime();
					waitTime();
				} catch (Exception e) {

					System.out
							.println("Unable to Create the child organization  "
									+ stateOrgName);

				}

				break;
			case "district":
				try {
					WebElement stateOrg = driver
							.findElement(By
									.xpath("//li[.//span[text()='"
											+ stateOrgName
											+ "' and @class = 'jqtree_common jqtree-title']][last()]"));
					waitTime();
					waitAndFocus(stateOrg);
					waitTime();
					waitTime();
					WebElement stateOrgAddIcaon = driver.findElement(By
							.xpath("//li[.//span[text()='" + stateOrgName
									+ "' ]]//button[@title='Add Child']"));

					waitTime();
					waitTime();
					waitForElementAndClick(stateOrgAddIcaon);
					waitTime();
					waitForElementAndSendKeys(tName, distOrgName);
					selectOption(tType, orgType);
					waitForElementAndClick(createHerarchy);
					waitTime();
					waitTime();

				} catch (Exception e) {
					System.out
							.println("Unable to Create the child organization  "
									+ distOrgName);

				}

				break;
			case "school":
				try {
					WebElement StateOrganizationNode = driver
							.findElement(By
									.xpath("//li[.//span[text()='"
											+ stateOrgName
											+ "' and @class = 'jqtree_common jqtree-title']][last()]/div/a/span"));
					waitTime();
					waitTime();
					waitForElementAndClick(StateOrganizationNode);
					waitTime();
					waitTime();

					WebElement distOrganization = driver
							.findElement(By
									.xpath("//li[.//span[text()='"
											+ stateOrgName
											+ "' and @class = 'jqtree_common jqtree-title']][last()]/ul/li//span[text()='"
											+ distOrgName + "']"));

					waitAndFocus(distOrganization);
					waitTime();
					waitTime();
					WebElement distAddIcon = driver
							.findElement(By
									.xpath("//li[.//span[text()='"
											+ stateOrgName
											+ "' and @class = 'jqtree_common jqtree-title']][last()]/ul/li//span[text()='"
											+ distOrgName
											+ "']//button[@title='Add Child']"));

					waitForElementAndClick(distAddIcon);
					waitTime();
					waitTime();
					waitForElementAndSendKeys(tName, schoolOrgName);
					selectOption(tType, orgType);
					waitForElementAndClick(createHerarchy);
					waitTime();
					waitTime();

				} catch (Exception e) {
					System.out
							.println("Unable to Create the child organization  "
									+ schoolOrgName);
				}

				break;
			}

		} catch (Exception e) {

			// TODO

		}

	}

	public void deleteOrganization(String stateOrgName, String distOrgName,
			String schoolOrgName, String orgType) {
		try {
			waitTime();
			switch (orgType) {
			case "state":
				try {
					WebElement stateOrg = driver
							.findElement(By
									.xpath("//li[.//span[text()='"
											+ stateOrgName
											+ "' and @class = 'jqtree_common jqtree-title']][last()]"));
					waitTime();
					waitAndFocus(stateOrg);
					WebElement stateOrgDeleteIcon = driver
							.findElement(By
									.xpath("//li[.//span[text()='"
											+ stateOrgName
											+ "' and @class = 'jqtree_common jqtree-title']][last()]//button[@title='Remove']"));
					waitTime();
					waitForElementAndClick(stateOrgDeleteIcon);
					waitTime();

				} catch (Exception e) {

					System.out.println("Unable to delete  organization  "
							+ stateOrgName);

				}

				break;
			case "district":
				try {
					WebElement parentOrganizationIcon = driver
							.findElement(By
									.xpath("//li[.//span[text()='"
											+ stateOrgName
											+ "' and @class = 'jqtree_common jqtree-title']][last()]/div/a/span"));
					waitTime();
					waitTime();
					waitForElementAndClick(parentOrganizationIcon);
					waitTime();
					waitTime();
					WebElement childOrganization = driver
							.findElement(By
									.xpath("//li[.//span[text()='"
											+ stateOrgName
											+ "' and @class = 'jqtree_common jqtree-title']][last()]/ul/li//span[text()='"
											+ distOrgName + "']"));

					waitAndFocus(childOrganization);
					waitTime();
					waitTime();

					WebElement distDeleteIcon = driver
							.findElement(By
									.xpath("//li[.//span[text()='"
											+ stateOrgName
											+ "' and @class = 'jqtree_common jqtree-title']][last()]/ul/li//span[text()='"
											+ distOrgName
											+ "']//button[@title='Remove']"));

					waitForElementAndClick(distDeleteIcon);
					waitTime();
					waitTime();

					waitTime();

				} catch (Exception e) {

					System.out.println("Unable to delete  organization  "
							+ distOrgName);

				}
				break;
			case "school":

				try {
					WebElement stateOrganizationIcon = driver
							.findElement(By
									.xpath("//li[.//span[text()='"
											+ stateOrgName
											+ "' and @class = 'jqtree_common jqtree-title']][last()]/div/a/span"));
					waitTime();
					waitTime();
					waitForElementAndClick(stateOrganizationIcon);
					waitTime();
					waitTime();
					WebElement distOrganizationIcon = driver
							.findElement(By
									.xpath("//li[.//span[text()='"
											+ stateOrgName
											+ "' and @class = 'jqtree_common jqtree-title']][last()]/ul/li//span[text()='"
											+ distOrgName + "']/../a/span"));

					waitForElementAndClick(distOrganizationIcon);

					WebElement schoolOrganizationIcon = driver
							.findElement(By
									.xpath("//li[.//span[text()='"
											+ stateOrgName
											+ "' and @class = 'jqtree_common jqtree-title']][last()]/ul/li//span[text()='"
											+ distOrgName
											+ "']/../../ul/li//span[text()='"
											+ schoolOrgName + "']"));

					waitAndFocus(schoolOrganizationIcon);

					waitTime();
					waitTime();

					WebElement schoolDeleteIcon = driver
							.findElement(By
									.xpath("//li[.//span[text()='"
											+ stateOrgName
											+ "' and @class = 'jqtree_common jqtree-title']][last()]/ul/li//span[text()='"
											+ distOrgName
											+ "']/../../ul/li//span[text()='"
											+ schoolOrgName
											+ "']//button[@title='Remove']"));

					waitForElementAndClick(schoolDeleteIcon);
					waitTime();
					waitTime();

				} catch (Exception e) {

					System.out.println("Unable to delete  organization  "
							+ schoolOrgName);

				}
				break;

			}

		} catch (Exception e) {
			// TODO

		}

	}
}
