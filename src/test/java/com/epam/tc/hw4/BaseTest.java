package com.epam.tc.hw4;

import com.epam.tc.hw4.teststeps.CommonTestSteps;
import com.epam.tc.hw4.teststeps.DifferentElementsTestSteps;
import com.epam.tc.hw4.teststeps.SiteStructureTestSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    private static WebDriver webDriver;
    public SoftAssertions softAssertions;
    public PropertiesForTests properties;
    public CommonTestSteps commonTestSteps;
    public DifferentElementsTestSteps differentElementsTestSteps;
    public SiteStructureTestSteps siteStructureTestSteps;

    @BeforeClass
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupClass(ITestContext context) {

        softAssertions = new SoftAssertions();

        properties = new PropertiesForTests();

        webDriver = new ChromeDriver();
        context.setAttribute("webDriver", webDriver);

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(properties.getImplicitlyWait(), TimeUnit.SECONDS);

        SessionId session = ((ChromeDriver) webDriver).getSessionId();
        System.out.println("Session(setup in BeforeClass) id: " + session.toString());

        commonTestSteps = new CommonTestSteps(webDriver);
        commonTestSteps.setSoftAssertions(softAssertions);

        differentElementsTestSteps = new DifferentElementsTestSteps(webDriver);
        differentElementsTestSteps.setSoftAssertions(softAssertions);

        siteStructureTestSteps = new SiteStructureTestSteps(webDriver);
        siteStructureTestSteps.setSoftAssertions(softAssertions);

    }

    @AfterMethod
    public void teardown() {
        webDriver.close();
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
