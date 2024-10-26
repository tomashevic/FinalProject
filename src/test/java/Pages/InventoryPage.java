package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenuButton;

    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCartIcon;

    @FindBy(className = "shopping_cart_badge")
    public WebElement shoppingCartBadge;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    public WebElement addToCartBackPack;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    public WebElement addToCartBoltTshirt;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    public WebElement addToCartBikeLight;

    @FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
    public WebElement addToCartTshirtRed;

    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    public WebElement addToCartOnesie;

    @FindBy(id = "remove-sauce-labs-bolt-t-shirt")
    public WebElement removeBoltTshirt;

    @FindBy(id = "remove-sauce-labs-bike-light")
    public WebElement removeBikeLight;

    @FindBy(id = "remove-sauce-labs-onesie")
    public WebElement removeOnesie;

    @FindBy(className = "inventory_item_price")
    public WebElement backpackInventoryPrice;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div/div/div[3]/div[2]/div[2]/div")
    public WebElement inventoryItemPrice;

    @FindBy(linkText = "Sauce Labs Bolt T-Shirt")
    public WebElement inventoryItemName;

    @FindBy(id = "item_0_img_link")
    public WebElement bikeLightImageLink;

    @FindBy(id = "item_3_img_link")
    public WebElement shirtRedImageLink;

    @FindBy(className = "select_container")
    public WebElement sortButton;

    @FindBy(className = "active_option")
    public WebElement selectedSortOrder;

    @FindBy(css = "option[value='az']")
    public WebElement alphabetOrder;

    @FindBy(css = "option[value='za']")
    public WebElement reverseAlphabetOrder;

    @FindBy(css = "option[value='lohi']")
    public WebElement lowToHigh;

    @FindBy(css = "option[value='hilo']")
    public WebElement highToLow;


    public void clickOnBurgerMenuButton() {
        burgerMenuButton.click();
    }

    public void clickOnCartIcon() {
        shoppingCartIcon.click();
    }

    public void clickOnAddToCartBackpack() {
        addToCartBackPack.click();
    }

    public void clickOnAddToCartBoltTshirt() {
        addToCartBoltTshirt.click();
    }

    public void clickOnAddToCartTshirtRed() {
        addToCartTshirtRed.click();
    }

    public void clickOnAddToCartBikeLight() {
        addToCartBikeLight.click();
    }

    public void clickOnAddToCartOnesie() {
        addToCartOnesie.click();
    }

    public void clickOnRemoveBoltTshirt() {
        removeBoltTshirt.click();
    }

    public void clickOnRemoveBikeLight() {
        removeBikeLight.click();
    }

    public void clickOnBikeLightImage() {
        bikeLightImageLink.click();

    }

    public void clickOnShirtRedImageLink() {
        shirtRedImageLink.click();
    }

    public void clickOnSortButton() {
        sortButton.click();
    }

    public void sortByAlphabeticalOrder() {
        alphabetOrder.click();
    }

    public void sortByReverseAlphabeticalOrder() {
        reverseAlphabetOrder.click();
    }

    public void sortByLowestPrice() {
        lowToHigh.click();
    }

    public void sortByHighestPrice() {
        highToLow.click();
    }


}
