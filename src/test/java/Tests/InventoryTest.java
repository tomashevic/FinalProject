package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HamburgerMenuPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest {

    @BeforeMethod
    public void inventoryPageSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        hamburgerMenuPage = new HamburgerMenuPage();

        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
    }

    @Test(priority = 10)
    public void verifyItemSorting() {
        String inventoryURL = "https://www.saucedemo.com/inventory.html";


        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertEquals(inventoryPage.selectedSortOrder.getText(), "Name (A to Z)");
        inventoryPage.clickOnSortButton();
        inventoryPage.sortByReverseAlphabeticalOrder();
        Assert.assertEquals(inventoryPage.selectedSortOrder.getText(), "Name (Z to A)");
        inventoryPage.clickOnSortButton();
        inventoryPage.sortByLowestPrice();
        Assert.assertEquals(inventoryPage.selectedSortOrder.getText(), "Price (low to high)");
        inventoryPage.clickOnSortButton();
        inventoryPage.sortByHighestPrice();
        Assert.assertEquals(inventoryPage.selectedSortOrder.getText(), "Price (high to low)");
        inventoryPage.clickOnSortButton();
        inventoryPage.sortByAlphabeticalOrder();
        Assert.assertEquals(inventoryPage.selectedSortOrder.getText(), "Name (A to Z)");


    }

    @Test(priority = 20)
    public void verifyThatResetAppStateRemovesAllItemsFromCart() {
        String inventoryURL = "https://www.saucedemo.com/inventory.html";
        String cartURL = "https://www.saucedemo.com/cart.html";


        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));

        inventoryPage.clickOnAddToCartBackpack();
        Assert.assertEquals(inventoryPage.shoppingCartBadge.getText(), "1");

        inventoryPage.clickOnAddToCartBikeLight();
        Assert.assertEquals(inventoryPage.shoppingCartBadge.getText(), "2");

        inventoryPage.clickOnAddToCartBoltTshirt();
        Assert.assertEquals(inventoryPage.shoppingCartBadge.getText(), "3");

        inventoryPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertTrue(isDisplayed(cartPage.removeButton));

        Assert.assertEquals(cartPage.cartItemName.getText(), "Sauce Labs Backpack");
        Assert.assertEquals(cartPage.itemOneQuantity.getText(), "1");
        Assert.assertEquals(cartPage.cartItemPrice.getText(), "$29.99");
        Assert.assertEquals(cartPage.cartItemNameTwo.getText(), "Sauce Labs Bike Light");
        Assert.assertEquals(cartPage.itemTwoQuantity.getText(), "1");
        Assert.assertEquals(cartPage.cartItemTwoPrice.getText(), "$9.99");
        Assert.assertEquals(cartPage.cartItemNameThree.getText(), "Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(cartPage.itemThreeQuantity.getText(), "1");
        Assert.assertEquals(cartPage.cartItemThreePrice.getText(), "$15.99");
        cartPage.printAllCartItems();
        inventoryPage.clickOnBurgerMenuButton();
        hamburgerMenuPage.clickOnResetAppButton();
        driver.navigate().refresh();

        Assert.assertEquals(driver.getCurrentUrl(), cartURL);
        Assert.assertFalse(isDisplayed(inventoryPage.shoppingCartBadge));
        Assert.assertFalse(isDisplayed(cartPage.cartItemName));
        Assert.assertFalse(isDisplayed(cartPage.itemOneQuantity));
        Assert.assertFalse(isDisplayed(cartPage.cartItemPrice));
        Assert.assertFalse(isDisplayed(cartPage.cartItemNameTwo));
        Assert.assertFalse(isDisplayed(cartPage.itemTwoQuantity));
        Assert.assertFalse(isDisplayed(cartPage.cartItemTwoPrice));
        Assert.assertFalse(isDisplayed(cartPage.cartItemNameThree));
        Assert.assertFalse(isDisplayed(cartPage.itemThreeQuantity));
        Assert.assertFalse(isDisplayed(cartPage.cartItemThreePrice));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
