package com.epam.tc.hw7.site;

import com.epam.jdi.light.elements.complex.JList;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.tc.hw7.site.custom.MenuItem;
import com.epam.tc.hw7.site.pages.HomePage;
import com.epam.tc.hw7.site.pages.MetalsAndColorsPage;
import com.epam.tc.hw7.site.sections.Header;
import com.epam.tc.hw7.site.sections.LoginForm;
import org.openqa.selenium.WebElement;

public class SiteJdi {

    public static HomePage homePage;
    public static MetalsAndColorsPage metalsColorsPage;

    @Css("form")
    public static LoginForm loginForm;

    @Css("header")
    public static Header header;

    @Css(".logout")
    public static WebElement logout;

    @UI(".sidebar-menu li")
    public static JList<MenuItem> menu;
}
