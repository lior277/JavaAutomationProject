import org.example.helpers.DataRep;
import org.example.objectsUi.devicePage.IDevicePage;
import org.example.objectsUi.upperMenu.IUpperNavigationMenu;
import org.example.objectsUi.upperMenu.UpperNavigationMenu;
import org.example.placeOrderForm.IPlaceOrderForm;
import org.example.placeOrderForm.PlaceOrderForm;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.example.dataObjects.ProductDTO;

public class VerifyDeviceDataWhenChoosingFromHomePageTest extends TestSuitBase {
    private IUpperNavigationMenu upperNavigationMenu;
    private IDevicePage devicePage;
    private final String expectedDeviceName = "Nokia lumia 1520";
    private final String categoryName = "phone";
    private final String username = "testuser" + System.currentTimeMillis();
    private final String password = "password123";

    // Add default constructor for TestNG
    public VerifyDeviceDataWhenChoosingFromHomePageTest() {
        super();
    }

    @BeforeTest
    void setUp() {
        // Get the driver
        WebDriver driver = getDriver();

        ProductDTO productDTO = new ProductDTO();
        IPlaceOrderForm placeOrderForm = new PlaceOrderForm(driver);

        upperNavigationMenu = new UpperNavigationMenu(driver,
                productDTO, placeOrderForm);

        driver.get(DataRep.demoBlazeUrl);

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
                .clickOnCategorieByName(categoryName)
                .clickOnDeviceByName(expectedDeviceName);
    }

    @AfterTest
    void tearDown() {
        driverDispose();
    }

    @Test
    void VerifyDeviceDataWhenChoosingFromHomePage() {
        var expectedDeviceDescription = "Nokia lumia 1520";
        var expectedDevicePrice = "$820";
        var expectedDeviceName = "nokia_lumia_1520.png";

        var deviceData = devicePage
                .getDeviceData();

        Assertions.assertAll(
                () -> Assertions.assertTrue(deviceData.productImage.contains(this.expectedDeviceName),
                        String.format("Expected Device Name: %s, Actual Device Name: %s",
                                this.expectedDeviceName, deviceData.productImage)),

                () -> Assertions.assertTrue(deviceData.productDescription.contains(expectedDeviceDescription),
                        String.format("Expected Device description: %s, Actual Device description: %s",
                                expectedDeviceDescription, deviceData.productDescription)),

                () -> Assertions.assertTrue(deviceData.productPrice.contains(expectedDevicePrice),
                        String.format("Expected Device price: %s, Actual Device price: %s",
                                expectedDevicePrice, deviceData.productPrice)),

                () -> Assertions.assertTrue(deviceData.productName.contains(expectedDeviceName),
                        String.format("Expected Device Name: %s, Actual Device Name: %s",
                                expectedDeviceName, deviceData.productName))
        );
    }
}