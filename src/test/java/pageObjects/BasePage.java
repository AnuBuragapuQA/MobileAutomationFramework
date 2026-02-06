package pageObjects;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {

	// constructor
	AndroidDriver driver;
	// This is the constructor for the BasePage class.
	// It takes a AndroidDriver object as an argument, which is used to interact
	// with
	// the browser.

	public BasePage(AndroidDriver driver) {
		this.driver = driver; // The passed driver is assigned to the instance variable driver. This allows
		// the class and its subclasses to use it for browser interactions.
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);

		// PageFactory.initElements(driver, this);
		// The above line initializes the web elements defined in the class using
		// Selenium's
		// PageFactory.
		// PageFactory.initElements() tells Selenium to scan the current class (this)
		// for any @FindBy annotations
		// and connect them to actual elements on the page using the provided driver.
	}

	// locators

	// action methods

	// Generic scroll to text method
	public void scrollToText(String text) {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true))" + ".scrollTextIntoView(\"" + text + "\")"));
	}

	// Generic Scroll and click

	public void scrollToTextAndClick(String text) {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true))" + ".scrollTextIntoView(\"" + text + "\")"));

		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")")).click();
	}

	// Press Android back button
	public void pressBack() {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

	// Switch context (NATIVE_APP / WEBVIEW)
	public void switchContext(String context) {
		driver.context(context);
	}
}
