package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends BaseTest {

    public ItemPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_details_name.large_size")
    public WebElement ItemName;

    @FindBy(id = "remove")
    public WebElement removeButton;

    @FindBy(id = "add-to-cart")
    public WebElement addToCartButton;


    public void clickOnRemoveButton() {
        removeButton.click();
    }

    public void clickOnAddToCartButton() {
        addToCartButton.click();
    }

}
