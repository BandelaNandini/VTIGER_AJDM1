package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.WebDriverUtility;

public class HomePomPage {

	// Declare
	@FindBy(partialLinkText = "Home")
	private WebElement homeHeader;

	@FindBy(linkText = "Organizations")
	private WebElement orgTab;

	@FindBy(linkText = "Contacts")
	private WebElement conTab;

	@FindBy(xpath = "//img[contains(@src,'user')]")
	private WebElement adminIcon;

	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;

	// Initialize
	public HomePomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilize
	public String getHomeHeader() {
		return homeHeader.getText();
	}

	public void getOrgTab() {
		orgTab.click();
	}

	public void getConTab() {
		conTab.click();
	}

	public WebElement getAdminIcon() {
		return adminIcon;
	}

	public void getSignOutLink() {
		signOutLink.click();
	}
	
	public void logout(WebDriver driver)
	{
		WebDriverUtility wutil= new WebDriverUtility();
		wutil.mousehoverOnAnEle(driver, adminIcon);
		signOutLink.click();
	}

}
