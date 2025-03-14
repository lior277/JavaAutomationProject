import org.example.dataObjects.ProductDTO;
import org.example.helpers.DataRep;
import org.example.objectsUi.upperMenu.IUpperNavigationMenu;
import org.example.objectsUi.upperMenu.UpperNavigationMenu;
import org.example.objectsUi.placeOrderForm.IPlaceOrderForm;
import org.example.objectsUi.placeOrderForm.PlaceOrderForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.example.TestSuitBase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VerifyPlaceOrderTest extends TestSuitBase {
    private IUpperNavigationMenu upperNavigationMenu;
    private final String expectedDeviceName = "Nokia lumia 1520";

    @BeforeEach
    void setUp() {
        // Get the driver
        WebDriver driver = getDriver();

        var productDTO = new ProductDTO();
        var placeOrderForm = new PlaceOrderForm(driver);

        upperNavigationMenu = new UpperNavigationMenu(driver,
                productDTO, placeOrderForm);

        driver.get(DataRep.demoBlazeUrl);

        // Generate random credentials for signup
        String username = "testuser" + System.currentTimeMillis();
        String password = "password123";

        // Register a new user
        var homepage = upperNavigationMenu
                .clickOnSignUpLink()
                .setUserName(username)
                .setPassword(password)
                .clickOnSignUpButton();

        // login
        upperNavigationMenu
                .clickOnLoginLink()
                .setUserName(username)
                .setPassword(password)
                .clickOnLoginButton();

        // add the first device to the cart
        homepage
                .clickOnCategorieByName("phone")
                .clickOnDeviceByName(expectedDeviceName)
                .clickOnAddToChartButton();
    }

    @AfterEach
    void tearDown() {
        driverDispose();
    }

    @Test
    void verifyPlaceOrder() {
        var expectedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
        var expectedName = "Test User";
        var expectedCreditCard = "1234567890";
        var expectedAmount = "820 USD";
        var expectedSuccessMessage = "Thank you for your purchase!";

        var actualPurchaseMessage = upperNavigationMenu
                .clickOnCartLink()
                .clickOnPlaceOrderButton()
                .placeOrderPipe();

        Assertions.assertAll(
                () -> Assertions.assertTrue(actualPurchaseMessage.contains(expectedDate),
                        String.format("Expected message contains date: %s, Actual message contains date: %s",
                                expectedDate, actualPurchaseMessage)),

                () -> Assertions.assertTrue(actualPurchaseMessage.contains(expectedAmount),
                        String.format("Expected message contains amount: %s, Actual message contains amount: %s",
                                expectedAmount, actualPurchaseMessage)),

                () -> Assertions.assertTrue(actualPurchaseMessage.contains(expectedCreditCard),
                        String.format("Expected message contains Credit Card: %s, Actual message contains Credit Card: %s",
                                expectedCreditCard, actualPurchaseMessage)),

                () -> Assertions.assertTrue(actualPurchaseMessage.contains(expectedName),
                        String.format("Expected message contains name: %s, Actual message contains name: %s",
                                expectedName, actualPurchaseMessage)),

                () -> Assertions.assertTrue(actualPurchaseMessage.contains(expectedSuccessMessage),
                        String.format("Expected message contains Success Message: %s, Actual message Success Message: %s",
                                expectedSuccessMessage, actualPurchaseMessage))
        );
    }
}