package com.epam.tc.hw6.driver;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WebDriverSingleton {

    private static WebDriver webDriver;

    public static WebDriver getDriver() {
        String driverType = System.getProperty("driver.type", "local");
        String browserName = System.getProperty("browser.name", "chrome");
        if (Objects.isNull(webDriver)) {
            webDriver = WebDriverFactory.createWebDriver(driverType, browserName);
        }
        return webDriver;
    }

    public static void closeDriver() {
        webDriver.quit();
        webDriver = null;
    }
}
