package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends BasePage {

	// constructor
	// HomePage is a subclass of BasePage.

	// When a HomePage object is created, it needs a AndroidDriver instance to work
	// with.

	// super(driver); is a call to the constructor of the parent class (BasePage),
	// passing along the WebDriver.

	public HomePage(AndroidDriver driver) {
		super(driver);
	}

	// locators

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	WebElement nameField;

	// com.androidsample.generalstore:id/btnLetsShop
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	WebElement letsShopBtn;

	// com.androidsample.generalstore:id/spinnerCountry
	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	WebElement countryDropdown;

	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	WebElement getToastMsg;

	// action methods

	public String verifyHomePage() {
		return nameField.getText();
	}

	public void enterName() {
		nameField.sendKeys("Sid");
	}

	public void clickLetsShop() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(letsShopBtn));
		letsShopBtn.click();

		// letsShopBtn.click();
	}

	public void clickCountryDropdown() {

		countryDropdown.click();

		// letsShopBtn.click();
	}

	public String getToastMsg() {
		return getToastMsg.getAttribute("name");
	}

}
