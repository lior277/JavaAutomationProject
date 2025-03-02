package org.example.objectsUi.login;

import org.example.objectsUi.homePage.IHomePage;

public interface ILoginPage {
    ILoginPage setUserName(String userName);
    ILoginPage setPassword(String password);
    IHomePage clickOnLoginButton();
}
