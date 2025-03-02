import org.example.helpers.DataRep;
import org.example.objectsUi.devicePage.IDevicePage;
import org.example.objectsUi.upperMenu.IUpperNavigationMenu;
import org.example.objectsUi.upperMenu.UpperNavigationMenu;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.example.dataObjects.ProductDTO;

public class VerifyDeviceDataWhenChoosingFromHomePageTest extends TestSuitBase {
    private IDevicePage devicePage;
    private String expectedDeviceName = "Nokia lumia 1520";

    // Add default constructor for TestNG
    public VerifyDeviceDataWhenChoosingFromHomePageTest() {
        super();
    }

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
                .clickOnDeviceImageByName("Lumia_1520");
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