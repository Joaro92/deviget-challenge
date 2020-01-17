package common;

import org.javatuples.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.Map;

public class AuxMethods {
    private static String objectRepositoryPath = "src/main/objectRepository/";
    private static Yaml yaml = new Yaml();

    public static Pair<String, String> getSelectorFromFile(String testObject) {
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

    public static WebElement findWebElement(WebDriver driver, Pair<?, ?> selector) {
        WebElement elem = null;
        String findBy = selector.getValue0().toString();
        String selectorPath = selector.getValue1().toString();

        switch (findBy) {
            case "xpath":
                elem = driver.findElement(By.xpath(selectorPath));
                break;
            case "css":
                elem = driver.findElement(By.cssSelector(selectorPath));
                break;
            default:
                throw new IllegalArgumentException(findBy + " selector method is invalid");
        }

        return elem;
    }
}
