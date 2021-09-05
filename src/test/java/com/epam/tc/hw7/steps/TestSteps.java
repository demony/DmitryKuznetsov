package com.epam.tc.hw7.steps;

import static com.epam.tc.hw7.site.SiteJdi.header;
import static com.epam.tc.hw7.site.SiteJdi.homePage;
import static com.epam.tc.hw7.site.SiteJdi.loginForm;
import static com.epam.tc.hw7.site.SiteJdi.menu;
import static com.epam.tc.hw7.site.SiteJdi.metalsColorsPage;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.epam.jdi.light.elements.common.UIElement;
import com.epam.tc.hw7.entities.LeftMenuData;
import com.epam.tc.hw7.entities.MetalsColorsDataSet;
import com.epam.tc.hw7.entities.User;
import io.qameta.allure.Step;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TestSteps {

    @Step
    public static void login(User user) {
        homePage.shouldBeOpened();
        if (!header.userName.isDisplayed()) {
            header.userIcon.click();
            loginForm.loginAs(user);
            header.userName.is().text(user.fullName.toUpperCase(Locale.ROOT));
        }
    }

    @Step
    public static void openMenu(LeftMenuData metalsColors) {
        menu.select(metalsColors);
        metalsColorsPage.isOpened();
    }

    @Step
    public static void fillFromMetalsAndColors(MetalsColorsDataSet metalsColorsDataSet) {
        metalsColorsPage.summary.select(metalsColorsDataSet.summary[0].toString());
        metalsColorsPage.summary.select(metalsColorsDataSet.summary[1].toString());
        for (String element : metalsColorsDataSet.elements) {
            metalsColorsPage.elements.select(element);
        }
        metalsColorsPage.metals.select(metalsColorsDataSet.metals);
        metalsColorsPage.colors.select(metalsColorsDataSet.color);
        // deselect vegetables fields (some of them are already selected by default)
        metalsColorsPage.vegetables.select(metalsColorsPage.vegetables.selected());
        for (String vegetable : metalsColorsDataSet.vegetables) {
            metalsColorsPage.vegetables.select(vegetable);
        }
        assertThatAllSelectorsAreCorrectlySet(metalsColorsDataSet);
    }

    public static void assertThatAllSelectorsAreCorrectlySet(MetalsColorsDataSet metalsColorsDataSet) {
        metalsColorsPage.elements.selected().equals(String.join(",", metalsColorsDataSet.elements));
        metalsColorsPage.metals.is().selected(metalsColorsDataSet.metals);
        metalsColorsPage.colors.is().selected(metalsColorsDataSet.color);
        assertThat(metalsColorsPage.vegetables.getText())
            .isEqualTo(String.join(", ", metalsColorsDataSet.vegetables));
    }

    @Step
    public static void submitMetalsAndColorsForm() {
        metalsColorsPage.submit.click();
    }

    @Step
    public static void assertThatResultSectionIsCorrect(MetalsColorsDataSet metalsColorsDataSet) {
        List<String> actualResult = metalsColorsPage.results
            .stream().map(UIElement::getText).collect(Collectors.toList());
        List<String> expectedResult = Arrays.asList(
            metalsColorsDataSet.getSummaryAsLog(),
            metalsColorsDataSet.getElementsAsLog(),
            metalsColorsDataSet.getColorAsLog(),
            metalsColorsDataSet.getMetalsAsLog(),
            metalsColorsDataSet.getVegetablesAsLog());
        assertThat(actualResult).as("Check results form").isEqualTo(expectedResult);
    }
}
