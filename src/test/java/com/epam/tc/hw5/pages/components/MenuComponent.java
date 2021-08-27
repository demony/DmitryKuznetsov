package com.epam.tc.hw5.pages.components;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuComponent extends AbstractComponent {

    @FindBy(css = ".uui-navigation > li > a")
    private List<WebElement> headerMenu;

    @FindBy(css = ".dropdown-menu > li > a")
    private List<WebElement> serviceMenu;

    @FindBy(xpath = "//header//a[contains(., 'User Table ')]")
    private WebElement menuUserTable;

    public MenuComponent(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickToHeaderMenuElement(String elementName) {
        findMenuElementByNameAndClickOnIt(headerMenu, elementName);
    }

    public void clickToMenuElementInServiceDropDown(String elementName) {
        findMenuElementByNameAndClickOnIt(serviceMenu, elementName);
    }

    private void findMenuElementByNameAndClickOnIt(List<WebElement> webElementList, String elementName) {
        List<WebElement> resultList = webElementList.stream()
                  .filter(el -> el.getText().compareToIgnoreCase(elementName) == 0).collect(Collectors.toList());

        if (resultList.isEmpty()) {
            throw new RuntimeException("Can't find menu element " + elementName);
        }

        resultList.get(0).click();
    }

}
