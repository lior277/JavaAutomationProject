package org.example.homePage;

import org.example.internals.WebDriverExtension;
import org.example.login.ILoginPage;
import org.example.login.LoginPage;
import org.example.searchResults.SearchResultsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage implements IHomePage {
    private WebDriver driver;

    // Locators
    private By searchJobFiledExp = By.cssSelector("input[id='input-277']");
    private By searchBtnExp = By.cssSelector("button[class*='search-btn']");
    private By clickOnAreaFilterExp = By.xpath("//button[contains(.,'תחום')]");
    private By clickOnSaveAreaFilterExp = By.cssSelector("button[data-cy*='cy-button-subcat-filter-btn']");
    private By ChooseAreaExp = By.xpath("//label[@class='v-label theme--light'][contains(.,'מהנדס/ת QA')]");
    private By loginLinkExp = By.cssSelector("button[class*='login-btn']");
    private By searchJobAutoCompleteExp = By.xpath("//div[@class='v-list-item__content']//div[text()='QA Engineer']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public IHomePage navigateToUrl(String url) {
        driver.get(url);

        return this;
    }

    @Override
    public ILoginPage clickOnLoginLink() {

        WebElement element = WebDriverExtension
                .searchElement(driver, loginLinkExp, null);

        WebDriverExtension
                .forceClick(element, driver, loginLinkExp, null);

        return new LoginPage(driver);
    }

    @Override
    public IHomePage setJob(String jobName) {
        var element = WebDriverExtension
                .searchElement(driver, searchJobFiledExp, null);

        WebDriverExtension
                .sendsKeysAuto(element, driver, searchJobFiledExp, jobName,
                        null);

        return this;
    }

    @Override
    public IHomePage clickOnAutoComplete(String permissionName) {
        //var searchJobAutoCompleteExp = By
        //.xpath(String.format(searchJobAutoComplete, permissionName));

        var element = WebDriverExtension
                .searchElement(driver, searchJobAutoCompleteExp, null);

        WebDriverExtension
                .forceClick(element, driver, searchJobAutoCompleteExp, null);

        return this;
    }

    @Override
    public org.example.objects.ui.searchResults.ISearchResultsPage chooseAreaFilter() {

        // open choose Area filter
        var element = WebDriverExtension
                .searchElement(driver, clickOnAreaFilterExp, null);

        WebDriverExtension
                .forceClick(element, driver, clickOnAreaFilterExp, null);

        // choose Area
        element = WebDriverExtension
                .searchElement(driver, ChooseAreaExp, null);

        WebDriverExtension
                .forceClick(element, driver, ChooseAreaExp, null);

        // click on save filter
        element = WebDriverExtension
                .searchElement(driver, clickOnSaveAreaFilterExp, null);

        WebDriverExtension
                .forceClick(element, driver, clickOnSaveAreaFilterExp, null);

        org.example.objects.ui.searchResults.ISearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        return searchResultsPage;
    }

    @Override
    public org.example.objects.ui.searchResults.ISearchResultsPage clickOnSearchButton() {
        var element = WebDriverExtension
                .searchElement(driver, searchBtnExp, null);

        WebDriverExtension
                .forceClick(element, driver, searchBtnExp, null);

        org.example.objects.ui.searchResults.ISearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        return searchResultsPage;
    }
}
