package com.epam.tc.hw7.site.sections;

import com.epam.jdi.light.elements.complex.Checklist;
import com.epam.jdi.light.elements.complex.dropdown.Dropdown;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.complex.RadioButtons;
import com.epam.tc.hw7.entities.MetalsColorsDataSet;

public class MetalsAndColorsForm extends Form<MetalsColorsDataSet> {

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

    @UI("['Submit']") public Button submit;
}
