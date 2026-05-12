package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConPomPage {

	// Declare
	@FindBy(linkText = "Contacts")
	private WebElement conheader;

	@FindBy(xpath = "//img[@title=\"Create Contact...\"]")
	private WebElement conPlusicon;

	// Initialize
	public ConPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getConheader() {
		return conheader.getText();
	}

	public void getConPlusicon() {
		conPlusicon.click();
	}

}
