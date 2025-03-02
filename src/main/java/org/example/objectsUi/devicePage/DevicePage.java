package org.example.objectsUi.devicePage;

import org.example.dataObjects.ProductDTO;
import org.example.internals.utils.DriverEXT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DevicePage implements IDevicePage {

    private final ProductDTO productDTO;
    private final WebDriver driver;

    public DevicePage(WebDriver driver, ProductDTO productDTO) {
        this.driver = driver;
        this.productDTO = productDTO;
    }

    // Locators
    private final By addToChartBtnExp = By.cssSelector("a[onclick*='addToCart']");
    private final By productImageExp = By.cssSelector("div[class='item active'] img");
    private final By productNameExp = By.cssSelector("h2[class='name']");
    private final By productPriceExp = By.cssSelector("h3[class='price-container']");
    private final By productDescriptionExp = By.cssSelector("div[id='more-information']");


    @Override
    public IDevicePage clickOnAddToChartButton() {
        DriverEXT.forceClick(driver, addToChartBtnExp, null);
        DriverEXT.closeAlertMessage(driver, null);

        return this;
    }

    @Override
    public ProductDTO getDeviceData() {

        this.productDTO.productName = DriverEXT
                .searchElement(driver, productNameExp, null)
                .getText();

        this.productDTO.productPrice = DriverEXT
                .searchElement(driver, productPriceExp, null)
                .getText();

        this.productDTO.productDescription = DriverEXT
                .searchElement(driver, productDescriptionExp, null)
                .getText();

        this.productDTO.productImage = DriverEXT
                .searchElement(driver, productImageExp, null)
                .getAttribute("src");

        return this.productDTO;
    }
}
