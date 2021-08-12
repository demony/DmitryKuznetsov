package com.epam.tc.hw1;

import org.testng.annotations.DataProvider;

public class CalculatorDataProvider {

    @DataProvider(name = "dataCanBeConvertedToLongDatatype")
    public static Object[][] getDataForDivideTest_ForLongDatatype() {
        return new Object[][] {
            {"8", "4"},
            {"6", "1"},
            {"-8", "-4"},
            {"-8", "4"},
            {"8", "-4"},
            {"0", "4000"},
        };
    }

    @DataProvider(name = "dataCanBeConvertedToDoubleDatatype")
    public static Object[][] getDataForDivideTest_ForDoubleDatatype() {
        return new Object[][] {
            {"8.34", "4.2"},
            {"6.0", "1.34"},
            {"-8.11", "-4.23"},
            {"-8.3", "4.98723"},
            {"8.1223423", "-4.00001"},
            {"0.000", "4.1424234"},
        };
    }

}
