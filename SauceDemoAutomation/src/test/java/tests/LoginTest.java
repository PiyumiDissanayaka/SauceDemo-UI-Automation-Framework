package tests;

import Base.BaseTest;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Verification: Login වුණාම URL එක වෙනස් වුණාද බලනවා
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void testAddToCart() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        // මුලින්ම Login වෙන්න ඕනේ
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // ඊටපස්සේ Item එකක් Cart එකට දානවා
        inventoryPage.addBackpackToCart();

        // Verification: Cart එකේ Badge එකේ '1' වැටෙනවද බලනවා
        String badgeCount = inventoryPage.getCartBadgeCount();
        Assert.assertEquals(badgeCount, "1");
    }
}