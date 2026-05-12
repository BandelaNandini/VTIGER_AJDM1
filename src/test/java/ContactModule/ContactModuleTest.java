package ContactModule;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseclassUtility.Baseclass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import ListenersUtility.UtilityObjectClass;
import POMUtilities.ConInfoPomPage;
import POMUtilities.ConPomPage;
import POMUtilities.CreateConPomPage;
import POMUtilities.CreateOrgPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.OrgInfoPomPage;
import POMUtilities.OrgPomPage;

@Listeners(ListenersUtility.Listeners.class)
public class ContactModuleTest extends Baseclass {
	@Test(groups = "smoke", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createCon_test() throws InterruptedException, IOException {

		// Fetch random integer
		UtilityObjectClass.getTest().log(Status.INFO, "Fetching random integer");
		JavaUtility jutil = new JavaUtility();
		int randomnum = jutil.fetchRandomInt();

		// Fetch data from Excel File
		UtilityObjectClass.getTest().log(Status.INFO, "Fetching data from Excel File");
		ExcelFileUtility exutil = new ExcelFileUtility();
		String contactname = exutil.fetchDataFromExcelFile("ConData", 1, 3) + randomnum;

		// Verify Home Page
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "Validating home header");
		soft.assertEquals(driver.getCurrentUrl(), "http://localhost:8888/index.php?action=index&module=Home",
				"Validating home header");
		UtilityObjectClass.getTest().log(Status.INFO, "Verified Home Page");

		// Identify contact tab and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "Identify contact tab and click on it");
		HomePomPage home = new HomePomPage(driver);
		home.getConTab();

		// Identify plus icon and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "Identify plus icon and click on it");
		ConPomPage con = new ConPomPage(driver);
		con.getConPlusicon();

		// Identify org name TF and pass org name
		UtilityObjectClass.getTest().log(Status.INFO, "Identify org name TF and pass org name");
		CreateConPomPage createcon = new CreateConPomPage(driver);
		createcon.getLastnameTF(contactname);

		// Identify save button and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "Identify save button and click on it");
		createcon.getSavebtn();

		// Verify contact name in contact info page
		ConInfoPomPage coninfo = new ConInfoPomPage(driver);
		String infoheader = coninfo.getConinfoheader();
		Assert.assertTrue(infoheader.contains(contactname), "Validating created contact name");
		UtilityObjectClass.getTest().log(Status.PASS, "Verify contact name in contact info page");

		// Identify contact tab and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "Identify contact tab and click on it");
		home.getConTab();

		// Identify del button for the created org name
		// Dynamic xpath
		UtilityObjectClass.getTest().log(Status.INFO, "Identify del button for the created org name");
		driver.findElement(By.xpath("//a[text()='" + contactname + "']/../../descendant::a[text()='del']")).click();
		Thread.sleep(4000);

		// Handle alert popup and click on ok button
		UtilityObjectClass.getTest().log(Status.INFO, "Handle alert popup and click on ok button");
		wutil.handleAlertClickOnOk(driver);

		// Close the excel
		UtilityObjectClass.getTest().log(Status.INFO, "Close the excel");
		exutil.closeExcel();
		soft.assertAll();
	}

