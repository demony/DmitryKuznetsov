package com.epam.tc.hw7.site.pages;

import com.epam.jdi.light.elements.complex.Checklist;
import com.epam.jdi.light.elements.complex.Selector;
import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.complex.dropdown.Dropdown;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.complex.MultiSelector;
import com.epam.jdi.light.ui.html.elements.complex.RadioButtons;

@Title("Metal and Colors")
public class MetalsAndColorsPage extends WebPage {

    @JDropdown(root = "div[ui=dropdown]", value = ".filter-option",
               list = "li", expand = ".caret")
    public Dropdown colors;

    @JDropdown(root = "div[ui=combobox]", value = "input",
               list = "li", expand = ".caret")
    public Dropdown metals;

    @JDropdown(root = "div[ui=droplist]",
            value = "button",
            list = "li",
            expand = ".caret")
    public Dropdown  vegetables;

    @FindBy(css = "#elements-checklist input")
    public Checklist elements;

    @FindBy(css = ".info-panel-section input")
    public RadioButtons summary;

    @FindBy(css = ".results li")
    public WebList results;

    @UI("['Submit']") public Button submit;
}
