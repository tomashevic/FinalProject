package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void cartPageSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        itemPage = new ItemPage();
        checkoutFormPage = new CheckoutFormPage();
        checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutCompletePage = new CheckoutCompletePage();

        String username = "error_user";
        String password = "secret_sauce";
        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
    }

    @Test(priority = 10)
    public void verifyThatItemCannotBeRemovedFromTheCartFromInventoryOrItemPage() {

        String inventoryURL = "https://www.saucedemo.com/inventory.html";
        String cartURL = "https://www.saucedemo.com/cart.html";
        String itemURL = "https://www.saucedemo.com/inventory-item.html?id=0";

        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);

        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));
        inventoryPage.clickOnCartIcon();
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertFalse(isDisplayed(cartPage.removeButton));

        cartPage.clickOnContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);

        inventoryPage.clickOnAddToCartBikeLight();
        Assert.assertTrue(isDisplayed(inventoryPage.shoppingCartBadge));

        inventoryPage.clickOnRemoveBikeLight();
        Assert.assertTrue(isDisplayed(inventoryPage.removeBikeLight));

        inventoryPage.clickOnBikeLightImage();
        Assert.assertEquals(driver.getCurrentUrl(), itemURL);
        Assert.assertEquals(itemPage.ItemName.getText(), "Sauce Labs Bike Light");

        itemPage.clickOnRemoveButton();
        Assert.assertTrue(isDisplayed(itemPage.removeButton));
        Assert.assertTrue(isDisplayed(inventoryPage.shoppingCartBadge));

        inventoryPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertTrue(isDisplayed(cartPage.cartItemName));
        Assert.assertTrue(isDisplayed(cartPage.cartItemPrice));
        System.out.println(cartPage.cartItemName.getText());
        System.out.println(cartPage.cartItemPrice.getText());

        cartPage.clickOnRemoveButton();
        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));
        Assert.assertFalse(isDisplayed(cartPage.cartItemName));

    }

    @Test(priority = 20)
    public void verifyThatItemCannotBeAddedToACart() {
        String inventoryURL = "https://www.saucedemo.com/inventory.html";
        String cartURL = "https://www.saucedemo.com/cart.html";
        String itemURL = "https://www.saucedemo.com/inventory-item.html?id=3";

        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));
        inventoryPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertFalse(isDisplayed(cartPage.cartItemName));
        Assert.assertFalse(isDisplayed(cartPage.removeButton));

        cartPage.clickOnContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);

        inventoryPage.clickOnAddToCartTshirtRed();
        Assert.assertTrue(isDisplayed(inventoryPage.addToCartTshirtRed));
        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));

        inventoryPage.clickOnShirtRedImageLink();
        Assert.assertEquals(driver.getCurrentUrl(), itemURL);
        Assert.assertEquals(itemPage.ItemName.getText(), "Test.allTheThings() T-Shirt (Red)");

        itemPage.clickOnAddToCartButton();
        Assert.assertFalse(isDisplayed(itemPage.removeButton));
        Assert.assertTrue(isDisplayed(itemPage.addToCartButton));
        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));

        inventoryPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertFalse(isDisplayed(cartPage.cartItemName));
        Assert.assertFalse(isDisplayed(cartPage.removeButton));


    }

    @Test(priority = 30)
    public void verifyThatUserCannotPurchaseItemInCart() {
        String inventoryURL = "https://www.saucedemo.com/inventory.html";
        String cartURL = "https://www.saucedemo.com/cart.html";
        String checkoutFormURL = "https://www.saucedemo.com/checkout-step-one.html";
        String checkoutOverviewURL = "https://www.saucedemo.com/checkout-step-two.html";

        String firstName = "John";
        String postalCode = "90210";

        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));
        inventoryPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertFalse(isDisplayed(cartPage.cartItemName));
        Assert.assertFalse(isDisplayed(cartPage.removeButton));

        cartPage.clickOnContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);

        inventoryPage.clickOnAddToCartOnesie();
        Assert.assertTrue(isDisplayed(inventoryPage.removeOnesie));
        Assert.assertTrue(isDisplayed(inventoryPage.shoppingCartBadge));

        inventoryPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertEquals(cartPage.cartItemName.getText(), "Sauce Labs Onesie");
        Assert.assertTrue(isDisplayed(cartPage.cartItemPrice));

        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutFormURL);
        Assert.assertTrue(isDisplayed(checkoutFormPage.cancelButton));

        checkoutFormPage.inputFirstName(firstName);
        Assert.assertTrue(isDisplayed(checkoutFormPage.displayedFirstName));
        checkoutFormPage.inputPostalCode(postalCode);
        Assert.assertTrue(isDisplayed(checkoutFormPage.displayedPostalCode));
        checkoutFormPage.clickOnContinueButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), checkoutFormURL);
        Assert.assertEquals(driver.getCurrentUrl(), checkoutOverviewURL);
        checkoutOverviewPage.clickOnFinishButton();
        Assert.assertTrue(isDisplayed(checkoutOverviewPage.finishButton));
        Assert.assertEquals(inventoryPage.shoppingCartBadge.getText(), "1");
        inventoryPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertTrue(isDisplayed(cartPage.cartItemName));
        Assert.assertTrue(isDisplayed(cartPage.cartItemPrice));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
