package com.epam.tc.hw3;

import com.epam.tc.hw3.pageobjects.elements.LoginElements;
import com.epam.tc.hw3.pageobjects.pages.MainPage;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;

public class CommonTestSteps {

    private final LoginElements loginElements;
    private final SoftAssertions softAssertions;

    public CommonTestSteps(MainPage mainPage, SoftAssertions softAssertions) {

        this.loginElements = mainPage.getLoginPageObjects();
        this.softAssertions = softAssertions;
    }

    public void openWebSite(String url) {
        loginElements.openURL(url);
        softAssertions.assertThat(loginElements.getCurrentUrl()).as("The site is opened").isEqualTo(url);
    }

    public void assertThatTitleIsCorrect(String titleExpected) {
        softAssertions.assertThat(loginElements.getTitle())
                      .as("Browser title equals 'Home Page'")
                      .isEqualTo("Home Page");
    }

    public void login(String userLogin, String userPassword) {
        boolean loginSuccess = loginElements.login(userLogin, userPassword);
        softAssertions.assertThat(loginSuccess)
                      .as("User is logged in")
                      .isTrue();
    }

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

    public void closeBrowser() {
        boolean browserIsClosed = loginElements.closeBrowser();
        softAssertions.assertThat(browserIsClosed).as("Browser is closed").isTrue();
    }

}
