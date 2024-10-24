package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public WebElement removeButton;

    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div[1]/div[3]/div[2]/div[2]/div")
    public WebElement cartItemPrice;

    @FindBy(className = "inventory_item_price")
    public WebElement backPackCartPrice;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div[1]/div[3]/div[2]/a/div")
    public WebElement cartItemName;


    public void clickOnContinueShopping() {
        continueShoppingButton.click();
    }

    public void clickOnCheckoutButton() {
        checkoutButton.click();
    }

    public void clickOnRemoveButton() {
        removeButton.click();
    }

}
