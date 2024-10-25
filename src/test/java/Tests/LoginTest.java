package Tests;

import Base.BaseTest;
import Pages.HamburgerMenuPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {


    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        hamburgerMenuPage = new HamburgerMenuPage();

    }

    @Test(priority = 10)
    public void verifyThatUserCanLoginUsingValidCredentials() throws InterruptedException {
        String username = "standard_user";
        String password = "secret_sauce";
        String loginURL = driver.getCurrentUrl();
        String expectedURL = "https://www.saucedemo.com/inventory.html";


        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        Assert.assertTrue(inventoryPage.cartIsPresent());
        inventoryPage.clickOnBurgerMenuButton();
        Thread.sleep(1000);
        hamburgerMenuPage.clickOnLogoutButton();

    }

    @Test(priority = 20)
    public void verifyThatUserCanLoginAsProblemUser() throws InterruptedException {
        String username = "problem_user";
        String password = "secret_sauce";
        String loginURL = driver.getCurrentUrl();
        String expectedURL = "https://www.saucedemo.com/inventory.html";


        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        Assert.assertTrue(inventoryPage.cartIsPresent());
        inventoryPage.clickOnBurgerMenuButton();
        Thread.sleep(1000);
        hamburgerMenuPage.clickOnLogoutButton();

    }

    @Test(priority = 30)
    public void verifyThatUserCanLoginAsGlitchUser() throws InterruptedException {
        String username = "performance_glitch_user";
        String password = "secret_sauce";
        String loginURL = driver.getCurrentUrl();
        String expectedURL = "https://www.saucedemo.com/inventory.html";

        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        Assert.assertTrue(inventoryPage.cartIsPresent());
        inventoryPage.clickOnBurgerMenuButton();
        Thread.sleep(1000);
        hamburgerMenuPage.clickOnLogoutButton();

    }

    @Test(priority = 40)
    public void verifyThatUserCanLoginAsErrorUser() throws InterruptedException {
        String username = "error_user";
        String password = "secret_sauce";
        String loginURL = driver.getCurrentUrl();
        String expectedURL = "https://www.saucedemo.com/inventory.html";


        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        Assert.assertTrue(inventoryPage.cartIsPresent());
        inventoryPage.clickOnBurgerMenuButton();
        Thread.sleep(1000);
        hamburgerMenuPage.clickOnLogoutButton();

    }

    @Test(priority = 50)
    public void verifyThatUserCanLoginAsVisualUser() throws InterruptedException {
        String username = "visual_user";
        String password = "secret_sauce";
        String loginURL = driver.getCurrentUrl();
        String expectedURL = "https://www.saucedemo.com/inventory.html";


        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        Assert.assertTrue(inventoryPage.cartIsPresent());
        inventoryPage.clickOnBurgerMenuButton();
        Thread.sleep(1000);
        hamburgerMenuPage.clickOnLogoutButton();

    }

    @Test(priority = 60)
    public void verifyThatUserCannotLoginUsingLockedOutUsername() {
        String username = "locked_out_user";
        String password = "secret_sauce";
        String loginURL = driver.getCurrentUrl();
        String inventoryURL = "https://www.saucedemo.com/inventory.html";


        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertTrue(isDisplayed(loginPage.loginButton));
        Assert.assertTrue(isDisplayed(loginPage.errorMessage));
        Assert.assertFalse(isDisplayed(inventoryPage.burgerMenuButton));
        System.out.println(loginPage.errorMessage.getText());

    }

    @Test(priority = 70)
    public void verifyThatUserCannotLoginUsingInvalidUsername() {
        String username = "nonstandard_user";
        String password = "secret_sauce";
        String loginURL = driver.getCurrentUrl();
        String inventoryURL = "https://www.saucedemo.com/inventory.html";

        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertTrue(isDisplayed(loginPage.loginButton));
        Assert.assertTrue(isDisplayed(loginPage.errorMessage));
        Assert.assertFalse(isDisplayed(inventoryPage.burgerMenuButton));
        System.out.println(loginPage.errorMessage.getText());

    }

    @Test(priority = 80)
    public void verifyThatUserCannotLoginUsingInvalidPassword() {
        String username = "standard_user";
        String password = "nonSecret_sauce";
        String loginURL = driver.getCurrentUrl();
        String inventoryURL = "https://www.saucedemo.com/inventory.html";

        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertTrue(isDisplayed(loginPage.loginButton));
        Assert.assertTrue(isDisplayed(loginPage.errorMessage));
        Assert.assertFalse(isDisplayed(inventoryPage.burgerMenuButton));
        System.out.println(loginPage.errorMessage.getText());

    }

    @Test(priority = 90)
    public void verifyThatUserCannotLoginWithoutUsername() {
        String password = "secret_sauce";
        String loginURL = driver.getCurrentUrl();
        String inventoryURL = "https://www.saucedemo.com/inventory.html";

        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertTrue(isDisplayed(loginPage.loginButton));
        Assert.assertTrue(isDisplayed(loginPage.errorMessage));
        Assert.assertFalse(isDisplayed(inventoryPage.burgerMenuButton));
        System.out.println(loginPage.errorMessage.getText());

    }

    @Test(priority = 91)
    public void verifyThatUserCannotLoginWithoutPassword() {
        String username = "standard_user";
        String loginURL = driver.getCurrentUrl();
        String inventoryURL = "https://www.saucedemo.com/inventory.html";

        loginPage.inputUserName(username);
        loginPage.clickOnLoginButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertTrue(isDisplayed(loginPage.loginButton));
        Assert.assertTrue(isDisplayed(loginPage.errorMessage));
        Assert.assertFalse(isDisplayed(inventoryPage.burgerMenuButton));
        System.out.println(loginPage.errorMessage.getText());

    }

    @Test(priority = 92)
    public void verifyThatUserCannotLoginWithoutUsernameAndPassword() {

        String loginURL = driver.getCurrentUrl();
        String inventoryURL = "https://www.saucedemo.com/inventory.html";

        loginPage.clickOnLoginButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertTrue(isDisplayed(loginPage.loginButton));
        Assert.assertTrue(isDisplayed(loginPage.errorMessage));
        Assert.assertFalse(isDisplayed(inventoryPage.burgerMenuButton));
        System.out.println(loginPage.errorMessage.getText());

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
