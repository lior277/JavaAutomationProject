package org.example.placeOrderForm;

import org.example.internals.utils.DriverEXT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlaceOrderForm implements IPlaceOrderForm {
    private WebDriver driver;

    public PlaceOrderForm(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By nameExp = By.cssSelector("input[id='name']");
    private final By countryExp = By.cssSelector("input[id='country']");
    private final By cityExp = By.cssSelector("button[id='city']");
    private final By creditCardExp = By.cssSelector("input[id='card']");
    private final By monthExp = By.cssSelector("input[id='month']");
    private final By yearExp = By.cssSelector("button[id='year']");
    private final By purchaseBtnExp = By.cssSelector("button[onclick*='purchaseOrder']");

    @Override
    public IPlaceOrderForm setName(String name) {

        DriverEXT.sendsKeysAuto(driver, nameExp, name, null);

        return this;
    }

    @Override
    public IPlaceOrderForm setCountry(String country) {

        DriverEXT.sendsKeysAuto(driver, countryExp, country, null);

        return this;
    }

    @Override
    public IPlaceOrderForm setCity(String city) {

        DriverEXT.sendsKeysAuto(driver, cityExp, city, null);

        return this;
    }

    @Override
    public IPlaceOrderForm setCreditCard(String creditCard) {

        DriverEXT.sendsKeysAuto(driver, creditCardExp, creditCard, null);

        return this;
    }

    @Override
    public IPlaceOrderForm setMonth(String month) {

        DriverEXT.sendsKeysAuto(driver, monthExp, month, null);

        return this;
    }

    @Override
    public IPlaceOrderForm setYear(String year) {

        DriverEXT.sendsKeysAuto(driver, yearExp, year, null);

        return this;
    }

    @Override
    public IPlaceOrderForm clickOnPurchaseButton() {

        DriverEXT.forceClick(driver, purchaseBtnExp, null);
        DriverEXT.getAlertMessageText(driver, null);

        return this;
    }

    @Override
    public String getPurchaseMessage() {

        return DriverEXT.getAlertMessageText(driver, null);
    }
}