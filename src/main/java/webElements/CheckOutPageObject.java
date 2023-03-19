package webElements;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPageObject extends AbstractPage {

	WebDriver driver;
	AbstractPage abstractPage;
	JavascriptExecutor js;

	public CheckOutPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".a-truncate.sc-grid-item-product-title.a-size-base-plus")
	private WebElement cartProduct;

	@FindBy(css = "input[value='Proceed to checkout']")
	private WebElement proceedCheckout;

	@FindBy(id = "add-new-address-popover-link")
	private WebElement addnewAddress;

	@FindBy(id = "address-ui-widgets-countryCode-dropdown-nativeId")
	private WebElement staticDropdown;

	@FindBy(id = "address-ui-widgets-enterAddressFullName")
	private WebElement fullName;

	@FindBy(id = "address-ui-widgets-enterAddressPhoneNumber")
	private WebElement phoneNumber;

	@FindBy(id = "address-ui-widgets-enterAddressPostalCode")
	private WebElement pinCode;

	@FindBy(id = "address-ui-widgets-enterAddressLine1")
	private WebElement addressLine1;

	@FindBy(id = "address-ui-widgets-enterAddressLine2")
	private WebElement areaOrStreet;

	@FindBy(id = "address-ui-widgets-enterAddressCity")
	private WebElement city;

	@FindBy(id = "address-ui-widgets-enterAddressStateOrRegion-dropdown-nativeId")
	private WebElement state;

	@FindBy(id = "address-ui-widgets-form-submit-button")
	private WebElement useThisAddress;

	@FindBy(xpath = "//h3[normalize-space()='Select a payment method']")
	private WebElement selectPaymentMethod;

//	@FindBy(css = "[id$='-357']")
//	WebElement codOption;

	@FindBy(xpath = "//span[@class='a-color-base a-text-bold'][normalize-space()='Cash On Delivery/Pay On Delivery']")
	private WebElement codOption;

	@FindBy(css = "[id$='-360']")
	private WebElement paymentMethod;

	@FindBy(xpath = "//h3[normalize-space()='Review items and delivery']")
	private WebElement reviewItems;

	@FindBy(id = "submitOrderButtonId")
	private WebElement placeYourOrder;

	public String getCartProductDetails() {
		return cartProduct.getText();
	}

	public void proceedToBuy() {
		proceedCheckout.click();
	}

	public void addNewAddress(String FullName, String PhoneNumber, String PinCode, String Address1, String StreetArea,
			String City) {

		addWait(addnewAddress);

		addnewAddress.click();

		Select dropdown = new Select(staticDropdown);
		dropdown.selectByVisibleText("India"); // select country

		try {
			fullName.sendKeys(FullName);
		} catch (ElementNotInteractableException e) {
			addnewAddress.click();
			dropdown.selectByVisibleText("India"); // select country
			fullName.sendKeys(FullName);
		}

		phoneNumber.sendKeys(PhoneNumber);
		pinCode.sendKeys(PinCode);
		addressLine1.sendKeys(Address1);
		areaOrStreet.sendKeys(StreetArea);
		city.sendKeys(City);

		Select dropdown2 = new Select(state);
		dropdown2.selectByVisibleText("UTTAR PRADESH"); // select state

		useThisAddress.click();
	}

	public void selectCOD(){

		wait6sec();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 1500)");

		codOption.click(); // Select cash on delivery option
		paymentMethod.click(); // Use this payment method
	}

	public void placeYourOrder() {
		addWait(placeYourOrder);
//		placeYourOrder.click();   // Commenting it out as we dont want to create order again and again
	}

}
