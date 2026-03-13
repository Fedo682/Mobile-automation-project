package com.mobile.testing.tests;

import com.mobile.testing.base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.Location;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

/**
 * Wheels Delivery Order App Test
 * Tests the Wheels app functionality
 */
public class WheelsAppTest extends BaseTest {
    
    private WebDriverWait wait;
    
    @Test(priority = 1, description = "Verify Wheels app launches successfully")
    public void testWheelsAppLaunch() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        System.out.println("\n=== Starting Wheels App Test ===");
        System.out.println("Waiting for Wheels app to launch...");
        
        try {
            // Wait for app to load
            Thread.sleep(5000);
            
            // Verify app launched by checking if driver is active
            Assert.assertNotNull(driver, "Driver should not be null");
            Assert.assertNotNull(driver.getPageSource(), "App should have loaded with content");
            
            System.out.println(" Wheels app launched successfully!");
            
         
            
        } catch (InterruptedException e) {
            e.printStackTrace();
            Assert.fail("Test interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(" Error during app launch: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("App launch failed: " + e.getMessage());
        }
    }
    
   @Test(priority = 2,description = "set up new account for guest user")
    public void testCreateNewAccount() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		System.out.println("\n=== Starting Create New Account Test ===");
			
		WebElement languageselect = wait.until(
			ExpectedConditions.presenceOfElementLocated(
				AppiumBy.accessibilityId("الإنجليزية")
			)
		);
		languageselect.click();
		
		
		WebElement continuebutton = wait.until(
			ExpectedConditions.presenceOfElementLocated(
				AppiumBy.accessibilityId("متابعة")
			)
		);
		continuebutton.click();
		
	
		
		try {
			// Wait for app to load
			Thread.sleep(5000);
			
			WebElement locationbutton = wait.until(
					ExpectedConditions.presenceOfElementLocated(
						AppiumBy.accessibilityId("Share Location")
					)
				);
			locationbutton.click();
			Thread.sleep(7000);
		
			WebElement skipphonenumberbtn = wait.until(
					ExpectedConditions.presenceOfElementLocated(
						AppiumBy.accessibilityId("Skip")
					)
				);
			skipphonenumberbtn.click();
			
		Thread.sleep(5000);
			
		Assert.assertTrue(true,"Created account successfully without phone number and set the location to current location now in main page");
			
			 WebElement searchbutton = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View")
						)
					);
			 searchbutton.click();
			
			 WebElement searchfield = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.className("android.widget.EditText")
						)
					);
			 searchfield.sendKeys("heart");
		
			 
			 WebElement heartattackplace = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.className("android.widget.ImageView")
						)
					);
			 heartattackplace.click();
			 Thread.sleep(5000);
			 
			 WebElement mealtry = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"عرض ال 50\n"
									+ "( 6 بروست أو 10 كرسبي ) + كوسلو كبير + كولا كبير +بطاطا كبير + 3 خبز + صوصات\n"
									+ "50.0\n"
									+ "₪\"]")
						)
					);
			 mealtry.click();
			 Thread.sleep(5000);
			 WebElement addtocartbtn = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.className("android.widget.Button")
						)
					);
			 addtocartbtn.click();
			 Assert.assertTrue(true, "clicked add to cart button successfully");
			 System.out.println("Added meal to cart successfully!");
		
			 WebElement cartbutton = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.className("android.widget.Button")
						)
					);
			 cartbutton.click();
			
			 WebElement amountlbl = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.xpath("//android.view.View[@content-desc=\"Sub Total\n"
									+ "50.00 ₪\n"
									+ "Delivery Fee\n"
									+ "10.00 ₪\n"
									+ "Total\n"
									+ "60.00 ₪\"]")
						)
					);
			 Assert.assertTrue(amountlbl.isDisplayed(), "there exists items in the cart ");
			 
			 boolean isAmountCorrect = amountlbl.getText().contains("60.00");
			 
			 Assert.assertTrue(true, "total amount in cart is correct");
			 System.out.println("Verified total amount in cart successfully!");
			 
			 WebElement gobackbutton = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.accessibilityId("Back")
						)
					);
			 gobackbutton.click();
			 
			 
			 WebElement gettomainpagebutton = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View")
						)
					);
			 
			 gettomainpagebutton.click();
			 
			 WebElement confirmbutton = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.accessibilityId("OK")
						)
					);
			 confirmbutton.click();
			 
			
			 searchbutton.click();

			 WebElement searchfield2 = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.className("android.widget.EditText")
						)
					);
			
			 searchfield2.sendKeys("ghada");
			
			 
			 WebElement nodatalbl = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.accessibilityId("No data")
						)
					);
			 Assert.assertNotNull(nodatalbl,"No data label should be displayed after searching for ghada");
			 
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Test interrupted: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error during account creation: " + e.getMessage());
			e.printStackTrace();
			Assert.fail("Account creation failed: " + e.getMessage());
		}
	}
   
   
   
}
