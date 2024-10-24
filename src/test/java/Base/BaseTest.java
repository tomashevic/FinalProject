package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;

public class BaseTest {


    public static WebDriver driver;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public CheckoutFormPage checkoutFormPage;
    public CheckoutOverviewPage checkoutOverviewPage;
    public CheckoutCompletePage checkoutCompletePage;
    public ItemPage itemPage;
    public HamburgerMenuPage hamburgerMenuPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();


    }

    public boolean isDisplayed(WebElement element) {

        boolean displayed = false;

        try{
            displayed = element.isDisplayed();
        } catch (Exception e) {

        }
        return displayed;
    }


}
