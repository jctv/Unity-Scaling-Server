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

	/*@FindBy(xpath = "//li[.//span[text()='Automated School' and @class = 'jqtree_common jqtree-title']][last()]")
	public WebElement schoolCreated;*/

	@FindBy(xpath = "//li[.//span[text()='Automated School']]//button[@title='Remove']")
	public WebElement deleteSchoolIcon;

	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backToDashboard;

	@FindBy(xpath = "//a[text()='Create User']")
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
	
	@FindBy(xpath = "//button[@data-id = 'userCreateOrg']")
	public WebElement searchOrgButton;

	@FindBy(xpath = "//*[@id='globalModalViewBody']/div/form/div[6]/div/div/div/div/input")
	public WebElement searchOrgFieldInput;
	
	@FindBy(id = "globalModalViewTitle")
	public WebElement globalModalViewTitle;
	
	@FindBy(id = "userCreateInputSubmit")
	public WebElement submit;
	
	@FindBy(id = "globalModalInfoOkButton")
	public WebElement modalOk;
	
	String statusMessage;

	
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
			//waitForElementAndClick(backToDashboard);
		} catch (Exception e) {
			System.out.println("Unable to Create the  " + "school");
		}

	}

	public void deleteCreatedOrganization(String org) {
		try {
			WebElement schoolCreated = driver
					.findElement(By
							.xpath("//span[text()='" + org + "']"));
			waitAndFocus(schoolCreated);
			WebElement schoolDeleteIcon = driver
					.findElement(By
							.xpath("//span[text()='" + org + "']//button[@title='Remove']"));
			waitForElementAndClick(schoolDeleteIcon);
			waitForElementAndClick(globalModalOKCancelSaveButton);
			System.out.println("School deleted");
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
			waitForElementAndClick(globalModalInfoOkButton);
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
			waitForElementAndClick(globalModalOKCancelSaveButton);

		} catch (Exception e) {
			// TODO

		}

	}
	
	public String createSpecificUser(String firstName, String lastName,
			String newPassword, String confirmPassword, String newRole,
			String organization) {
		try {
			// statusMessage ="";
			waitTime();
			waitForElementAndClick(createUserLink);
			waitTime();
			waitForElementAndSendKeys(firstNameField, firstName);
			waitForElementAndSendKeys(lastNameField, lastName);
			waitForElementAndSendKeys(password, newPassword);
			waitForElementAndSendKeys(retypePassword, confirmPassword);
			waitForElementAndSendKeys(role, newRole);
			role.click();
			waitForElementAndClick(searchOrgButton);
			waitForElementAndSendKeys(searchOrgFieldInput, "Automated");
			globalModalViewTitle.click();
			waitTime();
			waitForElementAndClick(submit);
			try {
				statusMessage = waitAndGetElementText(globalModalInfoBody);
				System.out.println(statusMessage);

				waitForElementAndClick(modalOk);
			} catch (Exception e) {
				System.out.println("Modal not found");
			}

		} catch (Exception e) {
			System.out.println("User creation Failed");
		}
		if (statusMessage.contains("Passwords Don't Match!")) {
			waitForElementAndClick(createUserLink);
		}
		return statusMessage;
	}

	
	
	public String getStateFromOrgTree(String state){
		String stateName = null;
		try{
			WebElement stateOrgTree = driver.findElement(By.xpath(".//*[@id='treeNavigation']/ul/li/div/span[text()='"+ state +"']"));
			stateName= waitAndGetElementText(stateOrgTree);
		}catch(Exception e){
			
		}
		 
		return stateName;
	}
	
	public String getDistFromOrgTree(String state , String dist ){
		String distName = null;
		try{
			WebElement stateOrgTree = driver.findElement(By.xpath(".//*[@id='treeNavigation']/ul/li/div/span[text()='"+ state +"']"));
			waitForElementAndClick(stateOrgTree);
			customeWaitTime(2);
			WebElement distOrgTree = driver.findElement(By.xpath(".//*[@id='treeNavigation']/ul/li/div/span[text()='"+ state +"']/../..//ul/li/div/span[text()='"+ dist +"']"));
			distName= waitAndGetElementText(distOrgTree);
		}catch(Exception e){
			
		}
		 
		return distName;
	}
	
	
	
	public String getOrgNameInTree(String state ,String dist, String school , String orgType){
		String orgName = null;
		try{
			if(orgType.equalsIgnoreCase("state")){
				WebElement stateOrgTree = driver.findElement(By.xpath(".//*[@id='treeNavigation']/ul/li/div/span[text()='"+ state +"']"));
				orgName= waitAndGetElementText(stateOrgTree);
				refreshPage();

			}else if(orgType.equalsIgnoreCase("district")){
				WebElement stateOrgTree = driver.findElement(By.xpath(".//*[@id='treeNavigation']/ul/li/div/span[text()='"+ state +"']"));
				waitForElementAndClick(stateOrgTree);
				customeWaitTime(2);
				WebElement distOrgTree = driver.findElement(By.xpath(".//*[@id='treeNavigation']/ul/li/div/span[text()='"+ state +"']/../..//ul/li/div/span[text()='"+ dist +"']"));
				orgName= waitAndGetElementText(distOrgTree);
				refreshPage();
				
			}else if(orgType.equalsIgnoreCase("school")){
				WebElement stateOrgTree = driver.findElement(By.xpath(".//*[@id='treeNavigation']/ul/li/div/span[text()='"+ state +"']"));
				waitAndGetElementText(stateOrgTree);
				customeWaitTime(2);
				WebElement distOrgTree = driver.findElement(By.xpath(".//*[@id='treeNavigation']/ul/li/div/span[text()='"+ state +"']/../..//ul/li/div/span[text()='"+ dist +"']"));
				waitForElementAndClick(distOrgTree);
				customeWaitTime(2);
				WebElement schoolOrgTree= driver.findElement(By.xpath(".//*[@id='treeNavigation']/ul/li/div/span[text()='"+ state +"']/../..//ul/li/div/span[text()='"+ dist +"']/../../ul/li/div/span[text()='"+ school +"']"));
				orgName= waitAndGetElementText(schoolOrgTree);
				refreshPage();
			}
			
		}catch(Exception e){
			System.out.println("Unable to get the org name ");
		}
		 
		return orgName;
	}
	
	
	/*public boolean isOrganizationHierarachyCreated(String state , String dist , String school){
		boolean validator = false;
		try{
		WebElement stateOrgTree = driver.findElement(By.xpath(".//*[@id='treeNavigation']/ul/li/div/span[text()='"+ state +"']"));
		waitForElementAndClick(stateOrgTree);
		customeWaitTime(2);
		WebElement distOrgTree = driver.findElement(By.xpath(".//*[@id='treeNavigation']/ul/li/div/span[text()='"+ state +"']/../..//ul/li/div/span[text()='"+ dist +"']"));
		waitForElementAndClick(distOrgTree);
		customeWaitTime(2);
		WebElement schoolOrgTree= driver.findElement(By.xpath(".//*[@id='treeNavigation']/ul/li/div/span[text()='"+ state +"']/../..//ul/li/div/span[text()='"+ dist +"']/../../ul/li/div/span[text()='"+ school +"']"));
		customeWaitTime(5);
		waitForElementAndClick(schoolOrgTree);
		if(stateOrgTree.isDisplayed() &&  distOrgTree.isDisplayed() && schoolOrgTree.isDisplayed()){
			validator = true;
		}else{
			
			validator = false;
		}
		}catch(Exception e){
			System.out.println("Organization Structure is not created ");

		}
		return validator;
	}
	*/
	
	
	
	
}
