package deviget.project;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import selenium.controller.WebUI;

public class AliExpressTest extends BaseTest {
    @Test
    public void test() {
        WebUI.goToURL("https://www.aliexpress.com");

        WebUI.sendKeys("aliexpress/searchInput", "iPhone");
        WebUI.sendKeys("aliexpress/searchInput", Keys.chord(Keys.ENTER));
    }
}
