package pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class SisImport extends BasePage{
	
	@FindBy(xpath = "//span[text()='Import']")
	public WebElement importLink;
	
	@FindBy(xpath = "//span[text()='SIS Files']")
	public WebElement sisFileFilter;
	
	@FindBy(xpath = "//span[text()='Status']")
	public WebElement statusFilter;
	
	@FindBy(xpath = "//span[text()='Organization']/../i")
	public WebElement organizationFilter;
	
	@FindBy(xpath = "//span[text()='Staff']/../i")
	public WebElement staffFilter;
	
	@FindBy(xpath = "//span[text()='Student']/../i")
	public WebElement studentFilter;
	
	@FindBy(xpath = "//span[text()='Registration']/../i")
	public WebElement registrationFilter;
	
	@FindBy(xpath = "//span[text()='Course']/../i")
	public WebElement courseFilter;
	
	@FindBy(xpath = "//span[text()='In Progress']/../i")
	public WebElement inProgressStatus;
	
	@FindBy(xpath = "//span[text()='Completed']/../i")
	public WebElement completedStatus;
	
	@FindBy(xpath = "//span[text()='Failed']/../i")
	public WebElement failedStatus;
	
	@FindBy(xpath = "//tbody[@class='files']//td[1]/i")
	public WebElement sisImportSuccessfulIcon;
	
	@FindBy(xpath = "//td[@class='watable-col-package_file_name']")
	public WebElement sisFilePackageName;
	
	@FindBy(xpath = "//td[@class='watable-col-sis_file_names']")
	public WebElement sisImportedFiles;
	
	@FindBy(xpath = "//td[@class='watable-col-status']")
	public WebElement sisImportedFilestatus;
	
	@FindBy(xpath = "//td[@class='watable-col-status_detail']")
	public WebElement sisImportedFileDetail;
	
	@FindBy(xpath = "//td[text()='File: organization.csv']")
	public WebElement fileNameOnSummery;
	
	@FindBy(xpath = "//td[text()='teacher-to-class']/following-sibling::td")
	public WebElement teacherToClassCount;
	
	@FindBy(xpath = "//td[text()='school']/following-sibling::td")
	public WebElement schoolCount;
	
	@FindBy(xpath = "//td[text()='state']/following-sibling::td")
	public WebElement stateCount;
	
	@FindBy(xpath = "//td[text()='roster']/following-sibling::td")
	public WebElement rosterCount;
	
	@FindBy(xpath = "//td[text()='district']/following-sibling::td")
	public WebElement districtCount;
	
	@FindBy(xpath = "//td[text()='student-to-class']/following-sibling::td")
	public WebElement studentToClassCount;
	
	@FindBy(xpath = "//td[text()='user']/following-sibling::td")
	public WebElement userCount;
	
	
	public SisImport(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	
	
	/**
	 * This is the method for sis import
	 * @param filepath
	 * @return
	 */
	public boolean sisImport(String filepath){
		boolean isImportSuccessful = false ;
		try{
			File f = new File(filepath);
			String SisImportFilepath = f.getAbsolutePath();
			waitForElementAndClick(importLink);
			customeWaitTime(5);
			fileupload.sendKeys(SisImportFilepath);
			customeWaitTime(10);
			if(sisImportSuccessfulIcon.getAttribute("style").contains("green")){
				isImportSuccessful = true;
		    }
			customeWaitTime(10);
		}catch(Exception e){
			
			System.out.println("Unable to import the sis file  -->  "  + filepath);
		}
		return isImportSuccessful;
	}
}
