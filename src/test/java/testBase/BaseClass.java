package testBase;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseClass {

    public AndroidDriver driver;

    // 🔹 Log4j Logger
    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    @BeforeClass
    @Parameters({ "deviceName", "systemPort" })
    public void ConfigureAppium(String dn, String sysPort) {
        logger.debug("========== Appium Configuration Started ==========");

        try {
            logger.debug("Setting UiAutomator2Options");
            UiAutomator2Options options = new UiAutomator2Options();

            logger.debug("Setting systemPort: {}", sysPort);
            options.setSystemPort(Integer.parseInt(sysPort));

            logger.debug("Setting deviceName: {}", dn);
            options.setDeviceName(dn);

            logger.debug("Setting application path");
            options.setApp(
            		"C:\\Users\\anu11\\myeclipse-workspace_new\\MobileV3\\src\\main\\resources\\General-Store.apk");

            logger.debug("Initializing AndroidDriver");
            driver = new AndroidDriver(
                    new URI("http://127.0.0.1:4723").toURL(),
                    options
            );

            logger.debug("Setting implicit wait");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            logger.debug("AndroidDriver initialized successfully");

        } catch (NumberFormatException e) {
            logger.error("Invalid systemPort value: {}", sysPort, e);
            throw e;

        } catch (MalformedURLException | URISyntaxException e) {
            logger.error("Appium server URL is invalid", e);
            throw new RuntimeException(e);

        } catch (Exception e) {
            logger.error("Unexpected error during Appium configuration", e);
            throw e;
        }

        logger.debug("========== Appium Configuration Completed ==========");
    }

    @AfterClass
    public void tearDown() {
        logger.debug("========== Tear Down Started ==========");

        try {
            if (driver != null) {
                logger.debug("Quitting AndroidDriver");
                driver.quit();
            }
        } catch (Exception e) {
            logger.error("Error while quitting driver", e);
        }

        logger.debug("========== Tear Down Completed ==========");
    }

    // ================= Screenshot ====================

    public String captureScreen(String tname) {
        logger.debug("Capturing screenshot for test: {}", tname);

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);

            String targetFilePath = System.getProperty("user.dir")
                    + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

            File targetFile = new File(targetFilePath);
            sourceFile.renameTo(targetFile);

            logger.debug("Screenshot saved at: {}", targetFilePath);
            return targetFilePath;

        } catch (Exception e) {
            logger.error("Failed to capture screenshot for test: {}", tname, e);
            return null;
        }
    }
}