package ContactModule;

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
import POMUtilities.ConInfoPomPage;
import POMUtilities.ConPomPage;
import POMUtilities.CreateConPomPage;
import POMUtilities.CreateOrgPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;
import POMUtilities.OrgInfoPomPage;
import POMUtilities.OrgPomPage;

public class CreateContactWithOrgTest {

	@Test
	public void createConWithOrg_test() throws InterruptedException, IOException {

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
		String contactname = exutil.fetchDataFromExcelFile("ConData", 7, 3) + randomnum;
		String Orgname = exutil.fetchDataFromExcelFile("ConData", 7, 4) + randomnum;

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

		// Login
		LoginPomPage login = new LoginPomPage(driver);
		login.login(username, password);

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
		createOrg.getOrgnameTF(Orgname);

		// Identify save button and click on it
		createOrg.getSaveBtn();

		// Verify org name in org info page
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String infoheader = orginfo.getOrginfoHeader();

		if (infoheader.contains(Orgname)) {
			System.out.println("Successfully created Org");
		} else {
			System.out.println("Creating Org Test Fail");
		}

		// Identify contact tab and click on it
		home.getConTab();

		// Identify plus icon and click on it
		ConPomPage con = new ConPomPage(driver);
		con.getConPlusicon();

		// Identify org name TF and pass cont name
		CreateConPomPage createCon = new CreateConPomPage(driver);
		createCon.getLastnameTF(contactname);

		// Click on org plus icon
		createCon.getOrgplusicon();

		// Fetch the parent window id
		String pwid = wutil.fetchWindoWID(driver);

		// Switch the driver control to child window
		wutil.switchToChildWindow_url(driver, "module=Accounts&action");

		// Searching orgname
		createCon.getOrgsearchTF(Orgname);
		createCon.getOrgsearchbtn();
		driver.findElement(By.xpath("//a[text()='" + Orgname + "']")).click();

		// Switch back to parent window
		wutil.switchToParentWindow(driver, pwid);

		// Identify save button and click on it
		createCon.getSavebtn();

		// Verify contact name in contact info page
		ConInfoPomPage coninfo = new ConInfoPomPage(driver);
		String coninfoheader = coninfo.getConinfoheader();
		if (coninfoheader.contains(contactname)) {
			System.out.println("Successfully created contact");
		} else {
			System.out.println("Creating Contact Test Fail");
		}

		// Verify org name in contact info page
		String verifyOrg = coninfo.getVerifyOrgname();
		if (verifyOrg.contains(Orgname)) {
			System.out.println("Successfully created contact with org name");
		} else {
			System.out.println("Creating Contact with org name Test Fail");
		}

		// Identify contact tab and click on it
		home.getConTab();

		// Identify del button for the created org name
		// Dynamic xpath
		driver.findElement(By.xpath("//a[text()='" + contactname + "']/../../descendant::a[text()='del']")).click();

		Thread.sleep(2000);

		// Handle alert popup and click on ok button
		wutil.handleAlertClickOnOk(driver);

		// Identify organization tab and click on it
		home.getOrgTab();

		// Identify del button for the created org name
		// Dynamic xpath
		driver.findElement(
				By.xpath("//a[text()='" + Orgname + "' and @title='Organizations']/../../descendant::a[text()='del']"))
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
