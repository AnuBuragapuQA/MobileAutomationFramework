package testCases;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.FinalPurchasePage;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.CartPage;
import testBase.BaseClass;

public class CompletePurchase extends BaseClass {

    // 🔹 Log4j Logger
    private static final Logger logger = LogManager.getLogger(CompletePurchase.class);

    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void completePurchaseTest() {

        logger.debug("========== completePurchaseTest Started ==========");

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

            logger.debug("Clicking on 'Visit to Web' button");
            cp.clickVisitToWeb();

            logger.debug("Waiting for WebView to load");
            Thread.sleep(30000); // can be replaced with WebDriverWait later

            logger.debug("Initializing FinalPurchasePage");
            FinalPurchasePage fpg = new FinalPurchasePage(driver);

            logger.debug("Fetching available contexts");
            Set<String> contexts = driver.getContextHandles();

            for (String contextName : contexts) {
                logger.debug("Available context: {}", contextName);
            }

            // 🔹 Switch to WebView with validation
            try {
                logger.debug("Switching to WEBVIEW");
                fpg.switchContext("WEBVIEW_com.androidsample.generalstore");
                logger.debug("Successfully switched to WEBVIEW");
            } catch (Exception e) {
                logger.error("Failed to switch to WEBVIEW", e);
                Assert.fail("WebView context not available");
            }

            logger.debug("Entering text in WebView");
            fpg.enterText();

            logger.debug("Pressing Back button");
            fpg.pressBack();

            // 🔹 Switch back to Native App
            try {
                logger.debug("Switching back to NATIVE_APP");
                fpg.switchContext("NATIVE_APP");
                logger.debug("Successfully switched back to NATIVE_APP");
            } catch (Exception e) {
                logger.error("Failed to switch back to NATIVE_APP", e);
                Assert.fail("Failed to switch back to Native App");
            }

        } catch (Exception e) {
            logger.error("Unexpected exception occurred in completePurchaseTest", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.debug("========== completePurchaseTest Completed ==========");
    }
}