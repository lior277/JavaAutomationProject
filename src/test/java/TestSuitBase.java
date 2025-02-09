import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestSuitBase {
    private WebDriver driver;

    public WebDriver getDriver() {
        if (driver == null) {
            try {
                WebDriverManager.chromedriver()
                        .browserVersion("132")
                        .setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments(
                        "--start-maximized",
                        "--no-sandbox",
                        "--disable-dev-shm-usage",
                        "--ignore-ssl-errors",
                        "--ignore-certificate-errors",
                        "--remote-allow-origins=*"
                );

                options.setAcceptInsecureCerts(true);
                driver = new ChromeDriver(options);

            } catch (Exception e) {
                System.err.println("Error initializing ChromeDriver: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
        }
        return driver;
    }

    public void driverDispose() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ex) {
                System.err.println("Error disposing WebDriver: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                driver = null;
            }
        }
    }
}