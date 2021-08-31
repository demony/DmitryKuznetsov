package com.epam.tc.hw5.pages.components;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuComponent extends AbstractComponent {

    @FindBy(css = ".uui-navigation > li > a")
    private List<WebElement> headerMenu;

    @FindBy(css = "ul[role='menu'] a")
    private List<WebElement> serviceMenu;

    public MenuComponent(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickToHeaderMenuElement(String elementName) {
        findMenuElementByName(headerMenu, elementName).click();
    }

    public void clickToMenuElementInServiceDropDown(String elementName) {
        findMenuElementByName(serviceMenu, elementName).click();
    }

    private WebElement findMenuElementByName(List<WebElement> webElementList, String elementName) {
        List<WebElement> resultList = webElementList.stream()
                  .filter(el -> el.getText().compareToIgnoreCase(elementName) == 0).collect(Collectors.toList());

        if (resultList.isEmpty()) {
            throw new RuntimeException("Can't find menu element " + elementName);
        }
        return resultList.get(0);
    }
}
