package com.epam.tc.hw5.cucumber.steps;

import io.cucumber.java.en.Given;


public class GivenStep extends AbstractStep {

    @Given("I open JDI GitHub site")
    public void openSiteGDI() {
        homePage.open();
    }

    @Given("I login as user {string}")
    public void loginAsUser(String fullUserName) {
        if (fullUserName.equals("Roman Iovlev")) {
            login(properties.getUserLogin(), properties.getUserPassword());
        } else {
            throw new RuntimeException("Such user not implemented: " + fullUserName);
        }
    }

    @Given("I login as {string} and password {string}")
    public void login(String login, String password) {
        homePage.clickToUserIcon();
        homePage.sendKeysToUserLoginField(login);
        homePage.sendKeysToUserPasswordField(password);
        homePage.clickToLoginButton();
    }
}
