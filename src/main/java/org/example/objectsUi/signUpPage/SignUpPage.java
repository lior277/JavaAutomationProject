package org.example.objectsUi.signUpPage;

import org.example.dataObjects.ProductDTO;
import org.example.objectsUi.homePage.HomePage;
import org.example.objectsUi.homePage.IHomePage;
import org.example.internals.utils.DriverEXT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage implements ISignUpPage {
    private final WebDriver driver;
    private final ProductDTO productDTO;

    // Locators
    private static final By USERNAME_FIELD = By.cssSelector("input[id='sign-username']");
    private static final By PASSWORD_FIELD = By.cssSelector("input[id='sign-password']");
    private static final By SIGNUP_BUTTON = By.cssSelector("button[onclick*='register']");

    public SignUpPage(WebDriver driver, ProductDTO productDTO) {
        this.driver = driver;
        this.productDTO = productDTO;
    }

    @Override
    public ISignUpPage setUserName(String userName) {
        DriverEXT.sendsKeysAuto(driver, USERNAME_FIELD, userName, null);
        return this;
    }

    @Override
    public ISignUpPage setPassword(String password) {
        DriverEXT.sendsKeysAuto(driver, PASSWORD_FIELD, password, null);
        return this;
    }

    @Override
    public IHomePage clickOnSignUpButton() {
        DriverEXT.forceClick(driver, SIGNUP_BUTTON, null);
        DriverEXT.closeAlertMessage(driver, null);
        return new HomePage(driver, this.productDTO);
    }
}