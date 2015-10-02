package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;

import generic.BaseTest;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.ClassRoster;
import pages.DashBoard;
import pages.Delivery;
import pages.HandScoring;
import pages.Items;
import pages.ItemsBank;
import pages.Login;
import pages.Organization;
import pages.Reports;
import pages.Schedule;
import pages.TestCreation;
import pages.TestsBank;
import pages.Users;

public class ReportTest extends BaseTest {

	HappyPathTest Nav;
	public String user = "tdemo28";
	public String genericPassword = "12345";
	Login loginPageObject;
	DashBoard dashBoardPageObject;
	Items itemsPageObject;
	Users usersPageObject;
	ClassRoster classRosterPageObject;
	TestCreation testCreationPageObject;
	Schedule sechedulePageObject;
	Delivery deliveryPageObject;
	HandScoring handScoringPageObject;
	Reports reportsPageObject;
	Organization organizationPageObject;
	ItemsBank itemsBankPageObject;
	TestsBank testBankPageObject;

	public ReportTest() {
		super();

	}

	@BeforeMethod
	public void setUp() {

		driver.get(url);

		loginPageObject = new Login(driver);
		System.out.println("******** logging as super administrator ********");

		dashBoardPageObject = loginPageObject.loginSuccess(user, "12345");
		// driver.get(url + "#dashboard");
		waitTime();

		// System.out.println(dashBoardPageObject.addTiles());

	}

	@Test
	public void runPath() {

		waitTime();
		/*
		 * File file = new File("log.txt"); FileOutputStream fos; try { fos =
		 * new FileOutputStream(file); PrintStream ps = new PrintStream(fos);
		 * System.setOut(ps); } catch (FileNotFoundException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		try {

			String csvFile = "C:/Users/juan.tribin/Desktop//ReportsUsers.txt";
			String csvFileInputs = "C:/Users/juan.tribin/Desktop//inputs.csv";
			BufferedReader br = null;
			BufferedReader br2 = null;
			String line = "";
			String[] rosters = null;
			String[] inputs = null;
			ArrayList<String> rosterOne = new ArrayList<String>();
			ArrayList<String> rosterTwo = new ArrayList<String>();
			ArrayList<String> rosterThree = new ArrayList<String>();
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();

			rosters = line.split(",");
			System.out.println(line);
			
			br.close();
			
			br2 = new BufferedReader(new FileReader(csvFileInputs));
			line = br2.readLine();
			inputs = line.split(",");
			System.out.println(line);
			br2.close();
			int x = 1;

			for (String value : rosters) {

				if (x < 31) {
					rosterOne.add(value);
					System.out.println("roster one " + value);
				}

				if (x >= 31) {
					rosterTwo.add(value);
					System.out.println("roster two " + value);
				}
				if (x >= 16) {
					rosterThree.add(value);
					System.out.println("roster three " + value);
				}
				x++;
			}
			waitTime();
			//classRosterPageObject = dashBoardPageObject.goToClassRoster();
			//classRosterPageObject.createRoster(rosterOne, "Reports School", "Class 1");
			//classRosterPageObject = dashBoardPageObject.goToClassRoster();
			//classRosterPageObject.createRoster(rosterTwo, "Reports School", "Class 2");
			//classRosterPageObject = dashBoardPageObject.goToClassRoster();
			//classRosterPageObject.createRoster(rosterThree, "Reports School", "Class 3");
			for(int y=1;y<3;y++){
			//sechedulePageObject = dashBoardPageObject.goToSchedule();		
			//sechedulePageObject.scheduleTestReports("Class "+y,(y));
			//waitTime();
			
			}
			dashBoardPageObject.logOut();
			
			waitTime();
			for(int z = 0; z <= inputs.length; z++){
				
				try{
			System.out
					.println("******** logging as "+rosters[z]+" ********");
			dashBoardPageObject = loginPageObject.loginSuccess(rosters[z],
					genericPassword);
			
			System.out.println(dashBoardPageObject.addTiles());
			waitTime();
			deliveryPageObject = dashBoardPageObject.goToDelivery();
			waitTime();
			
			System.out.println("******** Taking the scheduled test ********");
			
			deliveryPageObject.takeTestReports(Integer.parseInt(inputs[z]));
			
			waitTime();
			dashBoardPageObject.logOut();
			System.out.println("************************************************");
			
				}catch(Exception e){
					System.out.println("Failed to access with " + rosters[z]);
					
				}			
			}			
			System.out.println("******** logging as the teacher ********");
			dashBoardPageObject = loginPageObject.loginSuccess(user,
					genericPassword);
			waitTime();
			reportsPageObject = dashBoardPageObject.goToReports();
			waitTime();
			reportsPageObject.viewReport();
			waitTime();
			loginPageObject = dashBoardPageObject.logOut();

		} catch (Exception e) {
			System.out.println(" Failed " + e.getMessage());
		}

	}
}