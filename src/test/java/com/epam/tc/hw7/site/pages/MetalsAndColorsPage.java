package com.epam.tc.hw7.site.pages;

import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.tc.hw7.site.sections.MetalsAndColorsForm;

@Title("Metal and Colors")
public class MetalsAndColorsPage extends WebPage {

    @UI(".form") public static MetalsAndColorsForm metalsAndColorsForm;

    @FindBy(css = ".results li")
    public WebList results;
}
