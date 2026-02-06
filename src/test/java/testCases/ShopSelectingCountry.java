package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ProductPage;
import testBase.BaseClass;

public class ShopSelectingCountry extends BaseClass {

    // 🔹 Log4j Logger
    private static final Logger logger = LogManager.getLogger(ShopSelectingCountry.class);

    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void selectCountryTest() {

        logger.debug("========== selectCountryTest Started ==========");

        try {
            logger.debug("Initializing HomePage");
            HomePage hp = new HomePage(driver);

            logger.debug("Clicking on Country dropdown");
            hp.clickCountryDropdown();

            logger.debug("Scrolling and selecting country: Bahamas");
            hp.scrollToTextAndClick("Bahamas");

            logger.debug("Entering user name");
            hp.enterName();

            logger.debug("Clicking on 'Let's Shop' button");
            hp.clickLetsShop();

            logger.debug("Initializing ProductPage");
            ProductPage pp = new ProductPage(driver);

            logger.debug("Fetching Product Page title");
            String products = pp.getProductPageTitle();
            logger.debug("Product Page title received: {}", products);

            // 🔹 Assertion with logging & retry support
            try {
                Assert.assertEquals(products, "Products");
                logger.debug("Assertion Passed: Product page displayed successfully");
            } catch (AssertionError ae) {
                logger.error(
                        "Assertion Failed: Expected 'Products' but found '{}'",
                        products,
                        ae
                );
                throw ae; // required for RetryAnalyzer
            }

        } catch (Exception e) {
            logger.error("Unexpected exception occurred in selectCountryTest", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.debug("========== selectCountryTest Completed ==========");
    }
}