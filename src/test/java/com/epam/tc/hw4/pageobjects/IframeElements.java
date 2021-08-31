package com.epam.tc.hw4.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IframeElements extends BaseElements {

    @FindBy(id = "frame")
    private List<WebElement> iframes;

    @FindBy(id = "frame-button")
    private List<WebElement> iframeButtons;

    public IframeElements(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean iframeExists() {
        if (iframes.size() > 0) {
            return true;
        }
        return false;
    }

    public void switchToIframe() {
        webDriver.switchTo().frame("frame");
    }

    public boolean frameButtonExists() {
        return iframeButtons.size() > 0;
    }

    public boolean switchToOriginalWindow() {
        webDriver.switchTo().defaultContent();
        boolean focusOnOriginalWindow = iframes.size() > 0;
        return focusOnOriginalWindow;
    }


}
