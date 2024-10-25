package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div[1]/div[4]/div[2]/div[2]/div")
    public WebElement cartItemTwoPrice;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div[1]/div[5]/div[2]/div[2]/div")
    public WebElement cartItemThreePrice;

    @FindBy(className = "inventory_item_price")
    public WebElement backPackCartPrice;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div[1]/div[3]/div[2]/a/div")
    public WebElement cartItemName;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div[1]/div[4]/div[2]/a/div")
    public WebElement cartItemNameTwo;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div[1]/div[5]/div[2]/a/div")
    public WebElement cartItemNameThree;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div[1]/div[3]/div[1]")
    public WebElement itemOneQuantity;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div[1]/div[4]/div[1]")
    public WebElement itemTwoQuantity;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div[1]/div[5]/div[1]")
    public WebElement itemThreeQuantity;

    @FindBy(className = "cart_list")
    public List<WebElement> cartList;


    public void clickOnContinueShopping() {
        continueShoppingButton.click();
    }

    public void clickOnCheckoutButton() {
        checkoutButton.click();
    }

    public void clickOnRemoveButton() {
        removeButton.click();
    }

    public void printAllCartItems() {
        for (int i = 0; i < cartList.size(); i++) {
            System.out.println(cartList.get(i).getText());
        }
    }

}
