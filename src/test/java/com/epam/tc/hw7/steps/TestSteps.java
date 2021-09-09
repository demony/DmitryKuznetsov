package com.epam.tc.hw7.steps;

import static com.epam.tc.hw7.site.SiteJdi.header;
import static com.epam.tc.hw7.site.SiteJdi.homePage;
import static com.epam.tc.hw7.site.SiteJdi.loginForm;
import static com.epam.tc.hw7.site.SiteJdi.menu;
import static com.epam.tc.hw7.site.SiteJdi.metalsColorsPage;
import static com.epam.tc.hw7.site.pages.MetalsAndColorsPage.metalsAndColorsForm;
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

        metalsAndColorsForm.summary.select(metalsColorsDataSet.summary[0].toString());
        metalsAndColorsForm.summary.select(metalsColorsDataSet.summary[0].toString());
        metalsAndColorsForm.summary.select(metalsColorsDataSet.summary[1].toString());
        for (String element : metalsColorsDataSet.elements) {
            metalsAndColorsForm.elements.select(element);
        }
        metalsAndColorsForm.metals.select(metalsColorsDataSet.metals);
        metalsAndColorsForm.colors.select(metalsColorsDataSet.color);
        // deselect vegetables fields (some of them are already selected by default)
        metalsAndColorsForm.vegetables.select(metalsAndColorsForm.vegetables.selected());
        for (String vegetable : metalsColorsDataSet.vegetables) {
            metalsAndColorsForm.vegetables.select(vegetable);
        }
        assertThatAllSelectorsAreCorrectlySet(metalsColorsDataSet);
    }

    public static void assertThatAllSelectorsAreCorrectlySet(MetalsColorsDataSet metalsColorsDataSet) {
        metalsAndColorsForm.elements.is().selected(String.join(",", metalsColorsDataSet.elements));
        metalsAndColorsForm.metals.is().selected(metalsColorsDataSet.metals);
        metalsAndColorsForm.colors.is().selected(metalsColorsDataSet.color);
        assertThat(metalsAndColorsForm.vegetables.getText())
            .isEqualTo(String.join(", ", metalsColorsDataSet.vegetables));
    }

    @Step
    public static void submitMetalsAndColorsForm() {
        metalsAndColorsForm.submit.click();
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
