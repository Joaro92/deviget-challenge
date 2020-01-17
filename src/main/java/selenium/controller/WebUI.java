package selenium.controller;

import common.AuxMethods;
import org.javatuples.Pair;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class WebUI {
    private static WebDriver driver;

    //Initializing WebDriver Wrapper
    public static void init(WebDriver _driver) {
        driver = _driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
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

    public static void sendKeys(String testObject, String text) {
        getWebElementFrom(testObject).sendKeys(text);
    }


    //-------------- Private Methods --------------

    private static WebElement getWebElementFrom(String testObject) {
        Pair<String, String> selector = AuxMethods.getSelectorFromFile(testObject);
        return AuxMethods.findWebElement(driver, selector);
    }
}
