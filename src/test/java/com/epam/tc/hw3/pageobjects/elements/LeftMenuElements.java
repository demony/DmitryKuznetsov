package com.epam.tc.hw3.pageobjects.elements;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeftMenuElements extends BaseElements {

    @FindBy(xpath = "//ul[@class='sidebar-menu left']/li/a/span")
    private List<WebElement> menuLeftSidebar;

    public LeftMenuElements(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> getLeftMenuSideBarElements() {
        List<String> leftMenuSideBarElements = new ArrayList<>();
        menuLeftSidebar.forEach(webElement -> leftMenuSideBarElements.add(webElement.getText()));
        return  leftMenuSideBarElements;
    }

    public List<String> getLeftMenuSideBarElementsVisible() {
        List<String> leftMenuSideBarElements = new ArrayList<>();
        for (WebElement webElement : menuLeftSidebar) {
            if (webElement.isDisplayed()) {
                leftMenuSideBarElements.add(webElement.getText());
            }
        }
        return  leftMenuSideBarElements;
    }


}
