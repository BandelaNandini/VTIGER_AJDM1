package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConInfoPomPage {

	// Declare
	@FindBy(xpath = "//span[contains(text(),'Contact Information')]")
	private WebElement coninfoheader;

	@FindBy(id = "dtlview_Last Name")
	private WebElement verifyConname;

	@FindBy(xpath = "//td[@id='mouseArea_Organization Name']/a")
	private WebElement verifyOrgname;

	@FindBy(id = "dtlview_Support Start Date")
	private WebElement verifyStartdate;

	@FindBy(id = "dtlview_Support End Date")
	private WebElement verifyEnddate;

	public ConInfoPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getConinfoheader() {
		return coninfoheader.getText();
	}

	public String getVerifyConname() {
		return verifyConname.getText();
	}

	public String getVerifyOrgname() {
		return verifyOrgname.getText();
	}

	public String getVerifyStartdate() {
		return verifyStartdate.getText();
	}

	public String getVerifyEnddate() {
		return verifyEnddate.getText();
	}

}
