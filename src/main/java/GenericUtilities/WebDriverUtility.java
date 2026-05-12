package GenericUtilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void navigateToAnAppln(WebDriver driver, String url) {
		driver.get(url);
	}

	public String fetchTheTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String fetchTheUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String fetchThePageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void closeTheBrowser(WebDriver driver) {
		driver.close();
	}

	public void quitTheBrowser(WebDriver driver) {
		driver.quit();
	}

	public void maxTheWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void minTheWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}

	public void fullscreenTheWindow(WebDriver driver) {
		driver.manage().window().fullscreen();
	}

	public void setWindowSize(WebDriver driver, int width, int height) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	public void setWindowPosition(WebDriver driver, int x, int y) {
		driver.manage().window().setPosition(new Point(x, y));
	}

	public Dimension getWindowSize(WebDriver driver) {
		Dimension dim = driver.manage().window().getSize();
		return dim;
	}

	public Point getWindowPosition(WebDriver driver) {
		Point p = driver.manage().window().getPosition();
		return p;
	}

	public void navigateToPrevious_WP(WebDriver driver) {
		driver.navigate().back();
	}

	public void navigateToNext_WP(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshTheWebpage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void navigateToApp_toStringUrl(WebDriver driver, String url) {
		driver.navigate().to(url);
	}

	public void navigateToApp_toURL(WebDriver driver, String url) throws MalformedURLException {
		driver.navigate().to(new URL(url));
	}

	public String fetchWindoWID(WebDriver driver) {
		String wid = driver.getWindowHandle();
		return wid;
	}

	public Set<String> fetchWindoWAllIDs(WebDriver driver) {
		Set<String> wids = driver.getWindowHandles();
		return wids;
	}

	public void waitForAnElement(WebDriver driver, String time) {
		long t = Long.parseLong(time);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(t));
	}

	public void waitForEleVisibility(WebDriver driver, String time, WebElement ele) {
		long t = Long.parseLong(time);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(t));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForEleToBeClickable(WebDriver driver, String time, WebElement ele) {
		long t = Long.parseLong(time);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(t));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitForTitleToBeVisible(WebDriver driver, String time, String title) {
		long t = Long.parseLong(time);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(t));
		wait.until(ExpectedConditions.titleContains(title));
	}

	public void selectDDByIndex(WebElement dropdown, int index) {
		Select s = new Select(dropdown);
		s.selectByIndex(index);
	}

	public void selectDDByValue(WebElement dropdown, String value) {
		Select s = new Select(dropdown);
		s.selectByValue(value);
	}

	public void selectDDByVisibleText(WebElement dropdown, String text) {
		Select s = new Select(dropdown);
		s.selectByVisibleText(text);
	}

	public void clickAnEle_Actions(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.click(ele).perform();
	}

	public void mousehoverOnAnEle(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
	}

	public void dragAndDropEle(WebDriver driver, WebElement targetele, WebElement targetLoc) {
		Actions act = new Actions(driver);
		act.dragAndDrop(targetele, targetLoc).perform();
	}

	public void handleAlertClickOnOk(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void handleAlertClickOnCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String handleAlertFetchTheText(WebDriver driver) {
		String text = driver.switchTo().alert().getText();
		return text;
	}

	public void handleAlertEnterText(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	public void switchToChildWindow_url(WebDriver driver, String expurl) {
		Set<String> wids = driver.getWindowHandles();
		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getCurrentUrl().contains(expurl)) {
				break;
			}

		}
	}

	public void switchToChildWindow_title(WebDriver driver, String exptitle) {
		Set<String> wids = driver.getWindowHandles();
		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getTitle().contains(exptitle)) {
				break;
			}

		}
	}

	public void switchToParentWindow(WebDriver driver, String id) {
		driver.switchTo().window(id);
	}

}
