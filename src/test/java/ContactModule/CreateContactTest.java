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
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;

public class CreateContactTest {
	@Test
	public void createCon_test() throws InterruptedException, IOException {

		// Fetch data from prop file
		PropertyFileUtility putil = new PropertyFileUtility();
		String browser = putil.fetchDataFromPropFile("browser");
		String url = putil.fetchDataFromPropFile("url");
		String timeouts = putil.fetchDataFromPropFile("timeouts");
		String username = putil.fetchDataFromPropFile("username");
		String password = putil.fetchDataFromPropFile("password");

		// Fetch random integer
		JavaUtility jutil = new JavaUtility();
		int randomnum = jutil.fetchRandomInt();

		// Fetch data from Excel File
		ExcelFileUtility exutil = new ExcelFileUtility();
		String contactname = exutil.fetchDataFromExcelFile("ConData", 1, 3) + randomnum;

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

		// Login to vtiger
		LoginPomPage l = new LoginPomPage(driver);
		l.login(username, password);

		// Validate Home page
		if (driver.getCurrentUrl().contains("action=index&module=Home")) {
			System.out.println("Navigated to Home page");
		} else {
			System.out.println("Login Test Fail");
		}

		// Identify contact tab and click on it
		HomePomPage home = new HomePomPage(driver);
		home.getConTab();

		// Identify plus icon and click on it
		ConPomPage con = new ConPomPage(driver);
		con.getConPlusicon();

		// Identify org name TF and pass org name
		CreateConPomPage createcon = new CreateConPomPage(driver);
		createcon.getLastnameTF(contactname);

		// Identify save button and click on it
		createcon.getSavebtn();

		// Verify contact name in contact info page
		ConInfoPomPage coninfo = new ConInfoPomPage(driver);
		String infoheader = coninfo.getConinfoheader();

		if (infoheader.contains(contactname)) {
			System.out.println("Successfully created contact");
		} else {
			System.out.println("Creating Contact Test Fail");
		}

		// Identify contact tab and click on it
		home.getConTab();

		// Identify del button for the created org name
		// Dynamic xpath
		driver.findElement(By.xpath("//a[text()='" + contactname + "']/../../descendant::a[text()='del']")).click();

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
