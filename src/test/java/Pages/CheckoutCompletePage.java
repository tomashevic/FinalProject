package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BaseTest {

    public CheckoutCompletePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;

    @FindBy(className = "title")
    public WebElement titleMessage;

    @FindBy(className = "complete-header")
    public WebElement thankYouMessage;


    public void clickOnBackHomeButton() {
        backHomeButton.click();
    }

}
