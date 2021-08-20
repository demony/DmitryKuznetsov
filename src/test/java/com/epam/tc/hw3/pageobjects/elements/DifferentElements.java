package com.epam.tc.hw3.pageobjects.elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DifferentElements extends BaseElements {

    @FindBy(xpath = "//label[@class='label-checkbox' and contains(., 'Water')]/input")
    private WebElement checkboxWater;

    @FindBy(xpath = "//label[@class='label-checkbox' and contains(., 'Earth')]/input")
    private WebElement checkboxEarth;

    @FindBy(xpath = "//label[@class='label-checkbox' and contains(., 'Wind')]/input")
    private WebElement checkboxWind;

    @FindBy(xpath = "//label[@class='label-checkbox' and contains(., 'Fire')]/input")
    private WebElement checkboxFire;

    @FindBy(css = "label[class='label-radio']")
    private List<WebElement> radioButtons;

    @FindBy(xpath = "//label[@class='label-radio' and contains(., 'Gold')]/input")
    private WebElement radioButtonGold;

    @FindBy(xpath = "//label[@class='label-radio' and contains(., 'Silver')]/input")
    private WebElement radioButtonSilver;

    @FindBy(xpath = "//label[@class='label-radio' and contains(., 'Bronze')]/input")
    private WebElement radioButtonBronze;

    @FindBy(xpath = "//label[@class='label-radio' and contains(., 'Selen')]/input")
    private WebElement radioButtonSelen;

    @FindBy(xpath = "//div[@class='colors']/select")
    private WebElement dropDownColors;

    @FindBy(xpath = "//ul[@class='panel-body-list logs']/li[1]")
    private WebElement firstLogMessage;

    public DifferentElements(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnCheckboxWater() {
        checkboxWater.click();
    }

    public void clickOnCheckboxEarth() {
        checkboxEarth.click();
    }

    public void clickOnCheckboxWind() {
        checkboxWind.click();
    }

    public void clickOnCheckboxFire() {
        checkboxFire.click();
    }

    public boolean checkboxWaterIsSelected() {
        return checkboxWater.isSelected();
    }

    public boolean checkboxEarthIsSelected() {
        return checkboxEarth.isSelected();
    }

    public boolean checkboxWindIsSelected() {
        return checkboxWind.isSelected();
    }

    public boolean checkboxFireIsSelected() {
        return checkboxFire.isSelected();
    }

    public List<WebElement> getRadioButtons() {
        return radioButtons;
    }

    public WebElement getRadioButtonGold() {
        return radioButtonGold;
    }

    public WebElement getRadioButtonSilver() {
        return radioButtonSilver;
    }

    public WebElement getRadioButtonBronze() {
        return radioButtonBronze;
    }

    public WebElement getRadioButtonSelen() {
        return radioButtonSelen;
    }

    public boolean getRadioButtonSelenClick() {
        radioButtonSelen.click();
        return radioButtonSelen.isSelected();
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

    public boolean clickCheckBox(String checkboxName) {
        WebElement checkBox;
        switch (checkboxName) {
            case "Water":
                checkBox = checkboxWater;
                break;
            case "Earth":
                checkBox = checkboxEarth;
                break;
            case "Wind":
                checkBox = checkboxWind;
                break;
            case "Fire":
                checkBox = checkboxFire;
                break;
            default:
                throw new IllegalArgumentException("Invalid checkbox name " + checkboxName);
        }
        checkBox.click();
        return checkBox.isSelected();
    }

    public Map<Boolean, String> doubleClickOnCheckBoxAndCollectStatusAndLogs(String checkBoxName) {
        Map<Boolean, String> checkboxLogValues = new HashMap<>();
        checkboxLogValues.put(clickCheckBox(checkBoxName), getFirstLogMessageAsText());
        checkboxLogValues.put(clickCheckBox(checkBoxName), getFirstLogMessageAsText());

        return checkboxLogValues;
    }

    public WebElement getRadioButtonByName(String radioButtonName) {
        WebElement radioButton;
        switch (radioButtonName) {
            case "Gold":
                radioButton = radioButtonGold;
                break;
            case "Silver":
                radioButton = radioButtonSilver;
                break;
            case "Bronze":
                radioButton = radioButtonBronze;
                break;
            case "Selen":
                radioButton = radioButtonSelen;
                break;
            default:
                throw new IllegalArgumentException("Invalid checkbox name " + radioButtonName);
        }
        return radioButton;
    }

    public boolean radioButtonClick(String radioButtonName) {
        WebElement radioButton = getRadioButtonByName(radioButtonName);
        radioButton.click();
        return radioButton.isSelected();
    }
}


