package webElements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchedProductPageObject extends AbstractPage {

	WebDriver driver;

	List<Integer> myList = new ArrayList<Integer>(); // List of products price
	List<String> searchedResults = new ArrayList<String>();

	public SearchedProductPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "low-price")
	private WebElement minPrice;

	@FindBy(id = "high-price")
	private WebElement maxPrice;

	@FindBy(xpath = "//div[@data-component-type='s-search-result']")
	private List<WebElement> searchResults;

	@FindBy(xpath = "//li[@title='Click to select Krypton Blue']")
	private WebElement selectColour;

	// add-to-cart-button
	@FindBy(id = "add-to-cart-button")
	private WebElement addToCart;

	@FindBy(id = "productTitle")
	private WebElement actualProductTitle;

	@FindBy(xpath = "//input[@aria-labelledby='attachSiNoCoverage-announce']")
	private WebElement skipWarranty;

	public void filterPriceRange(String MinPrice, String MaxPrice) {
		minPrice.sendKeys(MinPrice);
		maxPrice.sendKeys(MaxPrice);
		maxPrice.submit();
	}

	public List<Integer> getProductsPrice() {

		wait2sec();
		for (int i = 0; i < searchResults.size(); i++) {

			int Productprice = Integer.parseInt(
					searchResults.get(i).findElement(By.cssSelector(".a-price-whole")).getText().replaceAll(",", ""));

			myList.add(Productprice);
		}
		return myList;
	}

	public void selectProduct(String productName, String dummyProductName) {
		for (int i = 0; i < searchResults.size(); i++) {

			WebElement resultProduct = searchResults.get(i)
					.findElement(By.cssSelector(".a-size-medium.a-color-base.a-text-normal")); // get all products name
			searchedResults.add(resultProduct.getText());
		}

		// If product which we want falls in range then it will move to next Page
		// else if it will add another product "dummyProductName"
		// else it will pick the first item from the search results
		if (searchedResults.contains(productName)) {
			int value = searchedResults.indexOf(productName);
			searchResults.get(value).click();
		} else if (searchedResults.contains(dummyProductName)) {
			int value = searchedResults.indexOf(dummyProductName);
			searchResults.get(value).click();
		} else {
			searchResults.get(0).click();
		}
	}

	public String selectDesiredOption() {

		Set<String> windows = driver.getWindowHandles(); // To handle the windows [parentId, childID]
		Iterator<String> iterator = windows.iterator(); // To iterate between the ID's

		String parentID = iterator.next();
		String childID = iterator.next();

		driver.switchTo().window(childID);

		wait2sec();
		selectColour.click();
		wait2sec();
		String productTitle = actualProductTitle.getText(); // product which we are adding to cart

		addToCart.click();
		return productTitle;
	}

	public void skipWarranty() {
		skipWarranty.click();
	}

}
