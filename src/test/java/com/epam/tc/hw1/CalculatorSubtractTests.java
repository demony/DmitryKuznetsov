package com.epam.tc.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.Test;

public class CalculatorSubtractTests {
    @Test
    public void subTest() {
        long expected = 1;
        Calculator calculator = new Calculator();
        long actual = calculator.sub(4, 3);
        assertThat(actual)
            .as("Check that 4 - 3 = 1")
            .isEqualTo(expected);
    }
}
