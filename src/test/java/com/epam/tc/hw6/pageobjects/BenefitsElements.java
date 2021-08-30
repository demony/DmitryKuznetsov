package com.epam.tc.hw6.pageobjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BenefitsElements extends BaseElements {

    @FindBy(xpath = "//span[contains(@class,'icons-benefit')]")
    private List<WebElement> imagesOnIndexPage;

    @FindBy(xpath = "//div[@class ='benefit-icon']//..//preceding-sibling::span[@class='benefit-txt']")
    private List<WebElement> textsUnderIcons;

    public BenefitsElements(WebDriver webDriver) {
        super(webDriver);
    }

    public int countBenefitsIcons() {
        return imagesOnIndexPage.size();
    }

    public int countBenefitsIconsThatIsVisible() {
        int countVisible = 0;
        for (WebElement webElement : imagesOnIndexPage) {
            if (webElement.isDisplayed()) {
                countVisible++;
            }
        }
        return countVisible;
    }

    public List<String> getTextsUnderBenefitIcons() {
        List<String> textsUnderIconsAsStrings = new ArrayList<>();
        textsUnderIcons.forEach(webElement -> textsUnderIconsAsStrings.add(webElement.getText()));
        return  textsUnderIconsAsStrings;
    }

    public List<String> getTextsUnderBenefitIconsThatIsVisible() {
        List<String> textsUnderIconsAsStrings = new ArrayList<>();
        for (WebElement webElement : textsUnderIcons) {
            if (webElement.isDisplayed()) {
                textsUnderIconsAsStrings.add(webElement.getText());
            }
        }
        return  textsUnderIconsAsStrings;
    }
}
