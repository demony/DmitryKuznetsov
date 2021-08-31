package com.epam.tc.hw6.pages;

import com.epam.tc.hw6.pageobjects.BaseElements;
import org.openqa.selenium.WebDriver;

public class BasePage extends BaseElements {

    protected BasePage(WebDriver webDriver) {
        super(webDriver);
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
}
