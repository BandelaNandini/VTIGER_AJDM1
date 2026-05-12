package OrganizationModule;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseclassUtility.Baseclass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import ListenersUtility.UtilityObjectClass;
import POMUtilities.CreateOrgPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.OrgInfoPomPage;
import POMUtilities.OrgPomPage;

@Listeners(ListenersUtility.Listeners.class)
public class OrgModuleTest extends Baseclass {
	@Test(groups = "smoke", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createOrg_test() throws InterruptedException, IOException {

		// Fetch random integer
		UtilityObjectClass.getTest().log(Status.INFO, "Fetch randon number");
		JavaUtility jutil = new JavaUtility();
		int randomnum = jutil.fetchRandomInt();

		// Fetch data from Excel File
		UtilityObjectClass.getTest().log(Status.INFO, "Fetch data from excel");
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcelFile("OrgData", 1, 3) + randomnum;

		// Validate Home page
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "Validating home header");
		UtilityObjectClass.getTest().log(Status.INFO, "Home page validate using soft assert");

		// Identify organization tab and click on it
		HomePomPage home = new HomePomPage(driver);
		home.getOrgTab();
		UtilityObjectClass.getTest().log(Status.INFO, "clicked on org tab");

		// Identify plus icon and click on it
		OrgPomPage org = new OrgPomPage(driver);
		org.getOrgPlusicon();
		UtilityObjectClass.getTest().log(Status.INFO, "clicked on plus icon");

		// Identify org name TF and pass org name
		CreateOrgPomPage createorg = new CreateOrgPomPage(driver);
		createorg.getOrgnameTF(orgname);
		UtilityObjectClass.getTest().log(Status.INFO, "entered org name");

		// Identify save button and click on it
		createorg.getSaveBtn();
		UtilityObjectClass.getTest().log(Status.INFO, "clicked on save button");

		// Verify org name in org info page
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String infoheader = orginfo.getOrginfoHeader();
		Assert.assertTrue(infoheader.contains(orgname), "Verify org name in org info page");
		UtilityObjectClass.getTest().log(Status.PASS, "verified org name");

		// Identify organization tab and click on it
		home.getOrgTab();
		UtilityObjectClass.getTest().log(Status.INFO, "clicked on org tab");

		// Identify del button for the created org name
		// Dynamic xpath
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant::a[text()='del']"))
				.click();
		UtilityObjectClass.getTest().log(Status.INFO, "deleted organization");

		Thread.sleep(4000);

		// Handle alert popup and click on ok button
		wutil.handleAlertClickOnOk(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "Handled the alert popup");

		// close the excel
		UtilityObjectClass.getTest().log(Status.INFO, "closing excel");
		exutil.closeExcel();
		soft.assertAll();
	}

	@Test(groups = "regression", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createOrgWithIndType_test() throws InterruptedException, IOException {

		// Fetch random integer
		JavaUtility jutil = new JavaUtility();
		int randomnum = jutil.fetchRandomInt();
		UtilityObjectClass.getTest().log(Status.INFO, "Fetched random int");

		// Fetch data from Excel File
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcelFile("OrgData", 4, 3) + randomnum;
		String indutry = exutil.fetchDataFromExcelFile("OrgData", 4, 4);
		String type = exutil.fetchDataFromExcelFile("OrgData", 4, 5);
		UtilityObjectClass.getTest().log(Status.INFO, "Fetched data from excel");

		// Validate Home page
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "Validating home header");
		UtilityObjectClass.getTest().log(Status.INFO, "validated home page using soft assert");

		// Identify organization tab and click on it
		HomePomPage home = new HomePomPage(driver);
		home.getOrgTab();
		UtilityObjectClass.getTest().log(Status.INFO, "clicked on org tab");

		// Identify plus icon and click on it
		OrgPomPage org = new OrgPomPage(driver);
		org.getOrgPlusicon();
		UtilityObjectClass.getTest().log(Status.INFO, "clicked on plus icon");

		// Identify org name TF and pass org name
		CreateOrgPomPage createOrg = new CreateOrgPomPage(driver);
		createOrg.getOrgnameTF(orgname);
		UtilityObjectClass.getTest().log(Status.INFO, "entered org name");

		// Select the industry from DD
		WebElement ind_dd = createOrg.getIndustry_DD();
		wutil.selectDDByValue(ind_dd, indutry);
		UtilityObjectClass.getTest().log(Status.INFO, "selected option from industry DD");

		// Select The type From DD
		WebElement type_dd = createOrg.getType_DD();
		wutil.selectDDByValue(type_dd, type);
		UtilityObjectClass.getTest().log(Status.INFO, "selected option from type DD");

		// Identify save button and click on it
		createOrg.getSaveBtn();
		UtilityObjectClass.getTest().log(Status.INFO, "clicked on save button");

		// Verify org name in org info page
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String verify_infoheader = orginfo.getOrginfoHeader();
		Assert.assertTrue(verify_infoheader.contains(orgname), "Verify org name in org info page");
		UtilityObjectClass.getTest().log(Status.PASS, "verified org name");

		// Verify industry in org info page
		String verifyindustry = orginfo.getVerifyIndustry();
		Assert.assertTrue(verifyindustry.contains(indutry), "Verify industry in org info page");
		UtilityObjectClass.getTest().log(Status.PASS, "verified indutry");

		// Verify type in org info page
		String verifyType = orginfo.getVerifyType();
		Assert.assertTrue(verifyType.contains(type), "Verify type in org info page");
		UtilityObjectClass.getTest().log(Status.PASS, "verified type");

		// Identify organization tab and click on it
		home.getOrgTab();
		UtilityObjectClass.getTest().log(Status.INFO, "clicked on org tab");

		// Identify del button for the created org name
		// Dynamic xpath
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant::a[text()='del']"))
				.click();
		UtilityObjectClass.getTest().log(Status.INFO, "deleted organization");

		Thread.sleep(2000);

		// Handle alert popup and click on ok button
		wutil.handleAlertClickOnOk(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "handled alert popup");

		// close the excel
		exutil.closeExcel();
		soft.assertAll();
		UtilityObjectClass.getTest().log(Status.INFO, "closed excel");

	}

