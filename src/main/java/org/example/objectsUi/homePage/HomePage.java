package org.example.objectsUi.homePage;

import org.example.dataObjects.ProductDTO;
import org.example.objectsUi.devicePage.DevicePage;
import org.example.objectsUi.devicePage.IDevicePage;
import org.example.helpers.DataRep;
import org.example.internals.utils.DriverEXT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage implements IHomePage {
    private final WebDriver driver;
    private final String categorieName = "a[onclick*='%s']";
    private final String deviceByName = "//a[contains(.,'%s')]";
    private final ProductDTO productDTO;

    public HomePage(WebDriver driver, ProductDTO productDTO) {
        this.driver = driver;
        this.productDTO = productDTO;
    }

    @Override
    public IHomePage clickOnCategorieByName(String categorieName) {
        var categorieExt = By.cssSelector(String.format(this.categorieName, categorieName));
        DriverEXT.forceClick(driver, categorieExt, null);

        return this;
    }

    @Override
    public IDevicePage clickOnDeviceImageByName(String deviceName) {
        var deviceExt = By.xpath(String.format(deviceByName, deviceName));
        DriverEXT.forceClick(driver, deviceExt, null);

        return new DevicePage(driver,this.productDTO);
    }
}
