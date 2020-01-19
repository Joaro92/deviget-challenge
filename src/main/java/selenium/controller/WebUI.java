package selenium.controller;

import common.AuxMethods;
import org.openqa.selenium.WebDriver;

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
}
