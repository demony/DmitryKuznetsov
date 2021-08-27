package com.epam.tc.hw3.exercise2;

import com.epam.tc.hw3.BaseTest;
import com.epam.tc.hw3.CommonTestSteps;
import com.epam.tc.hw3.pageobjects.elements.DifferentElements;
import com.epam.tc.hw3.pageobjects.elements.HeaderMenuElements;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SiteSelectorsTests extends BaseTest {

    @Test
    public void siteSelectors_workCorrectly() {
        SoftAssertions softAssertions = new SoftAssertions();

        CommonTestSteps commonTestSteps = new CommonTestSteps(mainPage, softAssertions);

        // 1. Open test site by URL
        commonTestSteps.openWebSite(properties.getUrlMainPage());

        // 2. Assert Browser title
        commonTestSteps.assertThatTitleIsCorrect("Home Page");

        // 3. Perform login
        commonTestSteps.login(properties.getUserLogin(), properties.getUserPassword());

        //  4. Assert Username
        commonTestSteps.assertUserName(properties.getUserName());

        // 5.Open through the header menu Service -> Different Elements Page
        HeaderMenuElements headerMenuElements = mainPage.getHeaderMenuElements();
        headerMenuElements.openPageDifferentElements();

        softAssertions.assertThat(mainPage.getCurrentUrl())
                      .as("Different Elements Page is opened")
                      .isEqualTo(properties.getUrlDifferentElementPage());

        // 6. Select checkboxes - Water, Wind
        DifferentElements differentElements = differentElementsPage.getDifferentElements();

        String checkboxName = "Water";
        Boolean isSelected = differentElements.clickOnCheckBox(checkboxName);
        softAssertions.assertThat(isSelected)
                      .as("Checkbox " + checkboxName + " is checked").isTrue();

        checkboxName = "Wind";
        isSelected = differentElements.clickOnCheckBox(checkboxName);
        softAssertions.assertThat(isSelected)
                      .as("Checkbox " + checkboxName + " is checked").isTrue();

        // 7. Select radio - Selen
        boolean radioButtonSelenSelected = differentElements.clickOnRadioButton("Selen");
        softAssertions.assertThat(radioButtonSelenSelected).as("Radio button Selen is selected").isTrue();

        // 8. Select in dropdown - Yellow
        String selectedAsString = differentElements.dropDownColorsSelect("Yellow");
        softAssertions.assertThat(selectedAsString)
                      .as("Selected color is Yellow")
                      .isEqualTo("Yellow");

        // 9. Assert that
        //    • for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        //    • for radio button there is a log row and value is corresponded to the status of radio button
        //    • for dropdown there is a log row and value is corresponded to the selected value.

        List<String> logElementsActual = differentElements.getLogElementsTrimDates();

        List<String> logElementsExpected = Arrays.asList(
            "Colors: value changed to Yellow",
            "metal: value changed to Selen",
            "Wind: condition changed to true",
            "Water: condition changed to true");

        softAssertions.assertThat(logElementsActual)
                      .as("Logs rows are displayed and correct")
                      .isEqualTo(logElementsExpected);

    }
}
