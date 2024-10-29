package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

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
    public ExcelReader excelReader;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();

        excelReader = new ExcelReader("FinalTestData.xlsx");

    }

    public boolean isDisplayed(WebElement element) {

        boolean displayed = false;

        try {
            displayed = element.isDisplayed();
        } catch (Exception e) {

        }
        return displayed;
    }


}
