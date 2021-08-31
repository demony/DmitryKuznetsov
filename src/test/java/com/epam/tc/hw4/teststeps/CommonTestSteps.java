package com.epam.tc.hw4.teststeps;

import com.epam.tc.hw4.pageobjects.LoginElements;
import com.epam.tc.hw4.pages.BasePage;
import com.epam.tc.hw4.pages.MainPage;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;

public class CommonTestSteps {

    private final LoginElements loginElements;
    private final MainPage mainPage;
    private SoftAssertions softAssertions;

    public CommonTestSteps(WebDriver webDriver) {

        mainPage = new MainPage(webDriver);
        loginElements = mainPage.getLoginElements();
    }

    public void setSoftAssertions(SoftAssertions softAssertions) {
        this.softAssertions = softAssertions;
    }

    @Step("Open site")
    public void openWebSite(String url) {
        mainPage.openURL(url);
        softAssertions.assertThat(mainPage.getCurrentUrl()).as("The site is opened").isEqualTo(url);
    }

    @Step("Check title")
    public void assertThatTitleIsCorrect(String titleExpected) {
        softAssertions.assertThat(mainPage.getTitle())
                      .as("Browser title equals 'Home Page'")
                      .isEqualTo(titleExpected);
    }

    @Step("Login to site")
    public void login(String userLogin, String userPassword) {
        boolean loginSuccess = loginElements.login(userLogin, userPassword);
        softAssertions.assertThat(loginSuccess)
                      .as("User is logged in")
                      .isTrue();
    }

    @Step("Assert username")
    public void assertUserName(String userName) {
        boolean currentUserIsDisplayed = loginElements.currentUserIsDisplayed();
        softAssertions.assertThat(currentUserIsDisplayed)
                      .as("Name is displayed")
                      .isTrue();

        String currentUser = loginElements.currentUser();
        softAssertions.assertThat(currentUser)
                      .as("Name is equals to expected result")
                      .isEqualTo(userName);
    }

}
