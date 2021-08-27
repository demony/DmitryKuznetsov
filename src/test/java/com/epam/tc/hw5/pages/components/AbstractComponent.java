package com.epam.tc.hw5.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractComponent {
    protected WebDriver webDriver;
    protected WebDriverWait wait;
    protected Actions actions;

    protected AbstractComponent(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(this.webDriver, 10L);
        PageFactory.initElements(this.webDriver, this);
        actions = new Actions(this.webDriver);
    }
}
