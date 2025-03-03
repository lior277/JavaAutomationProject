package org.example.objectsUi.cartPage;

import org.example.dataObjects.ProductDTO;
import org.example.placeOrderForm.IPlaceOrderForm;

public interface ICartPage {
    ProductDTO getCartTotal();

    ProductDTO getCartItemInfoByName(String productName, String productPrice);

    ICartPage deleteItemFromCartByName(String productName);

    IPlaceOrderForm clickOnPlaceOrderButton(String productName);
}
