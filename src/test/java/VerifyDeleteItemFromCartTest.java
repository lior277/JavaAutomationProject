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

public class VerifyDeleteItemFromCartTest extends TestSuitBase {
    private IUpperNavigationMenu upperNavigationMenu;
    private final String expectedFirstDeviceName = "Nokia lumia 1520";
    private final String expectedSecondDeviceName = "Nexus 6";
    private final String categoryName = "phone";
    private final String username = "testuser" + System.currentTimeMillis();
    private final String password = "password123";

    public VerifyDeleteItemFromCartTest() {
        super();
    }

    @BeforeEach
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

        // add the first device to the cart
        upperNavigationMenu
                .clickOnLoginLink()
                .setUserName(username)
                .setPassword(password)
                .clickOnLoginButton()
                .clickOnCategorieByName(categoryName)
                .clickOnDeviceByName(expectedFirstDeviceName)
                .clickOnAddToChartButton();

        // add the second device to the cart
        upperNavigationMenu
                .clickOnHomeLink()
                .clickOnCategorieByName(categoryName)
                .clickOnDeviceByName(expectedSecondDeviceName)
                .clickOnAddToChartButton();
    }

    @AfterEach
    void tearDown() {
        driverDispose();
    }

    @Test
    void vVerifyDeleteItemFromCartTest() {
        var cartItems = upperNavigationMenu
                .clickOnCartLink()
                .deleteItemFromCartByName(expectedFirstDeviceName)
                .getCartItems();

        Assertions.assertAll(
                () -> Assertions.assertTrue(1 == cartItems.size(),
                        String.format("Expected num of products: %s, Actual num of products: %s",
                                1, cartItems.size())),

                () -> Assertions.assertTrue(cartItems.getFirst().getText()
                                .contains(expectedSecondDeviceName),
                        String.format("Expected Device Name %s, Actual Device Name: %s",
                                expectedSecondDeviceName, cartItems.getFirst().getText()))
        );
    }
}