package com.epam.tc.hw3.pageobjects.elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DifferentElements extends BaseElements {


    @FindBy(xpath = "//label[@class='label-checkbox']")
    private List<WebElement> checkboxes;

    @FindBy(xpath = "//label[@class='label-radio']")
    private List<WebElement> radioButtons;

    @FindBy(xpath = "//div[@class='colors']/select")
    private WebElement dropDownColors;

    @FindBy(xpath = "//ul[@class='panel-body-list logs']/li[1]")
    private WebElement firstLogMessage;

    @FindBy(xpath = "//ul[@class='panel-body-list logs']/li")
    private List<WebElement> logElements;

    public DifferentElements(WebDriver webDriver) {
        super(webDriver);
    }

    public List<WebElement> getRadioButtons() {
        return radioButtons;
    }

    public WebElement getDropDownColors() {
        return dropDownColors;
    }

    public String dropDownColorsSelect(String selectorName) {
        Select dropdownSelectorColors = new Select(dropDownColors);
        dropdownSelectorColors.selectByVisibleText(selectorName);
        WebElement selectedColor = dropdownSelectorColors.getFirstSelectedOption();
        return selectedColor.getText();
    }

    public String getFirstLogMessageAsText() {
        return firstLogMessage.getText();
    }

    public boolean clickOnCheckBox(String textInsideCheckbox) {
        return findAndClickOnElementContainingText(checkboxes, textInsideCheckbox);
    }

    public Boolean clickOnRadioButton(String textInsideRadioButton) {
        return findAndClickOnElementContainingText(radioButtons, textInsideRadioButton);
    }

    private boolean findAndClickOnElementContainingText(List<WebElement> webElements, String text) {

        List<WebElement> elements = webElements.stream()
                                      .filter(el -> el.getText().contains(text))
                                      .collect(Collectors.toList());
        if (elements.size() == 1) {
            elements.get(0).click();
        } else {
            throw new RuntimeException("Can't find element with text = " + text);
        }
        return elements.get(0).findElement(By.cssSelector("input")).isSelected();
    }

    public List<String> getLogElementsTrimDates() {
        return logElements
            .stream()
            .filter(WebElement::isDisplayed)
            .map(el -> el.getText().substring(9))
            .collect(Collectors.toList());
    }
}


