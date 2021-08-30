package com.epam.tc.hw6;

import com.epam.tc.hw6.driver.WebDriverSingleton;
import com.epam.tc.hw6.teststeps.CommonTestSteps;
import com.epam.tc.hw6.teststeps.DifferentElementsTestSteps;
import com.epam.tc.hw6.teststeps.SiteStructureTestSteps;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    public SoftAssertions softAssertions;
    public PropertiesForTests properties;
    public CommonTestSteps commonTestSteps;
    public DifferentElementsTestSteps differentElementsTestSteps;
    public SiteStructureTestSteps siteStructureTestSteps;

    @BeforeMethod
    public void setupClass(ITestContext context) {
        softAssertions = new SoftAssertions();
        properties = new PropertiesForTests();
        WebDriver webDriver = WebDriverSingleton.getDriver();
        context.setAttribute("webDriver", webDriver);
        webDriver.manage().window().maximize();
        commonTestSteps = new CommonTestSteps(webDriver);
        commonTestSteps.setSoftAssertions(softAssertions);
        differentElementsTestSteps = new DifferentElementsTestSteps(webDriver);
        differentElementsTestSteps.setSoftAssertions(softAssertions);
        siteStructureTestSteps = new SiteStructureTestSteps(webDriver);
        siteStructureTestSteps.setSoftAssertions(softAssertions);
    }

    @AfterMethod
    public void teardown() {
        WebDriverSingleton.closeDriver();
    }
}
