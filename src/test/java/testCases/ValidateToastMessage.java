package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class ValidateToastMessage extends BaseClass {

    // 🔹 Log4j Logger
    private static final Logger logger = LogManager.getLogger(ValidateToastMessage.class);

    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void ValidateToastMessageTest() {

        logger.debug("========== ValidateToastMessageTest Started ==========");

        try {
            logger.debug("Initializing HomePage");
            HomePage hp = new HomePage(driver);

            logger.debug("Clicking on 'Let's Shop' button without entering name");
            hp.clickLetsShop();

            logger.debug("Fetching toast message");
            String msg = hp.getToastMsg();
            logger.debug("Toast message received: {}", msg);

            // 🔹 Assertion with logging & retry support
            try {
                Assert.assertEquals(msg, "Please enter your name");
                logger.debug("Assertion Passed: Correct toast message displayed");
            } catch (AssertionError ae) {
                logger.error(
                        "Assertion Failed: Expected 'Please enter your name' but found '{}'",
                        msg,
                        ae
                );
                throw ae; // required for RetryAnalyzer
            }

        } catch (Exception e) {
            logger.error("Unexpected exception occurred in ValidateToastMessageTest", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.debug("========== ValidateToastMessageTest Completed ==========");
    }
}