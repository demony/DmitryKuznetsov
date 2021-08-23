package com.epam.tc.hw4.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginElements extends BaseElements {

    @FindBy(id = "user-name")
    private WebElement userNameLabel;

    @FindBy(id = "user-icon")
    private WebElement userIcon;

    @FindBy(css = "input#name")
    private WebElement userNameInput;

    @FindBy(css = "input#password")
    private WebElement userPasswordInput;

    @FindBy(css = "button#login-button")
    private WebElement loginButton;

    public LoginElements(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean login(String userLogin, String userPassword) {
        userIcon.click();
        userNameInput.sendKeys(userLogin);
        userPasswordInput.sendKeys(userPassword);
        loginButton.click();
        boolean loginSuccess = !loginButton.isDisplayed();
        return loginSuccess;
    }

    public boolean currentUserIsDisplayed() {
        return userNameLabel.isDisplayed();
    }

    public String currentUser() {
        return userNameLabel.getText();
    }

}
