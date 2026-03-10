@echo off
SETLOCAL

echo ========================================
echo   Appium Mobile Test Launcher
echo ========================================
echo.

REM ====== CONFIGURATION ======
SET ANDROID_HOME=%LOCALAPPDATA%\Android\Sdk
SET EMULATOR_NAME=Pixel_9_Pro
SET APPIUM_CMD=C:\Users\Extreme\AppData\Roaming\npm\appium.cmd
SET APPIUM_PORT=4723
REM ===========================

echo [1/4] Cleaning up old processes...

REM Kill old emulator instances
tasklist /FI "IMAGENAME eq emulator.exe" 2>NUL | find /I /N "emulator.exe">NUL
if "%ERRORLEVEL%"=="0" (
    echo Stopping old emulator...
    taskkill /F /IM emulator.exe >nul 2>&1
    timeout /t 2 /nobreak >nul
)

REM Kill processes on Appium port
for /f "tokens=5" %%i in ('netstat -ano ^| findstr :%APPIUM_PORT%') do (
    echo Stopping process on port %APPIUM_PORT%...
    taskkill /F /PID %%i >nul 2>&1
)

echo [OK] Cleanup complete
echo.

echo [2/4] Starting Android Emulator: %EMULATOR_NAME%...
start "Android Emulator" /MIN "%ANDROID_HOME%\emulator\emulator.exe" -avd %EMULATOR_NAME%

echo Waiting for emulator to boot...
"%ANDROID_HOME%\platform-tools\adb.exe" wait-for-device

:bootcheck
for /f "delims=" %%i in ('"%ANDROID_HOME%\platform-tools\adb.exe" shell getprop sys.boot_completed 2^>nul') do set boot=%%i
if "%boot%"=="1" goto bootdone
timeout /t 3 /nobreak >nul
goto bootcheck

:bootdone
echo [OK] Emulator fully booted
echo.

echo [3/4] Starting Appium Server on port %APPIUM_PORT%...
start "Appium Server" /MIN "%APPIUM_CMD%" --port %APPIUM_PORT%

timeout /t 5 /nobreak >nul

REM Verify Appium is running
netstat -ano | findstr :%APPIUM_PORT% >nul 2>&1
if %ERRORLEVEL% equ 0 (
    echo [OK] Appium Server started successfully
) else (
    echo [ERROR] Appium Server failed to start
    pause
    exit /b 1
)

echo.
echo [4/4] Verifying setup...

REM Check emulator
"%ANDROID_HOME%\platform-tools\adb.exe" devices | findstr "emulator-" >nul 2>&1
if %ERRORLEVEL% equ 0 (
    echo [OK] Emulator connected
) else (
    echo [WARN] Emulator not detected
)

echo.
echo ========================================
echo   ENVIRONMENT READY!
echo ========================================
echo.
echo Appium Server: Running on port %APPIUM_PORT%
echo Android Emulator: %EMULATOR_NAME%
echo.

echo.
echo Press any key to stop all services...
pause >nul

echo.
echo Stopping services...
taskkill /F /IM emulator.exe >nul 2>&1
for /f "tokens=5" %%i in ('netstat -ano ^| findstr :%APPIUM_PORT%') do taskkill /F /PID %%i >nul 2>&1

echo Done!
timeout /t 2 /nobreak >nul
