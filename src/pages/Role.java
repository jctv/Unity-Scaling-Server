package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class Role extends BasePage {

	public Role(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Create Role']")
	public WebElement createRoleLink;

	@FindBy(xpath = "//td[text()='System Administrator']/..//td[@class='watable-col-add_tile']/button")
	public WebElement systemAdminAddTileButton;

	@FindBy(xpath = ".//*[@id='globalModalOKCancelSaveButton']")
	public WebElement globalModalOKCancelSaveButton;

	@FindBy(xpath = "//td[text()='System Administrator']/..//td[@class='watable-col-tiles']//li[@data-title='Item Import']")
	public WebElement systemAdminItemImportTile;

	@FindBy(xpath = "//input[@data-id='create']")
	public WebElement createPermissionItemImport;

	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement backToDashboard;

	@FindBy(id = "roleCreateName")
	public WebElement roleCreateNameField;

	@FindBy(id = "roleCreateSubmit")
	public WebElement roleCreateButton;

	@FindBy(id = "roleCreateCancel")
	public WebElement roleCreateCancelButton;

	public void enableTile(String id) {
		try {
			waitForElementAndClick(systemAdminAddTileButton);
			waitTime();
			WebElement tileId = driver.findElement(By
					.xpath("//input[@data-id='" + id + "']"));
			if (!tileId.isSelected()) {
				tileId.click();
			}
			waitForElementAndClick(globalModalOKCancelSaveButton);

		} catch (Exception e) {
			System.out.println("Unable to select  the Tile ");

		}

	}

	public void enableCreatePermissionItemImportTile() {
		try {

			waitForElementAndClick(systemAdminItemImportTile);
			waitForElementVisible(createPermissionItemImport);
			if (!createPermissionItemImport.isSelected()) {
				waitTime();
				createPermissionItemImport.click();
			}

			waitForElementAndClick(globalModalOKCancelSaveButton);
		} catch (Exception e) {

			System.out
					.println("Unable to select  Create permission for the Item Import Tile ");

		}

	}

	public void createRole(String roleName) {
		try {
			waitForElementAndClick(createRoleLink);
			waitTime();
			waitTime();
			waitForElementAndSendKeys(roleCreateNameField, roleName);
			waitTime();
			waitForElementAndClick(roleCreateButton);

		} catch (Exception e) {
			System.out.println("Role " + roleName + " is not created ");

		}

	}

	public void deleteRole(String roleName) {
		try {
			waitTime();
			WebElement roleDeleteButton = driver.findElement(By
					.xpath("//td[@class='watable-col-name' and text () ='"
							+ roleName
							+ "']/../td[@class='watable-col-del']/button"));
			waitTime();
			waitForElementAndClick(roleDeleteButton);

		} catch (Exception e) {
			System.out.println("Unable to delte Role " + roleName);

		}

	}

	public void addPermissions(String tileName, String role, String permissions) {
		try {
			List<String> permissionsToAdd = new ArrayList<String>(
					Arrays.asList(permissions.split(",")));
			waitForElementPresenceAndClick("//tr[./td[text()='" + role
					+ "']]//span[text()='" + tileName + "']");
			for (String permission : permissionsToAdd) {
				waitForElementPresenceAndClick("//input[@data-id='" + permission
						+ "']");
			}
			waitForElementAndClick(globalModalOKCancelSaveButton);
		} catch (Exception e) {
			System.out.println("Unable to add permissions to the tile " + tileName);
		}
		
	}

	public void enableTileByRole(String role, String tileName) {
		try {
			waitForElementPresenceAndClick("//tr[./td[text()='" + role
					+ "']]//button[@class='btn btn-link addTile']");
			waitForElementPresenceAndClick("//input[@data-id = '" + tileName + "']");
			waitForElementAndClick(globalModalOKCancelSaveButton);
			System.out.println("Tile " + tileName + " added to the  " + role);
		} catch (Exception e) {
			System.out.println("Unable to add the tile  " + tileName);
		}
		

	}
}
