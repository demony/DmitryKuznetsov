package com.epam.tc.hw3.exercise2;

import com.epam.tc.hw3.BaseTest;
import com.epam.tc.hw3.CommonTestSteps;
import com.epam.tc.hw3.pageobjects.elements.DifferentElements;
import com.epam.tc.hw3.pageobjects.elements.HeaderMenuElements;
import java.util.HashMap;
import java.util.Map;
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

        softAssertions.assertThat(headerMenuElements.getCurrentUrl())
                      .as("Different Elements Page is opened")
                      .isEqualTo(properties.getUrlDifferentElementPage());

        // 6. Select checkboxes - Water, Wind
        DifferentElements differentElements = differentElementsPage.getDifferentElements();
        differentElements.clickOnCheckboxWater();
        differentElements.clickOnCheckboxWind();
        softAssertions.assertThat(differentElements.checkboxWaterIsSelected())
                      .as("Checkbox Water is checked").isTrue();
        softAssertions.assertThat(differentElements.checkboxWindIsSelected())
                      .as("Checkbox Wind is checked").isTrue();

        // 7. Select radio - Selen
        boolean radioButtonSelenSelected = differentElements.getRadioButtonSelenClick();
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

        // assert checkboxes
        String checkboxName = "Water";
        Map<Boolean, String> checkboxLogValues;
        checkboxLogValues = differentElements.doubleClickOnCheckBoxAndCollectStatusAndLogs(checkboxName);
        assertThatCheckboxStatusCorrespondingToSelected(checkboxName, checkboxLogValues, softAssertions);

        checkboxName = "Earth";
        checkboxLogValues = differentElements.doubleClickOnCheckBoxAndCollectStatusAndLogs(checkboxName);
        assertThatCheckboxStatusCorrespondingToSelected(checkboxName, checkboxLogValues, softAssertions);

        checkboxName = "Wind";
        checkboxLogValues = differentElements.doubleClickOnCheckBoxAndCollectStatusAndLogs(checkboxName);
        assertThatCheckboxStatusCorrespondingToSelected(checkboxName, checkboxLogValues, softAssertions);

        checkboxName = "Fire";
        checkboxLogValues = differentElements.doubleClickOnCheckBoxAndCollectStatusAndLogs(checkboxName);
        assertThatCheckboxStatusCorrespondingToSelected(checkboxName, checkboxLogValues, softAssertions);

        // assert radioButtons
        String radioButtonName = "Gold";
        Boolean radioButtonIsSelected = differentElements.radioButtonClick(radioButtonName);
        assertThatRadioButtonSelectedAndLogIsCorrect(radioButtonName, radioButtonIsSelected,
            differentElements.getFirstLogMessageAsText(), softAssertions);

        radioButtonName = "Silver";
        radioButtonIsSelected = differentElements.radioButtonClick(radioButtonName);
        assertThatRadioButtonSelectedAndLogIsCorrect(radioButtonName, radioButtonIsSelected,
            differentElements.getFirstLogMessageAsText(), softAssertions);

        radioButtonName = "Bronze";
        radioButtonIsSelected = differentElements.radioButtonClick(radioButtonName);
        assertThatRadioButtonSelectedAndLogIsCorrect(radioButtonName, radioButtonIsSelected,
            differentElements.getFirstLogMessageAsText(), softAssertions);

        radioButtonName = "Selen";
        radioButtonIsSelected = differentElements.radioButtonClick(radioButtonName);
        assertThatRadioButtonSelectedAndLogIsCorrect(radioButtonName, radioButtonIsSelected,
            differentElements.getFirstLogMessageAsText(), softAssertions);

        // assert dropdown
        String dropDownElementName = "Red";
        String selectedDropdown = differentElements.dropDownColorsSelect(dropDownElementName);
        assertThatDropdownSelectedAndLogIsCorrect(dropDownElementName,
            selectedDropdown, differentElements.getFirstLogMessageAsText(), softAssertions);

        dropDownElementName = "Green";
        selectedDropdown = differentElements.dropDownColorsSelect(dropDownElementName);
        assertThatDropdownSelectedAndLogIsCorrect(dropDownElementName,
            selectedDropdown, differentElements.getFirstLogMessageAsText(), softAssertions);

        dropDownElementName = "Blue";
        selectedDropdown = differentElements.dropDownColorsSelect(dropDownElementName);
        assertThatDropdownSelectedAndLogIsCorrect(dropDownElementName,
            selectedDropdown, differentElements.getFirstLogMessageAsText(), softAssertions);

        dropDownElementName = "Yellow";
        selectedDropdown = differentElements.dropDownColorsSelect(dropDownElementName);
        assertThatDropdownSelectedAndLogIsCorrect(dropDownElementName,
            selectedDropdown, differentElements.getFirstLogMessageAsText(), softAssertions);

        // 10. Close Browser
        commonTestSteps.closeBrowser();

        softAssertions.assertAll();

    }

    public void assertThatCheckboxStatusCorrespondingToSelected(String checkboxName,
                                                                Map<Boolean, String> checkboxLogValues,
                                                                SoftAssertions softAssertions) {

        Map<Boolean, String> checkboxLogValuesExpected = new HashMap<>();
        checkboxLogValuesExpected.put(true, "" + checkboxName + ": condition changed to true");
        checkboxLogValuesExpected.put(false, "" + checkboxName + ": condition changed to false");

        softAssertions.assertThat(checkboxLogValues.get(false))
                      .as("Correct log message for unchecked " + checkboxName)
                      .contains(checkboxLogValuesExpected.get(false));

        softAssertions.assertThat(checkboxLogValues.get(true))
                      .as("Correct log message for checked water " + checkboxName)
                      .contains(checkboxLogValuesExpected.get(true));
    }

    public void assertThatRadioButtonSelectedAndLogIsCorrect(
        String radioButtonName, Boolean isSelected, String logValue, SoftAssertions softAssertions) {

        softAssertions.assertThat(isSelected)
                      .as("Check that " + radioButtonName + " is selected")
                      .isTrue();

        softAssertions.assertThat(logValue)
                      .as("Correct log message when selected " + radioButtonName + " selector")
                      .contains("metal: value changed to " + radioButtonName);

    }

    private void assertThatDropdownSelectedAndLogIsCorrect(
        String dropDownElementName, String selectedDropdown, String firstLogMessageAsText,
        SoftAssertions softAssertions) {

        softAssertions.assertThat(selectedDropdown)
                      .as("Selected color is " + selectedDropdown)
                      .isEqualTo(dropDownElementName);

        softAssertions.assertThat(firstLogMessageAsText)
                      .as("The log message for select " + selectedDropdown + " color is correct")
                      .contains("Colors: value changed to " + dropDownElementName);

    }
}