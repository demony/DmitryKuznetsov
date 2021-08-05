package com.epam.tc.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.Test;

public class CalculatorMultiplyTest {
    @Test
    public void multiplyTest() {
        long expected = 4;
        Calculator calculator = new Calculator();
        long actual = calculator.mult(2, 2);
        assertThat(actual)
            .as("Check that 2 * 2 = 4")
            .isEqualTo(expected);
    }
}
