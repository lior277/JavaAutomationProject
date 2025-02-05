package org.example.homePage;

import org.example.login.ILoginPage;
import org.example.searchResults.ISearchResultsPage;


public interface IHomePage {
    IHomePage setJob(String jobName);
    IHomePage clickOnAutoComplete(String permissionName);
    ILoginPage clickOnLoginLink();
    ISearchResultsPage chooseAreaFilter();
    ISearchResultsPage clickOnSearchButton();
    IHomePage navigateToUrl(String url);

}
