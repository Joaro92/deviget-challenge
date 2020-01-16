package deviget.project;

import org.junit.jupiter.api.Test;
import selenium.controller.WebUI;

public class AliExpressTest extends BaseTest {
    @Test
    public void test() {
        WebUI.goToURL("https://www.aliexpress.com");
    }
}
