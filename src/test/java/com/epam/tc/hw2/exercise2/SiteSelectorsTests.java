package com.epam.tc.hw2.exercise2;

import com.epam.tc.hw2.BaseTest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SiteSelectorsTests extends BaseTest {

    @Test
    public void siteSelectors_workCorrectly() {

        SoftAssertions softAssertions = new SoftAssertions();

        // 1. Open test site by URL
        String siteURL = "https://jdi-testing.github.io/jdi-light/index.html";
        webDriver.navigate().to(siteURL);
        String actualURL = webDriver.getCurrentUrl();
        softAssertions.assertThat(actualURL)
                      .as("Test site is opened")
                      .isEqualTo(siteURL);

        // 2. Assert Browser title
        String title = webDriver.getTitle();
        softAssertions.assertThat(title)
                      .as("Browser title equals 'Home Page'")
                      .isEqualTo("Home Page");

        // 3. Perform login
        WebElement userIcon = webDriver.findElement(By.id("user-icon"));
        userIcon.click();

        WebElement userNameInput = webDriver.findElement(By.cssSelector("input#name"));
        WebElement userPasswordInput = webDriver.findElement(By.cssSelector("input#password"));
        WebElement loginButton = webDriver.findElement(By.cssSelector("button#login-button"));
        userNameInput.sendKeys("Roman");
        userPasswordInput.sendKeys("Jdi1234");
        loginButton.click();
        softAssertions.assertThat(loginButton.isDisplayed())
                      .as("User is logged")
                      .isFalse();

        //  4. Assert Username is logged in
        WebElement userNameLabel = webDriver.findElement(By.id("user-name"));
        softAssertions.assertThat(userNameLabel.isDisplayed())
                      .as("Name is displayed")
                      .isTrue();
        softAssertions.assertThat(userNameLabel.getText())
                      .as("Name is equals to expected result")
                      .isEqualTo("ROMAN IOVLEV");

        // 5.Open through the header menu Service -> Different Elements Page
        WebElement menuService = webDriver
            .findElement(By.xpath("//header/.//a[@class='dropdown-toggle' and  @data-toggle='dropdown']"));
        menuService.click();

        WebElement submenuDifferentElements = webDriver.findElement(
            By.xpath("//ul[@class='dropdown-menu']/li/a[contains(text(), 'Different elements')]"));
        submenuDifferentElements.click();

        String urlDifferentElements = "https://jdi-testing.github.io/jdi-light/different-elements.html";
        actualURL = webDriver.getCurrentUrl();
        softAssertions.assertThat(actualURL)
                      .as("Different Elements Page is opened")
                      .isEqualTo(urlDifferentElements);

        // 6. Select checkboxes - Water, Wind
        WebElement checkboxWater = webDriver.findElement(
            By.xpath("//label[@class='label-checkbox' and contains(., 'Water')]/child::input[@type='checkbox']"));
        checkboxWater.click();
        softAssertions.assertThat(checkboxWater.isSelected()).as("Checkbox Water is checked").isTrue();

        WebElement checkboxWind = webDriver.findElement(
            By.xpath("//label[@class='label-checkbox' and contains(., 'Wind')]/child::input[@type='checkbox']"));
        checkboxWind.click();
        softAssertions.assertThat(checkboxWind.isSelected()).as("Checkbox Wind is checked").isTrue();

        // 7. Select radio - Selen

        WebElement radioBtnSelen = webDriver.findElement(
            By.xpath("//label[@class='label-radio' and contains(., 'Selen')]/child::input[@type='radio']"));
        radioBtnSelen.click();
        softAssertions.assertThat(radioBtnSelen.isSelected()).as("Radio button Selen is selected").isTrue();

        // 8. Select in dropdown - Yellow
        WebElement dropDownColors = webDriver.findElement(
            By.xpath("//div[@class='colors']/select"));
        Select dropdownSelectorColors = new Select(dropDownColors);
        dropdownSelectorColors.selectByVisibleText("Yellow");
        WebElement selectedColor = dropdownSelectorColors.getFirstSelectedOption();
        softAssertions.assertThat(selectedColor.getText())
                      .as("Selected color is Yellow")
                      .isEqualTo("Yellow");


        // 9. Assert that
        //    • for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        //    • for radio button there is a log row and value is corresponded to the status of radio button
        //    • for dropdown there is a log row and value is corresponded to the selected value.

        List<WebElement> logWebElements = webDriver.findElements(By.xpath("//ul[@class='panel-body-list logs']/li"));
        List<String> logElementsAsList = logWebElements
            .stream()
            .filter(WebElement::isEnabled)
            .map(el -> el.getText().substring(9))
            .collect(Collectors.toList());

        List<String> logElementsExpected = Arrays.asList(
            "Colors: value changed to Yellow",
            "metal: value changed to Selen",
            "Wind: condition changed to true",
            "Water: condition changed to true");

        softAssertions.assertThat(logElementsAsList)
                      .as("Logs rows are displayed and correct")
                      .isEqualTo(logElementsExpected);

        // 10. Close Browser
        // No more needed

        softAssertions.assertAll();
    }

}
