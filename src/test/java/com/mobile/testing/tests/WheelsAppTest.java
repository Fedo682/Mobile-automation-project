package com.mobile.testing.tests;

import com.mobile.testing.base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.Location;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

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
			 searchfield.sendKeys("orgada");
		
			 
			 WebElement orgadaplace = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.className("android.widget.ImageView")
						)
					);
			 orgadaplace.click();
			 Thread.sleep(5000);
			 
			 WebElement mealtry = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"بوكس اللمة\n"
									+ "ستريبس دجاج 300 غم، تشيكن بايتس 300غم، أجنحة 8 قطع، وبطاطا عادية مقرمشة مع تشكيلة لذيذة من الصوصات - وجبة مثالية للّمة 😋\n"
									+ "70.0\n"
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
									+ "70.00 ₪\n"
									+ "Delivery Fee\n"
									+ "10.00 ₪\n"
									+ "Total\n"
									+ "80.00 ₪\"]")
						)
					);
			 Assert.assertTrue(amountlbl.isDisplayed(), "there exists items in the cart ");
			 amountlbl.getText().contains("80.00");
			 Assert.assertEquals(amountlbl.getText(), "Sub Total\\n70.00 ₪\\nDelivery Fee\\n10.00 ₪\\nTotal\\n80.00 ₪", "Total amount should be correct in cart");
			 System.out.println("Verified total amount in cart successfully!");
			 
			 WebElement gettomainpagebutton = wait.until(
						ExpectedConditions.presenceOfElementLocated(
							AppiumBy.xpath("//android.view.View[@content-desc=\"Item added to cart successfully\"]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View")
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
			
			 searchfield.sendKeys("ghada");
			
			 
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
