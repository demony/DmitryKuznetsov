package com.epam.tc.hw4.failtestexamples;

import com.epam.tc.hw4.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

@Feature("Site structure should be correct and selectors should work properly also")
public class FailSiteStructureTests extends BaseTest {

    @Test
    @Description("Test that checks site structure (exercise 1)")
    @Story("Site structure should be correct (SHOULD FAIL)")
    public void siteStructure_isCorrect_Should_Fail() {

        //SoftAssertions softAssertions = new SoftAssertions();
        //commonTestSteps.setSoftAssertions(softAssertions);

        // 1. Open test site by URL
        commonTestSteps.openWebSite(properties.getUrlMainPage());

        // 2. Assert Browser title
        // set "wrong title"
        // not "Home Page"
        commonTestSteps.assertThatTitleIsCorrect("wrong title");

        // 3. Perform login
        // set "wrong password"
        // properties.getUserPassword()
        commonTestSteps.login(properties.getUserLogin(), properties.getUserPassword());

        //  4. Assert Username
        // wrong username
        // properties.getUserName()
        commonTestSteps.assertUserName(properties.getUserName());

        softAssertions.assertAll();
    }

}
