package testCases;

import java.util.List;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import webElements.CheckOutPageObject;
import webElements.HomePageObject;
import webElements.LoginPageObject;
import webElements.SearchedProductPageObject;

public class AmazonHomePage extends Base {

	HomePageObject homePage;
	LoginPageObject loginPage;
	SearchedProductPageObject searchProduct;
	CheckOutPageObject checkOut;

	String emailId;
	String password;
	String productTitle;
	SoftAssert softAssert;

	// =============================================================
	// Amazon - SignIn
	// =============================================================

	@Test(description = "Amazon login functioanlity")
	public void loginInTest() {

		loginPage = new LoginPageObject(driver);
		homePage = new HomePageObject(driver);

		homePage.SignIn(); // click SignIn button on homePage

		loginPage.login(prop.getProperty("emailId"), prop.getProperty("password")); // Login to homePage

		softAssert = new SoftAssert();
		softAssert.assertEquals(driver.getTitle(), "Amazon Sign In");
	}

	// =============================================================
	// Search Product
	// =============================================================

	@Test(dependsOnMethods = "loginInTest", description = "Searching for a product")
	public void Searchproduct() {
		String productName = prop.getProperty("productName");
		String searchedProduct = homePage.searchProduct(productName); // Search Product

		String expectedproduct = "\"" + productName + "\""; // "ProductName"
		Assert.assertEquals(searchedProduct, expectedproduct);
	}

	// =============================================================
	// Filter search results (Based on Price)
	// =============================================================

	@Test(dependsOnMethods = "Searchproduct", description = "Filtering the search results")
	public void filterSearchResult() {

		searchProduct = new SearchedProductPageObject(driver);

		String minPrice = prop.getProperty("minPrice");
		String maxPrice = prop.getProperty("maxPrice");

		searchProduct.filterPriceRange(minPrice, maxPrice);
		List<Integer> productPrice = searchProduct.getProductsPrice();

		for (int i = 0; i < productPrice.size(); i++) {
			int priceValue = productPrice.get(i);
			Assert.assertEquals(true,
					priceValue >= Integer.parseInt(minPrice) && priceValue <= Integer.parseInt(maxPrice));
		}
	}

	// =============================================================
	// Add Product to Cart
	// =============================================================

	@Test(dependsOnMethods = "filterSearchResult", description = "Adding a product to the shopping cart")
	public void addProductTocart() throws InterruptedException {
		searchProduct.selectProduct(prop.getProperty("firstProduct"), prop.getProperty("secondProduct"));
		productTitle = searchProduct.selectDesiredOption();

		try {
			homePage.moveTocart();
		} catch (ElementClickInterceptedException e) {
			searchProduct.skipWarranty();
			homePage.moveTocart();
		} catch (ElementNotInteractableException e) {
			searchProduct.skipWarranty();
			homePage.moveTocart();
		}
	}

	// =============================================================
	// Proceeding to checkout
	// =============================================================

	@Test(dependsOnMethods = "addProductTocart", description = "Adding a product to the shopping cart & Proceeding to checkout")
	public void cartProducts() {
		checkOut = new CheckOutPageObject(driver);

		String productDetail = checkOut.getCartProductDetails();
		String cartProductName = productDetail.replace("…", "");

		// Assertion to check if correct product is added in a cart or not
		if (productTitle.contains(cartProductName)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		checkOut.proceedToBuy();
	}

	// =============================================================
	// Filling out the checkout form
	// =============================================================

	@Test(dependsOnMethods = "cartProducts", description = "Filling out the checkout form:")
	public void addAddress() {
		checkOut.addNewAddress(prop.getProperty("fullName"), prop.getProperty("phoneNumber"),
				prop.getProperty("pincode"), prop.getProperty("addressLine1"), prop.getProperty("StreetArea"),
				prop.getProperty("city"));
	}

	// =============================================================
	// Select Payment Method
	// =============================================================

	@Test(dependsOnMethods = "addAddress", description = "Select Payment method")
	public void paymentOption() throws InterruptedException {
		checkOut.selectCOD();
	}

	// =============================================================
	// Place Order
	// =============================================================

	@Test(dependsOnMethods = "paymentOption", description = "Submit Order")
	public void placeOrder() {
		checkOut.placeYourOrder();
		softAssert.assertAll();
	}

}
