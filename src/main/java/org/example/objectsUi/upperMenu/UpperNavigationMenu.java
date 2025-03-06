package org.example.objectsUi.upperMenu;

import org.example.dataObjects.ProductDTO;
import org.example.internals.utils.DriverEXT;
import org.example.objectsUi.cartPage.CartPage;
import org.example.objectsUi.cartPage.ICartPage;
import org.example.objectsUi.homePage.HomePage;
import org.example.objectsUi.homePage.IHomePage;
import org.example.objectsUi.login.ILoginPage;
import org.example.objectsUi.login.LoginPage;
import org.example.objectsUi.signUpPage.ISignUpPage;
import org.example.objectsUi.signUpPage.SignUpPage;
import org.example.objectsUi.placeOrderForm.IPlaceOrderForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UpperNavigationMenu implements IUpperNavigationMenu {

    private final WebDriver driver;
    private final ProductDTO productDTO;
    private final IPlaceOrderForm placeOrderForm;

    // Locators
    private static final By SIGNUP_LINK = By.cssSelector("a[id*='signin']");
    private static final By LOGIN_LINK = By.cssSelector("a[id*='login']");
    private static final By CART_LINK = By.cssSelector("a[id='cartur']");
    private static final By HOME_LINK = By.xpath("//a[contains(.,'Home ')]");

    public UpperNavigationMenu(WebDriver driver, ProductDTO productDTO,
                               IPlaceOrderForm placeOrderForm) {
        this.driver = driver;
        this.productDTO = productDTO;
        this.placeOrderForm = placeOrderForm;
    }

    @Override
    public ILoginPage clickOnLoginLink() {
        DriverEXT.forceClick(this.driver, LOGIN_LINK, null);
        return new LoginPage(this.driver, this.productDTO);
    }

    @Override
    public ISignUpPage clickOnSignUpLink() {
        DriverEXT.forceClick(this.driver, SIGNUP_LINK, null);
        return new SignUpPage(driver, this.productDTO);
    }

    @Override
    public ICartPage clickOnCartLink() {
        DriverEXT.forceClick(this.driver, CART_LINK, null);
        return new CartPage(driver, this.productDTO, this.placeOrderForm);
    }

    @Override
    public IHomePage clickOnHomeLink() {
        DriverEXT.forceClick(this.driver, HOME_LINK, null);
        return new HomePage(driver, this.productDTO);
    }
}