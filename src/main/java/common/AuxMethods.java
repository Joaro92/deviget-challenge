package common;

import org.javatuples.Pair;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.Map;

public class AuxMethods {
    private static String objectRepositoryPath = "src/main/objectRepository/";
    private static Yaml yaml = new Yaml();

    public static WebElement getWebElementFrom(WebDriver driver, String testObject) {
        Pair<String, String> selector = AuxMethods.getSelectorFromFile(testObject);
        return driver.findElement(getByFrom(selector));
    }

    public static boolean waitForElementPresent(WebDriver driver, int seconds, String testObject) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        Pair<String, String> selector = getSelectorFromFile(testObject);

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(getByFrom(selector)));
            return driver.findElements(getByFrom(selector)).size() > 0;
        }
        catch (TimeoutException e) {
            return false;
        }
    }

    public static void waitUntilElementDisappears(WebDriver driver, int seconds, String testObject) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        Pair<String, String> selector = getSelectorFromFile(testObject);
        By locator = getByFrom(selector);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean isElementPresent(WebDriver driver, String testObject) {
        Pair<String, String> selector = getSelectorFromFile(testObject);
        return driver.findElements(getByFrom(selector)).size() > 0;
    }

    public static void scrollTo(WebDriver driver, String testObject) throws InterruptedException {
        Pair<String, String> selector = getSelectorFromFile(testObject);

        WebElement element = driver.findElement(getByFrom(selector));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(300);
    }

    // --------------- Private Methods ---------------

    //Return the By Class used to find Web elements
    private static By getByFrom(Pair<?,?> selector) {
        String findBy = selector.getValue0().toString();
        String selectorPath = selector.getValue1().toString();

        switch (findBy) {
            case "xpath":
                return By.xpath(selectorPath);
            case "css":
                return By.cssSelector(selectorPath);
            default:
                throw new IllegalArgumentException(findBy + " selector method is invalid");
        }
    }

    //Return a Pair containing how to find the Element and the path to it
    private static Pair<String, String> getSelectorFromFile(String testObject) {
        Pair<String, String> elementSelector = null;
        String filePath = objectRepositoryPath + testObject + ".yaml";

        try {
            Reader reader = new FileReader(filePath);
            Map<String, Object> obj = yaml.load(reader);
            elementSelector = new Pair<>(obj.keySet().toArray()[0].toString(), obj.values().toArray()[0].toString());
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return elementSelector;
    }
}
