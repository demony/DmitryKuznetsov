package com.epam.tc.hw3.pageobjects.elements;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderMenuElements extends BaseElements {


    @FindBy(xpath = "//header//ul[@class='uui-navigation nav navbar-nav m-l8']/li/a")
    private List<WebElement> menuElements;

    @FindBy(xpath = "//header//..//a[contains(text(), 'Service')]")
    private WebElement menuService;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li/a[contains(text(), 'Different elements')]")
    WebElement submenuDifferentElements;

    public HeaderMenuElements(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> getHeaderMenuElementsAll() {
        List<String> menuElementsAsStrings = new ArrayList<>();
        menuElements.forEach(webElement -> menuElementsAsStrings.add(webElement.getText()));
        return  menuElementsAsStrings;
    }

    public List<String> getHeaderMenuElementsThatIsVisible() {

        List<String> menuElementsAsStrings = new ArrayList<>();
        for (WebElement webElement : menuElements) {
            if (webElement.isDisplayed()) {
                menuElementsAsStrings.add(webElement.getText());
            }
        }
        return  menuElementsAsStrings;
    }

    public void openPageDifferentElements() {
        menuService.click();
        submenuDifferentElements.click();
    }

}
