package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.CartPage;
import testBase.BaseClass;

public class AddToCart extends BaseClass {

    // 🔹 Log4j Logger
    private static final Logger logger = LogManager.getLogger(AddToCart.class);

    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void addToCartTest() {

        logger.debug("========== addToCartTest Started ==========");

        try {
            logger.debug("Initializing HomePage");
            HomePage hp = new HomePage(driver);

            logger.debug("Selecting country from dropdown");
            hp.clickCountryDropdown();

            logger.debug("Scrolling and selecting country: Bahamas");
            hp.scrollToTextAndClick("Bahamas");

            logger.debug("Entering user name");
            hp.enterName();

            logger.debug("Clicking on 'Let's Shop' button");
            hp.clickLetsShop();

            logger.debug("Initializing ProductPage");
            ProductPage pp = new ProductPage(driver);

            logger.debug("Scrolling to product: Jordan 6 Rings");
            pp.scrollToText("Jordan 6 Rings");

            logger.debug("Clicking Add To Cart for product");
            pp.clickAddToCart();

            logger.debug("Clicking View Cart");
            pp.clickViewCart();

            logger.debug("Initializing CartPage");
            CartPage cp = new CartPage(driver);

            logger.debug("Fetching product name from Cart page");
            String lastPageProduct = cp.getAddedproductName();
            logger.debug("Product name in Cart: {}", lastPageProduct);

            // 🔹 Assertion with logging & retry support
            try {
                Assert.assertEquals(lastPageProduct, "Jordan 6 Rings");
                logger.debug("Assertion Passed: Correct product added to cart");
            } catch (AssertionError ae) {
                logger.error(
                        "Assertion Failed: Expected 'Jordan 6 Rings' but found '{}'",
                        lastPageProduct,
                        ae
                );
                throw ae; // mandatory for RetryAnalyzer
            }

        } catch (Exception e) {
            logger.error("Unexpected exception occurred in addToCartTest", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.debug("========== addToCartTest Completed ==========");
    }
}