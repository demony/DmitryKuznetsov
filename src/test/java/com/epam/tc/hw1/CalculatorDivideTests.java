package com.epam.tc.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.Test;

public class CalculatorDivideTests {
    @Test
    public void divideTest() {
        long expected = 2;
        Calculator calculator = new Calculator();
        long actual = calculator.div(4, 2);
        assertThat(expected)
            .as("Check that 4/2 = 2").isEqualTo(actual);
    }
}
