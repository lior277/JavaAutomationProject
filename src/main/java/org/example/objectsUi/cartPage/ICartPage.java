package org.example.objectsUi.cartPage;

import org.example.dataObjects.ProductDTO;
import org.example.placeOrderForm.IPlaceOrderForm;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface ICartPage {

    ProductDTO getCartTotal();

    ProductDTO getCartItemInfoByName(String productName, String productPrice);

    List<WebElement> getCartItems();

    ICartPage deleteItemFromCartByName(String productName);

    IPlaceOrderForm clickOnPlaceOrderButton();
}
