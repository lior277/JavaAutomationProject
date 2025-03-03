package org.example.placeOrderForm;

public interface IPlaceOrderForm {
    IPlaceOrderForm setName(String userName);

    IPlaceOrderForm setCountry(String country);

    IPlaceOrderForm setCity(String city);

    IPlaceOrderForm setCreditCard(String creditCard);

    IPlaceOrderForm setMonth(String month);

    IPlaceOrderForm setYear(String year);

    IPlaceOrderForm clickOnPurchaseButton();

    String getPurchaseMessage();
}
