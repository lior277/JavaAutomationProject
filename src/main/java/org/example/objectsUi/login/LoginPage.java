package org.example.objectsUi.login;

import org.example.dataObjects.ProductDTO;
import org.example.objectsUi.homePage.HomePage;
import org.example.objectsUi.homePage.IHomePage;
import org.example.internals.utils.DriverEXT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage implements ILoginPage {
    private WebDriver driver;
    private final ProductDTO productDTO;

    public LoginPage(WebDriver driver, ProductDTO productDTO) {
        this.driver = driver;
        this.productDTO = productDTO;
    }

    // Locators
    private final By userNameExp = By.cssSelector("input[id='loginusername']");
    private final By passwordExp = By.cssSelector("input[id='loginpassword']");
    private final By loginBtnExp = By.cssSelector("button[onclick='logIn()']");

    @Override
    public ILoginPage setUserName(String userName) {

        DriverEXT.sendsKeysAuto(driver, userNameExp, userName, null);

        return this;
    }

    @Override
    public ILoginPage setPassword(String password) {

        DriverEXT.sendsKeysAuto(driver, passwordExp, password, null);

        return this;
    }

    @Override
    public IHomePage clickOnLoginButton() {

        DriverEXT.forceClick(driver, loginBtnExp, null);

        return new HomePage(driver, this.productDTO);
    }
}