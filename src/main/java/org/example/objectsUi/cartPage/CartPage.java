package org.example.objectsUi.cartPage;

import org.example.dataObjects.ProductDTO;
import org.example.internals.utils.DriverEXT;
import org.example.objectsUi.placeOrderForm.IPlaceOrderForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Logger;

import static org.example.internals.utils.SleepUtil.sleep;
import static org.example.internals.utils.StringUtils.capitalizeFirstLetter;

public class CartPage implements ICartPage {
    private static final Logger LOGGER = Logger.getLogger(CartPage.class.getName());

    private final WebDriver driver;
    private final ProductDTO productDTO;
    private final IPlaceOrderForm placeOrderForm;

    // Locators as constants
    private static final String PRODUCT_NAME_IN_CART = "//td[contains(.,'%s')]";
    private static final String PRODUCT_PRICE_IN_CART = "//td[contains(.,'%s')]";
    private static final String PRODUCT_IMAGE = "//tr[contains(.,'%s')]//img";
    private static final String DELETE_ITEM_FROM_CART = "//tr[contains(.,'%s')]/td[contains(.,'Delete')]/a";

    private static final By TOTAL_CART = By.xpath("//div[contains(@class,'col-lg') and contains(.,'Total')]");
    private static final By PLACE_ORDER_BUTTON = By.cssSelector("button[class='btn btn-success']");
    private static final By CART_ITEMS = By.cssSelector("tbody[id='tbodyid'] tr");

    public CartPage(WebDriver driver, ProductDTO productDTO, IPlaceOrderForm placeOrderForm) {
        this.driver = driver;
        this.productDTO = productDTO;
        this.placeOrderForm = placeOrderForm;

        LOGGER.info("CartPage initialized");
    }

    @Override
    public ProductDTO getCartTotal() {
        String cartTotal = DriverEXT.getElementText(driver, TOTAL_CART, null);
        productDTO.cartTotal = cartTotal;
        LOGGER.info("Cart total retrieved: " + cartTotal);

        return productDTO;
    }

    @Override
    public ProductDTO getCartItemInfoByName(String productName, String productPrice) {
        LOGGER.info("Getting cart item info for product: " + productName);

        try {
            // Create locators with the product details
            By productNameLocator = By.xpath(String.format(PRODUCT_NAME_IN_CART, productName));
            By productPriceLocator = By.xpath(String.format(PRODUCT_PRICE_IN_CART, productPrice));
            By productImageLocator = By.xpath(String.format(PRODUCT_IMAGE, productName));

            // Get product name
            WebElement nameElement = DriverEXT.searchElement(driver, productNameLocator, null);
            productDTO.productName = nameElement.getText();

            // Get product price
            WebElement priceElement = DriverEXT.searchElement(driver, productPriceLocator, null);
            productDTO.productPrice = priceElement.getText();

            // Get product image
            WebElement imageElement = DriverEXT.searchElement(driver, productImageLocator, null);
            String imageSrc = imageElement.getAttribute("src");
            extractAndSetImageName(imageSrc);

            // Get cart total
            productDTO.cartTotal = DriverEXT.getElementText(driver, TOTAL_CART, null);

            LOGGER.info("Successfully retrieved cart item info for: " + productName);
        } catch (Exception e) {
            LOGGER.severe("Error retrieving cart item info: " + e.getMessage());
            throw e;
        }

        return productDTO;
    }

    private void extractAndSetImageName(String imageSrc) {
        if (imageSrc != null && !imageSrc.isEmpty()) {
            String imageName = capitalizeFirstLetter(imageSrc);

            productDTO.productImage = capitalizeFirstLetter(
                    imageName.substring(imageName.lastIndexOf('/') + 1));
        } else {
            productDTO.productImage = "Unknown";
            LOGGER.warning("Image source was empty or null");
        }
    }

    @Override
    public List<WebElement> getCartItems() {
        List<WebElement> items = DriverEXT.searchElements(driver, CART_ITEMS, null);
        LOGGER.info("Retrieved " + items.size() + " cart items");

        return items;
    }

    @Override
    public ICartPage deleteItemFromCartByName(String productName) {
        LOGGER.info("Deleting item from cart: " + productName);

        var productsBeforeDelete = getCartItems().size();
        By deleteButtonLocator = By.xpath(String.format(DELETE_ITEM_FROM_CART, productName));
        DriverEXT.forceClick(driver, deleteButtonLocator, null);
        var productsAfterDelete = getCartItems().size();

        for (var i = 0; i < 7; i++) {
            if (productsAfterDelete < productsBeforeDelete) {
                break;
            }

           sleep(300);
        }

        return this;
    }

    @Override
    public IPlaceOrderForm clickOnPlaceOrderButton() {
        LOGGER.info("Clicking Place Order button");
        DriverEXT.forceClick(driver, PLACE_ORDER_BUTTON, null);
        return placeOrderForm;
    }
}