package com.epam.tc.hw4.pageobjects;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseElements {

    public WebDriver webDriver;

    public BaseElements(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }
}
