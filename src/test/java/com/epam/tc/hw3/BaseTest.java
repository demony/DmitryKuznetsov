package com.epam.tc.hw3;

import com.epam.tc.hw3.pageobjects.pages.DifferentElementsPage;
import com.epam.tc.hw3.pageobjects.pages.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected WebDriver webDriver;
    protected PropertiesForTests properties;
    protected MainPage mainPage;
    protected DifferentElementsPage differentElementsPage;

    @BeforeClass
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {

        properties = new PropertiesForTests();

        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(properties.getImplicitlyWait(), TimeUnit.SECONDS);

        mainPage = new MainPage(webDriver);
        differentElementsPage = new DifferentElementsPage(webDriver);

    }

    @AfterMethod
    public void teardown() {
        webDriver.close();
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
