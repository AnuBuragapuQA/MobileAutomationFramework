package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPage extends BasePage
{
	
  //Constructor
  public CartPage(AndroidDriver driver)
  {
	  super(driver);
  }
  
  //Locaters 
   
  @AndroidFindBy(className = "android.widget.CheckBox")
	WebElement checkBox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/alertTitle")
	WebElement alerttext;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	WebElement termsButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	WebElement addedProductName;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	WebElement btnToWeb;
   
	// actions
		public void longClickTermsButton() {
			((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) termsButton).getId()));
		}

		public void clickTCCheckBox() {
			checkBox.click();
		}

		public String getAlertTitle() {
			return alerttext.getText();
		}

		public String getAddedproductName() throws InterruptedException {
			Thread.sleep(1000);
			return addedProductName.getText();
		}

		public void clickVisitToWeb() throws InterruptedException {
			btnToWeb.click();
			Thread.sleep(1000);
		}

	
}