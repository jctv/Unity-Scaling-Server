package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BasePage;

public class Accommodation extends BasePage {
	public Accommodation(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//td[@class='watable-col-first_name']")
	public WebElement studentFirstNameList;
	
	@FindBy(xpath = "//td[@class='watable-col-last_name]")
	public WebElement studentLastNameList;
	
	@FindBy(xpath = "//td[@class='watable-col-state_id']")
	public WebElement studentStateIdList;
	
	@FindBy(xpath = "//td[@class='watable-col-grade']")
	public WebElement studentGradeList;
	
	@FindBy(xpath = "//span[text()='First Name']")
	public WebElement firstNameFilterNav;
	
	@FindBy(xpath = "//span[text()='First Name']/../..//input")
	public WebElement firstNameFilterInputField;
	
	@FindBy(xpath = "//span[text()='Middle Name']")
	public WebElement middleNameFilterNav;
	
	@FindBy(xpath = "//span[text()='Middle Name']/../..//input")
	public WebElement middleNameFilterInputField;
	
	@FindBy(xpath = "//span[text()='Last Name']")
	public WebElement lastNameFilterNav;
	
	@FindBy(xpath = "//span[text()='Last Name']/../..//input")
	public WebElement lastNameFilterInputField;
	
	@FindBy(xpath = "//span[text()='State ID']")
	public WebElement stateIdFilterNav;
	
	@FindBy(xpath = "//span[text()='State ID']/../..//input")
	public WebElement stateIdFilterInputField;
	
	@FindBy(css = "#globalModalView .modal-content")
	public WebElement accommodationModal;
	
	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[2]/div/div/div[3]/table/tbody/tr[1]")
	public WebElement rowOneGrid;
	
	@FindBy(id = "alternate_colors")
	public WebElement alternateColorscheckBox;
	
	@FindBy(id = "answerEliminator")
	public WebElement answerEliminatorcheckBox;
	
	@FindBy(id = "answerMasking")
	public WebElement answerMaskingcheckBox;
	
	@FindBy(id = "extendedTime")
	public WebElement extendedTimecheckBox;
	
	@FindBy(id = "linereader")
	public WebElement lineReadercheckBox;
	
	@FindBy(className = "editRow")
	public WebElement editAccommodationsButton;
	
	@FindBy(id = "magnification")
	public WebElement magnificationcheckBox;
	
	@FindBy(xpath = "//button[@class='btn btn-primary pull-right accessibility-save']")
	public WebElement accommodationSaveButton ;
	
	@FindBy(xpath = ".//*[@id='accessibility']/tr/td/div/div[1]")
	public WebElement studentNameInfo ;
	
	@FindBy(xpath = ".//*[@id='globalModalView']/div/div/div[1]/button")
	public WebElement accommodationCloseButton;
	
	@FindBy(id = "additional_testing_time_multiplier_radio")
	public WebElement additionalTestingTimeMultiplierRadioButton;
	
	@FindBy(id = "additional_testing_time_multiplie")
	public WebElement additionalTestingTimeMultiplierInputField;
	
	@FindBy(id = "additional_testing_time_hour_radio")
	public WebElement additionalTestingTimeHourRadioButton;
	
	@FindBy(id = "additional_testing_time_hour")
	public WebElement additionalTestingTimeHourInputField;
	
	@FindBy(id = "additional_testing_time_mins")
	public WebElement additionalTestingTimeMinuteInputField;
	
	@FindBy(id = "additional_testing_time_unlimited_radio")
	public WebElement additionalTestingTimeUnlimitedRadioButton;
	
	@FindBy(id = "magnification-time")
	public WebElement selectMagnificationTime;
	
	public boolean searchStudent(String criteria) {
		try {
			waitTime();
			waitForElementAndSendKeys(searchAutoComplete , criteria);
			waitTime();
			validator = rowOneGrid.isDisplayed();
		} catch (Exception e) {
			System.out.println("Record not found");
		}
		return validator;
	}
	
	public boolean filterStudent(String name , String  criteria){
		try {
			switch (criteria) {
			case "First Name":
				waitForElementAndClick(firstNameFilterNav);
				waitForElementAndSendKeys(firstNameFilterInputField, name); 
				break;
			case "Middle Name":
				waitForElementAndClick(middleNameFilterNav);
				waitForElementAndSendKeys(middleNameFilterInputField, name); 
				break;
			case "Last Name":
				waitForElementAndClick(lastNameFilterNav);
				waitForElementAndSendKeys(lastNameFilterInputField, name); 
				break;
			case "State ID":
				waitForElementAndClick(stateIdFilterNav);
				waitForElementAndSendKeys(stateIdFilterInputField, name);
				break;	
			default:
				break;
			}
			
		  validator = rowOneGrid.isDisplayed();

		} catch (Exception e) {
			System.out.println("Record not found");
		}
		return validator;	
	}
	
 public void checkAlternateColorTextBackground(boolean toBeSelected){
	 try{
		 if(toBeSelected){
			 if(!alternateColorscheckBox.isSelected()){
				waitForElementAndClick(alternateColorscheckBox);
			 }
			 
		 }else{
			 if(alternateColorscheckBox.isSelected()){
					waitForElementAndClick(alternateColorscheckBox);
				 }
		 }
	 }catch(Exception e){
		 
	 }
 }
 
 public void checkAnswerEliminator(boolean toBeSelected){
	 try{
		 if(toBeSelected){
			 if(!answerEliminatorcheckBox.isSelected()){
				waitForElementAndClick(answerEliminatorcheckBox);
			 }
			 
		 }else{
			 if(answerEliminatorcheckBox.isSelected()){
					waitForElementAndClick(answerEliminatorcheckBox);
				 }
		 }
	 }catch(Exception e){
		 
	 }
 }
 
 
 public void checkAnswerMasking (boolean toBeSelected){
	 try{
		 if(toBeSelected){
			 if(!answerMaskingcheckBox.isSelected()){
				waitForElementAndClick(answerMaskingcheckBox);
			 }
			 
		 }else{
			 if(answerMaskingcheckBox.isSelected()){
					waitForElementAndClick(answerMaskingcheckBox);
				 }
		 }
	 }catch(Exception e){
		 
	 }
 }
 
 
 public void checkLineReader(boolean toBeSelected){
	 try{
		 if(toBeSelected){
			 if(!lineReadercheckBox.isSelected()){
				waitForElementAndClick(lineReadercheckBox);
			 }
			 
		 }else{
			 if(lineReadercheckBox.isSelected()){
					waitForElementAndClick(lineReadercheckBox);
				 }
		 }
	 }catch(Exception e){
		 
	 }
 }
 
 public void checkAddExtendedTime(boolean toBeSelected){
	 try{
		 if(toBeSelected){
			 if(!extendedTimecheckBox.isSelected()){
				waitForElementAndClick(extendedTimecheckBox);
			 }
			 
		 }else{
			 if(extendedTimecheckBox.isSelected()){
					waitForElementAndClick(extendedTimecheckBox);
				 }
		 }
	 }catch(Exception e){
		 
	 }
 }
 
 
 public void addextendedTimeMultier(String multiplier){
	    try{
		waitForElementAndClick(additionalTestingTimeMultiplierRadioButton);
		waitForElementAndSendKeys(additionalTestingTimeMultiplierInputField , multiplier);

	    }catch(Exception e){
	    	//TODO
	    }
 }
 
 
 public void addextendedTimeHoursAndMinate(String hours , String minute){
	    try{
		waitForElementAndClick(additionalTestingTimeHourRadioButton);
		waitForElementAndSendKeys(additionalTestingTimeMultiplierInputField , hours);
		waitForElementAndSendKeys(additionalTestingTimeMultiplierInputField , minute);

	    }catch(Exception e){
	    	//TODO
	    }
}
 
 public void addextendedTimeUnlimited(){
	    try{
		waitForElementAndClick(additionalTestingTimeUnlimitedRadioButton);
	    }catch(Exception e){
	    	//TODO
	    }
}
 
 public void saveAccommodationAssignment(){
	 waitForElementAndClick(accommodationSaveButton);
	 waitForElementAndClick(globalModalInfoOkButton);
	 
 }
	
}
