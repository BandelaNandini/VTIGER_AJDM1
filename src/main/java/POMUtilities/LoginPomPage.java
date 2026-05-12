package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPomPage {

	// Declare
	@FindBy(linkText = "vtiger")
	private WebElement VtigerHeader;

	@FindBy(name = "user_name")
	private WebElement UsernameTF;

	@FindBy(name = "user_password")
	private WebElement PasswordTF;

	@FindBy(id = "submitButton")
	private WebElement LoginBtn;

	// Initialize

	public LoginPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilize
	public String getVtigerHeader() {
		return VtigerHeader.getText();
	}

	public void getUsernameTF(String username) {
		UsernameTF.sendKeys(username);
	}

	public void getPasswordTF(String password) {
		PasswordTF.sendKeys(password);
	}

	public void getLoginBtn() {
		LoginBtn.click();
	}

	// Business logic
	public void login(String username, String password) {
		UsernameTF.sendKeys(username);
		PasswordTF.sendKeys(password);
		LoginBtn.click();
	}
}
