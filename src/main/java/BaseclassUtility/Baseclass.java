package BaseclassUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import GenericUtilities.DatabaseUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import ListenersUtility.UtilityObjectClass;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;

public class Baseclass {
	public DatabaseUtility dbutil;
	public PropertyFileUtility putil = new PropertyFileUtility();
	public WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	@BeforeSuite(alwaysRun = true)
	public void connectToDB() throws SQLException {
		dbutil = new DatabaseUtility();
		dbutil.getconnectWithDB();
		Reporter.log("Connected with DB", true);

	}

	@BeforeTest(alwaysRun = true)
	public void configParallelExe() {
		Reporter.log("Configured the parallel Exe", true);
	}

//	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void LaunchTheBrowser() throws IOException {
		Reporter.log("Launching Browser", true);
		String browser = System.getProperty("browser", putil.fetchDataFromPropFile("browser"));
		// Launch the browser
		if (browser.equals("chrome"))
			driver = new ChromeDriver();
		else if (browser.equals("edge"))
			driver = new EdgeDriver();
		else if (browser.equals("firefox"))
			driver = new FirefoxDriver();
		else
			driver = new ChromeDriver();

		sdriver = driver;
		UtilityObjectClass.setDriver(driver);

	}

	@BeforeMethod(alwaysRun = true)
	public void Login() throws IOException {

		String url = System.getProperty("url", putil.fetchDataFromPropFile("url"));
		String username = System.getProperty("username", putil.fetchDataFromPropFile("username"));
		String password = System.getProperty("password", putil.fetchDataFromPropFile("password"));
		String timeouts = System.getProperty("timeouts", putil.fetchDataFromPropFile("timeouts"));
		wutil.maxTheWindow(driver);
		wutil.waitForAnElement(driver, timeouts);
		wutil.navigateToAnAppln(driver, url);
		LoginPomPage l = new LoginPomPage(driver);

		l.login(username, password);
		Reporter.log("Logged into the application", true);

	}

	@AfterMethod(alwaysRun = true)
	public void Logout() {
		HomePomPage home = new HomePomPage(driver);
		wutil.mousehoverOnAnEle(driver, home.getAdminIcon());
		home.getSignOutLink();
		Reporter.log("Logged out of the application", true);

	}

	@AfterClass(alwaysRun = true)
	public void quitTheBrowser() {
		wutil.quitTheBrowser(driver);
		Reporter.log("Quiting the Browser", true);
	}

	@AfterTest(alwaysRun = true)
	public void closeConfigPE() {
		Reporter.log("Close config of parallel exe", true);
	}

	@AfterSuite(alwaysRun = true)
	public void Disconnect_DB() throws SQLException {
		dbutil.disconnectWithDB();
		Reporter.log("Disconnected with Database", true);

	}

}
