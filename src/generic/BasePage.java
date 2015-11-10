package generic;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.DashBoard;
import pages.Login;

public class BasePage {

	protected WebDriver driver;

	@FindBy(xpath = "//*[@id='quickViewHelp']/a")
	public WebElement addHelpContent;

	@FindBy(id = "tinymce")
	public WebElement helpContentBox;

	@FindBy(id = "globalModalInfoOkButton")
	public WebElement globalModalInfoOkButton;

	@FindBy(xpath = "(//i[@class='fa fa-edit'])[last()]")
	public WebElement editLastIcon;

	@FindBy(id = "searchAutoComplete")
	public WebElement searchAutoComplete;

	@FindBy(id = "searchButton")
	public WebElement searchButton;

	@FindBy(id = "resetSearchFilter")
	public WebElement resetSearchFilter;

	public boolean validator = false;

	public String variable = "";

	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[2]/div/div/div[3]/table/tfoot/tr/td/div/div[1]/ul/li[7]/a")
	public WebElement nextPageButton;

	@FindBy(xpath = "//*[@id='region-workspace']/div/div/div[2]/div/div/div[3]/table/tfoot/tr/td/div/div[1]/ul/li[1]/a")
	public WebElement backPageButton;

	@FindBy(xpath = "//*[@id='region-navigation']/div/a")
	public WebElement dashBoardPage;





	/** Constructor */
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void waitTime() {
		try {
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean waitForElementVisible(WebElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);

			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR] Issue waiting for element"
					+ e.getMessage());
			return false;
		}
	}

	public void dragAndDrop(WebElement item, WebElement target) {
		waitTime();
		try {
			(new Actions(driver)).dragAndDrop(item, target).perform();
		} catch (Exception e) {

			System.out.println("Drag and Drop Failed");
		}
	}

	public boolean addHelp(WebElement helpLink) {
		waitTime();

		try {
			helpLink.click();
			addHelpContent.click();
			waitTime();
			helpContentBox.sendKeys("This is a test ");
			addHelpContent.click();
			helpLink.click();
			helpLink.click();
			if (helpContentBox.getText().equals("This is a test ")) {
				validator = true;
			}
		} catch (Exception e) {
			System.out.println("Unable to add help Content");
		}
		return validator;
	}

	public boolean searchFilters(List<WebElement> allElements,
			WebElement column, String criteria) {
		try {
			waitTime();
			searchAutoComplete.sendKeys(Keys.CONTROL + "a");
			searchAutoComplete.sendKeys(Keys.DELETE);
			for (WebElement element : allElements) {
				element.click();

				if (criteria != " ") {
					element.sendKeys(criteria);
					element.sendKeys(Keys.ENTER);

					if (element.getText().contains(column.getText())) {
						validator = true;
						System.out.println("Festing filter " + criteria);

					} else {
						validator = false;
						System.out.println("Failed filter " + criteria);
					}

				} else {

					if (element.getText().contains(column.getText())) {
						validator = true;
						System.out.println("Festing filter "
								+ element.getText());
					} else {
						validator = false;
						System.out
								.println("Failed filter " + element.getText());
					}
				}
				resetSearchFilter.click();
			}
		} catch (Exception e) {
			System.out.println("Unable locate the search filters");
			validator = false;
		}
		return validator;
	}

	public boolean navigateNetxPage() {
		try {
			nextPageButton.click();
			validator = true;
		} catch (Exception e) {
			System.out.println("Unable to navigate next page");
		}
		return validator;
	}

	public boolean navigateBackPage() {
		try {
			backPageButton.click();
			validator = true;
		} catch (Exception e) {
			System.out.println("Unable to back page");
		}
		return validator;
	}

	public void waitForElementAndClick(final WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			waitTime();
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			System.out.println("Button clicked ");

		} catch (Exception e2) {
			System.out.println("Unable to perform the click on element "
					+ element.getText());

		}

	}

	public void waitForElementAndDoubleClick(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			(new Actions(driver)).doubleClick(element).perform();

			System.out.println("Button double clicked ");
			waitTime();
		} catch (Exception e) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element));
				(new Actions(driver)).doubleClick(element).perform();
			} catch (Exception e2) {

				System.out
						.println("Unable to perform the double click on element "
								+ element.getText());

			}
		}

	}

	public void waitForElementAndSendKeys(WebElement element, String keys) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {

			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			element.sendKeys(keys);
			waitTime();
			System.out.println("Keys send  " + keys);
		} catch (Exception e) {
			System.out.println("Unable to Send Keys  " + keys);
		}

	}

	public void clearSearchFilter() {

		searchAutoComplete.sendKeys(Keys.CONTROL + "a");

		searchAutoComplete.sendKeys(Keys.DELETE);
	}

	public void selectOption(WebElement dropDownListBox, String option) {
		try {
			Select droplist = new Select(dropDownListBox);
			droplist.selectByVisibleText(option);
		} catch (Exception e) {

			System.out.println("Unable to find select option element");
		}

	}

	public void takeScreenShot() {
		try {
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("D:\\screenshot.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("takeScreenShot method failed  "
					+ e.getMessage());
		}
	}

	public DashBoard backToDashboard() {
		waitForElementAndClick(dashBoardPage);
		waitTime();
		return new DashBoard(driver);
	}

	public void waitAndFocus(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			new Actions(driver).moveToElement(element).perform();

		} catch (Exception e) {
			System.out.println("Unable to focus element");
		}

	}

	/**
	 * This is overloaded method to select drop down by index
	 *
	 * @param element
	 * @param index
	 */
	public void selectOption(WebElement element, int index) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			Select mySelect = new Select(element);
			mySelect.selectByIndex(index);
			System.out.println("Select option   " + element.getText());
		} catch (Exception e) {
			System.out.println("Select option  " + element.getText());
		}

	}

	public List<WebElement> getDropDownOptions(WebElement dropDownBox) {
		List<WebElement> options = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(dropDownBox));
			Select mySelect = new Select(dropDownBox);
			options = mySelect.getOptions();

		} catch (Exception e) {
			System.out.println("Unable to get the Drop down values");
		}

		return options;
	}



	public WebElement getSelectedOption(WebElement dropDownBox){
		WebElement options = null;
		try{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(dropDownBox));
			Select mySelect = new Select(dropDownBox);
			options = mySelect.getFirstSelectedOption();

		}catch(Exception e){
			System.out.println("Unable to get the selected option");
		}

		return options;
	}

	public void refreshPage(){
		driver.navigate().refresh();
		waitTime();
	}


}
