package com.epam.tc.hw5.pages.components;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DifferentElementsComponent extends AbstractComponent {

    @FindBy(xpath = "//label[@class='label-checkbox']")
    private List<WebElement> checkboxes;

    @FindBy(xpath = "//label[@class='label-radio']")
    private List<WebElement> radioButtons;

    @FindBy(tagName = "select")
    private WebElement dropDownColors;

    @FindBy(css = ".logs>li")
    private List<WebElement> logElements;

    public DifferentElementsComponent(WebDriver webDriver) {
        super(webDriver);
    }

    public List<WebElement> getRadioButtons() {
        return radioButtons;
    }

    public WebElement getRadioButtonByText(String text) {
        return findSelectorByName(radioButtons, text);
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

    public WebElement dropdownColorsGetSelected() {
        Select dropdownSelectorColors = new Select(dropDownColors);
        return dropdownSelectorColors.getFirstSelectedOption();
    }

    public void clickOnCheckBox(String textInsideCheckbox) {
        findElementContainingText(checkboxes, textInsideCheckbox).click();
    }

    public List<WebElement> getCheckboxes() {
        return checkboxes;
    }

    public WebElement getCheckboxByText(String text) {
        return findSelectorByName(checkboxes, text);
    }

    public void clickOnRadioButton(String textInsideRadioButton) {
        findElementContainingText(radioButtons, textInsideRadioButton).click();
    }

    private WebElement findElementContainingText(List<WebElement> webElements, String text) {
        List<WebElement> elements = webElements.stream()
                                      .filter(el -> el.getText().contains(text))
                                      .collect(Collectors.toList());
        if (elements.size() == 1) {
            return elements.get(0);
        } else {
            throw new RuntimeException("Can't find element with text = " + text + " for click");
        }
    }

    public WebElement findSelectorByName(List<WebElement> webElements, String selectorName) {
        List<WebElement> elements = webElements.stream()
                                               .filter(el -> el.getText().contains(selectorName))
                                               .collect(Collectors.toList());
        if (elements.size() != 1) {
            throw new RuntimeException("Can't find selector with text = " + selectorName);
        }
        return elements.get(0).findElement(By.cssSelector("input"));
    }

    public List<String> getLogElementsTrimDates() {
        return logElements
            .stream()
            .filter(WebElement::isDisplayed)
            .map(el -> el.getText().substring(9))
            .collect(Collectors.toList());
    }
}
