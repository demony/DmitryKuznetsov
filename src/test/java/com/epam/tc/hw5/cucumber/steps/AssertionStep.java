package com.epam.tc.hw5.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;

public class AssertionStep extends AbstractStep {

    @Then("{string} page should be opened")
    public void pageShouldBeOpened(String pageTitle) {
        String actualPageTitle = differentElementsPage.getTitle();
        assertThat(actualPageTitle)
            .as("Page title is equal to " + pageTitle)
            .isEqualTo(pageTitle);
    }

    @Then("User should be logged in")
    public void userShouldBeLoggedIn() {
        assertThat(homePage.userIsLoggedIn()).as("User should be logged in.").isTrue();
    }

    @ParameterType("'(.+)'")
    public List<String> elementNamesTest(String elementNamesAsString) {
        return Arrays.stream(elementNamesAsString.split(","))
                     .map(el -> el.trim())
                     .collect(Collectors.toList());
    }

    @Then("Checkboxes {elementNamesTest} should be checked")
    public void checkboxesShouldBeChecked(List<String> elementNamesTest) {
        List<String> selectedCheckboxes = differentElementsPage.getSelectedCheckboxes();
        assertThat(selectedCheckboxes)
            .as("Checkboxes should be checked")
            .hasSameElementsAs(elementNamesTest);
    }

    @Then("Radio button {string} should be checked")
    public void radioButtonShouldBeChecked(String elementName) {
        assertThat(differentElementsPage.radioButtonIsChecked(elementName))
            .as("Radio button should be checked")
            .isTrue();
    }

    @Then("Dropdown {string} should be selected")
    public void dropdownShouldBeChecked(String elementName) {
        assertThat(differentElementsPage.dropdownColorGetSelected())
            .as("Correct color should be selected")
            .isEqualTo(elementName);
    }

    @Then("Log rows are displayed and corresponded to text:")
    public void logRowsAreDisplayedAndCorrespondedToText(DataTable dataTable) {
        List<String> logElementsExpected = dataTable.asList(String.class);
        assertThat(differentElementsPage.getLogMessages())
            .as("Log messages are displayed and correct")
            .isEqualTo(logElementsExpected);
    }


    @Then("{int} Number Type Dropdowns should be displayed on Users Table on User Table Page")
    public void numberTypeDropdownsShouldBeDisplayedOnUsersTableOnUserTablePage(Integer expectedAmount) {
        assertThat(userTablePage.getDropdowns())
            .as("Correct amount of dropdowns on table page")
            .hasSize(expectedAmount);
    }

    @Then("{int} Usernames should be displayed on Users Table on User Table Page")
    public void usernamesShouldBeDisplayedOnUsersTableOnUserTablePage(Integer expectedAmount) {
        assertThat(userTablePage.getUsernames())
            .as("Correct amount of usernames on table page")
            .hasSize(expectedAmount);
    }

    @Then("{int} Description texts under images should be displayed on Users Table on User Table Page")
    public void descriptionTextsUnderImagesShouldBeDisplayedOnUsersTableOnUserTablePage(Integer expectedAmount) {
        assertThat(userTablePage.getDescriptionTexts())
            .as("Correct amount of description texts on table page")
            .hasSize(expectedAmount);
    }

    @Then("{int} checkboxes should be displayed on Users Table on User Table Page")
    public void checkboxes_should_be_displayed_on_users_table_on_user_table_page(Integer expectedAmount) {
        assertThat(userTablePage.getCheckboxes())
            .as("Correct amount of checkboxes texts on table page")
            .hasSize(expectedAmount);
    }

    @Then("User table should contain following values:")
    public void userTableShouldContainFollowingValues(DataTable dataTable) {
        List<String> expectedNumbers = dataTable.column(0).stream().skip(1).collect(Collectors.toList());
        List<String> actualRowNumbers = userTablePage.getElementsAsListOfStrings(userTablePage.getRowNumbers());
        assertThat(actualRowNumbers).as("Check column numbers in table").isEqualTo(expectedNumbers);
        List<String> expectedNames = dataTable.column(1).stream().skip(1).collect(Collectors.toList());
        List<String> actualUserNames = userTablePage.getElementsAsListOfStrings(userTablePage.getUsernames());
        assertThat(actualUserNames).as("Check column names in table").isEqualTo(expectedNames);
        List<String> expectedDescription = dataTable.column(2).stream().skip(1).collect(Collectors.toList());
        List<String> actualDescriptions = userTablePage
            .getElementsAsListOfStrings(userTablePage.getDescriptionTexts());
        assertThat(actualDescriptions).as("Check column description in table")
                                      .isEqualTo(expectedDescription);
    }

    @Then("droplist should contain values in column Type for user Roman")
    public void droplistShouldContainValuesInColumnTypeForUserRoman(DataTable dataTable) {
        List<String> expectedValues = dataTable.column(0).stream().skip(1).collect(Collectors.toList());
        List<String> actualOptions = userTablePage.getDropDownOptionsByUserName("Roman");
        assertThat(actualOptions).as("Check droplist values for Roman").isEqualTo(expectedValues);
    }

    @Then("{int} log row has {string} text in log section")
    public void logRowHasTextInLogSection(Integer logRowIndex, String logText) {
        assertThat(userTablePage.getLogByIndex(logRowIndex))
            .as("Check log message on index " + logRowIndex)
            .contains(logText);
    }
}
