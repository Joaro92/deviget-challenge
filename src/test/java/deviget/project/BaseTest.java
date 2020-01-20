package deviget.project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.yaml.snakeyaml.Yaml;
import selenium.controller.WebUI;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

public class BaseTest {
    private static String browser;

    @BeforeAll
    public static void setupClass() {
        try {
            Reader reader = new FileReader("seleniumBrowser.config");
            Map<String, String> obj = (new Yaml()).load(reader);
            browser = obj.get("browser");
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        switch(browser) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            default:
                throw new IllegalArgumentException("Browser is not supported or invalid");
        }
    }

    @BeforeEach
    public void setupTest() {
        switch(browser) {
            case "Chrome":
                WebUI.init(new ChromeDriver());
                break;
            case "Firefox":
                WebUI.init(new FirefoxDriver());
                break;
        }
    }

    @AfterEach
    public void teardown() {
        WebUI.takeScreenshot();
        WebUI.closeBrowser();
    }
}
