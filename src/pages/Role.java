package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class Role extends BasePage{

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
	
	
	public void enableTile(String id ){
		try{
			waitForElementAndClick(systemAdminAddTileButton);
			waitTime();
			WebElement tileId = driver
	                .findElement(By
	                        .xpath("//input[@data-id='"+ id +"']"));
			if(!tileId.isSelected()){
					tileId.click();
			}
			waitForElementAndClick(globalModalOKCancelSaveButton);
			
		}catch(Exception e){
			 System.out.println("Unable to select  the Tile ");

		}
		
	}
	
	public void enableCreatePermissionItemImportTile(){
		try{
			waitForElementAndClick(systemAdminItemImportTile);
			
			if(!createPermissionItemImport.isSelected()){
				waitTime();
				createPermissionItemImport.click();
			}
			waitForElementAndClick(globalModalOKCancelSaveButton);
		}catch(Exception e){
			
			 System.out.println("Unable to select  Create permission for the Item Import Tile ");

		}
		
	}
	
}
