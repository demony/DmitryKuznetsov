package com.epam.tc.hw4.teststeps;

import com.epam.tc.hw4.pageobjects.BenefitsElements;
import com.epam.tc.hw4.pageobjects.HeaderMenuElements;
import com.epam.tc.hw4.pageobjects.IframeElements;
import com.epam.tc.hw4.pageobjects.LeftMenuElements;
import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;

public class SiteStructureTestSteps {

    private final HeaderMenuElements headerMenuElements;
    private final BenefitsElements benefitsElements;
    private final IframeElements iframeElements;
    private final LeftMenuElements leftMenuElements;
    private SoftAssertions softAssertions;

    public SiteStructureTestSteps(WebDriver webDriver) {
        headerMenuElements = new HeaderMenuElements(webDriver);
        benefitsElements = new BenefitsElements(webDriver);
        iframeElements = new IframeElements(webDriver);
        leftMenuElements = new LeftMenuElements(webDriver);
    }

    public void setSoftAssertions(SoftAssertions softAssertions) {
        this.softAssertions = softAssertions;
    }

    @Step("Check header menu elements")
    public void assertThatHeaderMenuIsCorrect() {

        List<String> headerMenuNamesExpected = new ArrayList<>();
        headerMenuNamesExpected.add("HOME");
        headerMenuNamesExpected.add("CONTACT FORM");
        headerMenuNamesExpected.add("SERVICE");
        headerMenuNamesExpected.add("METALS & COLORS");

        List<String> headerMenuNames = headerMenuElements.getHeaderMenuElementsAll();

        softAssertions.assertThat(headerMenuNames.size())
                      .as("The menu in header section should have "
                          + headerMenuNamesExpected.size() + " top elements")
                      .isEqualTo(headerMenuNamesExpected.size());

        softAssertions.assertThat(headerMenuNames)
                      .as("The menu in header correct names")
                      .hasSameElementsAs(headerMenuNamesExpected);

        List<String> headerMenuNamesVisible = headerMenuElements.getHeaderMenuElementsThatIsVisible();
        softAssertions.assertThat(headerMenuNamesVisible)
                      .as("The menu elements should be all visible")
                      .hasSameElementsAs(headerMenuNamesExpected);

    }

    @Step("Check images on index page")
    public void assertThatImagesOnIndexPageAreCorrect() {

        int imagesOnIndexPageExpected = 4;
        softAssertions.assertThat(benefitsElements.countBenefitsIcons())
                      .as(" There are should be 4 images on the Index Page")
                      .isEqualTo(imagesOnIndexPageExpected);

        softAssertions.assertThat(benefitsElements.countBenefitsIconsThatIsVisible())
                      .as("There are should be "
                          + imagesOnIndexPageExpected + " visible images on the Index Page")
                      .isEqualTo(imagesOnIndexPageExpected);

        // 7. Assert that there are 4 texts on the Index Page under icons, and they have proper text
        List<String> textsUnderBenefitIconsExpected = new ArrayList<>();
        textsUnderBenefitIconsExpected.add("To include good practices\nand ideas from successful\nEPAM project");
        textsUnderBenefitIconsExpected.add("To be flexible and\ncustomizable");
        textsUnderBenefitIconsExpected.add("To be multiplatform");
        textsUnderBenefitIconsExpected.add("Already have good base\n(about 20 internal and\n"
            + "some external projects),\nwish to get more…");

        List<String> textsUnderBenefitIcons = benefitsElements.getTextsUnderBenefitIcons();
        softAssertions.assertThat(textsUnderBenefitIcons.size())
                      .as(" There are should be "
                          + textsUnderBenefitIconsExpected.size() + " texts on the Index Page under icons")
                      .isEqualTo(textsUnderBenefitIconsExpected.size());

        softAssertions.assertThat(textsUnderBenefitIcons)
                      .as("The texts under images equal to expected")
                      .hasSameElementsAs(textsUnderBenefitIconsExpected);

        List<String> textsUnderBenefitIconsThatIsVisible = benefitsElements.getTextsUnderBenefitIconsThatIsVisible();
        softAssertions.assertThat(textsUnderBenefitIconsThatIsVisible)
                      .as("The texts under images are visible")
                      .hasSameElementsAs(textsUnderBenefitIconsExpected);
    }

    @Step("Check that iframe with 'Frame Button' exists")
    public void assertThatIframeWithFrameButtonExists() {
        Boolean iframeExits = iframeElements.iframeExists();
        softAssertions.assertThat(iframeExits)
                      .as("The iframe with should exists").isTrue();
    }

    @Step("Switch to iframe and check that 'Iframe Button' exists")
    public void switchToIframeAndAssertThatFrameButtonExists() {
        iframeElements.switchToIframe();
        softAssertions.assertThat(iframeElements.frameButtonExists())
                      .as("The 'Frame Button' should exists").isTrue();
    }

    @Step("Switch to original window back")
    public void switchToOriginalWindowBackFromIframe() {
        Boolean focusOnOriginalWindow = iframeElements.switchToOriginalWindow();
        softAssertions.assertThat(focusOnOriginalWindow)
                      .as("Driver has focus on the original window")
                      .isTrue();
    }

    @Step("Assert that left menu is correct")
    public void assertThatLeftMenuIsCorrect() {

        List<String> menuElementsLeftSideBarExpected = new ArrayList<>();
        menuElementsLeftSideBarExpected.add("Home");
        menuElementsLeftSideBarExpected.add("Contact form");
        menuElementsLeftSideBarExpected.add("Service");
        menuElementsLeftSideBarExpected.add("Metals & Colors");
        menuElementsLeftSideBarExpected.add("Elements packs");

        List<String> leftMenuSideBarElements = leftMenuElements.getLeftMenuSideBarElements();

        softAssertions.assertThat(leftMenuSideBarElements.size())
                      .as("The left menu should contain "
                          + menuElementsLeftSideBarExpected.size() + " elements")
                      .isEqualTo(menuElementsLeftSideBarExpected.size());

        softAssertions.assertThat(leftMenuSideBarElements)
                      .as("Left menu should contain correct item names")
                      .hasSameElementsAs(menuElementsLeftSideBarExpected);

        List<String> leftMenuElementsVisible = leftMenuElements.getLeftMenuSideBarElementsVisible();
        softAssertions.assertThat(leftMenuElementsVisible)
                      .as("The left menu element should be visible")
                      .hasSameElementsAs(menuElementsLeftSideBarExpected);
    }

    @Step("Open 'Different Elements Page")
    public void openDifferentElementsPage(String urlForCheckCorrectPage) {

        headerMenuElements.openPageDifferentElements();

        softAssertions.assertThat(headerMenuElements.getCurrentUrl())
                      .as("Different Elements Page is opened")
                      .isEqualTo(urlForCheckCorrectPage);
    }

}
