package org.example.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.example.TestSuitBase;
import org.example.dataObjects.ProductDTO;
import org.example.helpers.DataRep;
import org.example.objectsUi.placeOrderForm.IPlaceOrderForm;
import org.example.objectsUi.placeOrderForm.PlaceOrderForm;
import org.example.objectsUi.cartPage.ICartPage;
import org.example.objectsUi.upperMenu.IUpperNavigationMenu;
import org.example.objectsUi.upperMenu.UpperNavigationMenu;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartStepDefinitions extends TestSuitBase {
    private IUpperNavigationMenu upperNavigationMenu;
    private ICartPage cartPage;
    private List<WebElement> cartItems;

    private final String categoryName = "phone";
    private final String username = "testuser" + System.currentTimeMillis();
    private final String password = "password123";

    // Ensure driver is set up at the beginning of the scenario
    public void setupDriver() {
        getDriver();
    }

    // Ensure driver is disposed at the end of the scenario
    public void tearDownDriver() {
        driverDispose();
    }

    @Given("I am on the DemoBlaze home page")
    public void i_am_on_the_demoblaze_home_page() {
        // Ensure driver is set up
        setupDriver();

        WebDriver driver = getDriver();

        ProductDTO productDTO = new ProductDTO();
        IPlaceOrderForm placeOrderForm = new PlaceOrderForm(driver);

        upperNavigationMenu = new UpperNavigationMenu(driver, productDTO, placeOrderForm);

        driver.get(DataRep.demoBlazeUrl);
    }

    @And("I sign up and login with a new user")
    public void i_sign_up_and_login_with_a_new_user() {
        upperNavigationMenu
                .clickOnSignUpLink()
                .setUserName(username)
                .setPassword(password)
                .clickOnSignUpButton();

        upperNavigationMenu
                .clickOnLoginLink()
                .setUserName(username)
                .setPassword(password)
                .clickOnLoginButton();
    }

    @And("I add {string} to my cart")
    public void i_add_to_my_cart(String deviceName) {
        upperNavigationMenu
                .clickOnHomeLink()
                .clickOnCategorieByName(categoryName)
                .clickOnDeviceByName(deviceName)
                .clickOnAddToChartButton();
    }

    @When("I navigate to my cart")
    public void i_navigate_to_my_cart() {
        cartPage = upperNavigationMenu.clickOnCartLink();
    }

    @And("I delete {string} from my cart")
    public void i_delete_from_my_cart(String deviceName) {
        cartItems = cartPage
                .deleteItemFromCartByName(deviceName)
                .getCartItems();
    }

    @Then("my cart should contain {int} item")
    public void my_cart_should_contain_items(int expectedCount) {
        Assertions.assertEquals(expectedCount, cartItems.size(),
                String.format("Expected %d items in cart, found %d", expectedCount, cartItems.size()));
    }

    @And("my cart should contain {string}")
    public void my_cart_should_contain(String deviceName) {
        try {
            boolean containsDevice = cartItems.get(0).getText().contains(deviceName);

            Assertions.assertTrue(containsDevice,
                    String.format("Expected to find %s in cart but it was not present. Actual text: %s",
                            deviceName, cartItems.get(0).getText()));
        } finally {
            // Ensure driver is disposed after the test
            tearDownDriver();
        }
    }
}