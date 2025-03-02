import org.example.dataObjects.ProductDTO;
import org.example.helpers.DataRep;
import org.example.objectsUi.devicePage.IDevicePage;
import org.example.objectsUi.upperMenu.IUpperNavigationMenu;
import org.example.objectsUi.upperMenu.UpperNavigationMenu;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyDeviceDataInCartWhenAddingToCartTest extends TestSuitBase {
    private IDevicePage devicePage;
    private IUpperNavigationMenu upperNavigationMenu;
    private final String expectedDeviceName = "Nokia lumia 1520";

    @BeforeTest
    void setUp() {
        // Get the driver
        WebDriver driver = getDriver();

        ProductDTO productDTO = new ProductDTO();
        IUpperNavigationMenu upperNavigationMenu = new UpperNavigationMenu(driver, productDTO);
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
                .clickOnDeviceImageByName(expectedDeviceName);
    }

    @AfterTest
    void tearDown() {
        driverDispose();
    }

    @Test
    void VerifyDeviceDataInCartWhenAddingToCart() {
        upperNavigationMenu = new UpperNavigationMenu(getDriver(), new ProductDTO());
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