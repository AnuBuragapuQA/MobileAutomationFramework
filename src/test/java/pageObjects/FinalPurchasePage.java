package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

public class FinalPurchasePage extends BasePage {


	public FinalPurchasePage(AndroidDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(name = "q")
    WebElement alerttext;
	
	//actions
	
	public void setText() {
		alerttext.sendKeys("CloudBerry QA Bootcamp");
		alerttext.sendKeys(Keys.ENTER);
	}

	public void enterText() {
		// TODO Auto-generated method stub
		
	}
	
}