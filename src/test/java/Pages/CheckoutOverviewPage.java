package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage extends BaseTest {

    public CheckoutOverviewPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "cart_item")
    public WebElement cartItem;

    @FindBy(className = "summary_subtotal_label")
    public WebElement itemTotalPrice;

    @FindBy(className = "cart_quantity")
    public WebElement cartQuantityField;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div[2]/div[6]/text()[2]")
    public WebElement itemTotal;

    @FindBy(id = "finish")
    public WebElement finishButton;


    public void clickOnFinishButton() {
        finishButton.click();
    }
}
