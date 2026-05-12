package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrgPomPage {

	// Declare
	@FindBy(xpath = "//span[text()='Creating New Organization']")
	private WebElement createOrgHeader;

	@FindBy(name = "accountname")
	private WebElement orgnameTF;

	@FindBy(id = "phone")
	private WebElement phnoTF;

	@FindBy(name = "industry")
	private WebElement industry_DD;

	@FindBy(name = "accounttype")
	private WebElement type_DD;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public CreateOrgPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getCreateOrgHeader() {
		return createOrgHeader.getText();
	}

	public void getOrgnameTF(String orgname) {
		orgnameTF.sendKeys(orgname);
	}

	public void getPhnoTF(String phno) {
		phnoTF.sendKeys(phno);
	}

	public WebElement getIndustry_DD() {
		return industry_DD;
	}

	public WebElement getType_DD() {
		return type_DD;
	}

	public void getSaveBtn() {
		saveBtn.click();
	}

}
