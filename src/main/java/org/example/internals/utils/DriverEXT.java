package org.example.internals.utils;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.example.helpers.DataRep;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Arrays;

public class DriverEXT {

    private DriverEXT() {
        throw new AssertionError("Utility class - do not instantiate");
    }

    private static void waitForStaleElementError() {
        SleepUtil.sleep(400);
    }

    public static WebElement searchElement(WebDriver driver, By by,
                                           @Nullable Integer fromSeconds) {
        var timeout = fromSeconds != null ? fromSeconds : DataRep.TIME_TO_WAIT_FROM_SECONDS;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        wait.ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(InvalidSelectorException.class)
                .ignoring(NoSuchFrameException.class)
                .ignoring(WebDriverException.class);

        return wait.until(d -> {
            try {
                var element = driver.findElement(by);

                return element;
            } catch (StaleElementReferenceException e) {
                waitForStaleElementError();

                return null;
            }
        });
    }

    public static String getElementText(WebDriver driver, By by,
                                        @Nullable Integer fromSeconds) {
        var timeout = fromSeconds != null ? fromSeconds : DataRep.TIME_TO_WAIT_FROM_SECONDS;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.ignoreAll(Arrays.asList(
                NoSuchElementException.class,
                ElementNotInteractableException.class,
                InvalidSelectorException.class,
                NoSuchFrameException.class,
                WebDriverException.class
        ));

        String newString = "";

        try {
            newString = wait.until(d -> {
                try {
                    var webElement = searchElement(driver, by, timeout);
                    String text = webElement.getText();
                    String newText = text.replace(System.lineSeparator(), " ");
                    return newText;
                } catch (StaleElementReferenceException e) {
                    waitForStaleElementError();

                    return null;
                }
            });
        } catch (TimeoutException e) {
            waitForStaleElementError();
        }

        return newString;
    }

    public static void forceClick(WebDriver driver, By by, @Nullable Integer fromSeconds) {
        var timeout = fromSeconds != null ? fromSeconds : DataRep.TIME_TO_WAIT_FROM_SECONDS;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        wait.ignoreAll(Arrays.asList(
                NoSuchElementException.class,
                ElementNotInteractableException.class,
                InvalidSelectorException.class,
                NoSuchFrameException.class,
                WebDriverException.class
        ));

        wait.until(d -> {
            try {
                var elementToClick = searchElement(driver, by, timeout);
                if (elementToClick.isEnabled()) {
                    elementToClick.click();

                    return true;
                }

                return false;

            } catch (StaleElementReferenceException e) {
                waitForStaleElementError();

                return false;
            }
        });
    }

    public static void
    selectElementFromDropDownByText(WebElement element,
                                    WebDriver driver, By by, String input,
                                    @Nullable Integer fromSeconds) {
        var timeout = fromSeconds != null ? fromSeconds : DataRep.TIME_TO_WAIT_FROM_SECONDS;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        wait.ignoreAll(Arrays.asList(
                NoSuchElementException.class,
                ElementNotInteractableException.class,
                InvalidSelectorException.class,
                NoSuchFrameException.class,
                WebDriverException.class
        ));

        wait.until(d -> {
            try {
                WebElement selectElement = (by != null) ? driver.findElement(by) : element;

                new Select(selectElement)
                        .selectByVisibleText(input);

                return true;
            } catch (StaleElementReferenceException e) {
                waitForStaleElementError();

                return false;
            }
        });
    }

    public static void selectElementFromDropDownByValue(WebElement element,
                                                        WebDriver driver,
                                                        String input,
                                                        @Nullable Integer fromSeconds) {
        var timeout = fromSeconds != null ? fromSeconds : DataRep.TIME_TO_WAIT_FROM_SECONDS;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        wait.ignoreAll(Arrays.asList(
                NoSuchElementException.class,
                ElementNotInteractableException.class,
                InvalidSelectorException.class,
                NoSuchFrameException.class,
                WebDriverException.class
        ));

        wait.until(d -> {
            try {
                new Select(element)
                        .selectByValue(input);

                return true;
            } catch (StaleElementReferenceException e) {
                waitForStaleElementError();
                return false;
            }
        });
    }

    public static void sendsKeysAuto(WebDriver driver,
                                     By by,
                                     String inputText,
                                     @Nullable Integer fromSeconds) {
        var timeout = fromSeconds != null ? fromSeconds : DataRep.TIME_TO_WAIT_FROM_SECONDS;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        wait.ignoreAll(Arrays.asList(
                NoSuchElementException.class,
                ElementNotInteractableException.class,
                InvalidSelectorException.class,
                NoSuchFrameException.class,
                WebDriverException.class
        ));

        wait.until((ExpectedCondition<Boolean>) d -> {
            try {
                var element = searchElement(driver, by, timeout);
                var expectedText = element.getAttribute("value");

                if (!expectedText.equals(inputText)) {
                    element.clear();
                    element.sendKeys(inputText);

                    return false;
                }

                return true;

            } catch (StaleElementReferenceException e) {
                waitForStaleElementError();

                return false;
            }
        });
    }

    public static void closeAlertMessage(WebDriver driver, @Nullable Integer fromSeconds) {
        var timeout = fromSeconds != null ? fromSeconds : DataRep.TIME_TO_WAIT_FROM_SECONDS;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        wait.ignoreAll(Arrays.asList(
                NoSuchElementException.class,
                ElementNotInteractableException.class,
                InvalidSelectorException.class,
                NoSuchFrameException.class,
                WebDriverException.class
        ));

        wait.until((ExpectedCondition<Boolean>) d -> {
            try {
                var alert = driver.switchTo().alert();
                alert.accept();

                return true;

            } catch (StaleElementReferenceException e) {
                waitForStaleElementError();

                return false;
            }
        });
    }
}