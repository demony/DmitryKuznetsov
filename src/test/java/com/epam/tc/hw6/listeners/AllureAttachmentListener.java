package com.epam.tc.hw6.listeners;

import com.epam.tc.hw6.utils.AttachmentUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureAttachmentListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult tr) {
        Object webDriver = tr.getTestContext().getAttribute("webDriver");
        if (webDriver != null) {
            SessionId session = ((ChromeDriver) webDriver).getSessionId();
            System.out.println("Session(on test failure) id: " + session.toString());
            AttachmentUtils.makeScreenshotAttachment("Problem1",
                 ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
        } else {
            throw new RuntimeException("No WebDriver!!!");
        }
    }
}
