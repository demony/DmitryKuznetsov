package com.epam.tc.hw1;

import org.testng.annotations.DataProvider;

public class CalculatorDataProvider {

    @DataProvider(name = "dataCanBeConvertedToLongDatatype")
    public Object[][] getDataForDivideTest_ForLongDatatype() {
        Object[][] dataForDivideTest = {
            {"8", "4"},
            {"6", "1"},
            {"-8", "-4"},
            {"-8", "4"},
            {"8", "-4"},
            {"0", "4000"},
        };
        return dataForDivideTest;
    }

    @DataProvider(name = "dataCanBeConvertedToDoubleDatatype")
    public Object[][] getDataForDivideTest_ForDoubleDatatype() {
        Object[][] dataForDivideTest = {
            {"8.34", "4.2"},
            {"6.0", "1.34"},
            {"-8.11", "-4.23"},
            {"-8.3", "4.98723"},
            {"8.1223423", "-4.00001"},
            {"0.000", "4.1424234"},
        };
        return dataForDivideTest;
    }

}
