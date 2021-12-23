package com.example.newcalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void evaluationIsCorrect1() {
        String inputString = "3+5-8+2";

        double expectedValue = 2;
        double actualValue = MainActivity.eval(inputString);
        double delta = 0;

        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void evaluationIsCorrect2() {
        String inputString = "3+5";

        double expectedValue = 8;
        double actualValue = MainActivity.eval(inputString);
        double delta = 0;

        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void evaluationIsCorrect3() {
        String inputString = "0-1";

        double expectedValue = -1;
        double actualValue = MainActivity.eval(inputString);
        double delta = 0;

        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void evaluationIsCorrect4() {
        String inputString = "8÷2";

        double expectedValue = 4;
        double actualValue = MainActivity.eval(inputString);
        double delta = 0;

        assertEquals(expectedValue, actualValue, delta);
    }
}