import org.example.homePage.HomePage;
import org.example.homePage.IHomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.example.helpers.DataRep;
import org.openqa.selenium.WebDriver;

import java.sql.DriverManager;

public class VerifyActiveCampaignCanBeCreatedTest extends TestSuitBase {
    private WebDriver driver;
    private IHomePage homePageUi;

    @BeforeTest
    void setUp() {
        driver = getDriver();
        homePageUi = new HomePage(driver);

        // login process
        var pageUrl = DataRep.ETHOSIA_URL;
        homePageUi
                .navigateToUrl(pageUrl)
                .clickOnLoginLink()
                .setUserName("lior277@gmail.com")
                .setPassword("Liorh963")
                .clickOnLoginButton();
    }

    @AfterTest
    void tearDown() {
        try {
        } finally {
            driverDispose();
        }
    }

    @Test
    void verifyActiveCampaignCanBeCreated() {
        String actualCampaignName = "";
        String expectedCampaignName = "";

        // search for a job
        homePageUi
                .setJob("QA Engineer")
                .clickOnAutoComplete("QA Engineer")
                .clickOnSearchButton();

        // set the area filter
        var jobData = homePageUi
                .chooseAreaFilter()
                .getJobData();

        Assertions.assertAll(
                () -> Assertions.assertTrue(jobData.contains("Comm-IT"),
                        () -> String.format("Expected: %s, Actual: %s",
                                expectedCampaignName, actualCampaignName)));
    }
}