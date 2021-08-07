package com.epam.tc.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorDivideTests {

    Calculator calculator = new Calculator();

    @Test
    public void divide_ThrowsCorrectException_WhenDivisionByZeroParamsLongDatatype() {
        assertThatThrownBy(() -> {
            long actual = calculator.div(1L, 0L);
        }).isInstanceOf(NumberFormatException.class).hasMessage("Attempt to divide by zero");
    }

    @Test
    public void divide_ThrowsCorrectException_WhenDivisionByZeroParamsDoubleDatatype() {
        assertThatThrownBy(() -> {
            long actual = calculator.div(1 / 3, 0 / 3);
        }).isInstanceOf(NumberFormatException.class).hasMessage("Attempt to divide by zero");
    }

    @Test(dataProvider = "dataCanBeConvertedToLongDatatype")
    public void divide_isCorrect_WhenLongDatatypeAsParams(String a, String b) {
        long expected = Long.parseLong(a) / Long.parseLong(b);
        long actual = calculator.div(Long.parseLong(a), Long.parseLong(b));
        assertThat(actual)
            .as("Check that " + a + "/" + b + " = " + expected + " (using type long)")
            .isEqualTo(expected);
    }

    @Test(dataProvider = "dataCanBeConvertedToDoubleDatatype")
    public void  divide_isCorrect_WhenDoubleDatatypeAsParams(String a, String b) {
        double expected = Double.parseDouble(a) / Double.parseDouble(b);
        double actual = calculator.div(Double.parseDouble(a), Double.parseDouble(b));
        assertThat(actual)
            .as("Check that " + a + "/" + b + " = " + expected + " (using type double)")
            .isEqualTo(expected);
    }

    @DataProvider(name = "dataCanBeConvertedToLongDatatype")
    public Object[][] getDataForDivideTest_LongDatatype() {
        Object[][] dataForDivideTest = new Object[5][2];

        dataForDivideTest[0][0] = "8";
        dataForDivideTest[0][1] = "4";

        dataForDivideTest[1][0] = "6";
        dataForDivideTest[1][1] = "1";

        dataForDivideTest[2][0] = "0";
        dataForDivideTest[2][1] = "1";

        dataForDivideTest[3][0] = Long.toString(Long.MAX_VALUE);
        dataForDivideTest[3][1] = "1";

        dataForDivideTest[4][0] = Long.toString(Long.MAX_VALUE);
        dataForDivideTest[4][1] = Long.toString(Long.MIN_VALUE);

        return dataForDivideTest;
    }

    @DataProvider(name = "dataCanBeConvertedToDoubleDatatype")
    public Object[][] getDataForDivideTest_DoubleDatatype() {
        Object[][] dataForDivideTest = new Object[5][2];

        dataForDivideTest[0][0] = "8.6";
        dataForDivideTest[0][1] = "4.2";

        dataForDivideTest[1][0] = "6.3";
        dataForDivideTest[1][1] = "1.0";

        dataForDivideTest[2][0] = "0.000";
        dataForDivideTest[2][1] = "121.000";

        dataForDivideTest[3][0] = Double.toString(Double.MAX_VALUE);
        dataForDivideTest[3][1] = "1.0";

        dataForDivideTest[4][0] = Double.toString(Double.MIN_VALUE);
        dataForDivideTest[4][1] = Double.toString(Double.MIN_VALUE);

        return dataForDivideTest;
    }

}
