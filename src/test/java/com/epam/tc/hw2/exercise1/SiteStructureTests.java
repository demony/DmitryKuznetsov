package com.epam.tc.hw2.exercise1;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SiteStructureTests {
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
                      .isEqualTo(false);

        //  4. Assert Username is logged in
        WebElement userNameLabel = webDriver.findElement(By.id("user-name"));
        softAssertions.assertThat(userNameLabel.isDisplayed())
                      .as("Name is displayed")
                      .isEqualTo(true);
        softAssertions.assertThat(userNameLabel.getText())
                      .as("Name is equals to expected result")
                      .isEqualTo("ROMAN IOVLEV");

        // 5. Assert that there are 4 items on the header section are displayed, and they have proper texts
        List<WebElement> menuElements = webDriver.findElements(
            By.xpath("//header//ul[@class='uui-navigation nav navbar-nav m-l8']/li"));
        softAssertions.assertThat(menuElements.size())
                      .as("The menu in header section should have 4 top elements")
                      .isEqualTo(4);

        WebElement menuHome = webDriver.findElement(By.xpath("//header/.//a[contains(text(), 'Home')]"));
        softAssertions.assertThat(menuHome.getText())
                      .as("The menu element 'home' is displayed as 'HOME'")
                      .isEqualTo("HOME");
        softAssertions.assertThat(menuHome.isDisplayed())
                      .as("The menu element 'HOME' should be visible").isTrue();

        WebElement menuContactForm = webDriver.findElement(By.xpath("//header/.//a[contains(text(), 'Contact form')]"));
        softAssertions.assertThat(menuContactForm.getText())
                      .as("The menu element 'Contact form' is displayed as 'CONTACT FORM'")
                      .isEqualTo("CONTACT FORM");
        softAssertions.assertThat(menuContactForm.isDisplayed())
                      .as("The menu element 'CONTACT FORM' should be visible").isTrue();

        WebElement menuService = webDriver
            .findElement(By.xpath("//header/.//a[@class='dropdown-toggle' and  @data-toggle='dropdown']"));
        softAssertions.assertThat(menuService.getText())
                      .as("The menu element 'Service' is displayed as 'SERVICE'")
                      .isEqualTo("SERVICE");
        softAssertions.assertThat(menuService.isDisplayed())
                      .as("The menu element 'SERVICE' should be visible").isTrue();

        WebElement menuMetalsAndColors = webDriver
            .findElement(By.xpath("//header/.//a[contains(text(), 'Metals & Colors')]"));
        softAssertions.assertThat(menuMetalsAndColors.getText())
                      .as("The menu element 'Metals & Colors' is displayed as 'METALS & COLORS'")
                      .isEqualTo("METALS & COLORS");
        softAssertions.assertThat(menuMetalsAndColors.isDisplayed())
                      .as("The menu element 'METALS & COLORS' should be visible").isTrue();

        // 6. Assert that there are 4 images on the Index Page, and they are displayed
        List<WebElement> imagesOnIndexPage =
            webDriver.findElements(By.xpath("//span[contains(@class,'icons-benefit')]"));
        softAssertions.assertThat(imagesOnIndexPage.size())
                      .as(" There are should be 4 images on the Index Page")
                      .isEqualTo(4);

        for (WebElement imageElement : imagesOnIndexPage) {
            softAssertions.assertThat(imageElement.isDisplayed())
                          .as("The image element with class = '"
                              + imageElement.getAttribute("class")
                              + "' should be visible").isTrue();
        }

        // 7. Assert that there are 4 texts on the Index Page under icons, and they have proper text
        List<WebElement> benefitsTxt = webDriver.findElements(By.xpath("//span[contains(@class,'benefit-txt')]"));
        softAssertions.assertThat(benefitsTxt.size())
                      .as(" There are should be 4 texts on the Index Page under icons")
                      .isEqualTo(4);

        WebElement textUnderPractice = webDriver
            .findElement(By.xpath(
                "//span[@class ='icons-benefit icon-practise']//../..//preceding-sibling::span[@class='benefit-txt']"));

        softAssertions.assertThat(textUnderPractice.getText())
                      .as("The text under element should have proper value:")
                      .isEqualTo("To include good practices\nand ideas from successful\nEPAM project");

        WebElement textUnderCustom = webDriver
            .findElement(By.xpath(
                "//span[@class ='icons-benefit icon-custom']//../..//preceding-sibling::span[@class='benefit-txt']"));

        softAssertions.assertThat(textUnderCustom.getText())
                      .as("The text under element should have proper value:")
                      .isEqualTo("To be flexible and\ncustomizable");

        WebElement textUnderMulti = webDriver
            .findElement(By.xpath(
                "//span[@class ='icons-benefit icon-multi']//../..//preceding-sibling::span[@class='benefit-txt']"));

        softAssertions.assertThat(textUnderMulti.getText())
                      .as("The text under element should have proper value:").isEqualTo("To be multiplatform");

        WebElement textUnderBase = webDriver
            .findElement(By.xpath(
                "//span[@class ='icons-benefit icon-base']//../..//preceding-sibling::span[@class='benefit-txt']"));

        softAssertions.assertThat(textUnderBase.getText())
                      .as("The text under element should have proper value:")
                      .isEqualTo("Already have good base\n(about 20 internal and"
                          + "\nsome external projects),\nwish to get more…");

        // 8. Assert that there is the iframe with “Frame Button” exist
        boolean iframeFrameExists = webDriver.findElements(By.id("frame")).size() != 0;
        softAssertions.assertThat(iframeFrameExists)
                      .as("The frame with 'Frame Button' should exists").isTrue();

        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        WebElement iframe = webDriver.findElement(By.id("frame"));
        webDriver.switchTo().frame(iframe);
        boolean frameButtonExists = webDriver.findElements(By.xpath(
            "//input[@id='frame-button' and @type='button' and @value='Frame Button']")).size() != 0;
        softAssertions.assertThat(frameButtonExists)
                      .as("The 'Frame Button' should exists").isTrue();

        // 10. Switch to original window back
        webDriver.switchTo().defaultContent();
        actualURL = webDriver.getCurrentUrl();
        softAssertions.assertThat(actualURL)
                      .as("Driver has focus on the original window")
                      .isEqualTo(siteURL);

        // 11. Assert that there are 5 items in the Left Section are displayed, and they have proper text
        List<WebElement> leftMenuElements = webDriver.findElements(
            By.xpath("//ul[@class='sidebar-menu left']/li/a/span"));
        softAssertions.assertThat(leftMenuElements.size())
                      .as("The left menu should contain 5 elements").isEqualTo(5);

        for (WebElement menuItem : leftMenuElements) {
            softAssertions.assertThat(menuItem.isDisplayed())
                          .as("The menu element with  = '"
                              + menuItem.getText()
                              + "' should be visible").isTrue();
        }

        WebElement leftMenuHome = webDriver.findElement(
            By.xpath("//ul[@class='sidebar-menu left']//a/span[contains(text(), 'Home')]"));
        softAssertions.assertThat(leftMenuHome.getText())
                      .as("The menu element 'Home' is displayed as 'Home'")
                      .isEqualTo("Home");

        WebElement leftMContactForm = webDriver.findElement(
            By.xpath("//ul[@class='sidebar-menu left']//a/span[contains(text(), 'Contact form')]"));
        softAssertions.assertThat(leftMContactForm.getText())
                      .as("The menu element 'Contact form' is displayed as 'Contact form'")
                      .isEqualTo("Contact form");

        WebElement leftMenuService = webDriver.findElement(
            By.xpath("//ul[@class='sidebar-menu left']//a/span[contains(text(), 'Service')]"));
        softAssertions.assertThat(leftMenuService.getText())
                      .as("The menu element 'Service' is displayed as 'Service'")
                      .isEqualTo("Service");

        WebElement leftMenuElementPacks = webDriver.findElement(
            By.xpath("//ul[@class='sidebar-menu left']//a/span[contains(text(), 'Elements packs')]"));
        softAssertions.assertThat(leftMenuElementPacks.getText())
                      .as("The menu element 'Elements packs' is displayed as 'Elements packs'")
                      .isEqualTo("Elements packs");

        WebElement leftMenuMetalsAndColors = webDriver.findElement(
            By.xpath("//ul[@class='sidebar-menu left']//a/span[contains(text(), 'Metals & Colors')]"));
        softAssertions.assertThat(leftMenuMetalsAndColors.getText())
                      .as("The menu element 'Metals & Colors' is displayed as 'Metals & Colors'")
                      .isEqualTo("Metals & Colors");

        // 12. Close Browser
        webDriver.close();

        softAssertions.assertAll();
    }
}
