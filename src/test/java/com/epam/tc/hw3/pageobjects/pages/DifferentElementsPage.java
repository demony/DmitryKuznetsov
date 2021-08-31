package com.epam.tc.hw3.pageobjects.pages;

import com.epam.tc.hw3.pageobjects.elements.DifferentElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

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
