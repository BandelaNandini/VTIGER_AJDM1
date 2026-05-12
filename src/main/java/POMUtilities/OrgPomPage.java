package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgPomPage {

	// Declare
	@FindBy(linkText = "Organizations")
	private WebElement orgheader;

	@FindBy(xpath = "//img[@title=\"Create Organization...\"]")
	private WebElement orgPlusicon;

	// Initialize
	public OrgPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilize
	public String getOrgheader() {
		return orgheader.getText();
	}

	public void getOrgPlusicon() {
		orgPlusicon.click();
	}

}
