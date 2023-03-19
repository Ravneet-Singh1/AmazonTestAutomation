package webElements;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObject extends AbstractPage {

	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "nav-link-accountList-nav-line-1")
	private WebElement signIn;

	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchBar;

	@FindBy(css = ".a-color-state.a-text-bold")
	private WebElement searchedProductName;

	@FindBy(id = "nav-cart")
	private WebElement cart;

	public void SignIn() {
		signIn.click();
	}

	public String searchProduct(String productName) {
		searchBar.sendKeys(productName);
		searchBar.submit();
		return searchedProductName.getText();
	}

	public void moveTocart() {

		try {
			cart.click();
		} catch (ElementClickInterceptedException e) {
			wait2sec();
			cart.click();
		}
		catch (StaleElementReferenceException e) {
			wait2sec();
			cart.click();
		}

	}
}
