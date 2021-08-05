package com.epam.tc.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.Test;

public class CalculatorSumTests {

    @Test
    public void sumTest() {
        long expected = 11;
        Calculator calculator = new Calculator();
        long actual = calculator.sum(8, 2);
        assertThat(actual)
            .as("Check that 10 = 8 + 2")
            .isEqualTo(expected);
    }

}
