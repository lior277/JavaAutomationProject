package org.example.objectsUi.devicePage;

import org.example.dataObjects.ProductDTO;
import org.example.internals.utils.DriverEXT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.logging.Logger;

public class DevicePage implements IDevicePage {
    private static final Logger LOGGER = Logger.getLogger(DevicePage.class.getName());

    private final ProductDTO productDTO;
    private final WebDriver driver;

    // Locators as constants
    private static final By ADD_TO_CART_BTN = By.cssSelector("a[onclick*='addToCart']");
    private static final By PRODUCT_IMAGE = By.cssSelector("div[class='item active'] img");
    private static final By PRODUCT_NAME = By.cssSelector("h2[class='name']");
    private static final By PRODUCT_PRICE = By.cssSelector("h3[class='price-container']");
    private static final By PRODUCT_DESCRIPTION = By.cssSelector("div[id='more-information']");

    public DevicePage(WebDriver driver, ProductDTO productDTO) {
        this.driver = driver;
        this.productDTO = productDTO;
        LOGGER.info("DevicePage initialized");
    }

    @Override
    public IDevicePage clickOnAddToChartButton() {
        LOGGER.info("Clicking Add to Cart button");
        try {
            DriverEXT.forceClick(driver, ADD_TO_CART_BTN, null);
            DriverEXT.closeAlertMessage(driver, null);
            LOGGER.info("Product added to cart successfully");
        } catch (Exception e) {
            LOGGER.severe("Error adding product to cart: " + e.getMessage());
            throw e;
        }
        return this;
    }

    @Override
    public ProductDTO getDeviceData() {
        LOGGER.info("Retrieving device data");
        try {
            this.productDTO.productName = DriverEXT
                    .searchElement(driver, PRODUCT_NAME, null)
                    .getText();

            this.productDTO.productPrice = DriverEXT
                    .searchElement(driver, PRODUCT_PRICE, null)
                    .getText();

            this.productDTO.productDescription = DriverEXT
                    .searchElement(driver, PRODUCT_DESCRIPTION, null)
                    .getText();

            this.productDTO.productImage = DriverEXT
                    .searchElement(driver, PRODUCT_IMAGE, null)
                    .getAttribute("src");

            LOGGER.info("Retrieved device data for: " + this.productDTO.productName);
        } catch (Exception e) {
            LOGGER.severe("Error retrieving device data: " + e.getMessage());
            throw e;
        }

        return this.productDTO;
    }
}