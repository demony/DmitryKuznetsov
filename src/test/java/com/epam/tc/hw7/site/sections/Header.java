package com.epam.tc.hw7.site.sections;

import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;
import com.epam.jdi.light.ui.html.elements.common.Icon;
import com.epam.jdi.light.ui.html.elements.common.Text;

public class Header extends Section {
    @Css("form") public LoginForm loginForm;
    @Css("img#user-icon") public Icon userIcon;
    @Css("#user-name") public Text userName;
}
