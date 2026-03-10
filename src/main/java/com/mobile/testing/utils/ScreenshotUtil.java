package com.mobile.testing.utils;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for taking screenshots
 */
public class ScreenshotUtil {
    
    /**
     * Take a screenshot and save it to the screenshots folder
     * @param driver - Appium driver instance
     * @param testName - Name of the test for the screenshot file
     * @return Path to the saved screenshot
     */
    public static String takeScreenshot(AppiumDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/";
        
        // Create screenshots directory if it doesn't exist
        File directory = new File(screenshotDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        String filePath = screenshotDir + fileName;
        
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));
            System.out.println("Screenshot saved: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Take a screenshot with default naming
     */
    public static String takeScreenshot(AppiumDriver driver) {
        return takeScreenshot(driver, "screenshot");
    }
}
