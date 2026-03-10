# Appium Mobile Testing Project

Simple Appium test automation for ApiDemos - Alert Dialog testing.

## Quick Start

### 1. Start Environment
```
Double-click: START-ENVIRONMENT.bat
```
This will:
- Start Android Emulator
- Start Appium Server
- Verify everything is ready

### 2. Run Tests
In Eclipse:
```
Right-click: testng-apidemos.xml
Select: Run As → TestNG Suite
```

## Test Scenario

**ApiDemos Alert Dialog Test:**
1. Opens ApiDemos app
2. Navigates: App → Alert Dialogs
3. Clicks: "OK Cancel dialog with a message"
4. Verifies alert text is displayed
5. Tests both OK and Cancel buttons
6. Verifies alert dismissal

## Project Structure

```
AppiumMobileTests/
├── START-ENVIRONMENT.bat          # Start Appium + Emulator
├── testng-apidemos.xml           # Test suite
├── src/
│   ├── main/java/com/mobile/testing/
│   │   ├── base/
│   │   │   └── BaseTest.java     # Test base class
│   │   ├── pages/                # Page objects
│   │   └── utils/                # Utilities
│   └── test/
│       ├── java/com/mobile/testing/tests/
│       │   └── ApiDemosAlertTest.java  # Alert dialog tests
│       └── resources/
│           └── config.properties       # Configuration
└── pom.xml                        # Maven dependencies
```

## Configuration

Edit `src/test/resources/config.properties`:

```properties
android.app.path=C:/Users/Extreme/Desktop/android Apks/ApiDemos-debug.apk
android.app.package=io.appium.android.apis
android.app.activity=io.appium.android.apis.ApiDemos
appium.server.url=http://127.0.0.1:4723
```

## Requirements

- Java 17+
- Android SDK
- Node.js + Appium
- Android Emulator

## Test Report

After running tests, view report:
```
test-output/emailable-report.html
```
