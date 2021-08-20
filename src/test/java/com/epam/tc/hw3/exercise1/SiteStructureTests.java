package com.epam.tc.hw3.exercise1;

import com.epam.tc.hw3.BaseTest;
import com.epam.tc.hw3.CommonTestSteps;
import com.epam.tc.hw3.pageobjects.elements.BenefitsElements;
import com.epam.tc.hw3.pageobjects.elements.HeaderMenuElements;
import com.epam.tc.hw3.pageobjects.elements.IframeElements;
import com.epam.tc.hw3.pageobjects.elements.LeftMenuElements;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SiteStructureTests extends BaseTest {

    @Test
    public void siteStructure_isCorrect() {
        SoftAssertions softAssertions = new SoftAssertions();

        CommonTestSteps commonTestSteps = new CommonTestSteps(mainPage, softAssertions);

        // 1. Open test site by URL
        commonTestSteps.openWebSite(properties.getUrlMainPage());

        // 2. Assert Browser title
        commonTestSteps.assertThatTitleIsCorrect("Home Page");

        // 3. Perform login
        commonTestSteps.login(properties.getUserLogin(), properties.getUserPassword());

        //  4. Assert Username
        commonTestSteps.assertUserName(properties.getUserName());

        // 5. Assert that there are 4 items on the header section are displayed, and they have proper texts
        HeaderMenuElements headerMenuElements = mainPage.getHeaderMenuElements();
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

        // 6. Assert that there are 4 images on the Index Page, and they are displayed
        BenefitsElements benefitsElements = mainPage.getBenefitsElements();

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

        IframeElements iframe = mainPage.getIframeElements();

        // 8. Assert that there is the iframe with “Frame Button” exist
        boolean iframeExits = iframe.iframeExists();
        softAssertions.assertThat(iframeExits)
                      .as("The iframe with should exists").isTrue();

        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe

        iframe.switchToIframe();
        softAssertions.assertThat(iframe.frameButtonExists())
                      .as("The 'Frame Button' should exists").isTrue();

        // 10. Switch to original window back
        boolean focusOnOriginalWindow = iframe.switchToOriginalWindow();
        softAssertions.assertThat(focusOnOriginalWindow)
                      .as("Driver has focus on the original window")
                      .isTrue();

        // 11. Assert that there are 5 items in the Left Section are displayed, and they have proper text
        LeftMenuElements leftMenuElements = mainPage.getLeftMenuElements();

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

        // 12. Close Browser
        commonTestSteps.closeBrowser();

        softAssertions.assertAll();

    }

}
