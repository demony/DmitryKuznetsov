package com.epam.tc.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorMultiplyTests {

    Calculator calculator = new Calculator();

    @Test(dataProvider = "dataCanBeConvertedToLongDatatype", dataProviderClass = CalculatorDataProvider.class)
    public void multiply_isCorrect_WhenLongDatatypeAsParams(String a, String b) {
        long expected = Long.parseLong(a) * Long.parseLong(b);
        long actual = calculator.mult(Long.parseLong(a), Long.parseLong(b));
        assertThat(actual)
            .as("Check that " + a + "*" + b + " = " + expected + " (using type long)")
            .isEqualTo(expected);
    }

    @Test(dataProvider = "dataCanBeConvertedToDoubleDatatype", dataProviderClass = CalculatorDataProvider.class)
    public void  multiply_isCorrect_WhenDoubleDatatypeAsParams(String a, String b) {
        double expected = Double.parseDouble(a) * Double.parseDouble(b);
        double actual = calculator.mult(Double.parseDouble(a), Double.parseDouble(b));
        assertThat(actual)
            .as("Check that " + a + "*" + b + " = " + expected + " (using type double)")
            .isEqualTo(expected);
    }

}
