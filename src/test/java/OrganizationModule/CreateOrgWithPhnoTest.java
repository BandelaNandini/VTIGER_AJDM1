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

public class CreateOrgWithPhnoTest {
	@Test
	public void createOrgWithphno_test() throws InterruptedException, IOException {

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
		String orgname = exutil.fetchDataFromExcelFile("OrgData", 7, 3) + randomnum;
		String phno = exutil.fetchDataFromExcelFile("OrgData", 7, 4);

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

		// Identify phno TF and pass the phno
		createOrg.getPhnoTF(phno);

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

		// Verify phno in org info page
		String verifyPhno = orginfo.getVerifyOrgPhno();

		if (verifyPhno.contains(phno)) {
			System.out.println("Successfully created Org with Phno");
		} else {
			System.out.println("Creating Org with Phno Test Fail");

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

//		Actions act = new Actions(driver);
		wutil.mousehoverOnAnEle(driver, admin);

		// Identify signout link and click on it
		home.getSignOutLink();

		// Close the browser
		wutil.quitTheBrowser(driver);

	}
}
