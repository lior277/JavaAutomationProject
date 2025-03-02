package org.example.objectsUi.homePage;

import org.example.objectsUi.devicePage.IDevicePage;

public interface IHomePage {

    IHomePage clickOnCategorieByName(String categorieName);

    IDevicePage clickOnDeviceImageByName(String deviceName);
}
