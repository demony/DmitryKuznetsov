package com.epam.tc.hw4.pages;

import com.epam.tc.hw4.pageobjects.DifferentElements;
import org.openqa.selenium.WebDriver;

public class DifferentElementsPage extends BasePage {

    private final DifferentElements differentElements;

    public DifferentElementsPage(WebDriver webDriver) {
        super(webDriver);
        differentElements = new DifferentElements(webDriver);
    }

    public DifferentElements getDifferentElements() {
        return differentElements;
    }
}
