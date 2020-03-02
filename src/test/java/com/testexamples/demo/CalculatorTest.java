package com.testexamples.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.testexamples.demo.exceptions.DivisionByZeroException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class CalculatorTest {

    @InjectMocks
    Calculator calculator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
                "4, 2, 6",
                "6, 3, 9",
                "18, 22, 40"
    })
	void testSum(int a, int b, int c) {
        assertEquals(c, calculator.sum(a, b));
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
        "5, 7, 35",
        "9, 9, 81",
        "3, 0, 0"
    })
	void testMultiplication(int a, int b, int c) {
        assertEquals(c, calculator.multiply(a, b));
    }

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
        "15, 8, 7",
        "6, 20, -14",
        "10, 0, 10"
    })
	void testSubstraction(int a, int b, int c) {
        assertEquals(c, calculator.substract(a, b));
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
        "21, 3, 7",
        "24, 5, 4",
        "89, 1, 89"
    })
	void testDivision(int a, int b, int c) throws DivisionByZeroException {
        assertEquals(c, calculator.divide(a, b));
    }

    @Test
    void testExceptionThrownInDivisionByZero() {
        assertThrows(DivisionByZeroException.class, () -> calculator.divide(50, 0));
    }

}
