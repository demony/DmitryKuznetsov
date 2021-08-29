package com.epam.tc.hw5.pages.components;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginComponent extends AbstractComponent {

    @FindBy(css = "img#user-icon")
    private WebElement userIcon;

    @FindBy(css = "input#name")
    private WebElement userLoginInput;

    @FindBy(css = "input#password")
    private WebElement userPasswordInput;

    @FindBy(css = "#user-name")
    private WebElement userNameLabel;

    @FindBy(css = "button#login-button")
    private WebElement loginButton;

    public LoginComponent(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickToUserIcon() {
        userIcon.click();
        // wait.until(visibilityOf(userIcon)).click();
    }

    public void sendKeysToUserPasswordField(String text) {
        userPasswordInput.sendKeys(text);
        // wait.until(visibilityOf(userPasswordInput)).sendKeys(text);
    }

    public void sendKeysToUserLoginField(String text) {
        userLoginInput.sendKeys(text);
        // wait.until(visibilityOf(userLoginInput)).sendKeys(text);
    }

    public void clickToLoginButton() {
        wait.until(visibilityOf(loginButton)).click();
    }

    public Boolean loginButtonIsDisplayed() {
        return loginButton.isDisplayed();
    }

}
