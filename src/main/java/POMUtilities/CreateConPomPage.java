package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateConPomPage {

	// Declare
	@FindBy(name = "lastname")
	private WebElement lastnameTF;

	@FindBy(xpath = "//span[text()='Creating New Contact']")
	private WebElement createconheader;

	@FindBy(xpath = "//img[contains(@onclick,'module=Accounts&action=Popup')]")
	private WebElement orgplusicon;

	@FindBy(name = "support_start_date")
	private WebElement suppStartdateTF;

	@FindBy(name = "support_end_date")
	private WebElement suppEnddateTF;

	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement savebtn;

	@FindBy(id = "search_txt")
	private WebElement orgsearchTF;

	@FindBy(name = "search")
	private WebElement orgsearchbtn;

	public CreateConPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void getLastnameTF(String conname) {
		lastnameTF.sendKeys(conname);
	}

	public String getCreateconheader() {
		return createconheader.getText();
	}

	public void getOrgplusicon() {
		orgplusicon.click();
	}

	public void getSuppStartdateTF(String startdate) {
		suppStartdateTF.clear();
		suppStartdateTF.sendKeys(startdate);
	}

	public void getSuppEnddateTF(String enddate) {
		suppEnddateTF.clear();
		suppEnddateTF.sendKeys(enddate);
	}

	public void getSavebtn() {
		savebtn.click();
	}

	public void getOrgsearchTF(String orgname) {
		orgsearchTF.sendKeys(orgname);
	}

	public void getOrgsearchbtn() {
		orgsearchbtn.click();
	}

}
