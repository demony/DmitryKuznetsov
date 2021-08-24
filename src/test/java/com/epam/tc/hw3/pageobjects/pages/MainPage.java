package com.epam.tc.hw3.pageobjects.pages;

import com.epam.tc.hw3.pageobjects.elements.BenefitsElements;
import com.epam.tc.hw3.pageobjects.elements.HeaderMenuElements;
import com.epam.tc.hw3.pageobjects.elements.IframeElements;
import com.epam.tc.hw3.pageobjects.elements.LeftMenuElements;
import com.epam.tc.hw3.pageobjects.elements.LoginElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    private final LoginElements loginPageObjects;
    private final HeaderMenuElements headerMenuElements;
    private final BenefitsElements benefitsElements;
    private final IframeElements iframeElements;
    private final LeftMenuElements leftMenuElements;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
        loginPageObjects = new LoginElements(webDriver);
        headerMenuElements = new HeaderMenuElements(webDriver);
        benefitsElements = new BenefitsElements(webDriver);
        iframeElements = new IframeElements(webDriver);
        leftMenuElements = new LeftMenuElements(webDriver);
    }

    public LoginElements getLoginPageObjects() {
        return loginPageObjects;
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
