package org.example.internals;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.example.helpers.DataRep;
import org.example.internals.utils.SleepUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

import static java.lang.Thread.sleep;

public final class WebDriverExtension {

    private WebDriverExtension() {
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

    public static String getElementText(WebElement element, WebDriver driver, By by,
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
                    var webElement = driver.findElement(by);
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

    public static void forceClick(WebElement element, WebDriver driver, By by,
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
                var elementToClick = (by != null) ? driver.findElement(by) : element;
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

    public static void sendsKeysAuto(WebElement element,
                                     WebDriver driver,
                                     By by,
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

        wait.until((ExpectedCondition<Boolean>) d -> {
            try {
                WebElement el = (by != null) ? driver.findElement(by) : element;

                if (el != null && el.isDisplayed() && el.isEnabled()) {
                    el.clear(); // Clears existing text before input
                    el.sendKeys(input);

                    return true; // Successful input
                }
            } catch (StaleElementReferenceException e) {
                waitForStaleElementError();

                return false;
            }

            return true;
        });
    }
}