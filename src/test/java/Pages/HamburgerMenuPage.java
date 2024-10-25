package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HamburgerMenuPage extends BaseTest {

    public HamburgerMenuPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "react-burger-cross-btn")
    public WebElement burgerCloseButton;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logOutButton;

    @FindBy(id = "reset_sidebar_link")
    public WebElement resetAppButton;


    public void clickOnBurgerClose() {
        burgerCloseButton.click();

    }

    public void clickOnLogoutButton() {
        logOutButton.click();
    }

    public void clickOnResetAppButton() {
        resetAppButton.click();
    }


}
