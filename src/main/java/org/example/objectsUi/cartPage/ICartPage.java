package org.example.objectsUi.cartPage;

import org.example.dataObjects.ProductDTO;

public interface ICartPage {
    ProductDTO getCartTotal();

    ProductDTO getCartItemInfoByName(String productName, String productPrice);

    ICartPage deleteItemFromCartByName(String productName);
}
