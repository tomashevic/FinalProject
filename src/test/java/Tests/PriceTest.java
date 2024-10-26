package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PriceTest extends BaseTest {

    @BeforeMethod
    public void pricePageSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        checkoutFormPage = new CheckoutFormPage();
        checkoutOverviewPage = new CheckoutOverviewPage();

    }

    @Test(priority = 10)
    public void verifyThatItemInventoryPriceAndItemCartPriceMatch() {

        String username = "standard_user";
        String password = "secret_sauce";
        String firstName = "John";
        String lastName = "Doe";
        String postalCode = "90210";

        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        String inventoryURL = "https://www.saucedemo.com/inventory.html";
        String cartURL = "https://www.saucedemo.com/cart.html";
        String checkoutFormURL = "https://www.saucedemo.com/checkout-step-one.html";
        String checkoutOverviewURL = "https://www.saucedemo.com/checkout-step-two.html";
        String itemInventoryPrice = "Price of a item on inventory page: ";
        String priceWithoutTax = "Without tax ";

        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);

        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));
        Assert.assertTrue(isDisplayed(inventoryPage.backpackInventoryPrice));

        inventoryPage.clickOnAddToCartBackpack();
        Assert.assertEquals(inventoryPage.shoppingCartBadge.getText(), "1");

        inventoryPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertEquals(inventoryPage.backpackInventoryPrice.getText(), cartPage.backPackCartPrice.getText());

        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutFormURL);

        checkoutFormPage.inputFirstName(firstName);
        Assert.assertTrue(isDisplayed(checkoutFormPage.displayedFirstName));
        checkoutFormPage.inputLastName(lastName);
        Assert.assertTrue(isDisplayed(checkoutFormPage.displayedLastName));
        checkoutFormPage.inputPostalCode(postalCode);
        Assert.assertTrue(isDisplayed(checkoutFormPage.displayedPostalCode));
        checkoutFormPage.clickOnContinueButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), checkoutFormURL);
        Assert.assertEquals(driver.getCurrentUrl(), checkoutOverviewURL);
        Assert.assertTrue(isDisplayed(checkoutOverviewPage.cartItem));
        Assert.assertTrue(isDisplayed(checkoutOverviewPage.cartQuantityField));
        System.out.println(itemInventoryPrice + inventoryPage.backpackInventoryPrice.getText());
        System.out.println(priceWithoutTax + checkoutOverviewPage.itemTotalPrice.getText());


    }


    @Test(priority = 20)
    public void verifyThatItemInventoryPriceAndItemCartPriceDiffer() {

        String username = "visual_user";
        String password = "secret_sauce";
        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        String inventoryURL = "https://www.saucedemo.com/inventory.html";
        String cartURL = "https://www.saucedemo.com/cart.html";
        String itemInventoryPrice = "Price of a item on inventory page: ";
        String itemCartPrice = "Price of a item on cart page: ";

        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);

        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));
        Assert.assertTrue(isDisplayed(inventoryPage.inventoryItemPrice));

        inventoryPage.clickOnAddToCartBoltTshirt();
        Assert.assertTrue(isDisplayed(inventoryPage.removeBoltTshirt));
        Assert.assertTrue(isDisplayed(inventoryPage.shoppingCartBadge));

        System.out.println(itemInventoryPrice + inventoryPage.inventoryItemPrice.getText());

        inventoryPage.clickOnCartIcon();
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertEquals(driver.getCurrentUrl(), cartURL);

        Assert.assertEquals(cartPage.cartItemName.getText(), inventoryPage.inventoryItemName.getText());
        System.out.println(itemCartPrice + cartPage.cartItemPrice.getText());

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
