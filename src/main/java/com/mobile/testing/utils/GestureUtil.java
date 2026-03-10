package com.mobile.testing.utils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.Collections;

/**
 * Utility class for gesture operations like swipe, scroll, tap
 */
public class GestureUtil {
    
    private AppiumDriver driver;
    
    public GestureUtil(AppiumDriver driver) {
        this.driver = driver;
    }
    
    /**
     * Swipe from one point to another
     */
    public void swipe(Point start, Point end, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        
        driver.perform(Collections.singletonList(swipe));
    }
    
    /**
     * Swipe up on the screen
     */
    public void swipeUp() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        
        swipe(new Point(startX, startY), new Point(startX, endY), Duration.ofMillis(800));
    }
    
    /**
     * Swipe down on the screen
     */
    public void swipeDown() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.2);
        int endY = (int) (size.height * 0.8);
        
        swipe(new Point(startX, startY), new Point(startX, endY), Duration.ofMillis(800));
    }
    
    /**
     * Swipe left on the screen
     */
    public void swipeLeft() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.8);
        int endX = (int) (size.width * 0.2);
        int y = size.height / 2;
        
        swipe(new Point(startX, y), new Point(endX, y), Duration.ofMillis(800));
    }
    
    /**
     * Swipe right on the screen
     */
    public void swipeRight() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.2);
        int endX = (int) (size.width * 0.8);
        int y = size.height / 2;
        
        swipe(new Point(startX, y), new Point(endX, y), Duration.ofMillis(800));
    }
    
    /**
     * Tap at a specific point
     */
    public void tap(Point point) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), point.x, point.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        
        driver.perform(Collections.singletonList(tap));
    }
    
    /**
     * Long press at a specific point
     */
    public void longPress(Point point, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPress = new Sequence(finger, 1);
        
        longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), point.x, point.y));
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), point.x, point.y));
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        
        driver.perform(Collections.singletonList(longPress));
    }
}
