import org.example.dataObjects.ProductDTO;
import org.example.helpers.DataRep;
import org.example.objectsUi.devicePage.IDevicePage;
import org.example.objectsUi.upperMenu.IUpperNavigationMenu;
import org.example.objectsUi.upperMenu.UpperNavigationMenu;
import org.example.placeOrderForm.IPlaceOrderForm;
import org.example.placeOrderForm.PlaceOrderForm;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VerifyPlaceOrderTest extends TestSuitBase {
    private IDevicePage devicePage;
    private IUpperNavigationMenu upperNavigationMenu;
    private final String expectedDeviceName = "Nokia lumia 1520";

    @BeforeTest
    void setUp() {
        // Get the driver
        WebDriver driver = getDriver();

        ProductDTO productDTO = new ProductDTO();
        IPlaceOrderForm placeOrderForm = new PlaceOrderForm(driver);

        upperNavigationMenu = new UpperNavigationMenu(driver,
                productDTO, placeOrderForm);

        driver.get(DataRep.demoBlazeUrl);

        // Generate random credentials for signup
        String username = "testuser" + System.currentTimeMillis();
        String password = "password123";

        upperNavigationMenu
                .clickOnSignUpLink()
                .setUserName(username)
                .setPassword(password)
                .clickOnSignUpButton();

        devicePage = upperNavigationMenu
                .clickOnLoginLink()
                .setUserName(username)
                .setPassword(password)
                .clickOnLoginButton()
                .clickOnCategorieByName("phone")
                .clickOnDeviceByName(expectedDeviceName)
                .clickOnAddToChartButton();
    }

    @AfterTest
    void tearDown() {
        driverDispose();
    }

    @Test
    void VerifyPlaceOrder() {

        var expectedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/yyyy"));;
        var expectedName = "Test User";
        var expectedCreditCard = "1234567890";
        var expectedAmount = "820 USD";
        var expectedSuccessMessage = "Thank you for your purchase!";

        var actualPurchaseMessage = upperNavigationMenu
                .clickOnCartLink()
                .clickOnPlaceOrderButton()
                .placeOrderPipe();

        Assertions.assertAll(
                () -> Assertions.assertTrue(actualPurchaseMessage.contains("expectedDate"),
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