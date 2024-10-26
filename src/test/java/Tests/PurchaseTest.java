package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PurchaseTest extends BaseTest {

    @BeforeMethod
    public void purchasePageSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        hamburgerMenuPage = new HamburgerMenuPage();
        cartPage = new CartPage();
        checkoutFormPage = new CheckoutFormPage();
        checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutCompletePage = new CheckoutCompletePage();

        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

    }

    @Test(priority = 10)
    public void verifyThatUserCanPurchaseAnItem() {
        String inventoryURL = "https://www.saucedemo.com/inventory.html";
        String cartURL = "https://www.saucedemo.com/cart.html";
        String checkoutFormURL = "https://www.saucedemo.com/checkout-step-one.html";
        String checkoutOverviewURL = "https://www.saucedemo.com/checkout-step-two.html";
        String checkoutCompleteURL = "https://www.saucedemo.com/checkout-complete.html";

        String firstName = "John";
        String lastName = "Doe";
        String postalCode = "90210";

        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));

        inventoryPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertFalse(isDisplayed(cartPage.removeButton));

        cartPage.clickOnContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);

        inventoryPage.clickOnAddToCartBackpack();
        Assert.assertEquals(inventoryPage.shoppingCartBadge.getText(), "1");

        inventoryPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertTrue(isDisplayed(cartPage.removeButton));

        cartPage.clickOnCheckoutButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertEquals(driver.getCurrentUrl(), checkoutFormURL);
        Assert.assertTrue(isDisplayed(checkoutFormPage.cancelButton));

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
        checkoutOverviewPage.clickOnFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutCompleteURL);
        Assert.assertTrue(isDisplayed(checkoutCompletePage.backHomeButton));
        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));
        System.out.println(checkoutCompletePage.titleMessage.getText());
        System.out.println(checkoutCompletePage.thankYouMessage.getText());

    }

    @Test(priority = 20)
    public void verifyThatUserCanFinalizePurchaseWithEmptyCart() throws InterruptedException {
        String inventoryURL = "https://www.saucedemo.com/inventory.html";
        String cartURL = "https://www.saucedemo.com/cart.html";
        String checkoutFormURL = "https://www.saucedemo.com/checkout-step-one.html";
        String checkoutOverviewURL = "https://www.saucedemo.com/checkout-step-two.html";
        String checkoutCompleteURL = "https://www.saucedemo.com/checkout-complete.html";

        String firstName = "John";
        String lastName = "Doe";
        String postalCode = "90210";

        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);
        inventoryPage.clickOnBurgerMenuButton();
        Thread.sleep(500);

        hamburgerMenuPage.clickOnResetAppButton();
        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));

        inventoryPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertFalse(isDisplayed(cartPage.removeButton));

        cartPage.clickOnCheckoutButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertEquals(driver.getCurrentUrl(), checkoutFormURL);
        Assert.assertTrue(isDisplayed(checkoutFormPage.cancelButton));

        checkoutFormPage.inputFirstName(firstName);
        Assert.assertTrue(isDisplayed(checkoutFormPage.displayedFirstName));
        checkoutFormPage.inputLastName(lastName);
        Assert.assertTrue(isDisplayed(checkoutFormPage.displayedLastName));
        checkoutFormPage.inputPostalCode(postalCode);
        Assert.assertTrue(isDisplayed(checkoutFormPage.displayedPostalCode));

        checkoutFormPage.clickOnContinueButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), checkoutFormURL);
        Assert.assertEquals(driver.getCurrentUrl(), checkoutOverviewURL);
        Assert.assertFalse(isDisplayed(checkoutOverviewPage.cartItem));
        Assert.assertFalse(isDisplayed(checkoutOverviewPage.cartQuantityField));
        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));

        checkoutOverviewPage.clickOnFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutCompleteURL);
        Assert.assertTrue(isDisplayed(checkoutCompletePage.backHomeButton));
        System.out.println(checkoutCompletePage.titleMessage.getText());
        System.out.println(checkoutCompletePage.thankYouMessage.getText());

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
