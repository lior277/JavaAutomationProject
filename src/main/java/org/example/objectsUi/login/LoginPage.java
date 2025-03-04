package org.example.objectsUi.login;

import org.example.dataObjects.ProductDTO;
import org.example.objectsUi.homePage.HomePage;
import org.example.objectsUi.homePage.IHomePage;
import org.example.internals.utils.DriverEXT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage implements ILoginPage {
    private final WebDriver driver;
    private final ProductDTO productDTO;

    // Locators
    private static final By USERNAME_FIELD = By.cssSelector("input[id='loginusername']");
    private static final By PASSWORD_FIELD = By.cssSelector("input[id='loginpassword']");
    private static final By LOGIN_BUTTON = By.cssSelector("button[onclick='logIn()']");

    public LoginPage(WebDriver driver, ProductDTO productDTO) {
        this.driver = driver;
        this.productDTO = productDTO;
    }

    @Override
    public ILoginPage setUserName(String userName) {
        DriverEXT.sendsKeysAuto(driver, USERNAME_FIELD, userName, null);
        return this;
    }

    @Override
    public ILoginPage setPassword(String password) {
        DriverEXT.sendsKeysAuto(driver, PASSWORD_FIELD, password, null);
        return this;
    }

    @Override
    public IHomePage clickOnLoginButton() {
        DriverEXT.forceClick(driver, LOGIN_BUTTON, null);
        return new HomePage(driver, this.productDTO);
    }
}