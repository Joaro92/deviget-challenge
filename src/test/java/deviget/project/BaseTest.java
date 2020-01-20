package deviget.project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import selenium.controller.WebUI;

public class BaseTest {
    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        WebUI.init(new ChromeDriver());
        System.out.println("\n-----------------------------------------------\n");
    }

    @AfterEach
    public void teardown() {
        WebUI.closeBrowser();
    }
}
