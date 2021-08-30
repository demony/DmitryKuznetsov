package com.epam.tc.hw6.exercise1;

import com.epam.tc.hw6.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Site structure should be correct and selectors should work properly also")
public class SiteStructureTests extends BaseTest {

    @Test
    @Description("Test that checks site structure (exercise 1)")
    @Story("Site structure should be correct")
    public void siteStructure_isCorrect() {

        // 1. Open test site by URL
        commonTestSteps.openWebSite(properties.getUrlMainPage());

        // 2. Assert Browser title
        commonTestSteps.assertThatTitleIsCorrect("Home Page");

        // 3. Perform login
        commonTestSteps.login(properties.getUserLogin(), properties.getUserPassword());

        //  4. Assert Username
        commonTestSteps.assertUserName(properties.getUserName());

        // 5. Assert that there are 4 items on the header section are displayed, and they have proper texts
        siteStructureTestSteps.assertThatHeaderMenuIsCorrect();

        // 6. Assert that there are 4 images on the Index Page, and they are displayed
        siteStructureTestSteps.assertThatImagesOnIndexPageAreCorrect();

        // 8. Assert that there is the iframe with “Frame Button” exist
        siteStructureTestSteps.assertThatIframeWithFrameButtonExists();

        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        siteStructureTestSteps.switchToIframeAndAssertThatFrameButtonExists();

        // 10. Switch to original window back
        siteStructureTestSteps.switchToOriginalWindowBackFromIframe();

        // 11. Assert that there are 5 items in the Left Section are displayed, and they have proper text
        siteStructureTestSteps.assertThatLeftMenuIsCorrect();

        softAssertions.assertAll();
    }
}
