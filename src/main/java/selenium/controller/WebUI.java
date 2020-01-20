package selenium.controller;

import common.AuxMethods;
import org.openqa.selenium.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebUI {
    private static WebDriver driver;

    //Initializing WebDriver Wrapper
    public static void init(WebDriver _driver) {
        driver = _driver;
    }

    //Close opened Browser instances
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    //-------------- Public Methods --------------

    //Open Browser and navigate to the indicated site
    public static void goToURL(String url) {
        if (!(url.startsWith("http://") || url.startsWith("https://"))) {
            throw new IllegalArgumentException("Provided URL must start with 'https://'");
        }

        driver.get(url);
    }

    //Send text as Key Presses to the specified Input box
    public static void typeIn(String testObject, String text) {
        AuxMethods.getWebElementFrom(driver, testObject).sendKeys(text);
    }

    //Mouse click on the element
    public static void clickOn(String testObject) {
        AuxMethods.getWebElementFrom(driver, testObject).click();
    }

    //Wait for an element to appear in the DOM until timeOut seconds and returns True if present
    public static boolean waitForElement(String testObject, int timeOut) {
        return AuxMethods.waitForElementPresent(driver, timeOut,testObject);
    }

    //Presses the PAGE_DOWN button as many times as specified until element is found
    public static void scrollDownUntilElementPresent(String testObject, int times) throws Exception {
        int counter = 0;
        while(!AuxMethods.isElementPresent(driver, testObject)) {
            if (counter >= times) {
                throw new Exception("Scrolled " + times + " times and the element " + testObject + " was not found");
            }
            counter++;
            driver.findElement(By.tagName("body")).sendKeys(Keys.chord(Keys.PAGE_DOWN));
        }

        scrollDownTo(testObject);
    }

    //Returns if the element is visible in the DOM
    public static boolean isElementVisible(String testObject) {
        if (!AuxMethods.isElementPresent(driver, testObject)) {
            return false;
        }

        return AuxMethods.getWebElementFrom(driver, testObject).isDisplayed();
    }

    //Return the displayed text from the specified element
    public static String getTextFrom(String testObject) {
        return AuxMethods.getWebElementFrom(driver, testObject).getText();
    }

    //Finds the element and then waits until it's removed from the DOM or the time is out
    public static void waitUntilElementHasDisappeared(String testObject, int timeOut) throws InterruptedException {
        if (!AuxMethods.waitForElementPresent(driver, 2, testObject)) {
            return;
        }

        AuxMethods.waitUntilElementDisappears(driver, timeOut, testObject);
    }

    //Scrolls down until element is visible on screen
    public static void scrollDownTo(String testObject) throws InterruptedException {
        AuxMethods.scrollTo(driver, testObject);
    }

    //Switch Driver focus to last opened tab
    public static void switchToCurrentTab() {
        try {Thread.sleep(500);} catch (Exception ignored) {}

        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(windows.size() - 1));
    }

    //Take screenshot of the page
    public static void takeScreenshot() {
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        (new File("screenshots/")).mkdirs();

        String now = (new SimpleDateFormat("yyyy-MM-dd_HHmmss")).format(new Date());
        File newFileDestination = new File("screenshots/" + now + ".png");

        try (var fis = new FileInputStream(screenshotFile);
        var fos = new FileOutputStream(newFileDestination)) {
            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
