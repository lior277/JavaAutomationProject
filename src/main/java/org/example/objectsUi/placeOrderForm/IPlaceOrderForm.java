package org.example.objectsUi.placeOrderForm;

public interface IPlaceOrderForm {
    IPlaceOrderForm setName(String userName);

    IPlaceOrderForm setCountry(String country);

    IPlaceOrderForm setCity(String city);

    IPlaceOrderForm setCreditCard(String creditCard);

    IPlaceOrderForm setMonth(String month);

    IPlaceOrderForm setYear(String year);

    String clickOnPurchaseButton();

    String placeOrderPipe();
}
