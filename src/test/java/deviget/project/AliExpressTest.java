package deviget.project;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import selenium.controller.WebUI;

public class AliExpressTest extends BaseTest {
    @Test
    public void test() throws Exception {
        WebUI.goToURL("https://www.aliexpress.com");

        //Search if Popup shown and close it
        boolean isPresent = WebUI.waitForElement("aliexpress/closePopupButton", 3);
        if (isPresent) {
            WebUI.clickOn("aliexpress/closePopupButton");
        }

        WebUI.typeIn("aliexpress/searchInput", "iPhone");
        WebUI.typeIn("aliexpress/searchInput", Keys.chord(Keys.ENTER));

        //Search again for the Popup if present and close it
        isPresent = WebUI.waitForElement("aliexpress/closePopup2Button", 3);
        if (isPresent) {
            WebUI.clickOn("aliexpress/closePopup2Button");
        }

        //Scroll down until Page 2 button is found. 30 PAGE_DOWN maximum
        WebUI.scrollDownUntilElementPresent("aliexpress/pageTwoButton", 30);

        WebUI.clickOn("aliexpress/pageTwoButton");
    }
}
