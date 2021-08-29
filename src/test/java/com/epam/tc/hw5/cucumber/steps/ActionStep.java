package com.epam.tc.hw5.cucumber.steps;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.When;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ActionStep extends AbstractStep {

    @When("I click on {string} button in Header")
    public void clickOnButtonInHeader(String menuName) {
        homePage.clickToHeaderMenuElement(menuName);
    }

    @When("I click on {string} button in Service dropdown")
    public void clickOnButtonInServiceDropdown(String submenuName) {
        homePage.clickToMenuElementInServiceDropDown(submenuName);
    }

    @When("I click on {string} button in Header and click on submenu {string} in dropdown")
    public void clickOnButtonInHeaderAndClickOnSubmenuInDropdown(String menuName, String submenuName) {
        clickOnButtonInHeader(menuName);
        clickOnButtonInServiceDropdown(submenuName);
    }

    @ParameterType("'(.+)'")
    public List<String> elementNames(String elementNamesAsString) {
        return Arrays.stream(elementNamesAsString.split(","))
                     .map(el -> el.trim())
                     .collect(Collectors.toList());
    }

    @When("I select checkboxes {elementNames}")
    public void selectCheckboxes(List<String> elementNames) {
        differentElementsPage.clickOnCheckboxes(elementNames);
    }

    @When("I select radio button {string}")
    public void selectRadio(String elementName) {
        differentElementsPage.clickOnRadioButton(elementName);
    }

    @When("I select dropdown {string}")
    public void selectDropdown(String elementName) {
        differentElementsPage.clickOnDropdown(elementName);
    }

    @When("I select 'vip' checkbox for {string}")
    public void selectCheckboxFor(String userName) {
        userTablePage.selectVipCheckBoxByUserName(userName);
    }

}
