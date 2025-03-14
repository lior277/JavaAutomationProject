import org.example.dataObjects.ProductDTO;
import org.example.helpers.DataRep;
import org.example.objectsUi.devicePage.IDevicePage;
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

public class VerifyDeviceDataInCartWhenAddingToCartTest extends TestSuitBase {
    private IDevicePage devicePage;
    private IUpperNavigationMenu upperNavigationMenu;
    private final String expectedDeviceName = "Nokia lumia 1520";
    private final String categoryName = "phone";
    private final String username = "testuser" + System.currentTimeMillis();
    private final String password = "password123";

    @BeforeEach
    void setUp() {
        // Get the driver
        WebDriver driver = getDriver();

        var productDTO = new ProductDTO();
        IPlaceOrderForm placeOrderForm = new PlaceOrderForm(driver);

        upperNavigationMenu = new UpperNavigationMenu(driver,
                productDTO, placeOrderForm);

        driver.get(DataRep.demoBlazeUrl);

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

        // choose device from home page
        homepage
                .clickOnCategorieByName(categoryName)
                .clickOnDeviceByName(expectedDeviceName);
    }

    @AfterEach
    void tearDown() {
        driverDispose();
    }

    @Test
    void verifyDeviceDataInCartWhenAddingToCart() {
        var expectedDevicePrice = "820";
        var expectedDeviceImageName = "Lumia_1520.jpg";

        devicePage
                .clickOnAddToChartButton()
                .getDeviceData();

        var deviceData = upperNavigationMenu
                .clickOnCartLink()
                .getCartItemInfoByName(expectedDeviceName, expectedDevicePrice);

        Assertions.assertAll(
                () -> Assertions.assertTrue(deviceData.productImage.contains(expectedDeviceImageName),
                        String.format("Expected Device Name: %s, Actual Device Name: %s",
                                this.expectedDeviceName, deviceData.productImage)),

                () -> Assertions.assertTrue(deviceData.productPrice.contains(expectedDevicePrice),
                        String.format("Expected Device price: %s, Actual Device price: %s",
                                expectedDevicePrice, deviceData.productPrice)),

                () -> Assertions.assertTrue(deviceData.productName.contains(expectedDeviceName),
                        String.format("Expected Device Name: %s, Actual Device Name: %s",
                                expectedDeviceName, deviceData.productName))
        );
    }
}