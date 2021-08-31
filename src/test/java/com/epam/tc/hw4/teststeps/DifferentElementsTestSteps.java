package com.epam.tc.hw4.teststeps;

import com.epam.tc.hw4.pageobjects.DifferentElements;
import com.epam.tc.hw4.pages.DifferentElementsPage;
import io.qameta.allure.Step;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;

public class DifferentElementsTestSteps {

    private final DifferentElements differentElements;
    private SoftAssertions softAssertions;

    public DifferentElementsTestSteps(WebDriver webDriver) {

        differentElements = new DifferentElementsPage(webDriver).getDifferentElements();
    }

    public void setSoftAssertions(SoftAssertions softAssertions) {
        this.softAssertions = softAssertions;
    }

    @Step("Set checkboxes water and wind")
    public void setCheckboxesWaterAndWind() {
        String checkboxName = "Water";
        Boolean isSelected = differentElements.clickOnCheckBox(checkboxName);
        softAssertions.assertThat(isSelected)
                      .as("Checkbox " + checkboxName + " is checked").isTrue();

        checkboxName = "Wind";
        isSelected = differentElements.clickOnCheckBox(checkboxName);
        softAssertions.assertThat(isSelected)
                      .as("Checkbox " + checkboxName + " is checked").isTrue();
    }

    @Step("Select radio button Selen")
    public void selectRadioButtonSelen() {
        boolean radioButtonSelenSelected = differentElements.clickOnRadioButton("Selen");
        softAssertions.assertThat(radioButtonSelenSelected).as("Radio button Selen is selected").isTrue();
    }

    @Step("Select color Yellow")
    public void selectDropDownYellow() {
        String selectedAsString = differentElements.dropDownColorsSelect("Yellow");
        softAssertions.assertThat(selectedAsString)
                      .as("Selected color is Yellow")
                      .isEqualTo("Yellow");
    }

    @Step("Assert that selectors has correct log message")
    public void assertThatSelectorsHasCorrectLogMessage() {

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
