package com.epam.tc.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.Test;

public class CalculatorMultiplyTests {

    Calculator calculator = new Calculator();

    @Test
    public void multiplyTest() {
        long expected = 4;
        long actual = calculator.mult(2, 2);
        assertThat(actual)
            .as("Check that 2 * 2 = 4")
            .isEqualTo(expected);
    }
}
