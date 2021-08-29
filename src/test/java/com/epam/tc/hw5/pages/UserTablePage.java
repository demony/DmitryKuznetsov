package com.epam.tc.hw5.pages;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserTablePage extends AbstractBasePage {

    @FindBy(xpath = "//td/select")
    private List<WebElement> dropdowns;

    @FindBy(xpath = "//td/a")
    private List<WebElement> usernames;

    @FindBy(css = ".user-descr > span")
    private List<WebElement> descriptionTexts;

    @FindBy(css = "input[type='checkbox']")
    private List<WebElement> checkboxes;

    @FindBy(css = "td:first-child")
    private List<WebElement> rowNumbers;

    @FindBy(css = "#user-table tbody > tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//ul[@class='panel-body-list logs']/li")
    private List<WebElement> logElements;

    public UserTablePage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<WebElement> getDropdowns() {
        return dropdowns;
    }

    public List<WebElement> getUsernames() {
        return usernames;
    }

    public List<WebElement> getDescriptionTexts() {
        return descriptionTexts;
    }

    public List<WebElement> getCheckboxes() {
        return checkboxes;
    }

    public List<WebElement> getRowNumbers() {
        return rowNumbers;
    }

    public List<String> getElementsAsListOfStrings(List<WebElement> webElementLis) {
        return webElementLis.stream()
                            .map(el -> el.getText().replaceAll("\n", " "))
                            .collect(Collectors.toList());

    }

    public WebElement getTableRowByUserName(String userName) {
        List<WebElement> rowByUserName = tableRows
            .stream()
            .filter(el -> el.findElement(By.cssSelector("a")).getText().equals(userName))
            .collect(Collectors.toList());
        if (rowByUserName.size() == 0) {
            throw new RuntimeException("Can't find row with username = " + userName);
        }
        return rowByUserName.get(0);
    }

    public List<String> getDropDownOptionsByUserName(String userName) {
        return getTableRowByUserName(userName)
            .findElements(By.cssSelector("option"))
            .stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
    }

    public void selectVipCheckBoxByUserName(String userName) {
        getTableRowByUserName(userName).findElement(By.cssSelector("input[type='checkbox']")).click();
    }

    public List<WebElement> getLogElements() {
        return logElements;
    }

    public String getLogByIndex(Integer logIndex) {
        return logElements.get(logIndex - 1).getText();
    }

}