	@Test(groups = "regression", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createOrgWithphno_test() throws InterruptedException, IOException {

		// Fetch random integer
		JavaUtility jutil = new JavaUtility();
		int randomnum = jutil.fetchRandomInt();
		UtilityObjectClass.getTest().log(Status.INFO, "fetched random int");

		// Fetch data from Excel File
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcelFile("OrgData", 7, 3) + randomnum;
		String phno = exutil.fetchDataFromExcelFile("OrgData", 7, 4);
		UtilityObjectClass.getTest().log(Status.INFO, "fetched data from excel");

		// Validate Home page
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "Validating home header");
		UtilityObjectClass.getTest().log(Status.INFO, "validated home page using soft assert");

		// Identify organization tab and click on it
		HomePomPage home = new HomePomPage(driver);
		home.getOrgTab();
		UtilityObjectClass.getTest().log(Status.INFO, "clicked on org tab");

		// Identify plus icon and click on it
		OrgPomPage org = new OrgPomPage(driver);
		org.getOrgPlusicon();
		UtilityObjectClass.getTest().log(Status.INFO, "clicked on plus icon");

		// Identify org name TF and pass org name
		CreateOrgPomPage createOrg = new CreateOrgPomPage(driver);
		createOrg.getOrgnameTF(orgname);
		UtilityObjectClass.getTest().log(Status.INFO, "entered org name");

		// Identify phno TF and pass the phno
		createOrg.getPhnoTF(phno);
		UtilityObjectClass.getTest().log(Status.INFO, "entered phno");

		// Identify save button and click on it
		createOrg.getSaveBtn();
		UtilityObjectClass.getTest().log(Status.INFO, "clicke don save btn");

		// Verify org name in org info page
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String verify_infoheader = orginfo.getOrginfoHeader();
		Assert.assertTrue(verify_infoheader.contains(orgname), "Verify org name in org info page");
		UtilityObjectClass.getTest().log(Status.PASS, "validated org name");

		// Verify phno in org info page
		String verifyPhno = orginfo.getVerifyOrgPhno();
		Assert.assertTrue(verifyPhno.contains(phno), "Verify phno in org info page");
		UtilityObjectClass.getTest().log(Status.PASS, "validated phno");

		// Identify organization tab and click on it
		home.getOrgTab();
		UtilityObjectClass.getTest().log(Status.INFO, "clicked on org tab");

		// Identify del button for the created org name
		// Dynamic xpath
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant::a[text()='del']"))
				.click();
		UtilityObjectClass.getTest().log(Status.INFO, "deleted organization");

		Thread.sleep(2000);

		// Handle alert popup and click on ok button
		wutil.handleAlertClickOnOk(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "handled alert popup");

		// close the excel
		exutil.closeExcel();
		soft.assertAll();
		UtilityObjectClass.getTest().log(Status.INFO, "closed excel");

	}
}
