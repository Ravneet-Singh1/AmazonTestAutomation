# AmazonTestAutomation
Amazon Web Page E2E Automation
NOTE: Enter Correct Email id and Password in data.properties before running the test suite, for dummy purpose I have given 'dummyEmail@gmail.com' & 'dummyPassword'

amazon\src\test\java\testCases\data.properties

=> Search for a product:
--------------------------

Steps:

1. Go to the Amazon website (https://www.amazon.in/)
2. On the home page click sign and login using your email id and password.
4. After login is sucessfull, in the search bar, enter the name or keyword of the product you want to search for (e.g., "Boat Headphones")
5. Press the "Enter" key or click the magnifying glass icon to initiate the search.

Expected Results:

1. The Amazon website should display the search results for the entered keyword.
2. The search results should include the product name, & price.
3. The search results should also display filters for refining the search results.


=> Filtering the search results:
----------------------------------

Steps:

1. After the search results are displayed, click on the "Filter by" dropdown menu located on the left-hand side of the page.
2. Select "Price" from the available options or give minimum and maximum limit.
3. Click on the "Apply" button to filter the search results.

Expected Results:

1. The search results should be updated to show only the products within the selected price range.
2. The search results should display the product name & price for the filtered products.


=> Adding a product to the shopping cart:
------------------------------------------

Steps:

1. Click on the product you want to purchase from the search results.
2. Choose the desired options such as size or color, if applicable.
3. Click on the "Add to Cart" button.

Expected Results:

1. The selected product should be added to the shopping cart.
2. Validate if the correct product has been added to cart or not.


=> Proceeding to checkout:
----------------------------

Steps:

1. Click on the shopping cart icon located in the upper right-hand corner of the page.
2. Click on the "Proceed to Checkout" button.

Expected Results:

1. The checkout page should be displayed.
2. The shopping cart should show the selected product with the chosen options, quantity, and the total price.


Filling out the checkout form:
--------------------------------

Steps:

1. Enter the shipping address details (such as name, address, and phone number) in the required fields.
2. Select the desired shipping option.
3. Enter the payment information (such as credit card number, expiration date, and security code) or you can also opt for cash on delivert in the required fields.
4. Review the order summary and click on the "Place your order" button to complete the checkout.

Expected Results:

1. The checkout form should be filled out with accurate and valid information.
2. The order summary should show the selected product with the chosen options, quantity, and the total price.
3. A message should appear on the screen confirming that the order has been placed.



