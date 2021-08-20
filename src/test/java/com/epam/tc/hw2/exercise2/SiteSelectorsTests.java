package com.epam.tc.hw2.exercise2;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SiteSelectorsTests {
    WebDriver webDriver;

    @BeforeClass
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

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
                      .isEqualTo(false);

        //  4. Assert Username is logged in
        WebElement userNameLabel = webDriver.findElement(By.id("user-name"));
        softAssertions.assertThat(userNameLabel.isDisplayed())
                      .as("Name is displayed")
                      .isEqualTo(true);
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

        final String xpathFirstLogElement = "//ul[@class='panel-body-list logs']/li[1]";
        // test checkboxes

        // water checkbox
        if (checkboxWater.isSelected()) {
            checkboxWater.click();
        }
        checkboxWater.click();
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message for checked Water checkbox")
                      .contains("Water: condition changed to true");
        checkboxWater.click();
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message for unchecked Water checkbox")
                      .contains("Water: condition changed to false");

        // Earth checkbox
        WebElement checkboxEarth = webDriver.findElement(
            By.xpath("//label[@class='label-checkbox' and contains(., 'Earth')]/child::input[@type='checkbox']"));
        checkboxWind.click();

        if (checkboxEarth.isSelected()) {
            checkboxEarth.click();
        }
        checkboxEarth.click();
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message for checked Earth checkbox")
                      .contains("Earth: condition changed to true");
        checkboxEarth.click();
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message for unchecked Earth checkbox")
                      .contains("Earth: condition changed to false");

        // wind checkbox
        if (checkboxWind.isSelected()) {
            checkboxWind.click();
        }
        checkboxWind.click();
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message for checked Wind checkbox")
                      .contains("Wind: condition changed to true");
        checkboxWind.click();
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message for unchecked Wind checkbox")
                      .contains("Wind: condition changed to false");


        // fire checkbox
        WebElement checkboxFire = webDriver.findElement(
            By.xpath("//label[@class='label-checkbox' and contains(., 'Fire')]/child::input[@type='checkbox']"));
        checkboxFire.click();

        if (checkboxFire.isSelected()) {
            checkboxFire.click();
        }
        checkboxFire.click();
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message for checked Fire checkbox")
                      .contains("Fire: condition changed to true");
        checkboxFire.click();
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message for unchecked Fire checkbox")
                      .contains("Fire: condition chnged to false");

        // test selector
        WebElement radioBtnGold = webDriver.findElement(
            By.xpath("//label[@class='label-radio' and contains(., 'Gold')]/child::input[@type='radio']"));
        radioBtnGold.click();
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message when selected Gold selector")
                      .contains("metal: value changed to Gold");

        WebElement radioBtnSilver = webDriver.findElement(
            By.xpath("//label[@class='label-radio' and contains(., 'Silver')]/child::input[@type='radio']"));
        radioBtnSilver.click();
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message when selected Silver selector")
                      .contains("metal: value changed to Silver");

        WebElement radioBtnBronze = webDriver.findElement(
            By.xpath("//label[@class='label-radio' and contains(., 'Bronze')]/child::input[@type='radio']"));
        radioBtnBronze.click();
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message when selected Bronze selector")
                      .contains("metal: value changed to Bronze");

        radioBtnSelen.click();
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message when selected Selen selector")
                      .contains("metal: value changed to Selen");

        // test dropdown

        // change current selection to prevent intersection with first check
        dropdownSelectorColors.selectByVisibleText("Yellow");

        dropdownSelectorColors.selectByVisibleText("Red");
        selectedColor = dropdownSelectorColors.getFirstSelectedOption();
        softAssertions.assertThat(selectedColor.getText())
                      .as("Selected color is Red")
                      .isEqualTo("Red");
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message when selected Red in dropdown menu 'colors'")
                      .contains("Colors: value changed to Red");

        dropdownSelectorColors.selectByVisibleText("Green");
        selectedColor = dropdownSelectorColors.getFirstSelectedOption();
        softAssertions.assertThat(selectedColor.getText())
                      .as("Selected color is Green")
                      .isEqualTo("Green");
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message when selected Green in dropdown menu 'colors'")
                      .contains("Colors: value changed to Green");

        dropdownSelectorColors.selectByVisibleText("Blue");
        selectedColor = dropdownSelectorColors.getFirstSelectedOption();
        softAssertions.assertThat(selectedColor.getText())
                      .as("Selected color is Blue")
                      .isEqualTo("Blue");
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message when selected Blue in dropdown menu 'colors'")
                      .contains("Colors: value changed to Blue");

        dropdownSelectorColors.selectByVisibleText("Yellow");
        selectedColor = dropdownSelectorColors.getFirstSelectedOption();
        softAssertions.assertThat(selectedColor.getText())
                      .as("Selected color is Yellow")
                      .isEqualTo("Yellow");
        softAssertions.assertThat(
                          webDriver.findElement(By.xpath(xpathFirstLogElement)).getText())
                      .as("Correct log message when selected Yellow in dropdown menu 'colors'")
                      .contains("Colors: value changed to Yellow");

        // 10. Close Browser
        webDriver.close();
        assertThatThrownBy(() -> webDriver.getTitle()).as("Browser is closed")
                                                      .isInstanceOf(NoSuchSessionException.class);

        softAssertions.assertAll();
    }

}
