package org.example.objectsUi.cartPage;

import org.example.dataObjects.ProductDTO;
import org.example.internals.utils.DriverEXT;
import org.example.placeOrderForm.IPlaceOrderForm;
import org.example.placeOrderForm.PlaceOrderForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.example.internals.utils.StringUtils.capitalizeFirstLetter;

public class CartPage implements ICartPage {

    private final WebDriver driver;
    private final ProductDTO productDTO;
    private final IPlaceOrderForm placeOrderForm;

    private final String itemInChart = "//table[contains(@class,'bordered')" +
            " and contains(.,'%s')]//td[not(position() = last())]";

    private final String productNameInCart = "//td[contains(.,'%s')]";
    private final String productPriceInCart = "//td[contains(.,'%s')]";
    private final String productImage = "//tr[contains(.,'%s')]//img";
    private final String deleteItemFromCartBtn = "//tr[contains(.,'%s')]/td[contains(.,'Delete')]/a";

    // Locators
    private final By totalCartExp =
            By.xpath("//div[contains(@class,'col-lg')" +
                    " and contains(.,'Total')]");

    private final By placeOrderBtnExp =
            By.cssSelector("button[class='btn btn-success']");

    private final By cartItemsExp = By.cssSelector("tbody[id='tbodyid'] tr");

    public CartPage(WebDriver driver,
                    ProductDTO productDTO, IPlaceOrderForm placeOrderForm) {

        this.driver = driver;
        this.productDTO = productDTO;
        this.placeOrderForm = placeOrderForm;
    }

    @Override
    public ProductDTO getCartTotal() {
        productDTO.cartTotal = DriverEXT
                .getElementText(driver, totalCartExp, null);

        return productDTO;
    }

    @Override
    public ProductDTO getCartItemInfoByName(String productName, String productPrice) {
        var productNameExt = By.xpath(String.format(productNameInCart, productName));
        var productPriceExt = By.xpath(String.format(productPriceInCart, productPrice));
        var productImageExt = By.xpath(String.format(productImage, productName));

        // Get all cells from the row
        productDTO.productName = DriverEXT
                .searchElement(driver, productNameExt, null)
                .getText();

        productDTO.productPrice = DriverEXT
                .searchElement(driver, productPriceExt, null)
                .getText();

        var imageName = capitalizeFirstLetter(DriverEXT
                .searchElement(driver, productImageExt, null)
                .getAttribute("src"));

        productDTO.productImage =
                capitalizeFirstLetter(
                        imageName.substring(imageName.lastIndexOf('/') + 1));

        productDTO.cartTotal = DriverEXT
                .searchElement(driver, totalCartExp, null)
                .getText();

        return productDTO;
    }

    @Override
    public List<WebElement> getCartItems() {

        return DriverEXT.searchElements(driver, cartItemsExp, null);
    }

    @Override
    public ICartPage deleteItemFromCartByName(String productName) {

        var item = By.xpath(String.format(deleteItemFromCartBtn, productName));
        DriverEXT.forceClick(driver, item, null);

        return this;
    }

    @Override
    public IPlaceOrderForm clickOnPlaceOrderButton() {
        DriverEXT.forceClick(driver, placeOrderBtnExp, null);

        return new PlaceOrderForm(driver);
    }
}
