package com.epam.tc.hw4.pages;

import com.epam.tc.hw4.pageobjects.BenefitsElements;
import com.epam.tc.hw4.pageobjects.HeaderMenuElements;
import com.epam.tc.hw4.pageobjects.IframeElements;
import com.epam.tc.hw4.pageobjects.LeftMenuElements;
import com.epam.tc.hw4.pageobjects.LoginElements;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    private final LoginElements loginElements;
    private final HeaderMenuElements headerMenuElements;
    private final BenefitsElements benefitsElements;
    private final IframeElements iframeElements;
    private final LeftMenuElements leftMenuElements;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
        loginElements = new LoginElements(webDriver);
        headerMenuElements = new HeaderMenuElements(webDriver);
        benefitsElements = new BenefitsElements(webDriver);
        iframeElements = new IframeElements(webDriver);
        leftMenuElements = new LeftMenuElements(webDriver);
    }

    public LoginElements getLoginElements() {
        return loginElements;
    }

    public HeaderMenuElements getHeaderMenuElements() {
        return headerMenuElements;
    }

    public BenefitsElements getBenefitsElements() {
        return benefitsElements;
    }

    public IframeElements getIframeElements() {
        return iframeElements;
    }

    public LeftMenuElements getLeftMenuElements() {
        return leftMenuElements;
    }
}
