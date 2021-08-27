package com.epam.tc.hw5.pages;

import com.epam.tc.hw5.pages.components.AbstractComponent;
import com.epam.tc.hw5.pages.components.LoginComponent;
import com.epam.tc.hw5.pages.components.MenuComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractBasePage extends AbstractComponent {

    private static final String BASE_URL = "https://jdi-testing.github.io";
    // /jdi-light/index.html

    protected LoginComponent loginComponent;
    protected MenuComponent menuComponent;

    protected AbstractBasePage(WebDriver webDriver) {
        super(webDriver);
        loginComponent = new LoginComponent(webDriver);
        menuComponent = new MenuComponent(webDriver);
    }

    protected void open(String url) {
        webDriver.navigate().to(BASE_URL + url);
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public void clickToUserIcon() {
        loginComponent.clickToUserIcon();
    }

    public void sendKeysToUserLoginField(String text) {
        loginComponent.sendKeysToUserLoginField(text);
    }

    public void sendKeysToUserPasswordField(String text) {
        loginComponent.sendKeysToUserPasswordField(text);
    }

    public void clickToLoginButton() {
        loginComponent.clickToLoginButton();
    }

    public void clickToHeaderMenuElement(String elementName) {
        menuComponent.clickToHeaderMenuElement(elementName);
    }

    public void clickToMenuElementInServiceDropDown(String elementName) {
        menuComponent.clickToMenuElementInServiceDropDown(elementName);
    }

}
