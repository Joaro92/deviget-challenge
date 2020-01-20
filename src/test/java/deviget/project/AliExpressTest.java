package deviget.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import selenium.controller.WebUI;

public class AliExpressTest extends BaseTest {
    @Test
    public void test() throws Exception {
        WebUI.goToURL("https://www.aliexpress.com");

        //Search if Popup shown and close it
        boolean isPresent = WebUI.waitForElement("aliexpress/closePopupButton", 2);
        if (isPresent) {
            WebUI.clickOn("aliexpress/closePopupButton");
        }

        WebUI.typeIn("aliexpress/searchInput", "iPhone");
        WebUI.typeIn("aliexpress/searchInput", Keys.chord(Keys.ENTER));

        //Search again for the Popup if present and close it
        isPresent = WebUI.waitForElement("aliexpress/closePopup2Button", 2);
        if (isPresent) {
            WebUI.clickOn("aliexpress/closePopup2Button");
        }

        //Scroll down until Page 2 button is found. 30 PAGE_DOWN maximum
        WebUI.scrollDownUntilElementPresent("aliexpress/pageTwoButton", 30);
        WebUI.clickOn("aliexpress/pageTwoButton");

        WebUI.waitUntilElementHasDisappeared("aliexpress/loadingIndicator", 10);

        //Assert that Ads are displayed in the Page
        Assertions.assertTrue(WebUI.isElementVisible("aliexpress/p4pAds"));

        WebUI.scrollDownTo("aliexpress/firstP4pAd");
        WebUI.clickOn("aliexpress/firstP4pAd");

        WebUI.switchToCurrentTab();

        //If Buy Now button is visible then the iPhone results page has at least one item to be bought
        Assertions.assertTrue(WebUI.isElementVisible("aliexpress/buyNowButton"));
    }
}
