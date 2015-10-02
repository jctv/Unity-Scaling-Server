package pages;

import generic.BasePage;
import generic.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoard extends BasePage {
	BasePage base;
	BaseTest test;

	public DashBoard(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// tiles options

	@FindBy(xpath = "//*[@id='region-navigation']/div/span[1]")
	public WebElement addTile;

	@FindBy(xpath = "//*[@id='region-navigation']/div/span[2]")
	public WebElement removeTile;
	
	@FindBy(xpath = "//*[@id='region-navigation']/div/span[3]")
	public WebElement helpLink;
	
	@FindBy(id = "tile_view_adaptive_config")
	public WebElement adaptiveConfigTile;

	@FindBy(id = "tile_view_blog")
	public WebElement blogTile;

	@FindBy(id = "tile_view_roster")
	public WebElement classRosterTile;

	@FindBy(id = "tile_view_delivery")
	public WebElement deliveryTile;

	@FindBy(id = "tile_view_handscoring")
	public WebElement handScoringTile;

	@FindBy(id = "tile_view_item_bank")
	public WebElement itemBankTile;

	@FindBy(id = "tile_view_item")
	public WebElement ItemsTile;

	@FindBy(id = "tile_view_machine_profile")
	public WebElement machineProfileTile;

	@FindBy(id = "tile_view_report")
	public WebElement mediaTile;

	@FindBy(id = "tile_view_message")
	public WebElement messageTile;
					
	@FindBy(id = "tile_view_organization")
	public WebElement organizationTile;

	@FindBy(id = "tile_view_passage")
	public WebElement passageTile;

	@FindBy(id = "tile_view_permission")
	public WebElement permissionTile;

	@FindBy(id = "tile_view_report")
	public WebElement reportsTile;

	@FindBy(id = "tile_view_role")
	public WebElement roleTile;

	@FindBy(id = "tile_view_rubric")
	public WebElement rubricTile;

	@FindBy(id = "tile_view_calendar")
	public WebElement scheduleTile;

	@FindBy(id = "tile_view_score_profile")
	public WebElement scoreProfileTile;

	@FindBy(id = "tile_view_standard")
	public WebElement satandardTile;

	@FindBy(id = "tile_view_test_administration")
	public WebElement testAdministrationTile;

	@FindBy(id = "tile_view_test_bank") 
	public WebElement testbankTile;

	@FindBy(id = "tile_view_test")
	public WebElement testCreationTile;

	@FindBy(id = "tile_view_test_performance")
	public WebElement performanceTile;

	@FindBy(id = "tile_view_testing_environment")
	public WebElement testingEnviromentTile;

	@FindBy(id = "tile_view_user")
	public WebElement usersTile;

	@FindBy(id = "tile_view_workflow_admin")
	public WebElement workflowTile;
	

	@FindBy(id = "navbarDrop1")
	public WebElement userDrop;

	@FindBy(xpath = "//*[@id='navigationUserName']/ul/li/a")
	public WebElement logOut;

	public boolean validator = false;
	
	@FindBy(xpath = "// *[@id='dashboardGrid']/ul/li[1]")
	public WebElement firtsTile;
	
	@FindBy(xpath = "// *[@id='dashboardGrid']/ul/li[2]")
	public WebElement secondTile;
	
	
	@FindBy(id = "tileDeleteQuickPanel")
	public WebElement removeArea;
	
	@FindBy(id = "tile_quick_link")
	public WebElement quickLinkInput;
	
	
	@FindBy(xpath = "//*[@id='quick_links']/li")
	public WebElement quickLinkList;
	
	@FindBy(id = "globalModalOKCancelSaveButton")
	public WebElement globalModalOKCancelSaveButton;
	
	@FindBy(id = "messageLink")
	public WebElement messageLink;
	
	

	@FindBy(xpath = "//*[@id='dashboardGrid']/ul/li[1]/i")
	public WebElement configLink;
	
	
	
	

	
	

	public Users goToUsers() {
		System.out.println("Users Tile is enable " + usersTile.isEnabled());
		
		try {
			waitForElementAndDoubleClick(usersTile);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return new Users(driver);

	}

	public ClassRoster goToClassRoster() {
		System.out.println("Is the  class roster tile enable "
				+ classRosterTile.isEnabled());
		waitForElementAndDoubleClick(classRosterTile);
		try {
			classRosterTile.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return new ClassRoster(driver);

	}

	public TestCreation goToTestCreation() {
		System.out.println("Is the test creation tile enable "
				+ testCreationTile.isEnabled());

		waitForElementAndDoubleClick(testCreationTile);
		try {
			waitForElementAndDoubleClick(testCreationTile);
			
		} catch (Exception e) {
			
		}
		return new TestCreation(driver);

	}

	public Schedule goToSchedule() {
		System.out.println("Is the schedule tile enable "
				+ scheduleTile.isEnabled());

		
		
		waitForElementAndDoubleClick(scheduleTile);
		try {
			waitForElementAndDoubleClick(scheduleTile);
			
		} catch (Exception e) {
			
		}
		
		return new Schedule(driver);

	}

	public Delivery goToDelivery() {
		System.out.println("Is delivery tile enable "
				+ deliveryTile.isEnabled());
		waitForElementAndDoubleClick(deliveryTile);
		try {
			deliveryTile.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Delivery(driver);

	}

	public HandScoring goToHandScoring() {
		System.out.println("Is the handscoring tile enable "
				+ handScoringTile.isEnabled());

		waitForElementAndDoubleClick(handScoringTile);
		try {
			
			waitForElementAndDoubleClick(handScoringTile);
		} catch (Exception e) {
			
		}
		
		
		return new HandScoring(driver);

	}

	public Reports goToReports() {
		System.out.println("Is the reports tile enable "
				+ reportsTile.isEnabled());
		try{
		waitForElementAndDoubleClick(reportsTile);
		}catch(Exception e){
			waitForElementAndDoubleClick(reportsTile);
			
		}
		
		return new Reports(driver);

	}

	public Items goToItems() {
		waitTime();
		System.out.println("Is the test class items tile enable "
				+ ItemsTile.isEnabled());
		waitForElementAndDoubleClick(ItemsTile);
		try{
			waitForElementAndDoubleClick(ItemsTile);
			
		}catch (Exception e) {
			
		}
		
		
		return new Items(driver);

	}

	public String addTiles() {
		//*[@id="tileSelectionContainer"]/div[1]
		waitForElementAndDoubleClick(addTile);
		waitTime();
		for (int x = 1; x < 26; x++) {
			try {
				String path = "//*[@id='tileSelectionContainer']/div[" + x + "]";
				driver.findElement(
						By.xpath(path))
						.click();
			} catch (Exception e) {

			}
		}

		return "The tiles has been added";
	}

	public boolean deleteTile() {
		waitTime();
		removeTile.click();
		try {
			base.dragAndDrop(firtsTile, removeArea);
			validator = true;
		} catch (Exception e) {
			System.out.println("Unable to delete a Tile");
		}
		return validator;
	}

	
	public boolean moveTiles() {
		waitTime();
		
		try {
			base.dragAndDrop(firtsTile, secondTile);
			validator = true;
		} catch (Exception e) {
			System.out.println("Unable to move a Tile");
		}
		return validator;
	}
	
	public boolean changeTileConfig() {
		waitTime();
		
		try {
			configLink.click();
			quickLinkInput.click();
			globalModalOKCancelSaveButton.click();
			waitTime();
			quickLinkList.isDisplayed();
			
			validator = true;
		} catch (Exception e) {
			System.out.println("Unable to move a Tile");
		}
		return validator;
	}
	
	
	public boolean goToMessages() {
		waitTime();		
		try {
			messageLink.click();
			waitTime();			
			validator = driver.getCurrentUrl().contains("message");
		} catch (Exception e) {
			System.out.println("Unable to go to the message page");
		}
		return validator;
	}
	
	
	public Organization goToOrganization() {
		waitTime();		
		try {
			waitForElementAndDoubleClick(organizationTile);		
		} catch (Exception e) {
			System.out.println("Unable to go to the organizationTile page");
		}
		return new Organization(driver);
	}
	

	public ItemsBank goToItemsBank() {
		waitTime();		
		try {
			waitForElementAndDoubleClick(itemBankTile);		
		} catch (Exception e) {
			System.out.println("Unable to go to the items bank page");
		}
		return new ItemsBank(driver);
	}
	
	public TestsBank goToTestsBank() {
		waitTime();		
		try {
			waitForElementAndDoubleClick(testbankTile);		
		} catch (Exception e) {
			System.out.println("Unable to go to the tests bank page");
		}
		return new TestsBank(driver);
	}
	
	public Login logOut() {

		userDrop.click();
		logOut.click();

		return new Login(driver);
	}
}
