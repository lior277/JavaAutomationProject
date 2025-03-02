package org.example.objectsUi.upperMenu;

import org.example.objectsUi.cartPage.ICartPage;
import org.example.objectsUi.login.ILoginPage;
import org.example.objectsUi.signUpPage.ISignUpPage;

public interface IUpperNavigationMenu {


    ISignUpPage clickOnSignUpLink();

    ICartPage clickOnCartLink();

    ILoginPage clickOnLoginLink();
}
