package com.mobile.testing.base;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

/**
 * Base Test class that handles Appium driver initialization and teardown
 * Supports both Android and iOS platforms
 */
public class BaseTest {
    
    protected AppiumDriver driver;
    protected Properties properties;
    
    /**
     * Initialize the Appium driver before each test method
     * @param platformName - Platform to test (Android/iOS)
     * @param deviceName - Name of the device
     * @param appPath - Path to the application file
     */
  
	@BeforeMethod
    @Parameters({"platformName", "deviceName", "appPath"})
    public void setUp(@Optional("Android") String platformName, 
                      @Optional("emulator") String deviceName,
                      @Optional String appPath) throws MalformedURLException {
        
        System.out.println("\n========================================");
        System.out.println("Setting up test environment...");
        System.out.println("========================================");
        
        loadProperties();
        
        String appiumServerUrl = properties.getProperty("appium.server.url", "http://127.0.0.1:4723");
        
        try {
            if (platformName.equalsIgnoreCase("Android")) {
                driver = initializeAndroidDriver(appiumServerUrl, deviceName, appPath);
            } else if (platformName.equalsIgnoreCase("iOS")) {
                driver = initializeIOSDriver(appiumServerUrl, deviceName, appPath);
            } else {
                throw new IllegalArgumentException("Unsupported platform: " + platformName);
            }
            
            // Set implicit wait
            int implicitWait = Integer.parseInt(properties.getProperty("implicit.wait", "10"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            
            System.out.println("========================================");
            System.out.println("Test environment ready!");
            System.out.println("========================================\n");
            
        } catch (Exception e) {
            System.err.println("\n========================================");
            System.err.println("ERROR: Failed to initialize driver!");
            System.err.println("========================================");
            System.err.println("Error message: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Initialize Android driver with capabilities
     */
    private AndroidDriver initializeAndroidDriver(String serverUrl, String deviceName, String appPath) 
            throws MalformedURLException {
        
        System.out.println("=== Initializing Android Driver ===");
        System.out.println("Server URL: " + serverUrl);
        System.out.println("Device Name: " + deviceName);
        System.out.println("App Path: " + appPath);
        
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setPlatformName("Android");
        
        // Set app path - SIMPLE MODE: Just install and launch
        String finalAppPath = null;
        if (appPath != null && !appPath.isEmpty()) {
            finalAppPath = appPath;
        } else if (properties.getProperty("android.app.path") != null && 
                   !properties.getProperty("android.app.path").isEmpty()) {
            finalAppPath = properties.getProperty("android.app.path");
        }
        
        if (finalAppPath != null) {
            System.out.println("Using app: " + finalAppPath);
            options.setApp(finalAppPath);
            
            // Set package and activity if provided
            String appPackage = properties.getProperty("android.app.package");
            String appActivity = properties.getProperty("android.app.activity");
            
            if (appPackage != null && !appPackage.isEmpty()) {
                options.setAppPackage(appPackage);
                System.out.println("App Package: " + appPackage);
            }
            
            if (appActivity != null && !appActivity.isEmpty()) {
                options.setAppActivity(appActivity);
                System.out.println("App Activity: " + appActivity);
            } else {
                System.out.println("App Activity: AUTO-DETECT");
            }
        }
        
        // SIMPLE CONFIGURATION - Just the basics
        options.setAutomationName("UiAutomator2");
        options.setAutoGrantPermissions(true);
        
        System.out.println("Creating AndroidDriver...");
        AndroidDriver androidDriver = new AndroidDriver(new URL(serverUrl), options);
        System.out.println("AndroidDriver created successfully!");
        
        return androidDriver;
    }
    
    /**
     * Initialize iOS driver with capabilities
     */
    private IOSDriver initializeIOSDriver(String serverUrl, String deviceName, String appPath) 
            throws MalformedURLException {
        
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(deviceName);
        options.setPlatformName("iOS");
        
        if (appPath != null && !appPath.isEmpty()) {
            options.setApp(appPath);
        } else if (properties.getProperty("ios.app.path") != null) {
            options.setApp(properties.getProperty("ios.app.path"));
        }
        
        // Optional capabilities
        if (properties.getProperty("ios.bundle.id") != null) {
            options.setBundleId(properties.getProperty("ios.bundle.id"));
        }
        
        options.setAutomationName("XCUITest");
        options.setNoReset(false);
        
        return new IOSDriver(new URL(serverUrl), options);
    }
    
    /**
     * Load properties from config file
     */
    private void loadProperties() {
        properties = new Properties();
        try {
            String configPath = System.getProperty("user.dir") + 
                              "/src/test/resources/config.properties";
            FileInputStream fis = new FileInputStream(configPath);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.out.println("Warning: Could not load config.properties. Using default values.");
            setDefaultProperties();
        }
    }
    
    /**
     * Set default properties if config file is not found
     */
    private void setDefaultProperties() {
        properties.setProperty("appium.server.url", "http://127.0.0.1:4723");
        properties.setProperty("implicit.wait", "10");
    }
    
    /**
     * Quit the driver after each test method
     */
 
	@AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println("\n========================================");
            System.out.println("Cleaning up test environment...");
            System.out.println("========================================");
            
            
            try {
                Thread.sleep(10000);// 10 seconds to allow any pending operations to complete before quitting the driver
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            driver.quit();
            System.out.println("Driver closed successfully");
            System.out.println("========================================\n");
        }
    }
    
    /**
     * Get the current driver instance
     */
    public AppiumDriver getDriver() {
        return driver;
    }
}
