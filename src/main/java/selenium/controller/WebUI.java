package selenium.controller;

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

    public static void goToURL(String url) {
        if (!(url.startsWith("http://") || url.startsWith("https://"))) {
            throw new IllegalArgumentException("Provided URL must start with 'https://'");
        }

        driver.get(url);
    }
}
