package org.example.login;

import org.example.homePage.IHomePage;

public interface ILoginPage {
    ILoginPage setUserName(String userName);
    ILoginPage setPassword(String password);
    IHomePage clickOnLoginButton();
}
