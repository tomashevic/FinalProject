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
    public void verifyThatUserCanLoginUsingValidCredentials() {
        for (int i = 1; i <= excelReader.getLastRow("UserPass"); i++) {

            String validUsername = excelReader.getStringData("UserPass", i, 0);
            String validPassword = excelReader.getStringData("UserPass", 1, 1);
            String expectedURL = "https://www.saucedemo.com/inventory.html";

            loginPage.inputUserName(validUsername);
            loginPage.inputPassword(validPassword);
            loginPage.clickOnLoginButton();
            Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
            Assert.assertTrue(isDisplayed(inventoryPage.shoppingCartIcon));
            inventoryPage.clickOnBurgerMenuButton();
            hamburgerMenuPage.clickOnLogoutButton();
        }

    }

    @Test(priority = 20)
    public void verifyThatUserCannotLoginAsLockedOutUser() {
        String lockedOUtUsername = "locked_out_user";
        String validPassword = "secret_sauce";
        String loginURL = driver.getCurrentUrl();

        loginPage.inputUserName(lockedOUtUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertTrue(isDisplayed(loginPage.loginButton));
        Assert.assertTrue(isDisplayed(loginPage.errorMessage));
        Assert.assertFalse(isDisplayed(inventoryPage.burgerMenuButton));
        System.out.println(loginPage.errorMessage.getText());

    }

    @Test(priority = 30)
    public void verifyThatUserCannotLoginUsingInvalidUsername() {
        for (int i = 1; i <= excelReader.getLastRow("UserPass"); i++) {

            String validUsername = excelReader.getStringData("UserPass", i, 2);
            String invalidPassword = excelReader.getStringData("UserPass", 1, 1);
            String loginURL = driver.getCurrentUrl();

            loginPage.inputUserName(validUsername);
            loginPage.inputPassword(invalidPassword);
            loginPage.clickOnLoginButton();
            Assert.assertEquals(driver.getCurrentUrl(), loginURL);
            Assert.assertTrue(isDisplayed(loginPage.loginButton));
            Assert.assertTrue(isDisplayed(loginPage.errorMessage));
            Assert.assertFalse(isDisplayed(inventoryPage.burgerMenuButton));
            System.out.println(loginPage.errorMessage.getText());
        }

    }

    @Test(priority = 40)
    public void verifyThatUserCannotLoginUsingInvalidPassword() {
        for (int i = 1; i <= excelReader.getLastRow("UserPass"); i++) {

            String validUsername = excelReader.getStringData("UserPass", 1, 0);
            String invalidPassword = excelReader.getStringData("UserPass", i, 3);
            String loginURL = driver.getCurrentUrl();

            loginPage.inputUserName(validUsername);
            loginPage.inputPassword(invalidPassword);
            loginPage.clickOnLoginButton();
            Assert.assertEquals(driver.getCurrentUrl(), loginURL);
            Assert.assertTrue(isDisplayed(loginPage.loginButton));
            Assert.assertTrue(isDisplayed(loginPage.errorMessage));
            Assert.assertFalse(isDisplayed(inventoryPage.burgerMenuButton));
            System.out.println(loginPage.errorMessage.getText());


        }
    }

    @Test(priority = 50)
    public void verifyThatUserCannotLoginWithoutUsername() {
        String password = "secret_sauce";
        String loginURL = driver.getCurrentUrl();

        loginPage.usernameField.clear();
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertTrue(isDisplayed(loginPage.loginButton));
        Assert.assertTrue(isDisplayed(loginPage.errorMessage));
        Assert.assertFalse(isDisplayed(inventoryPage.burgerMenuButton));
        System.out.println(loginPage.errorMessage.getText());

    }

    @Test(priority = 60)
    public void verifyThatUserCannotLoginWithoutPassword() {
        String username = "standard_user";
        String loginURL = driver.getCurrentUrl();

        loginPage.inputUserName(username);
        loginPage.passwordField.clear();
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertTrue(isDisplayed(loginPage.loginButton));
        Assert.assertTrue(isDisplayed(loginPage.errorMessage));
        Assert.assertFalse(isDisplayed(inventoryPage.burgerMenuButton));
        System.out.println(loginPage.errorMessage.getText());

    }

    @Test(priority = 70)
    public void verifyThatUserCannotLoginWithoutUsernameAndPassword() {
        String loginURL = driver.getCurrentUrl();

        loginPage.usernameField.clear();
        loginPage.passwordField.clear();
        loginPage.clickOnLoginButton();
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
