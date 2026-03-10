package com.mobile.testing.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.mobile.testing.base.BaseTest;
import io.appium.java_client.AppiumDriver;

/**
 * TestNG Listener for handling test events
 * Automatically takes screenshots on test failure
 */
public class TestListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting test: " + result.getName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        
        // Take screenshot on failure
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            AppiumDriver driver = ((BaseTest) testClass).getDriver();
            if (driver != null) {
                ScreenshotUtil.takeScreenshot(driver, result.getName() + "_FAILED");
            }
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but within success percentage: " + result.getName());
    }
    
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test suite started: " + context.getName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test suite finished: " + context.getName());
    }
}
