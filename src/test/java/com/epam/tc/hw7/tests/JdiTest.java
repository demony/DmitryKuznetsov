package com.epam.tc.hw7.tests;

import com.epam.tc.hw7.TestInit;
import com.epam.tc.hw7.dataproviders.MetalsColorsDataProvider;
import com.epam.tc.hw7.entities.LeftMenuData;
import com.epam.tc.hw7.entities.MetalsColorsDataSet;
import com.epam.tc.hw7.entities.User;
import com.epam.tc.hw7.steps.TestSteps;
import org.testng.annotations.Test;

public class JdiTest implements TestInit {

    @Test(dataProvider = "metalsColorsDataSet", dataProviderClass = MetalsColorsDataProvider.class)
    public void loginSimpleTest(String datasetName, MetalsColorsDataSet metalsColorsDataSet) {

        // Login on JDI site as User
        TestSteps.login(User.USER_ROMAN);

        // Open Metals & Colors page by Header menu
        TestSteps.openMenu(LeftMenuData.MetalsColors);

        // Fill form Metals & Colors by data below: data[%s]
        TestSteps.fillFromMetalsAndColors(metalsColorsDataSet);

        // Submit form Metals & Colors
        // Result sections should contain data below:
        //  Summary: sum(data[%s].summary)
        //  Elements: data[%s].elements
        //  Color: data[%s].color
        //  Metal: data[%s].metal
        //  Vegetables: data[%s].vegetables
        TestSteps.submitMetalsAndColorsForm();
        TestSteps.assertThatResultSectionIsCorrect(metalsColorsDataSet);
    }
}
