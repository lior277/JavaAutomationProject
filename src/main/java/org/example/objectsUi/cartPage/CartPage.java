package org.example.objectsUi.cartPage;

import org.example.dataObjects.ProductDTO;
import org.example.internals.utils.DriverEXT;
import org.example.placeOrderForm.IPlaceOrderForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.example.internals.utils.StringUtils.capitalizeFirstLetter;

public class CartPage implements ICartPage {

    private final WebDriver driver;
    private final ProductDTO productDTO = new ProductDTO();

    private final String itemInChart = "//table[contains(@class,'bordered')" +
            " and contains(.,'%s')]//td[not(position() = last())]";

    private final String productNameInCart = "//td[contains(.,'%s')]";
    private final String productPriceInCart = "//td[contains(.,'%s')]";
    private final String productImage = "//tr[contains(.,'%s')]//img";

    // Locators
    private final By totalCartExp =
            By.xpath("//div[contains(@class,'col-lg')" +
            " and contains(.,'Total')]");

    private final By placeOrderBtnExp =
            By.xpath("button[class='btn btn-success']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
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
        var placeOrderExt = By.xpath(String.format(productImage, productName));

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
    public ICartPage deleteItemFromCartByName(String productName) {
        String deleteItemFromCartBtn = "//table[contains(@class,'bordered')" +
                " and contains(.,'%s') and contains(.,'Delete')]//a";

        var item = By.xpath(String.format(deleteItemFromCartBtn, productName));
        DriverEXT.forceClick(driver, item, null);

        return this;
    }

    @Override
    public IPlaceOrderForm clickOnPlaceOrderButton(String productName) {
        DriverEXT.forceClick(driver, placeOrderBtnExp, null);

        return this;
    }
}
