package com.epam.tc.hw5.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractBasePage {

    public static final String INDEX_PAGE = "/jdi-light/index.html";

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void open() {
        open(INDEX_PAGE);
    }
}
