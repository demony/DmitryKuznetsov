package com.epam.tc.hw4.pageobjects;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseElements {

    WebDriver webDriver;

    public BaseElements(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public String openURL(String siteURL) {
        webDriver.navigate().to(siteURL);
        String actualURL = webDriver.getCurrentUrl();
        return actualURL;
    }

    public String getTitle() {
        String title = webDriver.getTitle();
        return title;
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public boolean closeBrowser() {
        webDriver.close();
        try {
            webDriver.getTitle();
        } catch (NoSuchSessionException e) {
            return true;
        }
        return false;
    }
}
