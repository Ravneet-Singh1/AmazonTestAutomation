package webElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends AbstractPage {

	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "ap_email")
	private WebElement email;

	@FindBy(id = "ap_password")
	private WebElement password;

	public void login(String EmailID, String Password) {
		email.sendKeys(EmailID);
		email.submit();
		password.sendKeys(Password);
		password.submit();
	}

}
