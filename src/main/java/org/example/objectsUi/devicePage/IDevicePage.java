package org.example.objectsUi.devicePage;

import org.example.dataObjects.ProductDTO;

public interface IDevicePage {
    IDevicePage clickOnAddToChartButton();

    ProductDTO getDeviceData();
}
