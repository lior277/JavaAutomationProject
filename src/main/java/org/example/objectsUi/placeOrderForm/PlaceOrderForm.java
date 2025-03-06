package org.example.objectsUi.placeOrderForm;

import org.example.internals.utils.DriverEXT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlaceOrderForm implements IPlaceOrderForm {
    private final WebDriver driver;

    // Locators
    private static final By NAME_FIELD = By.cssSelector("input[id='name']");
    private static final By COUNTRY_FIELD = By.cssSelector("input[id='country']");
    private static final By CITY_FIELD = By.cssSelector("input[id='city']");
    private static final By CREDIT_CARD_FIELD = By.cssSelector("input[id='card']");
    private static final By MONTH_FIELD = By.cssSelector("input[id='month']");
    private static final By YEAR_FIELD = By.cssSelector("input[id='year']");
    private static final By CONFIRM_PURCHASE_MESSAGE = By.cssSelector("div[class*='sweet-alert']");
    private static final By PURCHASE_BUTTON = By.cssSelector("button[onclick*='purchaseOrder']");

    public PlaceOrderForm(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public IPlaceOrderForm setName(String name) {
        DriverEXT.sendsKeysAuto(driver, NAME_FIELD, name, null);
        return this;
    }

    @Override
    public IPlaceOrderForm setCountry(String country) {
        DriverEXT.sendsKeysAuto(driver, COUNTRY_FIELD, country, null);
        return this;
    }

    @Override
    public IPlaceOrderForm setCity(String city) {
        DriverEXT.sendsKeysAuto(driver, CITY_FIELD, city, null);
        return this;
    }

    @Override
    public IPlaceOrderForm setCreditCard(String creditCard) {
        DriverEXT.sendsKeysAuto(driver, CREDIT_CARD_FIELD, creditCard, null);
        return this;
    }

    @Override
    public IPlaceOrderForm setMonth(String month) {
        DriverEXT.sendsKeysAuto(driver, MONTH_FIELD, month, null);
        return this;
    }

    @Override
    public IPlaceOrderForm setYear(String year) {
        DriverEXT.sendsKeysAuto(driver, YEAR_FIELD, year, null);
        return this;
    }

    @Override
    public String clickOnPurchaseButton() {
        DriverEXT.forceClick(driver, PURCHASE_BUTTON, null);
        return DriverEXT.getElementText(driver, CONFIRM_PURCHASE_MESSAGE, null);
    }

    @Override
    public String placeOrderPipe() {
        return setName("Test User")
                .setCountry("Test Country")
                .setCity("Test City")
                .setCreditCard("1234567890")
                .setMonth("12")
                .setYear("2023")
                .clickOnPurchaseButton();
    }
}