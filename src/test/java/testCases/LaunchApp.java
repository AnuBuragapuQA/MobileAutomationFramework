package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class LaunchApp extends BaseClass {

    // 🔹 Log4j Logger
    private static final Logger logger = LogManager.getLogger(LaunchApp.class);

    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void launchAppTest() {

        logger.debug("========== LaunchApp Test Started ==========");

        try {
            logger.debug("Initializing HomePage object");
            HomePage hp = new HomePage(driver);

            logger.debug("Verifying Home Page");
            String homePage = hp.verifyHomePage();
            logger.debug("Home Page verification result: {}", homePage);

            // 🔹 Assertion with logging
            try {
                Assert.assertEquals(homePage, "Enter name here");
                logger.debug("Assertion Passed: Home page launched successfully");
            } catch (AssertionError ae) {
                logger.error("Assertion Failed: Home page not launched", ae);
                throw ae; // important for RetryAnalyzer
            }

        } catch (Exception e) {
            logger.error("Unexpected exception occurred in launchAppTest", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.debug("========== LaunchApp Test Completed ==========");
    }
}