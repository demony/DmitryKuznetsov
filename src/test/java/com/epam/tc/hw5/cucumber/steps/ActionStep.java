package com.epam.tc.hw5.cucumber.steps;

import io.cucumber.java.en.When;

public class ActionStep extends AbstractStep {

    @When("I click on {string} button in Header")
    public void clickOnButtonInHeader(String menuName) {
        homePage.clickToHeaderMenuElement(menuName);
    }

    @When("I click on {string} button in Service dropdown")
    public void clickOnButtonInServiceDropdown(String submenuName) {
        homePage.clickToMenuElementInServiceDropDown(submenuName);
    }
}
