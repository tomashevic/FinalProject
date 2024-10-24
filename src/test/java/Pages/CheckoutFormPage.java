package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutFormPage extends BaseTest {

    public CheckoutFormPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(id = "first-name")
    public WebElement firstNameField;

    @FindBy(id = "last-name")
    public WebElement lastNameField;

    @FindBy(id = "postal-code")
    public WebElement postalCodeField;

    @FindBy(css = "input[value='John']")
    public WebElement displayedFirstName;

    @FindBy(css = "input[value='Doe']")
    public WebElement displayedLastName;

    @FindBy(css = "input[value='90210']")
    public WebElement displayedPostalCode;



    public void inputFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void inputPostalCode(String postalCode) {
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    public void clickOnContinueButton() {
        continueButton.click();
    }
}
