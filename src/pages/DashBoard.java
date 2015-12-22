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

	@FindBy(xpath = "//a[text()='add tile']")
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

	@FindBy(id = "tile_view_item_import")
	public WebElement ItemsImportTile;
	
	@FindBy(id = "tile_view_machine_profile")
	public WebElement machineProfileTile;

	@FindBy(id = "tile_view_media")
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
	
	@FindBy(id = "tile_view_test_bank") 
	public WebElement itemImportTile;
	
	@FindBy(id = "tile_view_sis_import") 
	public WebElement sisImportTile;
	

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
	
	@FindBy(id = "globalModalInfoOkButton")
	public WebElement globalModalInfoOkButton;
	
	@FindBy(id = "messageLink")
	public WebElement messageLink;
	
	

	@FindBy(xpath = "//*[@id='dashboardGrid']/ul/li[1]/i")
	public WebElement configLink;
	
	@FindBy(id = "tile_view_help") 
	public WebElement helpTile;
	
	
	
	public boolean goToTile(String tileName){
		customeWaitTime(2);	
		String xpathTile = "(//span[text() = '"+ tileName + "'])[last()]";
		customeWaitTime(2);
		driver.findElement(By.xpath(xpathTile)).click();
		customeWaitTime(2);
		waitForJsProcess();
		if(globalModalInfoOkButton.isDisplayed()){
			System.out.println("Device not supported");
			return false;
		}
		return true;
	}
	
	

	public Users goToUsers() {
		System.out.println("Users Tile is enable " + usersTile.isEnabled());
		
		try {
			waitForElementAndDoubleClick(usersTile);
			customeWaitTime(2);	
			waitForJsProcess();
			if(globalModalInfoOkButton.isDisplayed()){
				System.out.println("Device not supported");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return new Users(driver);

	}

	public MachineProfile goMachineProfile() {
		System.out.println("Machine Profile Tile is enable " + machineProfileTile.isEnabled());
		
		try {
			waitForElementAndDoubleClick(machineProfileTile);
			customeWaitTime(2);
			waitForJsProcess();
			if(globalModalInfoOkButton.isDisplayed()){
				System.out.println("Device not supported");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return new MachineProfile(driver);

	}

	public ClassRoster goToClassRoster() {
		System.out.println("Is the  class roster tile enable "
				+ classRosterTile.isEnabled());
		waitForElementAndDoubleClick(classRosterTile);
		customeWaitTime(2);
		if(globalModalInfoOkButton.isDisplayed()){
			System.out.println("Device not supported");
		}
		
		return new ClassRoster(driver);

	}

	public TestCreation goToTestCreation() {
		System.out.println("Is the test creation tile enable "
				+ testCreationTile.isEnabled());

		waitForElementAndDoubleClick(testCreationTile);
		customeWaitTime(2);
		if(globalModalInfoOkButton.isDisplayed()){
			System.out.println("Device not supported");
		}
		return new TestCreation(driver);

	}

	public Schedule goToSchedule() {
		System.out.println("Is the schedule tile enable "
				+ scheduleTile.isEnabled());

		
		
		waitForElementAndDoubleClick(scheduleTile);
		customeWaitTime(2);
		waitForJsProcess();
		if(globalModalInfoOkButton.isDisplayed()){
			System.out.println("Device not supported");
		}
		return new Schedule(driver);

	}

	public Delivery goToDelivery() {
		System.out.println("Is delivery tile enable "
				+ deliveryTile.isEnabled());
		waitForElementAndDoubleClick(deliveryTile);
		customeWaitTime(2);
		if(globalModalInfoOkButton.isDisplayed()){
			System.out.println("Device not supported");
		}
		return new Delivery(driver);

	}

	public HandScoring goToHandScoring() {
		System.out.println("Is the handscoring tile enable "
				+ handScoringTile.isEnabled());

		waitForElementAndDoubleClick(handScoringTile);
		customeWaitTime(2);
		waitForJsProcess();
		if(globalModalInfoOkButton.isDisplayed()){
			System.out.println("Device not supported");
		}
		
		
		return new HandScoring(driver);

	}

	public Reports goToReports() {
		System.out.println("Is the reports tile enable "
				+ reportsTile.isEnabled());
		try{
		waitForElementAndDoubleClick(reportsTile);
		customeWaitTime(2);
		waitForJsProcess();
		if(globalModalInfoOkButton.isDisplayed()){
			System.out.println("Device not supported");
		}
		}catch(Exception e){
			waitForElementAndDoubleClick(reportsTile);
			
		}
		
		return new Reports(driver);

	}

	public Items goToItems() {
		customeWaitTime(2);
		System.out.println("Is the items tile enable "
				+ ItemsTile.isEnabled());
		waitForElementAndDoubleClick(ItemsTile);
		waitForJsProcess();
		customeWaitTime(10);
		if(globalModalInfoOkButton.isDisplayed()){
			System.out.println("Device not supported");
		}
		
		
		return new Items(driver);

	}

	public Help goToHelp() {
		System.out.println("Users Tile is enable " + helpTile.isEnabled());
		
		try {
			waitForElementAndDoubleClick(helpTile);
			customeWaitTime(2);			
			if(globalModalInfoOkButton.isDisplayed()){
				System.out.println("Device not supported");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return new Help(driver);
	}
	public String addTiles() {
		//*[@id="tileSelectionContainer"]/div[1]
		waitForElementAndDoubleClick(addTile);
		customeWaitTime(2);
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

	
	public String getAvailableTile(String tileName) {
		waitForElementAndDoubleClick(addTile);
		customeWaitTime(2);
		WebElement tile = driver.findElement(By.xpath("(//span[text()='"
				+ tileName + "'])[last()]"));
		String availableTile = null;
		try {
			if (tile.isDisplayed()) {
				System.out.println(tile.getText());
				availableTile = tile.getText();
			}

		} catch (Exception e) {
			System.out.println(tileName + " Tile is not available");
		}
		return availableTile;
	}

	public boolean deleteTile() {
		customeWaitTime(2);
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
		customeWaitTime(2);
		
		try {
			base.dragAndDrop(firtsTile, secondTile);
			validator = true;
		} catch (Exception e) {
			System.out.println("Unable to move a Tile");
		}
		return validator;
	}
	
	public boolean changeTileConfig() {
		customeWaitTime(2);
		
		try {
			configLink.click();
			quickLinkInput.click();
			globalModalOKCancelSaveButton.click();
			customeWaitTime(2);
			quickLinkList.isDisplayed();
			
			validator = true;
		} catch (Exception e) {
			System.out.println("Unable to move a Tile");
		}
		return validator;
	}
	
	
	public boolean goToMessages() {
		customeWaitTime(2);		
		try {
			messageLink.click();
			customeWaitTime(2);	
			waitForJsProcess();
			validator = driver.getCurrentUrl().contains("message");
		} catch (Exception e) {
			System.out.println("Unable to go to the message page");
		}
		return validator;
	}
	
	
	public Organization goToOrganization() {
		customeWaitTime(2);		
		try {
			waitForElementAndDoubleClick(organizationTile);	
			customeWaitTime(2);
			waitForJsProcess();
			if(globalModalInfoOkButton.isDisplayed()){
				System.out.println("Device not supported");
			}
		} catch (Exception e) {
			System.out.println("Unable to go to the organizationTile page");
		}
		return new Organization(driver);
	}
	

	public ItemsBank goToItemsBank() {
		customeWaitTime(2);		
		try {
			waitForElementAndDoubleClick(itemBankTile);	
			customeWaitTime(2);
			waitForJsProcess();
			if(globalModalInfoOkButton.isDisplayed()){
				System.out.println("Device not supported");
			}
			customeWaitTime(12);	
		} catch (Exception e) {
			System.out.println("Unable to go to the items bank page");
		}
		return new ItemsBank(driver);
	}
	
	public TestsBank goToTestsBank() {
		customeWaitTime(2);		
		try {
			waitForElementAndDoubleClick(testbankTile);		
			customeWaitTime(2);
			waitForJsProcess();
			if(globalModalInfoOkButton.isDisplayed()){
				System.out.println("Device not supported");
			}
		} catch (Exception e) {
			System.out.println("Unable to go to the tests bank page");
		}
		return new TestsBank(driver);
	}
	
	
	public ItemImport goToItemImport() {
		customeWaitTime(2);		
		try {
			waitForElementAndDoubleClick(ItemsImportTile);	
			customeWaitTime(2);
			waitForJsProcess();
			if(globalModalInfoOkButton.isDisplayed()){
				System.out.println("Device not supported");
			}
			customeWaitTime(2);
		} catch (Exception e) {
			System.out.println("Unable to go to the Item Import page");
		}
		return new ItemImport(driver);
	}
	
	public Role goToRole() {
		customeWaitTime(2);		
		try {
			waitForElementAndDoubleClick(roleTile);	
			customeWaitTime(2);
			if(globalModalInfoOkButton.isDisplayed()){
				System.out.println("Device not supported");
			}
		} catch (Exception e) {
			System.out.println("Unable to go to the Role page");
		}
		return new Role(driver);
	}
	
	public Domain goToDomain() {
		try {
			customeWaitTime(2);
			customeWaitTime(2);
			String dashboardUrl = driver.getCurrentUrl();
			driver.navigate().to(dashboardUrl.replace("dashboard", "domain"));
			customeWaitTime(2);
			customeWaitTime(2);
			refreshPage();
			customeWaitTime(2);
			customeWaitTime(2);
			customeWaitTime(2);
			customeWaitTime(2);
			if (resultListCount.isDisplayed()) {
				System.out.println(" Domain page is loaded successfully");
			} else {

				System.out.println(" Domain page is  not loaded ");
			}

		} catch (Exception e) {
			System.out.println("Unable to go to the Domain page");
		}
		return new Domain(driver);

	}
	
	public Media goToMedia() {
		customeWaitTime(2);		
		try {
			waitForElementAndDoubleClick(mediaTile);	
			customeWaitTime(2);
			if(globalModalInfoOkButton.isDisplayed()){
				System.out.println("Device not supported");
			}
		} catch (Exception e) {
			System.out.println("Unable to go to the Media page");
		}
		return new Media(driver);
	}
	
	public ScoreProfile goToScoreProfile() {
		customeWaitTime(2);		
		try {
			waitForElementAndDoubleClick(scoreProfileTile);	
			customeWaitTime(2);
			waitForJsProcess();
			if(globalModalInfoOkButton.isDisplayed()){
				System.out.println("Device not supported");
			}
		} catch (Exception e) {
			System.out.println("Unable to go to the Score Profile page");
		}
		return new ScoreProfile(driver);
	}
	
	public Passage goToPassage() {
		customeWaitTime(2);		
		try {
			waitForElementAndDoubleClick(passageTile);	
			customeWaitTime(2);
			waitForJsProcess();
			if(globalModalInfoOkButton.isDisplayed()){
				System.out.println("Device not supported");
			}
		} catch (Exception e) {
			System.out.println("Unable to go to the Passage page");
		}
		return new Passage(driver);
	}
	
	
	public Permission goToPermission() {
		customeWaitTime(2);		
		try {
			waitForElementAndDoubleClick(permissionTile);	
			customeWaitTime(2);
			waitForJsProcess();
			if(globalModalInfoOkButton.isDisplayed()){
				System.out.println("Device not supported");
			}
		} catch (Exception e) {
			System.out.println("Unable to go to the Permission page");
		}
		return new Permission(driver);
	}
	
	public SisImport goToSisImport() {
		customeWaitTime(2);		
		try {
			waitForElementAndDoubleClick(sisImportTile);	
			customeWaitTime(2);
			waitForJsProcess();
			if(globalModalInfoOkButton.isDisplayed()){
				System.out.println("Device not supported");
			}
		} catch (Exception e) {
			System.out.println("Unable to go to the Sis Import page");
		}
		return new SisImport(driver);
	}
	

}
