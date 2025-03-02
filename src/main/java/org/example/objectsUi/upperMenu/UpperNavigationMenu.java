package org.example.objectsUi.upperMenu;

import org.example.dataObjects.ProductDTO;
import org.example.internals.utils.DriverEXT;
import org.example.objectsUi.cartPage.CartPage;
import org.example.objectsUi.cartPage.ICartPage;
import org.example.objectsUi.login.ILoginPage;
import org.example.objectsUi.login.LoginPage;
import org.example.objectsUi.signUpPage.ISignUpPage;
import org.example.objectsUi.signUpPage.SignUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UpperNavigationMenu implements IUpperNavigationMenu {

    private WebDriver driver;
    private ProductDTO productDTO;
    // Locators
    private final By SIGN_UP_EXP  = By.cssSelector("a[id*='signin']");
    private final By LoginExp = By.cssSelector("a[id*='login']");
    private final By chartExp = By.cssSelector("a[id='cartur']");

    public UpperNavigationMenu(WebDriver driver, ProductDTO productDTO) {
        this.driver = driver;
        this.productDTO = productDTO;
    }

    @Override
    public ILoginPage clickOnLoginLink() {

        DriverEXT.forceClick(this.driver, LoginExp, null);

        return new LoginPage(this.driver, this.productDTO);
    }

    @Override
    public ISignUpPage clickOnSignUpLink() {

        DriverEXT.forceClick(this.driver, SIGN_UP_EXP, null);

        return new SignUpPage(driver, this.productDTO);
    }

    @Override
    public ICartPage clickOnCartLink() {

        DriverEXT.forceClick(this.driver, chartExp, null);

        return new CartPage(driver);
    }
}
