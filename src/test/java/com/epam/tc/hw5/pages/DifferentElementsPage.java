package com.epam.tc.hw5.pages;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DifferentElementsPage extends AbstractBasePage {

    public DifferentElementsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnCheckboxes(List<String> checkboxNames) {
        checkboxNames.stream().forEachOrdered(el -> differentElementsComponent.clickOnCheckBox(el));
    }

    public void clickOnRadioButton(String elementName) {
        differentElementsComponent.clickOnRadioButton(elementName);
    }

    public List<String> getSelectedCheckboxes() {
        return differentElementsComponent.getCheckboxes()
                                         .stream().filter(el -> el.findElement(By.cssSelector("input")).isSelected())
                                         .map(WebElement::getText)
                                         .collect(Collectors.toList());
    }

    public Boolean radioButtonIsChecked(String elementName) {
        return differentElementsComponent.getRadioButtonByText(elementName).isSelected();
    }

    public void clickOnDropdown(String elementName) {
        differentElementsComponent.dropDownColorsSelect(elementName);
    }

    public String dropdownColorGetSelected() {
        return differentElementsComponent.dropdownColorsGetSelected().getText();
    }

    public List<String> getLogMessages() {
        return differentElementsComponent.getLogElementsTrimDates();
    }
}
