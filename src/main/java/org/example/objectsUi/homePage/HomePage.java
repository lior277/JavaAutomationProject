package org.example.objectsUi.homePage;

import org.example.dataObjects.ProductDTO;
import org.example.objectsUi.devicePage.DevicePage;
import org.example.objectsUi.devicePage.IDevicePage;
import org.example.internals.utils.DriverEXT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage implements IHomePage {
    private final WebDriver driver;
    private final ProductDTO productDTO;

    private static final String CATEGORY_SELECTOR = "a[onclick*='%s']";
    private static final String DEVICE_BY_NAME = "//a[contains(.,'%s')]";

    public HomePage(WebDriver driver, ProductDTO productDTO) {
        this.driver = driver;
        this.productDTO = productDTO;
    }

    @Override
    public IHomePage clickOnCategorieByName(String categorieName) {
        By categoryLocator = By.cssSelector(String.format(CATEGORY_SELECTOR, categorieName));
        DriverEXT.forceClick(driver, categoryLocator, null);
        return this;
    }

    @Override
    public IDevicePage clickOnDeviceByName(String deviceName) {
        By deviceLocator = By.xpath(String.format(DEVICE_BY_NAME, deviceName));
        DriverEXT.forceClick(driver, deviceLocator, null);
        return new DevicePage(driver, this.productDTO);
    }
}