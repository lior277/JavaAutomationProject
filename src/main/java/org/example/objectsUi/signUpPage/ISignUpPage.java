package org.example.objectsUi.signUpPage;

import org.example.objectsUi.homePage.IHomePage;

public interface ISignUpPage {
    ISignUpPage setUserName(String userName);

    ISignUpPage setPassword(String password);

    IHomePage clickOnSignUpButton();
}
