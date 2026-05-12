package OrganizationModule;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import POMUtilities.CreateOrgPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;
import POMUtilities.OrgInfoPomPage;
import POMUtilities.OrgPomPage;

public class CreateOrgWithIndustryAndTypeTest {
	@Test
	public void createOrgWithIndType_test() throws InterruptedException, IOException {

		// Fetch data from prop file
		PropertyFileUtility p = new PropertyFileUtility();
		String browser = p.fetchDataFromPropFile("browser");
		String url = p.fetchDataFromPropFile("url");
		String timeouts = p.fetchDataFromPropFile("timeouts");
		String username = p.fetchDataFromPropFile("username");
		String password = p.fetchDataFromPropFile("password");

		// Fetch random integer
		JavaUtility jutil = new JavaUtility();
		int randomnum = jutil.fetchRandomInt();

		// Fetch data from Excel File
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcelFile("OrgData", 4, 3) + randomnum;
		String indutry = exutil.fetchDataFromExcelFile("OrgData", 4, 4);
		String type = exutil.fetchDataFromExcelFile("OrgData", 4, 5);

		// Launch the browser
		WebDriver driver = null;
		if (browser.equals("chrome"))
			driver = new ChromeDriver();
		else if (browser.equals("edge"))
			driver = new EdgeDriver();
		else if (browser.equals("firefox"))
			driver = new FirefoxDriver();
		else
			driver = new ChromeDriver();

		WebDriverUtility wutil = new WebDriverUtility();

		// Maximize the window
		wutil.maxTheWindow(driver);

		// Implicit wait
		wutil.waitForAnElement(driver, timeouts);

		// Navigate to an appln
		wutil.navigateToAnAppln(driver, url);

		LoginPomPage l = new LoginPomPage(driver);
		l.login(username, password);

		// Validate Home page
		if (driver.getCurrentUrl().contains("action=index&module=Home")) {
			System.out.println("Navigated to Home page");
		} else {
			System.out.println("Login Test Fail");
		}

		// Identify organization tab and click on it
		HomePomPage home = new HomePomPage(driver);
		home.getOrgTab();

		// Identify plus icon and click on it
		OrgPomPage org = new OrgPomPage(driver);
		org.getOrgPlusicon();

		// Identify org name TF and pass org name
		CreateOrgPomPage createOrg = new CreateOrgPomPage(driver);
		createOrg.getOrgnameTF(orgname);

		// Select the industry from DD
		WebElement ind_dd = createOrg.getIndustry_DD();
		wutil.selectDDByValue(ind_dd, indutry);

		// Select The type From DD
		WebElement type_dd = createOrg.getType_DD();
		wutil.selectDDByValue(type_dd, type);

		// Identify save button and click on it
		createOrg.getSaveBtn();

		// Verify org name in org info page
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String verify_infoheader = orginfo.getOrginfoHeader();

		if (verify_infoheader.contains(orgname)) {
			System.out.println("Successfully created Org");
		} else {
			System.out.println("Creating Org Test Fail");
		}

		// Verify industry in org info page

		String verifyindustry = orginfo.getVerifyIndustry();

		if (verifyindustry.contains(indutry)) {
			System.out.println("Successfully created Org with indsutry");
		} else {
			System.out.println("Creating Org with industry Test Fail");

		} // Verify type in org info page
		String verifyType = orginfo.getVerifyType();

		if (verifyType.contains(type)) {
			System.out.println("Successfully created Org with type");
		} else {
			System.out.println("Creating Org Test Fail with type");
		}

		// Identify organization tab and click on it
		home.getOrgTab();

		// Identify del button for the created org name
		// Dynamic xpath
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant::a[text()='del']"))
				.click();

		Thread.sleep(2000);

		// Handle alert popup and click on ok button
		wutil.handleAlertClickOnOk(driver);

		// Identify admin icon and mouse hover on it
		WebElement admin = home.getAdminIcon();
		wutil.mousehoverOnAnEle(driver, admin);

		// Identify signout link and click on it
		home.getSignOutLink();

		// Close the browser
		wutil.quitTheBrowser(driver);

	}

}
