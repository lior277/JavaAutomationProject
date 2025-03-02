package org.example.objectsUi.signUpPage;

import org.example.dataObjects.ProductDTO;
import org.example.objectsUi.homePage.HomePage;
import org.example.objectsUi.homePage.IHomePage;
import org.example.internals.utils.DriverEXT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage implements ISignUpPage {
    private WebDriver driver;
    private final ProductDTO productDTO;

    public SignUpPage(WebDriver driver, ProductDTO productDTO) {
        this.driver = driver;
        this.productDTO = productDTO;
    }

    // Locators
    private final By userNameExp = By.cssSelector("input[id='sign-username']");
    private final By passwordExp = By.cssSelector("input[id='sign-password']");
    private final By signUpBtnExp = By.cssSelector("button[onclick*='register']");

    @Override
    public ISignUpPage setUserName(String userName) {

        DriverEXT.sendsKeysAuto(driver, userNameExp, userName, null);

        return this;
    }

    @Override
    public ISignUpPage setPassword(String password) {

        DriverEXT.sendsKeysAuto(driver, passwordExp, password, null);

        return this;
    }

    @Override
    public IHomePage clickOnSignUpButton() {

        DriverEXT.forceClick(driver, signUpBtnExp, null);
        DriverEXT.closeAlertMessage(driver, null);

        return new HomePage(driver, this.productDTO);
    }
}