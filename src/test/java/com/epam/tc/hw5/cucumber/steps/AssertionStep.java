package com.epam.tc.hw5.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.en.Then;

public class AssertionStep extends AbstractStep {

    @Then("{string} page should be opened")
    public void pageShouldBeOpened(String pageTitle) {

        String actualPageTitle = differentElementsPage.getTitle();
        assertThat(actualPageTitle)
            .as("Page title is equal to " + pageTitle)
            .isEqualTo(pageTitle);
    }
}