	@Test(groups = "regression", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createConWithOrg_test() throws InterruptedException, IOException {

		// Fetch random integer
		UtilityObjectClass.getTest().log(Status.INFO, "Fetching random integer");
		JavaUtility jutil = new JavaUtility();
		int randomnum = jutil.fetchRandomInt();

		// Fetch data from Excel File
		UtilityObjectClass.getTest().log(Status.INFO, "Fetching data from Excel File");
		ExcelFileUtility exutil = new ExcelFileUtility();
		String contactname = exutil.fetchDataFromExcelFile("ConData", 7, 3) + randomnum;
		String Orgname = exutil.fetchDataFromExcelFile("ConData", 7, 4) + randomnum;

		// Validate Home page
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(driver.getCurrentUrl(), "http://localhost:8888/index.php?action=index&module=Home",
				"Validating home header");
		UtilityObjectClass.getTest().log(Status.INFO, "Verified Home Page");

		// Identify organization tab and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "Identify org tab and click on it");
		HomePomPage home = new HomePomPage(driver);
		home.getOrgTab();

		// Identify plus icon and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "Identify plus icon and click on it");
		OrgPomPage org = new OrgPomPage(driver);
		org.getOrgPlusicon();

		// Identify org name TF and pass org name
		UtilityObjectClass.getTest().log(Status.INFO, "Identify org name TF and pass org name");
		CreateOrgPomPage createOrg = new CreateOrgPomPage(driver);
		createOrg.getOrgnameTF(Orgname);

		// Identify save button and click on it
		createOrg.getSaveBtn();
		UtilityObjectClass.getTest().log(Status.INFO, "Identified save button and clicked on it");

		// Verify org name in org info page
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String infoheader = orginfo.getOrginfoHeader();
		Assert.assertTrue(infoheader.contains(Orgname), "Validating org name");
		UtilityObjectClass.getTest().log(Status.PASS, "Verify org name in org info page");

		// Identify contact tab and click on it
		UtilityObjectClass.getTest().log(Status.PASS, "Identify contact tab and click on it");
		home.getConTab();

		// Identify plus icon and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "Identify plus icon and click on it");
		ConPomPage con = new ConPomPage(driver);
		con.getConPlusicon();

		// Identify org name TF and pass cont name
		UtilityObjectClass.getTest().log(Status.INFO, "Identify org name TF and pass cont name");
		CreateConPomPage createCon = new CreateConPomPage(driver);
		createCon.getLastnameTF(contactname);

		// Click on org plus icon
		UtilityObjectClass.getTest().log(Status.INFO, "Click on org plus icon");
		createCon.getOrgplusicon();

		// Fetch the parent window id
		UtilityObjectClass.getTest().log(Status.INFO, "Fetch the parent window id");
		String pwid = wutil.fetchWindoWID(driver);

		// Switch the driver control to child window
		UtilityObjectClass.getTest().log(Status.INFO, "Switch the driver control to child window");
		wutil.switchToChildWindow_url(driver, "module=Accounts&action");

		// Searching orgname
		UtilityObjectClass.getTest().log(Status.INFO, "Searching orgname");
		createCon.getOrgsearchTF(Orgname);
		createCon.getOrgsearchbtn();
		driver.findElement(By.xpath("//a[text()='" + Orgname + "']")).click();

		// Switch back to parent window
		UtilityObjectClass.getTest().log(Status.INFO, "Switch back to parent window");
		wutil.switchToParentWindow(driver, pwid);

		// Identify save button and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "Identify save button and click on it");
		createCon.getSavebtn();

		// Verify contact name in contact info page
		ConInfoPomPage coninfo = new ConInfoPomPage(driver);
		String coninfoheader = coninfo.getConinfoheader();
		Assert.assertTrue(coninfoheader.contains(contactname), "Validating contact name");
		UtilityObjectClass.getTest().log(Status.PASS, "Verify contact name in contact info page");

		// Verify org name in contact info page
		String verifyOrg = coninfo.getVerifyOrgname();
		Assert.assertTrue(verifyOrg.contains(Orgname), "Validating org name in contact info page");
		UtilityObjectClass.getTest().log(Status.PASS, "Verify org name in contact info page");

		// Identify contact tab and click on it
		UtilityObjectClass.getTest().log(Status.INFO, " Identify contact tab and click on it");
		home.getConTab();

		// Identify del button for the created org name and delete
		// Dynamic xpath
		UtilityObjectClass.getTest().log(Status.INFO, "Identify del button for the created org name and delete");
		driver.findElement(By.xpath("//a[text()='" + contactname + "']/../../descendant::a[text()='del']")).click();

		Thread.sleep(2000);

		// Handle alert popup and click on ok button
		UtilityObjectClass.getTest().log(Status.INFO, "Handle alert popup and click on ok button");
		wutil.handleAlertClickOnOk(driver);

		// Identify organization tab and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "Identify organization tab and click on it");
		home.getOrgTab();

		// Identify del button for the created org name
		// Dynamic xpath
		UtilityObjectClass.getTest().log(Status.INFO, "Identify del button for the created org name and delete");
		driver.findElement(
				By.xpath("//a[text()='" + Orgname + "' and @title='Organizations']/../../descendant::a[text()='del']"))
				.click();

		Thread.sleep(2000);

		// Handle alert popup and click on ok button
		UtilityObjectClass.getTest().log(Status.INFO, "Handle alert popup and click on ok button");
		wutil.handleAlertClickOnOk(driver);

		// Close the excel
		UtilityObjectClass.getTest().log(Status.INFO, "Close the excel");
		exutil.closeExcel();
		soft.assertAll();

	}

	@Test(groups = "regression", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createConWithSuppDate_test() throws InterruptedException, IOException {

		// Fetch random integer
		UtilityObjectClass.getTest().log(Status.INFO, "Fetch random integer");
		JavaUtility jutil = new JavaUtility();
		int randomnum = jutil.fetchRandomInt();

		// Fetch data from Excel File
		UtilityObjectClass.getTest().log(Status.INFO, "Fetch data from excel");
		ExcelFileUtility exutil = new ExcelFileUtility();
		String contactname = exutil.fetchDataFromExcelFile("ConData", 4, 3) + randomnum;

		// Validate Home page
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(driver.getCurrentUrl(), "http://localhost:8888/index.php?action=index&module=Home",
				"Validating home header");
		UtilityObjectClass.getTest().log(Status.INFO, "Validating home page");

		// Identify contact tab and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "click on contact tab");
		HomePomPage home = new HomePomPage(driver);
		home.getConTab();

		// Identify plus icon and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "click on plus icon");
		ConPomPage con = new ConPomPage(driver);
		con.getConPlusicon();

		// Identify last name TF and pass con name
		UtilityObjectClass.getTest().log(Status.INFO, "Enter contact name");
		CreateConPomPage createcon = new CreateConPomPage(driver);
		createcon.getLastnameTF(contactname);

		// Fetch start date
		UtilityObjectClass.getTest().log(Status.INFO, "Fetch start date");
		String startdate = jutil.fetchCurrentDate();
		Reporter.log(startdate);

		// Identify SuppStartDate TF and pass start date
		UtilityObjectClass.getTest().log(Status.INFO, "Enter start date");
		createcon.getSuppStartdateTF(startdate);

		// Fetch the end date
		UtilityObjectClass.getTest().log(Status.INFO, "Fetch end date");
		String enddate = jutil.fetchDateAfterGivenDays(30);
		Reporter.log(enddate);

		// Identify SuppEndDate TF and pass end date
		UtilityObjectClass.getTest().log(Status.INFO, "Enter end date");
		createcon.getSuppEnddateTF(enddate);

		// Identify save button and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "click on save button");
		createcon.getSavebtn();

		// Verify contact name in contact info page
		ConInfoPomPage coninfo = new ConInfoPomPage(driver);
		String infoheader = coninfo.getConinfoheader();
		Assert.assertTrue(infoheader.contains(contactname), "Verify contact name in contact info page");
		UtilityObjectClass.getTest().log(Status.PASS, "Contact name verified");

		// Verify supp start date in contact info page
		String verifyStartDate = coninfo.getVerifyStartdate();
		Assert.assertTrue(verifyStartDate.contains(startdate), "Verify StartDate in contact info page");
		UtilityObjectClass.getTest().log(Status.PASS, "Verified start date");

		// Verify supp end date in contact info page
		String verifyEndDate = coninfo.getVerifyEnddate();
		Assert.assertTrue(verifyEndDate.contains(enddate), "Verify EndDate in contact info page");
		UtilityObjectClass.getTest().log(Status.PASS, "Verified enddate");

		// Identify contact tab and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "click on contact tab");
		home.getConTab();

		// Identify del button for the created con name
		// Dynamic xpath
		UtilityObjectClass.getTest().log(Status.INFO, "Delete contact");
		driver.findElement(By.xpath("//a[text()='" + contactname + "']/../../descendant::a[text()='del']")).click();

		Thread.sleep(2000);

		// Handle alert popup and click on ok button
		UtilityObjectClass.getTest().log(Status.INFO, "handle alert popup");
		wutil.handleAlertClickOnOk(driver);

		// Close the excel
		UtilityObjectClass.getTest().log(Status.INFO, "close excel");
		exutil.closeExcel();
		soft.assertAll();

	}

}
