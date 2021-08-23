package com.epam.tc.hw4.teststeps;

import com.epam.tc.hw4.pageobjects.DifferentElements;
import io.qameta.allure.Step;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;

public class DifferentElementsTestSteps {

    private final DifferentElements differentElements;
    private SoftAssertions softAssertions;

    public DifferentElementsTestSteps(WebDriver webDriver) {
        differentElements = new DifferentElements(webDriver);
    }

    public void setSoftAssertions(SoftAssertions softAssertions) {
        this.softAssertions = softAssertions;
    }

    @Step("Set checkboxes water and wind")
    public void setCheckboxesWaterAndWind() {
        differentElements.clickOnCheckboxWater();
        differentElements.clickOnCheckboxWind();
        softAssertions.assertThat(differentElements.checkboxWaterIsSelected())
                      .as("Checkbox Water is checked").isTrue();
        softAssertions.assertThat(differentElements.checkboxWindIsSelected())
                      .as("Checkbox Wind is checked").isTrue();
    }

    @Step("Select radio button Selen")
    public void selectRadioButtonSelen() {
        boolean radioButtonSelenSelected = differentElements.getRadioButtonSelenClick();
        softAssertions.assertThat(radioButtonSelenSelected).as("Radio button Selen is selected").isTrue();
    }

    public void selectDropDownYellow() {
        String selectedAsString = differentElements.dropDownColorsSelect("Yellow");
        softAssertions.assertThat(selectedAsString)
                      .as("Selected color is Yellow")
                      .isEqualTo("Yellow");
    }

    @Step("Assert that selectors has correct log message")
    public void assertThatSelectorsHasCorrectLogMessage() {

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
