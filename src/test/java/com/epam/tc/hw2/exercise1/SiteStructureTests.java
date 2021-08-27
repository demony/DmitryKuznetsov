package com.epam.tc.hw2.exercise1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.epam.tc.hw2.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SiteStructureTests extends BaseTest {

    @Test
    public void siteStructure_isCorrect() {

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

        // 5. Assert that there are 4 items on the header section are displayed, and they have proper texts
        List<String> menuElementsExpected = Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");

        List<WebElement> menuElements = webDriver.findElements(By.cssSelector("ul.m-l8 > li > a"));
        softAssertions.assertThat(menuElements.size())
                      .as("The menu in header section should have 4 top elements")
                      .isEqualTo(menuElementsExpected.size());


        List<String> menuElementsActual = menuElements.stream().map(WebElement::getText).collect(Collectors.toList());
        softAssertions.assertThat(menuElementsActual)
                      .as("Menu elements should have proper text")
                      .isEqualTo(menuElementsExpected);

        List<String> menuElementsVisibleActual = menuElements
            .stream().filter(WebElement::isEnabled).map(WebElement::getText).collect(Collectors.toList());
        softAssertions.assertThat(menuElementsVisibleActual)
                      .as("Menu elements should be visible")
                      .isEqualTo(menuElementsExpected);

        // 6. Assert that there are 4 images on the Index Page, and they are displayed
        List<WebElement> imagesOnIndexPage =
            webDriver.findElements(By.xpath("//span[contains(@class,'icons-benefit')]"));
        softAssertions. assertThat(imagesOnIndexPage)
                      .as(" There are should be 4 images on the Index Page").hasSize(4);

        softAssertions.assertThat(imagesOnIndexPage)
                      .as("All images on index page should be visible")
                      .allMatch(WebElement::isDisplayed);

        // 7. Assert that there are 4 texts on the Index Page under icons, and they have proper text
        List<WebElement> benefitsTxt = webDriver.findElements(By.cssSelector("span.benefit-txt"));
        softAssertions.assertThat(benefitsTxt)
                      .as("There are should be 4 texts on the Index Page under icons and visible")
                      .hasSize(4).allMatch(WebElement::isDisplayed);

        Map<String, String> mapImagesClassesToTextExpected = new HashMap<>();
        mapImagesClassesToTextExpected.put("icons-benefit icon-practise",
            "To include good practices\nand ideas from successful\nEPAM project");
        mapImagesClassesToTextExpected.put("icons-benefit icon-custom",
            "To be flexible and\ncustomizable");
        mapImagesClassesToTextExpected.put("icons-benefit icon-multi",
            "To be multiplatform");
        mapImagesClassesToTextExpected.put("icons-benefit icon-base",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");

        List<WebElement> benefits = webDriver.findElements(By.cssSelector("div.benefit"));
        Map<String, String> mapImagesClassesToText = new HashMap<>();
        for (WebElement benefit : benefits) {
            String iconClassName = benefit.findElement(By.cssSelector("span.icons-benefit")).getAttribute("class");
            String iconText = benefit.findElement(By.cssSelector("span.benefit-txt")).getText();
            mapImagesClassesToText.put(iconClassName, iconText);
        }
        softAssertions.assertThat(mapImagesClassesToText)
                      .as("Texts under icons equal to expected")
                      .isEqualTo(mapImagesClassesToTextExpected);

        // 8. Assert that there is the iframe with “Frame Button” exist
        List<WebElement> iframes = webDriver.findElements(By.id("frame"));
        softAssertions.assertThat(iframes)
                      .as("The frame with 'Frame Button' should exists").isNotEmpty();

        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        WebElement iframe = webDriver.findElement(By.id("frame"));
        webDriver.switchTo().frame(iframe);
        List<WebElement> frameButtons = webDriver.findElements(By.xpath(
            "//input[@id='frame-button' and @type='button' and @value='Frame Button']"));
        softAssertions.assertThat(frameButtons)
                      .as("The 'Frame Button' should exists").isNotEmpty();

        // 10. Switch to original window back
        webDriver.switchTo().defaultContent();
        actualURL = webDriver.getCurrentUrl();
        softAssertions.assertThat(actualURL)
                      .as("Driver has focus on the original window")
                      .isEqualTo(siteURL);

        // 11. Assert that there are 5 items in the Left Section are displayed, and they have proper text
        List<WebElement> leftMenuElements = webDriver.findElements(
            By.xpath("//ul[@class='sidebar-menu left']/li/a/span"));

        softAssertions.assertThat(leftMenuElements)
                      .as("The left menu should contain 5 elements and they are visible")
                      .hasSize(5)
                      .allMatch(WebElement::isDisplayed);

        List<String> menuElementsLeftSideBarExpected = new ArrayList<>();
        menuElementsLeftSideBarExpected.add("Home");
        menuElementsLeftSideBarExpected.add("Contact form");
        menuElementsLeftSideBarExpected.add("Service");
        menuElementsLeftSideBarExpected.add("Metals & Colors");
        menuElementsLeftSideBarExpected.add("Elements packs");

        List<String> leftMenuSideBarElements = leftMenuElements
            .stream().map(WebElement::getText).collect(Collectors.toList());

        softAssertions.assertThat(leftMenuSideBarElements)
                      .as("The left menu should contain "
                          + menuElementsLeftSideBarExpected.size() + " elements")
                      .hasSize(menuElementsLeftSideBarExpected.size());

        softAssertions.assertThat(leftMenuSideBarElements)
                      .as("Left menu should contain correct item names")
                      .hasSameElementsAs(menuElementsLeftSideBarExpected);

        // 12. Close Browser
        // No more needed

        softAssertions.assertAll();
    }
}
