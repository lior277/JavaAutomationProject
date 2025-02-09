package org.example.login;

import org.example.homePage.HomePage;
import org.example.homePage.IHomePage;
import org.example.internals.utils.WebDriverExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage implements ILoginPage {
    private WebDriver driver;

    // Locators
    private By DataTablesInfoExp = By
            .xpath("//div[contains(@id,'_info')]//ancestor::" +
                    "div[@class='dataTables_info' and contains(.,1)]");

    private By userNameExp = By.cssSelector("input[id='email-login-field']");
    private By passwordExp = By.cssSelector("input[id='password-login-field']");
    private By loginBtnExp = By.cssSelector("button[id='submit-login-btn']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public ILoginPage setUserName(String userName) {
        var element = WebDriverExtension
                .searchElement(driver, userNameExp, null);

        WebDriverExtension
                .sendsKeysAuto(element, driver,
                        userNameExp, userName, null);

        return this;
    }

    @Override
    public ILoginPage setPassword(String password) {
        WebElement element = WebDriverExtension
                .searchElement(driver, passwordExp, null);

        WebDriverExtension
                .sendsKeysAuto(element, driver,
                        passwordExp, password, null);

        return this;
    }

    @Override
    public IHomePage clickOnLoginButton() {
        WebElement element = WebDriverExtension
                .searchElement(driver, loginBtnExp, null);

        WebDriverExtension.forceClick(element, driver, loginBtnExp, null);

        return new HomePage(driver);
    }
}