package com.epam.tc.hw4.exercise2;

import com.epam.tc.hw4.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

@Feature("Site structure should be correct and selectors should work properly also")
public class SiteSelectorsTests extends BaseTest {

    @Test
    @Description("Test that checks behavior of selectors (exercise 2)")
    @Story("Selectors should work properly")
    public void siteSelectors_workCorrectly() {

        // 1. Open test site by URL
        commonTestSteps.openWebSite(properties.getUrlMainPage());

        // 2. Assert Browser title
        commonTestSteps.assertThatTitleIsCorrect("Home Page");

        // 3. Perform login
        commonTestSteps.login(properties.getUserLogin(), properties.getUserPassword());

        //  4. Assert Username
        commonTestSteps.assertUserName(properties.getUserName());

        // 5.Open through the header menu Service -> Different Elements Page
        siteStructureTestSteps.openDifferentElementsPage(properties.getUrlDifferentElementPage());

        // 6. Select checkboxes - Water, Wind

        differentElementsTestSteps.setCheckboxesWaterAndWind();

        // 7. Select radio - Selen
        differentElementsTestSteps.selectRadioButtonSelen();

        // 8. Select in dropdown - Yellow
        differentElementsTestSteps.selectDropDownYellow();

        // 9. Assert that
        //    • for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        //    • for radio button there is a log row and value is corresponded to the status of radio button
        //    • for dropdown there is a log row and value is corresponded to the selected value.
        differentElementsTestSteps.assertThatSelectorsHasCorrectLogMessage();

        // 10. Close Browser
        commonTestSteps.closeBrowser();

        softAssertions.assertAll();

    }

}
