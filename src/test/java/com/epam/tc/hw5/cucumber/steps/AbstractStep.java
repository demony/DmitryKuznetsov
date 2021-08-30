package com.epam.tc.hw5.cucumber.steps;

import com.epam.tc.hw5.PropertiesForTests;
import com.epam.tc.hw5.cucumber.context.TestContext;
import com.epam.tc.hw5.pages.DifferentElementsPage;
import com.epam.tc.hw5.pages.HomePage;
import com.epam.tc.hw5.pages.UserTablePage;
import org.openqa.selenium.WebDriver;

public class AbstractStep {

    protected PropertiesForTests properties;
    protected HomePage homePage;
    protected DifferentElementsPage differentElementsPage;
    protected UserTablePage userTablePage;

    public AbstractStep() {
        WebDriver driver = TestContext.getInstance().getTestObject("web_driver");
        properties = new PropertiesForTests();
        homePage = new HomePage(driver);
        differentElementsPage = new DifferentElementsPage(driver);
        userTablePage = new UserTablePage(driver);
    }
}
