package pages;

import generic.BasePage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MachineProfile extends BasePage{
	
	
	
public MachineProfile(WebDriver driver){
super(driver);
PageFactory.initElements(driver, this);
}

@FindBy(xpath = "//span[@class='link' and text() ='Create']")
public WebElement createProfileButton;

@FindBy(id = "machineProfileCreateInputName")
public WebElement machineProfileCreateInputName;

@FindBy(id = "machineProfileCreateInputTile")
public WebElement machineProfileCreateInputTile;

@FindBy(id = "machineProfileCreateInputDeviceType")
public WebElement machineProfileCreateInputDeviceType;

@FindBy(id = "machineProfileCreateInputScreenRes")
public WebElement machineProfileCreateInputScreenRes;

@FindBy(id = "machineProfileCreateInputOS")
public WebElement machineProfileCreateInputOS;

@FindBy(id = "machineProfileCreateInputVersion")
public WebElement machineProfileCreateInputVersion;

@FindBy(id = "machineProfileCreateInputBrowser")
public WebElement machineProfileCreateInputBrowser;

@FindBy(id = "machineProfileCreateInputSubmit")
public WebElement createButton;

@FindBy(xpath = "(//td[@class='watable-col-name'])[last()]")
public WebElement lastAddedProfile;



@FindBy(id = "name")
public WebElement nameUpdateField;

@FindBy(id = "tile")
public WebElement tileUpdateField;

@FindBy(id = "deviceType")
public WebElement deviceTypeUpdateField;

@FindBy(id = "screenResolution")
public WebElement screenResolutionUpdateField;

@FindBy(id = "browser")
public WebElement browserUpdateField;

@FindBy(id = "version")
public WebElement versionUpdateField;

@FindBy(xpath = "//*[@id='globalModalViewBody']/div/form/div[8]/div/button")
public WebElement saveUpdatesButton;

@FindBy(xpath = "(//button[@title='Delete'])[last()]")
public WebElement deletLastIcon;


@FindBy(id = "globalModalDeleteButton")
public WebElement globalModalDeleteButton;







public String createProfile(String name, String tile, String type, String resolution, String system, String browser, String version){
	waitForElementAndClick(createProfileButton);
	waitForElementAndSendKeys(machineProfileCreateInputName, name);
	waitForElementAndSendKeys(machineProfileCreateInputTile, tile);
	waitForElementAndSendKeys(machineProfileCreateInputDeviceType, type);
	waitForElementAndSendKeys(machineProfileCreateInputScreenRes, resolution);
	waitForElementAndSendKeys(machineProfileCreateInputOS, system);
	waitForElementAndSendKeys(machineProfileCreateInputBrowser, browser);
	waitForElementAndSendKeys(machineProfileCreateInputVersion, version);
	waitForElementAndClick(createButton);
	waitForElementAndClick(globalModalInfoOkButton);
	
	return lastAddedProfile.getText();
	
}
public String editProfile(String name, String tile, String type, String resolution, String browser, String version){
	
	waitForElementAndSendKeys(searchAutoComplete, name);
	searchButton.click();
	waitForElementAndClick(editLastIcon);
	
	
	waitForElementAndSendKeys(nameUpdateField, name);
	nameUpdateField.clear();
	waitForElementAndSendKeys(tileUpdateField, tile);
	screenResolutionUpdateField.clear();
	waitForElementAndSendKeys(screenResolutionUpdateField, resolution);
	waitForElementAndSendKeys(deviceTypeUpdateField, type);
	waitForElementAndSendKeys(browserUpdateField, browser);
	versionUpdateField.clear();
	waitForElementAndSendKeys(versionUpdateField, version);
	
	waitForElementAndClick(saveUpdatesButton);
	waitForElementAndClick(globalModalInfoOkButton);
	waitTime();
	return lastAddedProfile.getText();
	
	
}

public String deleteProfile(String name){
	
	waitForElementAndSendKeys(searchAutoComplete, name);
	searchButton.click();
	waitTime();
	waitForElementAndClick(deletLastIcon);

	waitForElementAndClick(globalModalDeleteButton);
	waitTime();
	return "deleted";
	
}


}

