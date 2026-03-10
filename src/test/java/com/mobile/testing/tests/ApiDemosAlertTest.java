package com.mobile.testing.tests;

import com.mobile.testing.base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class ApiDemosAlertTest extends BaseTest {
    
    private WebDriverWait wait;
    
    @Test(priority = 1, description = "Navigate to Alert Dialogs and verify OK Cancel dialog")
    public void testAlertDialogOkCancel() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        System.out.println("\n=== Starting Alert Dialog Test ===");
        
        try {
            // Wait for app to load
            Thread.sleep(2000);
            
            // Step 1: Click on "App" menu item
            System.out.println("[Step 1] Looking for 'App' menu item...");
            WebElement appMenuItem = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.TextView[@text='App']")
                )
            );
          
            appMenuItem.click();
          
            Thread.sleep(1000);
            
            // Step 2: Click on "Alert Dialogs"
           
            WebElement alertDialogsMenuItem = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.accessibilityId("Alert Dialogs")
                )
            );
           
            alertDialogsMenuItem.click();
           
            Thread.sleep(1000);
            
            // Step 3: Click on "OK Cancel dialog with a message"
           
            WebElement okcanelButton = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.accessibilityId("OK Cancel dialog with a message")
                )
            );
          
            okcanelButton.click();
          
            Thread.sleep(1000);
            
         
            WebElement alertMessage = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.id("android:id/alertTitle")
                )
            );
            
            String actualMessage = alertMessage.getText();
            System.out.println(" Alert message found: \"" + actualMessage + "\"");
            
            // Verify the expected text
            String expectedMessage = "Lorem ipsum dolor sit aie consectetur adipiscing\\nPlloaso mako nuto siwuf cakso dodtos anr koop.";
            
            Assert.assertEquals(actualMessage, expectedMessage,"Alert message should match expected text");
            
            
            
            // Step 5: Click OK button
         
            WebElement okButton = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.id("android:id/button1")
                )
            );
           
            okButton.click();
        
            Thread.sleep(1000);
            
            // Verify alert is closed (back to Alert Dialogs screen)
            System.out.println("\n[Step 6] Verifying alert was dismissed...");
            WebElement backToAlertDialogs = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.Button[@text='OK Cancel dialog with a message']")
                )
            );
            Assert.assertTrue(backToAlertDialogs.isDisplayed(), 
                            "Should be back to Alert Dialogs screen");
            System.out.println("[OK] Alert dismissed successfully - Back to Alert Dialogs screen");
            
            System.out.println("\n=== Test Completed Successfully ===\n");
            
        } catch (Exception e) {
            System.err.println("[ERROR] Test failed: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
    }
}

