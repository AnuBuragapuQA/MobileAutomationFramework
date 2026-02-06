package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.CartPage;
import testBase.BaseClass;

public class TermsAndConditions extends BaseClass {

    // 🔹 Log4j Logger
    private static final Logger logger = LogManager.getLogger(TermsAndConditions.class);

    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void termsAndConditonTest() {

        logger.debug("========== termsAndConditonTest Started ==========");

        try {
            logger.debug("Initializing HomePage");
            HomePage hp = new HomePage(driver);

            logger.debug("Entering user name");
            hp.enterName();

            logger.debug("Clicking on 'Let's Shop' button");
            hp.clickLetsShop();

            logger.debug("Initializing ProductPage");
            ProductPage pp = new ProductPage(driver);

            logger.debug("Scrolling to product: Jordan 6 Rings");
            pp.scrollToText("Jordan 6 Rings");

            logger.debug("Adding product to cart");
            pp.clickAddToCart();

            logger.debug("Navigating to Cart page");
            pp.clickViewCart();

            logger.debug("Initializing CartPage");
            CartPage cp = new CartPage(driver);

            logger.debug("Clicking Terms & Conditions checkbox");
            cp.clickTCCheckBox();

            logger.debug("Performing long click on Terms & Conditions button");
            cp.longClickTermsButton();

            logger.debug("Fetching alert title");
            String alertTitle = cp.getAlertTitle();
            logger.debug("Alert title displayed: {}", alertTitle);

            // 🔹 Assertion with logging & retry support
            try {
                Assert.assertEquals(alertTitle, "Terms Of Conditions");
                logger.debug("Assertion Passed: Terms & Conditions alert displayed correctly");
            } catch (AssertionError ae) {
                logger.error(
                        "Assertion Failed: Expected 'Terms Of Conditions' but found '{}'",
                        alertTitle,
                        ae
                );
                throw ae; // required for RetryAnalyzer
            }

        } catch (Exception e) {
            logger.error("Unexpected exception occurred in termsAndConditonTest", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.debug("========== termsAndConditonTest Completed ==========");
    }
}