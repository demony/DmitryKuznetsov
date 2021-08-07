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

    @Test(dataProvider = "dataCanBeConvertedToLongDatatype", dataProviderClass = CalculatorDataProvider.class)
    public void divide_isCorrect_WhenLongDatatypeAsParams(String a, String b) {
        long expected = Long.parseLong(a) / Long.parseLong(b);
        long actual = calculator.div(Long.parseLong(a), Long.parseLong(b));
        assertThat(actual)
            .as("Check that " + a + "/" + b + " = " + expected + " (using type long)")
            .isEqualTo(expected);
    }

    @Test(dataProvider = "dataCanBeConvertedToDoubleDatatype", dataProviderClass = CalculatorDataProvider.class)
    public void  divide_isCorrect_WhenDoubleDatatypeAsParams(String a, String b) {
        double expected = Double.parseDouble(a) / Double.parseDouble(b);
        double actual = calculator.div(Double.parseDouble(a), Double.parseDouble(b));
        assertThat(actual)
            .as("Check that " + a + "/" + b + " = " + expected + " (using type double)")
            .isEqualTo(expected);
    }

}
